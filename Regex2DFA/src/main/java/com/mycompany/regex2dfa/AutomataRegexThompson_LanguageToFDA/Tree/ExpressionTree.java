/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AutomataRegexThompson_LanguageToFDA.Tree;

import java.util.*;

/**
 *
 * @author Administrador
 */
public class ExpressionTree {

    /**
     * @return the root
     */
    public TreeNode getRoot() {
        return root;
    }
    
    private TreeNode root; // root node 
    
    
    public LinkedList<TreeNode> recorrerPos(){ //retorna lista con comportamiento de cola
        LinkedList<TreeNode> list = new LinkedList();
        if (getRoot()==null) return null;
        if (getRoot().isLeaf()) {
            //System.out.println(getRoot().Value);
            list.add(root);
        }
        else{
            recPosRecursivo(list,this.getRoot());
        }
        return list;
    }
    
    private void recPosRecursivo(LinkedList list,TreeNode root){
        if (root==null){
            return;
        }
        if(root.isLeaf()){
            //System.out.println(root.Value);
            list.add(root);
            return;
        }
        else{
            recPosRecursivo(list,root.Left);
            recPosRecursivo(list,root.Right);
            //System.out.println(root.Value);
            list.add(root);
        }
    }
    
    
    public ExpressionTree(String postfixTokens)
    {
        Stack<TreeNode> stack = new Stack<>();

        for (char token : postfixTokens.toCharArray() )
        {
            
            if ( (token>='0'&&token<='9') //caracteres numericos
                    || (token>='A'&&token<='Z') //alfabeto en mayuscula 
                    || (token>='a'&&token<='z') ) //alfabeto en minuscula  // Assuming operands are single-digit numbers for simplicity
            {
                stack.push(new TreeNode(token+""));
                System.out.println("token operando:"+token);
            }
            else // Operator
            {
                System.out.println("token operador:"+token);
                
                TreeNode leftNode = stack.pop();
                
                TreeNode newNode = new TreeNode(token+"");
                newNode.Left = leftNode;
                
                if (token!='*'){
                    TreeNode rightNode = stack.pop();
                newNode.Right = rightNode;
                
                }
                stack.push(newNode);
                
            }
        }
        TreeNode rt = stack.pop();
        System.out.println("raiz:"+rt.getValue());
        this.root=rt;
        
    }











     public class TreeNode {

        public String Value;
        public TreeNode Left;
        public TreeNode Right;

        public TreeNode(String value) {
            Value = value;
            Left = Right = null;
        }
        public boolean isLeaf(){
            return this.Left==null&&this.Right==null;
        }

        public String getValue() {
            return Value;
        }

        public void setValue(String Value) {
            this.Value = Value;
        }

        public TreeNode getLeft() {
            return Left;
        }

        public void setLeft(TreeNode Left) {
            this.Left = Left;
        }

        public TreeNode getRight() {
            return Right;
        }

        public void setRight(TreeNode Right) {
            this.Right = Right;
        }

    }
     

     
     
     
     
     

}