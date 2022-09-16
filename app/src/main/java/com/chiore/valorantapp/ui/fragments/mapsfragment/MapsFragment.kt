package com.chiore.valorantapp.ui.fragments.mapsfragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.chiore.valorantapp.adapters.MapsRvAdapter
import com.chiore.valorantapp.databinding.FragmentMapsBinding
import com.chiore.valorantapp.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MapsFragment : Fragment() {

    private var _binding: FragmentMapsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MapsViewModel by viewModels()

    private lateinit var mapsRvAdapter: MapsRvAdapter

    val TAG = "MapsFragment"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMapsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mapsRvAdapter = MapsRvAdapter()

        viewModel.allMaps()
        observeAllMapsResponse()
        setupMapsRv()

    }

    private fun setupMapsRv() {
        //mapsRvAdapter = MapsRvAdapter()
        binding.mapsRv.apply {
            adapter = mapsRvAdapter
        }
    }

    private fun observeAllMapsResponse() {
        lifecycleScope.launch {
            viewModel.allMaps.collect { response ->
                when (response) {
                    is Resource.Success -> {
                        //binding.shimmerLayout.visibility = View.GONE
                        response.data?.let { mapsResponse ->
                            mapsRvAdapter.submitList(mapsResponse)
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