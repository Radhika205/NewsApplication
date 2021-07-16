package com.example.newsapplication.ui

import android.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ActionMode
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapplication.NewsViewModel
import com.example.newsapplication.R
import com.example.newsapplication.adapter.NewsAdapter
import com.example.newsapplication.db.ArticleDatabase
import androidx.lifecycle.Observer
import com.example.newsapplication.repository.NewsRepository
import com.example.newsapplication.util.Resource
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var newsAdapter: NewsAdapter
    val TAG = "Breaking News"
      lateinit var viewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.displayOptions = androidx.appcompat.app.ActionBar.DISPLAY_SHOW_CUSTOM
          supportActionBar?.setCustomView(R.layout.abs_layout)

        val newsRepository = NewsRepository(ArticleDatabase(this))
        val  viewModelProviderFactory = NewsViewModelProviderFactory(newsRepository)
        viewModel =ViewModelProvider(this, viewModelProviderFactory).get(NewsViewModel::class.java)

        setupRecyclerView()
        viewModel.breakingNews.observe(this, Observer { response->
            when(response)
            {
                is Resource.Success->{
                    hideProgressBar()
                    response.data?.let{ newsResponse ->
                        newsAdapter.differ.submitList(newsResponse.articles)
                    }
                }
                is Resource.Error->{
                    hideProgressBar()
                    response.message?.let{ message ->
                        Log.e(TAG, "An error occured: $message")
                    }
                }
                is Resource.Loading ->{
                    showProgressBar()
                }
            }

        })
    }

    private  fun hideProgressBar(){
        paginationProgressBar.visibility = View.INVISIBLE
    }
    private  fun showProgressBar(){
        paginationProgressBar.visibility = View.VISIBLE
    }
    private fun setupRecyclerView(){
        newsAdapter = NewsAdapter(this)
        rvBreakingNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(applicationContext)
        }

    }
}