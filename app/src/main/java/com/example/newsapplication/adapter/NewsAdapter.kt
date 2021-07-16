package com.example.newsapplication.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapplication.R
import com.example.newsapplication.model.Article
import com.example.newsapplication.ui.SecondPage
import kotlinx.android.synthetic.main.item_article_preview.view.*

class NewsAdapter(context: Context) : RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {
    private var adapterContext:Context = context

    inner class ArticleViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)

    private val diffCallback= object: DiffUtil.ItemCallback<Article>(){
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
                }

    }
    val differ = AsyncListDiffer(this, diffCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_article_preview,
            parent,
            false
            )
        )
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
            val article = differ.currentList[position]
        holder.itemView.apply {

            Glide.with(this).load(article.urlToImage).into(ivArticleImage)
        tvSource.text = article.source.name
        tvTitle.text = article.title
        tvDescription.text = article.description
        tvPublishedAt.text = article.publishedAt

        setOnClickListener{
            onItemClikListener?.let {
                //it(article)

            }
            var intent = Intent(adapterContext, SecondPage::class.java)
            intent.putExtra("title",article.title )
            intent.putExtra("description",article.description )
            intent.putExtra("source",article.source.name )
             intent.putExtra("urlToimage",article.urlToImage )
            adapterContext.startActivity(intent)
            print("clicked")
            }
        }
    }



    private var onItemClikListener: ((Article)-> Unit)?=null
        fun setOnItemClickListner(listner:(Article)-> Unit){
            onItemClikListener = listner
        }
    override fun getItemCount(): Int {
            return differ.currentList.size
                }

}