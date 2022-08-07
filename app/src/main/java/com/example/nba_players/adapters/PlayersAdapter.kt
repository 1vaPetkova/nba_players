package com.example.nba_players.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.nba_players.databinding.PlayerItemBinding
import com.example.nba_players.entities.Player
import com.example.nba_players.fragments.PlayersFragmentDirections
import com.example.nba_players.viewModels.AppViewModel

class PlayersAdapter(
    diffCallback: DiffUtil.ItemCallback<Player>,
    private val viewModel: AppViewModel
) :
    PagingDataAdapter<Player, PlayersAdapter.PlayerViewHolder>(diffCallback) {
    class PlayerViewHolder(var binding: PlayerItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        return PlayerViewHolder(
            PlayerItemBinding.inflate(
                LayoutInflater
                    .from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val player = getItem(position)
        if (player == null) {
            holder.binding.tvPlayer.text = ""
        } else {
            val playerName = "${player.first_name} ${player.last_name}"
            holder.binding.tvPlayer.text = playerName
            holder.itemView.setOnClickListener {
                val action = PlayersFragmentDirections
                    .actionHomeFragmentToPlayerDetailsFragment()
                viewModel.setCurrentPlayer(player)
                it.findNavController().navigate(action)
            }
        }
    }
}