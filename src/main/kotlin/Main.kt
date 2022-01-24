import java.util.logging.Level
import java.util.logging.LogManager

//Creamos la interfaz Dispara para que objetos que de normal no disparen puedan disparar
interface Dispara {
    var municion: Int
    var municionARestar: Int

    fun dispara(): Int {
        this.municion = municion - municionARestar
        return municion

    }

    fun recarga(): Int {
        this.municion += municion
        return this.municion
    }
}

//Superclase de las armas
abstract class ArmaDeFuego(
    open var nombre: String, override var municion: Int, override var municionARestar: Int, open var tipoDeMunicion: String,
    open var danio: Int, open var radio: String
):Dispara {    //Se pone la superclase de tipo Dispara para que después las sublases la hereden.

    //Método que hace que un arma dispare y se heredará a las demás clases
    override fun dispara(): Int {
        this.municion = municion - municionARestar
        return municion
    }

    //Método que hace que un arma dispare y se heredará a las demás clases
    override fun recarga(): Int {
        this.municion += municion
        return this.municion
    }

}

//Subclase pistola
class Pistola(
    override var nombre: String,
    override var municion: Int,
    override var municionARestar: Int,
    override var tipoDeMunicion: String,
    override var danio: Int,
    override var radio: String
) : ArmaDeFuego(nombre, municion, municionARestar, tipoDeMunicion, danio, radio) {

    //Ponemos este metodo para que al llamar al objeto salga con su nombre y no con el de la clase
    override fun toString(): String = nombre

    //A heredado el metodo dispara poniendo override delate del metodo y se a multiplicado *1 la municionARestar
    override fun dispara(): Int {
        municion = municion - (municionARestar * 1)
        return municion
    }

}

//Subclase Rifle
class Rifle(
    override var nombre: String,
    override var municion: Int,
    override var municionARestar: Int,
    override var tipoDeMunicion: String,
    override var danio: Int,
    override var radio: String
) : ArmaDeFuego(nombre, municion, municionARestar, tipoDeMunicion, danio, radio) {
    //Ponemos este metodo para que al llamar al objeto salga con su nombre y no con el de la clase
    override fun toString(): String = nombre

    //A heredado el metodo dispara poniendo override delate del metodo y se a multiplicado *2 la municionARestar
    override fun dispara(): Int {
        this.municion = municion - (municionARestar * 2)
        return municion
    }

}

//Subclase Bazooka
class Bazooka(
    nombre: String, municion: Int, municionARestar: Int, tipoDeMunicion: String, danio: Int, radio: String
) : ArmaDeFuego(nombre, municion, municionARestar, tipoDeMunicion, danio, radio) {
    //Ponemos este metodo para que al llamar al objeto salga con su nombre y no con el de la clase
    override fun toString(): String = nombre

    //A heredado el metodo dispara poniendo override delate del metodo y se a multiplicado *3 la municionARestar
    override fun dispara(): Int {
        this.municion = municion - (municionARestar * 3)
        return municion
    }
}
//Se crea una clase Casa que es de tipo dispara, es decir que usa la interfaz dispara
class Casa(
    var tipo: String,
    var direccion: String,
    var codigoPostal: Int,
    override var municion: Int,
    override var municionARestar: Int
) : Dispara {

    override fun toString(): String {
        return tipo
    }
}

//Se crea una clase Bocadillo que es de tipo dispara, es decir que usa la interfaz dispara
class Bocadillo(
    var tipo: String,
    var ingredientes: String,
    var salsa: String,
    override var municion: Int,
    override var municionARestar: Int
) : Dispara {

    override fun toString(): String {
        return tipo
    }
}

//Se crea una clase coche que es de tipo dispara, es decir que usa la interfaz dispara
class coche(
    var marca: String,
    var caballos: Int,
    var color: String,
    override var municion: Int,
    override var municionARestar: Int
) : Dispara {

    override fun toString(): String {
        return marca
    }

}

fun main() {
    //Se pone la variable l para poder poner logs en vez de println
    val l = LogManager.getLogManager().getLogger("").apply { level = Level.ALL }

    //Instancio 6 objetos, cada uno de una subclase distinta
    var Rk9: Pistola = Pistola("Rk9", 15, 1, "9mm", 2, "Pqueño")
    var AK47: Rifle = Rifle("AK47", 30, 3, "11mm", 3, "Amplio")
    var RPG: Bazooka = Bazooka("RPG", 15, 1, "Explosiva", 10, "Amplio")
    var Casa1: Casa = Casa("Apartamento", "Avenida los milagros", 11204, 35, 4)
    var BocadilloTortilla: Bocadillo =
        Bocadillo("Bocadillo tortilla", "Pan, tortilla y pimiento", "Mayonesa", 40, 2)
    var Mustang: coche = coche("Ford Mustang", 240, "negro", 33, 2)

    var listaArma = listOf<Dispara>(Rk9, RPG, AK47, Casa1, BocadilloTortilla, Mustang)
    var mapaAleatorio = mutableMapOf<Int, Dispara>()

    //Se crea un bucle para que las armas disparen de forma aleatoria
    for (i in 1..6) {
        var random = (0..5).random()
        mapaAleatorio.put(i, listaArma[random])
    }
    //Se hace un log para comprobar que el objeto que va a disparar es el mismo que el que dispara
    for (it in mapaAleatorio) {
        l.info("${it.value}")

    }
    println()
    //Se imprimen por pantalla los objetos que disparan y cuantas veces de forma aleatoria
    for (i in 1..6) {
        println("${mapaAleatorio.get(i)} a disparado = ${mapaAleatorio.get(i)?.dispara()}")
    }

}

/*  Teoría:
b)¿Que beneficios obtienes al usar una clase abstracta? ¿Y de una interface?
 Beneficios de usar clases abstractas es que en este caso puedo definir que tipo de arma es y si hiciera falta
 podría haber puesto métodos que solo puede hacer una clase, como por ejemplo un animal puede hablar pero cada
 uno de una forma que lo define y aparte por ejemplo un párajo es un animal y vuela pero un perro es un animal
 y no vuela.
 Un beneficio de usar una interfaz es que un objeto que de normal no puede realizar esa acción gracias a la interfaz
 pueda realizarla, como por ejemplo una casa no dispara, pero la interfaz te permite hacer que la casa dispare, es
 parecido a la herencia pero no lo es, solo la simula.

c)¿Que modificadores y mecanismos has utilizado para bloquear y forzar la herencia de clases y métodos?
Se ha usado open, override, abstract, también podría haber usado súper y para bloquear la herencia podría
haber usado final
        */