package com.example.cronometro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cronometro.databinding.ActivityCronometroBinding

class Cronometro_Activity : AppCompatActivity() {
    lateinit var binding: ActivityCronometroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityCronometroBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}