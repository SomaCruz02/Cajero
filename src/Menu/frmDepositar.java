/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menu;

import Metodos.inic;
import Metodos.ulog;
import Metodos.user;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Formatter;
import java.util.Properties;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author kevin
 */
public class frmDepositar extends javax.swing.JFrame {

    String barra = File.separator;
    String ubi1 = System.getProperty("user.dir")+barra+"src"+barra+"archivos"+barra+"cajero"+barra;
    String ubi2 = System.getProperty("user.dir")+barra+"src"+barra+"archivos"+barra;
    String ubi3 = System.getProperty("user.dir")+barra+"src"+barra+"archivos"+barra+"usuarios"+barra;
    String ubi4 = System.getProperty("user.dir")+barra+"src"+barra+"archivos"+barra+"transacciones"+barra;
    
    File contenedor = new File(ubi3);
    File[] registros = contenedor.listFiles();
    
    public inic O;
    public user A;
    public ulog P;
    public ArrayList<inic> a = new ArrayList<inic>();
    public ArrayList<ulog> b = new ArrayList<ulog>();
    public ArrayList<user> c = new ArrayList<user>();
    
    public String fecha(){
        return ZonedDateTime.now().getDayOfMonth()+"-"+ZonedDateTime.now().getMonthValue()+"-"+ZonedDateTime.now().getYear();
    }
    
