package comprimidos;

import java.io.*;
import java.util.*;

public class Utilizador implements Serializable {

    private static int count = 0;

    private int id;
    private String nome;
    private int idade;
    private Data dataNascimento;
    private String genero;
    private ArrayList<Medicamento> medicamentos;
    private ArrayList<Data> tomas_futuras;
    private String password;

    public Utilizador() {
        count = count + 1;
        this.id = count;
        this.nome = "";
        this.idade = 0;
        this.dataNascimento = new Data();
        this.genero = "";
        this.medicamentos = new ArrayList<>();
        this.password = "";

    }

    public Utilizador(String nome) {
        count = count + 1;
        this.id = count;
        this.nome = nome;
        this.idade = 0;
        this.password = "";
        this.dataNascimento = new Data();
        this.genero = "";
        this.medicamentos = new ArrayList<>();
    }

    public Utilizador(String nome, int idade, Data dataNascimento, String genero, String password) {
        count = count + 1;
        this.id = count;
        this.nome = nome;
        this.idade = idade;
        this.dataNascimento = dataNascimento;
        this.genero = genero;
        this.password = password;
        this.medicamentos = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public boolean testarPassword(String passwordIntroduzida) {
        return passwordIntroduzida.equals(this.password);
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }
    
    public String getPassword()
    {
        return password;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public Data getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Data dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getGenero() {
        return genero;
    }
    
    public ArrayList<Data> getTomas_Futuras()
    {
        return tomas_futuras;
    }
    
    public void setTomas_Futuras (ArrayList<Data> tomas)
    {
        this.tomas_futuras = tomas;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public ArrayList<Medicamento> getMedicamentos() {
        return medicamentos;
    }
    
    public void setMedicamentos(ArrayList<Medicamento> medicamentos) {
        this.medicamentos = medicamentos;
    }

    public Medicamento findDroga() throws ArrayVazio {

        ArrayList<Medicamento> drugs = getMedicamentos();
        int i;

        if (drugs.isEmpty()) {
            throw new ArrayVazio("Não existem medicamentos!");
        }

        System.out.println("Qual o medicamento? (insira o codigo)");

       // while (true) {
            id = Read.Int();

            for (i = 0; i < drugs.size(); i++) {
                if (i == id)
                   break;
                
            }
            
            if(i == id){
             System.out.println(drugs.get(i).toString());
             return drugs.get(i);
            }
            
            else throw new ArrayVazio("Medicamento não encontrado");
            

       // }
    }



@Override
        public String toString() {
        return "Nome: " + this.nome
                + "\nIdade: " + this.idade
                + "\nId: " + this.id
                + "\nData de nascimento: " + dataNascimento.toString()
                + "\nGenero: " + this.genero;
    }

    @Override
        public boolean equals(Object obj) {

        if (obj != null && this.getClass() == obj.getClass()) {
            Utilizador u = (Utilizador) obj;
            return (this.nome.equals(u.nome)
                    && this.idade == u.idade
                    && this.id == u.id
                    && this.dataNascimento.equals(u.dataNascimento)
                    && this.genero.equals(u.genero)
                    && this.medicamentos.equals(u.medicamentos));
        } else {
            return false;
        }
    }

    @Override
        public Object clone() {
        Utilizador copia = new Utilizador(this.nome, this.idade, this.dataNascimento, this.genero, this.password);
        copia.setMedicamentos(medicamentos);
        return copia;
    }
}
