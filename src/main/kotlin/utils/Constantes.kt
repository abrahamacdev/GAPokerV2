package utils

import modelo.Carta
import modelo.Palo

object Constantes {

    // El objetivo sera alcanzar la escalera real con un determinado palo
    val OBJETIVO = listOf(
        10,
        11,
        12,
        13,
        14
    )

    // Los posibles genes seran las cartas del palo corazon, desde el 2 hasta el As(14)
    val GENES = Carta.todasCartas

}