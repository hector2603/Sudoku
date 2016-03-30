package IA;

import Estructuras.Lista;
import Estructuras.Nodo;
import Sudoku.Tablero;

public class Busqueda {

	private Tablero tablerito;
	private Heuristica heuristica;
	private Lista listaPrioridad;
	
	public static void main(String[] args) {
		int[][] t = {{3,0,2,0},{0,2,0,0},{0,0,4,0},{0,4,0,1}};
		int[][] t2 = {{1,0,0,0},{4,3,0,0},{2,1,0,3},{3,4,1,0}};
		int[][] t3 = {{0,0,3,4},{3,4,0,2},{4,3,0,0},{0,2,4,3}};
		int[][] t4 = {{0,0,0,4},{0,0,3,0},{0,2,0,0},{1,0,0,0}};
		int[][] tp = {{0,1,0,3},{3,0,0,0},{0,0,0,2},{2,0,4,0}};
		int[][] t5 = {{0,0,0,0},{0,3,4,0},{4,0,2,0},{0,2,0,0}};// este es el que más se demora en encontrar la solución
		int[][] t6 = {{5,9,0,2,0,0,0,0,7},{0,7,0,5,8,0,3,0,1},{0,1,0,0,7,0,0,5,0},{8,0,0,0,4,0,5,1,0},{2,0,0,0,0,1,0,0,6},{0,6,5,0,3,7,0,0,8},{0,8,0,0,9,0,0,2,0},{3,0,1,0,6,8,0,7,0},{9,0,0,0,0,3,0,8,4}};
		//int[][] t7 = {{5,9,3,2,1,4,8,6,7},{4,7,2,5,8,6,3,9,1},{6,1,8,3,7,9,4,5,2},{8,3,7,6,4,0,5,1,0},{2,0,0,0,0,1,0,0,6},{0,6,5,0,3,7,0,0,8},{0,8,0,0,9,0,0,2,0},{3,0,1,0,6,8,0,7,0},{9,0,0,0,0,3,0,8,4}};// quitando el último 6 da resultados raros ¿quéééé?
		int[][] t7 = {{5,9,0,2,0,4,0,0,7},{4,7,0,5,8,0,3,0,1},{0,1,0,0,7,0,0,5,0},{8,0,0,0,4,0,5,1,0},{2,0,0,0,0,1,0,0,6},{0,6,5,0,3,7,0,0,8},{0,8,0,0,9,0,0,2,0},{3,0,1,0,6,8,0,7,0},{9,0,0,0,0,3,0,8,4}};
		Tablero tablerito = new Tablero(t6);
		Busqueda buscadorcito = new Busqueda(tablerito);
		buscadorcito.EmpezarBusqueda();
		// después de haber encontrado la solucion
		//System.out.println("\n\nResultado Final:");
		//buscadorcito.getListaPrioridad().listDisplay();
		//System.out.println("\n\nTamaño de la lista Final: "+buscadorcito.getListaPrioridad().Tamano());
		System.out.println("Resultado:");
		tablerito = buscadorcito.getListaPrioridad().getHead().getKey();
		tablerito.printNumeros(tablerito.getTableroNumeros());
		System.out.println("Costo total de la solución: "+tablerito.getG());
		System.out.println("Altura del Árbol: "+tablerito.getAltura());
		/*System.out.println("heurística:  "+tablerito.getH());
		System.out.println("dominios");
		tablerito.printNumeros(tablerito.getDominioFilas());
		tablerito.printNumeros(tablerito.getDominioColumnas());
		tablerito.printNumeros(tablerito.getDominioCuadros());
		System.out.println("dominios actualizados");
		tablerito.ReiniciarDominios();
		tablerito.CrearDominioFilas();
		tablerito.CrearDominioColumnas();
		tablerito.CrearDominioCuadros();
		tablerito.CalcularDominioTotal();
		tablerito.printNumeros(tablerito.getDominioFilas());
		tablerito.printNumeros(tablerito.getDominioColumnas());
		tablerito.printNumeros(tablerito.getDominioCuadros());
		tablerito.printNumeros(tablerito.getDominioTotal());
		System.out.println("otro valor heuristico: "+buscadorcito.getHeuristica().h(tablerito));*/
		
	}
	
	public Busqueda(Tablero t){
		tablerito = t;
		heuristica = new Heuristica();
		tablerito.LlenarTablero();
		listaPrioridad = new Lista();
		System.out.println("Valor heuristica inicial: "+heuristica.h(tablerito));
		System.out.println("Tablero Inicial: ");
		tablerito.printNumeros(tablerito.getTableroNumeros());
		//System.out.println("\n imprimir estaticos: \n");
		//tablerito.printEstaticos();
		listaPrioridad.InsertarTablero(tablerito);
		/*
		System.out.println(tablerito.getH());
		System.out.println("");
		tablerito.printNumeros(tablerito.getDominioTotal());
		System.out.println("");
		tablerito.printNumeros(tablerito.getTableroNumeros());*/
	}
	
