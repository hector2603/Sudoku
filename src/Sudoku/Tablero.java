package Sudoku;

import java.util.ArrayList;

public class Tablero {

	private int[][] tableroNumeros;
	private boolean[][] tableroEstaticos;
	private int[][] dominioFilas;
	private int[][] dominioColumnas;
	private int[][] dominioCuadros;
	private int[][] dominioTotal;
	private double h;
	private int g;
	private int altura;
	
	
	public Tablero(int[][] t){// constructor de la clase, recibe una matriz que sera los numeros estaticos del tablero
		tableroNumeros = t;
		tableroEstaticos = new boolean[t.length][t[0].length];
		dominioFilas = new int[t.length][t[0].length];
		dominioColumnas = new int[t.length][t[0].length];
		dominioCuadros = new int[t.length][t[0].length];
		dominioTotal = new int[t.length][t[0].length];
		h=0;
		g=0;
		altura=0;

		
		for (int i = 0; i < t.length; i++) {// inicializacion de las matrices 
			for (int j = 0; j < t[i].length; j++) {
				dominioFilas[i][j]=0;
				dominioColumnas[i][j]=0;
				dominioCuadros[i][j]=0;
				dominioTotal[i][j]=0;
				if(tableroNumeros[i][j]==0){
					tableroEstaticos[i][j]=false;
				}else{
					tableroEstaticos[i][j]=true;
				}
			}
		}
		
		CrearDominioFilas();
		CrearDominioColumnas();
		CrearDominioCuadros();
		CalcularDominioTotal();
	}
	
	public Tablero(Tablero t){// contructor de copia
		tableroNumeros = new int[t.getTableroNumeros().length][t.getTableroNumeros().length];
		tableroEstaticos = new boolean[t.getTableroNumeros().length][t.getTableroNumeros().length];
		dominioFilas = new int[t.getTableroNumeros().length][t.getTableroNumeros().length];
		dominioColumnas = new int[t.getTableroNumeros().length][t.getTableroNumeros().length];
		dominioCuadros = new int[t.getTableroNumeros().length][t.getTableroNumeros().length];
		dominioTotal = new int[t.getTableroNumeros().length][t.getTableroNumeros().length];
		
		for (int i = 0; i < tableroNumeros.length; i++) {
			for (int j = 0; j < tableroNumeros.length; j++) {
				tableroNumeros[i][j] = t.getTableroNumeros()[i][j];
				tableroEstaticos[i][j] = t.getTableroEstaticos()[i][j];
				dominioFilas[i][j] = t.getDominioFilas()[i][j];
				dominioColumnas[i][j] = t.getDominioColumnas()[i][j];
				dominioCuadros[i][j] = t.getDominioCuadros()[i][j];
				dominioTotal[i][j] = t.getDominioTotal()[i][j];
			}
		}
		
		h=t.getH();
		g=t.getG();
		altura=t.getAltura();	
	}
	
	public void printNumeros(int[][]t){ // funcion auxiliar para imprimir matrices 
		for (int i = 0; i < t.length; i++) { // ESTO CREO QUE LO PODEMOS BORRAR
			for (int j = 0; j < t[i].length; j++) {
				System.out.print(t[i][j]+"\t");
			}
			System.out.println(" ");
		}
	}
	
	public void printEstaticos(){ // funcion auxiliar para imprimir la matriz de booleanos
		for (int i = 0; i < tableroEstaticos.length; i++) {  //ESTO CREO QUE LO PODEMOS BORRAR
			for (int j = 0; j < tableroEstaticos[i].length; j++) {
				System.out.print(tableroEstaticos[i][j]+"\t");
			}
			System.out.println(" ");
		}
	}
	