    public String Hora(){
        return ZonedDateTime.now().getHour()+":"+ZonedDateTime.now().getMinute()+":"+ZonedDateTime.now().getSecond();
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
    
    public int auxiliar = 0;
    
    public frmDepositar() {
        initComponents();
       this.setLocationRelativeTo(null);
        setResizable(false);
         this.getContentPane().setBackground(java.awt.Color.WHITE);
         
         ordenarPorNumero(registros);

        //SE OBTIENEN LOS DATOS DE LA SESIÓN ACTUAL
        File s = new File(ubi2+"record.txt");
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
            
        }
        
         File url = new File(ubi1+fecha()+".txt");
        if(url.exists()){
            try{
                FileInputStream fis = new FileInputStream(url);
                Properties mostrar = new Properties();
                mostrar.load(fis);               

                O = new inic(Integer.parseInt(mostrar.getProperty("Saldo")),
                                    Integer.parseInt(mostrar.getProperty("Q200")),
                                    Integer.parseInt(mostrar.getProperty("Q100")),
                                    Integer.parseInt(mostrar.getProperty("Q50")),
                                    Integer.parseInt(mostrar.getProperty("Q20")),
                                    Integer.parseInt(mostrar.getProperty("Q10")),
                                    Integer.parseInt(mostrar.getProperty("Q5")),
                                    Integer.parseInt(mostrar.getProperty("Q1")));
                a.add(O);

            }catch(Exception e){}
 
        }
        
          for(auxiliar=0;auxiliar<registros.length;auxiliar++){
        
            File url2 = new File(ubi3+registros[auxiliar].getName());
            try{
                FileInputStream fis = new FileInputStream(url2);
                Properties mostrar = new Properties();
                mostrar.load(fis);               

                if(mostrar.getProperty("Tarjeta").equals(b.get(0).Tarjeta)){
                    A = new user(mostrar.getProperty("Nombre"),
                                        mostrar.getProperty("Tarjeta"),
                                        mostrar.getProperty("PIN"),
                                        Integer.parseInt(mostrar.getProperty("Limite")),
                                        Integer.parseInt(mostrar.getProperty("Saldo")),
                                        mostrar.getProperty("Tipo"));
                    c.add(A);
                    break;
                }
            }catch(Exception e){}
            
        }
         
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        B1 = new javax.swing.JSpinner();
        B5 = new javax.swing.JSpinner();
        B10 = new javax.swing.JSpinner();
        B20 = new javax.swing.JSpinner();
        B50 = new javax.swing.JSpinner();
        B100 = new javax.swing.JSpinner();
        B200 = new javax.swing.JSpinner();
        saldo = new javax.swing.JSpinner();
        jLabel10 = new javax.swing.JLabel();
        btntransacciones = new javax.swing.JButton();
        btntransacciones1 = new javax.swing.JButton();
        btnregresar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Info = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Deposito");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(271, 271, 271)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 13, Short.MAX_VALUE)
                .addComponent(jLabel2))
        );

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));

        jPanel3.setBackground(new java.awt.Color(153, 153, 153));

        jLabel5.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Q20.00");
        jLabel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jLabel7.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Q50.00");
        jLabel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jLabel8.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Q100.00");
        jLabel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jLabel9.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Q200.00");
        jLabel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jLabel3.setBackground(new java.awt.Color(102, 102, 255));
        jLabel3.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Q1.00");
        jLabel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jLabel6.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Q10.00");
        jLabel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jLabel4.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Q5.00");
        jLabel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        B1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        B1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                B1StateChanged(evt);
            }
        });

        B5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        B5.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                B5StateChanged(evt);
            }
        });

        B10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        B10.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                B10StateChanged(evt);
            }
        });

        B20.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        B20.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                B20StateChanged(evt);
            }
        });

        B50.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        B50.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                B50StateChanged(evt);
            }
        });

        B100.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        B100.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                B100StateChanged(evt);
            }
        });

        B200.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        B200.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                B200StateChanged(evt);
            }
        });

        saldo.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        saldo.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                saldoStateChanged(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setText("Ingrese el Deposito");

        btntransacciones.setBackground(new java.awt.Color(255, 255, 255));
        btntransacciones.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btntransacciones.setText("Ingresar");
        btntransacciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntransaccionesActionPerformed(evt);
            }
        });

        btntransacciones1.setBackground(new java.awt.Color(255, 255, 255));
        btntransacciones1.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btntransacciones1.setText("Borrar Datos");
        btntransacciones1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntransacciones1ActionPerformed(evt);
            }
        });

        btnregresar.setBackground(new java.awt.Color(51, 51, 51));
        btnregresar.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnregresar.setForeground(new java.awt.Color(255, 255, 255));
        btnregresar.setText("Regresar");
        btnregresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnregresarActionPerformed(evt);
            }
        });

        Info.setBackground(new java.awt.Color(204, 204, 204));
        Info.setColumns(20);
        Info.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        Info.setRows(5);
        jScrollPane1.setViewportView(Info);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(saldo, javax.swing.GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGap(100, 100, 100)
                                        .addComponent(jLabel10))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(btntransacciones, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btntransacciones1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(btnregresar, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(122, 122, 122))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(B1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(B5, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(B10, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(B20, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(B50, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(B100, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(B200, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(B1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(B5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(B10, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(B20, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(B50, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(B100, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(B200, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addComponent(jLabel10)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(saldo, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGap(18, 18, 18)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btntransacciones)
                                .addComponent(btntransacciones1))
                            .addGap(18, 18, 18)
                            .addComponent(btnregresar))
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 648, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btntransaccionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntransaccionesActionPerformed
        // TODO add your handling code here:
        Alerta();
    }//GEN-LAST:event_btntransaccionesActionPerformed

     public void Alerta(){
       
        if(E){
            JOptionPane.showMessageDialog(null, "Distribucion de Billetes erronea");

        }else{
            Crear();
            JOptionPane.showMessageDialog(null, "Deposito realizado");
   
            a.get(0).Saldo = a.get(0).Saldo + Integer.parseInt(saldo.getValue().toString());
                a.get(0).Q200 = a.get(0).Q200 + Integer.parseInt(B200.getValue().toString());
                a.get(0).Q100 = a.get(0).Q100 + Integer.parseInt(B100.getValue().toString());
                a.get(0).Q50 = a.get(0).Q50 + Integer.parseInt(B50.getValue().toString());
                a.get(0).Q20 = a.get(0).Q20 + Integer.parseInt(B20.getValue().toString());
                a.get(0).Q10 = a.get(0).Q10 + Integer.parseInt(B10.getValue().toString());
                a.get(0).Q5 = a.get(0).Q5 + Integer.parseInt(B5.getValue().toString());
                a.get(0).Q1 = a.get(0).Q1 + Integer.parseInt(B1.getValue().toString());
                            
        }
    }
    
    private void Crear(){
        String archivo = fecha()+".txt";
        File crea_ubicación = new File(ubi1);
                
        try{    
            
            crea_ubicación.mkdirs();
            Formatter crear = new Formatter(ubi1+archivo);
            
            crear.format("%s\r\n%s\r\n%s\r\n%s\r\n%s\r\n%s\r\n%s\r\n%s",
                    "Saldo="+(Integer.parseInt(saldo.getValue().toString())+a.get(0).Saldo),
                    "Q200="+(Integer.parseInt(B200.getValue().toString())+a.get(0).Q200),
                    "Q100="+(Integer.parseInt(B100.getValue().toString())+a.get(0).Q100),
                    "Q50="+(Integer.parseInt(B50.getValue().toString())+a.get(0).Q50),
                    "Q20="+(Integer.parseInt(B20.getValue().toString())+a.get(0).Q20),
                    "Q10="+(Integer.parseInt(B10.getValue().toString())+a.get(0).Q10),
                    "Q5="+(Integer.parseInt(B5.getValue().toString())+a.get(0).Q5),
                    "Q1="+(Integer.parseInt(B1.getValue().toString())+a.get(0).Q1));
            
            crear.close();
            
        }catch(Exception e){}     
        modUser();
        
    }
    
    private void modUser(){
        String archivo = "user"+(auxiliar+1)+".txt";
        File crea_ubicación = new File(ubi3);
                
        try{    
            
            crea_ubicación.mkdirs();
            Formatter crear = new Formatter(ubi3+archivo);
            
            crear.format("%s\r\n%s\r\n%s\r\n%s\r\n%s\r\n%s",
                    "Nombre="+c.get(0).Nombre,
                    "Tarjeta="+c.get(0).Tarjeta,
                    "PIN="+c.get(0).Pin,
                    "Limite="+c.get(0).limite,
                    "Saldo="+(c.get(0).saldo+Integer.parseInt(saldo.getValue().toString())),
                    "Tipo="+c.get(0).tipo);
            
             c.get(0).saldo = c.get(0).saldo + Integer.parseInt(saldo.getValue().toString());
            crear.close();
            
        }catch(Exception e){}     
        transaccion();
    }
    
    private void transaccion(){
        File contenedor = new File(ubi4);
        File[] registros = contenedor.listFiles();
        
        String archivo = "tr"+(registros.length+1)+".txt";
        File crea_ubicación = new File(ubi4);
                
        try{    
            
            crea_ubicación.mkdirs();
            Formatter crear = new Formatter(ubi4+archivo);
            
            crear.format("%s\r\n%s\r\n%s\r\n%s\r\n%s",
                    "Tarjeta="+b.get(0).Tarjeta,
                    "Fecha="+fecha(),
                    "Hora="+Hora(),
                    "Monto="+saldo.getValue(),
                    "Tipo=Deposito");
            
            crear.close();
            
        }catch(Exception e){}     
        
    }
    
    
    private void saldoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_saldoStateChanged
        texto();
    }//GEN-LAST:event_saldoStateChanged

    private void B200StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_B200StateChanged
       texto();
    }//GEN-LAST:event_B200StateChanged

    private void B100StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_B100StateChanged
       texto();
    }//GEN-LAST:event_B100StateChanged

    private void B50StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_B50StateChanged
        texto();
    }//GEN-LAST:event_B50StateChanged

    private void B20StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_B20StateChanged
       texto();
    }//GEN-LAST:event_B20StateChanged

    private void B10StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_B10StateChanged
     texto();
    }//GEN-LAST:event_B10StateChanged

    private void B5StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_B5StateChanged
      texto();
    }//GEN-LAST:event_B5StateChanged

    private void B1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_B1StateChanged
        texto();
    }//GEN-LAST:event_B1StateChanged

    private void btntransacciones1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntransacciones1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btntransacciones1ActionPerformed

    private void btnregresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnregresarActionPerformed
        // TODO add your handling code here:
         frmMenu_usuarios x = new frmMenu_usuarios();
        this.setVisible(false);
        x.setVisible(true); // Regresar al menu principal
    }//GEN-LAST:event_btnregresarActionPerformed

   public boolean E = true;
    public void texto(){
     
        int Total = Integer.parseInt(B200.getValue().toString())*200 + 
                    Integer.parseInt(B100.getValue().toString())*100 +
                    Integer.parseInt(B50.getValue().toString())*50 +
                    Integer.parseInt(B20.getValue().toString())*20 +
                    Integer.parseInt(B10.getValue().toString())*10 +
                    Integer.parseInt(B5.getValue().toString())*5 +
                    Integer.parseInt(B1.getValue().toString());

        if(Total<Integer.parseInt(saldo.getValue().toString()) || Total>Integer.parseInt(saldo.getValue().toString())){
            E = true;
            Info.setText( "Distribucion de Billetes erronea: "+Total+" no es igual a: "+saldo.getValue());
           // JOptionPane.showMessageDialog(null, "Distribucion de Billetes erronea"+Total+" no es igual a "+Deposito.getValue());
        }else if(Integer.parseInt(saldo.getValue().toString()) == 0){
            E = true;
            Info.setText( "El saldo debe ser mayor a 0");
            //JOptionPane.showMessageDialog(null, "El saldo debe ser mayor a 0");
        }else{
            E = false;
            Info.setText( "Distribucion Correcta");
           // JOptionPane.showMessageDialog(null, "Distribucion correcta");
        }
        
       // alerta.setText(T);
    }
    
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
            java.util.logging.Logger.getLogger(frmDepositar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmDepositar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmDepositar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmDepositar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmDepositar().setVisible(true);
            }
        });
    }
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSpinner B1;
    private javax.swing.JSpinner B10;
    private javax.swing.JSpinner B100;
    private javax.swing.JSpinner B20;
    private javax.swing.JSpinner B200;
    private javax.swing.JSpinner B5;
    private javax.swing.JSpinner B50;
    private javax.swing.JTextArea Info;
    private javax.swing.JButton btnregresar;
    private javax.swing.JButton btntransacciones;
    private javax.swing.JButton btntransacciones1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner saldo;
    // End of variables declaration//GEN-END:variables
}
