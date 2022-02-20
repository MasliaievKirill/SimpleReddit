package com.masliaiev.simplereddit.presentation.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.cachedIn
import com.masliaiev.simplereddit.domain.usecases.GetPostsUseCase
import com.masliaiev.simplereddit.domain.usecases.InsertPostsUseCase
import com.masliaiev.simplereddit.domain.usecases.LoadPostsUseCase
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase,
    private val loadPostsUseCase: LoadPostsUseCase,
    private val insertPostsUseCase: InsertPostsUseCase,
    private val compositeDisposable: CompositeDisposable
) : ViewModel() {

    init {
        loadPosts()
    }

    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean>
        get() = _error

    val posts = getPostsUseCase.getPosts().cachedIn(this)

    private fun loadPosts() {
        val disposable = loadPostsUseCase.loadPosts()
            .subscribeOn(Schedulers.io())
            .subscribe({
                insertPostsUseCase.insertPosts(it)
                _error.postValue(false)
            }, {
                _error.postValue(true)
            })
        compositeDisposable.add(disposable)
    }

    fun retryLoading() {
        loadPosts()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}