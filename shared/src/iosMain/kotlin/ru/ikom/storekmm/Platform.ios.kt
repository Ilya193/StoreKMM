package ru.ikom.storekmm

import platform.UIKit.UIDevice

class IOSPlatform: Platform {
    override val name:
}

enum class NetworkState {
    Success,
    Error
}

actual fun getPlatform(): Platform = IOSPlatform()