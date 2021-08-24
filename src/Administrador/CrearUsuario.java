/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Administrador;

import Menu.frmMenu_Administrador;
import Metodos.lim;

import java.io.File;
import java.util.ArrayList;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Formatter;
import java.util.Properties;
import javax.swing.JOptionPane;
import Metodos.user;
/**
 *
 * @author kevin
 */
public class CrearUsuario extends javax.swing.JFrame {

    String barra = File.separator;      //Separador de la maquina 
    String ruta = System.getProperty("user.dir")+barra+"src"+barra+"archivos"+barra+"usuarios"+barra;        //Ruta principal
    String ubi2 = System.getProperty("user.dir")+barra+"src"+barra+"archivos"+barra;
    String ubi4 = ubi2+"usuarios"+barra;
    String ubi5 = ubi2+"limite"+barra;
    
    File contenedor = new File(ruta);       
    File[] registros = contenedor.listFiles();      //Encuentra los registros de usuarios

    File contenedor2 = new File(ubi5);
    
    public user O;
    public ArrayList<user> a = new ArrayList<user>();
    public ArrayList<user> c = new ArrayList<user>();
    public ArrayList<lim> d = new ArrayList<lim>();
    public int auxiliar = 0;
     public void ordenarPorNumero(File[] str) {    // Ordena los usuarios comparandolos unos con otros
        Arrays.sort(str, new Comparator<File>() {
                public int compare(File o1, File o2) {
                    int n1 = Extraer(o1.getName());
                    int n2 = Extraer(o2.getName());
                    return n1 - n2;
                }

                private int Extraer(String name) { // Obtiene el nombre de los usuarios
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
    
    /** Creates new form CrearUsuario */
    public CrearUsuario() {
        initComponents();
        this.setLocationRelativeTo(null);
        setResizable(false);
        this.getContentPane().setBackground(java.awt.Color.WHITE); // Aspectos Graficos del Frame CrearUsuario
        
        ordenarPorNumero(registros); //ordena los usuarios mediante el metodo
        
        for( auxiliar=0;auxiliar<registros.length;auxiliar++){
        
            File url = new File(ruta+registros[auxiliar].getName());
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
                a.add(O);                                                               // obtiene los datos del constructor 

            }catch(Exception e){}
            
        }
            
    }

    private void Crear(){
        String archivo = ("user")+(a.size()+1)+".txt";
        File crea_ubicación = new File(ruta);   // Crea un archivo para almacenar los datos de un nuevo usuario 
                
        try{    
            
            crea_ubicación.mkdirs();
            Formatter crear = new Formatter(ruta+archivo);          // Crea un archivo con los datos escritos
            crear.format("%s\r\n%s\r\n%s\r\n%s\r\n%s\r\n%s",
                    "Nombre="+nombre.getText(),
                    "Tarjeta="+notarjeta.getText().toUpperCase(),
                    "PIN="+pin.getText().toUpperCase(),
                    "Limite="+montolimite.getValue(),
                    "Saldo="+montoactual.getValue(),
                    "Tipo=user");
            crear.close();

            O = new user(
                    nombre.getText(),
                    notarjeta.getText(),
                    pin.getText(),
                    Integer.parseInt(montolimite.getValue().toString()),
                    Integer.parseInt(montoactual.getValue().toString()),
                    "user");
            
            a.add(O);
            
        }catch(Exception e){}     
       modLim((int) montolimite.getValue());
    }
    
//    private void modUser(int nSaldo){       //Modificar la información del usuario   
//        String archivo = "user"+(auxiliar+1)+".txt";
//        File crea_ubicación = new File(ubi4);
//                
//        try{    
//            
//            crea_ubicación.mkdirs();
//            Formatter crear = new Formatter(ubi4+archivo);
//            
//            crear.format("%s\r\n%s\r\n%s\r\n%s\r\n%s\r\n%s",
//                    "Nombre="+c.get(0).Nombre,
//                    "Tarjeta="+c.get(0).Tarjeta,
//                    "PIN="+c.get(0).Pin,
//                    "Limite="+c.get(0).limite,
//                    "Saldo="+(c.get(0).saldo-nSaldo),
//                    "Tipo="+c.get(0).tipo);
//            
//            crear.close();
//            
//        }catch(Exception e){}    
//        modLim(nSaldo);
//    }
    
     private void modLim(int nSaldo){        //Actualiza el límite
        String archivo = "user"+(auxiliar+1)+".txt";
        File crea_ubicación = new File(ubi5);
                
        try{    
            
            crea_ubicación.mkdirs();
            Formatter crear = new Formatter(ubi5+archivo);
            
            crear.format("%s\r\n%s",
                    "Tarjeta="+notarjeta.getText().toUpperCase(),
                    "Limite="+montolimite.getValue());
            
            crear.close();
            
        }catch(Exception e){}    
        
    } 
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        nombre = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        notarjeta = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        pin = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btncrear = new javax.swing.JButton();
        btncrear1 = new javax.swing.JButton();
        btncrear2 = new javax.swing.JButton();
        montoactual = new javax.swing.JSpinner();
        montolimite = new javax.swing.JSpinner();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setPreferredSize(new java.awt.Dimension(415, 470));

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Creacion de Usuario");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel3))
        );

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Nombre:");

        nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombreActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("Numero de Tarjeta:");

        notarjeta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                notarjetaActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setText("PIN:");

        pin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pinActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setText("Monto Actual:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setText("Monto Limite:");

        btncrear.setBackground(new java.awt.Color(51, 51, 51));
        btncrear.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btncrear.setForeground(new java.awt.Color(255, 255, 255));
        btncrear.setText("Ingresar Datos");
        btncrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncrearActionPerformed(evt);
            }
        });

