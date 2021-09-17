package com.example.androiddevhw_2.lesson_13.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.androiddevhw_2.lesson_12.room.User

interface UserStore {
    fun getUser(pageNumber: Int): List<User>
    fun nextPageIndex(): Int
}

class UsersPagingSource(private val userStore: UserStore) : PagingSource<Int, User>() {
    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val page = state.closestPageToPosition(anchorPosition)
            page?.prevKey?.plus(1) ?: page?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        val nextPageNumber = params.key ?: 1
        val response = userStore.getUser(nextPageNumber)
        return LoadResult.Page(
            data = response,
            prevKey = null,
            nextKey = userStore.nextPageIndex()
        )
    }
}