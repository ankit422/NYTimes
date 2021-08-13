package com.nytimes.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nytimes.data.entities.Seller
import com.nytimes.databinding.DataItemsBinding


class Adapter(private val context: Context, private val listener: ArticleItemListener) :
    RecyclerView.Adapter<DataViewHolder>() {

    interface ArticleItemListener {
        fun onClickedArticle(articleId: Int, code: String)
    }

    private var list: List<Seller> = ArrayList()

    fun setData(data: List<Seller>) {
        list = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val binding: DataItemsBinding =
            DataItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(binding, listener, context)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(list[position])

    override fun getItemCount(): Int = list.size
}

class DataViewHolder(
    private val itemBinding: DataItemsBinding,
    private val listener: Adapter.ArticleItemListener,
    private val context: Context
) : RecyclerView.ViewHolder(itemBinding.root), View.OnClickListener {

    private lateinit var article: Seller

    init {
        itemBinding.root.setOnClickListener(this)
    }

    fun bind(dataItem: Seller) {
        try {
            article = dataItem
            itemBinding.title.text = dataItem.display_name
            itemBinding.pinCode.text = "Update On: ${dataItem.newest_published_date} (${dataItem.update})"
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onClick(v: View?) {
        listener.onClickedArticle(article.id, article.list_name!!)
    }
}