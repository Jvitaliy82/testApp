package com.appCraft.testApp.dispatcher.event


sealed class CustomEvent {
    class SampleEvent(
        val stringData: String,
        val longData: Long
    ) : CustomEvent()
}
