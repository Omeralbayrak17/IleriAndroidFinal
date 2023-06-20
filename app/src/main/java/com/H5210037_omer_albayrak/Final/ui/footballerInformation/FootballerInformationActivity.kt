package com.H5210037_omer_albayrak.Final.ui.footballerInformation

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.H5210037_omer_albayrak.Final.R
import com.H5210037_omer_albayrak.Final.databinding.ActivityFootballerInformationBinding
import com.H5210037_omer_albayrak.Final.databinding.ActivityMainBinding
import com.H5210037_omer_albayrak.Final.util.getFromUrl


//Class Tanımı
//Bu class sayesinde bize putextra ile gelen intent verilerini kullanarak bir ekran yaratıyoruz ve bu ekranda 2 textbox 1 imageview'a sahibiz.
class FootballerInformationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFootballerInformationBinding

    private fun init(){

        initBinding() //Bindingimiz çağırıyoruz



    }

    @SuppressLint("SuspiciousIndentation")
    private fun initBinding(){

        binding = ActivityFootballerInformationBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val bundle = intent.extras //Burada intentten gelen verileri çekiyoruz
        val value1 = bundle?.getString("key1")
        val value2 = bundle?.getString("key2")
        val value3 = bundle?.getString("key3")

        binding.txtFootballerName.text = value1 //Burada ise gelen verileri textboxlara ve imageviewa bağlıyoruz.

        binding.txtFootballerInformation.text = value2

            binding.imageView2.getFromUrl(value3!!)


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init() //init fonksiyonunu çağırarak oop kurallarına uygun bir kod yazmış oluyoruz

    }
}