package sananismayilov.coderaapp.domain.use_case.LoginUserUseCase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import sananismayilov.coderaapp.domain.model.UserCRUD
import sananismayilov.coderaapp.domain.repository.UserRepository
import javax.inject.Inject

class loginuserusecase @Inject constructor(val repository: UserRepository) {

    fun executeloginUser(
        user_email: String,
        user_password: String,
        device_id: String
    ): Flow<UserCRUD> = flow {

        try {
            val response = repository.loginUser(user_email, user_password, device_id)
            emit(response)
        } catch (e: Exception) {

        }
    }
}