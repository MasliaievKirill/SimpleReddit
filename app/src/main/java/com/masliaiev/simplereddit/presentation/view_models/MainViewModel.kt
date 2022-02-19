package com.masliaiev.simplereddit.presentation.view_models

import androidx.lifecycle.ViewModel
import com.masliaiev.simplereddit.domain.usecases.GetPostsUseCase
import com.masliaiev.simplereddit.domain.usecases.InsertPostsUseCase
import com.masliaiev.simplereddit.domain.usecases.LoadPostsUseCase
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase,
    private val loadPostsUseCase: LoadPostsUseCase,
    private val insertPostsUseCase: InsertPostsUseCase
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    init {
        val disposable = loadPostsUseCase.loadPosts()
            .subscribeOn(Schedulers.io())
            .subscribe({
                insertPostsUseCase.insertPosts(it)
            }, {

            })
        compositeDisposable.add(disposable)
    }

    val posts = getPostsUseCase.getPosts()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}