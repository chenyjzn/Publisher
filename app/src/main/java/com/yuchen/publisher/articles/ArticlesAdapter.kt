package com.yuchen.publisher.articles

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yuchen.publisher.TimeUtil
import com.yuchen.publisher.data.Article
import com.yuchen.publisher.databinding.ArticleLayoutBinding

class ArticlesAdapter (): ListAdapter<Article, ArticlesAdapter.ArticleLayoutViewHolder>(
    DiffCallback
) {

    class ArticleLayoutViewHolder(private var binding:ArticleLayoutBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article) {
            binding.article = article
            binding.publisherCreateTime.text =
                TimeUtil.StampToDate(article.createdTime)
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleLayoutViewHolder {
        return ArticleLayoutViewHolder(
            ArticleLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ArticleLayoutViewHolder, position: Int) {
        val article = getItem(position)
        holder.bind(article)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }
}