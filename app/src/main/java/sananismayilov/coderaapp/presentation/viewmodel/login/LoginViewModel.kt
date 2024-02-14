package sananismayilov.coderaapp.presentation.viewmodel.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import sananismayilov.coderaapp.domain.model.UserCRUD
import sananismayilov.coderaapp.domain.use_case.LoginUserUseCase.loginuserusecase
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginuserusecase: loginuserusecase) :
    ViewModel() {

    val loginstatus = MutableLiveData<UserCRUD>()
    var job: Job? = null

    fun loginUser(user_email: String, user_password: String, device_id: String) {
        job = loginuserusecase.executeloginUser(user_email, user_password, device_id)
            .onEach {
                loginstatus.value = it
            }.launchIn(viewModelScope)
    }


    override fun onCleared() {
        job?.cancel()
        super.onCleared()
    }
}