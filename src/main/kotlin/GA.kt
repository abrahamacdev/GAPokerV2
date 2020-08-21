import ga.Individuo

class GA(val tamanioPoblacion: Int = 100, val tasaElitismo: Double = 0.1, val tasaSeleccion: Double = 0.5, val probMutacion: Double = 0.05) {

    private var poblacion = IntRange(1,tamanioPoblacion).map { Individuo() }.toMutableList()

    var loggear = false

    /**
     * Comenzamos a evolucionar los individuos de nuestra poblacion hasta cumplir la [condicion]
     *
     */
    fun evolucionar(condicion: (poblacionOrdenada: MutableList<Individuo>) -> Boolean, inicio: (poblacionOrdenada: MutableList<Individuo>) -> Unit = {}, final: (poblacion: MutableList<Individuo>, rondas: Int) -> Unit = {e,y -> }){

        var encontrado = false
        var generacion = 0

        // Evolucionaremos a la poblacion hasta que se cumpla la condicion
        while (!encontrado){

            // Ordenamos a los individuos por su aptitud en orden ascendente
            poblacion.sortByDescending { it.aptitud }// Cuando usemos la escala 0-100 (maximizar el error)
            //poblacion.sortBy { it.aptitud } // Cuando usemos la escala 0-23   (minimizar el error)

            // Lanzaremos el evento de inicio
            if (generacion == 0){
                inicio(poblacion)
            }

            // Si se cumple la condicion, paramos de iterar
            if (condicion(poblacion)){
                encontrado = true
                break
            }

            val nuevaGeneracion = mutableListOf<Individuo>()

            // Añadimos a la nueva generacion la elite
            val numElite = tamanioPoblacion * tasaElitismo
            val elite = poblacion.take(numElite.toInt())
            nuevaGeneracion.addAll(elite)

            // Calculamos el numero de hijos necesarios para cubrir la siguiente generacion (ademas de la elite)
            // y el numero de padres que participaran en la fase de procreacion
            val numHijos = tamanioPoblacion * (1.0 - tasaElitismo)
            val numPadres = tamanioPoblacion * tasaSeleccion

            // Seleccionamos los mejores [numPadres] padres de la poblacion actual y creamos los nuevos hijos
            val padres = poblacion.take(numPadres.toInt())
            for (i in 0 until numHijos.toInt()){
                val padre1 = padres.get((Math.random() * padres.size).toInt())
                val padre2 = padres.get((Math.random() * padres.size).toInt())

                val hijo = padre1.cruzar(padre2)
                nuevaGeneracion.add(hijo)
            }

            // Guardamos la nueva generacion
            poblacion = nuevaGeneracion

            // Mostramos el estado actual de la poblacion
            if (loggear){
                val promedio = poblacion.sumByDouble { it.aptitud.toDouble() } / poblacion.size.toDouble()
                println("Generacion $generacion\t Cartas del mejor: ${poblacion[0].cartas}\t Aptitud del mejor: ${poblacion[0].aptitud}\t Aptitud promedia: ${promedio}")
                println("----------------------")
            }

            generacion++
        }

        final(poblacion, generacion)
    }
}