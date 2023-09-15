package com.example.arquitecturaesencial1

import android.os.Handler
import android.os.Looper
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Cronometro (
    var minutos: Int,
    var segundos: Int
        ) : Parcelable {

    private var cronometroHandler: Handler = Handler(Looper.getMainLooper())
    private var onCronometroTick: ((Int, Int) -> Unit)? = null

    fun setOnCronometroTick(listener: (Int, Int) -> Unit) {
        onCronometroTick = listener
    }

    private val actualizarTarea = object : Runnable {
        override fun run() {
            actualizarCronometro()
            cronometroHandler.postDelayed(this, 1000)
        }

    }

    private fun actualizarCronometro() {
        segundos++
        if(segundos > 59) {
            minutos ++
            segundos = 0
        }
        onCronometroTick?.let {
            it(minutos, segundos)
        }
    }

    fun pausar() {
        cronometroHandler.removeCallbacks(actualizarTarea)
    }

    fun comenzar() {
        cronometroHandler.post(actualizarTarea)
    }
}