package com.H5210037_omer_albayrak.Final.data.footballer.dataSource

import com.H5210037_omer_albayrak.Final.model.Footballer
import com.H5210037_omer_albayrak.Final.network.Resource
import kotlinx.coroutines.flow.Flow

//Class Tanımlaması
//Bu classın açılma sebebi bir dataSource'a bağlanıp oradan gelecek akışı göstermektir.

interface FootballerDataSource {

    fun getAllFootballers(): Flow<Resource<List<Footballer>>> // Burada bir flow (akış) döner ve bu akış list<Footballer> olarak gelir

}