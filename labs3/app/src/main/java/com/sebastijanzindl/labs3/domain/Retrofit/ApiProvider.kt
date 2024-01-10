package com.sebastijanzindl.labs3.domain.Retrofit

import com.google.gson.GsonBuilder
import com.sebastijanzindl.labs3.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class ApiProvider {
    companion object {
        @Volatile
        private var INSTANCE: API? = null

        @JvmStatic
        fun getAPI(): API {
            return INSTANCE ?: synchronized(this) {
                val instance = createAPI()
                INSTANCE = instance
                instance
            }
        }

        private  fun createAPI(): API {
            class QueryParamInterceptor: Interceptor {
                @Throws(IOException::class)
                override fun intercept(chain: Interceptor.Chain): Response {
                    var request: Request = chain.request()

                    val http = request.url.newBuilder()
                        .addQueryParameter("apikey", BuildConfig.MOVIE_API_KEY)
                        .addQueryParameter("plot", "short")
                        .build()

                    request = request.newBuilder().url(http).build()

                    return chain.proceed(request)
                }
            }
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            val okhttpClient = OkHttpClient.Builder()
                .addInterceptor(QueryParamInterceptor())
                .addInterceptor(httpLoggingInterceptor)
                .build()

            val gson = GsonBuilder()
                .setLenient()
                .create()

            val gsonConverterFactory = GsonConverterFactory.create(gson)

            val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.MOVIE_API_BASE_URL)
                .client(okhttpClient)
                .addConverterFactory(gsonConverterFactory)
                .build()
            return retrofit.create(API::class.java)

        }
    }
}