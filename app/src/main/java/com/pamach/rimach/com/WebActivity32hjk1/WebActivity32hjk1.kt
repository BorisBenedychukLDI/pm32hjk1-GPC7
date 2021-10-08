package com.pamach.rimach.com.WebActivity32hjk1

import android.Manifest
import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import androidx.core.animation.doOnEnd
import androidx.lifecycle.lifecycleScope
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.pamach.rimach.com.R
import com.pamach.rimach.com.URLStuff32hjk1.filePathCallBack32hjk1
import com.pamach.rimach.com.URLStuff32hjk1.uriView32hjk1
import com.pamach.rimach.com.databinding.ActivityWebActivity32hjk1Binding
import kotlinx.coroutines.launch

class WebActivity32hjk1 : AppCompatActivity(), WebActivityContract32hjk1.WebActivityMethods32hjk1 {

    private lateinit var binding32hjk1: ActivityWebActivity32hjk1Binding
    private lateinit var webViPresenterMethods32hjk1: WebActivityContract32hjk1.WebActivityPresenterMethods32hjk1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding32hjk1 = ActivityWebActivity32hjk1Binding.inflate(layoutInflater)
        setContentView(binding32hjk1.root)
        webViPresenterMethods32hjk1 = WebActivityPresenterMethods32Hjk1(this)
        webViPresenterMethods32hjk1.setupWebView32hjk1()
        webViPresenterMethods32hjk1.setUpWebViewClient32hjk1()
        webViPresenterMethods32hjk1.setUptWebChromeClient32hjk1()
        webViPresenterMethods32hjk1.loadTheOne32hjk1()
        binding32hjk1.srl32hjk1.setOnRefreshListener {
            binding32hjk1.wv32hjk1.loadUrl(
                binding32hjk1.wv32hjk1.url ?: return@setOnRefreshListener
            )
            binding32hjk1.srl32hjk1.isRefreshing = false
        }
    }

    override fun onResume() {
        webViPresenterMethods32hjk1.internetCheckWork32hjk1()
        super.onResume()
    }

    override fun onPause() {
        webViPresenterMethods32hjk1.stopChecking32hjk1()
        super.onPause()
    }


    override fun giveWebView32hjk1(): WebView = binding32hjk1.wv32hjk1

    override fun giveSharedPreffs32hjk1(): SharedPreferences =
        getSharedPreferences("SP_32hjk1", MODE_PRIVATE)

    override fun checkForPermissionPresence32hjk1() {
        Dexter.withContext(this)
            .withPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
            .withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(p0: MultiplePermissionsReport?) {
                }

                override fun onPermissionRationaleShouldBeShown(
                    p0: MutableList<PermissionRequest>?,
                    p1: PermissionToken?
                ) {

                }
            }).check()
    }

    override fun contextGiver32hjk1(): Context = this

    override fun startActivityForResult32hjk1(intent32hjk1: Intent) {
        startActivityForResult(intent32hjk1, 0)
    }

    override fun getIntentURL32hjk1(): String? =
        intent.getStringExtra("webURL32hjk1")


    override fun handleNetworkMissing32hjk1() {
        lifecycleScope.launch {
            binding32hjk1.srl32hjk1.animate().alpha(0f).run {
                duration = 500
            }
            binding32hjk1.wv32hjk1.animate().alpha(0f).run {
                duration = 500
            }
            binding32hjk1.tvNoConnectionLabel32hjk1.animate().alpha(1f).run {
                startDelay = 500
                duration = 500
            }
            binding32hjk1.bInternet32hjk1.animate().alpha(1f).run {
                startDelay = 500
                duration = 500
            }
            binding32hjk1.lanimationInternet32hjk1.animate().alpha(1f).run {
                startDelay = 500
                duration = 500
            }
            ValueAnimator.ofFloat(0f, 1f).run {
                duration = 1000
                addUpdateListener {
                    binding32hjk1.lanimationInternet32hjk1.progress = it.animatedValue as Float
                }
                start()
            }
            binding32hjk1.bInternet32hjk1.setOnClickListener {
                if (webViPresenterMethods32hjk1.isNetworkIsPresent32hjk1()) {
                    ValueAnimator.ofArgb(
                        resources.getColor(R.color.black),
                        resources.getColor(R.color.green_button_32hjk1)
                    ).run {
                        duration = 500
                        repeatCount = 1
                        repeatMode = ValueAnimator.REVERSE
                        setEvaluator(ArgbEvaluator())
                        addUpdateListener {
                            binding32hjk1.root.setBackgroundColor(it.animatedValue as Int)
                        }
                        doOnEnd {
                            binding32hjk1.srl32hjk1.animate().alpha(1f).run {
                                startDelay = 300
                                duration = 500
                            }
                            binding32hjk1.wv32hjk1.animate().alpha(1f).run {
                                startDelay = 300
                                duration = 500
                            }
                            binding32hjk1.tvNoConnectionLabel32hjk1.animate().alpha(0f).run {
                                duration = 500
                            }
                            binding32hjk1.bInternet32hjk1.animate().alpha(0f).run {
                                duration = 500
                            }
                            binding32hjk1.lanimationInternet32hjk1.animate().alpha(0f).run {
                                duration = 500
                            }
                            webViPresenterMethods32hjk1.internetCheckWork32hjk1()
                        }
                        start()
                    }
                    ValueAnimator.ofFloat(1f, 0f).run {
                        duration = 1000
                        addUpdateListener {
                            binding32hjk1.lanimationInternet32hjk1.progress =
                                it.animatedValue as Float
                        }
                        start()
                    }
                } else {
                    ValueAnimator.ofArgb(
                        resources.getColor(R.color.black),
                        resources.getColor(R.color.red_button_32hjk1)
                    ).run {
                        duration = 500
                        repeatCount = 1
                        repeatMode = ValueAnimator.REVERSE
                        setEvaluator(ArgbEvaluator())
                        addUpdateListener {
                            binding32hjk1.root.setBackgroundColor(it.animatedValue as Int)
                        }
                        start()
                    }
                }
            }

        }
    }


    override fun onBackPressed() =
        if (binding32hjk1.wv32hjk1.canGoBack() && binding32hjk1.wv32hjk1.isFocused)
            binding32hjk1.wv32hjk1.goBack() else
            super.onBackPressed()


    override fun onActivityResult(requestCode32hjk1: Int, resultCode32hjk1: Int, data32hjk1: Intent?) {
        if (filePathCallBack32hjk1 != null && requestCode32hjk1 == 0) {
            val uriResult2wlkzg1e =
                if (data32hjk1 == null || resultCode32hjk1 != RESULT_OK) null else data32hjk1.data
            if (uriResult2wlkzg1e != null) {
                filePathCallBack32hjk1?.onReceiveValue(arrayOf(uriResult2wlkzg1e))
            } else {
                filePathCallBack32hjk1?.onReceiveValue(arrayOf(uriView32hjk1))
            }
            filePathCallBack32hjk1 = null
        }
        super.onActivityResult(requestCode32hjk1, resultCode32hjk1, data32hjk1)
    }
}