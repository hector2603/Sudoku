package Vista;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableColumnModel;

import IA.Busqueda;
import Sudoku.Tablero;

public class Principal extends javax.swing.JFrame{
	
	//definimos lo necesario para la interfaz
	private javax.swing.JLabel alturaArbol;
    private javax.swing.JLabel costoSolucion;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton leerTexto;
    private javax.swing.JLabel logoU;
    private javax.swing.JLabel nodosAgregados;
    private javax.swing.JLabel nodosCreados;
    private javax.swing.JLabel nodosDescartados;
    private javax.swing.JLabel nodosExpandidos;
    private javax.swing.JLabel nombre1;
    private javax.swing.JLabel nombre2;
    private javax.swing.JLabel nombre3;
    private javax.swing.JButton solucion;
    private javax.swing.JTable tablero;
    private javax.swing.JLabel titulo;
    FormatoTabla formato = new FormatoTabla();
    int [][] tableroInicial = new int[9][9];
    
    public Principal() {
        initComponents(); //metodo que contiene los elementos de la interfaz
        setSize(760, 580);  //damos el tamaño a la ventana
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Sudoku");
        llenarTabla(tableroInicial);
        
        tablero.setDefaultRenderer(String.class, formato); //damos formato a los numeros para los diferentes colores
        tablero.setDefaultRenderer(Integer.class, formato);
    }
    
    public void llenarTabla(int[][] estado){ // funcion que llena la tabla con el estado inicial
        tablero.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        TableColumnModel columnModel = tablero.getColumnModel();
        for (int i = 0; i < columnModel.getColumnCount(); i++) {
        columnModel.getColumn(i).setPreferredWidth(44);
        }
        tablero.setRowHeight(30);// damos tamaño predeterminaro a las celdas del tablero
        for ( int z=0; z < estado.length; z++ ) { 
            for ( int j=0; j<estado[z].length; j++ ){
                if(estado[z][j] != 0){
                tablero.setValueAt(estado[z][j], z, j); // mostramos en pantalla los numeros leidos del archivo de texto
                }
            }
        }
    }
    
    public void limpiarVista(){ // funcion para limpiar la tabla cuando se requiera desarrollar otro sudoku
    	for ( int z=0; z < 9; z++ ) { 
            for ( int j=0; j<9; j++ ){
                tablero.setValueAt(null, z, j);
            }
        }
    	nodosAgregados.setText(null);
    	nodosCreados.setText(null);
    	nodosDescartados.setText(null);
    	nodosExpandidos.setText(null);
    	costoSolucion.setText(null);
    	alturaArbol.setText(null);
    }
    
    public void llenarTabla2(boolean[][] estadoBoleanos, int[][] tableroFinal){ // funcion para llenar
        tablero.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);//la tabla despues de tener resu
        TableColumnModel columnModel = tablero.getColumnModel();//dando diferencia en el color del estado inicial con
        for (int i = 0; i < columnModel.getColumnCount(); i++) {//la solucion encontrada
        columnModel.getColumn(i).setPreferredWidth(44);
        }
        tablero.setRowHeight(30);
        
