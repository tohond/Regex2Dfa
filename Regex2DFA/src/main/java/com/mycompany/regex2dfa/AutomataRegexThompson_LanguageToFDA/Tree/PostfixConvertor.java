/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AutomataRegexThompson_LanguageToFDA.Tree;

import java.util.Stack;

/**
 *
 * @author Administrador
 */
public class PostfixConvertor {
    
        // Java Program to Convert Infix 
// expression to Postfix expression 

// Driver Class 
        // lets define a method for conversion of infix 
        // expression to postfix expression 
        public static String infixToPostfix(String infix) {

            // The output will be represented as postfix 
            StringBuilder postfix = new StringBuilder();

            // Initialize the stack to 
            // store the operators 
            Stack<Character> stk = new Stack<>();
            for (char c : infix.toCharArray()) {

                // if the encountered character is an operand 
                // add it to the output i.e postfix 
                if (Character.isLetterOrDigit(c)) {
                    postfix.append(c);

                    // if the encountered character is '(' push 
                    // it to the stack(stk) 
                } else if (c == '(') {
                    stk.push(c);

                    // if the encountered character is ')' pop 
                    // the stack(stk) until '(' is encountered 
                } else if (c == ')') {
                    while (!stk.isEmpty()
                            && stk.peek() != '(') {
                        postfix.append(stk.pop());
                    }
                    stk.pop(); // discard '(' by popping it from 
                    // the stack 
                } else {

                    // if the encountered character is not 
                    // parenthesis or operand, then check the 
                    // precendence of the operator and pop the 
                    // stack and add it to the output 
                    // until the top of the stack has lower 
                    // precedence than the current character 
                    while (!stk.isEmpty()
                            && precedence(stk.peek()+"")
                            >= precedence(c+"")) {
                        postfix.append(stk.pop());
                    }
                    stk.push(c); // push the current operator to 
                    // the stack 
                }
            }

            // After traversing the entire string, check whether 
            // the stack is empty or not, if the stack is not 
            // empty, pop the stack and add it to the output*/ 
            while (!stk.isEmpty()) {
                postfix.append(stk.pop());
            }
            String result = postfix.toString();
            System.out.println("infijo:"+infix+" posfijo:"+result);
            return postfix.toString();
        }

        // define a method to check the precedence of the 
        // operator 
        public static int precedence(String operator) {
            switch (operator) {
                case "|":
                    return 1;
                case ".":
                    return 2;
                case "*":
                    return 3;
                default:
                    return 0;
            }
        }

        
    
}
