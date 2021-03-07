import java.lang.StringBuilder
import javax.xml.stream.events.Characters

object CodiceFiscale {
    private val vocali = "aeiouàùòèì"
    private val meseCarattere = "ABCDEHLMPRST"
    private val alfabeto = "ABCDEFGHIJKLMNOPQRSTUVWYXZ"
    private val pesoCaratteriCodiceControllo = "BAKPLCQDREVOSFTGUHMINJWZYX"
    private val omocodiaCaratteri = "LMNPQRSTUV"

    private fun isConsonante(carattere: Char): Boolean{
        return vocali.contains( carattere ,true);
    }

    private fun calcolaCognome(cognome: String):String{
        var cognomeCF: StringBuilder = StringBuilder();
        for(lettera: Char in cognome){
            if(!isConsonante(lettera)) {
                cognomeCF.append(lettera);
                if (cognomeCF.length == 3) break
            }
        }
        if(cognomeCF.length<3){
            for(lettera: Char in cognome){
                if(isConsonante(lettera))
                    cognomeCF.append(lettera);
                if(cognomeCF.length==3) break
            }
            while(cognomeCF.length<3) {
                cognomeCF.append('x')
            }
        }
        return cognomeCF.toString().toUpperCase();
    }

    private fun calcolaNome(nome: String):String{
        var nomeCF: StringBuilder = StringBuilder()
        var contaConsonanti: Int = 0

        //conteggio consonanti
        for(lettera: Char in nome){
            if(!isConsonante(lettera)) contaConsonanti++
        }

        if(contaConsonanti>=4){
            contaConsonanti = 0
            for(lettera: Char in nome){
                if(!isConsonante(lettera)){
                    contaConsonanti++
                    if(nomeCF.length<3 &&(contaConsonanti==1 || contaConsonanti == 3 || contaConsonanti == 4)){
                        nomeCF.append(lettera)
                        if (nomeCF.length == 3) break
                    }
                }
            }
        }else return calcolaCognome(nome)

        return nomeCF.toString().toUpperCase()
    }

    private fun calcolaAnno(anno: String): String{
        return anno.substring(2);
    }

    private fun calcolaAnno(anno:Int): String{
        return calcolaAnno(anno.toString());
    }

    private fun calcolaMese(mese: Int): Char{
        return meseCarattere[mese-1];
    }

    private fun calcolaGiornoSesso(giorno: Int, sesso: String): String{
        return when{
            sesso.equals("F") -> (giorno+40).toString()
            giorno<10 -> "0$giorno"
            else -> (giorno).toString()
        }
    }

    /**
     * Non ancora implementata
     */
    private fun calcolaComune(comune:String): String{
        return when(comune.toLowerCase()){
            "bologna"->"A944"
            "milano"->"F205"
            else -> "XXXX"
        }
    }

    private fun calcolaCarControllo(codice: String) : Char{
        var indice = 1
        var somma = 0
        var codiceConvertito: String = codice
        for((index,lettera) in codice.withIndex()){
            if(lettera.isDigit()){
                codiceConvertito = codiceConvertito.replaceFirst(lettera, alfabeto[Character.getNumericValue(lettera)],
                    ignoreCase = true)
            }
            //sommo il valore delle lettere in base alle loro posizioni
            if((index+1)%2 == 0){
                somma += codiceConvertito[index].toUpperCase() - 'A'
            }else{
                somma += pesoCaratteriCodiceControllo.indexOf(codiceConvertito[index])
            }

        }
        return alfabeto[somma%26];
    }

    fun calcolaCF(nome: String, cognome: String, giorno: Int, mese: Int, anno: Int,
                          sesso: String, comune: String):String{
        var cf: String =  calcolaCognome(cognome)+ calcolaNome(nome)+ calcolaAnno(anno)+ calcolaMese(mese) +
                calcolaGiornoSesso(giorno, sesso) + calcolaComune(comune)
        return cf + calcolaCarControllo(cf);
    }

    fun verificaCF(nome: String, cognome: String, giorno: Int, mese: Int, anno: Int,
    sesso: String, comune: String, codiceFiscale: String): Boolean{
        if(codiceFiscale[codiceFiscale.lastIndex] !=
            calcolaCarControllo(codiceFiscale.substring(0,codiceFiscale.lastIndex))){
            //carattere di verifica incorretto
            return false
        }
        var codiceFiscaleCalcolato: String =  calcolaCF(nome, cognome, giorno, mese, anno, sesso, comune)
        if(codiceFiscaleCalcolato.equals(codiceFiscale)){
            //codici identici
            return true
        }else{
            //possibile caso di omocodia
            for((index, cCF) in codiceFiscale.substring(0,codiceFiscale.lastIndex).withIndex()){
                if(!cCF.equals(codiceFiscaleCalcolato[index])){
                    var charDiverso = codiceFiscaleCalcolato[index]
                    if(charDiverso.isDigit()){
                        if(!omocodiaCaratteri[Character.getNumericValue(charDiverso)].equals(cCF))
                            return false //il numero non è stato sostituito correttamente
                    }else{

                        //il carattere diverso è una lettera, è sicuramente incorreto
                        return false
                    }

                }
            }
            return true
        }
    }
}