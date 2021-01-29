package com.appcraft.testapp.app.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.os.bundleOf
import com.appcraft.device.manager.PARAM_METADATA
import com.appcraft.device.manager.PARAM_TYPE
import com.appcraft.testapp.app.ui.AppActivity

class NotificationReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val activityIntent = Intent(context, AppActivity::class.java).apply {
            putExtras(
                bundleOf(
                    PARAM_TYPE to intent.getStringExtra(PARAM_TYPE),
                    PARAM_METADATA to intent.getStringExtra(PARAM_METADATA)
                )
            )
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        context.startActivity(activityIntent)
    }

    companion object {
        const val BROADCAST_RECEIVER = "com.appCraft.testApp.notification"
    }
}
