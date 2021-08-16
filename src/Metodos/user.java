package Metodos;


public class user {
    public String Nombre;
    public String Tarjeta;
    public String Pin;
    public int limite;
    public int saldo;
    public String tipo;
    public String Sexo;
    
    public user(String Nombre, String Tarjeta, String Pin, int limite, int saldo, String tipo){
        this.Nombre = Nombre;
        this.Tarjeta = Tarjeta;
        this.Pin = Pin;
        this.limite = limite;
        this.saldo = saldo;
        this.tipo = tipo;
    }
}

