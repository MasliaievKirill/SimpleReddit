package com.masliaiev.simplereddit.presentation.activities

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.masliaiev.simplereddit.SimpleRedditApp
import com.masliaiev.simplereddit.databinding.ActivityMainBinding
import com.masliaiev.simplereddit.presentation.adapters.PostAdapter
import com.masliaiev.simplereddit.presentation.view_models.MainViewModel
import com.masliaiev.simplereddit.presentation.view_models.ViewModelFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private val component by lazy {
        (application as SimpleRedditApp).component
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: MainViewModel

    private lateinit var binding: ActivityMainBinding

    private val adapter by lazy { PostAdapter() }


    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

        binding.rvPosts.adapter = adapter
        binding.rvPosts.layoutManager = LinearLayoutManager(this)

        adapter.onPostClickListener = object : PostAdapter.OnPostClickListener {
            override fun onPosterClick(url: String) {
                launchChromeTab(url)
            }
        }

        binding.retryButton.setOnClickListener {
            viewModel.retryLoading()
        }

        viewModel.posts.observe(this) {
            adapter.submitData(lifecycle, it)
        }
        viewModel.error.observe(this) {
            if (it) {
                with(binding) {
                    ivWarning.visibility = View.VISIBLE
                    tvWarning.visibility = View.VISIBLE
                    retryButton.visibility = View.VISIBLE
                    rvPosts.visibility = View.INVISIBLE
                }
            } else {
                with(binding) {
                    ivWarning.visibility = View.INVISIBLE
                    tvWarning.visibility = View.INVISIBLE
                    retryButton.visibility = View.INVISIBLE
                    rvPosts.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun launchChromeTab(url: String) {
        val tabIntent = CustomTabsIntent.Builder().setStartAnimations(
            this,
            com.google.android.material.R.anim.abc_fade_in,
            com.google.android.material.R.anim.abc_fade_out
        ).setExitAnimations(
            this,
            com.google.android.material.R.anim.abc_fade_in,
            com.google.android.material.R.anim.abc_fade_out
        ).build()
        tabIntent.launchUrl(this, Uri.parse(url))
    }

}

