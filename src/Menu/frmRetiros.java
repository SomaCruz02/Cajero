
package Menu;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Formatter;
import java.util.Properties;
import javax.swing.JFrame;
import Metodos.inic;
import Metodos.lim;
import Metodos.ulog;
import Metodos.user;
import static java.awt.image.ImageObserver.HEIGHT;
import javax.swing.JOptionPane;


public class frmRetiros extends javax.swing.JFrame {

    String barra = File.separator;
    
    String ubi2 = System.getProperty("user.dir")+barra+"src"+barra+"archivos"+barra;
    String ubi1 = ubi2+"transacciones"+barra;
    String ubi3 = ubi2+"cajero"+barra;
    String ubi4 = ubi2+"usuarios"+barra;
    String ubi5 = ubi2+"limite"+barra;
    
    File contenedor = new File(ubi4);
    File[] registros = contenedor.listFiles();
    
    File contenedor2 = new File(ubi5);
    File[] registros2 = contenedor.listFiles();
    
    public inic O;
    public ulog P;
    public user A;
    public lim M;
    public ArrayList<inic> a = new ArrayList<inic>();
    public ArrayList<ulog> b = new ArrayList<ulog>();
    public ArrayList<user> c = new ArrayList<user>();
    public ArrayList<lim> d = new ArrayList<lim>();
    public int auxiliar = 0;
    
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
    
    private void Crear(){       //Crear registro de la transacción 
        File contenedor = new File(ubi1);
        File[] registros = contenedor.listFiles();
        
        String archivo = "tr"+(registros.length+1)+".txt";
        File crea_ubicación = new File(ubi1);
                
        try{    
            
            crea_ubicación.mkdirs();
            Formatter crear = new Formatter(ubi1+archivo);
            
            crear.format("%s\r\n%s\r\n%s\r\n%s\r\n%s",
                    "Tarjeta="+b.get(0).Tarjeta,
                    "Fecha="+fecha(),
                    "Hora="+Hora(),
                    "Monto="+txtretiro.getValue().toString(),
                    "Tipo=Retiro");
            
            crear.close();
            
        }catch(Exception e){}     
        
    }
    
    
    private void modUser(int nSaldo){       //Modificar la información del usuario   
        String archivo = "user"+(auxiliar+1)+".txt";
        File crea_ubicación = new File(ubi4);
                
        try{    
            
            crea_ubicación.mkdirs();
            Formatter crear = new Formatter(ubi4+archivo);
            
            crear.format("%s\r\n%s\r\n%s\r\n%s\r\n%s\r\n%s",
                    "Nombre="+c.get(0).Nombre,
                    "Tarjeta="+c.get(0).Tarjeta,
                    "PIN="+c.get(0).Pin,
                    "Limite="+c.get(0).limite,
                    "Saldo="+(c.get(0).saldo-nSaldo),
                    "Tipo="+c.get(0).tipo);
            
            crear.close();
            
        }catch(Exception e){}    
        modLim(nSaldo);
    }
    
    private void modCajero(int B200, int B100, int B50, int B20, int B10, int B5, int B1, int nSaldo){      //Modificar la información del cajero
        String archivo = fecha()+".txt";
        File crea_ubicación = new File(ubi3);
                
        try{    
            
            crea_ubicación.mkdirs();
            Formatter crear = new Formatter(ubi3+archivo);
            
            
            
            crear.format("%s\r\n%s\r\n%s\r\n%s\r\n%s\r\n%s\r\n%s\r\n%s",
                    "Saldo="+(a.get(0).Saldo-nSaldo),           
                    "Q200="+(a.get(0).Q200-B200),       //IMPORTANTE QUE CONCUERDEN EL FORMATO DE Qnumero
                    "Q100="+(a.get(0).Q100-B100),
                    "Q50="+(a.get(0).Q50-B50),
                    "Q20="+(a.get(0).Q20-B20),
                    "Q10="+(a.get(0).Q10-B10),
                    "Q5="+(a.get(0).Q5-B5),
                    "Q1="+(a.get(0).Q1-B1));
            
            crear.close();
            
        }catch(Exception e){}    
        
        modUser(nSaldo);
    }
    
    
    private void modLim(int nSaldo){        //Actualiza el límite
        String archivo = "user"+(auxiliar+1)+".txt";
        File crea_ubicación = new File(ubi5);
                
        try{    
            
            crea_ubicación.mkdirs();
            Formatter crear = new Formatter(ubi5+archivo);
            
            crear.format("%s\r\n%s",
                    "Tarjeta="+d.get(0).Tarjeta,
                    "Limite="+(d.get(0).Limite-nSaldo));
            
            crear.close();
            
        }catch(Exception e){}    
        
    } 
    
