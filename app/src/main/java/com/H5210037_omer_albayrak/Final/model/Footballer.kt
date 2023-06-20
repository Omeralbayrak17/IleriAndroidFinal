package com.H5210037_omer_albayrak.Final.model

import com.google.gson.annotations.SerializedName

//Class tanımlaması
//Bu class sayesinde bize gelecek veriyi tanımlıyoruz ve modelini oluşturuyoruz. MVVM mimarisinde önemli bir yere sahiptir çünkü verileri tanımlayarak işlerimizi kolaylaştırmış oluruz.

data class Footballer (

    @SerializedName("footballerName")
    val footballerName: String?,
    @SerializedName("footballerInformation")
    val footballerInformation: String?,
    @SerializedName("url")
    val url: String?

) : Comparable<Footballer> { //Burada ise listemizi sıralama fonksiyonu ile kullanabilmek için yazdığımız bir kod var. İnternetten buldum ancak kodun ne işe yaradığını bir altta anlatıyorum
    override fun compareTo(other: Footballer): Int {
        return footballerName!!.compareTo(other.footballerName!!) //Kıyas yapabilmek için yazılan bu kod kıyaslanacak alanı ister. footballerName yazdığımız için bir sıralama işleminde footballerName verisine göre kıyaslama ve listeleme yapar
    }
}