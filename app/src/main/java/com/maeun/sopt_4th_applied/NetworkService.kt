package com.maeun.sopt_4th_applied

import com.maeun.sopt_4th_applied.get.GetBoardResponse
import com.maeun.sopt_4th_applied.post.PostBoardResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface NetworkService {

    @Multipart
    @POST("board")
    fun postBoard(
            @Part boardImg : MultipartBody.Part?,
            @Part("user_id") id : RequestBody,
            @Part("board_title") title: RequestBody,
            @Part("board_content") content : RequestBody,
            @Part("pw") pw : RequestBody
    ) : Call<PostBoardResponse>


    @GET("board")
    fun getContent() : Call<GetBoardResponse>
}