        btncrear1.setBackground(new java.awt.Color(255, 0, 0));
        btncrear1.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btncrear1.setForeground(new java.awt.Color(51, 51, 51));
        btncrear1.setText("Eliminar Texto");
        btncrear1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncrear1ActionPerformed(evt);
            }
        });

        btncrear2.setBackground(new java.awt.Color(51, 51, 51));
        btncrear2.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btncrear2.setForeground(new java.awt.Color(255, 255, 255));
        btncrear2.setText("Regresar");
        btncrear2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncrear2ActionPerformed(evt);
            }
        });

        montoactual.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        montoactual.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));

        montolimite.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        montolimite.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 2, 10)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(204, 0, 0));
        jLabel9.setText("16 Digitos");

        jLabel10.setFont(new java.awt.Font("Tahoma", 2, 10)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(204, 0, 0));
        jLabel10.setText("4 Digitos");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(nombre)
                            .addComponent(notarjeta)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel10))
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel9)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pin, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(montoactual, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(montolimite, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btncrear, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btncrear2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btncrear1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(notarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pin, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btncrear))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(montoactual, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btncrear2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(montolimite, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btncrear1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombreActionPerformed

    private void notarjetaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_notarjetaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_notarjetaActionPerformed

    private void pinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pinActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pinActionPerformed

    private void btncrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncrearActionPerformed
        // TODO add your handling code here:
        
        Boolean E = false;
        
        String T = notarjeta.getText();
        String P = pin.getText();
        
        if(T.length()<16 || P.length()<4 || T.length()>16 || P.length()>4){
            E = true;
            if(T.length()<16 || T.length()>16){JOptionPane.showMessageDialog(null, "Verificar el Numero de la Tarjeta", "Error", JOptionPane.ERROR_MESSAGE);}
            else{JOptionPane.showMessageDialog(null, "Verificar el Numero del PIN", "Error", JOptionPane.ERROR_MESSAGE);}
            
        }
        
        for(int i = 0; i<a.size();i++){
             if(a.get(i).Tarjeta.equals(T)){
                 E = true;
                 JOptionPane.showMessageDialog(null, "El Numero de la Tarjeta ya Existe", "Error", JOptionPane.ERROR_MESSAGE);
                 break;
             }
             
        }
        
        if(nombre.getText().isEmpty()){
            E = true;
            JOptionPane.showMessageDialog(null, "Verificar el Campo 'Nombre'", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
//        int montoac = (int) montoactual.getValue();
//        int montoli =(int) montolimite.getValue();
//        if(montoac>montoli){
//            E = true;
//            JOptionPane.showMessageDialog(null, "El monto Limite debe ser menor al monto actual", "Error", JOptionPane.ERROR_MESSAGE);
//        }
        
        if(!E){
            Crear();
            JOptionPane.showMessageDialog(null, "El Usuario fue creado con Exito", "Usuario", JOptionPane.INFORMATION_MESSAGE);
        }
        
        
        
        
        
    }//GEN-LAST:event_btncrearActionPerformed

    
    private void btncrear1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncrear1ActionPerformed
        // TODO add your handling code here:
        nombre.setText("");
        notarjeta.setText("");
        pin.setText("");
        montoactual.setValue(0);
        montolimite.setValue(0);
    }//GEN-LAST:event_btncrear1ActionPerformed

    private void btncrear2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncrear2ActionPerformed
        // TODO add your handling code here:
        frmMenu_Administrador x = new frmMenu_Administrador();
        this.setVisible(false);
        x.setVisible(true); // Regresar al menu de administrador
    }//GEN-LAST:event_btncrear2ActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(CrearUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CrearUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CrearUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CrearUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CrearUsuario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btncrear;
    private javax.swing.JButton btncrear1;
    private javax.swing.JButton btncrear2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSpinner montoactual;
    private javax.swing.JSpinner montolimite;
    private javax.swing.JTextField nombre;
    private javax.swing.JTextField notarjeta;
    private javax.swing.JTextField pin;
    // End of variables declaration//GEN-END:variables

}
