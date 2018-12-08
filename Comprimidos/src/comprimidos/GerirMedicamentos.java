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
            MenuMedicamentos();
            op = Read.Int();
            switch (op) {
                // adicionar medicamento
                case 1:
                    adicionar(idUtilizador, utilizadores);
                    break;

                // remover medicamento
                case 2:
                    remover(idUtilizador, utilizadores);
                    break;

                // modificar medicamento
                case 3:
                    break;

                //consultar todos os medicamntos
                case 4:
                    try {
                        utilizadores.get(idUtilizador).findDroga();
                    } catch (ArrayVazio e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                /* 
                    no fim de fazer as alteraçoes necessarias aos medicamentos, 
                    alteraçoes feitas ao arraylist utilizadores sao guardadas no 
                    ficheiro e retorna-se para o menu MenuUtilizadores() em gerirUtilizadores
                 */
                case 5:

                    Ficheiro.escrever(utilizadores);
                    return;

                default:
                    System.out.println("Opçao Invalida\n");
            }
        }
    }

    public static void menu_PeriodoToma() {
        System.out.println("Indique o qual o tipo de periodo de toma:\n1.) x em x horas\n2.) x em x dias\n");
    }

    public static void adicionar(int idUtilizador, ArrayList<Utilizador> utilizadores) {
        System.out.print("Introduza nome do novo medicamento: ");
        String nome = Read.String();

        System.out.print("Introduza o codigo do medicamento: ");
        int codigo = Read.Int();

        System.out.print("Medicamento e tomado de hora a hora ou de dias a dias(h/d): ");
        char op = Read.Char();

        System.out.println("Primeira toma: ");

        System.out.println("minutos: ");
        int minutos = Read.Int();

        System.out.println("hora: ");
        int hora = Read.Int();

        System.out.println("dia: ");
        int dia = Read.Int();

        System.out.println("mes: ");
        int mes = Read.Int();

        Data primeiraToma = new Data(minutos, hora, dia, mes, 0);

        System.out.println("Introduza a quantidade a tomar: ");
        int quantidade = Read.Int();

        System.out.print("Introduza a descriçao do medicamento: ");
        String descricao = Read.String();

        /*
            - dar a escolher ao utilizador se quer tomar o medicamento 
              de x em x horas (ex: 12 em 12 horas) ou de x em x dias (ex 7 em 7 dias)
            - se utilizador escolhe de x em x horas, e criada uma nova data
              com todas as variaveis a 0, e horas com o valor introduzido
            - se utilizador escolhe de x em x dias, e criada uma nova data
              com todas as variaveis a 0, e dias com o valor introduzido
            - se e introduzida uma opçao invalida, o utilizador tem de voltar a 
              introduzir se perfere dias ou horas  
         */
        while (true) {

            /* eu odeio java
            
                entao esta linguagem mal parida nao me deixa declarar duas variaveis
                com o mesmo nome para cases diferentes, dai eu ter duas datas com 
                nome diferentes para a mesma coisa
            
                MAS
            
                estas variaveis so sao locais dentro do switch, isso quer dizer 
                que nao as posso utiliza-as fora do switch OU SEJA: A ULTIMA 
                COISA QUE EU VOU LER E O PERIODO DE TOMA E VOU TER DE ADICIONAR
                O NOVO MEDICAMENTO DENTRO DO SWITCH
             */
            switch (op) {

                case 'h':
                    System.out.println("Tomar medicamento de quantas em quantas horas?");
                    int horasToma = Read.Int();
                    Data periodoTomaHoras = new Data(horasToma, 0);

                    Medicamento m1 = new Medicamento(codigo, nome, descricao, periodoTomaHoras, primeiraToma, quantidade);
                    utilizadores.get(idUtilizador).getMedicamentos().add(m1);

                    return;

                case 'd':
                    System.out.println("Tomar medicamento de quantos em quantos dias?");
                    int diasToma = Read.Int();
                    Data periodoTomaDias = new Data(0, diasToma);

                    Medicamento m2 = new Medicamento(codigo, nome, descricao, periodoTomaDias, primeiraToma, quantidade);
                    utilizadores.get(idUtilizador).getMedicamentos().add(m2);

                    return;

                default:
                    System.out.println("Opção Inválida");
            }
            break;
        }
    }

    public static void remover(int idUtilizador, ArrayList<Utilizador> utilizadores) {

        utilizadores.get(idUtilizador).getMedicamentos().remove();
    }

    public static void menuModificar() {
        System.out.println("<><><><><><><><><><><><><><><><><><>");
        System.out.println("                                    ");
        System.out.println("   Escolha o que pretende alterar   ");
        System.out.println("                                    ");
        System.out.println("<><><><><><><><><><><><><><><><><><>");
        System.out.println("        1. Nome do medicamento      ");
        System.out.println("        2. Quantidade               ");
        System.out.println("        3. Periodo de toma          ");
        System.out.println("        4. Data de nascimento       ");
        System.out.println("        5. Terminar                 ");
        System.out.println("<><><><><><><><><><><><><><><><><><>");
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
