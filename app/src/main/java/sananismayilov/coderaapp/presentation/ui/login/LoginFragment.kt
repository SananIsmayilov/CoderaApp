package sananismayilov.coderaapp.presentation.ui.login

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.provider.Settings.Secure
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.Navigation
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import sananismayilov.coderaapp.R
import sananismayilov.coderaapp.databinding.FragmentLoginBinding
import sananismayilov.coderaapp.presentation.viewmodel.login.LoginViewModel

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        binding.loginsubmitbutton.setOnClickListener {
            login(requireContext())
        }

        observeLogin()
        return binding.root
    }

    private fun login(context: Context) {
        val email = binding.loginuseremail.text.toString()
        val password = binding.loginuserpassword.text.toString()
        val device_id = getdevice_id(context)

        viewModel.loginUser(email, password, device_id)
    }

    @SuppressLint("HardwareIds")
    private fun getdevice_id(context: Context): String {
        return Secure.getString(context.contentResolver, Secure.ANDROID_ID)
    }

    private fun observeLogin() {
        viewModel.loginstatus.observe(viewLifecycleOwner, Observer {
            if (it.success == 1) {
                Snackbar.make(requireView(), "Daxil ola bilərsiniz.", Snackbar.LENGTH_SHORT)
                    .show()
                Navigation.findNavController(requireView()).navigate(R.id.action_loginFragment_to_mainPageFragment)
            } else {
                Snackbar.make(
                    requireView(),
                    "Bu hesabla başqa cihazda giriş edilmişdir.",
                    Snackbar.LENGTH_SHORT
                )
                    .show()
            }
        })
    }


}