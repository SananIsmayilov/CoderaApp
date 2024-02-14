package sananismayilov.coderaapp.presentation.ui.signup

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.provider.Settings.Secure
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import sananismayilov.coderaapp.R
import sananismayilov.coderaapp.databinding.FragmentSignUpBinding
import sananismayilov.coderaapp.presentation.viewmodel.signup.SignUpViewModel

@AndroidEntryPoint
class SignUpFragment : Fragment() {
    private lateinit var binding: FragmentSignUpBinding
    private lateinit var viewmodel: SignUpViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        viewmodel = ViewModelProvider(this).get(SignUpViewModel::class.java)

        binding.submitbutton.setOnClickListener {
            signUp()
        }
        binding.logintextview.setOnClickListener {
            Navigation.findNavController(requireView()).navigate(R.id.action_signUpFragment_to_loginFragment)
        }

        observeUser()

        return binding.root
    }

    private fun observeUser() {
        viewmodel.signupstatus.observe(viewLifecycleOwner, Observer {
            if (it.success == 1) {
                Snackbar.make(requireView(), "${it.message}", Snackbar.LENGTH_SHORT)
                    .show()
                Navigation.findNavController(requireView())
                    .navigate(R.id.action_signUpFragment_to_loginFragment)
            } else {
                Snackbar.make(requireView(), "${it.message}", Snackbar.LENGTH_SHORT)
                    .show()
            }
        })
    }

    private fun signUp() {
        val email = binding.useremail.text.toString()
        val password = binding.userpassword.text.toString()
        val device_id = getDevice_id(requireContext())

        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
            device_id?.let { viewmodel.signup(email, password, it) }
        }
    }

    @SuppressLint("HardwareIds")
    private fun getDevice_id(context: Context): String? {
        return Secure.getString(context.contentResolver, Secure.ANDROID_ID)
    }

}