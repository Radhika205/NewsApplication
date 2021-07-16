package com.example.newsapplication.ui

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.newsapplication.R
import kotlinx.android.synthetic.main.activity_second_page.*
import kotlinx.android.synthetic.main.item_article_preview.view.*


class SecondPage : AppCompatActivity() {

override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_page)
        val actionBar: ActionBar? = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true);
   // actionBar.setO

        val sources = intent.getStringExtra("source")
    val titles = intent.getStringExtra("title")
    val descriptions = intent.getStringExtra("description")
    val images = intent.getStringExtra("urlToimage")
     tvtitle.text = titles.toString()
    tvsource.text = sources.toString()
    tvdescription.text = descriptions.toString()
    Glide.with(this).load(images).into(tvimage)

    }
}