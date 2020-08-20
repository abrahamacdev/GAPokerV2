class Individuo(val cromosoma: List<Int>) {

    // TODO MOdificar
    val aptitud = calcularAptitudExperimental()

    companion object {

        fun crear_gen_aleatorio(): Int {
            val pos = Math.random() * Constantes.GENES.size
            return Constantes.GENES[pos.toInt()]
        }

        fun crear_genoma_aleatorio(): List<Int> {

            var final = mutableListOf<Int>()
            Constantes.OBJETIVO.forEach {
                final.add(crear_gen_aleatorio())
            }

            return final.toList()
        }
    }

    fun cruzar(otro: Individuo): Individuo {

        var cromosoma_hijo = mutableListOf<Int>()
        for (i in 0 until cromosoma.size){

            val rnd = Math.random()

            if (rnd < 0.45){

                cromosoma_hijo.add(cromosoma.get(i))
            } else if (rnd < 0.9){

                cromosoma_hijo.add(otro.cromosoma.get(i))
            } else {

                cromosoma_hijo.add(crear_gen_aleatorio())
            }
        }

        return Individuo(cromosoma_hijo)
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