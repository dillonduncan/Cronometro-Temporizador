package com.example.cronometro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import com.example.cronometro.databinding.ActivityCronometroBinding

class Cronometro_Activity : AppCompatActivity() {
    lateinit var binding: ActivityCronometroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCronometroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Timer()
    }

    fun Timer() {
        var minutos:Long = 0
        var segundos:Long = 0
        var milisegundos:Long = 0
        var sec=0
        var timer = object : CountDownTimer(1000, 1) {
            override fun onTick(millisUntilFinished: Long) {
                milisegundos=millisUntilFinished/10
                sec++
                if (milisegundos > 98 && sec>1) {
                    segundos++
                    milisegundos = 0
                }
                if (segundos > 59) {
                    minutos++
                    segundos = 0
                }
                if (milisegundos < 10 && segundos < 10 && minutos < 10) {
                    binding.txtTiempo.text = "0$minutos : 0$segundos . 0$milisegundos"
                } else if (minutos < 10 && segundos > 9 && milisegundos < 10) {
                    binding.txtTiempo.text = "0$minutos : $segundos . 0$milisegundos"
                } else if (minutos >9 && segundos > 9 && milisegundos < 10) {
                    binding.txtTiempo.text = "0$minutos : 0$segundos . $milisegundos"
                } else if (segundos < 10 && minutos < 10) {
                    binding.txtTiempo.text = "0$minutos : 0$segundos . $milisegundos"
                } else if (segundos < 10) {
                    binding.txtTiempo.text = "$minutos : 0$segundos . $milisegundos"
                } else if (minutos < 10) {
                    binding.txtTiempo.text = "0$minutos : $segundos . $milisegundos"
                }
            }

            override fun onFinish() {
                this.start()
            }
        }
        binding.btnStarStop.setOnClickListener {
            if (binding.btnStarStop.text.equals("Iniciar")) {
                timer.start()
                binding.btnStarStop.text = "Detener"
                binding.btnRestart.isEnabled = true
            } else if (binding.btnStarStop.text.equals("Detener")) {
                timer.cancel()
                binding.btnStarStop.text = "Iniciar"
                binding.btnRestart.isEnabled = true
            }
        }
        binding.btnRestart.setOnClickListener {
            milisegundos = 0
            sec=0
            segundos = 0
            minutos = 0
            timer.cancel()
            binding.txtTiempo.text = "0$minutos : 0$segundos . 0$milisegundos"
            binding.btnStarStop.text = "Iniciar"
            binding.btnRestart.isEnabled = false
        }
    }
}