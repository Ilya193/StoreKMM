package ru.ikom.storekmm

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import org.koin.core.module.Module
import org.koin.dsl.module
import sqldelight.ru.ikom.storekmm.AppDatabase

actual val sqlDriverModule: Module = module {
    single<SqlDriver> {
        NativeSqliteDriver(AppDatabase.Schema, "notes.db")
    }

    single<AppDatabase> {
        AppDatabase(get())
    }
}
