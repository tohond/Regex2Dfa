/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AutomataRegexThompson_LanguageToFDA.RegexToEpsilonNFA;

/**
 *
 * @author Administrador
 */
public class Transicion {
    String simbolo;
    
    Estado estadoDestino;

    public Transicion(String simbolo, Estado estadoFinal) {
        this.simbolo = simbolo;

        this.estadoDestino = estadoFinal;
    }
    
    public Transicion (String simbolo){
        this.simbolo = simbolo;
        this.estadoDestino = estadoDestino;
    }
    
    
}
