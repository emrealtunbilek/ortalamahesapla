package com.emrealtunbilek.ortalamahesapla

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.view.animation.TranslateAnimation
import kotlinx.android.synthetic.main.activity_splash.*

class ActivitySplash : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        var asagidanGelenButon = AnimationUtils.loadAnimation(this, R.anim.asagidangelenbuton)
        var yukaridanGelenBalon = AnimationUtils.loadAnimation(this, R.anim.yukaridangelenbalon)

        var asagiyaGeriDonenButon = AnimationUtils.loadAnimation(this, R.anim.asagigidenbuton)
        var yukariyaGeriDonenBalon = AnimationUtils.loadAnimation(this, R.anim.yukariyagidenbalon)

        btnOrtalamaHesaplaAnim.animation = asagidanGelenButon
        imgBalon.animation = yukaridanGelenBalon

        btnOrtalamaHesaplaAnim.setOnClickListener {

            btnOrtalamaHesaplaAnim.startAnimation(asagiyaGeriDonenButon)
            imgBalon.startAnimation(yukariyaGeriDonenBalon)

        }


    }
}
