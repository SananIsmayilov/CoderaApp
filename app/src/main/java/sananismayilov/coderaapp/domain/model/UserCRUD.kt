package sananismayilov.coderaapp.domain.model

import com.google.gson.annotations.SerializedName

data class UserCRUD(

    @SerializedName("success")
    val success: Int,
    @SerializedName("message")
    val message: String


)