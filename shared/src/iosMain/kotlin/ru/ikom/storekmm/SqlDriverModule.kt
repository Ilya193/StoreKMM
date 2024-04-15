package ru.ikom.storekmm

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
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
