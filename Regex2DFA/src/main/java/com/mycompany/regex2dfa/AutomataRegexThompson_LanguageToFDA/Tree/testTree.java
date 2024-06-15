/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AutomataRegexThompson_LanguageToFDA.Tree;

import AutomataRegexThompson_LanguageToFDA.Tree.PostfixConvertor;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Administrador
 */
public class testTree {
    public static void main(String[] args) {
        
        
         PostfixConvertor in = new PostfixConvertor();
         String postfixTokens = in.infixToPostfix("a.(b|c)");
         System.out.println("argumento posfijo:"+postfixTokens);
         
         ExpressionTree et = new ExpressionTree(postfixTokens);
         LinkedList<ExpressionTree.TreeNode> l = et.recorrerPos();
         for (ExpressionTree.TreeNode node : l) {
             System.out.println(node.Value);
        }
    }
}
