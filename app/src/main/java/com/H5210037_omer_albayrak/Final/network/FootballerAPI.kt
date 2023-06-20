package com.H5210037_omer_albayrak.Final.network

import com.H5210037_omer_albayrak.Final.model.Footballer
import retrofit2.http.GET

//Class tanımlaması
//Bu class sayesinde api'in nereye istek atacağını belirleriz ve ondan gelecek veriyi tanımlarız.

interface FootballerAPI {

    @GET("footballers.json") //istek atılacak alan.
    suspend fun getFootballers(): List<Footballer> //Gelecek veri. Daha doğrusu bizim çalışan fonksiyonumuz ve ondan gelecek veri.

}