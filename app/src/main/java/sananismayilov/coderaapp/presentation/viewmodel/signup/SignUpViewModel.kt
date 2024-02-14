package sananismayilov.coderaapp.presentation.viewmodel.signup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import sananismayilov.coderaapp.domain.model.UserCRUD
import sananismayilov.coderaapp.domain.use_case.SignUpUserUseCase.signupuserusecase
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val signupuserusecase: signupuserusecase) : ViewModel() {
    val signupstatus = MutableLiveData<UserCRUD>()

    fun signup(email : String,password : String,device_id : String){
        signupuserusecase.executesignup(email,password,device_id).onEach {
            signupstatus.value = it
        }.launchIn(viewModelScope)
    }

}