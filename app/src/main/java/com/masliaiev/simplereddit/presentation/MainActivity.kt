package com.masliaiev.simplereddit.presentation

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.masliaiev.simplereddit.R
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

    private val adapter by lazy {
        PostAdapter()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        binding.rvPosts.adapter = adapter
        binding.rvPosts.layoutManager = LinearLayoutManager(this)

        viewModel.posts.observe(this){
            adapter.submitData(lifecycle, it)
        }

    }

}

