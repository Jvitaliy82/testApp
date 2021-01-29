package com.appcraft.domain.global

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope

class CoroutineProvider {
    val scopeIo = CoroutineScope(Dispatchers.IO)
    val scopeDefault = GlobalScope
    val scopeMain = MainScope()
    val scopeMainImmediate = CoroutineScope(Dispatchers.Main.immediate)
    val scopeUnconfined = CoroutineScope(Dispatchers.Unconfined)
}
