package com.maeun.sopt_4th_applied

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast

import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.maeun.sopt_4th_applied.get.GetBoardResponse
import com.maeun.sopt_4th_applied.get.GetBoardResponseData
import kotlinx.android.synthetic.main.activity_detail.*

import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), View.OnClickListener{

    lateinit var networkService: NetworkService
    lateinit var boardAdapter : BoardAdapter
    lateinit var boardItems : ArrayList<GetBoardResponseData>
    lateinit var requestManager : RequestManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        networkService = ApplicationController.instance.networkSerVice
        requestManager = Glide.with(this)
        main_board_rv.layoutManager = LinearLayoutManager(this)


        main_write_btn.setOnClickListener{
            startActivity(Intent(applicationContext, BoardActivity::class.java))
        }

        val getBoardResponse = networkService.getContent()

        getBoardResponse.enqueue(object : Callback<GetBoardResponse> {

            override fun onFailure(call: Call<GetBoardResponse>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<GetBoardResponse>?, response: Response<GetBoardResponse>?) {

                if (response!!.isSuccessful) {
                    boardItems = response.body().data
                    boardAdapter = BoardAdapter(boardItems, requestManager)
                    boardAdapter.setOnItemClickListener(this@MainActivity)
                    main_board_rv.adapter = boardAdapter
                }
            }
        })
    }

    override fun onClick(v: View?) {
        val idx : Int = main_board_rv.getChildAdapterPosition(v)

        val id : String? = boardItems[idx].user_id
        val content : String? = boardItems[idx].board_content
        val photo : String? = boardItems[idx].board_photo

        val intent = Intent(applicationContext, DetailActivity::class.java)
        intent.putExtra("id", id)
        intent.putExtra("content", content)
        intent.putExtra("photo", photo)
        startActivity(intent)
    }
}
