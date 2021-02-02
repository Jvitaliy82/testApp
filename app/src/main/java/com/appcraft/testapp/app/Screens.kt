package com.appcraft.testapp.app

import android.content.Intent
import android.net.Uri
import com.appcraft.domain.model.TvShowItemMP
import com.appcraft.testapp.R
import com.appcraft.testapp.app.ui.fragment.detail.DetailFlowFragment
import com.appcraft.testapp.app.ui.fragment.detail.DetailFragment
import com.appcraft.testapp.app.ui.fragment.main.MainFlowFragment
import com.appcraft.testapp.app.ui.fragment.main.MainFragment
import com.appcraft.testapp.app.ui.fragment.splash.SplashFlowFragment
import com.appcraft.testapp.app.ui.fragment.splash.SplashFragment
import com.github.terrakok.cicerone.androidx.ActivityScreen
import pro.appcraft.lib.navigation.getFragmentScreen

object Screens {
    object Flow {
        fun splash() = SplashFlowFragment::class.getFragmentScreen()

        fun main() = MainFlowFragment::class.getFragmentScreen()

        fun detail(
            item: TvShowItemMP,
            from_save: Boolean = false
        ) = DetailFlowFragment::class.getFragmentScreen(
            DetailFlowFragment.ID to item,
            DetailFlowFragment.FROM_SAVE to from_save
        )
    }

    object Screen {
        fun splash() = SplashFragment::class.getFragmentScreen()

        fun main() = MainFragment::class.getFragmentScreen()

        fun detail(
            item: TvShowItemMP,
            from_save: Boolean = false
        ) = DetailFragment::class.getFragmentScreen(
            DetailFragment.ID to item,
            DetailFragment.FROM_SAVE to from_save
        )

    }

    // COMMON ACTIONS
    object Common {
        @Suppress("unused")
        fun actionAppSettings() = ActivityScreen("actionAppSettings") { context ->
            Intent(
                android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                Uri.parse("package:" + context.packageName)
            ).apply {
                addCategory(Intent.CATEGORY_DEFAULT)
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
        }

        @Suppress("unused", "UNUSED_ANONYMOUS_PARAMETER")
        fun actionOpenLink(
            link: String
        ) = ActivityScreen("actionOpenLink") { context ->
            Intent(
                Intent.ACTION_VIEW
            ).apply {
                data = Uri.parse(link)
            }
        }

        @Suppress("unused", "UNUSED_ANONYMOUS_PARAMETER")
        fun actionOpenDial(
            phone: String
        ) = ActivityScreen("actionOpenDial") { context ->
            Intent(
                Intent.ACTION_DIAL
            ).apply {
                data = Uri.fromParts("tel", phone, null)
            }
        }

        @Suppress("unused")
        fun actionMailTo(
            email: String,
            subject: String
        ) = ActivityScreen("actionMailTo") { context ->
            Intent.createChooser(
                Intent(
                    Intent.ACTION_SENDTO
                ).apply {
                    data = Uri.parse("mailto:")
                    putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
                    putExtra(Intent.EXTRA_SUBJECT, subject)
                },
                context.resources.getString(R.string.write_on_email)
            )
        }

        @Suppress("unused")
        fun actionShareText(
            text: String,
            header: String? = null
        ) = ActivityScreen("actionShareText") { context ->
            Intent.createChooser(
                Intent(
                    Intent.ACTION_SEND
                ).apply {
                    putExtra(Intent.EXTRA_TEXT, text)
                    putExtra("sms_body", text)
                    addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT)
                    type = "text/plain"
                },
                header ?: context.resources.getString(R.string.share)
            )
        }

        @Suppress("unused")
        fun actionShareFile(
            uri: Uri,
            mimeType: String
        ) = ActivityScreen("actionShareFile") { context ->
            Intent.createChooser(
                Intent(
                    Intent.ACTION_SEND
                ).apply {
                    putExtra(Intent.EXTRA_STREAM, uri)
                    type = mimeType
                },
                context.resources.getString(R.string.share)
            )
        }

        @Suppress("unused")
        fun actionOpenFolder(
            uri: Uri
        ) = ActivityScreen("actionOpenFolder") { context ->
            Intent.createChooser(
                Intent(
                    Intent.ACTION_VIEW
                ).apply {
                    setDataAndType(uri, "*/*")
                },
                context.resources.getString(R.string.open_folder)
            )
        }

        @Suppress("unused")
        fun actionOpenFile(
            uri: Uri,
            mimeType: String
        ) = ActivityScreen("actionOpenFile") { context ->
            Intent.createChooser(
                Intent(
                    Intent.ACTION_VIEW
                ).apply {
                    setDataAndType(uri, mimeType)
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_GRANT_READ_URI_PERMISSION
                },
                context.resources.getString(R.string.open_file)
            )
        }
    }
}