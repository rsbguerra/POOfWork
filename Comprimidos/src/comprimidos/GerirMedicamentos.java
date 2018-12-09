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
            System.out.print("Introduza uma opção: ");
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
                    modificar(idUtilizador, utilizadores);
                    break;

                //consultar todos os medicamntos
                case 4:
                    consultar(idUtilizador, utilizadores);
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

            switch (op) {

                case 'h':
                    System.out.println("Tomar medicamento de quantas em quantas horas?");
                    int horasToma = Read.Int();
                    Data periodoTomaHoras = new Data(horasToma, 0);

                    Medicamento m1 = new Medicamento(codigo, nome, descricao, periodoTomaHoras, quantidade);
                    utilizadores.get(idUtilizador).getMedicamentos().add(m1);

                    return;

                case 'd':
                    System.out.println("Tomar medicamento de quantos em quantos dias?");
                    int diasToma = Read.Int();
                    Data periodoTomaDias = new Data(0, diasToma);

                    Medicamento m2 = new Medicamento(codigo, nome, descricao, periodoTomaDias, quantidade);
                    utilizadores.get(idUtilizador).getMedicamentos().add(m2);

                    return;

                default:
                    System.out.println("Opção Inválida");
            }
            break;
        }
    }

    public static void consultar(int idUtilizador, ArrayList<Utilizador> utilizadores) {
        ArrayList<Medicamento> medicamentos = utilizadores.get(idUtilizador).getMedicamentos();
        int i;

        try {
            if (medicamentos.isEmpty()) throw new ArrayVazio("Nao existem medicamentos!");
            

            for (i = 0; i < medicamentos.size(); i++) {
                System.out.println("nome: " + medicamentos.get(i).getNome()
                        + "\ncodigo: " + medicamentos.get(i).getid() + "\n");
            }

            System.out.print("Qual o medicamento? (insira o codigo): ");
            int id = Read.Int();

            for (i = 0; i < medicamentos.size(); i++) {
                if (id == medicamentos.get(i).getid()) {
                    System.out.println(medicamentos.get(i).toString() + "\n");
                }
            }

            if (i > medicamentos.size()) {
                throw new ArrayVazio("Medicamento nao existe!");
            }

        } catch (ArrayVazio e) {
            System.out.println(e.getMessage());
        }
    }

    public static void remover(int idUtilizador, ArrayList<Utilizador> utilizadores) {
        
        ArrayList<Medicamento> medicamentos = utilizadores.get(idUtilizador).getMedicamentos();
        int i;

        try {
            if (medicamentos.isEmpty()) 
                throw new ArrayVazio("Nao existem medicamentos!");

            for (i = 0; i < medicamentos.size(); i++) 
                System.out.println("nome: " + medicamentos.get(i).getNome()
                        + "\ncodigo: " + medicamentos.get(i).getid() + "\n");

            System.out.print("Qual o medicamento? (insira o codigo): ");
            int id = Read.Int();

            for (i = 0; i < medicamentos.size(); i++)
                if (id == medicamentos.get(i).getid())
                    medicamentos.remove(i);
            
            if (i > medicamentos.size())
                throw new ArrayVazio("Medicamento nao existe!");
            
        } catch (ArrayVazio e) {
            System.out.println(e.getMessage());
        }
    }

    private static void showMedicamentos(ArrayList<Medicamento> medicamentos){
        int i;
        System.out.println("<><><><><><><><><><><><><><><><><><>");
        System.out.println("                                    ");
        System.out.println("   Escolha o que pretende alterar   ");
        System.out.println("                                    ");
        System.out.println("<><><><><><><><><><><><><><><><><><>");
        for (i = 0; i < medicamentos.size(); i++)
            System.out.println( medicamentos.get(i));
        System.out.println((medicamentos.size() +1) + " - " + " Sair" );
        System.out.println("<><><><><><><><><><><><><><><><><><>");
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
        System.out.println("        4. Terminar                 ");
        System.out.println("<><><><><><><><><><><><><><><><><><>");
    }

    public static void modificar(int idUtilizador, ArrayList<Utilizador> utilizadores) {
        int choice = 0;
        boolean ProgramOn = true;

        while (ProgramOn) {

            menuModificar();
            System.out.print("Introduza uma opção: ");
            choice = Read.Int();

            switch (choice) {
                case 1: {
                    showMedicamentos(utilizadores.get(idUtilizador).getMedicamentos());
                    System.out.println("Código do medicamento: ");
                    int id = Read.Int();
                    int i;
                    for (i = 0; i < utilizadores.get(idUtilizador).getMedicamentos().size(); i++) {
                        if(id == utilizadores.get(idUtilizador).getMedicamentos().get(i).getid()){
                                System.out.print("Introduza o novo nome: ");
                                String nome = Read.String();
                                utilizadores.get(idUtilizador).getMedicamentos().get(i).setNome(nome);
                                break;
                        }
                    }
                    if(i == utilizadores.get(idUtilizador).getMedicamentos().size())  //  Verificar se encontrou o medicamento.
                        System.out.println("Medicamento não encontrado");
                    else {
                    System.out.println("Medicamento modificado com sucesso.");
                    System.out.println(utilizadores.get(idUtilizador).getMedicamentos().get(0));
                    }
                    System.out.println("\nEscolha outra opção...");
                    break;
                    
                }

                case 2: {
                    showMedicamentos(utilizadores.get(idUtilizador).getMedicamentos());
                    System.out.println("Código do medicamento: ");
                    int id = Read.Int();
                    int i;
                    for (i = 0; i < utilizadores.get(idUtilizador).getMedicamentos().size(); i++) {
                        if(id == utilizadores.get(idUtilizador).getMedicamentos().get(i).getid()){
                                System.out.print("Introduza a nova quantidade: ");
                                int quanti = Read.Int();
                                utilizadores.get(idUtilizador).getMedicamentos().get(i).setQuantidade(quanti);
                                break;
                        } 
                    }
                    if(i == utilizadores.get(idUtilizador).getMedicamentos().size())  //  Verificar se encontrou o medicamento.
                        System.out.println("Medicamento não encontrado");
                    else {
                    System.out.println("Medicamento modificado com sucesso.");
                    System.out.println(utilizadores.get(idUtilizador).getMedicamentos().get(0));
                    }
                    System.out.println("\nEscolha outra opção...");
                    break;
                }

                case 3: {
                    showMedicamentos(utilizadores.get(idUtilizador).getMedicamentos());
                    System.out.println("Código do medicamento: ");
                    int id = Read.Int();
                    int i;
                    for (i = 0; i < utilizadores.get(idUtilizador).getMedicamentos().size(); i++) {
                        if(id == utilizadores.get(idUtilizador).getMedicamentos().get(i).getid()){
                                System.out.print("Introduza o novo o período de toma: ");
                                int pt = Read.Int();
                                utilizadores.get(idUtilizador).getMedicamentos().get(i).setPeriodo_toma(pt);
                                break;
                        }
                    }
                    if(i == utilizadores.get(idUtilizador).getMedicamentos().size())  //  Verificar se encontrou o medicamento.
                        System.out.println("Medicamento não encontrado");
                    else {
                    System.out.println("Medicamento modificado com sucesso.");
                    System.out.println(utilizadores.get(idUtilizador).getMedicamentos().get(0));
                    }
                    System.out.println("\nEscolha outra opção...");
                    break;
                }
                
                case 4:
                       return;

                default: {
                    System.out.println("Opção inválida.");
                    break;
                }
            }
        }

    }
}
