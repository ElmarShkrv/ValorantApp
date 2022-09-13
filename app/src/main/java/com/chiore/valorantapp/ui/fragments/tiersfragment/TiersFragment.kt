package com.chiore.valorantapp.ui.fragments.tiersfragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.chiore.valorantapp.adapters.TiersRvAdapter
import com.chiore.valorantapp.databinding.FragmentTiersBinding
import com.chiore.valorantapp.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TiersFragment : Fragment() {

    private var _binding: FragmentTiersBinding? = null
    private val binding get() = _binding!!
    private val viewModel: TiersViewModel by viewModels()

    private lateinit var tiersRvAdapter: TiersRvAdapter

    val TAG = "TiersFragment"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTiersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tiersRvAdapter = TiersRvAdapter()

        viewModel.allTiers()
        observeAllTiersResponse()
        setupTiersRv()
    }

    private fun setupTiersRv() {
        binding.tiersRv.apply {
            adapter = tiersRvAdapter
        }
    }

    private fun observeAllTiersResponse() {
        lifecycleScope.launch {
            viewModel.allTiers.collect { response ->
                when(response) {
                    is Resource.Success -> {
                        //binding.shimmerLayout.visibility = View.GONE
                        response.data?.let { tiersResponse ->
                            tiersResponse.map {
                                tiersRvAdapter.submitList(it.tiers)
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
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}