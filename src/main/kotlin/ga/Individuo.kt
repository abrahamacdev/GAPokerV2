package ga

import modelo.Carta
import utils.Constantes

class Individuo(val cromosoma: List<Carta>) {

    // TODO MOdificar
    val aptitud = calcularAptitudExperimental()

    companion object {

        fun crear_gen_aleatorio(noRepetir: Set<Carta> = mutableSetOf()): Carta {

            // Nos quedamos con todos los genes que no coincidan con los del set "noRepetir"
            val noRepetidas = Constantes.GENES.filterNot { noRepetir.contains(it) }

            val pos = Math.random() * noRepetidas.size
            return noRepetidas[pos.toInt()]
        }

        fun crear_genoma_aleatorio(): List<Carta> {

            var final = mutableListOf<Carta>()

            Constantes.OBJETIVO.forEach {
                final.add(crear_gen_aleatorio(final.toHashSet()))
            }

            return final.toList()
        }
    }

    fun cruzar(otro: Individuo): Individuo {

        var cromosoma_hijo = hashSetOf<Carta>()
        for (i in 0 until cromosoma.size){

            val rnd = Math.random()
            var nuevaCarta: Carta? = null

            if (rnd < 0.45){

                nuevaCarta = cromosoma.get(i)
            } else if (rnd < 0.9){

                nuevaCarta = otro.cromosoma.get(i)
            } else {

                nuevaCarta = crear_gen_aleatorio(cromosoma_hijo)
            }

            // Comprobamos si se va ha añadir una carta repetida y la cambiamos es caso de que sea repetida
            if (cromosoma_hijo.contains(nuevaCarta)){
                nuevaCarta = crear_gen_aleatorio(cromosoma_hijo)
            }

            cromosoma_hijo.add(nuevaCarta)
        }

        return Individuo(cromosoma_hijo.toList())
    }

    /**
     * Esta funcion comprueba que el listado de numeros del individuo coincida exactamente con el deseado,
     * en este caso [10,11,12,13,14]
     */
    fun calcularAptitud(): Int {

        var fitness = 0

        for (i  in 0 until cromosoma.size){

            var letra = cromosoma[i]

            if (!letra.equals(Constantes.OBJETIVO[i])){
                fitness++
            }
        }
        return fitness
    }

    /**
     * Esta funcion comprueba que el listado de numeros del individuo contenga los numeros de la combinacion
     * deseada, en nuestro caso [10,11,12,13,14]
     */
    fun calcularAptitudExperimental(): Int {

        var fitness = 0

        for (i  in 0 until cromosoma.size){

            var num = cromosoma[i]

            if(!(num in Constantes.OBJETIVO)){
                fitness++
            }
        }

        val sinRepetidos = cromosoma.toHashSet()
        fitness += 5 - sinRepetidos.size

        return fitness
    }
}