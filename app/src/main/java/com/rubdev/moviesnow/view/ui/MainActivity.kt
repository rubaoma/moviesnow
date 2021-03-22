package com.rubdev.moviesnow.view.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import com.rubdev.moviesnow.R
import com.rubdev.moviesnow.databinding.ActivityMainBinding
import com.rubdev.moviesnow.di.DaggerAppComponent
import com.rubdev.moviesnow.view.MovieAdapter
import com.rubdev.moviesnow.viewmodel.MovieViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var movieAdapter: MovieAdapter


    private val viewModel: MovieViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        DaggerAppComponent.create().inject(this)

        setUpRecyclerView()

        observeLiveData()
    }

    private fun setUpRecyclerView() {
        binding.recycleView.apply {
            setHasFixedSize(true)
            itemAnimator = DefaultItemAnimator()
            adapter = movieAdapter
        }
    }

    private fun observeLiveData() {
        observeInProgress()
        observeIsError()
        observeMovieList()
    }

    private fun observeInProgress(){}

    private fun observeIsError(){}

    private fun observeMovieList(){}

}