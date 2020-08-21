import ga.Individuo
import junit.framework.TestCase
import modelo.Carta
import modelo.Palo
import org.junit.Test

class TestsIndividuo {

    @Test
    fun el_calculo_de_la_aptitud_inversa_se_realiza_correctamente(){

        val cartasMalas = mutableListOf<Carta>().apply {
            add(Carta(2, Palo.Treboles))
            add(Carta(3, Palo.Treboles))
            add(Carta(4, Palo.Picas))
            add(Carta(5, Palo.Corazones))
            add(Carta(6, Palo.Rombos))
        }

        val cartasBuenas = mutableListOf<Carta>().apply {
            add(Carta(10, Palo.Treboles))
            add(Carta(11, Palo.Treboles))
            add(Carta(12, Palo.Treboles))
            add(Carta(13, Palo.Treboles))
            add(Carta(14, Palo.Treboles))
        }

        val cartasMediocres = mutableListOf<Carta>().apply {
            add(Carta(10, Palo.Treboles))
            add(Carta(11, Palo.Treboles))
            add(Carta(12, Palo.Treboles))
            add(Carta(13, Palo.Corazones))
            add(Carta(9, Palo.Picas))
        }

        val individuoBueno = Individuo(cartasBuenas)
        val individuoMalo = Individuo(cartasMalas)

        val poblacion = mutableListOf<Individuo>().apply {
            add(individuoBueno)
            add(individuoMalo)
        }

        TestCase.assertEquals(100.0, individuoBueno.aptitud + individuoMalo.aptitud)

    }

}