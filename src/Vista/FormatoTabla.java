package Vista;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JFormattedTextField;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;

public class FormatoTabla implements TableCellRenderer{

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        
        //campoTexto nos sirve como componente dentro de la tabla, es cada una de las celdas de la misma
        JFormattedTextField campoTexto = new JFormattedTextField();
        //Se establece primeramente un borde
        campoTexto.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        if(row<9 && column<9){ //color blanco de fondo para todas las columnas
        	campoTexto.setBackground(Color.WHITE);
        }if((row==0 || row ==1 || row == 2) && (column==3 || column==4 || column==5)){
            campoTexto.setBackground(Color.LIGHT_GRAY);// pintamos de gris algunas casillas
        }if((row==3 || row ==4 || row == 5) && (column==0 || column==1 || column==2 || column ==6 || column ==7 || column==8)){
            campoTexto.setBackground(Color.LIGHT_GRAY);// pintamos de gris algunas casillas
        }if((row==6 || row ==7 || row ==8) && (column==3 || column==4 || column==5)){
            campoTexto.setBackground(Color.LIGHT_GRAY);// pintamos de gris algunas casillas
        }
        if(value instanceof Integer){// para los numeros del estado inicial o estaticos
            campoTexto.setText(value+"");
            campoTexto.setHorizontalAlignment(SwingConstants.CENTER);
            Font f = new Font( "Poor Richard", Font.BOLD,22 );
            campoTexto.setFont(f);
            campoTexto.setForeground(Color.RED);
        }if(value instanceof String){// para los numeros encontrados en la solucion
            campoTexto.setText(value+"");
            campoTexto.setHorizontalAlignment(SwingConstants.CENTER);
            Font f = new Font( "Poor Richard", Font.PLAIN,22 );
            campoTexto.setFont(f);
            campoTexto.setForeground(Color.BLACK);
        }
        //Por último se devuelve el componente ya con el formato establecido para mostrarlo en la tabla
        return campoTexto;
    }

	
}
