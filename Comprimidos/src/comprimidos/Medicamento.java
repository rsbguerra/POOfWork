package comprimidos;

import java.io.*;
import java.util.ArrayList;

public class Medicamento implements Serializable {

    private int id;
    private String nome;
    private String descricao;
    private int periodo_toma;
    private int quantidade;
    private Data prim_toma;
    private ArrayList<Data> tomas_futuras;  // cada comprimido terá uma lista com as datas das tomas futuras

    public Medicamento(int id, String nome, String descricao, int periodo_toma, int quantidade, Data prim_toma) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.periodo_toma = periodo_toma;
        this.quantidade = quantidade;
        this.prim_toma = prim_toma;
    }

    public Medicamento() {
        this.id = -1;
        this.nome = "";
        this.descricao = "";
        this.periodo_toma = 0;
        this.quantidade = 0;
    }

    public int getid() {
        return this.id;
    }
    
    public ArrayList<Data> getTomas_Futuras()
    {
        return tomas_futuras;
    }
    
    public void setTomas_Futuras (ArrayList<Data> tomas)
    {
        this.tomas_futuras = tomas;
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

    public int getPeriodo_toma() {
        return this.periodo_toma;
    }

    public void setPeriodo_toma(int periodo_toma) {
        this.periodo_toma = periodo_toma;
    }

    public boolean equals(Object obj) {

        if (obj != null && this.getClass() == obj.getClass()) {
            Medicamento m = (Medicamento) obj;
            return (this.id == m.id
                    && this.quantidade == m.quantidade
                    && this.nome.equals(m.nome)
                    && this.descricao.equals(m.descricao)
                    && this.periodo_toma == m.periodo_toma);
        } else {
            return false;
        }
    }

    public Object clone() {
        Medicamento copia = new Medicamento(this.id, this.nome, this.descricao, this.periodo_toma, this.quantidade, this.prim_toma);
        return copia;
    }

    @Override
    public String toString() {
        return "Nome: " + nome
                + "\nCodigo: " + id
                + "\nDescricao: " + descricao
                + "\nPeriodo toma: " + periodo_toma
                + "\nQuantidade: " + quantidade + "\n";
    }
}
