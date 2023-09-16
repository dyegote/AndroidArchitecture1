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
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.arquitecturaesencial1.viewmodels.CronoViewModel
import com.example.arquitecturaesencial1.viewmodels.ShareViewModel

class CronoFragment : Fragment() {

    private lateinit var cronometroMinutosView : TextView
    private lateinit var cronometroSegundosView : TextView


    private val viewModel: ShareViewModel by activityViewModels()
    private val cronoViewModel: CronoViewModel by viewModels()

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
            cronoViewModel.pausarCronometro()
        }


        nombreView.text = viewModel.nombre

        val observadorMinutos = Observer<String> { minutos ->
            cronometroMinutosView.text = minutos
        }
        cronoViewModel.textoMinutos.observe(viewLifecycleOwner, observadorMinutos)

        val observadorSegundos = Observer<String> { minutos ->
            cronometroMinutosView.text = minutos
        }
        cronoViewModel.textoSegundos.observe(viewLifecycleOwner, observadorSegundos)
    }

    override fun onResume() {
        super.onResume()
        cronoViewModel.comenzarCronometro()
    }

    override fun onPause() {
        super.onPause()
        cronoViewModel.pausarCronometro()
    }

    companion object {
        const val ESTADO_NOMBRE = "nombre"
        const val ESTADO_CRONOMETRO = "cronometro"
    }
}


