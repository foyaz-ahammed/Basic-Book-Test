package com.kou.example.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kou.example.entities.DataResult
import com.kou.example.entities.LoadResult
import com.kou.example.entities.Response
import com.kou.example.repository.MyRepository
import kotlinx.coroutines.launch

/**
 * [ViewModel] used on the main screen
 */
class MainViewModel(private val repository: MyRepository): ViewModel() {
    companion object {
        val TAG: String = MainViewModel::class.java.simpleName
    }

    /*-- livedata for the book list and loading status --*/
    private val _bookList = MutableLiveData<List<Response.Book>>()
    private val _loading = MutableLiveData<LoadResult>()

    /*-- public getter variables for the live data --*/
    val bookList: LiveData<List<Response.Book>>
        get() = _bookList
    val loading: LiveData<LoadResult>
        get() = _loading

    /**
     * Fetch data via repository
     */
    fun fetchData() {
        viewModelScope.launch {
            _loading.value = LoadResult.LOADING
            when(val result = repository.getUpcomingBookList()) {
                is DataResult.SUCCESS -> {
                    _bookList.value = result.data
                    _loading.value = LoadResult.SUCCESS
                }
                is DataResult.FAILURE -> {
                    Log.e(TAG, result.exception.toString())
                    _loading.value = LoadResult.FAILURE
                }
            }
        }
    }
}