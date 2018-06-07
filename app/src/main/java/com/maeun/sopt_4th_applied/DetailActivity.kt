package com.maeun.sopt_4th_applied

import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_detail.*
import android.graphics.Bitmap
import android.transition.Transition
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.Glide




class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        detail_writer_tv.setText(intent.getStringExtra("id"))
        detail_content_tv.setText(intent.getStringExtra("content"))
        //이미지를 서버 주소 형식으로 받아오는 것이어서(String 값으로 받아옴), 기존에 drawable에 있는 이미지를 꺼내는 것(Int 값으로 받아옴)과는 다름
        //detail_photo_iv.setImageResource 이거는 Int 값만 받을 수 있고
        //detail_photo_iv.setImageBitmap 이거는 bitmap 값으로 받아오는 것인데,
        //제일 간단한 것은 Glide를 이용하는 것
        Glide.with(this).load(intent.getStringExtra("photo")).into(detail_photo_iv)
    }
}
