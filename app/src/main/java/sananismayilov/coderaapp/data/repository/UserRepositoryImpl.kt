package sananismayilov.coderaapp.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import sananismayilov.coderaapp.data.remote.UserAPI
import sananismayilov.coderaapp.domain.model.UserCRUD
import sananismayilov.coderaapp.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(val userAPI: UserAPI) : UserRepository {
    override suspend fun SignUpUser(
        user_email: String,
        user_password: String,
        device_id: String
    ): UserCRUD =
        withContext(Dispatchers.Main) {
            userAPI.signUpUser(user_email, user_password, device_id)
        }


    override suspend fun loginUser(user_email: String, user_password: String, device_id: String): UserCRUD {
        return userAPI.LoginUser(user_email, user_password, device_id)
    }
}