object Tester {
    fun testCF(){
        assert(CodiceFiscale.calcolaCF("Mario", "Rossi", 12, 6, 1976, "M", "Bologna").equals("RSSMRA76H12A944I"));
        assert(CodiceFiscale.calcolaCF("Mario", "Rossi",  1, 1, 1990, "M", "Milano").equals("RSSMRA90A01F205Z"));
        assert(CodiceFiscale.verificaCF("Mario", "Rossi", 12, 6, 1976, "M", "Bologna", "RSSMRA76H12A94QF"));
        assert(CodiceFiscale.verificaCF("Mario", "Rossi", 12, 6, 1976, "M", "Bologna", "RSSMRA76H12A9Q4U"));
        assert(CodiceFiscale.verificaCF("Mario", "Rossi", 1, 1, 1990, "M", "Milano", "RSSMRA90A01F20RU"));
        assert(CodiceFiscale.verificaCF("Mario", "Rossi", 1, 1, 1990, "M", "Milano", "RSSMRA90A01F2L5K"));
        assert(CodiceFiscale.verificaCF("Mario", "Rossi", 1, 1, 1990, "M", "Milano", "RSSMRA90A01FN05O"));
        assert(CodiceFiscale.verificaCF("Mario", "Rossi", 1, 1, 1990, "M", "Milano", "RSSMRA90A0MF205R"));
        assert(CodiceFiscale.verificaCF("Mario", "Rossi", 1, 1, 1990, "M", "Milano", "RSSMRA90AL1F205K"));
    }

    fun testPersona(){
        var p1 : Persona = Persona("Edoardo Carrà", 2001);
        var p2 : Persona = Persona("edoardo carrà", 1983);
        var p3 : Persona = Persona("pietro Carrà", 2007);

        //getter
        assert(p1.getYearOfBirth()==2001)
        assert(p3.getNameSurname()=="pietro Carrà")

        //secondary constructor
        assert(Persona("Mario Rossi", "2021").getYearOfBirth() == 2021)

        //omonimo
        assert(p1.omonimo(p2))
        assert(!p1.omonimo(p3))

        //olderThan
        assert(p1.olderThan(Persona("test", 2001)) == 0)
        assert(p1.olderThan(p3) == 1)
        assert(p1.olderThan(p2) == -1)

        //toString
        println(p1)

        //equals
        assert(p1.equals(p1))
        assert(!p1.equals(p2))
    }
}