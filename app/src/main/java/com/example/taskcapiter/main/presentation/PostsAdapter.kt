package com.example.taskcapiter.main.presentation

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import com.bumptech.glide.Glide
import com.example.taskcapiter.R
import com.example.taskcapiter.databinding.ItemPostsBinding
import com.example.taskcapiter.base.BaseAdapter
import com.example.taskcapiter.base.BaseViewHolder
import com.example.taskcapiter.base.OnListItemClickListener
import com.example.taskcapiter.models.PostsItem


class PostsAdapter(
    onListItemClickListener: OnListItemClickListener<PostsItem.Children>?,
) : BaseAdapter<PostsItem.Children>(onListItemClickListener) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<PostsItem.Children> {
        val binding = ItemPostsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReposViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: BaseViewHolder<PostsItem.Children>, position: Int) {
        holder.onBind(data[position])
    }

    inner class ReposViewHolder(private val binding: ItemPostsBinding) :
        BaseViewHolder<PostsItem.Children>(binding.root) {

        init {
            with(binding){

                ivPlay.setOnClickListener{
                    val mediaController = MediaController(root.context)
                    mediaController.setAnchorView(videoView)
                    ivClose.visibility = View.VISIBLE
                    progress.visibility = View.VISIBLE
                    tvLoading.visibility = View.VISIBLE
                    videoView.visibility = View.VISIBLE
                    ivPlay.visibility = View.GONE
                    videoView.setZOrderOnTop(true)
                    videoView.requestFocus()
                    videoView.setMediaController(mediaController)
                    videoView.setVideoPath(data[adapterPosition].data?.secure_media?.reddit_video?.fallback_url)
                    videoView.start()
                }

                ivClose.setOnClickListener{
                    ivClose.visibility = View.GONE
                    progress.visibility = View.GONE
                    tvLoading.visibility = View.GONE
                    videoView.visibility = View.GONE
                    ivPlay.visibility = View.VISIBLE
                    videoView.stopPlayback()
                }

            }

            binding.ivFavourite.setOnClickListener(this)
        }

        override fun onBind(item: PostsItem.Children) {
            with(binding) {
                if (item.data?.is_video!!){
                    ivPlay.visibility = View.VISIBLE
                } else {
                    ivPlay.visibility = View.GONE
                }
                tvTitle.text = item.data.title
                Glide.with(root.context).load(item.data.thumbnail)
                    .placeholder(R.mipmap.ic_launcher).into(ivPostThumb)
            }
        }

        override fun onClick(view: View?) {
            onListItemClickListener?.onItemClicked(view, data[adapterPosition])
        }
    }
}
