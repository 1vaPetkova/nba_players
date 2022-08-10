package com.example.nba_players.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nba_players.adapters.PlayersAdapter
import com.example.nba_players.databinding.FragmentPlayersBinding
import com.example.nba_players.paging.PlayerComparator
import com.example.nba_players.viewModels.AppViewModel

class PlayersFragment : Fragment() {
    private val viewModel: AppViewModel by activityViewModels()
    private lateinit var binding: FragmentPlayersBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlayersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        setUpSearchView()
    }

    private fun setUpSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.setQuery(query!!)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText == "") {
                    viewModel.setQuery("")
                    return true
                }
                return false
            }
        })
    }

    private fun setUpRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(context)
        val playersAdapter = PlayersAdapter(PlayerComparator, viewModel)
        binding.rvPlayers.apply {
            layoutManager = linearLayoutManager
            adapter = playersAdapter
        }
        viewModel.playersLiveData.observe(viewLifecycleOwner) {
            playersAdapter.submitData(lifecycle, it)
        }
        viewModel.queryLiveData.observe(viewLifecycleOwner) {
            playersAdapter.refresh()
        }

        binding.rvPlayers.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                val showSearchView =
                    newState == RecyclerView.SCROLL_STATE_IDLE &&
                            linearLayoutManager.findFirstVisibleItemPosition() == 0
                binding.searchView.visibility = if (showSearchView) View.VISIBLE else View.GONE
            }
        })
    }
}