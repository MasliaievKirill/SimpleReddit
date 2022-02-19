package com.masliaiev.simplereddit.di

import androidx.lifecycle.ViewModel
import com.masliaiev.simplereddit.presentation.view_models.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindMainViewModel(impl: MainViewModel): ViewModel

}