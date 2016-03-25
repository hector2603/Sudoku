/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Estructuras;

/**
 *
 * @author USUARIO
 */
public class EjemploLista {

	private Lista listica;
    /**
     * Creates a new instance of <code>UsaLista</code>.
     */
    public EjemploLista() {
    	listica=new Lista();
    	listica.listInsert(new Nodo(25));
    	listica.listInsert(new Nodo(48));
    	listica.listInsert(new Nodo(12));
    	listica.listInsert(new Nodo(67));
    	listica.listDisplay();
    	Nodo y = listica.listSearch(48);
    	System.out.println(y+"  "+y.getKey());
    	listica.listDelete(y);
    	listica.listDisplay();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
		int[][] tablero = {{5,5,5},{4,4,4}};
    	System.out.println("tablero: "+tablero[1].length);
    	new EjemploLista();
		
    }
}

