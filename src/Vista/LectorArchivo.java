package Vista;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class LectorArchivo {
	
	public int[][] leerArchivo(){
        int [][] estadoInicial = new int[9][9];
        String texto;
        String file = "";
        JFileChooser JFC = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos de texto","txt","TXT");
        JFC.setFileFilter(filtro);
        String usuario = System.getProperty("user.name");//Obtenemos nombre de usuario
        JFC.setCurrentDirectory(new File("C:/Users/"+usuario+"/Desktop")); //donde se quiere empezar a buscar el archivo
        int abrir = JFC.showDialog(null, "Abrir"); 
        try {
			file = JFC.getSelectedFile().getName();
		} catch (NullPointerException e) {
			// TODO Bloque catch generado automáticamente
			//error al cancelar
		}

        if(abrir == JFileChooser.APPROVE_OPTION){
                File archivo = JFC.getSelectedFile();
                file = JFC.getSelectedFile().getAbsolutePath();
                try {
                        FileReader fr = new FileReader(archivo);
                        BufferedReader bf = new BufferedReader(fr);
                        String strLinea;
                        int i=0;
                        while ((strLinea = bf.readLine()) != null){
                                texto = String.valueOf(strLinea);//obtenemos linea por linea
                                for(int j=0; j<9; j++){
                                    int ingresar = texto.charAt(j)-48; //para poder convertir a entero
                                    estadoInicial [i][j] = ingresar; //ingresamos el numero
                                }
                            i++;    
                        }
                fr.close();
                bf.close();
                } catch (IOException e2) {
                        JOptionPane.showMessageDialog(null, "Error Al Cargar Archivo");
                } catch (StringIndexOutOfBoundsException e) {//Por si ahi letras en el archivo
					// TODO: handle exception
                	JOptionPane.showMessageDialog(null, "Error al cargar el archivo \nFormato Inválido", "Error Al Cargar El Archivo", JOptionPane.ERROR_MESSAGE);
                	for ( int z=0; z < estadoInicial.length; z++ ) { 
                        for ( int j=0; j<estadoInicial[z].length; j++ ){
                            estadoInicial[z][j] = 0;//rellenamos de 0 la matriz para que no de error
                    }
				}
           }
     }
        return estadoInicial;
    }

}
