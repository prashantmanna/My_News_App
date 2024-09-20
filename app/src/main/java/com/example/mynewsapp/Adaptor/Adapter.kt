package com.example.mynewsapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mynewsapp.R
import com.example.mynewsapp.models.Article
import com.example.mynewsapp.readNewsActivity

class Adapter(private val context: Context, private val data: ArrayList<Article>) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    private var lastPosition = -1

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.mainHeadlines_id)
        val description: TextView = itemView.findViewById(R.id.newsDescription_id)
        val author: TextView = itemView.findViewById(R.id.Auther)
        val published: TextView = itemView.findViewById(R.id.published)
        val image: ImageView = itemView.findViewById(R.id.newsImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = data[position]
        holder.title.text = article.title
        holder.description.text = article.description
        holder.author.text = article.author
        holder.published.text = article.publishedAt
        Glide.with(context).load(article.urlToImage).into(holder.image)
        setAnimation(holder.itemView,position)
        holder.itemView.setOnClickListener {
            var intent = Intent(context,readNewsActivity::class.java)
            intent.putExtra("url",data.get(position).url)
            context.startActivity(intent)

        }
    }
    private fun setAnimation(viewToAnimate: View, position: Int) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            val animation: Animation =
                AnimationUtils.loadAnimation(context, R.anim.slide_in)
            viewToAnimate.startAnimation(animation)
            lastPosition = position
        }
    }

    override fun getItemCount(): Int = data.size
}
