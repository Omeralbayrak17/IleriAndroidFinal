package com.H5210037_omer_albayrak.Final.data.footballer.dataSource

import com.H5210037_omer_albayrak.Final.model.Footballer
import com.H5210037_omer_albayrak.Final.network.FootballerService
import com.H5210037_omer_albayrak.Final.network.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


//Class Tanımlaması
//Bu class bir remote erişim sağlamak için açılmıştır. Buradaki amaç api ile bağlantı kurup internetten verileri çekmektir.
class FootballerRemoteDataSource : FootballerDataSource {

    override fun getAllFootballers(): Flow<Resource<List<Footballer>>> = flow {
        try { //Buradaki try catch methodu ile internete bir bağlantı göndeririz ve bu bağlantının sonucunu ele alarak bir dönüş verisi alırız.
            emit(Resource.Loading())
            val footballerList = FootballerService.build().getFootballers()
            emit(Resource.Success(footballerList))
        } catch (e: Exception) {
            emit(Resource.Error(e))
        }
    }
}