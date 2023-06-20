package com.H5210037_omer_albayrak.Final.ui.menu

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.GridLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.H5210037_omer_albayrak.Final.adapter.FootballersAdapter
import com.H5210037_omer_albayrak.Final.databinding.ActivityMainBinding
import com.H5210037_omer_albayrak.Final.model.Footballer
import com.H5210037_omer_albayrak.Final.network.FootballerAPI
import com.H5210037_omer_albayrak.Final.network.FootballerService
import com.H5210037_omer_albayrak.Final.ui.footballerInformation.FootballerInformationActivity
import com.H5210037_omer_albayrak.Final.util.openNewScreen
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

//Class tanımı
//Bu class uygulamamızın ana ekranıdır. Bu ekranda recyclerview ve butonlarımız vardır.

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding //Binding, adapter, viewmodel ve liste tanımlamalarımız burada
    private lateinit var footballerAdapter: FootballersAdapter
    private var footballerViewModel: MainActivityViewModel? = null
    var footballerList: ArrayList<Footballer> = getFootballers()

    enum class LIST_TYPE{ //Bir enumumuz var ve bu enum sayesinde kullanıcı istediği listeyi seçiyor
        LIST, GRID
    }

    var currentListType = LIST_TYPE.LIST //Kullanıcının current list'i. Bu kod sayesinde ilk açılışta linearlayout geliyor


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init(){

        initBinding()



    }

    private fun initViewModel(){ //Burada viewmodelimiz var ve verileri burada dinliyoruz
        footballerViewModel = MainActivityViewModel()



        footballerViewModel?.apply {

            footballerArray.observe(this@MainActivity) { //Observe ile verilere subscribe oluyoruz

                footballerAdapter.notifyDataSetChanged()

            }
            loading.observe(this@MainActivity, Observer {

            })

            error.observe(this@MainActivity, Observer {
                Toast.makeText(applicationContext, "Error: " + it.localizedMessage, Toast.LENGTH_LONG).show()
            })

        }



    }

    private fun initBinding(){ //Burada uygulamada kullanacağımız fonksiyonları çağırıyoruz ve bindingimizi bağlıyoruz

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initChangeListType()
        initSortList()
        initViewModel()
        initRecyclerView(footballerList, LIST_TYPE.LIST)
    }

    private fun initChangeListType(){ //Bu kodda liste tipi değişiyor.

        binding.apply {
            binding.btnSwitchListType.setOnClickListener{


                when(currentListType){ //Current list type değişkeni ile verilerimizi değiştiriyoruz
                    LIST_TYPE.GRID ->{
                        currentListType = LIST_TYPE.LIST
                        initRecyclerView(footballerList, LIST_TYPE.LIST)

                    }
                    LIST_TYPE.LIST ->{
                        currentListType = LIST_TYPE.GRID
                        initRecyclerView(footballerList, LIST_TYPE.GRID)
                        recyclerViewFootballers.layoutManager = GridLayoutManager(applicationContext, 2)
                    }

                }
            }
        }
    }

    private fun initSortList(){ //Bu kodda butonumuz için yazılmıştır. Butona basarak bir alertDialogAçıyoruz.
        binding.apply {
            binding.btnSortListing.setOnClickListener {
                showListAlertDialog()
            }
        }
    }

    private fun showListAlertDialog() { //Bu alertDialog sayesinde kullanıcılar sıralama işlemini yapabiliyor
        val items = arrayOf("Sort by A to Z", "Sort by Z to A")

        val alertDialogBuilder = AlertDialog.Builder(this@MainActivity)
        alertDialogBuilder.setTitle("Choose your sorting")
        alertDialogBuilder.setItems(items) { _, selected ->
            val selectedOption = items[selected]
            when (selectedOption) {
                "Sort by Z to A" -> {
                    footballerList.sortByDescending { it.footballerName } //Burada verilerimizi sıralamayı ayarlıyoruz
                    footballerAdapter.notifyDataSetChanged() //Veriler sıralandıktan sonra recyclerview'i güncelliyoruz
                }
                "Sort by A to Z" -> {
                    footballerList.sortBy { it.footballerName }
                    footballerAdapter.notifyDataSetChanged()
                }
            }
        }

        val alertDialog = alertDialogBuilder.create()
        alertDialog.show() //Alertdialogu gösteriyoruz
    }



    fun getFootballers(): ArrayList<Footballer> { //Bu kod sayesinde futbolcuların olduğu listemizi internetten çekerek alıyoruz
        return runBlocking {
            val footballers = FootballerService.build().getFootballers()
            footballers as ArrayList<Footballer>
        }
    }

    fun initRecyclerView(footballerList: ArrayList<Footballer>, listType: LIST_TYPE) { //Recyclerview kodları burada çalışıyor


        binding.apply {
            footballerAdapter = FootballersAdapter(footballerList, listType) { position ->

                val footballer = footballerList[position] //Pozisyona göre tıklama işlemi burada gerçekleşiyor ve verilerimizi burada bağlıyoruz

                val footballerName = footballer.footballerName!!
                val footballerInformation = footballer.footballerInformation!!
                val url = footballer.url!!


                openNewScreen(footballerName, footballerInformation, url) //Yeni ekrana geçiş intenti

            }
            recyclerViewFootballers.adapter = footballerAdapter //Adapterimiz burada bağlanıyor
            recyclerViewFootballers.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        }
    }

}