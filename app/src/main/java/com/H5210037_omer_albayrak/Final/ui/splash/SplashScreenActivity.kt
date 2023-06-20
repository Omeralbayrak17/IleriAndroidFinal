package com.H5210037_omer_albayrak.Final.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.provider.Settings
import com.H5210037_omer_albayrak.Final.R
import com.H5210037_omer_albayrak.Final.ui.menu.MainActivity
import com.H5210037_omer_albayrak.Final.util.NetworkUtils
import com.H5210037_omer_albayrak.Final.databinding.ActivitySplashScreenBinding
import com.H5210037_omer_albayrak.Final.util.toastInternetConnection

//Class tanımlaması

//Bu classta splashscreen çalışmaktadır. 4 saniye sonra internet yoksa ayarlar menüsüne gider, varsa ana menüye gider.

class SplashScreenActivity : AppCompatActivity() {


    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        supportActionBar?.hide() //Bu kod sayesinde appbar saklanır ve gerçekçi bir splashscreen yaratılır.
    }

    private fun init(){

        initBinding()

    }

    private fun initBinding(){ //Binding bağlanır ve countdowntimer fonksiyonu çağırılır

        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initCountDownTimer()

    }

    private fun initCountDownTimer(){ //Bu kod sayesinde 4 saniye geri sayım çalışır

        object : CountDownTimer(4000, 1000){
            override fun onTick(p0: Long) {

            }

            override fun onFinish() { //4 saniye bitince eğer internet varsa internet var toastı gelir ve ana menüye gidilir
                if (NetworkUtils.isInternetAvailable(applicationContext)){

                    val string = getString(R.string.got_connection)

                    toastInternetConnection(this@SplashScreenActivity, string)

                    openListingScreen(MainActivity::class.java)
                }
                else{ //Eğer yok ise internet yok toastı basılır ve ayarlar menüsü açılır

                    val string = getString(R.string.no_connection)

                    toastInternetConnection(this@SplashScreenActivity, string)

                    startActivity(
                        Intent(
                            Settings.ACTION_WIFI_SETTINGS
                        )
                    )
                }
            }
        }.start()

    }

    //Bu kod ile bir intent çalışır ve yeni ekrana geçilir
    private fun openListingScreen(newScreen : Class<*>){

        val newScreenIntent = Intent(this, newScreen)
        startActivity(newScreenIntent)
        finish()

    }



}