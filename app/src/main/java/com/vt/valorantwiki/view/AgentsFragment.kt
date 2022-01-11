package com.vt.valorantwiki.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.vt.valorantwiki.R
import com.vt.valorantwiki.adapters.AgentsAdapter
import com.vt.valorantwiki.adapters.AgentsViewHolder
import com.vt.valorantwiki.databinding.FragmentAgentsBinding
import com.vt.valorantwiki.utils.AgentButtonClick
import com.vt.valorantwiki.utils.UIState
import com.vt.valorantwiki.viewmodel.AgentsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class AgentsFragment : Fragment(), AgentButtonClick {

    private val binding by lazy {
        FragmentAgentsBinding.inflate(layoutInflater)
    }

    private val agentsViewModel: AgentsViewModel by viewModel()

    private lateinit var agentsAdapter: AgentsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        agentsAdapter = AgentsAdapter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding.agentsRecycler.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = agentsAdapter
        }

        binding.refreshItems.setOnRefreshListener {
            agentsViewModel.subscribeToAgentsInfo()
        }

        agentsViewModel.agentsLiveData.observe(viewLifecycleOwner, ::handleAgents)

        agentsViewModel.subscribeToAgentsInfo()
        return binding.root
    }

    private fun handleAgents(uiState: UIState){
        when(uiState){
            is UIState.LOADING -> {
                binding.agentsRecycler.visibility = View.GONE
                binding.refreshItems.isRefreshing = true
            }
            is UIState.SUCCESS -> {
                binding.refreshItems.isRefreshing = false
                binding.agentsRecycler.visibility = View.VISIBLE
                agentsAdapter.setAgents(uiState.success.data)
            }
            is UIState.ERROR -> {
                binding.agentsRecycler.visibility = View.GONE
                binding.refreshItems.visibility = View.GONE
                binding.refreshItems.isRefreshing = false

                Toast.makeText(requireContext(), "Please try again!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        fun newInstance() = AgentsFragment()
    }

    override fun moveToAgentsFragment(agentID: String) {
        TODO("Not yet implemented")
    }
}