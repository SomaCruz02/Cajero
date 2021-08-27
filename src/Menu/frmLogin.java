
package Menu;

import Metodos.user;
import Menu.frmMenu_Administrador;
import Menu.frmMenu_usuarios;
import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Formatter;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;


public class frmLogin extends javax.swing.JFrame {
    
    String barra = File.separator;      //Separador de la maquina 
    
    String ruta = System.getProperty("user.dir")+barra+"src"+barra+"archivos"+barra;        //Ruta principal
    String rutaUsuarios = ruta+"usuarios"+barra;        
    String rutaCajero = ruta+"cajero"+barra;
    String rutaLog = ruta+"log"+barra;
    
    File contenedor = new File(rutaUsuarios);       
    File[] registros = contenedor.listFiles();      //Encuentra los registros de usuarios

    public user O;
    public ArrayList<user> a = new ArrayList<user>();
    
    public frmLogin() {
        initComponents();
        this.setLocationRelativeTo(null);
        setResizable(false);
        
        //ordenarPorNumero(registros);        //Ordenar el Array de texto
        
        for(int i=0;i<registros.length;i++){
        
            File url = new File(rutaUsuarios+registros[i].getName());
            try{
                FileInputStream fis = new FileInputStream(url);     //abrir archivo
                Properties mostrar = new Properties();      //conseguir propiedades
                mostrar.load(fis);          //cargar las propiedades           

                O = new user(mostrar.getProperty("Nombre"),     //Iniciar lista
                                    mostrar.getProperty("Tarjeta"),
                                    mostrar.getProperty("PIN"),
                                    Integer.parseInt(mostrar.getProperty("Limite")),
                                    Integer.parseInt(mostrar.getProperty("Saldo")),
                                    mostrar.getProperty("Tipo"));
                a.add(O);

            }catch(Exception e){}
            
        }
        
    }
    
        
    /*public void ordenarPorNumero(File[] str) {
        Arrays.sort(str, new Comparator<File>() {
                public int compare(File o1, File o2) {
                    int n1 = Extraer(o1.getName());
                    int n2 = Extraer(o2.getName());
                    return n1 - n2;
                }

                private int Extraer(String n) {
                    int i = 0;
                    try {
                        int s = n.indexOf('r')+1;
                        int e = n.lastIndexOf('.');
                        String num = n.substring(s, e);
                        i = Integer.parseInt(num);
                    } catch(Exception e) {
                       i = 0; 
                    }
                    return i;
                }
            }
        );
    }*/
    
    private void Login(int i){
        File contenedor = new File(rutaLog);
        File[] registros = contenedor.listFiles();
        
        String archivo = "log"+registros.length+".txt";     //crear un nuevo archivo
        File crea_ubicación = new File(rutaLog);
                
        try{    
            
            crea_ubicación.mkdirs();        
            Formatter crear = new Formatter(rutaLog+archivo);
            
            crear.format("%s\r\n%s\r\n%s\r\n%s\r\n%s\r\n%s",
                    "Nombre="+a.get(i).Nombre,
                    "Tarjeta="+a.get(i).Tarjeta,
                    "PIN="+a.get(i).Pin,
                    "Limite="+a.get(i).limite,
                    "Fecha="+fecha("/"),
                    "Hora="+Hora());
            
            crear.close();
            
        }catch(Exception e){}     
        
    }
    
    private void Crear(int i){
        String archivo = ("record")+".txt";     //nombre del archivo .txt
        File crea_ubicación = new File(ruta);       //ubicación del archivo
                
        try{    
            
            crea_ubicación.mkdirs();        //Crear archivo
            Formatter crear = new Formatter(ruta+archivo);      //Crear Archivo de Texto      
            
            crear.format("%s\r\n%s\r\n%s\r\n%s\r\n%s\r\n%s",        //Darle un formato
                    "Nombre="+a.get(i).Nombre,      //obtener datos
                    "Tarjeta="+a.get(i).Tarjeta,
                    "PIN="+a.get(i).Pin,
                    "Limite="+a.get(i).limite,
                    "Fecha="+fecha("/"),
                    "Hora="+Hora());
            
            crear.close();      //Cerrar y guardar
            
        }catch(Exception e){}     
        
    }
    
    public String fecha(String s){
        return ZonedDateTime.now().getDayOfMonth()+s+ZonedDateTime.now().getMonthValue()+s+ZonedDateTime.now().getYear();
    }
    
    public String Hora(){
        return ZonedDateTime.now().getHour()+":"+ZonedDateTime.now().getMinute()+":"+ZonedDateTime.now().getSecond();
    }

    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblTarjeta = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txtTarjeta = new javax.swing.JTextField();
        lblTarjeta1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        txtPin = new javax.swing.JPasswordField();
        checkbox = new javax.swing.JCheckBox();
        btnAceptar = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        lblTarjeta2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblTarjeta.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblTarjeta.setText("PIN");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtTarjeta.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtTarjeta.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTarjeta.setBorder(null);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtTarjeta, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTarjeta)
                .addContainerGap())
        );

        lblTarjeta1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblTarjeta1.setText("Número de Tarjeta ");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtPin.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtPin.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPin.setBorder(null);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtPin, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtPin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        checkbox.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        checkbox.setText("Administración");

        btnAceptar.setBackground(new java.awt.Color(0, 0, 204));
        btnAceptar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnAceptar.setForeground(new java.awt.Color(255, 255, 255));
        btnAceptar.setText("Ingresar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(51, 51, 51));

        lblTarjeta2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblTarjeta2.setForeground(new java.awt.Color(255, 255, 255));
        lblTarjeta2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTarjeta2.setText("CAJERO AUTOMÁTICO");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTarjeta2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTarjeta2, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(checkbox))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(lblTarjeta1)
                        .addGap(50, 50, 50))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(lblTarjeta)
                        .addGap(121, 121, 121)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblTarjeta1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTarjeta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(checkbox)
                .addGap(41, 41, 41)
                .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
                
        String T = txtTarjeta.getText();
        String P = txtPin.getText();
        String Error1 = "";
        File f = new File(rutaCajero+fecha("-")+".txt");
        
        for(int i = 0; i<a.size();i++){
            if(a.get(i).Tarjeta.equals(T.toUpperCase())){      //Verificar que la tarjeta existe
                if(a.get(i).Pin.equals(P.toUpperCase())){      //Verificar que el pin existe en la lista
                    if(checkbox.isSelected()==true){     
                        if(a.get(i).tipo.equals("admin")){     //Si la cuenta es admin
                            frmMenu_Administrador window = new frmMenu_Administrador();
                            this.setVisible(false);
                            window.setVisible(true);
                        }else{
                             showMessageDialog(null, "Cuenta sin los permisos necesarios");
                        }
                    }else{
                        if (f.exists()) {       //Si se ha iniciado cajero
                            Crear(i);
                            Login(i);
                            frmMenu_usuarios window = new frmMenu_usuarios();
                            this.setVisible(false);
                            window.setVisible(true);
                        }else{
                            showMessageDialog(null, "Cajero no inicializado");
                        }
                    }
                 
                }else{
                    showMessageDialog(null, "PIN incorrecto");
                }
            }
        }
    }//GEN-LAST:event_btnAceptarActionPerformed


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
            java.util.logging.Logger.getLogger(frmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JCheckBox checkbox;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel lblTarjeta;
    private javax.swing.JLabel lblTarjeta1;
    private javax.swing.JLabel lblTarjeta2;
    private javax.swing.JPasswordField txtPin;
    private javax.swing.JTextField txtTarjeta;
    // End of variables declaration//GEN-END:variables
}
