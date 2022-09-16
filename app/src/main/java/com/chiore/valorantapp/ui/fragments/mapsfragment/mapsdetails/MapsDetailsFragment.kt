package com.chiore.valorantapp.ui.fragments.mapsfragment.mapsdetails

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
import com.chiore.valorantapp.databinding.FragmentMapsDetailsBinding
import com.chiore.valorantapp.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MapsDetailsFragment : Fragment() {

    private var _binding: FragmentMapsDetailsBinding? = null
    private val binding get() = _binding!!
    private val args: MapsDetailsFragmentArgs by navArgs()
    private val viewModel: MapDetailsViewModel by viewModels()

    val TAG = "AgentsDetailsFragment"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMapsDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.mapDetails(args.mapsId)
        observeMapDetailsResponse()

    }

    private fun observeMapDetailsResponse() {
        lifecycleScope.launchWhenStarted {
            viewModel.mapDetails.collect { response ->
                when (response) {
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        response.data?.let { detailsResponse ->

                            binding.apply {
                                Glide.with(root).load(detailsResponse.displayIcon)
                                    .into(mapsDetailsIv)

                                mapsDetailsNameTv.text = detailsResponse.displayName
                                mapsDetailsCoordinatesTv.text = detailsResponse.coordinates
                            }
                        }
                    }
                    is Resource.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                        response.message?.let { message ->
                            //binding.shimmerLayout.visibility = View.VISIBLE
                            Log.e(TAG, "An error occured: $message")
                        }
                    }
                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
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