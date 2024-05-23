package com.arjun.assignmentliveintech

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.arjun.assignmentliveintech.databinding.ActivityLogInBinding

class LogInActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLogInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        replaceWithFragment(SignInFragment())

        binding.signInToggle.setOnClickListener {
            it.setBackgroundResource(R.drawable.clicked_background)
            binding.signUpToggle.setBackgroundResource(R.drawable.normal_background)
            replaceWithFragment(SignInFragment())
        }
        binding.signUpToggle.setOnClickListener {
            it.setBackgroundResource(R.drawable.clicked_background)
            binding.signInToggle.setBackgroundResource(R.drawable.normal_background)
            replaceWithFragment(SignUpFragment())
        }
    }

    private fun replaceWithFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.commit()
    }
}
