package com.hongwui.nyt.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hongwui.nyt.databinding.AdapterArticleListBinding
import com.hongwui.nyt.model.ArticleModel

class ArticleListAdapter(
    private val context: Context,
    private var data: List<ArticleModel>,
    private val clickListener: (ArticleModel) -> Unit,
) : RecyclerView.Adapter<ArticleListAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            AdapterArticleListBinding.inflate(LayoutInflater.from(context), parent, false)
        )
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(data[position])

    inner class ViewHolder(private val binding: AdapterArticleListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ArticleModel) {
            binding.apply {
                tvTitle.text = data.title
                tvDate.text = data.publishDate
                binding.root.setOnClickListener { clickListener(data) }
            }
        }
    }
}