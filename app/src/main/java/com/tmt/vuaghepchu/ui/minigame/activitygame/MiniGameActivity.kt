package com.tmt.vuaghepchu.ui.minigame.activitygame

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Bitmap
import android.os.Bundle
import android.text.Html
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.tmt.vuaghepchu.databinding.ActivityMinigameBinding
import com.tmt.vuaghepchu.databinding.DialogCloseAlertBinding
import com.tmt.vuaghepchu.databinding.DialogInternetErrorBinding
import com.tmt.vuaghepchu.ui.base.viewBinding
import com.tmt.vuaghepchu.ui.minigame.network.Constant


@Suppress("deprecation")
class MiniGameActivity : AppCompatActivity() {
    private val binding by viewBinding(ActivityMinigameBinding::inflate)
    private val dialogBinding by viewBinding(DialogInternetErrorBinding::inflate)
    private val dialogCloseBinding by viewBinding(DialogCloseAlertBinding::inflate)

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(binding.root)

        binding.WebView.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                binding.ProgressLoadingGame.visibility = View.VISIBLE
            }

            override fun onPageFinished(view: WebView, url: String) {
                super.onPageFinished(view, url)
                binding.ProgressLoadingGame.visibility = View.GONE
                binding.WebView.visibility = View.VISIBLE
            }

            @Suppress("deprecation")
            override fun onReceivedError(
                mainWebView: WebView, errorCode: Int, description: String, failingUrl: String
            ) {
                binding.ProgressLoadingGame.visibility = View.GONE
                try {
                    mainWebView.stopLoading()
                    mainWebView.clearView()
                } catch (ignored: Exception) {
                }
                if (mainWebView.canGoBack()) {
                    mainWebView.goBack()
                }
//                mainWebView.loadUrl("file:///android_asset/interneterror.html")
                val alertDialog: AlertDialog = AlertDialog.Builder(this@MiniGameActivity).create()
                alertDialog.setButton(
                    Dialog.BUTTON_POSITIVE, "Try Again"
                ) { _, _ ->
                    finish()
                    startActivity(intent)
                }
                alertDialog.setView(dialogBinding.root)
                alertDialog.show()
                super.onReceivedError(mainWebView, errorCode, description, failingUrl)
            }

            @Suppress("deprecation")
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return true
            }
        }
        binding.WebView.loadUrl(intent.extras?.getString(Constant.linkGame, "") ?: return)
        val webSettings = binding.WebView.settings
        webSettings.javaScriptEnabled = true
        webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH)
        webSettings.setEnableSmoothTransition(true)
    }

    override fun onBackPressed() {
        if (binding.WebView.canGoBack()) {
            binding.WebView.goBack()
        } else {
            AlertDialog.Builder(this)
                .setPositiveButton(
                    Html.fromHtml("<font color='#D800FF'>EXIT</font>")
                ) { _, _ -> finish() }
                .setCancelable(false)
                .setNegativeButton(
                    Html.fromHtml("<font color='#FF7F27'>NO</font>")
                ) { dialog, _ -> dialog.dismiss() }
                .setView(dialogCloseBinding.root)
                .show()
        }
    }
}