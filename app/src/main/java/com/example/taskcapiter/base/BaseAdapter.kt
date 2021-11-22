package com.example.taskcapiter.base

import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<item>(
    val onListItemClickListener: OnListItemClickListener<item>?,
) : RecyclerView.Adapter<BaseViewHolder<item>>() {
    var data: MutableList<item>
    protected var isLoadingAdded = false


    init {
        data = mutableListOf()
    }

    fun insertAll(insertedItemList: MutableList<item>) {
        data = insertedItemList
        notifyItemRangeInserted(data.size, insertedItemList.count())
    }

    fun updateAll(insertedItemList: MutableList<item>) {
        data.clear()
        data = insertedItemList
        notifyDataSetChanged()
    }

    fun addLoadingFooter(item: item) { // can add empty object
        isLoadingAdded = true
        data.add(item)
        notifyItemInserted(data.size - 1)
    }

    fun removeLoadingFooter() {
        isLoadingAdded = false
        val position = data.size - 1
        data.removeAt(position)
        notifyItemRemoved(position)
    }

}