        for ( int z=0; z < tableroFinal.length; z++ ) { 
            for ( int j=0; j<tableroFinal[z].length; j++ ){
                if(estadoBoleanos[z][j] == true && tableroFinal[z][j] != 0){
                tablero.setValueAt(tableroFinal[z][j], z, j);//numeros estaticos, se pintan de rojo
                }if (estadoBoleanos[z][j]== false && tableroFinal[z][j] !=0){
                	String resultado = tableroFinal[z][j]+"";//numeros encontrados en la solucion, se pasan
                	tablero.setValueAt(resultado, z, j);//a String para poder darle otro color al imprimirlo
                }
            }
        }
    }
    //clase con lo necesario para inicializar la interfaz
    private void initComponents() {

        titulo = new javax.swing.JLabel();
        logoU = new javax.swing.JLabel();
        nombre1 = new javax.swing.JLabel();
        nombre2 = new javax.swing.JLabel();
        nombre3 = new javax.swing.JLabel();
        leerTexto = new javax.swing.JButton();
        solucion = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablero = new javax.swing.JTable();
        nodosCreados = new javax.swing.JLabel();
        nodosExpandidos = new javax.swing.JLabel();
        nodosAgregados = new javax.swing.JLabel();
        nodosDescartados = new javax.swing.JLabel();
        costoSolucion = new javax.swing.JLabel();
        alturaArbol = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        titulo.setFont(new java.awt.Font("Poor Richard", 0, 24)); // NOI18N
        titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo.setText("Proyecto Inteligencía Artificial - Sudoku");
        getContentPane().add(titulo);
        titulo.setBounds(160, 21, 367, 28);

        logoU.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logoU2.png"))); // NOI18N
        getContentPane().add(logoU);
        logoU.setBounds(690, 460, 50, 72);

        nombre1.setFont(new java.awt.Font("Tw Cen MT", 0, 14)); // NOI18N
        nombre1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nombre1.setText("Hector Fabio Ocampo Arbelaez - 1355858");
        getContentPane().add(nombre1);
        nombre1.setBounds(430, 470, 249, 16);

        nombre2.setFont(new java.awt.Font("Tw Cen MT", 0, 14)); // NOI18N
        nombre2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nombre2.setText("Carlos Andrés Riascos Pareja - 1356541");
        getContentPane().add(nombre2);
        nombre2.setBounds(430, 490, 234, 16);

        nombre3.setFont(new java.awt.Font("Tw Cen MT", 0, 14)); // NOI18N
        nombre3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nombre3.setText("Luis Fernando Quintero Castaño - 1663714");
        getContentPane().add(nombre3);
        nombre3.setBounds(430, 510, 248, 16);

        leerTexto.setFont(new java.awt.Font("Poor Richard", 0, 14)); // NOI18N
        leerTexto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Logotxt.png"))); // NOI18N
        leerTexto.setText("Leer Archivo De Texto");
        leerTexto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                leerTextoActionPerformed(evt);
            }
        });
        getContentPane().add(leerTexto);
        leerTexto.setBounds(480, 120, 205, 41);

        solucion.setFont(new java.awt.Font("Poor Richard", 0, 18)); // NOI18N
        solucion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/LogoSolucion.png"))); // NOI18N
        solucion.setText("Solucionar  Sudoku");
        solucion.setEnabled(false);
        solucion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                solucionActionPerformed(evt);
            }
        });
        getContentPane().add(solucion);
        solucion.setBounds(480, 230, 205, 47);

        tablero.setAutoCreateRowSorter(true);
        tablero.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        tablero.setFont(new java.awt.Font("Poor Richard", 0, 24)); // NOI18N
        tablero.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "S", "U", "D", "O", "K", "U", "- ", "I", "A"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablero.setAlignmentX(1.0F);
        tablero.setAlignmentY(1.0F);
        tablero.setEnabled(false);
        tablero.setIntercellSpacing(new java.awt.Dimension(0, 0));
        jScrollPane1.setViewportView(tablero);
        if (tablero.getColumnModel().getColumnCount() > 0) {
            tablero.getColumnModel().getColumn(0).setResizable(false);
            tablero.getColumnModel().getColumn(1).setResizable(false);
            tablero.getColumnModel().getColumn(2).setResizable(false);
            tablero.getColumnModel().getColumn(3).setResizable(false);
            tablero.getColumnModel().getColumn(4).setResizable(false);
            tablero.getColumnModel().getColumn(5).setResizable(false);
            tablero.getColumnModel().getColumn(6).setResizable(false);
            tablero.getColumnModel().getColumn(7).setResizable(false);
            tablero.getColumnModel().getColumn(8).setResizable(false);
        }

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(40, 80, 402, 305);

        nodosCreados.setFont(new java.awt.Font("Poor Richard", 0, 14)); // NOI18N
        nodosCreados.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(nodosCreados);
        nodosCreados.setBounds(20, 400, 340, 20);

        nodosExpandidos.setFont(new java.awt.Font("Poor Richard", 0, 14)); // NOI18N
        nodosExpandidos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(nodosExpandidos);
        nodosExpandidos.setBounds(20, 420, 340, 20);

        nodosAgregados.setFont(new java.awt.Font("Poor Richard", 0, 14)); // NOI18N
        nodosAgregados.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(nodosAgregados);
        nodosAgregados.setBounds(20, 440, 340, 20);

        nodosDescartados.setFont(new java.awt.Font("Poor Richard", 0, 14)); // NOI18N
        nodosDescartados.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(nodosDescartados);
        nodosDescartados.setBounds(20, 460, 340, 20);

        costoSolucion.setFont(new java.awt.Font("Poor Richard", 0, 14)); // NOI18N
        costoSolucion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(costoSolucion);
        costoSolucion.setBounds(20, 480, 340, 20);

        alturaArbol.setFont(new java.awt.Font("Poor Richard", 0, 14)); // NOI18N
        alturaArbol.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(alturaArbol);
        alturaArbol.setBounds(20, 500, 340, 20);

        pack();
    }

    //oyente para leer archivo de texto
    private void leerTextoActionPerformed(java.awt.event.ActionEvent evt) {                                          
        // TODO add your handling code here:
    	limpiarVista();
        LectorArchivo lA = new LectorArchivo();
        solucion.setEnabled(true);
        tableroInicial = lA.leerArchivo();
        llenarTabla(tableroInicial);
    }
    
    //oyente para el boton solucionar
    private void solucionActionPerformed(java.awt.event.ActionEvent evt) {                                          
        // TODO add your handling code here:
        Tablero tablerito = new Tablero(tableroInicial);
        //se verifica que el sudoku tenga solucion y no este mal planteado
        if(tablerito.verificarSudoku(tableroInicial) == true){
        	Busqueda buscadorcito = new Busqueda(tablerito);
    		buscadorcito.EmpezarBusqueda();
    		// después de haber encontrado la solucion
    		Tablero tablerito2 = buscadorcito.getListaPrioridad().getHead().getKey();
    		llenarTabla2(tablerito.getTableroEstaticos(), tablerito2.getTableroNumeros());
    		tablerito2.printNumeros(tablerito2.getTableroNumeros());
    		nodosCreados.setText("Número  De  Nodos  Creados:  "+ buscadorcito.contadorCreados);
    		nodosExpandidos.setText("Número  De  Nodos  Expandidos:  "+ buscadorcito.contadorExpandidos);
    		nodosAgregados.setText("Número  De  Nodos  Agregados  Al  Árbol:  "+ (buscadorcito.contadorCreados - buscadorcito.contadorDescartados));
    		nodosDescartados.setText("Número  De  Nodos  Descartados:  "+ buscadorcito.contadorDescartados);
    		costoSolucion.setText("Costo  Total  De  La  Solución:  " + tablerito2.getG());
    		alturaArbol.setText("Altura  Del  Árbol:  "+tablerito2.getAltura());
    		solucion.setEnabled(false);
        }else{
        	JOptionPane.showMessageDialog(null, "El Sudoku No Tiene Solución \nEsta Mal Planteado", "Sudoku Mal Planteado", JOptionPane.INFORMATION_MESSAGE );
        	solucion.setEnabled(false);
        }
		
    }

    //main principal
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }
}
