package Metodos;


public class user {
    public String Nombre;
    public String Tarjeta;
    public String Pin;
    public int limite;
    public int saldo;
    public String tipo;
    
    public user(String Nombre, String Tarjeta, String Pin, int limite, int saldo, String tipo){
        this.Nombre = Nombre;
        this.Tarjeta = Tarjeta;
        this.Pin = Pin;
        this.limite = limite;
        this.saldo = saldo;
        this.tipo = tipo;
    }

    public user(String property, String property0, String property1, String property2, int parseInt, int parseInt0, String property3) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

