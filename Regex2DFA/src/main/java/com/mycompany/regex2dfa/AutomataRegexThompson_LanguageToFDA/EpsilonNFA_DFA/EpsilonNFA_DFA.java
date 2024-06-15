/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AutomataRegexThompson_LanguageToFDA.EpsilonNFA_DFA;

import AutomataRegexThompson_LanguageToFDA.RegexToEpsilonNFA.NFA;
import AutomataRegexThompson_LanguageToFDA.RegexToEpsilonNFA.Estado;
import AutomataRegexThompson_LanguageToFDA.RegexToEpsilonNFA.ProcesadorRegexThompson;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Administrador
 */
public class EpsilonNFA_DFA {
    
    public static void eliminarTransicionesVacias(NFA nfa){
        
        System.out.println("epsilon estado inicial:");
     
        HashSet<Estado> s = new HashSet();
        s.add(nfa.getEstadoInicial());
        FuncionDeTransicion ftInicial = new FuncionDeTransicion("A",s);
        
        
        nfa.getEstadoFinal().setEsAceptacion(true);
        
        ArrayList<String> alfabeto = nfa.alfabeto;
        nfa.alfabeto.remove(ProcesadorRegexThompson.VACIO);
        
         LinkedList<FuncionDeTransicion> colaFT = new LinkedList<>();
         colaFT.add(ftInicial);
         HashSet<FuncionDeTransicion> contenedor = new HashSet();
         contenedor.add(ftInicial);
         int contadorft = 1;
         while (!colaFT.isEmpty()) {
             
             contadorft++;
             FuncionDeTransicion ft = colaFT.poll();
             generarFuncionesTransicion(contenedor,ft, colaFT, alfabeto);
             
             
        }
         
         System.out.println("funciones de transicion:");
         
         for (FuncionDeTransicion ft : contenedor) {
             System.out.println(ft.imprimirFuncion());
        }
         
         
         System.out.println("mapa final:");
         for (FuncionDeTransicion funcionDeTransicion : contenedor) {
             System.out.print(funcionDeTransicion.nombre+":"+funcionDeTransicion.transiciones.toString()+" ");
             if (funcionDeTransicion.isFuncionDeAceptacion() ){
                 System.out.print("Estado final ");
             }
             System.out.println("");
        }
         
       
        
    }
    
    static void generarFuncionesTransicion (HashSet<FuncionDeTransicion> contenedor,FuncionDeTransicion currentFt,LinkedList cola,ArrayList<String> alfabeto){
       
       for (String simbolo : alfabeto) {
           FuncionDeTransicion ft = new FuncionDeTransicion((char)('A'+contenedor.size())+"",currentFt.SigmaDeSimbolo(simbolo));
           
           //System.out.println("ft:"+ft);
           //System.out.println("contenedor:"+contenedor);
           boolean encontrado = false;
           FuncionDeTransicion equivalente = null;
           for (FuncionDeTransicion contenido : contenedor) {
               encontrado = contenido.equals(ft);
               if (encontrado) {
                   equivalente = contenido;
                   break;
               }
           }
           if (!encontrado){
               currentFt.agregarTransicion(simbolo, ft);
               cola.add(ft);
               contenedor.add(ft);
           }
           else{
               currentFt.agregarTransicion(simbolo, equivalente);
           }
            
        }
        
    }
    
    
    
    
}
