/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AutomataRegexThompson_LanguageToFDA.RegexToEpsilonNFA;

import AutomataRegexThompson_LanguageToFDA.Tree.ExpressionTree.TreeNode;

import java.awt.print.PrinterException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;

/**
 *
 * @author Administrador
 */
public class NFA {

    ArrayList<Estado> estados = new ArrayList<>();
    Estado estadoInicial;
    Estado estadoFinal;
    public ArrayList<String> alfabeto; 

    public Estado getEstadoInicial() {
        return estadoInicial;
    }

    public Estado getEstadoFinal() {
        return estadoFinal;
    }

    /*public void añadirEstado (Estado estado){
         estados.add(estado);
     }*/

    public NFA(String thompsonRegex) {
        ProcesadorRegexThompson p = new ProcesadorRegexThompson(thompsonRegex);
        NFA a = p.generado;
        this.estadoFinal=a.estadoFinal;
        this.estadoInicial = a.estadoInicial;
        this.alfabeto = a.alfabeto;
        this.estados = estadoInicial.getAllFollowingEstados();
        
        System.out.println("AFND con transiciones epsilon:");
        imprimirTabla();
        
        System.out.println("Estado inicial:"+estadoInicial);
        System.out.println("Estado final:"+estadoFinal);
    }
   
    public NFA(){
        
    }
   
    
    public String toString() {
        String s = "";
        estados = estadoInicial.getAllFollowingEstados();

        //nombreEstado:simboloTransicion->EstadoSiguiente,simboloTransicion->EstadoSiguiente;
        for (Estado estado : estados) {
            s += estado.toString() + ";";
        }
        return s;
    }
    

    public void imprimirTabla() {
        ArrayList<String> both = new ArrayList<String>();
        both.add("Estado");
        both.addAll(alfabeto);
        String[] headers = both.toArray(new String[both.size()]);

        String data = toString();

        String[] estados = data.split(";");

        ArrayList<String[]> infoToPrint = new ArrayList<>();
        infoToPrint.add(headers);
        for (String estado : estados) {

            LinkedHashMap<String, ArrayList<String>> mapa = new LinkedHashMap();

            for (String simbolo : alfabeto) {
                mapa.put(simbolo, new ArrayList<String>());
            }
            String nombreEstado = estado.split(":")[0];
            if (estado.split(":").length > 1) {
                String[] transicionesString = estado.split(":")[1].split(",");

                for (String transicion : transicionesString) {
                    String simbolo = transicion.split("->")[0];
                    String estadoSiguiente = transicion.split("->")[1];
                    try {
                        mapa.get(simbolo).add(estadoSiguiente);
                    } catch (Exception e) {
                        System.out.println("Transicion con simbolo fuera del alfabeto");
                    }
                }

            }

            String[] infoEstado = new String[headers.length];
            infoEstado[0] = nombreEstado;
            for (int i = 1; i < infoEstado.length; i++) {
                ArrayList<String> estadosPorTransicion = mapa.get(alfabeto.get(i - 1));
                String sEstados = String.join(",", estadosPorTransicion);
                infoEstado[i] = sEstados;
            }
            infoToPrint.add(infoEstado);
        }
        /*Object[] inf = infoToPrint.toArray();
        String[][] infoPrint = new String[inf.length][alfabeto.size()+1];
        
        for (int i = 0; i < inf.length; i++) {
            if (inf[i] instanceof String[] ){
                infoPrint[i]=(String[]) inf[i];
            }
        }*/
       int max = 0;
        for (String[] strings : infoToPrint) {
            for (String string : strings) {
                if (string.length()>max) max=string.length();
            }
        }
        
        for (String[] strings : infoToPrint) {
                for (String string : strings) {
                    System.out.print(String.format( "[%-"+30+"s]",string ));
            }
                System.out.println("");
            }
        
        
        
        
        
        
        
                

    }
    
    

}


