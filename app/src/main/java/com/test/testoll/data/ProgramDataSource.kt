package com.test.testoll.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.test.testoll.network.APIService
import java.io.IOException
import kotlin.collections.ArrayDeque

class ProgramDataSource(
    private val apiService: APIService,
    private val uuid: String,
    ) : PagingSource<Int, Item>(){

    override fun getRefreshKey(state: PagingState<Int, Item>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(2)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Item> {
        val key = params.key ?: 0

        return try {
            val data = when (params) {
                is LoadParams.Refresh -> {
                    initialCall()
                }
                is LoadParams.Append -> {
                    loadNext(key)
                }
                is LoadParams.Prepend -> {
                    loadPrevious(key)
                }
            }

            LoadResult.Page(
                data = data.result!!,
                prevKey = data.prevKey,
                nextKey = data.nextKey
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        }
    }

    override val keyReuseSupported: Boolean = true

    data class ProgramResult(
        val result: ArrayDeque<Item>?,
        val nextKey: Int?,
        val prevKey: Int?
    )

    private suspend fun initialCall(): ProgramResult {

        val response = apiService.getPrograms(
            uuid = uuid,
            borderID = 0,
            direction = 0
        )

        return ProgramResult(
            result = addInArrayDeque(response),
            nextKey = response.items.last().id,
            prevKey = response.items.first().id
        )
    }

    private suspend fun loadNext(id: Int): ProgramResult {
        val response = apiService.getPrograms(
            uuid = uuid,
            borderID = id,
            direction = 1
        )
        return ProgramResult(
            result = addInArrayDeque(response),
            nextKey = response.items.last().id,
            prevKey = response.items.first().id
        )
    }

    private suspend fun loadPrevious(id: Int): ProgramResult {
        val response = apiService.getPrograms(
            uuid = uuid,
            borderID = id,
            direction = -1
        )
        return ProgramResult(
            result = addInArrayDeque(response),
            nextKey = response.items.last().id,
            prevKey = response.items.first().id
        )
    }

    private fun addInArrayDeque(response: PagedResponse): ArrayDeque<Item> {
        val result: ArrayDeque<Item> = ArrayDeque()
        response.items.forEach {
            result.add(it)
        }

        return result
    }
}