package com.example.marvelapp.network

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import java.math.BigInteger
import java.security.MessageDigest
import javax.inject.Inject


class Constants @Inject constructor() : Interceptor {
    private val md5 = MessageDigest.getInstance("MD5")
    private val apiKey = "24443ee7c47f138c9773b5e6cef4c2c7"
    private val privateKey = "c10587992a3a045efc2503147e1909458f48b9b7"

    override fun intercept(chain: Interceptor.Chain): Response {
        val ts = System.currentTimeMillis().toString()
        val input = ts + privateKey + apiKey
        val hash = BigInteger(1, md5.digest(input.toByteArray())).toString(16).padStart(32, '0')

        val originalRequest = chain.request()
        val url = originalRequest.url.newBuilder()
            .addQueryParameter("ts", ts)
            .addQueryParameter("apikey", apiKey)
            .addQueryParameter("hash", hash)
            .build()

        val requestBuilder = originalRequest.newBuilder().url(url)
        val request = requestBuilder.build()

        val response = chain.proceed(request)
        Log.d("Response", response.toString())

        return response
    }
}
