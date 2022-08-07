package com.example.nba_players.viewModels

import com.example.nba_players.entities.Player
import com.example.nba_players.entities.PositionsEnum

class PlayerViewDto(player: Player) {
    private val positionName = PositionsEnum.values().find {
        it.name == player.position
    }?.positionName ?: player.position!!

    private val heightValue = if (player.height_feet != null && player.height_inches != null) {
        "${player.height_feet} ft ${player.height_inches} in"
    } else {
        "N/A"
    }

    private val weightValue = player.weight_pounds ?: "N/A"

    val firstName = "First name: ${player.first_name ?: "N/A"}"
    val lastName = "Last name: ${player.last_name ?: "N/A"}"
    val position = "Position: $positionName"
    val height = "Height: $heightValue"
    val weight = "Weight: $weightValue"
    val teamName = "Name: ${player.team?.full_name}"
    val teamCity = "City: ${player.team?.city}"
    val teamConference = "Conference: ${player.team?.conference}"
    val teamDivision = "Division: ${player.team?.division}"
}