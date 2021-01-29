package com.appcraft.testapp.app.dispatcher.event


sealed class CustomEvent {
    class SampleEvent(
        val stringData: String,
        val longData: Long
    ) : CustomEvent()
}
