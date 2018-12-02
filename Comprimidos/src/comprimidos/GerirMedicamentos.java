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

    public static void menu() {
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
    
    public static void modificar(int idUtilizador, ArrayList<Utilizador> utilizadores) {
        int choice = 0;
        boolean ProgramOn = true;

        while (ProgramOn) {

            menuModificar();
            choice = Read.Int();

            switch (choice) {
                case 1: {
                    System.out.print("Introduza o novo nome: ");
                    String nome = Read.String();
                    utilizadores.get(idUtilizador).setNome(nome);
                    System.out.println("Medicamento modificado com sucesso.");
                    System.out.println(utilizadores.get(idUtilizador).getMedicamentos(id));
                    System.out.println("\nEscolha outra opção...");
                    break;
                }

                case 2: {
                    System.out.print("Introduza a nova quantidade: ");
                    int quanti = Read.Int();
                    utilizadores.get(idUtilizador).setQuantidade(quanti);
                    System.out.println("Medicamento modificado com sucesso.");
                    System.out.println(utilizadores.get(idUtilizador).getMedicamentos(id));
                    System.out.println("\nEscolha outra opção...");
                    break;
                }

                case 3: {
                    System.out.print("Introduza o novo o período de toma: ");
                    int pt = Read.Int();
                    utilizadores.get(idUtilizador).setPeriodo_toma(pt);
                    System.out.println("Medicamento modificado com sucesso.");
                    System.out.println(utilizadores.get(idUtilizador).getMedicamentos(id));
                    System.out.println("\nEscolha outra opção...");
                    break;
                }

                case 4: {
                    System.out.println("\n");
                    Ficheiro.escrever(utilizadores);
                    return;
                }

                default: {
                    System.out.println("Opção inválida.");
                    break;
                }
            }
        }

    }
}
