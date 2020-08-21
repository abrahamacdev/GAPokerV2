package ga

import jdk.jfr.Description
import jdk.jfr.Label
import modelo.Carta
import utils.Constantes
import utils.Utils

class Individuo(val cartasIniciales: List<Carta>? = null) {

    val cartas: List<Carta>
    val aptitud: Double

    init {
        // Por defecto crearemos un genoma aleatorio
        if (cartasIniciales == null){
            cartas = Carta.generarBarajaAleatoria()
        }

        else {
            cartas = cartasIniciales
        }

        aptitud = calcularAptitudInversa()
        //aptitud = calcularAptitudV3()
    }

    fun cruzar(otro: Individuo): Individuo {

        var cartasHijo = hashSetOf<Carta>()
        for (i in 0 until cartas.size){

            val rnd = Math.random()
            var nuevaCarta: Carta? = null

            when {
                rnd < 0.45 -> nuevaCarta = cartas.get(i)           // Carta de nuestra mano
                rnd < 0.90 -> nuevaCarta = otro.cartas.get(i)       // Carta de la mano del otro
                rnd <= 1.0 -> nuevaCarta = Carta.generarCartaAleatoria(noRepetir = cartasHijo)  // Carta aleatoria
            }

            // Comprobamos si se va ha añadir una carta repetida y la cambiamos en caso de que sea repetida
            if (cartasHijo.contains(nuevaCarta)){
                nuevaCarta = Carta.generarCartaAleatoria(noRepetir = cartasHijo)
            }

            // Añadimos la carta a la mano del hijo
            cartasHijo.add(nuevaCarta!!)
        }

        // Creamos el nuevo individuo con la mano recien creada
        return Individuo(cartasHijo.toList())
    }

    /**
     * Esta funcion comprueba que el listadoa de cartas del individuo contenga las 5 cartas que forman la escalera
     * real, siendo todas del mismo palo.
     * Usando esta funcion, los mejores individuos seran aquellos que tengan un valor lo mas cercano a 0 posible, en
     * cambio aquellos individuos que tengan un valor lo mas cercano posible a 23, seran los peores.
     * Los valores de salida perteneceran al rango [0-23].
     */
    fun calcularAptitudV3(): Double {

        var fitness = 0

        // Sumamos 1 al error por cada numero que nos falte para completar la escalera real
        for (i  in 0 until cartas.size){
            var num = cartas[i].numero

            if(!(num in Constantes.OBJETIVO)){
                fitness++
            }
        }

        // Sumamos 10 al error por cada carta que sea diferente del palo mayor
        val grupos = cartas.groupBy { it.palo }
        val cartasDelGrupoMayor = grupos.entries.maxBy { it.value.size }!!.value.size
        fitness += (5 - cartasDelGrupoMayor) * 10

        // Convertimos el error para que sea un numero del 0 al 23
        if (fitness >= 10){
            val base = fitness.toString()[0].toString().toInt() * 6
            val sumatorio = base + fitness.toString()[1].toString().toInt()

            fitness = sumatorio
        }

        return fitness.toDouble()
    }

    /**
     * Esta funcion utiliza el valor devuelto por la funcion [calcularAptitudV3] para convertirlo en uno que este dentro
     * del rango 0-100, donde 0 es la peor puntuacion y 100 la mejor puntuacion.
     */
    fun calcularAptitudInversa(): Double {

        // Obtenemos una puntuacion del 0(mejor) al 20(peor)
        val temp = calcularAptitudV3().toDouble()

        // Convertimos la puntuacion anterior para que este en la escala del 0(peor) al 100(mejor)
        val convertido = Utils.cambiarRango(temp,0.0,23.0,0.0, 100.0)

        return 100.0 - convertido
    }
}