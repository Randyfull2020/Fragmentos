package mx.com.randyfull8.fragmentos.model;


public class Categoria {

    private int idCategoria;
    private String imagen;
    private  String nombre;

    public Categoria() {
    }
    public Categoria(int idCategoria, String imagen, String nombre) {
        this.idCategoria = idCategoria;
        this.imagen = imagen;
        this.nombre = nombre;
    }
    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}



