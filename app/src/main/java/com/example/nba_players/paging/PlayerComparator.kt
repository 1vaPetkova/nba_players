package com.example.nba_players.paging

import androidx.recyclerview.widget.DiffUtil
import com.example.nba_players.entities.Player

object PlayerComparator : DiffUtil.ItemCallback<Player>() {
    override fun areItemsTheSame(oldItem: Player, newItem: Player) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Player, newItem: Player) = oldItem == newItem
}