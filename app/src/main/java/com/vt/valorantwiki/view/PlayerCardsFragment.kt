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
import com.vt.valorantwiki.adapters.PlayerCardsAdapter
import com.vt.valorantwiki.databinding.FragmentPlayerCardsBinding
import com.vt.valorantwiki.utils.AgentButtonClick
import com.vt.valorantwiki.utils.PlayerCardsButtonClick
import com.vt.valorantwiki.utils.UIState
import com.vt.valorantwiki.viewmodel.AgentsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlayerCardsFragment : Fragment(), PlayerCardsButtonClick {

    private val binding by lazy {
        FragmentPlayerCardsBinding.inflate(layoutInflater)
    }

    private val playerCardsViewModel: AgentsViewModel by viewModel()

    private lateinit var playerCardsAdapter: PlayerCardsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        playerCardsAdapter = PlayerCardsAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding.cardsRecycler.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = playerCardsAdapter
        }

        binding.refreshCards.setOnRefreshListener {
            playerCardsViewModel.subscribeToPlayerCardsInfo()
        }

        playerCardsViewModel.cardsLiveData.observe(viewLifecycleOwner, ::handleCards)
        playerCardsViewModel.subscribeToPlayerCardsInfo()
        return binding.root
    }

    private fun handleCards(uiState: UIState){
        when(uiState){
            is UIState.LOADING -> {
                binding.cardsRecycler.visibility = View.GONE
                binding.refreshCards.isRefreshing = true
            }
            is UIState.SUCCESSCARDS -> {
                binding.refreshCards.isRefreshing = false
                binding.cardsRecycler.visibility = View.VISIBLE
                playerCardsAdapter.setCards(uiState.playercards.data)

            }
            is UIState.ERROR -> {
                binding.cardsRecycler.visibility = View.GONE
                binding.refreshCards.visibility = View.GONE
                binding.refreshCards.isRefreshing = false

                Toast.makeText(requireContext(), "Please try again!", Toast.LENGTH_SHORT).show()

            }
            else -> {
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = PlayerCardsFragment()
    }

    override fun moveToPlayerCardsFragment(playerCardID: String) {
        TODO("Not yet implemented")
    }
}