
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Concesionario {

    // creamos el ArrayList de objetos "coche".
    private static ArrayList<Coche> coches = new ArrayList<>();


    public static void main(String[] args) {

        // añadir coches aleatorios para probar csv
        bulkCoches();

        comprobacionArchivo();
        int opcion;

        do {
            System.out.println("\nMenú:");
            System.out.println("1. Añadir nuevo coche");
            System.out.println("2. Borrar coche por id");
            System.out.println("3. Consultar coche por id");
            System.out.println("4. Listado de coches");
            System.out.println("5. Terminar el programa");
            System.out.println("6. Exportar coches a archivo CSV");
            System.out.print("Elige una opción: ");
            Scanner leer = new Scanner(System.in);
            opcion = leer.nextInt();
            switch (opcion) {
                case 1:
                    añadirCoche(leer);
                    break;
                case 2:
                    borrarCoche();
                    break;
                case 3:
                    buscarCoche();
                    break;
                case 4:
                    listarCoches();
                    break;
                case 5:
                    exitProgram();
                    break;
                case 6:
                    crearCsv();
                    break;

                default:
                    System.out.println("Opcion no valida.");
            }

        } while (opcion != 5);
    }

    // COMPROBACION DEL ARCHIVO .DAT.
    private static void comprobacionArchivo() {
        File file = new File("src/fichero/coches.dat");
        if (file.exists()) {
            // creamos un objectinputstream para leer objetos del archivo en caso de existir
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
                // lo casteamos en un arraylist asumiendo que de existir, van a tener ese formato
                coches = (ArrayList<Coche>) in.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error al leer el archivo: " + e.getMessage());
            }
        }
    }

    private static boolean cocheExiste(int id, String matricula) {
        for (Coche coche : coches) {
            if (coche.getId() == id || coche.getMatricula().equals(matricula)) {
                return true;
            }
        }
        return false;
    }

    private static void bulkCoches() {
        coches.add(new Coche(1, "ABC1234Z", "Toyota", "Corolla", "Blanco"));
        coches.add(new Coche(2, "XYZ5678T", "Ford", "Mustang", "Rojo"));
        coches.add(new Coche(3, "LMN9012P", "BMW", "Serie 3", "Azul"));
        coches.add(new Coche(4, "OPQ3456R", "Mercedes-Benz", "C-Class", "Negro"));
        coches.add(new Coche(5, "GHI7890U", "Honda", "Civic", "Verde"));
    }

    private static void añadirCoche(Scanner leer) {
        System.out.println("Introduzca el ID: ");
        int id = leer.nextInt();
        leer.nextLine();
        System.out.println("Introduzca la matricula: ");
        String matricula = leer.nextLine();
        if (cocheExiste(id, matricula)) {
            System.out.println("El coche con id " + id + " y matricula " + matricula + " ya existe en nuestra base de datos.");
            return;
        }
        System.out.println("Introduzca la marca: ");
        String marca = leer.nextLine();
        System.out.println("Introduzca el modelo: ");
        String modelo = leer.nextLine();
        System.out.println("Introduzca el color: ");
        String color = leer.nextLine();
        System.out.println("Procesando...");
        coches.add(new Coche(id, matricula, marca, modelo, color));

        System.out.println("Coche añadido con exito a la Base de Datos.");
    }


    private static void borrarCoche() {
        Scanner leer = new Scanner(System.in);
        System.out.println("Para borrar su registro, introduzca el ID del coche: ");
        int id = leer.nextInt();
        boolean idEncontrado = false;
        // recorremos el arraylist inversamenet y le vamos dando los valores a "coche"
        for (int i = coches.size() - 1; i >= 0; i--) {
            Coche coche = coches.get(i);
            // cuando el id del coche coincida con el id aportado por consola, ejecutara el metodo .remove().
            if (coche.getId() == id) {
                coches.remove(coche);
                idEncontrado = true;
                break;
            }
        }
        if (idEncontrado) {
            System.out.println("El coche con id " + id + " ha sido borrado con exito.");
        } else {
            System.out.println("Coche no encontrado.");
        }
    }


    private static void buscarCoche() {
        Scanner leer = new Scanner(System.in);
        System.out.println("Introduzca el id del coche: ");
        int id = leer.nextInt();
        boolean encontrado = false;
        for (int i = 0; i < coches.size() ; i++) {
            Coche coche = coches.get(i);
            if (coche.getId() == id) {
                System.out.println(coche);
                encontrado = true;
                break;
            }
        }
        if (!encontrado){
            System.out.println("Coche no encontrado.");
        }
    }


    private static void listarCoches() {
        for (Coche coche : coches) {
            System.out.println(coche);
        }
    }

    private static void exitProgram() {
        guardarDat();
        System.out.println("Hasta luego!");

    }

    // creamos el metodo para que al salir del programa guarde todos los archivos creados en el Arraylist en el archivo .dat
    private static void guardarDat() {
        // instanciamos el objeto
        File file = new File("src/fichero/coches.dat");
        // ponemos en modo escritura
        try (ObjectOutput escrituraDat = new ObjectOutputStream(new FileOutputStream(file))) {
            // ejecutamos la escritura
            escrituraDat.writeObject(coches);
        } catch (IOException e) {
            System.out.println("Error al guardar.");
        }


    }

    // metodo para crear el csv con BufferedWriter
    private static void crearCsv() {
        File file = new File("src/fichero/coches.csv");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            // escribimos la cabecera
            writer.write("ID;Matricula;Marca;Modelo;Color\n");
            // recorremos otra vez el arraylist para ir obteniendo los datos de cada atributo y escribirlos en el csv
            for (Coche coche : coches) {
                writer.write(coche.getId() + ";" + coche.getMatricula() + ";" + coche.getMarca() + ";" + coche.getModelo() + ";" + coche.getColor() + "\n");
            }
            System.out.println("Exportacion realizada con exito.");
        } catch (IOException e) {
            System.out.println("Error al exportar a CSV.");
        }

    }


}







