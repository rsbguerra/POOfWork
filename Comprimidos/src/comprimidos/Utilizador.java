package comprimidos;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Utilizador implements Serializable {
    private String nome;
    private int idade;
    private LocalDateTime dataNascimento;
    private String genero;
    private ArrayList<Medicamento> medicamentos;
    private String password;

    public Utilizador() {
        this.nome = "";
        this.idade = 0;
        this.dataNascimento = LocalDateTime.of(1, 1, 1, 0, 0);
        this.genero = "";
        this.medicamentos = new ArrayList<>();
        this.password = "";
    }

    public Utilizador(String nome) {
        this.nome = nome;
        this.idade = 0;
        this.password = "";
        this.dataNascimento = LocalDateTime.of(1, 1, 1, 0, 0);
        this.genero = "";
        this.medicamentos = new ArrayList<>();
    }

    public Utilizador(String nome, int idade, LocalDateTime dataNascimento, String genero, String password) {
        this.nome = nome;
        this.idade = idade;
        this.dataNascimento = dataNascimento;
        this.genero = genero;
        this.password = password;
        this.medicamentos = new ArrayList<>();
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

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public LocalDateTime getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDateTime dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getGenero() {
        return genero;
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

    @Override
    public String toString() {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        return "Nome: " + this.nome
                + "\nIdade: " + this.idade
                + "\nData de nascimento: " + dtf.format(dataNascimento)
                + "\nGenero: " + this.genero;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj != null && this.getClass() == obj.getClass()) {
            Utilizador u = (Utilizador) obj;
            return (this.nome.equals(u.nome)
                    && this.idade == u.idade
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
