package com.example.arquitecturaesencial1.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.arquitecturaesencial1.Cronometro

class CronoViewModel : ViewModel() {

    private var cronometro = Cronometro(0,0)

    private val _textoMinutos: MutableLiveData<String> = MutableLiveData()
    private val _textoSegundos: MutableLiveData<String> = MutableLiveData()

    val textoMinutos: LiveData<String>
        get() = _textoMinutos

    val textoSegundos: LiveData<String>
        get() = _textoSegundos

    init {
        cronometro.setOnCronometroTick { minutos, segundos ->
            _textoMinutos.value = minutos.toCronoFormat()
            _textoSegundos.value = segundos.toCronoFormat()
        }
    }

    fun comenzarCronometro() {
        cronometro.comenzar()
    }

    fun pausarCronometro() {
        cronometro.pausar()
    }


}

private fun Int.toCronoFormat(): String =
    if (this / 10 == 0) {
        "0" + toString()
    } else {
        toString()
    }