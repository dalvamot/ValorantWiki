package com.vt.valorantwiki.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.vt.valorantwiki.R
import com.vt.valorantwiki.databinding.FragmentMainBinding


class MainFragment : Fragment() {

    private val binding by lazy {
        FragmentMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding.agentButton.setOnClickListener {
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.my_fragment_container, AgentsFragment.newInstance())
                .addToBackStack(null)
                .commit()
        }
        binding.cardsButton.setOnClickListener {
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.my_fragment_container, PlayerCardsFragment.newInstance())
                .addToBackStack(null)
                .commit()
        }
        return binding.root

    }

    companion object {
        fun newInstance() = MainFragment()
    }
}