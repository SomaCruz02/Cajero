/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menu;

import Metodos.user;
import java.io.File;
import java.util.ArrayList;
import java.io.File;
import java.io.FileInputStream;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Formatter;
import java.util.Properties;
import javax.swing.JFrame;
import Metodos.ulog;
import Metodos.user;
import java.awt.Color;
import static javax.swing.JOptionPane.showMessageDialog;


public class frmPIN extends javax.swing.JFrame {
    
    String barra = File.separator;      //Separador de la maquina 
    
    String ruta = System.getProperty("user.dir")+barra+"src"+barra+"archivos"+barra;        //Ruta principal
    String rutaUsuarios = ruta+"usuarios"+barra;        
    String rutaCambio = ruta+"cambios"+barra;
    
    File contenedor = new File(rutaUsuarios);       
    File[] registros = contenedor.listFiles();      //Encuentra los registros de usuarios
    
    public user O;
    public ulog P;
    public ArrayList<user> a = new ArrayList<user>();
    public ArrayList<ulog> b = new ArrayList<ulog>();
    public int aux = 0;


    public frmPIN() {
        initComponents();
        this.setLocationRelativeTo(null);
        setResizable(false);
        this.getContentPane().setBackground(java.awt.Color.LIGHT_GRAY);
        
        ordenarPorNumero(registros);
        
        File s = new File(ruta+"record.txt");
        if(s.exists()){
            
            try{
                FileInputStream fis1 = new FileInputStream(s);
                Properties mostrar1 = new Properties();
                mostrar1.load(fis1);               

                P = new ulog(mostrar1.getProperty("Nombre"),
                        mostrar1.getProperty("Tarjeta"),
                        mostrar1.getProperty("PIN"),
                        Integer.parseInt(mostrar1.getProperty("Limite")),
                        mostrar1.getProperty("Fecha"),
                        mostrar1.getProperty("Hora"));
                b.add(P);

            }catch(Exception e){}
            
            tarjeta.setText(b.get(0).Tarjeta);
        }
            
            //SE REGISTRAN LOS DATOS DE LOS USUARIOS REGISTRADOS
        for(int i=0;i<registros.length;i++){

                File url = new File(rutaUsuarios+registros[i].getName());
                try{
                    FileInputStream fis = new FileInputStream(url);
                    Properties mostrar = new Properties();
                    mostrar.load(fis);               

                    O = new user(mostrar.getProperty("Nombre"),
                                mostrar.getProperty("Tarjeta"),
                                mostrar.getProperty("PIN"),
                                Integer.parseInt(mostrar.getProperty("Limite")),
                                Integer.parseInt(mostrar.getProperty("Saldo")),
                                mostrar.getProperty("Tipo"));
                a.add(O);

            }catch(Exception e){}

            //SE COMPARA LA EXISTENCIA DE LA TARJETA PARA OBTENER EL AUXILIAR DEL ARCHIVO DE USUARIO
                if(b.get(0).Tarjeta.equals(a.get(i).Tarjeta)){
                    aux = i+1;
                    System.out.println("EXISTENCIA DE LA TARJETA");
                }
        }
    }

        public void ordenarPorNumero(File[] str) {
        Arrays.sort(str, new Comparator<File>() {
                public int compare(File o1, File o2) {
                    int n1 = Extraer(o1.getName());
                    int n2 = Extraer(o2.getName());
                    return n1 - n2;
                }

                private int Extraer(String name) {
                    int i = 0;
                    try {
                        int s = name.indexOf('r')+1;
                        int e = name.lastIndexOf('.');
                        String number = name.substring(s, e);
                        i = Integer.parseInt(number);
                    } catch(Exception e) {
                       i = 0; 
                    }
                    return i;
                }
            }
        );
    }
        
    /*System.out.println("pin : "+a.get(aux-1).Pin);
    System.out.println("Nombre : "+a.get(aux-1).Nombre);
    System.out.println("Tarjeta : "+a.get(aux-1).Tarjeta);
    System.out.println("Limite : "+a.get(aux-1).limite);  */  
    private void Crear(){
        String archivo = ("user")+(aux)+".txt";
        File crea_ubicación = new File(rutaUsuarios);
                
        try{    
            crea_ubicación.mkdirs();
            Formatter crear = new Formatter(rutaUsuarios+archivo);
            crear.format("%s\r\n%s\r\n%s\r\n%s\r\n%s\r\n%s",
                    "Nombre="+a.get(aux-1).Nombre,
                    "Tarjeta="+a.get(aux-1).Tarjeta,
                    "PIN="+a.get(aux-1).Pin.toUpperCase(),
                    "Limite="+a.get(aux-1).limite,
                    "Saldo="+a.get(aux-1).saldo,
                    "Tipo="+a.get(aux-1).tipo);
            crear.close();
            
        }catch(Exception e){}     
        
    }
    
