package com.rubensousa.dpadrecyclerview.sample

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rubensousa.dpadrecyclerview.sample.list.ListModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val list = ArrayList<ListModel>()
    private val listLiveData = MutableLiveData<List<ListModel>>()
    private val loadingStateLiveData = MutableLiveData<Boolean>()
    val loadingState: LiveData<Boolean> = loadingStateLiveData
    val listState: LiveData<List<ListModel>> = listLiveData

    init {
        for (i in 0 until 3) {
            list.add(generateList("List $i"))
        }
        listLiveData.postValue(ArrayList(list))
    }

    fun loadMore(selectedPosition: Int) {
        if (loadingState.value == true) {
            return
        }
        val diffToEnd = list.size - selectedPosition
        if (diffToEnd >= 2) {
            return
        }

        loadingStateLiveData.postValue(true)
        viewModelScope.launch(Dispatchers.Default) {
            for (i in 0 until 2) {
                list.add(generateList("List ${list.size}"))
            }
            delay(100L)
            listLiveData.postValue(ArrayList(list))
        }.invokeOnCompletion { loadingStateLiveData.postValue(false) }

    }

    private fun generateList(title: String): ListModel {
        val items = ArrayList<Int>()
        repeat(100) {
            items.add(it)
        }
        return ListModel(title, items)
    }


}
