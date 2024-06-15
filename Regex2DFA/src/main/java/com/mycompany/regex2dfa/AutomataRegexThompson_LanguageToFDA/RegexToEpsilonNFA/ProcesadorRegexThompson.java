/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AutomataRegexThompson_LanguageToFDA.RegexToEpsilonNFA;

import AutomataRegexThompson_LanguageToFDA.Tree.ExpressionTree;
import AutomataRegexThompson_LanguageToFDA.Tree.ExpressionTree.TreeNode;
import AutomataRegexThompson_LanguageToFDA.Tree.PostfixConvertor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Stack;

/**
 *
 * @author Administrador
 */
public class ProcesadorRegexThompson {
    
    final public static String VACIO = "€";
    String regex;
    ArrayList<String> alfabeto; 
    String[] op = {"|","*","(",")","."};
    
    NFA generado;
    
    
            
    ArrayList<String> operadores = new ArrayList(Arrays.asList(op));
    
   
    
    
    public ProcesadorRegexThompson(String regex) {
        
        
        this.regex = regex;
        System.out.println("contains +"+operadores.contains("+")+",(:"+operadores.contains("("));
        char[] alfb = encontrarAlfabeto(regex.toCharArray(), regex.length());
        
        String [] alfbet = String.valueOf(alfb).split("");
        alfabeto = new ArrayList<>(Arrays.asList(alfbet) ); 
        alfabeto.add(VACIO);
        
        regex = PostfixConvertor.infixToPostfix(regex);
        ExpressionTree exTree = new ExpressionTree(regex);
        
        LinkedList<TreeNode> colaDeOperaciones = exTree.recorrerPos();
        generado = generarAutomata(colaDeOperaciones);
        generado.alfabeto = this.alfabeto;
        
    }
    
    
     final NFA generarAutomata(LinkedList<TreeNode> colaDeOperaciones){
         Stack<NFA> nfaStack = new Stack();
         
         int contador = 0;
         for (TreeNode operacion : colaDeOperaciones) {
             
             String value = operacion.getValue();
             int tipoOperacion = PostfixConvertor.precedence(value);
             if (tipoOperacion==0){ //es simbolo
                 Estado inicial = new Estado("q"+contador);
                 contador++;
                 Estado eFinal = new Estado ("q"+contador);
                 contador++;
                 inicial.añadirTransicion(value, eFinal);
                 NFA a = new NFA();
                 a.estadoInicial=inicial;
                 a.estadoFinal = eFinal;
                // a.añadirEstado(inicial);
                 //a.añadirEstado(eFinal);
                 
                 
                 System.out.println("Generando automata unitario \n\n ");
                 a.alfabeto=this.alfabeto;
                 a.imprimirTabla();
                 nfaStack.push(a);
             }
             
             if (tipoOperacion==1){ //union
                 NFA a1 = nfaStack.pop();
                 NFA a2 = nfaStack.pop();
                  Estado inicial = new Estado("q"+contador);
                 contador++;
                 Estado eFinal = new Estado ("q"+contador);
                 contador++;
                 
                 //transiciones vacias desde el estado inicial de union a los estados iniciales de a1 y a2, vacio siendo la variable final declarada en la clase.
                 inicial.añadirTransicion(VACIO, a1.estadoInicial);
                 inicial.añadirTransicion(VACIO, a2.estadoInicial);
                 
                 //transiciones vacias desde los estados finales de a1 y a2 hacia el estado final del automata de union
                 a1.estadoFinal.añadirTransicion(VACIO, eFinal);
                 a2.estadoFinal.añadirTransicion(VACIO, eFinal);
                 
                 NFA aUnion = new NFA();
                 aUnion.estadoFinal= eFinal;
                 aUnion.estadoInicial=inicial;
                 
                 ArrayList<Estado> estados = aUnion.estadoInicial.getAllFollowingEstados();
                 //System.out.println("Automata final de union:");
                 /*for (Estado estado : estados) {
                     System.out.println("Estado:"+estado.nombre+"  transiciones:");
                     for (Transicion t : estado.transiciones) {
                         System.out.print(t.simbolo+"->"+t.estadoFinal.nombre+"\t");
                     }
                     System.out.println("");
                 }*/
                 System.out.println("Generando automata de union\n\n ");
                 aUnion.alfabeto=this.alfabeto;
                 aUnion.imprimirTabla();
                 nfaStack.push(aUnion);
                 
             }
             
             if (tipoOperacion==2){//concat
                 NFA a1 = nfaStack.pop();
                 NFA a2 = nfaStack.pop();
                 
                 //el estado final de a1 adquiere todas las transiciones de a2, efectivamente fusionandolos y volviendose el estado intermedio del nuevo automata
                 a1.estadoFinal.transiciones.addAll(a2.estadoInicial.transiciones);
                 
                 //el estado final de a1 ahora sera el final de a2
                 a1.estadoFinal = a2.estadoFinal;
                 
                 
                 System.out.println("Generando automata de concatenacion\n\n ");
                 a1.alfabeto=this.alfabeto;
                 a1.imprimirTabla();
                 //System.out.println(a1);
                 nfaStack.push(a1);
                 
                 
             }
             
             if (tipoOperacion==3){ //kleene
                 NFA a1 = nfaStack.pop();
                 Estado nuevoInicio = new Estado("q"+contador);
                 contador++;
                 
                 //nuevo estdo inicial con transicion vacia al viejo
                 nuevoInicio.añadirTransicion(VACIO, a1.estadoInicial);
                 
                 //el viejo estado final apuntara al nuevo estado final con transicion vacia
                 Estado nuevoFinal = new Estado("q"+contador);
                 contador++;
                 a1.estadoFinal.añadirTransicion(VACIO, nuevoFinal);
                 
                 //transicion vacia del nuevo inicial al nuevo final
                 nuevoInicio.añadirTransicion(VACIO, nuevoFinal);
                 
                 //transicion vacia del viejo final al viejo inicial
                 a1.estadoFinal.añadirTransicion(VACIO,a1.estadoInicial);
                 
                 //finalmente, se transforma el automata original
                a1.estadoInicial = nuevoInicio;
                a1.estadoFinal = nuevoFinal;
                
                 System.out.println("Generando automata de Kleene\n\n ");
                 a1.alfabeto=this.alfabeto;
                 a1.imprimirTabla();
                nfaStack.push(a1);
                
             }
             
         }
         
         
         return nfaStack.pop();
         //nfaStack.pop().imprimirTabla(alfabeto);
     }
    
    
    
    

