package com.example.cronometro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cronometro.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnCronometro.setOnClickListener { startActivity(Intent(this,Cronometro_Activity::class.java)) }
        binding.btnTemporizador.setOnClickListener { startActivity(Intent(this,Temporizador_Activity::class.java)) }
    }
}