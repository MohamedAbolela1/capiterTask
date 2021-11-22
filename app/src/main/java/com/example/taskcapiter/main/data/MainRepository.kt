package com.example.taskcapiter.main.data

import com.example.taskcapiter.main.data.apiInterface.RetrofitApisInterface
import com.example.taskcapiter.main.domain.IMainRepository
import com.example.taskcapiter.models.PostsListModel
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val retrofitApisInterface: RetrofitApisInterface
) : IMainRepository {
    override suspend fun getPostsList(kind: String,limit:Int): PostsListModel {
        return retrofitApisInterface.getPostsList(kind,limit)
    }

    override suspend fun getOtherPostsList(kind: String,limit:Int,after:String): PostsListModel {
        return retrofitApisInterface.getOthersPostsList(kind,limit,after)
    }
}
