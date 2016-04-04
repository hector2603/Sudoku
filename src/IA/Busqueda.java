package IA;

import Estructuras.Lista;
import Estructuras.Nodo;
import Sudoku.Tablero;

public class Busqueda {

	private Tablero tablerito;
	private Heuristica heuristica;
	private Lista listaPrioridad;
	public int contadorCreados = 0;
	public int contadorExpandidos = 0;
	public int contadorDescartados = 0;
	
	
	public Busqueda(Tablero t){
		tablerito = t;
		tablerito.getTableroEstaticos();
		heuristica = new Heuristica();
		tablerito.LlenarTablero();
		listaPrioridad = new Lista();
		System.out.println("Valor heuristica inicial: "+heuristica.h(tablerito));
		System.out.println("Tablero Inicial: ");
		tablerito.printNumeros(tablerito.getTableroNumeros());
		listaPrioridad.InsertarTablero(tablerito);
	}
	
	public void EmpezarBusqueda(){
		System.out.println("empezó");
		Nodo head = null;
		Tablero tableroPadre = null;
		Tablero tableroHijo = null;
		boolean[][] tableroEstaticos = null;
		int fila = 0;// fila de la posicion que se va a comenzar a cambiar 
		int columna = 0 ; // columna de la posicion que se va a comenzar a cambiar
		boolean disponible = false; 
		while(listaPrioridad.getHead().getKey().getH()!=0){
			contadorExpandidos++;
			head = listaPrioridad.getHead();
			tableroPadre = listaPrioridad.getHead().getKey();
			tableroEstaticos = tableroPadre.getTableroEstaticos();
			for (int i = 0; i < tableroEstaticos.length; i++) {
				for (int j = 0; j < tableroEstaticos.length; j++) {
					if(!tableroEstaticos[i][j]){
						fila = i;
						columna = j;
						i=tableroEstaticos.length;
						j=i;	
					}
				}
			}
				for (int j = 0; j < tableroEstaticos.length; j++) {
					if(!tableroEstaticos[fila][j]){// cambioooo de i por fila
						contadorCreados++;
						tableroHijo = new Tablero(tableroPadre);
						disponible = tableroHijo.ComprobarDisponbilidad(tableroHijo.getDominioTotal(), fila, columna, tableroHijo.getTableroNumeros()[fila][j]);// cambioooo de i por fila
						tableroHijo.setAltura(tableroHijo.getAltura()+1);
						tableroHijo.setG(tableroHijo.getG()+1);
						CambioPos(tableroHijo, fila, columna, j);// función que cambia los números de posicion
						heuristica.h(tableroHijo);
						tableroHijo.getTableroEstaticos()[fila][columna]=true;
						tableroHijo.ActualizarDominio(fila, columna, tableroHijo.getTableroNumeros()[fila][columna]);
						if(disponible){
							listaPrioridad.InsertarTablero(tableroHijo);
						}else{
							contadorDescartados++;
						}
					}
				}
			listaPrioridad.listDelete(head);
		}
		System.out.println("contador del numero de nodos Creados: "+ contadorCreados);
		System.out.println("contador del numero de nodos Expandidos: "+ contadorExpandidos);
		System.out.println("contador del numero de nodos Agregados al árbol: "+ (contadorCreados-contadorDescartados));
		System.out.println("contador del numero de nodos Descartados: "+ contadorDescartados);
	}
	
	public boolean listasIguales(int[][] a, int[][] b){
		int[][] respuesta ={{2,4,3,1},{1,3,4,2},{4,1,2,3},{3,2,1,4}};
		boolean aux = true;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < b[i].length; j++) {
				if(a[i][j]!=respuesta[i][j]){
					aux = false;
				}
			}
		}
		return aux;
	}

	public void CambioPos(Tablero t, int fila, int columnaI,int columnaF){// función cambio definida para determinar la función para la heuristica 
		int valorAux = 0; // variable que guarda temporalmente el valor de la casilla que se va a cambiar
		valorAux = t.getTableroNumeros()[fila][columnaI];
		t.getTableroNumeros()[fila][columnaI]=t.getTableroNumeros()[fila][columnaF];// cambioooo de i por fila
		t.getTableroNumeros()[fila][columnaF]=valorAux;// cambioooo de i por fila

	}
	
	public Tablero getTablerito() {
		return tablerito;
	}

	public void setTablerito(Tablero tablerito) {
		this.tablerito = tablerito;
	}

	public Heuristica getHeuristica() {
		return heuristica;
	}

	public void setHeuristica(Heuristica heuristica) {
		this.heuristica = heuristica;
	}

	public Lista getListaPrioridad() {
		return listaPrioridad;
	}

	public void setListaPrioridad(Lista listaPrioridad) {
		this.listaPrioridad = listaPrioridad;
	}

}
