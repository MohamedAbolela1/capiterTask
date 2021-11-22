package com.example.taskcapiter.main.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskcapiter.base.ErrorTypes
import com.example.taskcapiter.base.Status
import com.example.taskcapiter.main.domain.IMainRepository
import com.example.taskcapiter.models.PostsItem
import com.example.taskcapiter.models.PostsListModel
import com.example.taskcapiter.source.remote.interceptors.NoNetworkConnectionException
import com.example.taskcapiter.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: IMainRepository
) : ViewModel() {
    private lateinit var postsListModel: PostsListModel
    private val _postsList = MutableLiveData<Status<MutableList<PostsItem.Children>>>()
    val postsList: LiveData<Status<MutableList<PostsItem.Children>>> = _postsList
    var currentPage = 0
    var lastPage = false


    init {
        currentPage++
        getPostsList(Constants.RemoteUrls.All,25)
    }

    fun loadMorePosts() {
        if (postsListModel.data.after.isNullOrEmpty()) {
            lastPage = true
        } else {
            lastPage = false
            getOthersReposList(Constants.RemoteUrls.All,25, postsListModel.data.after!!)
        }
    }

    private fun getPostsList(kind: String,limit:Int) {
        viewModelScope.launch {
            emitReposListStatus(Status.Loading)
            try {
                postsListModel = mainRepository.getPostsList(kind,limit)
                mapReposList(postsListModel.data.children)
            } catch (ex: Exception) {
                if (ex is NoNetworkConnectionException) {
                    emitReposListStatus(Status.Error(ErrorTypes.NoNetwork))
                } else {
                    emitReposListStatus(Status.Error(ErrorTypes.GeneralError))
                }
            }
        }
    }

    private fun getOthersReposList(kind: String,limit:Int,after:String) {
        viewModelScope.launch {
            emitReposListStatus(Status.Loading)
            try {
                postsListModel.data.children.addAll(mainRepository.getOtherPostsList(kind,limit,after).data.children)
                mapReposList(postsListModel.data.children)
            } catch (ex: Exception) {
                if (ex is NoNetworkConnectionException) {
                    emitReposListStatus(Status.Error(ErrorTypes.NoNetwork))
                } else {
                    emitReposListStatus(Status.Error(ErrorTypes.GeneralError))
                }
            }
        }
    }

    private fun mapReposList(postsList: MutableList<PostsItem.Children>) {
        if (postsList.isNullOrEmpty()) {
            emitReposListStatus(Status.Error(ErrorTypes.NoData))
        } else {
            emitReposListStatus(Status.Success(postsList))
        }
    }

    private fun emitReposListStatus(status: Status<MutableList<PostsItem.Children>>) {
        _postsList.value = status
    }
}