	public boolean verificarSudoku(int [][] tablero){//metodo que sirve para verificar que el sudoku tenga
		boolean tieneSolucion = true;//solucion por numeros repetidos
		for(int z=0; z<9; z++){
			for(int j=0; j<tablero.length; j++){
				for(int i=0; i<tablero.length-1; i++){//verifica columnas
					if(tablero[j][z]!=0 && tablero[i][z]!=0 && j!=i && tablero[j][z] == tablero [i][z]){
						System.out.println(tablero[j][z]+ " = "+ tablero [i][z]);
						tieneSolucion = false;//verifica filas
					}if(tablero[z][j]!=0 && tablero[z][i]!=0 && j!=i && tablero[z][j] == tablero [z][i]){
						System.out.println(tablero[z][j]+ " = "+ tablero [z][i]);
						tieneSolucion = false;
					}
				}
			}
		}
		return tieneSolucion;
	}

	
	public void CrearDominioFilas(){ // funcion que identifica los numeros usados en las filas
		for (int i = 0; i < tableroNumeros.length; i++) {
			for (int j = 0; j < tableroNumeros[i].length; j++) {
				if(tableroNumeros[i][j]!=0){
					dominioFilas[i][tableroNumeros[i][j]-1]=tableroNumeros[i][j];
				}
			}
		}
	}
	
	
	public void CrearDominioColumnas(){ // funcion que identifica los numeros usados en las columnas
		for (int i = 0; i < tableroNumeros.length; i++) {
			for (int j = 0; j < tableroNumeros[i].length; j++) {
				if(tableroNumeros[j][i]!=0){
					dominioColumnas[i][tableroNumeros[j][i]-1]=tableroNumeros[j][i];
				}
			}
		}
	}
	
	public void CrearDominioCuadros(){ // funcion que identifica los numeros usados en los cuadros
		for (int i = 0; i < tableroNumeros.length; i++) {
			for (int j = 0; j < tableroNumeros[i].length; j++) {
				if(tableroNumeros[i][j]!=0){
					dominioCuadros[((i/3)*3)+(j/3)][tableroNumeros[i][j]-1]=tableroNumeros[i][j];
				}
			}
		}
	}
	
	public void CalcularDominioTotal(){// funcion que calcula el dominio de cada casilla y lo guarda en su respectiva matriz
		for (int i = 0; i < tableroNumeros.length; i++) {
			for (int j = 0; j < tableroNumeros[i].length; j++) {
				if(tableroEstaticos[i][j]){
					dominioTotal[i][j]=0;
				}else{
					dominioTotal[i][j]=DominioCasilla(i, j);
				}
			}
		}
	}
	
	public int InterseccionDominios(int[] f, int[] c, int[] k ){// funcion que hace la interseccion entre los diferentes dominios de una casilla del tablero
		int aux=0;// variable auxiliar que ira llevando la suma de los valores del dominio 
		for (int i = 0; i < f.length; i++) {
			if(f[i]==0 && c[i]==0 && k[i]==0){
				aux += ConvertToBinary(i); 
			}
		}
		return aux;
	}
	
	public int DominioCasilla(int fila, int columna){//retorna el dominio dada una fila y columna
		int numeroCuadrante = ((fila/3)*3)+(columna/3);// variable que guarda el numero del cuadrante de la casilla que se va a consultar el dominio
		return InterseccionDominios(dominioFilas[fila], dominioColumnas[columna], dominioCuadros[numeroCuadrante]);
	}
	
	public int ConvertToBinary(int numero){// funcion que da el valor respectivo a un entero del 0 a 9
		return (int) Math.pow(2, numero);
	}
	
