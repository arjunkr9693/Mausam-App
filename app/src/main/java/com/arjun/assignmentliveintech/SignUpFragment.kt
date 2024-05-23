package com.arjun.assignmentliveintech

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.arjun.assignmentliveintech.databinding.FragmentSignInBinding
import com.arjun.assignmentliveintech.databinding.FragmentSignUpBinding
import com.google.firebase.auth.FirebaseAuth

class SignUpFragment : Fragment() {
    private lateinit var auth: FirebaseAuth
    private var _binding: FragmentSignUpBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding =  FragmentSignUpBinding.inflate(inflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()

        _binding?.signUpButton?.setOnClickListener {
            val userEmail = _binding?.userEmail?.text.toString()
            val userPassword = _binding?.userPassword?.text.toString()
            val userRePassword = _binding?.userRePassword?.text.toString()

            if (userEmail.isEmpty() || userPassword.isEmpty() || userRePassword.isEmpty()){
                Toast.makeText(requireContext(), "Fill all the block", Toast.LENGTH_SHORT).show()
            }
            else if(userPassword != userRePassword){
                Toast.makeText(requireContext(), "Both password isn't matching", Toast.LENGTH_SHORT).show()
            }
            else{
                auth.createUserWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener{
                    if(it.isSuccessful) {
                        startActivity(Intent(requireContext(), MainActivity::class.java))
                        requireActivity().finish()
                    }
                    else {
                        Toast.makeText(requireContext(), "Failed: ${it.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}