package comprimidos;

import java.util.ArrayList;

public class GerirUtilizadores {
    public static void MenuUtilizadores() {
        System.out.println(
                  "|==================================|\n"
                + "|                                  |\n"
                + "|               MENU               |\n"
                + "|                                  |\n"
                + "|==================================|\n"
                + "|        Selecione uma opção       |\n"
                + "|----------------------------------|\n"
                + "|   1.) Consultar Utilizador       |\n"
                + "|   2.) Remover Utilizador         |\n"
                + "|   3.) Modificar Utilizador       |\n" 
                + "|   4.) Gerir Medicamentos         |\n"
                + "|   5.) Voltar atrás               |\n"
                + "|----------------------------------|\n");
    }

    public static void gerirUtilizadores(int index, ArrayList<Utilizador> utilizadores) {

        int choice;
        while (true) {
            MenuUtilizadores();
            choice = Read.Int();

            switch (choice) {

                case 1: { //CONSULTA UTILIZADOR
                    System.out.println((utilizadores.get(index)).toString());
                    break;
                }

                case 2: { // REMOVE UTILIZADOR
                    // fazer remover() retornar um int para decidir se tem de
                    // voltar ao menu principal ou menu do utilizador
                    System.out.println("index: " + index);
                    remover(index, utilizadores);
                    Ficheiro.escrever(utilizadores);
                    return;
                }

                case 3: { // MODIFICA UTILIZADOR --> CHAMA O MÉTODO PARA MUDAR CADA VARIÁVEL DO UTILIZADOR EM QUESTÃO
                    modificar(index, utilizadores);
                    break;
                }

                case 4: { // GERIR MEDICAMENTOS
                    GerirMedicamentos.gerirMed(index, utilizadores);
                    break;
                }
                
                case 5: { // VOLTA PARA O MENU ANTERIOR
                    return;
                }

                default: // CASO SEJA NENHUMA OPÇÃO DAS ACIMA CITADAS
                    System.out.println("Opção inválida!!\nTente outra vez!!");
                    break;
            }
        }
    }

    public static void remover(int index, ArrayList<Utilizador> lista) {

        char op;
        System.out.print("are you sure about that?? (y/n): ");

        op = Read.Char();
        while ((op != 'y') || (op != 'n')) {
            switch (op) {
                case 'y':
                    lista.remove(index);
                    return;
                case 'n':
                    return;
                default:
                    System.out.println("op invalida");
                    break;
            }
        }       
    }

    public static void menu_Modificar() {
        System.out.println("<><><><><><><><><><><><><><><><><><>");
        System.out.println("                                    ");
        System.out.println("   Escolha o que pretende alterar   ");
        System.out.println("                                    ");
        System.out.println("<><><><><><><><><><><><><><><><><><>");
        System.out.println("            1. Nome                 ");
        System.out.println("            2. Género               ");
        System.out.println("            3. Idade                ");
        System.out.println("            4. Data de nascimento   ");
        System.out.println("            5. Terminar             ");
        System.out.println("<><><><><><><><><><><><><><><><><><>");
    }

    public static void modificar(int index, ArrayList<Utilizador> lista) //recebe a posição do utilizador na lista dos Utilizadores
    {
        int choice = 0;
        boolean ProgramOn = true;

        while (ProgramOn) {

            menu_Modificar();
            choice = Read.Int();

            switch (choice) {
                case 1: {
                    System.out.print("Introduza o novo nome: ");
                    String nome = Read.String();
                    lista.get(index).setNome(nome);
                    System.out.println("Utilizador modificado com sucesso.");
                    System.out.println(lista.get(index).toString());
                    System.out.println("\nEscolha outra opção...");
                    break;
                }

                case 2: {
                    System.out.print("Introduza o novo genero: ");
                    String genero = Read.String();
                    lista.get(index).setGenero(genero);
                    System.out.println("Utilizador modificado com sucesso.");
                    System.out.println(lista.get(index).toString());
                    System.out.println("\nEscolha outra opção...");
                    break;
                }

                case 3: {
                    System.out.print("Introduza a nova idade: ");
                    int idade = Read.Int();
                    lista.get(index).setIdade(idade);
                    System.out.println("Utilizador modificado com sucesso.");
                    System.out.println(lista.get(index).toString());
                    System.out.println("\nEscolha outra opção...");
                    break;
                }

                case 4: {
                    System.out.print("Introduza a nova data de nascimento: ");
                    int dia = Read.Int();
                    int mes = Read.Int();
                    int ano = Read.Int();
                    Data data_nascimento = new Data(dia, mes, ano);
                    lista.get(index).setDataNascimento(data_nascimento);
                    System.out.println("Utilizador modificado com sucesso.");
                    System.out.println(lista.get(index).toString());
                    System.out.println("\nEscolha outra opção...");
                    break;
                }

                case 5: {
                    System.out.println("\n");
                    Ficheiro.escrever(lista);
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