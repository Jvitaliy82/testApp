package com.appcraft.testapp.app.dispatcher.notifier

import androidx.annotation.StringRes
import com.appcraft.domain.global.CoroutineProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
class Notifier(
    private val coroutineProvider: CoroutineProvider
) {
    private val notifierChannel = BroadcastChannel<SystemMessage>(Channel.BUFFERED)

    fun subscribe(): ReceiveChannel<SystemMessage> {
        return notifierChannel.openSubscription()
    }

    fun sendMessage(text: String, level: SystemMessage.Level = SystemMessage.Level.NORMAL) {
        val msg = SystemMessage(
            text = text,
            type = SystemMessage.Type.BAR,
            level = level
        )
        coroutineProvider.scopeDefault.launch {
            notifierChannel.send(msg)
        }
    }

    fun sendMessage(
        @StringRes stringRes: Int,
        level: SystemMessage.Level = SystemMessage.Level.NORMAL
    ) {
        val msg = SystemMessage(
            textRes = stringRes,
            type = SystemMessage.Type.BAR,
            level = level
        )
        coroutineProvider.scopeDefault.launch {
            notifierChannel.send(msg)
        }
    }

    fun sendAlert(text: String) {
        val msg = SystemMessage(
            text = text,
            type = SystemMessage.Type.ALERT
        )
        coroutineProvider.scopeDefault.launch {
            notifierChannel.send(msg)
        }
    }

    fun sendAlert(@StringRes stringRes: Int) {
        val msg = SystemMessage(
            textRes = stringRes,
            type = SystemMessage.Type.ALERT
        )
        coroutineProvider.scopeDefault.launch {
            notifierChannel.send(msg)
        }
    }

    fun sendActionMessage(
        @StringRes textRes: Int,
        @StringRes actionTextRes: Int,
        actionCallback: () -> Unit?
    ) {
        val msg = SystemMessage(
            textRes = textRes,
            actionTextRes = actionTextRes,
            actionCallback = actionCallback,
            type = SystemMessage.Type.ACTION
        )
        coroutineProvider.scopeDefault.launch {
            notifierChannel.send(msg)
        }
    }

    fun sendActionMessage(text: String, actionText: String, actionCallback: () -> Unit?) {
        val msg = SystemMessage(
            text = text,
            actionText = actionText,
            actionCallback = actionCallback,
            type = SystemMessage.Type.ACTION
        )
        coroutineProvider.scopeDefault.launch {
            notifierChannel.send(msg)
        }
    }
}