    public String fecha(){
        return ZonedDateTime.now().getDayOfMonth()+"-"+ZonedDateTime.now().getMonthValue()+"-"+ZonedDateTime.now().getYear();
    }
    
    public String Hora(){
        return ZonedDateTime.now().getHour()+":"+ZonedDateTime.now().getMinute()+":"+ZonedDateTime.now().getSecond();
    }
    
    public frmRetiros() {
        initComponents();
        this.setLocationRelativeTo(null);
        setResizable(false);
        this.getContentPane().setBackground(java.awt.Color.WHITE);
        
        ordenarPorNumero(registros);
        ordenarPorNumero(registros2);
        
        File s = new File(ubi2+"record.txt");       //Quien fue el que inicio sesión
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
        
        //SE OBTIENEN LOS DATOS DEL USUARIO
        for(auxiliar=0;auxiliar<registros.length;auxiliar++){
        
            File url = new File(ubi4+registros[auxiliar].getName());
            try{
                FileInputStream fis = new FileInputStream(url);
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
        
        //SE OBTIENEN LOS DATOS DEL LIMITE DIARIO DEL USUARIO
        for(int i = 0;i<registros2.length;i++){
        
            File url = new File(ubi5+registros2[i].getName());
            try{
                FileInputStream fis = new FileInputStream(url);
                Properties mostrar = new Properties();
                mostrar.load(fis);               

                if(mostrar.getProperty("Tarjeta").equals(b.get(0).Tarjeta)){
                    M = new lim(mostrar.getProperty("Tarjeta"),
                                Integer.parseInt(mostrar.getProperty("Limite")));
                    d.add(M);
                    break;
                }
            }catch(Exception e){}
        }
        
        
        //SE OBTIENEN LOS DATOS DEL CAJERO EN EL DÍA ACTUAL
        File url = new File(ubi3+fecha()+".txt");
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
    }
    
    public int getBilletes(int B, int num, int C){
        int aux = C;
        for(int i = 0;i<=B;i++){
            aux = aux - i*num;
            if(aux == 0){
                return (i);
            }else if(aux < 0){
                return (i-1);
            }else{
                aux = C;
            }
        }
        
        return B;
    }
    
    private void imprimir(int B200, int B100, int B50, int B20, int B10, int B5, int B1, String saldo){
        String Q200 = String.valueOf(B200);
        String Q100 = String.valueOf(B100);
        String Q50 = String.valueOf(B50);
        String Q20 = String.valueOf(B20);
        String Q10 = String.valueOf(B10);
        String Q5 = String.valueOf(B5);
        String Q1 = String.valueOf(B1);
        txtretirofinal.setText(saldo);
        txt200.setText(Q200);
        txt100.setText(Q100);
        txt50.setText(Q50);
        txt20.setText(Q20);
        txt10.setText(Q10);
        txt5.setText(Q5);
        txt1.setText(Q1);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        tarjeta = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnAceptar = new javax.swing.JButton();
        btnAceptar1 = new javax.swing.JButton();
        txtretiro = new javax.swing.JSpinner();
        jPanel2 = new javax.swing.JPanel();
        tarjeta1 = new javax.swing.JLabel();
        txtretirofinal = new javax.swing.JTextField();
        tarjeta2 = new javax.swing.JLabel();
        txt200 = new javax.swing.JTextField();
        tarjeta3 = new javax.swing.JLabel();
        tarjeta4 = new javax.swing.JLabel();
        tarjeta5 = new javax.swing.JLabel();
        tarjeta6 = new javax.swing.JLabel();
        tarjeta7 = new javax.swing.JLabel();
        tarjeta8 = new javax.swing.JLabel();
        txt20 = new javax.swing.JTextField();
        txt100 = new javax.swing.JTextField();
        txt50 = new javax.swing.JTextField();
        txt10 = new javax.swing.JTextField();
        txt5 = new javax.swing.JTextField();
        txt1 = new javax.swing.JTextField();
        tarjeta9 = new javax.swing.JLabel();

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Cambiar PIN");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        tarjeta.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        tarjeta.setForeground(new java.awt.Color(255, 255, 255));
        tarjeta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tarjeta.setText("Retirar Efectivo");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tarjeta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(tarjeta)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Ingrese la cantidad a retirar");

        btnAceptar.setBackground(new java.awt.Color(102, 102, 102));
        btnAceptar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnAceptar.setForeground(new java.awt.Color(255, 255, 255));
        btnAceptar.setText("REGRESAR");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        btnAceptar1.setBackground(new java.awt.Color(255, 102, 51));
        btnAceptar1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnAceptar1.setForeground(new java.awt.Color(255, 255, 255));
        btnAceptar1.setText("ACEPTAR");
        btnAceptar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptar1ActionPerformed(evt);
            }
        });

