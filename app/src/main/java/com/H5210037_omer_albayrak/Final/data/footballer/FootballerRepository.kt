package com.H5210037_omer_albayrak.Final.data.footballer

import com.H5210037_omer_albayrak.Final.data.footballer.dataSource.FootballerDataSource
import com.H5210037_omer_albayrak.Final.data.footballer.dataSource.FootballerRemoteDataSource
import com.H5210037_omer_albayrak.Final.model.Footballer
import com.H5210037_omer_albayrak.Final.network.Resource
import kotlinx.coroutines.flow.Flow

//Class Tanımlaması
//Bu class sayesinde bir repository açmış oluruz ve hangi database'a bağlanacağımızı burada belirleriz

class FootballerRepository {

    private var footballerDataSource:FootballerDataSource?=null

    init{

        footballerDataSource = FootballerRemoteDataSource() //Sadece buradaki satırı değiştirerek uygulamanın bütün database bağlantısını değiştirebiliriz

    }

    fun getAllFootballers(): Flow<Resource<List<Footballer>>> { //Bu fonksiyon ile bir flow döndürürüz ve gelecek veriye erişiriz.
        return footballerDataSource!!.getAllFootballers()
    }


}