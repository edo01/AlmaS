class Persona (private var nameSurname: String, private var yearOfBirth: Int){

    init {
        println("LOG:è stata creata una nuova persona")
    }

    constructor(nameSurname:String, yearOfBirth: String ): this(nameSurname, yearOfBirth.toInt()){}

    fun omonimo(other: Persona): Boolean{
        return this.getNameSurname().equals(other.getNameSurname(), true)
    }

    fun olderThan(other: Persona): Int{
        return when {
            this.getYearOfBirth()==other.getYearOfBirth() -> 0
            this.getYearOfBirth() < other.getYearOfBirth() -> 1
            else -> -1
        }
    }

    fun getNameSurname(): String{
        return nameSurname
    }

    fun getYearOfBirth(): Int{
        return yearOfBirth
    }

    override fun toString(): String {
        return "Persona(nome e cognome='$nameSurname', anno=$yearOfBirth)"
    }

    override fun equals(other: Any?): Boolean {
        // Controlla che sia la stessa classe
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Persona // forza il cast

        if (this.getNameSurname() == other.getNameSurname()) return false
        if (this.getYearOfBirth() != other.getYearOfBirth()) return false

        return true
    }
}
