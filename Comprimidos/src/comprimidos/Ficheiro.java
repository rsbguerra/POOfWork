package comprimidos;

import java.io.*;
import java.util.ArrayList;

public class Ficheiro implements Serializable{
    
    public static ArrayList<Utilizador> abrir() {
        
        ArrayList<Utilizador> Utilizadores = new ArrayList();

        // guardar todos os utilizadores no ficheiro no arraylist 'Utilizadores'
        try {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream("utilizadores.dat"));
            Utilizadores = (ArrayList<Utilizador>) is.readObject();

        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return Utilizadores;
    }

    public static void escrever(ArrayList<Utilizador> Utilizadores) {
        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("utilizadores.dat"));
            os.writeObject(Utilizadores);
            os.flush();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
