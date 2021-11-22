package com.example.taskcapiter.main.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskcapiter.base.*
import com.example.taskcapiter.databinding.ActivityMainBinding
import com.example.taskcapiter.models.PostsItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() , OnListItemClickListener<PostsItem.Children> {

    private val mainViewModel by viewModels<MainViewModel>()
    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate
    private lateinit var postsAdapter: PostsAdapter

    var isLoading = false
    var isLastPage = false

    var linearLayoutManager: LinearLayoutManager = LinearLayoutManager(
        this@MainActivity,
        LinearLayoutManager.VERTICAL,
        false
    )

    override fun setup() {
        initReposRecyclerView()
        bindsViewModel()
    }

    private fun initReposRecyclerView() {
        with(binding) {
            postsAdapter = PostsAdapter(this@MainActivity)
            rvRepos.layoutManager = linearLayoutManager
            rvRepos.adapter = postsAdapter
            rvRepos.addOnScrollListener(object : PaginationScrollListener(linearLayoutManager) {
                override fun loadMoreItems() {
                    isLoading = true
                    mainViewModel.loadMorePosts()
                }

                override fun isLastPage(): Boolean = isLastPage

                override fun isLoading(): Boolean = isLoading
            })
        }
    }

    private fun bindsViewModel() {
        with(mainViewModel) {
            isLastPage = lastPage
            postsList.observe(this@MainActivity, ::observeReposList)
        }
    }

    private fun observeReposList(status: Status<MutableList<PostsItem.Children>>) {
        when (status) {
            is Status.Loading -> {
                isLoading = true
                binding.pbLoading.visibility = VISIBLE
            }
            is Status.Success -> bindReposList(status.data!!)
            is Status.Error -> bindErrorStatus(status.errorTypes)
        }
    }

    private fun bindReposList(postsList: MutableList<PostsItem.Children>) {
        binding.pbLoading.visibility = GONE
        isLoading = false
        postsAdapter.insertAll(postsList)
    }

    private fun bindErrorStatus(errorTypes: ErrorTypes) {
        // add values to the view
        isLoading = false
        binding.pbLoading.visibility = GONE
    }

    override fun onItemClicked(view: View?, model: PostsItem.Children) {

    }

}
