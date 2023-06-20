package com.H5210037_omer_albayrak.Final.network

import com.H5210037_omer_albayrak.Final.util.Constans
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//Class tanımlaması
//Bu class sayesinde bir http isteği oluştururuz. Bu istek retrofit kütüphanesini kullanarak bize internetten istek atıp istekten gelen veriyi almamızı sağlar

object FootballerService {


    fun build(): FootballerAPI {

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHtppClient =  OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constans.BASE_URL_FOOTBALLER_SERVICE) //Buraya bizim url'mizi veriyoruz ve istek atılacak alanı gösteriyoruz.
            .client(okHtppClient)
            .build()

        return retrofit.create(FootballerAPI::class.java) //Burada FootballerAPI ile create ediyor
    }
}