package ru.ikom.storekmm.android

sealed class Screen(val route: String) {
    data object Start : Screen(Screens.START)
    data object Ktor : Screen(Screens.NETWORK)
    data object Database : Screen(Screens.DATABASE)
}

object Screens {
    const val START = "Start"
    const val NETWORK = "Network"
    const val DATABASE = "Database"
}