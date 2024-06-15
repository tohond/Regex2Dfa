/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AutomataRegexThompson_LanguageToFDA.EpsilonNFA_DFA;

import AutomataRegexThompson_LanguageToFDA.RegexToEpsilonNFA.Estado;
import AutomataRegexThompson_LanguageToFDA.RegexToEpsilonNFA.ProcesadorRegexThompson;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author Administrador
 */
public class FuncionDeTransicion {
    HashSet<Estado> estadosFuncion;
    HashSet<Estado> eClosure = new HashSet();
    HashMap<String,FuncionDeTransicion> transiciones = new HashMap<>();
    String nombre;
    boolean FuncionDeAceptacion;

    public boolean isFuncionDeAceptacion() {
        boolean finalEncontrado = false;
        for (Estado estado : eClosure) {
            finalEncontrado = estado.esAceptacion();
            if (finalEncontrado) break;
        }
        return finalEncontrado;
    }
    
    
    public FuncionDeTransicion(String nombre,HashSet<Estado> estadosFuncion) {
        this.nombre=nombre;
        this.estadosFuncion = estadosFuncion;
        for (Estado estado : estadosFuncion) {
            eClosure.addAll(estado.getEstadosAlcanzablesPorSimbolo(ProcesadorRegexThompson.VACIO));
        }
       
    }
    
    public void agregarTransicion (String simbolo, FuncionDeTransicion destino){
        transiciones.put(simbolo, destino);
    }
    
    
    
    public HashSet<Estado> SigmaDeSimbolo(String simbolo){
        HashSet<Estado> SigmaDeSimbolo = new HashSet<>();
        for (Estado estado : eClosure) {
            SigmaDeSimbolo.addAll(estado.getEstadosAlcanzablesPorSimbolo(simbolo));
            
        }
        return SigmaDeSimbolo;
    }

    @Override
    public String toString() {
        return nombre;
    }

    
    
    public String imprimirFuncion() {
        //return "FuncionDeTransicion de nombre "+nombre+" con transiciones:"+transiciones;
        return "nombre de funcion de transicion:"+nombre+"\n"+
                "estados de funcion:"+estadosFuncion+"\n"+
                "e-closure:"+eClosure+"\n"+
                "transiciones:"+transiciones+"\n\n\n";
                
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FuncionDeTransicion other = (FuncionDeTransicion) obj;
        if (!Objects.equals(this.estadosFuncion, other.estadosFuncion)) {
            return false;
        }
        return Objects.equals(this.eClosure, other.eClosure);
    }

    

   
    
    
}
