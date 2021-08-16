
package Administrador;
import Menu.frmMenu_Administrador;
import Metodos.limite;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Formatter;
import Metodos.user;
import java.io.FileInputStream;
import java.util.Properties;
import javax.swing.JOptionPane;

public class ModLímite extends javax.swing.JFrame {

    String barra = File.separator;
    String rutaUsuarios = System.getProperty("user.dir")+barra+"src"+barra+"archivos"+barra+"usuarios"+barra;
    String rutaLimite = System.getProperty("user.dir")+barra+"src"+barra+"archivos"+barra+"limite"+barra;
    
    File contenedor = new File(rutaUsuarios);
    File[] registros = contenedor.listFiles();      //Registros de Usuarios

    File contenedor2 = new File(rutaLimite);
    File[] registros2 = contenedor.listFiles();     //Registros de Limites

    public user O;
    public limite M;
    public ArrayList<user> a = new ArrayList<user>();
    public ArrayList<limite> d = new ArrayList<limite>();
    
    public int auxiliar = 0;
    
    public ModLímite() {
        ordenarPorNumero(registros);
        ordenarPorNumero(registros2);
        initComponents();
        this.setLocationRelativeTo(null);
        setResizable(false);
        this.getContentPane().setBackground(java.awt.Color.WHITE);
        
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
        }
        
        for(int i = 0;i<registros2.length;i++){
        
            File url = new File(rutaLimite+registros2[i].getName());
            try{
                FileInputStream fis = new FileInputStream(url);
                Properties mostrar = new Properties();
                mostrar.load(fis);               

                
                    M = new limite(mostrar.getProperty("Tarjeta"),
                                Integer.parseInt(mostrar.getProperty("Limite")));
                    d.add(M);
                
            }catch(Exception e){}
            
        }
               
        llenar();
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
    
    public void llenar(){
        usuarios.removeAllItems();
        
        for(int i = 0; i<a.size(); i++){
            usuarios.addItem(a.get(i).Tarjeta);
        }
    }
    
    private void Crear(int i){
        String archivo = ("user")+(i+1)+".txt";
        File crea_ubicación = new File(rutaUsuarios);
                
        try{    
            crea_ubicación.mkdirs();
            Formatter crear = new Formatter(rutaUsuarios+archivo);
            crear.format("%s\r\n%s\r\n%s\r\n%s\r\n%s\r\n%s",
                    "Nombre="+a.get(i).Nombre,
                    "Tarjeta="+a.get(i).Tarjeta,
                    "PIN="+a.get(i).Pin,
                    "Limite="+limite.getValue(),
                    "Saldo="+a.get(i).saldo,
                    "Tipo="+a.get(i).tipo);
            crear.close();          
            
        }catch(Exception e){}    
        
        llenar();
    }    

    public void Modificar(){

        for(int i = 0; i<a.size();i++){
             if(usuarios.getSelectedItem().toString().equals(a.get(i).Tarjeta.toUpperCase())){
                 int respuesta = JOptionPane.showConfirmDialog(null, "¿Desea realizar cambios?", "Ok", JOptionPane.YES_NO_OPTION);
                 if(respuesta == JOptionPane.YES_OPTION){
                    int x = a.get(i).limite - d.get(i).Limite;
                    a.get(i).limite = Integer.parseInt(limite.getValue().toString());
                    Crear(i);
                    modLim(x, i);
                    break;
                 }

             }             
        }
    }
    
    
    private void modLim(int x, int i){
        String archivo = "user"+(i+1)+".txt";
        File crea_ubicación = new File(rutaLimite);
                
        try{    
            
            crea_ubicación.mkdirs();
            Formatter crear = new Formatter(rutaLimite+archivo);
            
            crear.format("%s\r\n%s",
                    "Tarjeta="+d.get(i).Tarjeta,
                    "Limite="+(a.get(i).limite - x));
            
            crear.close();
            
        }catch(Exception e){}    
        
    }

        
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        usuarios = new javax.swing.JComboBox<>();
        btnaceptar = new javax.swing.JButton();
        btncrear2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        limite = new javax.swing.JSpinner();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        limiteactual = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Numero de Tarjeta:");

        usuarios.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        usuarios.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                usuariosItemStateChanged(evt);
            }
        });
        usuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usuariosActionPerformed(evt);
            }
        });

        btnaceptar.setBackground(new java.awt.Color(51, 51, 51));
        btnaceptar.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnaceptar.setForeground(new java.awt.Color(255, 255, 255));
        btnaceptar.setText("Aceptar");
        btnaceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaceptarActionPerformed(evt);
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

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Modificar Límite");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel3))
        );

        limite.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        limite.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setText("Nuevo Límite:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setText("Límite Actual");

        limiteactual.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(usuarios, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(limiteactual, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addComponent(btnaceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(limite, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addComponent(btncrear2, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(usuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(limite, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(limiteactual))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnaceptar)
                    .addComponent(btncrear2))
                .addGap(66, 66, 66))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void usuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usuariosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usuariosActionPerformed

    private void btnaceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaceptarActionPerformed
        Modificar();    
    }//GEN-LAST:event_btnaceptarActionPerformed

    private void btncrear2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncrear2ActionPerformed
        frmMenu_Administrador x = new frmMenu_Administrador();
        this.setVisible(false);
        x.setVisible(true); // Regresar al menu de administrador
    }//GEN-LAST:event_btncrear2ActionPerformed

    private void usuariosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_usuariosItemStateChanged
        if(usuarios.getItemCount()>=1){
            for(int i = 0; i<a.size();i++){

                 if(usuarios.getSelectedItem().toString().equals(a.get(i).Tarjeta.toUpperCase())){
                     limiteactual.setText(a.get(i).limite+"");
                     break;
                 }             
            }
        }
    }//GEN-LAST:event_usuariosItemStateChanged

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
            java.util.logging.Logger.getLogger(ModLímite.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModLímite.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModLímite.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModLímite.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ModLímite().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnaceptar;
    private javax.swing.JButton btncrear2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSpinner limite;
    private javax.swing.JTextField limiteactual;
    private javax.swing.JComboBox<String> usuarios;
    // End of variables declaration//GEN-END:variables
}
