package es.widoapps.simpletresenraya.viewmodel

import android.app.Application
import androidx.databinding.ObservableArrayMap
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import es.widoapps.simpletresenraya.model.Partida

class PartidaViewModel(application: Application) : AndroidViewModel(application) {

    lateinit var partida: Partida
    lateinit var celdas: ObservableArrayMap<String, String>

    fun iniciarPartida() {

        partida = Partida()
        celdas = ObservableArrayMap()
    }

    fun mover(fil: Int, col: Int) {

        // Comprobamos que está vaciío donde queremos mover
        if (partida.tablero[fil][col] == null) {

            // Aplicamos el valor del jugador actual a la celda
            partida.tablero[fil][col] = partida.turnoJugadorActual

            // Para almacenar la key en el ArrayMap
            val movimiento = fil.toString() + col.toString()

            celdas.put(movimiento, partida.turnoJugadorActual)

            // Una vez que el jugador mueve comprobamos si es fin de partida
            if (partida.comprobarFinPartida(partida?.turnoJugadorActual, fil, col)) {

                partida.reiniciarPartida()

            } else {

                partida.cambiarTurno()
            }
        }
    }

    fun obtenerGanador(): LiveData<String?> = partida.jugadorGanador

}