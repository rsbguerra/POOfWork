package comprimidos;

import java.util.ArrayList;

public class GerirMedicamentos {

    public static void MenuMedicamentos() {
        System.out.println(
                "|==================================|\n"
                + "|                                  |\n"
                + "|               MENU               |\n"
                + "|                                  |\n"
                + "|==================================|\n"
                + "|        Selecione uma opção       |\n"
                + "|----------------------------------|\n"
                + "|   1.) Adicionar Medicamento      |\n" // perguntar qual sera o tipo de medicamento
                + "|   2.) Remover Medicamento        |\n"
                + "|   3.) Modificar Medicamento      |\n"
                + "|   4.) Consultar                  |\n"
                + "|   5.) Voltar atrás               |\n"
                + "|----------------------------------|\n");
    }

    public static void gerirMed(int idUtilizador, ArrayList<Utilizador> utilizadores) {
        int op;
        while (true) {
            op = Read.Int();
            switch (op) {
                // adicionar
                case 1:
                    break;
                // remover
                case 2:
                    remover(idUtilizador, utilizadores);
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Opçao Invalida\n");
            }
        }
    }



    public static void remover(int idUtilizador, ArrayList<Utilizador> utilizadores) {
        
    }

}
