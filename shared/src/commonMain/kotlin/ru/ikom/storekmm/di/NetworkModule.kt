package ru.ikom.storekmm.di

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module
import ru.ikom.storekmm.domain.goods.StoreRepository
import ru.ikom.storekmm.data.goods.StoreRepositoryImpl

val commonModule = module {
    single<HttpClient> {
        HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    useAlternativeNames = false
                })
            }
        }
    }

    factory<StoreRepository> {
        StoreRepositoryImpl(get())
    }
}