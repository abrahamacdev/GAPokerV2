package utils

object Utils {

    fun cambiarRango(x: Double, antiguoMin: Double, antiguoMax: Double, nuevoMin: Double, nuevoMax: Double): Double {

        // Comprobamos si el rango de entrada esta al reves
        var entradaAlReves = false
        var tempAntiguoMin = antiguoMin
        var tempAntiguoMax = antiguoMax
        tempAntiguoMin = Math.min(antiguoMin, antiguoMax)
        tempAntiguoMax = Math.max(antiguoMin, antiguoMax)
        if (tempAntiguoMin != antiguoMin){
            entradaAlReves = true
        }

        // Comprobamos si el rango de salida esta al reves
        var salidaAlReves = false
        var tempNuevoMin = nuevoMin
        var tempNuevoMax = nuevoMax
        tempNuevoMin = Math.min(nuevoMin, nuevoMax)
        tempNuevoMax = Math.max(nuevoMin, nuevoMax)
        if (tempNuevoMin != nuevoMin){
            salidaAlReves = true
        }

        var porcion = (x-tempAntiguoMin)*(tempNuevoMax-tempNuevoMin)/(tempAntiguoMax-tempAntiguoMin)
        if (entradaAlReves) porcion = (tempAntiguoMax-x)*(tempNuevoMax-tempNuevoMin)/(tempAntiguoMax-tempAntiguoMin)

        var resultado = porcion + tempNuevoMin
        if (salidaAlReves) resultado = tempNuevoMax - porcion

        return resultado
    }

}