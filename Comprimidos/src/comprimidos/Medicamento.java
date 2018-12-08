package comprimidos;

import java.io.*;
import java.util.ArrayList;

public class Medicamento implements Serializable {

    private int id;
    private String nome;
    private String descricao;
    private Data periodo_toma;
    private int quantidade;

    public Medicamento(int id, String nome, String descricao, Data periodo_toma, int quantidade) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.periodo_toma = periodo_toma;
        this.quantidade = quantidade;
    }

    public Medicamento() {
        this.id = -1;
        this.nome = "";
        this.descricao = "";
        this.periodo_toma = new Data();
        this.quantidade = 0;
    }

    public int getid() {
        return this.id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Data getPeriodo_toma() {
        return this.periodo_toma;
    }

    public void setPeriodo_toma(Data periodo_toma) {
        this.periodo_toma = periodo_toma;
    }

    public boolean equals(Object obj) {

        if (obj != null && this.getClass() == obj.getClass()) {
            Medicamento m = (Medicamento) obj;
            return (this.id == m.id
                    && this.quantidade == m.quantidade
                    && this.nome.equals(m.nome)
                    && this.descricao.equals(m.descricao)
                    && this.periodo_toma.equals(m.periodo_toma));
        } else {
            return false;
        }
    }

    public Object clone() {
        Medicamento copia = new Medicamento(this.id, this.nome, this.descricao, this.periodo_toma, this.quantidade);
        return copia;
    }

    @Override
    public String toString() {
        return "Nome: " + nome
                + "\nCodigo: " + id
                + "\nDescricao: " + descricao
                + "Periodo toma: " + periodo_toma
                + "\nQuantidade: " + quantidade + "\n";
    }
}
