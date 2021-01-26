package com.appCraft.testApp.dispatcher.event

import android.os.Handler
import android.os.Looper
import java.util.*
import kotlin.collections.ArrayList
import kotlin.reflect.KClass

class EventDispatcher {
    private val eventListeners = HashMap<String, MutableList<EventListener>>()

    fun addEventListener(customEventClass: KClass<out CustomEvent>, listener: EventListener): EventListener {
        val key = customEventClass.java.name
        if (eventListeners[key] == null) {
            eventListeners[key] = ArrayList()
        }

        val list = eventListeners[key]
        list?.add(listener)

        return listener
    }

    fun removeEventListener(listener: EventListener) = eventListeners
        .filter { it.value.size > 0 }
        .forEach { it.value.remove(listener) }

    fun sendEvent(customEvent: CustomEvent) {
        val key = customEvent::class.java.name
        eventListeners
            .filter { it.key == key && it.value.size > 0 }
            .forEach {
                it.value.forEach { listener ->
                    Handler(Looper.getMainLooper()).post {
                        listener.onEvent(
                            customEvent
                        )
                    }
                }
            }
    }

    interface EventListener {
        fun onEvent(event: CustomEvent)
    }
}