    private void CrearCambios(){

        File contenedor = new File(rutaCambio);
        File[] registros = contenedor.listFiles();
        String archivo = "cambio"+(registros.length + 1)+".txt";

        try{    
            contenedor.mkdirs();
            Formatter crear = new Formatter(rutaCambio+archivo);
            crear.format("%s\r\n%s\r\n%s\r\n%s",
                    "Tarjeta="+a.get(aux-1).Tarjeta,
                    "Fecha="+fecha(),
                    "PIN="+txtpin.getText(),
                    "NuevoPIN="+txtnpin.getText().toUpperCase());
            crear.close();

        }catch(Exception e){} 

    }
    
    public String fecha(){
        return ZonedDateTime.now().getDayOfMonth()+"-"+ZonedDateTime.now().getMonthValue()+"-"+ZonedDateTime.now().getYear();
    }
        


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        txtTarjeta1 = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        tarjeta = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        txtpin = new javax.swing.JTextField();
        btnAceptar = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        txtnpin = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        txtnpin1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtTarjeta1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtTarjeta1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTarjeta1.setBorder(null);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtTarjeta1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTarjeta1)
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(102, 102, 102));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(51, 51, 51));

        tarjeta.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        tarjeta.setForeground(new java.awt.Color(255, 255, 255));
        tarjeta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tarjeta.setText("Tarjeta");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("No. Tarjeta");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tarjeta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tarjeta)
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtpin.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtpin.setForeground(new java.awt.Color(204, 204, 204));
        txtpin.setText("PIN ACTUAL");
        txtpin.setBorder(null);
        txtpin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtpinMousePressed(evt);
            }
        });
        txtpin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtpinActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtpin, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtpin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnAceptar.setBackground(new java.awt.Color(255, 153, 0));
        btnAceptar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnAceptar.setForeground(new java.awt.Color(255, 255, 255));
        btnAceptar.setText("ACEPTAR");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(51, 51, 51));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("CANCELAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtnpin.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtnpin.setForeground(new java.awt.Color(204, 204, 204));
        txtnpin.setText("NUEVO PIN");
        txtnpin.setBorder(null);
        txtnpin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtnpinMousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtnpin, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtnpin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtnpin1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtnpin1.setForeground(new java.awt.Color(204, 204, 204));
        txtnpin1.setText("CONFIRMAR PIN");
        txtnpin1.setBorder(null);
        txtnpin1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtnpin1MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtnpin1, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtnpin1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Cambiar PIN");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAceptar)
                    .addComponent(jButton2))
                .addContainerGap(43, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtpinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtpinActionPerformed

    }//GEN-LAST:event_txtpinActionPerformed

    private void txtpinMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtpinMousePressed
        String pin = txtpin.getText();
        if(pin.equals("PIN ACTUAL")){
            txtpin.setText("");
            txtpin.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_txtpinMousePressed

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        String pin = txtpin.getText();
        String pin2 = txtnpin.getText();
        String pin3 = txtnpin1.getText();
        if(pin.isEmpty()){
            txtpin.setText("PIN ACTUAL");
            txtpin.setForeground(Color.LIGHT_GRAY);
        }
        if(pin2.isEmpty()){
            txtnpin.setText("NUEVO PIN");
            txtnpin.setForeground(Color.LIGHT_GRAY);
        }
        if(pin3.isEmpty()){
            txtnpin1.setText("CONFIRMAR PIN");
            txtnpin1.setForeground(Color.LIGHT_GRAY);
        }
    }//GEN-LAST:event_formMousePressed

    private void txtnpinMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtnpinMousePressed
        String pin = txtnpin.getText();
        if(pin.equals("NUEVO PIN")){
            txtnpin.setText("");
            txtnpin.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_txtnpinMousePressed

    private void txtnpin1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtnpin1MousePressed
        String pin = txtnpin1.getText();
        if(pin.equals("CONFIRMAR PIN")){
            txtnpin1.setText("");
            txtnpin1.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_txtnpin1MousePressed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        frmMenu_usuarios x = new frmMenu_usuarios();
        this.setVisible(false);
        x.setVisible(true); // Regresar al menu principal 
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        String pin = txtpin.getText();
        String pin1 = txtnpin.getText();
        String pin2 = txtnpin1.getText();
        
        if(pin.length()>4 || pin1.length()>4 || pin2.length()>4) {
            showMessageDialog(null, "PIN debe contener un total de 4 caracteres");
        }else if(pin1.equals(pin2)){
            if(pin.toUpperCase().equals(a.get(aux-1).Pin)){
                a.get(aux-1).Pin = pin1.toUpperCase();    
                Crear();
                CrearCambios();
                showMessageDialog(null, "Cambio de PIN exitoso");
            }
        }else{
            showMessageDialog(null, "Confirmar PIN");
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
            java.util.logging.Logger.getLogger(frmPIN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmPIN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmPIN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmPIN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmPIN().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JLabel tarjeta;
    private javax.swing.JTextField txtTarjeta1;
    private javax.swing.JTextField txtnpin;
    private javax.swing.JTextField txtnpin1;
    private javax.swing.JTextField txtpin;
    // End of variables declaration//GEN-END:variables
}
