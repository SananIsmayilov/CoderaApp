package sananismayilov.coderaapp.domain.use_case.SignUpUserUseCase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import sananismayilov.coderaapp.domain.model.UserCRUD
import sananismayilov.coderaapp.domain.repository.UserRepository
import javax.inject.Inject

class signupuserusecase @Inject constructor(val repository: UserRepository) {

    fun executesignup(
        user_email: String,
        user_password: String,
        device_id: String
    ): Flow<UserCRUD> = flow {

        try {
            val response = repository.SignUpUser(user_email, user_password, device_id)
            emit(response)
        } catch (e: Exception) {

        }
    }
}