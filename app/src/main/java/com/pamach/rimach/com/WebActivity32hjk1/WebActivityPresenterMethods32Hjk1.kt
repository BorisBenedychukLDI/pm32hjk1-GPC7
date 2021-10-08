package com.pamach.rimach.com.WebActivity32hjk1

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.net.http.SslError
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import android.webkit.*
import androidx.core.content.FileProvider
import com.pamach.rimach.com.URLStuff32hjk1.filePathCallBack32hjk1
import com.pamach.rimach.com.URLStuff32hjk1.uriView32hjk1
import kotlinx.coroutines.*
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class WebActivityPresenterMethods32Hjk1 (private val webActivityMethods32hjk1: WebActivityContract32hjk1.WebActivityMethods32hjk1) :
    WebActivityContract32hjk1.WebActivityPresenterMethods32hjk1 {

    private var job32hjk1: Job? = null

    override fun setupWebView32hjk1() {
        webActivityMethods32hjk1.giveWebView32hjk1().run {
            settings.run {
                loadWithOverviewMode = true
                displayZoomControls = false
                useWideViewPort = true
                javaScriptEnabled = true
                loadsImagesAutomatically = true
                cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
                builtInZoomControls = true
                displayZoomControls = false
                domStorageEnabled = true
                mediaPlaybackRequiresUserGesture = false
            }

            scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
        }
    }

    override fun internetCheckWork32hjk1() {
        job32hjk1 = CoroutineScope(Dispatchers.IO).launch {
            while (isActive) {
                delay(500)
                isNetworkIsPresent32hjk1().run {
                    if (!this) {
                        webActivityMethods32hjk1.handleNetworkMissing32hjk1()
                        cancel()
                    }
                }
            }
        }
    }

    override fun stopChecking32hjk1() {
        job32hjk1?.cancel()
    }

    override fun isNetworkIsPresent32hjk1(): Boolean {
        val connectivityManager32hjk1 = webActivityMethods32hjk1.contextGiver32hjk1().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val networkCapabilities32hjk1 = connectivityManager32hjk1.getNetworkCapabilities(connectivityManager32hjk1.activeNetwork) ?: return false
            return networkCapabilities32hjk1.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        } else {
            for (network32hjk1 in connectivityManager32hjk1.allNetworks) {
                connectivityManager32hjk1.getNetworkInfo(network32hjk1)?.let {
                    if (it.isConnected) return true
                }
            }
            return false
        }
    }

    override fun setUpWebViewClient32hjk1() {
        webActivityMethods32hjk1.giveWebView32hjk1().webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view32hjk1: WebView?,
                request32hjk1: WebResourceRequest?
            ): Boolean {
                val prohibitedLinks2wlkzg1e = listOf("instagram","facebook","youtube")
                val modifiedLinks2wlkzg1e = listOf("mailto:", "tel:")
                return when {
                    prohibitedLinks2wlkzg1e.filter {
                        request32hjk1?.url.toString().contains(it)
                    }.isNotEmpty() -> true
                    modifiedLinks2wlkzg1e.filter {
                        request32hjk1
                            ?.url.toString().startsWith(it)
                    }.isNotEmpty() -> {
                        view32hjk1?.context?.startActivity(
                            Intent(
                                Intent.ACTION_VIEW,
                                request32hjk1?.url
                            )
                        )
                        true
                    }
                    else -> false
                }
            }

            override fun onPageFinished(view32hjk1: WebView?, url32hjk1: String?) {
                webActivityMethods32hjk1.giveSharedPreffs32hjk1().edit().putString("Last_Page_32hjk1", url32hjk1?: return).apply()
                super.onPageFinished(view32hjk1, url32hjk1)
            }

            override fun onReceivedSslError(
                view32hjk1: WebView?,
                handler32hjk1: SslErrorHandler?,
                error32hjk1: SslError?
            ) {
                handler32hjk1?.proceed()
                super.onReceivedSslError(view32hjk1, handler32hjk1, error32hjk1)
            }
        }
    }

    override fun setUptWebChromeClient32hjk1() {
        webActivityMethods32hjk1.giveWebView32hjk1().webChromeClient = object : WebChromeClient () {
            override fun onShowFileChooser(
                webView: WebView?,
                filePathCallback: ValueCallback<Array<Uri>>?,
                fileChooserParams: FileChooserParams?
            ): Boolean {
                webActivityMethods32hjk1.checkForPermissionPresence32hjk1()
                filePathCallBack32hjk1 = filePathCallback
                val captureIntent32hjk1 = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                if (captureIntent32hjk1.resolveActivity(webActivityMethods32hjk1.contextGiver32hjk1().packageManager) != null) {
                    val providedFile2wlkzg1e = createTempFile32hjk1()
                    uriView32hjk1 = FileProvider.getUriForFile(
                        webActivityMethods32hjk1.contextGiver32hjk1(),
                        "${webActivityMethods32hjk1.contextGiver32hjk1().packageName}.provider",
                        providedFile2wlkzg1e
                    )
                    captureIntent32hjk1.run {
                        putExtra(MediaStore.EXTRA_OUTPUT, uriView32hjk1)
                        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                    }
                    val actionIntent2wlkzg1e = Intent(Intent.ACTION_GET_CONTENT).apply {
                        addCategory(Intent.CATEGORY_OPENABLE)
                        type = "image/*"
                    }
                    val intentChooser2wlkzg1e = Intent(Intent.ACTION_CHOOSER).apply {
                        putExtra(Intent.EXTRA_INTENT, captureIntent32hjk1)
                        putExtra(Intent.EXTRA_INITIAL_INTENTS, arrayOf(actionIntent2wlkzg1e))
                    }
                    webActivityMethods32hjk1.startActivityForResult32hjk1(intentChooser2wlkzg1e)
                    return true

                }
                return super.onShowFileChooser(
                    webView,
                    filePathCallback, fileChooserParams)

            }
        }
    }

    override fun loadTheOne32hjk1() {
        webActivityMethods32hjk1.giveSharedPreffs32hjk1().getString("Last_Page_32hjk1",null)?.let {
            lastpage32hjk1 -> webActivityMethods32hjk1.giveWebView32hjk1().loadUrl(lastpage32hjk1)
            return
        }
        webActivityMethods32hjk1.giveWebView32hjk1().loadUrl(webActivityMethods32hjk1.getIntentURL32hjk1() ?: return)
    }

    private fun createTempFile32hjk1 (): File {
        val date32hjk1 = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault())
            .format(Date())
        val fileDir32hjk1 = webActivityMethods32hjk1.contextGiver32hjk1().getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile("TMP${date32hjk1}_32hjk1", ".jpg", fileDir32hjk1)
    }
}