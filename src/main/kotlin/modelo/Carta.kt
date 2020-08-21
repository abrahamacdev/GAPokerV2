package modelo

import com.github.ajalt.mordant.AnsiCode
import com.github.ajalt.mordant.TermColors
import java.util.HashSet

class Carta(val numero: Int, val palo: Palo) {

    companion object {

        val cartasPorPalo by lazy {
            val todas = mutableMapOf<Palo, List<Carta>>()

            for (palo in Palo.values()){
                todas.put(palo, IntRange(2,14).map { Carta(it, palo) }.toList())
            }

            todas
        }

        val todasCartas = cartasPorPalo.values.flatten().toHashSet()

        fun generarCartaAleatoria(palo: Palo? = null, noRepetir: Set<Carta> = HashSet(0)): Carta{

            var sinRepetidas: List<Carta>
            if (palo != null){
                sinRepetidas = cartasPorPalo.get(palo)!!.filterNot { it in noRepetir }
            }
            else {
                sinRepetidas = todasCartas.filterNot { it in noRepetir }
            }

            return sinRepetidas[(Math.random() * sinRepetidas.size).toInt()]
        }

        fun generarBarajaAleatoria(palo: Palo? = null): List<Carta>{

            val cartas = hashSetOf<Carta>()
            IntRange(0,4).forEach { cartas.add(generarCartaAleatoria(palo,cartas)) }

            return cartas.toList()
        }
    }

    override fun toString(): String {
        return "$numero de ${palo.toString()}"
    }

    override fun equals(other: Any?): Boolean {
        if (other == null) return false
        if (!(other is Carta)) return false
        val otro = other as Carta
        return numero == otro.numero && palo == otro.palo
    }
}

enum class Palo {
    Picas,
    Rombos,
    Treboles,
    Corazones;

    companion object {
        private val color = TermColors()
    }

    override fun toString(): String {

        var estilos = color.inverse
        var imagen: String = ""

        when {
            this.equals(Picas) -> {
                imagen = "\u2660"
            }

            this.equals(Treboles) -> {
                imagen = "\u2663"
            }

            this.equals(Rombos) -> {
                estilos = (color.red on color.gray)
                imagen = "\u2666"
            }

            this.equals(Corazones) -> {
                estilos = (color.red on color.gray)
                imagen = "\u2665"
            }
        }

        return estilos(imagen)
    }
}