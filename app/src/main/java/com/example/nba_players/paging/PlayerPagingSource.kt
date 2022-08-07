package com.example.nba_players.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.nba_players.entities.Player
import com.example.nba_players.retrofit.PlayerApi
import retrofit2.awaitResponse

class PlayerPagingSource(private val playerApi: PlayerApi, private val query: String) :
    PagingSource<Int, Player>() {
    companion object {
        const val DEFAULT_PAGE_INDEX = 1
        val TAG: String = PlayerPagingSource::class.java.name
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Player> {
        val nextPageNumber = params.key ?: DEFAULT_PAGE_INDEX
        return try {
            val response = playerApi.getAllPlayers(nextPageNumber, 25, query)
                .awaitResponse().body()!!
            Log.d(TAG, "Loaded ${response.meta}")
            LoadResult.Page(
                response.data,
                prevKey = null,
                nextKey = if (response.data.isEmpty()) null else nextPageNumber + 1
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Player>): Int? {
        return null
    }
}