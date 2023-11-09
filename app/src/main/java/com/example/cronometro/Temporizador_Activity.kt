package com.example.cronometro

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import com.example.cronometro.databinding.ActivityTemporizadorBinding

class Temporizador_Activity : AppCompatActivity() {
    lateinit var binding: ActivityTemporizadorBinding
    lateinit var mpAlarma: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTemporizadorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Temporizador()
        alarma()
    }

    fun reiniciar() {
        binding.npHora.value = 0
        binding.npSegundos.value = 0
        binding.npMinutos.value = 0

    }

    fun alarma() {
        mpAlarma = MediaPlayer.create(this, R.raw.alarma)
    }
    fun sonarAlarma() {
        mpAlarma.start()
    }

    fun Temporizador() {
        try {
            var secods = 0
            var minutos = 0
            var horas = 0
            var tiempo = object : CountDownTimer(1000, 1000) {
                override fun onTick(p0: Long) {
                    secods = binding.npSegundos.value
                    minutos = binding.npMinutos.value
                    horas = binding.npHora.value
                    Toast.makeText(this@Temporizador_Activity, "$horas", Toast.LENGTH_SHORT).show()
                    if (secods == 58 && minutos > 58 && horas > 0) {
                        horas--
                    }
                    else if (horas>0 && minutos==0 && secods==0){
                        horas--
                    }
                    if (secods > 58) {
                        minutos--
                        binding.npMinutos.value = minutos
                    }
                    if (horas < 0) {
                        horas = 0
                    }
                    secods--
                    binding.npSegundos.value = secods
                    binding.npHora.value = horas


                }

                override fun onFinish() {
                    if (binding.npSegundos.value == 0 && binding.npMinutos.value == 0 && binding.npHora.value == 0) {
                        sonarAlarma()
                        binding.btnIniciar.text = "Iniciar"
                        binding.npSegundos.isEnabled = true
                        binding.npMinutos.isEnabled = true
                        binding.npHora.isEnabled = true
                        this.cancel()
                    } else if (binding.npSegundos.value > 0 || binding.npMinutos.value > 0) {
                        this.start()
                        binding.npSegundos.isEnabled = false
                        binding.npMinutos.isEnabled = false
                        binding.npHora.isEnabled = false
                    }
                }
            }
//        binding.btnIniciar.setOnClickListener { tiempo.start() }

            binding.btnIniciar.setOnClickListener {
                // mpAlarma.start()
                if (binding.btnIniciar.text.equals("Iniciar") && binding.npHora.value > 0 || binding.npMinutos.value > 0 || binding.npSegundos.value > 0) {
                    tiempo.start()
                    mpAlarma.stop()
                    binding.btnIniciar.text = "Detener"
                    binding.btnIniciar.isEnabled = true
                } else if (binding.btnIniciar.text.equals("Detener")) {
                    binding.btnIniciar.text = "Iniciar"
                    binding.btnIniciar.isEnabled = true
                    tiempo.cancel()
                }

            }
            binding.btnReiniciar.setOnClickListener {
                reiniciar()
                tiempo.cancel()
                secods = 0
                mpAlarma.stop()
                minutos = 0
                binding.btnIniciar.text = "Iniciar"
                binding.npSegundos.isEnabled = true
                binding.npMinutos.isEnabled = true
                binding.npHora.isEnabled = true
            }
        } catch (e: Exception) {
            println(e.message)
        }

    }
}