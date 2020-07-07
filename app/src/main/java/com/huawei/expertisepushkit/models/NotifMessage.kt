package com.huawei.carcodescanner.models

data class NotifMessage(

    //@SerializedName("access_token")
    var code: String,

    //@SerializedName("expires_in")
    var msg: Int,

    //@SerializedName("token_type")
    var requestId: String

)