    char[] encontrarAlfabeto(char[] regex, int n) {
        //encontrar el alfabeto de la expresion por medio de hashing, eliminando primero caracteres de operacion y luego eliminando caracteres repetidos
        
        
        
        for (int i = 0; i < n; i++) {
            
            
            //si el caracter no es ni numero, ni mayuscula, ni minuscula, ni operador, se genera error
            if (!(regex[i]>=48&&regex[i]<=57) //caracteres numericos
                    && !(regex[i]>=65&&regex[i]<=90) //alfabeto en mayuscula 
                    && !(regex[i]>=97&&regex[i]<=122) //alfabeto en minuscula 
                    && !operadores.contains(regex[i]+"") //no pertenece a algun operador aceptado
                    )
            {
                throw new RuntimeException("Presencia de caracteres invalidos:"+regex[i]+". Reformule expresion");
            }
            
            if (operadores.contains(regex[i]+"")) regex[i] = 0;
            
            
        }
        
        System.out.println("expresion al eliminar operadores:"+Arrays.toString(regex));
        
        Map<Character, Integer> exists = new HashMap<>();

        String st = "";
        for (int i = 0; i < n; i++) {
            if (regex[i]!=0 && !exists.containsKey(regex[i])) {
                st += regex[i];
                exists.put(regex[i], 1);
            }
        }
        
        System.out.println("alfabeto:"+st);
        return st.toCharArray();
    }

    
}
