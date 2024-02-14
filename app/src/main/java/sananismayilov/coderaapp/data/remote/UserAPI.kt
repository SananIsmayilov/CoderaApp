package sananismayilov.coderaapp.data.remote

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Query
import sananismayilov.coderaapp.domain.model.UserCRUD

interface UserAPI {

    @POST("coderaApp/insertUser.php")
    @FormUrlEncoded
    suspend fun signUpUser(
        @Field("user_email") user_email: String,
        @Field("user_password") user_password: String,
        @Field("device_id") device_id: String
    ): UserCRUD


    @POST("coderaApp/getUser.php")
    @FormUrlEncoded
   suspend fun LoginUser(
        @Field("user_email") user_email: String,
        @Field("user_password") user_password: String,
        @Field("device_id") device_id: String
    ): UserCRUD

}