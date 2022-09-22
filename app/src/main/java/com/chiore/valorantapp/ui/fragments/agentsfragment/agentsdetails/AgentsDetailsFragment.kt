package com.chiore.valorantapp.ui.fragments.agentsfragment.agentsdetails

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
import com.chiore.valorantapp.adapters.AgentsDetailsRvAdapter
import com.chiore.valorantapp.databinding.FragmentAgentsDetailsBinding
import com.chiore.valorantapp.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AgentsDetailsFragment : Fragment() {

    private var _binding: FragmentAgentsDetailsBinding? = null
    private val binding get() = _binding!!

    private val args: AgentsDetailsFragmentArgs by navArgs()
    private val viewModel: AgentDetailsViewModel by viewModels()

    private lateinit var agentsDetailsRvAdapter: AgentsDetailsRvAdapter

    val TAG = "AgentsDetailsFragment"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentAgentsDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        agentsDetailsRvAdapter = AgentsDetailsRvAdapter()

        viewModel.agentDetails(args.agentId)
        observeAgentDetailsResponse()
        setupAgentsDetailsRv()

    }

    private fun setupAgentsDetailsRv() {
        binding.agentsDetailsRv.apply {
            adapter = agentsDetailsRvAdapter
        }
    }

    private fun observeAgentDetailsResponse() {
        lifecycleScope.launch {
            viewModel.agentDetail.collect { response ->
                when (response) {
                    is Resource.Success -> {
                        response.data?.let { detailsResponse ->

                            binding.apply {
                                Glide.with(root).load(detailsResponse.fullPortrait)
                                    .into(agentsDetailsIv)

                                agentsDetailsNameTv.text = detailsResponse.displayName
                                agentsDetailsRoleTv.text = detailsResponse.role.displayName
                                descriptionTv.text = detailsResponse.description

                                agentsDetailsRvAdapter.submitList(detailsResponse.abilities)
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}