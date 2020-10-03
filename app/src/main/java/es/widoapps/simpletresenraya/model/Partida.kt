package es.widoapps.simpletresenraya.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import java.lang.Exception

class Partida {

    var tablero = Array(3) { arrayOfNulls<String>(3) }

    val jugador1: String? = "X"
    val jugador2: String? = "O"
    var turnoJugadorActual = jugador1
    var jugadorGanador: MutableLiveData<String?> = MutableLiveData()

    fun cambiarTurno() {

        turnoJugadorActual = if (turnoJugadorActual == jugador1) jugador2 else jugador1
    }

    fun reiniciarPartida() {

        turnoJugadorActual = jugador1
        tablero = Array(3) { kotlin.arrayOfNulls<kotlin.String>(3) }
    }

    fun comprobarFinPartida(jugador: String?, fil: Int, col: Int): Boolean {

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

    private fun comprobarTableroLleno(): Boolean {

        try {

            for (fil in tablero) {

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

    private fun comprobarHorizontal(jugador: String?, fil: Int): Boolean {

        try {

            for (i in tablero.indices) {

                if (tablero[fil][i] != jugador) {

                    return false
                }
            }

        } catch (e: Exception) {

            e.message?.let { Log.d("ERROR: ", it) }
        }

        return true
    }

    private fun comprobarVertical(jugador: String?, col: Int): Boolean {

        try {

            for (i in tablero.indices) {

                if (tablero[i][col] != jugador) {

                    return false
                }
            }

        } catch (e: Exception) {

            e.message?.let { Log.d("ERROR: ", it) }
        }

        return true
    }

    private fun comprobarDiagonalPrincipal(jugador: String?): Boolean {

        try {

            for (i in tablero.indices) {

                if (tablero[i][i] != jugador) {

                    return false
                }
            }

        } catch (e: Exception) {

            e.message?.let { Log.d("ERROR: ", it) }
        }

        return true
    }

    private fun comprobarDiagonalSecundaria(jugador: String?): Boolean {

        try {

            for (i in tablero.indices) {

                if (tablero[i][tablero.size - 1 - i] != jugador) {

                    return false
                }
            }

        } catch (e: Exception) {

            e.message?.let { Log.d("ERROR: ", it) }
        }

        return true
    }
}