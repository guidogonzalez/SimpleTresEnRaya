package es.widoapps.simpletresenraya.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import java.lang.Exception

class Partida {

    var partida = Array(3) { arrayOfNulls<String>(3) }

    val jugador1: String? = "X"
    val jugador2: String? = "O"
    var turnoJugadorActual: String? = jugador1;
    var jugadorGanador: MutableLiveData<String?> = MutableLiveData()

    fun cambiarTurno() {

        turnoJugadorActual = if (turnoJugadorActual == jugador1) jugador2 else jugador1
    }

    fun comprobarFinPartida(jugador: String, fil: Int, col: Int): Boolean {

        if (comprobarHorizontal(jugador, fil) || comprobarVertical(jugador, col) ||
            comprobarDiagonalPrincipal(jugador) || comprobarDiagonalSecundaria(jugador)) {

            jugadorGanador.value = turnoJugadorActual

            return true
        }

        if (comprobarTableroLleno()) {

            jugadorGanador.value = null

            return true
        }

        return false
    }

    fun comprobarTableroLleno(): Boolean {

        try {

            for (fil in partida) {

                for (col in fil) {

                    if (col == null) {

                        return false
                    }
                }
            }

        } catch (e: Exception) {

            e.message?.let { Log.d("ERROR: ", it) }
        }

        return true
    }

    fun comprobarHorizontal(jugador: String, fil: Int): Boolean {

        try {

            for (i in partida.indices) {

                if (partida[fil][i] != jugador) {

                    return false
                }
            }

        } catch (e: Exception) {

            e.message?.let { Log.d("ERROR: ", it) }
        }

        return true
    }

    fun comprobarVertical(jugador: String, col: Int): Boolean {

        try {

            for (i in partida.indices) {

                if (partida[i][col] != jugador) {

                    return false
                }
            }

        } catch (e: Exception) {

            e.message?.let { Log.d("ERROR: ", it) }
        }

        return true
    }

    fun comprobarDiagonalPrincipal(jugador: String): Boolean {

        try {

            for (i in partida.indices) {

                if (partida[i][i] != jugador) {

                    return false
                }
            }

        } catch (e: Exception) {

            e.message?.let { Log.d("ERROR: ", it) }
        }

        return true
    }

    fun comprobarDiagonalSecundaria(jugador: String): Boolean {

        try {

            for (i in partida.indices) {

                if (partida[i][partida.size - 1] != jugador) {

                    return false
                }
            }

        } catch (e: Exception) {

            e.message?.let { Log.d("ERROR: ", it) }
        }

        return true
    }
}