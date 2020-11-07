package com.alexandrequeiroz.mycommand

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody

class HttpHelper {

    fun post(path: String, json: String): String {

        val URL = "http://34.70.245.32:3389/${path}"

        val headerHttp = MediaType.parse("aplication/json; charset=utf-8")

        val client = OkHttpClient()

        val body = RequestBody.create(headerHttp, json)

        var request = Request.Builder().url(URL).post(body).build()

        val response = client.newCall(request).execute()

        return response.body()!!.string()

    }

    fun get (path: String): String {

        val URL = "http://34.70.245.32:3389/${path}"

        val client = OkHttpClient()

        var request = Request.Builder().url(URL).get().build()

        val response = client.newCall(request).execute()

        val responseBody = response.body()

        return responseBody!!.string()

    }

}

inline fun <reified T> parserJson(json: String): T {
    val type = object : TypeToken<T>(){}.type
    return Gson().fromJson<T>(json, type)
}