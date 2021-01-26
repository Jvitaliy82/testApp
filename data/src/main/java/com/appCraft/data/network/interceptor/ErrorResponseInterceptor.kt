package com.appCraft.data.network.interceptor

import com.appCraft.data.global.NetErrorCode.BAD_REQUEST
import com.appCraft.data.global.NetErrorCode.UNAUTHORIZED
import com.appCraft.data.preference.Preferences
import mu.KotlinLogging
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

private val logger = KotlinLogging.logger {}

class ErrorResponseInterceptor(private val preferences: Preferences) : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        if (response.code >= BAD_REQUEST) {
            logger.warn(response.toString())
        }
        if (response.code == UNAUTHORIZED) {
            preferences.authToken.delete()
        }
        return response
    }
}
