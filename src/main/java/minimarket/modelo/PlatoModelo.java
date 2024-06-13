package minimarket.modelo;

import minimarket.persistencia.PlatoDAO;

import java.util.ArrayList;

public class PlatoModelo {
    private int id_Plato;
    private String nombre;
    private double precio;
    private PlatoDAO platodao = new PlatoDAO(this);

    public PlatoModelo() {
    }

    public PlatoModelo(String nombre, double precio, PlatoDAO platodao) {
        this.nombre = nombre;
        this.precio = precio;
        this.platodao = platodao;
    }

    public int getId_Plato() {
        return id_Plato;
    }

    public void setId_Plato(int id_Plato) {this.id_Plato = id_Plato;}

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public PlatoDAO getPlatodao() {
        return platodao;
    }

    public void setPlatodao(PlatoDAO platodao) {
        this.platodao = platodao;
    }

    public void ingresarPlato(String nombre, double precio) throws Exception {
        platodao.ingresarPlato(nombre, precio);
    }

    public void modificarPlato(int id, String nombre, double precio) throws Exception {
        platodao.modificarPlato(id, nombre, precio);
    }

    public void eliminarPlato(int id) throws Exception {
        platodao.eliminarPlato(id);
    }

    public PlatoModelo buscarPlatoPorId(int id) throws Exception {
        return platodao.buscarPlatoPorId(id);
    }

    public ArrayList<String> listarPlatos() throws Exception {
        return platodao.listarPlatos();
    }

    public ArrayList<PlatoModelo> obtenerPlatosTodos() throws Exception {
        return platodao.obtenerPlatosTodos();
    }


}
