package ru.ikom.storekmm.android

import android.app.Application
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import ru.ikom.storekmm.sharedModule

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModule + sharedModule())
        }
    }

}

val appModule = module {
    viewModel<MainViewModel> {
        MainViewModel(get())
    }
}