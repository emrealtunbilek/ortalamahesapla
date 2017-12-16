package com.emrealtunbilek.ortalamahesapla

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.shashank.sony.fancytoastlib.FancyToast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.yeni_ders_layout.view.*

class MainActivity : AppCompatActivity() {

    //ders adı girerken otomatik tamamlamada çıkacak ders adları
    private val DERSLER= arrayOf("Matematik", "Türkçe", "Fizik", "Edebiyat","Algoritma","Tarih")

    //kullanıcının eklediği tüm dersleri ve bilgilerini tutan arraylist
    private var tumDerslerinBilgileri:ArrayList<Dersler> = ArrayList(5)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //otomatik tamamlama için adapter tanımladık ve de bunu edittextimize atadık
        //adapter sayesinde DERSLER deki string ifadeler tek tek alındı ve de
        //simple_dropdown item 1line isimli layoutun içinde buluna textviewa yazıldı
        var adapter=ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, DERSLER)

        //tanımladıgımız adapterin çalısacağı view öğesine bu atamayı yapıyoruz
        etDersAd.setAdapter(adapter)


        //eğer dersler listesi boş ise  hesapla butonu saklanır, değilse görünür yapılır
        if(rootLayout.childCount == 0){
            btnOrtalamaHesapla.visibility = View.INVISIBLE
        }else btnOrtalamaHesapla.visibility = View.VISIBLE




        btnDersEkle.setOnClickListener {

            if(!etDersAd.text.isNullOrEmpty()){
                var inflater = LayoutInflater.from(this)
                /* var inflater2 = layoutInflater
                 var inflater3=getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                 inflater3.inflate()*/

                var yeniDersView = inflater.inflate(R.layout.yeni_ders_layout, null)


                //yeni oluşturulan dersler için de yukarıdaki olusturdugumuz adapteri atıyoruz
                yeniDersView.etYeniDersAd.setAdapter(adapter)


                //statik alandan kullanıcının girdiği değerleri alalım
                var dersAdi = etDersAd.text.toString()
                var dersKredi = spnDersKredi.selectedItem.toString()
                var dersHarf = spnDersNot.selectedItem.toString()


                //dinamik oluşturulacak layoutta bulunan view öğelerine bu değerleri atayalım
                //böylece yeni layoutumuz kullanıcının girmiş oldugu değerler ile oluşturulacaktır.
                yeniDersView.etYeniDersAd.setText(dersAdi)
                yeniDersView.spnYeniDersKredi.setSelection(spinnerDegerinIndexiniBul(spnDersKredi, dersKredi))
                yeniDersView.spnYeniDersNot.setSelection(spinnerDegerinIndexiniBul(spnDersNot, dersHarf))

                //sil butonuna silme görevi atandı
                yeniDersView.btnDersSil.setOnClickListener {

                    rootLayout.removeView(yeniDersView)

                    //eğer dersler listesi boş ise veya silme sonrası listede ders kalmamıssa hesapla butonu saklanır, değilse görünür yapılır
                    if(rootLayout.childCount == 0){
                        btnOrtalamaHesapla.visibility = View.INVISIBLE
                    }else btnOrtalamaHesapla.visibility = View.VISIBLE
                }



                rootLayout.addView(yeniDersView)

                //yeni bir ders alanı eklenirse hesapla butonu görünür yapıldı
                btnOrtalamaHesapla.visibility = View.VISIBLE


                sifirla()

            }
            //Eğer ders adı girilmeden ekle butonu tıklanırsa uyarı verilir
            else {
                FancyToast.makeText(this,"Ders Adını Giriniz !",FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
            }




        }

    }

    //yeni bir ders eklendikten sonra en bastaki layoutta bulunan alanların değerleri temizlendi
    fun sifirla(){
        etDersAd.setText("")
        spnDersKredi.setSelection(0)
        spnDersNot.setSelection(0)
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


    //ortalama hesapla butonu tıklandıgında olusturulan tüm yeni derslerin bilgilerini
    //bir arraylistede topladık. Daha sonra burdaki değerleri kullanarak ortalama hesabını yaptık
    fun ortalamaHesapla(view: View){

        var toplamNot:Double = 0.0
        var toplamKredi = 0.0

        for (i in 0..rootLayout.childCount - 1){

            var tekSatir = rootLayout.getChildAt(i)

            var geciciDers= Dersler(tekSatir.etYeniDersAd.text.toString(),
                    ((tekSatir.spnYeniDersKredi.selectedItemPosition)+1).toString(),
                    tekSatir.spnYeniDersNot.selectedItem.toString())

            tumDerslerinBilgileri.add(geciciDers)
        }

        for (oankiDers in tumDerslerinBilgileri){

            toplamNot += harfiNotaCevir(oankiDers.dersHarfNot) * (oankiDers.dersKredi.toDouble())
            toplamKredi += oankiDers.dersKredi.toDouble()

        }


        //ORTALAMA HESABI YAPILIR VE KULLANICIYA GÖSTERİLİR

        FancyToast.makeText(this,"ORTALAMA : " + (toplamNot/toplamKredi) ,FancyToast.LENGTH_LONG,FancyToast.WARNING,true).show();
        tumDerslerinBilgileri.clear()

    }

    //gelen harf notlarını double değerleri çeviren fonksiyonumuz
    fun harfiNotaCevir(gelenNotHarfDegeri:String) : Double {

        var deger=0.0

        when(gelenNotHarfDegeri){

            "AA" -> deger=4.0
            "BA" -> deger =3.5
            "BB" -> deger = 3.0
            "CB" -> deger = 2.5
            "CC" -> deger = 2.0
            "DC" -> deger = 1.5
            "DD" -> deger = 1.0
            "FF" -> deger = 0.0

        }
        return deger



    }
}
