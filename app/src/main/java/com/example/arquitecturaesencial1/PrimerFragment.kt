package com.example.arquitecturaesencial1

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.activityViewModels
import com.example.arquitecturaesencial1.viewmodels.ShareViewModel
import java.lang.IllegalStateException

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class PrimerFragment : Fragment() {

    private lateinit var listener: Listener
    private val viewModel: ShareViewModel by activityViewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = when {
            context is Listener -> context
            parentFragment is Listener -> parentFragment as Listener
            else -> throw  IllegalStateException("Parent must implement Listener");
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_primer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val label = view.findViewById<EditText>(R.id.escribirNombre)
        val boton = view.findViewById<Button>(R.id.botonComenzar)
        boton.setOnClickListener {
            viewModel.nombre = label.text.toString()
            listener.onComensarPulzado()
        }
    }

    interface Listener {
        fun onComensarPulzado()
    }
}