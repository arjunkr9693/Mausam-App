package com.arjun.assignmentliveintech

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.arjun.assignmentliveintech.databinding.FragmentSignInBinding
import com.google.firebase.auth.FirebaseAuth


class SignInFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private var _binding: FragmentSignInBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        _binding =  FragmentSignInBinding.inflate(inflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser
        if(user != null){
            startActivity(Intent(requireContext(), MainActivity::class.java))
            requireActivity().finish()
        }

        _binding?.signInButton?.setOnClickListener {
            val userEmail = _binding?.signInUserEmail?.text.toString()
            val userPassword = _binding?.signInUserPassword?.text.toString()

            if (userEmail.isEmpty() || userPassword.isEmpty()){
                Toast.makeText(requireContext(), "Fill all the block", Toast.LENGTH_SHORT).show()
            }
            else{
                auth.signInWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener{
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