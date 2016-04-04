package IA;

import Sudoku.Tablero;

public class Heuristica {
	
	private Tablero tablerito;
	
	
	public float h(Tablero t){// funcion heuristica que calcula el valor de los numero que no están la posicion correcta
		boolean[][] tableroEstaticos = t.getTableroEstaticos();// tablero de los valores estaticos 
		int[][] tableroNumeros = t.getTableroNumeros();
		int[][] dominioTotal = t.getDominioTotal();
		float h = 0; // variable auxiliar que guardara el valor heuristico
		int n = 0; // variable auxiliar para guardar el valor que se evaluara en el dominio
		for (int i = 0; i < tableroEstaticos.length; i++) {
			for (int j = 0; j < tableroEstaticos[i].length; j++) {
				if(!tableroEstaticos[i][j]){
					n = ConvertToBinary((tableroNumeros[i][j]-1));
					if((n&dominioTotal[i][j])!=n){
						h++;
					}
				}
			}
		}
		// heuristica que encuentra los digitos que falte en las columnas o cuadros 
		t.ReiniciarDominios();
		t.CrearDominioColumnas();
		t.CrearDominioCuadros();
		t.CrearDominioFilas();
		int[][] dFilas = t.getDominioFilas();
		int[][] dColumnas = t.getDominioColumnas();
		int[][] dCuadros = t.getDominioCuadros();
		for (int i = 0; i < dCuadros.length; i++) {
			for (int j = 0; j < dCuadros[i].length; j++) {
					if(dFilas[i][j]==0){
						h+=1;
					}
					if(dColumnas[i][j]==0){// cada vez que en el dominio de la columna encuentre un cero, es porque falta un numero por agregar y habrá algún repetido
						h+=1;
					}
					if(dCuadros[i][j]==0){// cada vez que en el dominio del cuadrado encuentre un cero, es porque falta un numero por agregar y habrá algún repetido
						h+=1;
					}
			}
		}
		t.setH(h);// se divide por 5, ya que es el número de dominios afectados para que la heuristica sea admisible
		return h/5;
	}
	
	public int Disponibilidad(Tablero t,int fila, int columna,int p){// funcion heuristica que calcula el valor de los numero que no están la posicion correcta
		boolean[][] tableroEstaticos = t.getTableroEstaticos();// tablero de los valores estaticos 
		int[][] dominioTotal = t.getDominioTotal();
		int h = 0; // variable auxiliar que guardara el valor heuristico
		int n = 0; // variable auxiliar para guardar el valor que se evaluara en el dominio
		if(!tableroEstaticos[fila][columna]){
			n = ConvertToBinary((p-1));
			if((n&dominioTotal[fila][columna])!=n){
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
