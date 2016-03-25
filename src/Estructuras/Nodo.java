/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Estructuras;

import Sudoku.Tablero;

/**
 *
 * @author USUARIO
 */
public class Nodo {
	private Nodo prev;
	private Tablero key;
	private Nodo next;

    public Nodo(Tablero key){
    	this.key = key;
    	prev=next=null;
    }
    
    public void setPrev(Nodo prev){
    	this.prev = prev;
    }
    
    public void setKey(Tablero key){
    	this.key = key;
    }
    
    public void setNext(Nodo next){
    	this.next = next;
    }
    
    public Nodo getPrev(){
    	return prev;
    }
    
    public Tablero getKey(){
    	return key;
    }
    
    public Nodo getNext(){
    	return next;
    }    

}