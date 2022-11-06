package com.sample.marvelapp.data

import com.sample.marvelapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import java.math.BigInteger
import java.security.MessageDigest

class MarvelAuthorizationInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val privateKey = BuildConfig.PRIVATE_KEY
        val publicKey = BuildConfig.PUBLIC_KEY
        val ts = System.currentTimeMillis()
        val hash = md5(privateKey, publicKey, ts)
        var request = chain.request()
        val url = request.url.newBuilder()
            .addQueryParameter("ts", ts.toString())
            .addQueryParameter("apikey", publicKey)
            .addQueryParameter("hash", hash)
            .build()
        request = request.newBuilder().url(url).build()
        return chain.proceed(request)
    }

    private fun md5(private: String, public: String, ts: Long): String {
        val input = ts.toString() + private + public
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
    }
}