        txtretiro.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tarjeta1.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        tarjeta1.setForeground(new java.awt.Color(255, 255, 255));
        tarjeta1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tarjeta1.setText("Retiro:");

        txtretirofinal.setEditable(false);
        txtretirofinal.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        tarjeta2.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        tarjeta2.setForeground(new java.awt.Color(255, 255, 255));
        tarjeta2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tarjeta2.setText("Q200:");

        txt200.setEditable(false);
        txt200.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        tarjeta3.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        tarjeta3.setForeground(new java.awt.Color(255, 255, 255));
        tarjeta3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tarjeta3.setText("Q10:");

        tarjeta4.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        tarjeta4.setForeground(new java.awt.Color(255, 255, 255));
        tarjeta4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tarjeta4.setText("Q100:");

        tarjeta5.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        tarjeta5.setForeground(new java.awt.Color(255, 255, 255));
        tarjeta5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tarjeta5.setText("Q50:");

        tarjeta6.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        tarjeta6.setForeground(new java.awt.Color(255, 255, 255));
        tarjeta6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tarjeta6.setText("Q20:");

        tarjeta7.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        tarjeta7.setForeground(new java.awt.Color(255, 255, 255));
        tarjeta7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tarjeta7.setText("Q5:");

        tarjeta8.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        tarjeta8.setForeground(new java.awt.Color(255, 255, 255));
        tarjeta8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tarjeta8.setText("Q1:");