	public void ActualizarDominio(int fila, int columna,int n) {// función que actualiza el dominio de cada posicion del tablero 
		int numeroCuadrante = ((fila/3)*3)+(columna/3);// variable que guarda el numero del cuadrante de la casilla que se va a actualizar el dominio
		int f = 0,c = 0;// variables auxiliares para determinar donde comienza el cuadrante 
		int varActu = ~(ConvertToBinary(n-1));
		if(numeroCuadrante==0){
			f = c = 0;
		}else if(numeroCuadrante == 1){
			f = 0;
			c = 3;
		}else if(numeroCuadrante == 2){
			f = 0;
			c = 6;
		}else if(numeroCuadrante == 3){
			f = 3;
			c = 0;
		}else if(numeroCuadrante == 4){
			f = 3; 
			c = 3;
		}else if(numeroCuadrante == 5){
			f = 3; 
			c = 6;
		}else if(numeroCuadrante == 6){
			f = 6; 
			c = 0;
		}else if(numeroCuadrante == 7){
			f = 6; 
			c = 3;
		}else if(numeroCuadrante == 8){
			f = 6; 
			c = 6;
		}
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				dominioTotal[f+i][c+j]=(dominioTotal[f+i][c+j]&varActu);
			}
		}
		for (int i = 0; i < dominioTotal.length; i++) {
			dominioTotal[fila][i]=(dominioTotal[fila][i]&varActu);
			dominioTotal[i][columna]=(dominioTotal[i][columna]&varActu);
		}
	}
	
	public void setNumero(int numero, int fila, int columna){// asigna un numero a la posicion indicada
		if(!tableroEstaticos[fila][columna]){
			tableroNumeros[fila][columna]=numero;
		}
	}
	
	public void LlenarTablero(){// llena el tablero con los números que faltan 
		ArrayList<Integer> aux = new ArrayList<Integer>();
		for (int i = 0; i < dominioFilas.length; i++) {
			for (int j = 0; j < dominioFilas[i].length; j++) {
				if(dominioFilas[i][j]==0){
					aux.add(new Integer(j+1));
				}
			}
		}
		for (int i = 0; i < tableroNumeros.length; i++) {
			for (int j = 0; j < tableroNumeros[1].length; j++) {
				if(!tableroEstaticos[i][j]){
					tableroNumeros[i][j]= aux.get(0);
					aux.remove(aux.get(0));
				}
			}
		}
	}
	
	public void ReiniciarDominios(){
		dominioFilas = new int[tableroNumeros.length][tableroNumeros[0].length];
		dominioColumnas = new int[tableroNumeros.length][tableroNumeros[0].length];
		dominioCuadros = new int[tableroNumeros.length][tableroNumeros[0].length];
	}
	
	public boolean ComprobarDisponbilidad(int[][] is, int fila, int columna, int n){
		boolean aux = false;
		int a = 0;
		a=ConvertToBinary(n-1);
		if((a&is[fila][columna])==a){
			aux = true;
		}
		return aux;
	}
	
	// getters and setters
	public double getSumaHG(){
		return h+(double)g;
	}
	
	public int[][] getTableroNumeros() {
		return tableroNumeros;
	}

	public void setTableroNumeros(int[][] tableroNumeros) {
		this.tableroNumeros = tableroNumeros;
	}

	public boolean[][] getTableroEstaticos() {
		return tableroEstaticos;
	}

	public void setTableroEstaticos(boolean[][] tableroEstaticos) {
		this.tableroEstaticos = tableroEstaticos;
	}

	public int[][] getDominioFilas() {
		return dominioFilas;
	}

	public void setDominioFilas(int[][] dominioFilas) {
		this.dominioFilas = dominioFilas;
	}

	public int[][] getDominioColumnas() {
		return dominioColumnas;
	}

	public void setDominioColumnas(int[][] dominioColumnas) {
		this.dominioColumnas = dominioColumnas;
	}

	public int[][] getDominioCuadros() {
		return dominioCuadros;
	}

	public void setDominioCuadros(int[][] dominioCuadros) {
		this.dominioCuadros = dominioCuadros;
	}

	public int[][] getDominioTotal() {
		return dominioTotal;
	}

	public void setDominioTotal(int[][] dominioTotal) {
		this.dominioTotal = dominioTotal;
	}

	public double getH() {
		return h;
	}

	public void setH(double h) {
		this.h = h;
	}

	public int getG() {
		return g;
	}

	public void setG(int g) {
		this.g = g;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

}
