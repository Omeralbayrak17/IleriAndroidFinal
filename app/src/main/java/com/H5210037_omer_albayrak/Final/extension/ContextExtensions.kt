package com.H5210037_omer_albayrak.Final.util

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.H5210037_omer_albayrak.Final.ui.footballerInformation.FootballerInformationActivity


//Class tanımlaması
//Bu class ekran içinde ve ekranlar arası kullandığımız fonksiyonları tutuyor.


fun Activity.openNewScreen( footballerName: String, footballerInformation: String, url: String){ //Bu kod sayesinde futbolcu detay ekranına veri taşırız
        val newScreenIntent = Intent(this, FootballerInformationActivity::class.java)

        val bundle = Bundle()

        bundle.putString("key1", footballerName) //Bundle sayesinde istediğimiz kadar veriyi diğer ekrana taşırız

        bundle.putString("key2", footballerInformation)

        bundle.putString("key3", url)

        newScreenIntent.putExtras(bundle) //Yeni ekrana bundle'inda gitmesi için bundle'i putextra ile ekliyoruz

        startActivity(newScreenIntent)
    }

fun toastInternetConnection(activity: Activity, internetInformation: String){ //Bu fonksiyon sayesinde kullanıcının internet erişimi test ediyoruz ve ona göre bir toast basıyoruz
        Toast.makeText(activity, internetInformation, Toast.LENGTH_LONG).show()
}