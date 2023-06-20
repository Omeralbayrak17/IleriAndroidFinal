package com.H5210037_omer_albayrak.Final.network


//Class tanımlaması
//Bu class sayesinde gelecek verimizin mümkün olan 3 halini gösteriyoruz.
sealed class Resource<T> (

    val data: T?, //Data yani verinin çekilmiş hali
    val throwable: Throwable?, //Hata alınan alan yani hatamız.
    val status:ResourceStatus //Loading olduğu an.
)

//Buradaki classlar sayesinde statuslere erişim sağlıyoruz ve bu statusler ile verimizin son halinden bilgi sahibi oluyoruz
{
    class Loading<T> : Resource<T>(null, null, ResourceStatus.LOADING)
    class Success<T>(data: T?) : Resource<T>(data, null, ResourceStatus.SUCCESS)
    class Error<T>(exception: Exception) : Resource<T>(null, exception, ResourceStatus.ERROR)
}
