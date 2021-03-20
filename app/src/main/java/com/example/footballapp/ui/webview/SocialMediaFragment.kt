package com.example.footballapp.ui.webview

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.NavigationUI
import com.example.footballapp.R
import com.example.footballapp.ui.league.LeagueFragmentArgs
import kotlinx.android.synthetic.main.fragment_social_media.*
import kotlinx.android.synthetic.main.fragment_team_detail.*

class SocialMediaFragment : Fragment() {

    private val args: SocialMediaFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_social_media, container, false)
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val url = args.url
        setupNavigation()
        socialMediaFragmentToolbar.title = url
        socialMediaFragmentToolbar.setNavigationOnClickListener {
            it.findNavController().navigateUp()
        }

        webView.apply {
            webViewClient = WebViewClient()

            this.settings.javaScriptEnabled = true
            this.settings.loadWithOverviewMode = true
            this.settings.useWideViewPort = true
            loadUrl("https://$url")
        }
    }



    private fun setupNavigation() {
        val navController = findNavController()
        NavigationUI.setupWithNavController(socialMediaFragmentToolbar, navController)
    }
}