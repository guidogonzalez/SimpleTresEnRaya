package es.widoapps.simpletresenraya.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.inflate
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import es.widoapps.simpletresenraya.R
import es.widoapps.simpletresenraya.databinding.ActivityMainBinding
import es.widoapps.simpletresenraya.viewmodel.PartidaViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var partidaViewModel: PartidaViewModel
    private lateinit var dataBinding: ActivityMainBinding
    private lateinit var ganadorPartida: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        iniciarDataBinding()
    }

    fun iniciarDataBinding() {
        dataBinding = inflate(layoutInflater, R.layout.activity_main, null, false)
        setContentView(dataBinding.root)
        partidaViewModel = ViewModelProvider(this).get(PartidaViewModel::class.java)
        partidaViewModel.iniciarPartida()

        dataBinding.viewModel = partidaViewModel
    }

    private fun observarViewModel() {

        partidaViewModel.obtenerGanador().observe(this, Observer { ganador ->

            ganador?.let {

                ganadorPartida =
                    if (ganador == null || ganador.isEmpty()) "SIN GANADOR" else ganador

                // Crear el Dialog
            }
        })
    }
}