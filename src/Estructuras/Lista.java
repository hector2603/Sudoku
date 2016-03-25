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
public class Lista {
    private Nodo head;
     
    public Lista() {
      head = null;
    }

    public void setHead(Nodo head){
    	this.head = head;
    }   
    
    public Nodo getHead(){
    	return head;
    }
    
    public Nodo listSearch(Tablero k){
    	Nodo x=head;
    	while(x!=null && x.getKey()!=k)
    		x = x.getNext();
    	return x;
    }
    
    public int Tamano(){
    	int contador = 0;
    	Nodo x=head;
    	while(x!=null){
    		contador++;
    		x = x.getNext();    		
    	}

    	return contador;
    }
    
    public Nodo BuscarPosicion(int sumaHG, int altura){
    	Nodo x=head;
    	while((x!=null)&&(x.getKey().getSumaHG()<sumaHG)){
    		//System.out.println(x.getKey().getSumaHG()+" HG "+sumaHG);
    		x = x.getNext();
    	}
    	while(x!=null && x.getKey().getAltura()<=altura && x.getKey().getSumaHG()==sumaHG){
    		//System.out.println("h1 ");
    		//System.out.println(x.getKey().getSumaHG()+" h "+sumaHG);
    		x = x.getNext();
    		//System.out.println(x);
    		//System.out.println("h");
    	}
    	//System.out.println("termina de buscar posicion");
    	return x;
    }
    
    public void InsertarTablero(Tablero t){
    	Nodo n = new Nodo(t);
    	Nodo posicion;
    	posicion = BuscarPosicion(t.getSumaHG(), t.getAltura());
    	Nodo posPre = null;
    	Nodo posNext = null;
    	if(posicion!=null){
    		// 
    		if(posicion.getPrev()!=null)
    			posPre = posicion.getPrev();
    		if(posicion.getNext()!=null)
    			posNext = posicion.getNext();
    		
        	n.setNext(posicion);
        	posicion.setPrev(n);
        		
        	if(posPre!=null){
        		n.setPrev(posPre);
        		posPre.setNext(n);
        	}
        	
        	if(posPre==null){
        		head = n;
        	}
    	}else{
    		if(head==null)
    			head = n;
    		else{
    			Nodo aux = head;
    			while(aux.getNext()!=null){
    				aux = aux.getNext();
    			}
    			aux.setNext(n);
    			n.setPrev(aux);
    		}
    	}
    }
    
    public void listInsert(Nodo x){
    	x.setNext(head);
    	if(head!=null)
    		head.setPrev(x);
    	head = x;
    	x.setPrev(null);
    }
    
    public void listDelete(Nodo x){
    	if(x.getNext()!=null)
    		x.getNext().setPrev(x.getPrev());
    	if(x.getPrev()!=null)
    		x.getPrev().setNext(x.getNext());
    	else
    		head = x.getNext();
    }
    
    public void listDisplay(){
    	Nodo x=head;
    	while(x!=null){
    		x.getKey().printNumeros(x.getKey().getTableroNumeros());
    		System.out.println(" suma GH "+x.getKey().getSumaHG()+" Altura "+x.getKey().getAltura()+"\n");
            x=x.getNext();
    	}
    }
    
}