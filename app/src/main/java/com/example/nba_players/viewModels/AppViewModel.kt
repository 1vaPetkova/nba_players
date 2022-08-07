package com.example.nba_players.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.example.nba_players.entities.Player
import com.example.nba_players.paging.PlayerPagingSource
import com.example.nba_players.retrofit.RetrofitInstance

class AppViewModel : ViewModel() {
    private var query: MutableLiveData<String> = MutableLiveData("")
    val queryLiveData: LiveData<String> get() = query
    fun setQuery(newQuery: String) {
        query.value = newQuery
    }

    private var currentPlayer: MutableLiveData<Player> = MutableLiveData(null)
    val currentPlayerLiveData: LiveData<Player> get() = currentPlayer
    fun setCurrentPlayer(player: Player) {
        currentPlayer.value = player
    }

    val playersLiveData = Pager(
        PagingConfig(pageSize = 25)
    ) {
        PlayerPagingSource(RetrofitInstance.api, query.value!!)
    }.liveData
        .cachedIn(viewModelScope)
}