package comprimidos;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Medicamento implements Serializable {

    private int id;
    private String nome;
    private String descricao;
    private int periodo_toma;
    private int quantidade;
    private ArrayList<LocalDateTime> tomas_futuras;

    public Medicamento(int id, String nome, String descricao, int periodo_toma, int quantidade, ArrayList<LocalDateTime> tomas_futuras) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.periodo_toma = periodo_toma;
        this.quantidade = quantidade;
        this.tomas_futuras = tomas_futuras;
    }

    public Medicamento() {
        this.id = -1;
        this.nome = "";
        this.descricao = "";
        this.periodo_toma = 0;
        this.quantidade = 0;
    }

    public int getId() {
        return this.id;
    }
    public void setId(int id){
        this.id = id;
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

    public ArrayList<LocalDateTime> getTomas_Futuras(){
        return tomas_futuras;
    }
    
    public void setTomas_Futuras (ArrayList<LocalDateTime> lista){
        this.tomas_futuras = lista;
    }
    
    public boolean equals(Object obj) {

        if (obj != null && this.getClass() == obj.getClass()) {
            Medicamento m = (Medicamento) obj;
            return (this.id == m.id
                    && this.quantidade == m.quantidade
                    && this.nome.equals(m.nome)
                    && this.descricao.equals(m.descricao)
                    && this.periodo_toma == m.periodo_toma
                    && this.tomas_futuras.equals(m.tomas_futuras));
        } else {
            return false;
        }
    }

    public Object clone() {
        Medicamento copia = new Medicamento(this.id, this.nome, this.descricao, this.periodo_toma, this.quantidade, this.tomas_futuras);
        return copia;
    }

    @Override
    public String toString() {
        return "Nome: " + nome
                + "\nCódigo: " + id
                + "\nDescrição: " + descricao
                + "\nPeríodo toma: " + periodo_toma
                + "\nQuantidade: " + quantidade + "\n";
    }
    
    public String tomasToString(){
        String s = "";
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        s = s + "\nNome: " + this.nome + "\n";
        
        for(int i = 0; i < this.tomas_futuras.size(); i++){
            s = s + formato.format(this.tomas_futuras.get(i));
        }
        
        return s;
    }
}