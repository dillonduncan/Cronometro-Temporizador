package com.example.cronometro

import android.media.MediaPlayer
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.cronometro.databinding.ActivityTemporizador2Binding
import java.time.LocalDateTime
import java.time.LocalTime
import kotlin.math.min

class temporizador2 : AppCompatActivity() {
    lateinit var binding: ActivityTemporizador2Binding
    private var timer: CountDownTimer? = null
    lateinit var mpAlarma: MediaPlayer
    var hours: Long = 0
    var minutes: Long = 0
    var seconds: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTemporizador2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        mpAlarma = MediaPlayer.create(this, R.raw.alarma)
        binding.btnIniciar.setOnClickListener {
            if (binding.btnIniciar.text.equals("Iniciar")) {
                iniciarTemporizador()
                pausarAlarma()
            } else if (binding.btnIniciar.text.equals("Detener")) {
                binding.btnIniciar.text = "Iniciar"
                pausarAlarma()
                pausarTemporizador()
            }
        }
        binding.btnReiniciar.setOnClickListener {
            reiniciarTemporizador()
        }
    }

    fun pausarAlarma() {
        mpAlarma.stop()
    }

    fun sonarAlarma() {
        mpAlarma.start()
    }

    fun iniciarTemporizador() {
        val milisegundos: Long =
            (binding.npHora.value * 3600000L) + (binding.npMinutos.value * 60000L) + (binding.npSegundos.value * 1000L)

        if (milisegundos > 0) {
            binding.btnIniciar.text = "Detener"
            binding.btnReiniciar.isEnabled = true
            timer = object : CountDownTimer(milisegundos, 1000) {
                override fun onTick(milisecons: Long) {
                    hours = milisecons / 3600000
                    minutes = (milisecons % 3600000) / 60000
                    seconds = (milisecons % 60000) / 1000
                    binding.npHora.value = hours.toInt()
                    binding.npMinutos.value = minutes.toInt()
                    binding.npSegundos.value = seconds.toInt()
                }

                override fun onFinish() {
                    pausarTemporizador()
                    sonarAlarma()
                    binding.btnReiniciar.isEnabled = false
                }
            }.start()
        }
    }

    fun pausarTemporizador() {
        timer!!.cancel()
    }

    fun reiniciarTemporizador() {
        binding.npHora.value = 0
        binding.npMinutos.value = 0
        binding.npSegundos.value = 0
        timer?.cancel()
        hours = 0
        minutes = 0
        seconds = 0
        binding.btnIniciar.text = "Iniciar"
        binding.btnReiniciar.isEnabled = false
    }
}