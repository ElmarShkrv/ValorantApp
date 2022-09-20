package com.chiore.valorantapp.ui.fragments.weaponsfragment.weaponsdetails

import android.animation.ObjectAnimator
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.chiore.valorantapp.adapters.WeaponDetailsRvAdapter
import com.chiore.valorantapp.data.model.weapons.DamageRange
import com.chiore.valorantapp.databinding.FragmentWeaponDetailsBinding
import com.chiore.valorantapp.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WeaponDetailsFragment : Fragment() {

    private var _binding: FragmentWeaponDetailsBinding? = null
    private val binding get() = _binding!!
    private val args: WeaponDetailsFragmentArgs by navArgs()
    private val viewModel: WeaponDetailsViewModel by viewModels()

    private lateinit var weaponDetailsRvAdapter: WeaponDetailsRvAdapter

    val TAG = "WeaponDetailsFragment"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentWeaponDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        weaponDetailsRvAdapter = WeaponDetailsRvAdapter()

        viewModel.weaponDetails(args.weaponId)
        observeWeaponDetailsResponse()
        setupWeaponDetailsRv()

    }

    private fun setupWeaponDetailsRv() {
        binding.weaponDetailsSkinRv.apply {
            adapter = weaponDetailsRvAdapter
        }
    }

    private fun observeWeaponDetailsResponse() {
        lifecycleScope.launch {
            viewModel.weaponDetails.collect { response ->
                when (response) {
                    is Resource.Success -> {
                        response.data?.let { detailsResponse ->

                            weaponDetailsRvAdapter.submitList(detailsResponse.skins)

                            binding.apply {
                                Glide.with(root).load(detailsResponse.displayIcon)
                                    .into(weaponDetailsIv)

                                weaponDetailsNameTv.text = detailsResponse.displayName


                                if (detailsResponse.shopData != null) {
                                    weaponDetailsCategoryTv.text = detailsResponse.shopData.category
                                } else {
                                    weaponDetailsCategoryTv.visibility = View.GONE
                                }

                                if (detailsResponse.weaponStats != null) {
                                    detailsResponse.weaponStats.damageRanges.map { damageRange ->
                                        weaponDetailsHeadDamageTv.text =
                                            "Head - ${damageRange.headDamage.toInt()}"
                                        weaponDetailsBodyDamageTv.text =
                                            "Body - ${damageRange.bodyDamage}"
                                        weaponDetailsLegDamageTv.text =
                                            "Leg - ${damageRange.bodyDamage}"

                                        damageRangeProgress(damageRange)
                                    }
                                } else {
                                    topWeaponDetailsTv.visibility = View.GONE
                                    weaponDetailsHeadDamageTv.visibility = View.GONE
                                    weaponDetailsBodyDamageTv.visibility = View.GONE
                                    weaponDetailsLegDamageTv.visibility = View.GONE
                                    headProgressBar.visibility = View.GONE
                                    bodyProgressBar.visibility = View.GONE
                                    legProgressBar.visibility = View.GONE
                                }
                            }

                        }
                    }
                    is Resource.Loading -> {
                        response.message?.let { message ->
                            //binding.shimmerLayout.visibility = View.VISIBLE
                            Log.e(TAG, "An error occured: $message")
                        }
                    }
                    is Resource.Error -> {
                        response.message?.let { message ->
                            Log.e(TAG, "An error occured: $message")
                        }
                    }
                    else -> Unit
                }
            }
        }
    }

    private fun damageRangeProgress(damageRange: DamageRange) {
        binding.headProgressBar.max = 200
        val currentHeadProgressBar = damageRange.headDamage

        ObjectAnimator.ofInt(binding.headProgressBar, "progress", currentHeadProgressBar.toInt())
            .setDuration(2_000)
            .start()

        binding.bodyProgressBar.max = 200
        val currentBodyProgressBar = damageRange.bodyDamage

        ObjectAnimator.ofInt(binding.bodyProgressBar, "progress", currentBodyProgressBar)
            .setDuration(2_000)
            .start()

        binding.legProgressBar.max = 200
        val currentLegProgressBar = damageRange.legDamage

        ObjectAnimator.ofInt(binding.legProgressBar, "progress", currentLegProgressBar.toInt())
            .setDuration(2_000)
            .start()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}