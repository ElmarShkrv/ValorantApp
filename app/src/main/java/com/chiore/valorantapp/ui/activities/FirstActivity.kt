package com.chiore.valorantapp.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.chiore.valorantapp.databinding.ActivityFirstBinding
import kotlinx.coroutines.delay

class FirstActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFirstBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        lifecycleScope.launchWhenCreated {
            delay(1_200)
            val intent = Intent(
                this@FirstActivity,
                MainActivity::class.java
            )
            startActivity(intent)
            finish()
        }
    }
}