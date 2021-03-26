package com.rubdev.moviesnow.view.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
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

    private fun observeInProgress(){
        viewModel.repository.isInProgress.observe(this, Observer { isLoading ->
            isLoading.let {
                if (it){
                    binding.emptyText.visibility = View.GONE
                    binding.recycleView.visibility = View.GONE
                    binding.fetchProgress.visibility = View.VISIBLE
                }else{
                    binding.fetchProgress.visibility = View.GONE
                }
            }
        })
    }

    private fun observeIsError(){
        viewModel.repository.isError.observe(this, Observer { isError ->
            isError.let {
                if (it){
                    disableViewsOnError()
                }else{
                    binding.emptyText.visibility = View.GONE
                    binding.fetchProgress.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun observeMovieList(){
        viewModel.repository.movies.observe(this, Observer { movies ->
            movies.let {
                if (it != null && it.isNotEmpty()){
                    binding.fetchProgress.visibility = View.VISIBLE
                    binding.recycleView.visibility = View.VISIBLE
                    movieAdapter.setUpData(it)
                    binding.emptyText.visibility = View.GONE
                    binding.fetchProgress.visibility = View.GONE
                }
            }
        })
    }

    private fun disableViewsOnError(){
        binding.fetchProgress.visibility = View.VISIBLE
        binding.emptyText.visibility = View.VISIBLE
        binding.recycleView.visibility = View.GONE
        movieAdapter.setUpData(emptyList())
        binding.fetchProgress.visibility = View.GONE
    }


}