package com.masliaiev.simplereddit

import android.app.Application
import com.masliaiev.simplereddit.di.DaggerApplicationComponent

class SimpleRedditApp : Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}