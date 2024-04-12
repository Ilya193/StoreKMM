package ru.ikom.storekmm.android

import android.app.Application
import ru.ikom.storekmm.AppModule

class App : Application() {

    private val repository by lazy {
        AppModule.provideRepository()
    }

    val viewModel: MainViewModel by lazy {
        MainViewModel(repository)
    }

}