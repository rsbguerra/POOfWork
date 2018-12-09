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

                // mostra toda a informaçao do utilizador
                case 1: {
                    System.out.println((utilizadores.get(index)).toString());
                    break;
                }

                // remover() volta ao menu principal
                case 2: {
                    remover(index, utilizadores);
                    Ficheiro.escrever(utilizadores);
                    return;
                }

                // chama metodo modificar(), que chama
                case 3: {
                    modificar(index, utilizadores);
                    break;
                }

                /*
                    chama metodo gerirMed(int idUtilizador, ArrayList<Utilizador> utilizadores)
                    na classe GerirMedicamentos
                */
                case 4: {
                    GerirMedicamentos.gerirMed(index, utilizadores);
                    break;
                }

                /* 
                    no fim de fazer as alteraçoes necessarias ao utilizador, 
                    alteraçoes feitas ao arraylist utilizadores sao guardadas no 
                    ficheiro e retorna-se para o menu MenuUtilizadores() ainda nesta classe
                 */
                case 5: {
                    Ficheiro.escrever(utilizadores);
                    return;
                }

                default:
                    System.out.println("Opção inválida! Introduzir nova opção\n");
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
    
    public static void menuModificar() {
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
    
    /*
        - recebe a posição do utilizador na lista dos Utilizadores 
          e a lista dos utilizadores
        - chama menuModificar() para o utilizador escolher o campo a alterar
        - no fim de cada alteraçao mostra toda a informaçao do utilizador com 
          os dados alterados
    */
    public static void modificar(int index, ArrayList<Utilizador> lista) 
    {
        int choice;
        
        // removi a var ProgramOn pois o valor desta nunca e alterado
        while (true) {

            menuModificar();
            choice = Read.Int();

            switch (choice) {
                
                // modificar nome
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
                
                /* 
                    no fim de fazer as alteraçoes necessarias ao utilizador, 
                    alteraçoes feitas ao arraylist utilizadores sao guardadas no 
                    ficheiro e retorna-se para o menu MenuUtilizadores() ainda nesta classe
                 */
                
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
