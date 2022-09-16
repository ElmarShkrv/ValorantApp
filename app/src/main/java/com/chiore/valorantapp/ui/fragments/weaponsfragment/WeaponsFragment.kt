package com.chiore.valorantapp.ui.fragments.weaponsfragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.chiore.valorantapp.adapters.WeaponsRvAdapter
import com.chiore.valorantapp.databinding.FragmentWeaponsBinding
import com.chiore.valorantapp.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class WeaponsFragment : Fragment() {

    private var _binding: FragmentWeaponsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: WeaponsViewModel by viewModels()

    private lateinit var weaponsRvAdapter: WeaponsRvAdapter

    val TAG = "WeaponsFragment"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeaponsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        weaponsRvAdapter = WeaponsRvAdapter()

        viewModel.allWeapons()
        observeAllWeaponsResponse()
        setupWeaponsRv()
    }

    private fun setupWeaponsRv() {
        binding.weaponsRv.apply {
            adapter = weaponsRvAdapter
        }
    }

    private fun observeAllWeaponsResponse() {
        lifecycleScope.launch {
            viewModel.allWeapons.collect { response ->
                when(response) {
                    is Resource.Success -> {
                        //binding.shimmerLayout.visibility = View.GONE
                        response.data?.let { weaponsResponse ->
                            weaponsRvAdapter.submitList(weaponsResponse)
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}