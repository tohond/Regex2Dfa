/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AutomataRegexThompson_LanguageToFDA.RegexToEpsilonNFA;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Objects;

/**
 *
 * @author Administrador
 */
public class Estado {

    public String getNombre() {
        return nombre;
    }
    String nombre;
    HashSet<Transicion> transiciones = new HashSet<>();
    boolean esAceptacion;

    public void setEsAceptacion(boolean esAceptacion) {
        this.esAceptacion = esAceptacion;
    }

    public boolean esAceptacion() {
        return esAceptacion;
    }
    public Estado(){
        
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
        final Estado other = (Estado) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        
        return Objects.equals(this.transiciones, other.transiciones);
    }

   
    
    public Estado (String nombre){
        this.nombre=nombre;
    }
    
    public HashSet<Estado> getEstadosAlcanzablesPorSimbolo(String simbolo) {
        HashSet<Estado>  estadosEpsilon = new HashSet();
        if(simbolo.equals(ProcesadorRegexThompson.VACIO)) estadosEpsilon.add(this);
        EstadosEpsilonRecursivo(simbolo,estadosEpsilon, this);
        return estadosEpsilon;
    }
    
    public void EstadosEpsilonRecursivo(String simbolo,HashSet set,Estado estado){
        for (Transicion t : estado.transiciones) {
            if(t.simbolo.equals(simbolo)){
                
                if (set.add(t.estadoDestino) ) EstadosEpsilonRecursivo(simbolo,set, t.estadoDestino);
            }
        }
    }
    
    public void añadirTransicion(String simbolo,Estado estadoFinal){
        Transicion t = new Transicion(simbolo, estadoFinal);
        transiciones.add(t);
    }

    public HashSet<Transicion> getTransiciones() {
        return transiciones;
    }
    
    public ArrayList<Estado> getAllFollowingEstados(){
        ArrayList<Estado> lista = new ArrayList<Estado>();
        LinkedHashSet<Estado> setEstados = new LinkedHashSet<>();
        setEstados.add(this);
        estadosRecursivos(setEstados,this);
        return new ArrayList<Estado>(setEstados);
    }
    
    void estadosRecursivos (LinkedHashSet set, Estado estado){
        if (estado.transiciones.isEmpty()){
            set.add(estado);
        }
        
        else for (Transicion t: estado.transiciones) {
            if (!set.contains(t.estadoDestino)){
            set.add(t.estadoDestino);
            estadosRecursivos(set, t.estadoDestino);
            
            }
        }
    }

    @Override
    public String toString() {
        String sTransiciones = "";
        for (Transicion t : transiciones) {
            sTransiciones+= t.simbolo+"->"+t.estadoDestino.nombre+",";
        }
        
        return nombre+":"+
                sTransiciones;
                
    
    }
    
    
    
    
    
    
}
