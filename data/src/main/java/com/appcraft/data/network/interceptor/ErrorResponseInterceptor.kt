package com.appcraft.data.network.interceptor

import com.appcraft.data.global.NetErrorCode.BAD_REQUEST
import com.appcraft.data.global.NetErrorCode.UNAUTHORIZED
import com.appcraft.data.preference.Preferences
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
