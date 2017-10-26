package com.sahil.stackLinkedListImpl;

import java.util.Scanner;

import com.sahil.stackPojo.Data;

public class StackNode {

	String operand;
	StackNode next;
	static StackNode root = null;
	
	public String getOperand() {
		return operand;
	}
	public void setOperand(String operand) {
		this.operand = operand;
	}
	public static StackNode getRoot() {
		return root;
	}
	public static void setRoot(StackNode root) {
		StackNode.root = root;
	}
	public StackNode getNext() {
		return next;
	}
	public void setNext(StackNode next) {
		this.next = next;
	}
	
	public StackNode newNode(String operand){
		StackNode stackNode  = new StackNode();
		stackNode.setOperand(operand);
		stackNode.setNext(null);
		return stackNode;
	}
	
	boolean isEmpty(StackNode root){
		return root==null;
	}
	
	public void push(String operand){
		StackNode stackNode = newNode(operand);
		stackNode.next = root;
		root = stackNode;
		//System.out.println("data pushed"+operand);
	}
	
	public String pop(){
		if(isEmpty(root)){
			return "";
		}
		else
		{
			StackNode temp = root;
			root = root.next;
			return temp.operand;
		}
	}
	
	public String peek(){
		if(isEmpty(root)){
			return "";
		}
		else
			return root.operand;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String expression = sc.next();
		new StackNode().convertInfixToPostFix(expression);
	}
	
	public void convertInfixToPostFix(String str){
		for(int i = 0;i<str.length();i++){
			char temp = str.charAt(i);
			if(Character.isLetterOrDigit(temp))
			{
				System.out.print(temp);
			}
			else if(temp==')')
			{
				while(!isEmpty(root)&&!(peek().equals("(")))
				{
					System.out.print(pop());
				}
				if(!isEmpty(root)&& !peek().equals("("))
					System.out.println("Invalid data!");
				else
					pop();
			}
			
			else if(temp=='('){
				push(String.valueOf(temp));
			}
			else
			{
					while(!isEmpty(root)&&precedenceOrder(peek().charAt(0))>=precedenceOrder(temp))
						System.out.print(pop());
						push(String.valueOf(temp));
				
				
			}
		}
			while(!isEmpty(root)){
				System.out.print(pop());
			}
		}
	
	public int precedenceOrder(char temp){
	 switch(temp)
	 {
	 case '+':
	 case '-':
		 return 1;
	 case '*':
	 case '/':
		 return 2;
	 case '^':
		 return 3;
	 }
	 return -1;
	 }
	
	}