	public void EmpezarBusqueda(){
		System.out.println("empezó");
		Nodo head = null;
		Tablero tableroPadre = null;
		Tablero tableroHijo = null;
		/*System.out.println("altuda de tablero auxiliar \t"+tableroAux.getAltura());
		System.out.println("altuda de tablero \t"+tablerito.getAltura());
		tableroAux.setAltura(5);
		System.out.println("despues de actualizar");
		System.out.println("altuda de tablero auxiliar \t"+tableroAux.getAltura());
		System.out.println("altuda de tablero \t"+tablerito.getAltura());*/
		boolean[][] tableroEstaticos = null;
		int fila = 0;// fila de la posicion que se va a comenzar a cambiar 
		int columna = 0 ; // columna de la posicion que se va a comenzar a cambiar
		int valorAux = 0; // variable que guarda temporalmente el valor de la casilla que se va a cambiar
		int contadorCreados = 0;
		int contadorExpandidos = 0;
		int contadorDescartados = 0;
		boolean disponible = false; 
		//while(!(listasIguales(listaPrioridad.getHead().getKey().getTableroNumeros(), resultado))){
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
						//System.out.println("cuantas veces entra aquíiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");
					}
				}
			}
			//System.out.println(fila+"  "+columna);
			//tableroEstaticos[fila][columna]=true;
			//for (int i = 0; i < tableroEstaticos.length; i++) {
				for (int j = 0; j < tableroEstaticos.length; j++) {
					if(!tableroEstaticos[fila][j]){// cambioooo de i por fila
						contadorCreados++;
						tableroHijo = new Tablero(tableroPadre);
						disponible = tableroHijo.ComprobarDisponbilidad(tableroHijo.getDominioTotal(), fila, columna, tableroHijo.getTableroNumeros()[fila][j]);// cambioooo de i por fila
						//System.out.println("disponible:    "+disponible);
						//System.out.println("igualando las matrices    "+resul );
						/*if(resul){
							disponible = tableroHijo.ComprobarDisponbilidad(auxiliar.getDominioTotal(), fila, columna, tableroHijo.getTableroNumeros()[i][j]);
							System.out.println("compribando disponibilidad             :  "+disponible);
						}*/
						tableroHijo.setAltura(tableroHijo.getAltura()+1);
						tableroHijo.setG(tableroHijo.getG()+1);
						valorAux = tableroHijo.getTableroNumeros()[fila][columna];
						//System.out.println("imprimir fila + columna:   "+fila+"    "+columna);
						//System.out.println("valor de ValorAux: "+ valorAux);
						//System.out.println("valor en la posicion evaluando antes de cambiar:  "+tableroHijo.getTableroNumeros()[i][j]);
						tableroHijo.getTableroNumeros()[fila][columna]=tableroHijo.getTableroNumeros()[fila][j];// cambioooo de i por fila
						//System.out.println("valor en la posicion inicial después de cambiar:  "+tableroHijo.getTableroNumeros()[fila][columna]);
						tableroHijo.getTableroNumeros()[fila][j]=valorAux;// cambioooo de i por fila
						//System.out.println("valor en la posicion evaluando después de cambiar:  "+tableroHijo.getTableroNumeros()[i][j]);
						heuristica.h(tableroHijo);
						tableroHijo.getTableroEstaticos()[fila][columna]=true;
						tableroHijo.ActualizarDominio(fila, columna, tableroHijo.getTableroNumeros()[fila][columna]);
						//System.out.println("sum  "+tableroHijo.getSumaHG());
						//System.out.println("imprimir tablero, evaluando la casilla: "+fila+" "+columna);
						//tableroHijo.printNumeros(tableroHijo.getTableroNumeros());
						if(disponible){
							//System.out.println("inserté");
							listaPrioridad.InsertarTablero(tableroHijo);
							//System.out.println((listaPrioridad.Tamano())+"  "+(listaPrioridad.getHead()));
						}else{
							//System.out.println("no inserté");
							contadorDescartados++;
						}
						/*if(resul){
							i = j = tableroEstaticos.length;
							listaPrioridad.getHead().getKey().setH(0);
						}*/
					}
				}
			//}
			listaPrioridad.listDelete(head);
			//System.out.println((listaPrioridad.Tamano())+"  "+(listaPrioridad.getHead())+" antes de while");
			//listaPrioridad.getHead().getKey().printEstaticos();
			/*if(listasIguales(tableroHijo.getTableroNumeros(), tableroHijo.getTableroNumeros())){
				System.out.println("holaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa, estaticos de la solucion");
				System.out.println("Disponible    "+disponible+"    heuristica: "+tableroHijo.getH());
				System.out.println("imprimir fila + columna:   "+fila+"    "+columna);
				System.out.println("valor del dominio l final "+tableroHijo.getDominioTotal()[fila][columna]);
				System.out.println("valor del dominio al inicio "+tablerito.getDominioTotal()[fila][columna]);
				tableroHijo.printEstaticos();
				listaPrioridad.InsertarTablero(tableroHijo);
				break;
			}*/
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
