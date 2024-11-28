import java.io.Serializable;

public class Coche implements Serializable {
    // serializable es para que pueda ser guardado como un fichero de datos binarios y poder ser "serializado" o "deserializado"
    private int id;
    private String matricula;
    private String marca;
    private String modelo;
    private String color;


    // creacion del objeto coche con sus getters and setters, constructor y tostring().

    public Coche() {
    }


    @Override
    public String toString() {
        return "Coche: " +
                "id: " + id +
                ", matricula: '" + matricula + '\'' +
                ", marca: '" + marca + '\'' +
                ", modelo: '" + modelo + '\'' +
                ", color: '" + color + '\'' +
                '.';
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }



    public Coche(int id, String matricula, String marca, String modelo, String color) {
        this.id = id;
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
    }

}
