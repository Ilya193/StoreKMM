package ru.ikom.storekmm

import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import org.koin.core.module.Module
import org.koin.dsl.module
import sqldelight.ru.ikom.storekmm.AppDatabase

actual val sqlDriverModule: Module = module {
    single<SqlDriver> {
        AndroidSqliteDriver(
            schema = AppDatabase.Schema,
            context = get(),
            name = "notes.db"
        )
    }

    single<AppDatabase> {
        AppDatabase(get())
    }
}