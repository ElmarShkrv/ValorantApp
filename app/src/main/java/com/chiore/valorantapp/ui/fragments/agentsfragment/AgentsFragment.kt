package com.chiore.valorantapp.ui.fragments.agentsfragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chiore.valorantapp.R
import com.chiore.valorantapp.adapters.AgentsRvAdapter
import com.chiore.valorantapp.databinding.FragmentAgentsBinding
import com.chiore.valorantapp.util.CalculateWindowSize
import com.chiore.valorantapp.util.Resource
import com.chiore.valorantapp.util.WindowSizeClass
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AgentsFragment : Fragment() {

    private var _binding: FragmentAgentsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AgentsViewModel by viewModels()

    private lateinit var agentsRvAdapter: AgentsRvAdapter

    //lateinit var widthWindowClass: WindowSizeClass

    val TAG = "AgentsFragment"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAgentsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //widthWindowClass = CalculateWindowSize(requireActivity()).calculateCurrentWidthSize()

        agentsRvAdapter = AgentsRvAdapter()

        viewModel.allAgents()
        observeAllAgentsResponse()
        setupAgentsRv()

        binding.imgListType.apply {
            this.setOnClickListener {
                viewModel.setLayoutManager()

                setListTypeIcon(this)
                setCharacterListLayoutManager()
            }
        }

    }

    private fun setListTypeIcon(imageView: ImageView) {
        val icon = when (viewModel.getListType()) {
            ListType.GridLayout -> R.drawable.ic_linear
            else -> R.drawable.ic_grid
        }
        imageView.setImageResource(icon)

    }

    private fun setCharacterListLayoutManager() {
        if (viewModel.getListType() == ListType.GridLayout) {

            //val spanCount = if (widthWindowClass == WindowSizeClass.EXPANDED) 3 else 2

            binding.agentsRv.layoutManager = GridLayoutManager(requireContext(), 3)

            agentsRvAdapter.setListType(ListType.GridLayout)
        } else {
            binding.agentsRv.layoutManager = LinearLayoutManager(requireContext())
            agentsRvAdapter.setListType(ListType.LinearLayout)
        }
    }

    private fun setupAgentsRv() {
        setCharacterListLayoutManager()
        agentsRvAdapter.stateRestorationPolicy =
            RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        binding.agentsRv.adapter = agentsRvAdapter
    }

    private fun observeAllAgentsResponse() {
        lifecycleScope.launch {
            viewModel.allAgents.collect { response ->
                when(response) {
                    is Resource.Success -> {
                        //binding.shimmerLayout.visibility = View.GONE
                        response.data?.let { agentsResponse ->
                            agentsRvAdapter.submitList(agentsResponse)
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