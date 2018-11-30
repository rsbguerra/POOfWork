package comprimidos;

import java.io.*;

public class Data implements Serializable {

    private int dia, mes, ano;

    public Data() {
        dia = 0;
        mes = 0;
        ano = 0;
    }

    public Data(int day, int month, int year) {
        dia = day;
        mes = month;
        ano = year;
    }

    public int getDia() {
        return dia;
    }

    public int getMes() {
        return mes;
    }

    public int getAno() {
        return ano;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    @Override
    public String toString() {
        return dia + "/" + mes + "/" + ano;
    }

}
