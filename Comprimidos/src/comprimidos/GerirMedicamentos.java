package comprimidos;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class GerirMedicamentos {

    public static void MenuMedicamentos() {
        System.out.println(
                "\n|==================================|\n"
                + "|           MEDICAMENTOS           |\n"
                + "|==================================|\n"
                + "|        Selecione uma opção       |\n"
                + "|----------------------------------|\n"
                + "|   1.) Adicionar Medicamento      |\n"
                + "|   2.) Remover Medicamento        |\n"
                + "|   3.) Modificar Medicamento      |\n"
                + "|   4.) Consultar                  |\n"
                + "|   5.) Listar                     |\n"
                + "|   6.) Voltar atrás               |\n"
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
                    listar(idUtilizador, utilizadores);
                    break;
                case 6:
                    Ficheiro.escrever(utilizadores);
                    return;

                default:
                    System.out.println("Opção Inválida\n");
            }
        }
    }

    public static void adicionar(int idUtilizador, ArrayList<Utilizador> utilizadores) {
        ArrayList<Medicamento> medicamentos = utilizadores.get(idUtilizador).getMedicamentos();
        int i;

        System.out.print("Introduza o nome do novo medicamento: ");
        String nome = Read.String();

        System.out.print("Introduza o código do novo medicamento: ");
        int codigo = Read.Int();

        for (i = 0; i < medicamentos.size(); i++) {
            if (medicamentos.get(i).getNome().equals(nome) || medicamentos.get(i).getId() == codigo) {
                System.out.println("\nO medicamento " + nome + " já existe!\nNão foi possível adicionar\n");
                return;
            }
        }

        System.out.print("Introduza a descrição do medicamento: ");
        String descricao = Read.String();

        System.out.print("Introduza a quantidade a tomar: ");
        int quantidade = Read.Int();

        System.out.print("Introduza a data da primeira toma no formato hh:mm, dd/mm/aa.\n");
        System.out.print("Hora: ");
        int horas = Read.Int();

        System.out.print("Minuto: ");
        int min = Read.Int();

        System.out.print("Dia: ");
        int dia = Read.Int();

        System.out.print("Mês: ");
        int mes = Read.Int();

        System.out.print("Ano: ");
        int ano = Read.Int();

        LocalDateTime prim_toma = LocalDateTime.of(ano, mes, dia, horas, min);

        System.out.print("Introduza o período de toma em horas: ");
        int periodoToma = Read.Int();
        
        ArrayList<LocalDateTime> tomas_futuras = new ArrayList();
        tomas_futuras.add(prim_toma);

        for (i = 0; i < quantidade-1; i++) {
            tomas_futuras.add(tomas_futuras.get(i).plusHours(periodoToma));
        }

        medicamentos.add(new Medicamento(codigo, nome, descricao, periodoToma, quantidade, tomas_futuras));
        utilizadores.get(idUtilizador).setMedicamentos(medicamentos);
        Ficheiro.escrever(utilizadores);
    }

    public static void consultar(int idUtilizador, ArrayList<Utilizador> utilizadores) {
        ArrayList<Medicamento> medicamentos = utilizadores.get(idUtilizador).getMedicamentos();
        int i;

        try {
            if (medicamentos.isEmpty()) {
                throw new ArrayVazio("Não existem medicamentos!");
            }

            for (i = 0; i < medicamentos.size(); i++) {
                System.out.println("\nMedicamento " + (i + 1)
                        + ":\n\tNome: " + medicamentos.get(i).getNome()
                        + "\n\tCódigo: " + medicamentos.get(i).getId() + "\n");
            }

            System.out.print("Qual o código do medicamento? ");
            int id = Read.Int();

            for (i = 0; i < medicamentos.size(); i++) {
                if (id == medicamentos.get(i).getId()) {
                    System.out.println(medicamentos.get(i).toString());
                    System.out.println(medicamentos.get(i).tomasToString());
                    break;
                }

                if (i >= medicamentos.size()) {
                    System.out.println("Medicamento não encontrado.");
                }
            }

        } catch (ArrayVazio e) {
            System.out.println(e.getMessage());
        }
    }

    public static void listar(int idUtilizador, ArrayList<Utilizador> utilizadores) {

        ArrayList<Medicamento> medicamentos = utilizadores.get(idUtilizador).getMedicamentos();

        try {
            if (medicamentos.isEmpty()) {
                throw new ArrayVazio("Não existem medicamentos!");
            }

            for (int i = 0; i < medicamentos.size(); i++) {
                System.out.println("Medicamento " + (i + 1)
                        + ":\n\tNome: " + medicamentos.get(i).getNome()
                        + "\n\tCódigo: " + medicamentos.get(i).getId() + "\n");
            }

        } catch (ArrayVazio e) {
            System.out.println(e.getMessage());
        }
    }

    public static void remover(int idUtilizador, ArrayList<Utilizador> utilizadores) {

        ArrayList<Medicamento> medicamentos = utilizadores.get(idUtilizador).getMedicamentos();
        int i;

        try {
            if (medicamentos.isEmpty()) {
                throw new ArrayVazio("Não existem medicamentos!");
            }

            System.out.println("Medicamentos registados\n");
            for (i = 0; i < medicamentos.size(); i++) {
                System.out.println("Medicamento " + (i + 1) + "\n\tNome: " + medicamentos.get(i).getNome()
                        + "\n\tCódigo: " + medicamentos.get(i).getId() + "\n");
            }

            System.out.print("Qual o código do medicamento? ");
            int id = Read.Int();

            for (i = 0; i < medicamentos.size(); i++) {
                if (id == medicamentos.get(i).getId()) {
                    medicamentos.remove(i);
                    System.out.println("Medicamento removido.");
                    utilizadores.get(idUtilizador).setMedicamentos(medicamentos);
                    Ficheiro.escrever(utilizadores);
                    return;
                }
            }
            System.out.println("Medicamento não encontrado.");
        } catch (ArrayVazio e) {
            System.out.println(e.getMessage());
        }
    }

    private static void showMedicamentos(ArrayList<Medicamento> medicamentos) {
        int i;
        System.out.println("<><><><><><><><><><><><><><><><><><>");
        System.out.println("  Escolha o medicamento a alterar   ");
        System.out.println("<><><><><><><><><><><><><><><><><><>");

        for (i = 0; i < medicamentos.size(); i++) {
            System.out.println("\nMedicamento " + (i + 1) + ": " + "\n\tNome: " + medicamentos.get(i).getNome() + "\n\tCódigo: " + medicamentos.get(i).getId());
        }

        System.out.println("0" + " - " + " Sair");
        System.out.println("<><><><><><><><><><><><><><><><><><>");
    }

    public static void menuModificar() {
        System.out.println("<><><><><><><><><><><><><><><><><><>");
        System.out.println("   Escolha o que pretende alterar   ");
        System.out.println("<><><><><><><><><><><><><><><><><><>");
        System.out.println("        1. Nome do medicamento      ");
        System.out.println("        2. Quantidade               ");
        System.out.println("        3. Período de toma          ");
        System.out.println("        4. Terminar                 ");
        System.out.println("<><><><><><><><><><><><><><><><><><>\n");
    }

    public static void modificar(int idUtilizador, ArrayList<Utilizador> utilizadores) {
        int choice = 0;

        // este arraylsit e apenas um arrylist auxiliar
        // ao alterar, e necessario atualizar o arraylist do utilizador com setMedicamentos
        ArrayList<Medicamento> medicamentos = utilizadores.get(idUtilizador).getMedicamentos();

        try {
            if (medicamentos.isEmpty()) {
                throw new ArrayVazio("Não existem medicamentos!");
            }

            while (true) {

                menuModificar();
                System.out.print("Introduza uma opção: ");
                choice = Read.Int();

                switch (choice) {

                    // mudar nome medicamento
                    case 1: {
                        showMedicamentos(medicamentos);
                        System.out.println("Código do medicamento: ");
                        int id = Read.Int();

                        if (id == 0) {
                            return;
                        }

                        int i;
                        for (i = 0; i < medicamentos.size(); i++) {
                            if (id == medicamentos.get(i).getId()) {

                                System.out.print("Introduza o novo nome: ");
                                String nome = Read.String();

                               medicamentos.get(i).setNome(nome);

                                // atualizar o medicamento com as novas tomas do utilizador
                                utilizadores.get(idUtilizador).setMedicamentos(medicamentos);
                                // guardar atualizaçoes do arraylist utilizador no ficheiro
                                Ficheiro.escrever(utilizadores);

                                System.out.println("Medicamento modificado com sucesso.");
                                System.out.println(medicamentos.get(i));

                                break;
                            }
                        }

                        if (i >= medicamentos.size()) {
                            System.out.println("Medicamento não encontrado.");
                        }

                        System.out.println("\nEscolha outra opção...");
                        break;
                    }

                    // modificar quantidade
                    case 2: {
                        showMedicamentos(medicamentos);
                        System.out.println("Código do medicamento: ");

                        int id = Read.Int();
                        int i;

                        for (i = 0; i < medicamentos.size(); i++) {
                            if (id == medicamentos.get(i).getId()) {
                                System.out.print("Introduza a nova quantidade: ");

                                int quantidade = Read.Int();

                                // guarda a primeira toma
                                LocalDateTime primeiraToma = medicamentos.get(i).getTomas_Futuras().get(0);

                                // cria novo array list de tomas
                                ArrayList<LocalDateTime> novasTomas = new ArrayList();

                                // variavel auxiliar para guardar periodo de toma
                                int periodoToma = medicamentos.get(i).getPeriodo_toma();

                                // adicionar a primeira toma ao novo arraylist
                                novasTomas.add(primeiraToma);

                                // voltar a calcular tomas futuras
                                for (int j = 0; j < quantidade-1; j++) {
                                    novasTomas.add(novasTomas.get(j).plusHours(periodoToma));
                                }
                                
                                medicamentos.get(i).setQuantidade(quantidade);
                                // atulizar as tomas futuras do medicamento
                                medicamentos.get(i).setTomas_Futuras(novasTomas);

                                // atualizar o medicamento com as novas tomas do utilizador
                                utilizadores.get(idUtilizador).setMedicamentos(medicamentos);

                                // guardar atualizaçoes do arraylist utilizador no ficheiro
                                Ficheiro.escrever(utilizadores);

                                System.out.println("Medicamento modificado com sucesso.");
                                System.out.println(medicamentos.get(i));
                                break;
                            }
                        }
                        //  Verificar se encontrou o medicamento
                        if (i >= medicamentos.size()) {
                            System.out.println("Medicamento não encontrado");
                        }

                        System.out.println("\nEscolha outra opção...");
                        break;
                    }

                    // modificar novo periodo de toma
                    case 3: {
                        showMedicamentos(medicamentos);

                        System.out.println("Código do medicamento: ");
                        int codigoMedicamento = Read.Int();
                        int i;

                        for (i = 0; i < medicamentos.size(); i++) {
                            if (codigoMedicamento == medicamentos.get(i).getId()) {
                                System.out.print("Introduza o novo o período de toma: ");
                                int novoPeriodoToma = Read.Int();

                                // variavel auxilar com quantidade a tomar
                                int quantidade = medicamentos.get(i).getQuantidade();
                                // variavel auxiliar com primeira toma
                                LocalDateTime primeiraToma = medicamentos.get(i).getTomas_Futuras().get(0);

                                // criar novo arraylist com as novas tomas
                                ArrayList<LocalDateTime> novasTomas = new ArrayList();

                                // adicinar primeira toma ao novo array list
                                novasTomas.add(primeiraToma);

                                /*  na primeira iteraçao vai adicionar o numero de horas do novo periodo à primeira toma
                                    essa data vai ser adicionada ao novo array com as tomas
                                
                                    isto e repetido com a ultima data adicionada ao array 
                                    (neste caso, novasTomas.get(i) foi o ultimo a ser adicionada)     
                                 */
                                for (int j = 0; j < quantidade-1; j++) {
                                    novasTomas.add(novasTomas.get(j).plusHours(novoPeriodoToma));
                                }
                                
                                // atualizar arrays alterados
                                medicamentos.get(i).setPeriodo_toma(novoPeriodoToma);
                                medicamentos.get(i).setTomas_Futuras(novasTomas);
                                utilizadores.get(idUtilizador).setMedicamentos(medicamentos);
                                Ficheiro.escrever(utilizadores);
                                
                                System.out.println("Medicamento modificado com sucesso.");
                                System.out.println(utilizadores.get(idUtilizador).getMedicamentos().get(i));
                                break;
                            }
                        }
                        //  Verificar se encontrou o medicamento.
                        if (i >= medicamentos.size())
                            System.out.println("Medicamento não encontrado");

                        System.out.println("\nEscolha outra opção...");
                        break;
                    }

                    case 4:
                        Ficheiro.escrever(utilizadores);
                        return;

                    default: {
                        System.out.println("Opção inválida.");
                        break;
                    }
                }
            }
        } catch (ArrayVazio e) {
            System.out.println(e.getMessage());
        }
    }
}
