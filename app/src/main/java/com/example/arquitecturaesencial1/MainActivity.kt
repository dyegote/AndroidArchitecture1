package com.example.arquitecturaesencial1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import androidx.fragment.app.FragmentActivity

class MainActivity : FragmentActivity(), PrimerFragment.Listener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(findViewById<FrameLayout>(R.id.fragment_container) != null) {
            if(savedInstanceState != null) {
                return
            }
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, PrimerFragment()).commit()
        }

    }

    override fun onComensarPulzado(nombre: String) {
        inflateCronoFragment(nombre)
    }

    private fun inflateCronoFragment(nombre: String) {
        val cronoFragment = CronoFragment()
        cronoFragment.nombre = nombre
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, cronoFragment)
            .commit()

    }
}