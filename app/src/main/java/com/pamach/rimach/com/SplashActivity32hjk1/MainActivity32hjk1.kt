package com.pamach.rimach.com.SplashActivity32hjk1

import android.animation.ValueAnimator
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.pamach.rimach.com.R
import com.pamach.rimach.com.WebActivity32hjk1.WebActivity32hjk1
import com.pamach.rimach.com.databinding.ActivityMain32hjk1Binding
import kotlinx.coroutines.launch

class MainActivity32hjk1 : AppCompatActivity(), SplashActivityContract32hjk1.SplashActivityMethods32hjk {

    private lateinit var binding32hjk1: ActivityMain32hjk1Binding
    private lateinit var splashActivityPresenterMethods32hjk1: SplashActivityContract32hjk1.SplashActivityPresenterMethods32hjk1
    private var animator32hjk1: ValueAnimator? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSharedPreferences("SP_32hjk1", MODE_PRIVATE).getString("Last_Page_32hjk1", null)?.let {
            startActivity(Intent(this, WebActivity32hjk1::class.java))
            finish()
        }
        binding32hjk1 = ActivityMain32hjk1Binding.inflate(layoutInflater)
        setContentView(binding32hjk1.root)
        splashActivityPresenterMethods32hjk1 = SplashPresenter32hjk1(this)
        splashActivityPresenterMethods32hjk1.setupFB32hjk1()
        splashActivityPresenterMethods32hjk1.thisEndsHere32hjk1()
    }

    override fun onClickListener32hjk1(clickScope32hjk1: () -> Unit) {
        binding32hjk1.b32hjk1.setOnClickListener {
            lifecycleScope.launch {
                animation32hjk1()
                binding32hjk1.b32hjk1.isClickable = false
                clickScope32hjk1()
            }
        }
    }

    override fun startNewIntentWithURL32hjk1(url32hjk1: String) {
        startActivity(Intent(this, WebActivity32hjk1::class.java).apply { putExtra("webURL32hjk1", url32hjk1) })
        finish()
    }

    override fun getPackageName32hjk1(): String = packageName

    override fun onStop() {
        animator32hjk1?.cancel()
        super.onStop()
    }

    private fun animation32hjk1 () {
        animator32hjk1 = ValueAnimator.ofInt(0,3).apply {
            repeatMode = ValueAnimator.RESTART
            repeatCount = ValueAnimator.INFINITE
            addUpdateListener {
                var s = "Loading"
                for (i in 0..it.animatedValue as Int) {
                    s += "."
                }
                binding32hjk1.b32hjk1.text = s
            }
            duration = 500
            start()
        }

        binding32hjk1.pb32hjk1.animate().alpha(1f)
    }
}