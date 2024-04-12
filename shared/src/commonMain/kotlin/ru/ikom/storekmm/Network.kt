package ru.ikom.storekmm

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText

class Network {
    private val client = HttpClient()

    suspend fun fetchData(): String {
        val response = client.get("https://ktor.io/docs/")
        return response.bodyAsText()
    }
}