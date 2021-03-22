package com.rubdev.moviesnow.view.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.rubdev.moviesnow.R
import com.rubdev.moviesnow.di.DaggerAppComponent
import com.rubdev.moviesnow.view.MovieAdapter
import com.rubdev.moviesnow.viewmodel.MovieViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var movieAdapter: MovieAdapter

     lateinit var biding:
    private val viewModel: MovieViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DaggerAppComponent.create().inject(this)

        setUpRecyclerView()

//        observeLiveData()
    }

    private fun setUpRecyclerView(){
//        recycle_view.apply{

        }

}