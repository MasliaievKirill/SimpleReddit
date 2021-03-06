package com.masliaiev.simplereddit.di

import android.app.Application
import com.masliaiev.simplereddit.presentation.activities.MainActivity
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [DataModule::class, ViewModelModule::class])
interface ApplicationComponent {

    fun inject(activity: MainActivity)

    @Component.Factory
    interface Factory{

        fun create(@BindsInstance application: Application): ApplicationComponent
    }

}