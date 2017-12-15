package com.emrealtunbilek.ortalamahesapla

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Spinner
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.yeni_ders_layout.view.*

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

            //statik alandan kullanıcının girdiği değerleri alalım
            var dersAdi = etDersAd.text.toString()
            var dersKredi = spnDersKredi.selectedItem.toString()
            var dersHarf = spnDersNot.selectedItem.toString()


            //dinamik oluşturulacak layoutta bulunan view öğelerine bu değerleri atayalım
            //böylece yeni layoutumuz kullanıcının girmiş oldugu değerler ile oluşturulacaktır.
            yeniDersView.etYeniDersAd.setText(dersAdi)
            yeniDersView.spnYeniDersKredi.setSelection(spinnerDegerinIndexiniBul(spnDersKredi, dersKredi))
            yeniDersView.spnYeniDersNot.setSelection(spinnerDegerinIndexiniBul(spnDersNot, dersHarf))





            rootLayout.addView(yeniDersView)


        }

    }




    fun ortalamaHesapla(view: View){

    }

    //spinner'a direk olarak string değer atayamıyoruz, o yüzden atayacagımız string ifadenin karşılığı olan
    //position değerini bulan ve bize geri döndüren methodu oluşturalım
    fun spinnerDegerinIndexiniBul(spinner:Spinner , aranacakDeger:String) : Int{

        var index = 0
        for (i in 0..spinner.count){

            if(spinner.getItemAtPosition(i).toString().equals(aranacakDeger)){

                index = i
                break
            }
        }

        return index


    }
}
