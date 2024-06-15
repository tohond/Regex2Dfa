/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.main;

import AutomataRegexThompson_LanguageToFDA.EpsilonNFA_DFA.EpsilonNFA_DFA;
import static AutomataRegexThompson_LanguageToFDA.EpsilonNFA_DFA.EpsilonNFA_DFA.eliminarTransicionesVacias;
import AutomataRegexThompson_LanguageToFDA.RegexToEpsilonNFA.NFA;
import java.util.Scanner;

/**
 *
 * @author Administrador
 */
public class MainAutomata {
    public static void main(String[] args) {
        
        while (true) {            
            Scanner myObj = new Scanner(System.in);  // Create a Scanner object
         System.out.println("Introduzca expresion regular de Thompson. Escriba 'Salir' para salir del programa");

        String regex = myObj.nextLine();  // Read user input
        if (regex.toLowerCase().equals("salir")) return;
        System.out.println("Regex: " + regex);  // Output user input
        
          
        NFA nfa = new NFA(regex);
        EpsilonNFA_DFA.eliminarTransicionesVacias(nfa);
        }
        
        
    }
}
