package com.huawei.carcodescanner.models

import com.google.gson.annotations.SerializedName

data class AccessToken(

    //@SerializedName("access_token")
    var access_token : String,

    //@SerializedName("expires_in")
    var expires_in : Int,

    //@SerializedName("token_type")
    var token_type : String
)