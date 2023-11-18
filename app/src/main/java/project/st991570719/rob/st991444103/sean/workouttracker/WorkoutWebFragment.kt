package project.st991570719.rob.st991444103.sean.workouttracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import project.st991570719.rob.st991444103.sean.R
import project.st991570719.rob.st991444103.sean.databinding.FragmentWebviewBinding


class WorkoutWebFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding: FragmentWebviewBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_webview, container, false
        )

        val webView = binding.web

        webView.loadUrl("https://www.healthline.com/health/fitness-exercise/10-best-exercises-everyday");

        webView.settings.javaScriptEnabled = true

        webView.webViewClient = WebViewClient()

        return binding.root
    }

}