        txt20.setEditable(false);
        txt20.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        txt100.setEditable(false);
        txt100.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        txt50.setEditable(false);
        txt50.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        txt10.setEditable(false);
        txt10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        txt5.setEditable(false);
        txt5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        txt1.setEditable(false);
        txt1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        tarjeta9.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        tarjeta9.setForeground(new java.awt.Color(255, 255, 255));
        tarjeta9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tarjeta9.setText("Retiro en Billetes");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tarjeta3)
                    .addComponent(tarjeta2)
                    .addComponent(tarjeta4)
                    .addComponent(tarjeta5)
                    .addComponent(tarjeta6)
                    .addComponent(tarjeta7)
                    .addComponent(tarjeta8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt200)
                    .addComponent(txt100, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txt20)
                    .addComponent(txt50)
                    .addComponent(txt10, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txt5)
                    .addComponent(txt1))
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tarjeta1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtretirofinal, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8))
            .addComponent(tarjeta9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tarjeta9, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tarjeta1)
                    .addComponent(txtretirofinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tarjeta2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt200))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tarjeta4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt100))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tarjeta5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt50, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tarjeta6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tarjeta3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tarjeta7, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tarjeta8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtretiro, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(74, 74, 74))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnAceptar1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(26, 26, 26))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtretiro, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(100, 100, 100)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAceptar)
                            .addComponent(btnAceptar1)))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed

    }//GEN-LAST:event_formMousePressed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        frmMenu_usuarios t = new frmMenu_usuarios();
        this.setVisible(false); 
        t.setVisible(true);
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnAceptar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptar1ActionPerformed
        int E = 0;
        int B200, B100, B50, B20, B10, B5, B1;
        int value = (Integer) txtretiro.getValue();
        
        if(value==0){
            JOptionPane.showMessageDialog(null, "La cantidad a retirar no puede ser igual a 0", "RETIRO", HEIGHT);
        }else{
            if(Integer.parseInt(txtretiro.getValue().toString())>d.get(0).Limite){
                E = 1;        
            }else if(Integer.parseInt(txtretiro.getValue().toString())>a.get(0).Saldo){
                E = 2;
            }else if(Integer.parseInt(txtretiro.getValue().toString())>c.get(0).saldo){
                E = 3;
            }else{
                int saldo = Integer.parseInt(txtretiro.getValue().toString());
                String retiro = txtretiro.getValue().toString();
                B200 = getBilletes(a.get(0).Q200, 200, saldo);
                B100 = getBilletes(a.get(0).Q100, 100, saldo-B200*200);
                B50 = getBilletes(a.get(0).Q50, 50, saldo-B200*200-B100*100);
                B20 = getBilletes(a.get(0).Q20, 20, saldo-B200*200-B100*100-B50*50);
                B10 = getBilletes(a.get(0).Q10, 10, saldo-B200*200-B100*100-B50*50-B20*20);
                B5 = getBilletes(a.get(0).Q5, 5, saldo-B200*200-B100*100-B50*50-B20*20-B10*10);
                B1 = getBilletes(a.get(0).Q1, 1, saldo-B200*200-B100*100-B50*50-B20*20-B10*10-B5*5);
                modCajero(B200, B100, B50, B20, B10, B5, B1, saldo);
                imprimir(B200, B100, B50, B20, B10, B5, B1, retiro);

                d.get(0).Limite = d.get(0).Limite - saldo;
                a.get(0).Saldo = a.get(0).Saldo - saldo;
                    a.get(0).Q200 = a.get(0).Q200 - B200;
                    a.get(0).Q100 = a.get(0).Q100 - B100;
                    a.get(0).Q50 = a.get(0).Q50 - B50;
                    a.get(0).Q20 = a.get(0).Q20 - B20;
                    a.get(0).Q10 = a.get(0).Q10 - B10;
                    a.get(0).Q5 = a.get(0).Q5 - B5;
                    a.get(0).Q1 = a.get(0).Q1 - B1;

                c.get(0).saldo = c.get(0).saldo - saldo;
            }
            if(E!=0){
                if(E==1){
                    JOptionPane.showMessageDialog(null, "El Retiro supera el límite diario de su cuenta", "RETIRO", HEIGHT);
                }else if(E==2){
                    JOptionPane.showMessageDialog(null, "El Retiro supera la capacidad del cajero", "RETIRO", HEIGHT);
                }else if(E==3){
                    JOptionPane.showMessageDialog(null, "Saldo Insuficiente", "RETIRO", HEIGHT);
                }
            }else{
                Crear();
                JOptionPane.showMessageDialog(null, "RETIRO EXITOSO", "RETIRO", HEIGHT);
            }
        }
    }//GEN-LAST:event_btnAceptar1ActionPerformed

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
            java.util.logging.Logger.getLogger(frmRetiros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmRetiros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmRetiros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmRetiros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmRetiros().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnAceptar1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel tarjeta;
    private javax.swing.JLabel tarjeta1;
    private javax.swing.JLabel tarjeta2;
    private javax.swing.JLabel tarjeta3;
    private javax.swing.JLabel tarjeta4;
    private javax.swing.JLabel tarjeta5;
    private javax.swing.JLabel tarjeta6;
    private javax.swing.JLabel tarjeta7;
    private javax.swing.JLabel tarjeta8;
    private javax.swing.JLabel tarjeta9;
    private javax.swing.JTextField txt1;
    private javax.swing.JTextField txt10;
    private javax.swing.JTextField txt100;
    private javax.swing.JTextField txt20;
    private javax.swing.JTextField txt200;
    private javax.swing.JTextField txt5;
    private javax.swing.JTextField txt50;
    private javax.swing.JSpinner txtretiro;
    private javax.swing.JTextField txtretirofinal;
    // End of variables declaration//GEN-END:variables
}
