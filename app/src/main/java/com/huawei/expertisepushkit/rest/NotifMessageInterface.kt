package com.huawei.carcodescanner.rest

import com.huawei.carcodescanner.models.NotifMessage
import com.huawei.carcodescanner.models.NotifMessageBody
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.Call
import retrofit2.http.*

interface NotifMessageInterface {
    //POST, Body
    @Headers("Content-Type:application/json; charset=UTF-8")
    @POST("v1/102411513/messages:send")
    fun createNotification(
        @Header("Authorization") authorization: String?,
        @Body notifMessageBody: NotifMessageBody) : Call<NotifMessage>
}