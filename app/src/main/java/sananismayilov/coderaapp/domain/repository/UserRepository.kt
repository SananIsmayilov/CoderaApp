package sananismayilov.coderaapp.domain.repository

import sananismayilov.coderaapp.domain.model.UserCRUD

interface UserRepository {
    suspend fun SignUpUser(user_email : String, user_password : String, device_id : String) : UserCRUD

    suspend fun loginUser(user_email : String,user_password : String,device_id : String) : UserCRUD
}