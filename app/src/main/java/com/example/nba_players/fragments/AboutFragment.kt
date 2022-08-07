package com.example.nba_players.fragments

import android.icu.util.TimeZone
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.nba_players.BuildConfig
import com.example.nba_players.databinding.FragmentAboutBinding
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class AboutFragment : Fragment() {
    private lateinit var binding: FragmentAboutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAboutBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dateInMilliseconds = BuildConfig.BUILD_TIME
        val date = Instant
            .ofEpochMilli(dateInMilliseconds)
            .atZone(ZoneId.of(TimeZone.getDefault().id))
            .toLocalDate()
            .format(DateTimeFormatter.ofPattern("yyyyMMdd"))
        val appVersion = BuildConfig.VERSION_NAME
        val value = "App version:\n${appVersion}.${date}"
        binding.tvAppVersion.text = value
    }

}