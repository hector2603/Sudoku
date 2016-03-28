package Sudoku;

import java.util.ArrayList;

public class Tablero {

	private int[][] tableroNumeros;
	private boolean[][] tableroEstaticos;
	private int[][] dominioFilas;
	private int[][] dominioColumnas;
	private int[][] dominioCuadros;
	private int[][] dominioTotal;
	private int h;
	private int g;
	private int altura;
	
	public static void main(String[] args) {
		int[][] t = {{5,9,0,2,0,0,0,0,7},{0,7,0,5,8,0,3,0,1},{0,1,0,0,7,0,0,5,0},{8,0,0,0,4,0,5,1,0},{2,0,0,0,0,1,0,0,6},{0,6,5,0,3,7,0,0,8},{0,8,0,0,9,0,0,2,0},{3,0,1,0,6,8,0,7,0},{9,0,0,0,0,3,0,8,4}};
		int[][] t2 = {{5,9,3,2,1,4,8,6,7},{4,7,2,5,8,6,3,9,1},{6,1,8,3,7,9,4,5,2},{8,3,7,6,4,2,5,1,9},{2,4,9,8,5,1,7,3,6},{1,6,5,9,3,7,2,4,8},{7,8,4,1,9,5,6,2,3},{3,2,1,4,6,8,9,7,5},{9,5,6,7,0,3,0,8,4}};
		Tablero tablerito = new Tablero(t);
		System.out.println("Tablero de Numeros");
		tablerito.printNumeros(tablerito.getTableroNumeros());
		System.out.println("Tablero Estaticos");
		tablerito.printEstaticos();
		System.out.println("Dominio de las Filas ");
		tablerito.printNumeros(tablerito.getDominioFilas());
		System.out.println("Dominio de las Columnas");
		tablerito.printNumeros(tablerito.getDominioColumnas());
		System.out.println("Dominio de los cuadrados");
		//tablerito.ActualizarDominio(7,6,5);
		tablerito.printNumeros(tablerito.getDominioCuadros());
		System.out.println("Dominio de cada casilla");
		tablerito.printNumeros(tablerito.getDominioTotal());
		System.out.println("Tablero LLeno con los numeros faltantes");
		tablerito.LlenarTablero();
		tablerito.printNumeros(tablerito.getTableroNumeros());
		System.out.println("");
		
	}
	
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
		for (int i = 0; i < t.length; i++) {
			for (int j = 0; j < t[i].length; j++) {
				System.out.print(t[i][j]+"\t");
			}
			System.out.println(" ");
		}
	}
	
	public void printEstaticos(){ // funcion auxiliar para imprimir la matriz de booleanos
		for (int i = 0; i < tableroEstaticos.length; i++) {
			for (int j = 0; j < tableroEstaticos[i].length; j++) {
				System.out.print(tableroEstaticos[i][j]+"\t");
			}
			System.out.println(" ");
		}
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
			//System.out.println(f[i]+" "+c[i]+" "+k[i]);
			if(f[i]==0 && c[i]==0 && k[i]==0){
				aux += ConvertToBinary(i); 
			}
		}
		//System.out.println(aux);
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
			dominioTotal[f][i]=(dominioTotal[f][i]&varActu);
			dominioTotal[i][c]=(dominioTotal[i][c]&varActu);
		}
		
		
	}
	
	public void setNumero(int numero, int fila, int columna){// asigna un numero a la posicion indicada
		if(!tableroEstaticos[fila][columna]){
			tableroNumeros[fila][columna]=numero;
		}
		
	}
	
	public void LlenarTablero(){// llena el tablero con lo número que faltan 
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
		//System.out.println("n sin convertir:  "+n);
		a=ConvertToBinary(n-1);
		//System.out.println("n convertido:  "+a);
		//System.out.println("dominio en la casilla: "+dominioTotal[fila][columna]);
		//System.out.println("AND "+(a&dominioTotal[fila][columna])+" pintando matriz dominios:");
		//printNumeros(dominioTotal);
		if((a&is[fila][columna])==a){
			aux = true;
		}
		return aux;
	}
	
	// getters and setters
	public int getSumaHG(){
		return h+g;
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

	public int getH() {
		return h;
	}

	public void setH(int h) {
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
