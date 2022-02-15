package com.test.testoll.data

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.test.testoll.network.APIService
import kotlinx.coroutines.flow.Flow

class ProgramViewModel(
    private val apiService: APIService
    ) : ViewModel() {

    @SuppressLint("CheckResult")
    fun getProgramListPaged(uuid: String): Flow<PagingData<Item>> {
        return Pager(PagingConfig(pageSize = 1)) {
            ProgramDataSource(apiService,  uuid = uuid)
        }.flow.cachedIn(viewModelScope)
    }
}