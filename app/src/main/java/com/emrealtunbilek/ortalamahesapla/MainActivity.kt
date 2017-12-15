package com.emrealtunbilek.ortalamahesapla

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnDersEkle.setOnClickListener {

            var inflater = LayoutInflater.from(this)
           /* var inflater2 = layoutInflater
            var inflater3=getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            inflater3.inflate()*/

            var yeniDersView = inflater.inflate(R.layout.yeni_ders_layout, null)
            scroolview.addView(yeniDersView)


        }

    }




    fun ortalamaHesapla(view: View){

    }
}
