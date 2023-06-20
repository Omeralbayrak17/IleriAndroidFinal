package com.H5210037_omer_albayrak.Final.ui.menu

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.H5210037_omer_albayrak.Final.data.footballer.FootballerRepository
import com.H5210037_omer_albayrak.Final.model.Footballer
import com.H5210037_omer_albayrak.Final.network.ResourceStatus
import kotlinx.coroutines.launch

//Class tanımlaması
//Burada viewmodelimiz mevcut. Viewmodelimiz veriyi dinliyor ve activityde kullanmamız için bizimle bağlantıda oluyor

class MainActivityViewModel: ViewModel() {

    val footballerRepository = FootballerRepository()

    var footballerArray = MutableLiveData<List<Footballer>>()
    var error = MutableLiveData<Throwable>()
    var loading = MutableLiveData<Boolean>()

    fun getFootballers() { //Burada futbolcular verisini çekme kodumuz var ve bu kod çalışınca verilerimizin gelişine göre çekilme işlemi başlıyor

        viewModelScope.launch {

            footballerRepository.getAllFootballers().collect {

                when (it.status) {
                    ResourceStatus.LOADING -> {
                        loading.value = true
                    }
                    ResourceStatus.ERROR -> {
                        loading.value = false
                        error.value = it.throwable
                    }
                    ResourceStatus.SUCCESS -> {
                        footballerArray.value = it.data
                    }

                }

            }
        }
    }

}