package IA;

import Sudoku.Tablero;

public class Heuristica {
	
	private Tablero tablerito;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
	}
	
	public int h(Tablero t){// funcion heuristica que calcula el valor de los numero que no están la posicion correcta
		boolean[][] tableroEstaticos = t.getTableroEstaticos();// tablero de los valores estaticos 
		int[][] tableroNumeros = t.getTableroNumeros();
		int[][] dominioTotal = t.getDominioTotal();
		int h = 0; // variable auxiliar que guardara el valor heuristico
		int n = 0; // variable auxiliar para guardar el valor que se evaluara en el dominio
		for (int i = 0; i < tableroEstaticos.length; i++) {
			for (int j = 0; j < tableroEstaticos[i].length; j++) {
				if(!tableroEstaticos[i][j]){
					//System.out.println("numero del tablero a verificar: "+tableroNumeros[i][j]);
					//System.out.println("dominio de la posición: "+dominioTotal[i][j]);
					n = ConvertToBinary((tableroNumeros[i][j]-1));
					//System.out.println("numero convertido: "+n);
					//System.out.println("funcion AND: "+(n&dominioTotal[i][j]));
					if((n&dominioTotal[i][j])!=n){
						//System.out.println("no va aquí "+tableroNumeros[i][j]+" fila "+(i+1)+" columna "+(j+1)+" dominiocasilla "+dominioTotal[i][j]+" n "+n+" AND "+(dominioTotal[i][j]&n));
						h++;
					}
				}
			}
		}
		// heuristica que encuentra el numero de filas,columnas y cuadros que no suman 45
		t.ReiniciarDominios();
		t.CrearDominioColumnas();
		t.CrearDominioCuadros();
		t.CrearDominioFilas();
		int[][] dFila = t.getDominioFilas();
		int[][] dColumnas = t.getDominioColumnas();
		int[][] dCuadros = t.getDominioCuadros();
		int sumaF, sumaC, sumaK;
		for (int i = 0; i < dCuadros.length; i++) {
			sumaF = sumaC = sumaK = 0;
			for (int j = 0; j < dCuadros[i].length; j++) {
					sumaF += dFila[i][j];
					sumaC += dColumnas[i][j];
					sumaK += dCuadros[i][j];
			}
			if(sumaF!=45){
				h+=1;
				//System.out.println("fila no sumó 10");
			}
			if(sumaC!=45){
				h+=1;
				//System.out.println("columna no sumó 10");
			}
			if(sumaK!=45){
				h+=1;
				//System.out.println("cuadrado no sumó 10");
			}
		}
		t.setH(h);
		return h;
	}
	
	public int Disponibilidad(Tablero t,int fila, int columna,int p){// funcion heuristica que calcula el valor de los numero que no están la posicion correcta
		boolean[][] tableroEstaticos = t.getTableroEstaticos();// tablero de los valores estaticos 
		int[][] tableroNumeros = t.getTableroNumeros();
		int[][] dominioTotal = t.getDominioTotal();
		int h = 0; // variable auxiliar que guardara el valor heuristico
		int n = 0; // variable auxiliar para guardar el valor que se evaluara en el dominio
		if(!tableroEstaticos[fila][columna]){
			//System.out.println("numero del tablero a verificar: "+tableroNumeros[i][j]);
			//System.out.println("dominio de la posición: "+dominioTotal[i][j]);
			n = ConvertToBinary((p-1));
			//System.out.println("numero convertido: "+n);
			//System.out.println("funcion AND: "+(n&dominioTotal[i][j]));
			if((n&dominioTotal[fila][columna])!=n){
				//System.out.println("no va aquí "+tableroNumeros[i][j]+" fila "+(i+1)+" columna "+(j+1)+" dominiocasilla "+dominioTotal[i][j]+" n "+n+" AND "+(dominioTotal[i][j]&n));
				h++;
			}
		}
		return h;
	}

	
	public int ConvertToBinary(int numero){// funcion que da el valor respectivo a un entero del 0 a 9
		return (int) Math.pow(2, numero);
	}

	public Tablero getTablerito() {
		return tablerito;
	}

	public void setTablerito(Tablero tablerito) {
		this.tablerito = tablerito;
	}

}
