package comprimidos;

/* 1º PRIMEIRA TOMA
   2º QUANTAS TOMAS (X)
   3º GUARDAR 1ª TOMA E FAZER LOGO X*PERIODO_DE_TOMA P/ GUARDAR TODO O PREVISTO HISTÓRICO DE TOMA
   4º VERIFICAR TOMAS JÁ PASSADAS CASO A PESSOA NÃO ESTEJA PRESENTE NA HORA DOS ALERTAS
 */
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
                + "|   1.) Adicionar Medicamento      |\n" // perguntar qual sera o tipo de medicamento
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

        for (i = 0; i < quantidade; i++) {
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
                System.out.println("\nMedicamento " + (i + 1) + 
                        ":\n\tNome: " + medicamentos.get(i).getNome() + 
                        "\n\tCódigo: " + medicamentos.get(i).getId() + "\n");
            }

            System.out.print("Qual o código do medicamento? ");
            int id = Read.Int();

            for (i = 0; i < medicamentos.size(); i++) {
                if (id == medicamentos.get(i).getId()) {
                    System.out.println(medicamentos.get(i).toString() + "\n");
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
            if (medicamentos.isEmpty())
                throw new ArrayVazio("Não existem medicamentos!");

            for (int i = 0; i < medicamentos.size(); i++) 
                System.out.println("Medicamento " + (i + 1) + 
                        ":\n\tNome: " + medicamentos.get(i).getNome() + 
                        "\n\tCódigo: " + medicamentos.get(i).getId() + "\n");

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
        
        for (i = 0; i < medicamentos.size(); i++)
            System.out.println("\nMedicamento " + (i + 1) + ": " + "\n\tNome: " + medicamentos.get(i).getNome() + "\n\tCódigo: " + medicamentos.get(i).getId());
       
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
                    case 1: {
                        showMedicamentos(utilizadores.get(idUtilizador).getMedicamentos());
                        System.out.println("Código do medicamento: ");
                        int id = Read.Int();

                        if (id == 0) {
                            return;
                        }

                        int i;
                        for (i = 0; i < utilizadores.get(idUtilizador).getMedicamentos().size(); i++) {
                            if (id == utilizadores.get(idUtilizador).getMedicamentos().get(i).getId()) {
                                System.out.print("Introduza o novo nome: ");
                                String nome = Read.String();
                                utilizadores.get(idUtilizador).getMedicamentos().get(i).setNome(nome);
                                System.out.println("Medicamento modificado com sucesso.");
                                System.out.println(utilizadores.get(idUtilizador).getMedicamentos().get(i));
                                break;
                            }
                        }

                        if (i >= utilizadores.get(idUtilizador).getMedicamentos().size()) {
                            System.out.println("Medicamento não encontrado.");
                        }

                        System.out.println("\nEscolha outra opção...");
                        break;
                    }

                    
                    ////////////////////////////////////////////////////////////////////////////////
                       // TEM DE CALCULAR AS PROXIMAS TOMAS OUTRA VEZ, DE ACORDO COM A NOVA QUANTIDADE
                    ////////////////////////////////////////////////////////////////////////////////
                    case 2: {
                        showMedicamentos(utilizadores.get(idUtilizador).getMedicamentos());
                        System.out.println("Código do medicamento: ");
                        int id = Read.Int();
                        int i;
                        for (i = 0; i < utilizadores.get(idUtilizador).getMedicamentos().size(); i++) {
                            if (id == utilizadores.get(idUtilizador).getMedicamentos().get(i).getId()) {
                                System.out.print("Introduza a nova quantidade: ");
                                int quanti = Read.Int();
                                utilizadores.get(idUtilizador).getMedicamentos().get(i).setQuantidade(quanti);
                                
                                // GUARDA A PRIMEIRA TOMA
                                LocalDateTime prim_toma = utilizadores.get(idUtilizador).getMedicamentos().get(id).getTomas_Futuras().get(0);
                                ArrayList<LocalDateTime> tomas_futuras_modified = null;
                                
                                // ADICIONA A PRIMEIRA TOMA AO NOVO ARRAYLIST DE TOMAS FUTURAS
                                tomas_futuras_modified.add(prim_toma);
                                
                                // VOLTA A CALCULAR AS TOMAS FUTURAS
                                for (i = 0; i < quanti ; i++) 
                                 tomas_futuras_modified.add(tomas_futuras_modified.get(i).plusHours(utilizadores.get(idUtilizador).getMedicamentos().get(id).getPeriodo_toma()));
                                
                                // FAZ-SE O SETTOMAS_FUTURAS PARA FICAR C ESTE ARRAYLIST
                                utilizadores.get(idUtilizador).getMedicamentos().get(id).setTomas_Futuras(tomas_futuras_modified);
                                
                                System.out.println("Medicamento modificado com sucesso.");
                                System.out.println(utilizadores.get(idUtilizador).getMedicamentos().get(i));
                                break;
                            }
                        }
                        if (i >= utilizadores.get(idUtilizador).getMedicamentos().size()) //  Verificar se encontrou o medicamento.
                            System.out.println("Medicamento não encontrado");
                        

                        System.out.println("\nEscolha outra opção...");
                        break;
                    }
                    

                    /////////////////////////////////////////////////////////////////////////////////////
                       // TEM DE CALCULAR AS PROXIMAS TOMAS OUTRA VEZ, DE ACORDO COM O NOVO PERIODO DE TOMA 
                    //////////////////////////////////////////////////////////////////////////////////////
                    case 3: {
                        showMedicamentos(utilizadores.get(idUtilizador).getMedicamentos());
                        System.out.println("Código do medicamento: ");
                        int id = Read.Int();
                        int i;
                        for (i = 0; i < utilizadores.get(idUtilizador).getMedicamentos().size(); i++) {
                            if (id == utilizadores.get(idUtilizador).getMedicamentos().get(i).getId()) {
                                System.out.print("Introduza o novo o período de toma: ");
                                int pt = Read.Int();
                                ArrayList<LocalDateTime> tomas_futuras = utilizadores.get(i).getMedicamentos().get(id).getTomas_Futuras();
                                int quantidade = utilizadores.get(i).getMedicamentos().get(id).getQuantidade();
                                
                                for (i=1; i<tomas_futuras.size(); i++)  // apaga as tomas futuras, menos a prim_toma.
                                    tomas_futuras.remove(i);
                                
                                for (i = 0; i < quantidade; i++) {      // volta a calculá-las.
                                 tomas_futuras.add(tomas_futuras.get(i).plusHours(pt));
                            }
                                utilizadores.get(idUtilizador).getMedicamentos().get(i).setPeriodo_toma(pt);
                                System.out.println("Medicamento modificado com sucesso.");
                                System.out.println(utilizadores.get(idUtilizador).getMedicamentos().get(i));
                                break;
                            }
                        }
                        if (i >= utilizadores.get(idUtilizador).getMedicamentos().size()) //  Verificar se encontrou o medicamento.
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
