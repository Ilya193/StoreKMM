package ru.ikom.storekmm.android

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import ru.ikom.storekmm.di.sharedModule

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(sharedModule() + appModule)
        }
    }

}

val appModule = module {
    viewModel<NetworkViewModel> {
        NetworkViewModel(get())
    }

    viewModel<DatabaseViewModel> {
        DatabaseViewModel(get())
    }
}