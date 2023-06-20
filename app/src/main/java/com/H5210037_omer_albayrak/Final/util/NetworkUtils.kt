package com.H5210037_omer_albayrak.Final.util

import android.content.Context
import android.net.ConnectivityManager

//Class açıklaması
//Bu class sayesinde kullanıcının telefonunda internet erişimi var mı ona bakıyoruz

object NetworkUtils {

    fun isInternetAvailable(context: Context):Boolean{ //Bu fonksiyon sayesinde eğer kullanıcı bağlıysa veya bağlı değilse bir bool dönüyor

        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val networkInfo = connectivityManager.activeNetworkInfo

        return networkInfo!= null && networkInfo.isConnected

    }

}