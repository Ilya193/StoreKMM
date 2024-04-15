package ru.ikom.storekmm.di

import ru.ikom.storekmm.sqlDriverModule

fun sharedModule() = listOf(commonModule, sqlDriverModule, databaseModule)