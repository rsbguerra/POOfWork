package comprimidos;

import java.io.*;

public class Data implements Serializable {

    private int minuto, hora, dia, mes, ano;
    
    // construtor de omissao, utilizado no construtor de omissao de Medicamento
    public Data() {
        this.minuto = 0;
        this.hora = 0;
        this.dia = 0;
        this.mes = 0;
        this.ano = 0;
    }
    
    // construtor utilizado para definir um periodo de toma
    public Data(int minuto, int hora) {
        this.minuto = minuto;
        this.hora = hora;
        this.dia = 0;
        this.mes = 0;
        this.ano = 0;
    }

    // construtor utilizado para datas de nascimento
    public Data(int dia, int mes, int ano) {
        this.minuto = 0;
        this.hora = 0;
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }
    
    //construtor de copia
    public Data(int minuto, int hora, int dia, int mes, int ano) {
        this.minuto = minuto;
        this.hora = hora;
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }

    public int getMinuto() {
        return minuto;
    }

    public void setMinuto(int minuto) {
        this.minuto = minuto;
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }
    
    public boolean equals(Object obj) {

        if (obj != null && this.getClass() == obj.getClass()) {
            Data d = (Data) obj;
            return (this.minuto == d.minuto
                    && this.hora == d.hora
                    && this.dia == d.dia
                    && this.mes == d.mes
                    && this.ano == d.ano);
        } else {
            return false;
        }
    }
    
    public Data clone(){
        return new Data(this.minuto, this.hora, this.dia, this.mes, this.ano);
    }
    
    @Override
    public String toString() {
        return dia + "/" + mes + "/" + ano;
    }

}
