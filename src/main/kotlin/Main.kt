fun main() {

    val tamanio_poblacion = 10

    var encontrado = false
    var generacion = 1
    var poblacion = mutableListOf<Individuo>()

    for (i in 0 until tamanio_poblacion){
        val genoma = Individuo.crear_genoma_aleatorio()
        poblacion.add(Individuo(genoma))
    }

    while (!encontrado){

        poblacion.sortBy { it.aptitud }

        if (poblacion[0].aptitud <= 0){
            encontrado = true
            break
        }

        val nuevaGeneracion = mutableListOf<Individuo>()

        val numElite = tamanio_poblacion * 0.1
        val elite = poblacion.take(numElite.toInt())
        nuevaGeneracion.addAll(elite)

        val numHijos = tamanio_poblacion * 0.9
        val numPadres = tamanio_poblacion * 0.5
        val padres = poblacion.take(numPadres.toInt())
        for (i in 0 until numHijos.toInt()){
            val padre1 = padres.get((Math.random() * padres.size).toInt())
            val padre2 = padres.get((Math.random() * padres.size).toInt())

            val hijo = padre1.cruzar(padre2)
            nuevaGeneracion.add(hijo)
        }

        poblacion = nuevaGeneracion

        println("Generacion $generacion\t Cadena: ${poblacion[0].cromosoma}\t Aptitud: ${poblacion[0].aptitud}")
        println("Cadena del Peor Individuo ${poblacion[poblacion.size-1].cromosoma}\t Aptitud: ${poblacion[poblacion.size-1].aptitud}")
        println("----------------------")
        generacion++
    }

    println("Generacion $generacion\t Cadena: ${poblacion[0].cromosoma}\t Aptitud: ${poblacion[0].aptitud}")
    println("Cadena del Peor Individuo ${poblacion[poblacion.size-1].cromosoma}\t Aptitud: ${poblacion[poblacion.size-1].aptitud}")
}