import com.github.ajalt.mordant.TermColors
import ga.GA

fun main() {

    // Revisar 3 cosas:
    // 1º Condicion para que paremos de iterar
    // 2º Como ordenamos la lista (ga.GA.evolucionar)
    // 3º Funcion de aptitud utilizada


    val ga = GA(tamanioPoblacion = 10)
    //ga.loggear = true
    ga.evolucionar(inicio = {poblacionOrdenada ->

        val promedio = poblacionOrdenada.sumByDouble { it.aptitud.toDouble() } / poblacionOrdenada.size.toDouble()
        val termColors = TermColors()
        val titulo = with(termColors){
            (bold + blue)
        }
        val estrellas = with(termColors){
            (italic + green)
        }

        println("${estrellas("****************************************")}")
        println("\t\t${titulo("Resumen Inicial")}")
        println("Cartas del mejor: ${poblacionOrdenada[0].cartas}")
        println("Cartas del peor: ${poblacionOrdenada[poblacionOrdenada.size-1].cartas}")
        println("Aptitud del mejor: ${poblacionOrdenada[0].aptitud}")
        println("Aptitud del peor: ${poblacionOrdenada[poblacionOrdenada.size-1].aptitud}")
        println("Aptitud promedia: ${promedio}")
        println("${estrellas("****************************************")}")

    }, condicion = { poblacionOrdenada ->

        /********** AptitudV3 ****************/
        // No pararemos hasta que todos los individuos sepan la combinacion
        /*if (poblacionOrdenada[poblacionOrdenada.size-1].aptitud <= 0){
            return@evolucionar true
        }*/

        // No pararemos hasta que el mejor individuo encuentre la combinacion
        /*if (poblacionOrdenada[0].aptitud <= 0){
            return@evolucionar true
        }*/
        /*********************************/


        /********** Aptitud Inversa ****************/
        // No pararemos hasta que todos los individuos sepan la combinacion
        if (poblacionOrdenada[poblacionOrdenada.size-1].aptitud >= 100){
            return@evolucionar true
        }

        // No pararemos hasta que el mejor individuo encuentre la combinacion
        /*if (poblacionOrdenada[0].aptitud >= 100){
            return@evolucionar true
        }*/
        /*********************************/

        false
    }, final = { poblacion, rondas ->

        val promedio = poblacion.sumByDouble { it.aptitud.toDouble() } / poblacion.size.toDouble()
        val termColors = TermColors()
        val titulo = with(termColors){
            (bold + blue)
        }
        val estrellas = with(termColors){
            (italic + green)
        }

        println("${estrellas("****************************************")}")
        println("\t\t${titulo("Resumen Final")}")
        println("Rondas tomadas: $rondas")
        println("Cartas del mejor: ${poblacion[0].cartas}")
        println("Cartas del peor: ${poblacion[poblacion.size-1].cartas}")
        println("Aptitud del mejor: ${poblacion[0].aptitud}")
        println("Aptitud del peor: ${poblacion[poblacion.size-1].aptitud}")
        println("Aptitud promedia: ${promedio}")
        println("${estrellas("****************************************")}")
    })
}