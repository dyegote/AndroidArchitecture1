package com.example.arquitecturaesencial1

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import com.example.arquitecturaesencial1.viewmodels.ShareViewModel

class CronoFragment : Fragment() {

    private lateinit var cronometroMinutosView : TextView
    private lateinit var cronometroSegundosView : TextView
    private var cronometro = Cronometro(0,0)

    private val viewModel: ShareViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_crono, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val nombreView = view.findViewById<TextView>(R.id.nombre)
        val boton = view.findViewById<Button>(R.id.botonParar)
        cronometroMinutosView = view.findViewById(R.id.cronometroMinutos)
        cronometroSegundosView = view.findViewById(R.id.cronometroSegundos)
        boton.setOnClickListener {
            cronometro.pausar()
        }

        savedInstanceState?.let { estado ->
            cronometro = estado.getParcelable(ESTADO_CRONOMETRO) ?: Cronometro(0,0)
            cronometroMinutosView.text = cronometro.minutos.toCronoFormat()
            cronometroSegundosView.text = cronometro.minutos.toCronoFormat()
        }

        cronometro.setOnCronometroTick { minutos, segundos ->
            actualizarVista(minutos, segundos)
        }
        nombreView.text = viewModel.nombre
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run {
            putParcelable(ESTADO_CRONOMETRO, cronometro)
        }
        super.onSaveInstanceState(outState)
    }

    override fun onResume() {
        super.onResume()
        cronometro.comenzar()
    }

    override fun onPause() {
        super.onPause()
        cronometro.pausar()
    }


    private fun actualizarVista(minutos: Int, segundos: Int) {
        cronometroMinutosView.text = minutos.toCronoFormat()
        cronometroSegundosView.text = segundos.toCronoFormat()
    }

    companion object {
        const val ESTADO_NOMBRE = "nombre"
        const val ESTADO_CRONOMETRO = "cronometro"
    }
}

private fun Int.toCronoFormat(): String =
    if (this / 10 == 0) {
        "0" + toString()
    } else {
        toString()
    }
