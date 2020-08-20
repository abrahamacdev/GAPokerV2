package modelo

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

        fun generarCartaAleatoria(palo: Palo? = null, noRepetir: List<Carta> = ArrayList(0)): Carta{

            var sinRepetidas: List<Carta>
            if (palo != null){
                sinRepetidas = cartasPorPalo.get(palo)!!.filterNot { it in noRepetir }
            }
            else {
                sinRepetidas = todasCartas.filterNot { it in noRepetir }
            }

            return sinRepetidas.get((Math.random() * sinRepetidas.size).toInt())
        }

        fun generarBarajaAleatoria(palo: Palo? = null): List<Carta>{

            val cartas = mutableListOf<Carta>()
            IntRange(0,4).forEach { cartas.add(generarCartaAleatoria(palo,cartas)) }

            return cartas
        }

    }

    override fun toString(): String {
        return "$numero de ${palo.name.toUpperCase()}"
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
    Corazones
}