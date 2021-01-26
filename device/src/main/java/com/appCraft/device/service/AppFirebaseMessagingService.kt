package com.appCraft.device.service

import android.util.Log
import com.appCraft.device.manager.NotificationMessageManager
import com.appCraft.domain.global.CoroutineProvider
import com.appCraft.domain.interactor.auth.GetAuthorizationTokenUseCase
import com.appCraft.domain.interactor.notification.RegisterFirebaseTokenUseCase
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

private const val ID = "uuid"
private const val TITLE = "title"
private const val BODY = "body"
private const val TYPE = "notificationType"
private const val METADATA = "metadata"

class AppFirebaseMessagingService : FirebaseMessagingService() {
    private val manager: NotificationMessageManager by inject()
    private val coroutineProvider: CoroutineProvider by inject()
    private val registerFirebaseTokenUseCase: RegisterFirebaseTokenUseCase by inject()
    private val getAuthorizationTokenUseCase: GetAuthorizationTokenUseCase by inject()

    override fun onNewToken(newToken: String) {
        coroutineProvider.scopeIo.launch {
            getAuthorizationTokenUseCase().process(
                { token ->
                    if (token.isNotBlank()) {
                        registerFirebaseTokenUseCase(newToken).process(
                            { },
                            {
                                Log.e("MessagingService", it.message.orEmpty())
                            }
                        )
                    }
                },
                {
                    Log.e("MessagingService", it.message.orEmpty())
                }
            )
        }
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        val data = remoteMessage.data
        val title = if (data.containsKey(TITLE)) data[TITLE].orEmpty() else ""
        val body = if (data.containsKey(BODY)) data[BODY].orEmpty() else ""
        val type = if (data.containsKey(TYPE)) data[TYPE] else null
        val metadata = if (data.containsKey(METADATA)) data[METADATA] else null
        val id = if (data.containsKey(ID)) data[ID] else null

        manager.showMessageWithAction(
            title = title,
            body = body,
            type = type,
            data = metadata,
            id = id
        )
    }
}
