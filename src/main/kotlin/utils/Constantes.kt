package utils

import modelo.Carta
import modelo.Palo

object Constantes {

    // El objetivo sera alcanzar la escalera real con el palo "corazones"
    val OBJETIVO = listOf(
        Carta(10,Palo.Corazones),
        Carta(11,Palo.Corazones),
        Carta(12,Palo.Corazones),
        Carta(13,Palo.Corazones),
        Carta(14,Palo.Corazones))

    // Los posibles genes seran las cartas del palo corazon, desde el 2 hasta el As(14)
    val GENES = Carta.cartasPorPalo.get(Palo.Corazones)!!

}