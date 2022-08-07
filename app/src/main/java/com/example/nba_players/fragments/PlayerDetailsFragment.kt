package com.example.nba_players.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.nba_players.databinding.FragmentPlayerDetailsBinding
import com.example.nba_players.viewModels.AppViewModel
import com.example.nba_players.viewModels.PlayerViewDto

class PlayerDetailsFragment : Fragment() {

    private val viewModel: AppViewModel by activityViewModels()
    private lateinit var binding: FragmentPlayerDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlayerDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.currentPlayerLiveData.observe(viewLifecycleOwner) {
            binding.player = PlayerViewDto(it)
        }
    }
}