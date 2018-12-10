package comprimidos;

/* 1º PRIMEIRA TOMA
   2º QUANTAS TOMAS (X)
   3º GUARDAR 1ª TOMA E FAZER LOGO X*PERIODO_DE_TOMA P/ GUARDAR TODO O PREVISTO HISTÓRICO DE TOMA
   4º VERIFICAR TOMAS JÁ PASSADAS CASO A PESSOA NÃO ESTEJA PRESENTE NA HORA DOS ALERTAS
*/

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

    public static void adicionar(int idUtilizador, ArrayList<Utilizador> utilizadores) {
        System.out.print("Introduza nome do novo medicamento: ");
        String nome = Read.String();

        System.out.print("Introduza o codigo do medicamento: ");
        int codigo = Read.Int();
        
        System.out.print("Introduza a descriçao do medicamento: ");
        String descricao = Read.String();

        System.out.print("Introduza a quantidade a tomar: ");
        int quantidade = Read.Int();
        
        
        System.out.print("Medicamento é tomado de hora a hora ou de dias a dias(h/d): ");
        char op = Read.Char();
        
        System.out.print("Introduza a data da primeira toma no formato hh:mm, dd/mm/aa.\n");
        System.out.print("Hora: ");
        int h = Read.Int();
        System.out.print("Minuto: ");
        int min = Read.Int();
        System.out.print("Dia: ");
        int d = Read.Int();
        System.out.print("Mês: ");
        int m = Read.Int();
        System.out.print("Ano: ");
        int a = Read.Int();
        Data prim_toma = new Data (h,min,d,m,a);
        
        ArrayList<Data> tomas_futuras = new ArrayList();
        tomas_futuras.add(prim_toma);

        while (true) {

            switch (op) {

                case 'h':
                    System.out.println("Tomar medicamento de quantas em quantas horas?");
                    int horasToma = Read.Int();

                    Medicamento m1 = new Medicamento(codigo, nome, descricao, horasToma, quantidade, prim_toma);
                    utilizadores.get(idUtilizador).getMedicamentos().add(m1);

                    return;

                case 'd':
                    System.out.println("Tomar medicamento de quantos em quantos dias?");
                    int diasToma = Read.Int();
                    int periodoTomaDias = 24*diasToma;
                    System.out.println("O medicamento vai ser tomado de " + periodoTomaDias + " horas em " + periodoTomaDias + " horas.");

                    Medicamento m2 = new Medicamento(codigo, nome, descricao, periodoTomaDias, quantidade, prim_toma);
                    utilizadores.get(idUtilizador).getMedicamentos().add(m2);

                    return;

                default:
                    System.out.println("Opção Inválida");
            }
            break;
        }
        
        for (int i=0; i<quantidade-1; i++)
        {
            int m1_m2 = utilizadores.get(idUtilizador).getMedicamentos().size()-1; // descobre qual a posiçao do ultimo medicamento 
            Medicamento med = utilizadores.get(idUtilizador).getMedicamentos().get(m1_m2);  // qual o ultimo medicamento
            
            int new_hora = prim_toma.getHora() + med.getPeriodo_toma();
            int new_dia = 0;
            int new_mes = 0;
            int new_ano= 0;
            
            while (new_hora > 24)
            {
                int new_h = new_hora - 24;
                new_dia++;
                
                new_hora = new_h;
            }
            
            while (new_dia > 30)
            {
                int new_d = new_dia - 30;
                new_mes++;
                
                new_dia = new_d;
            }
            
            while (new_mes > 12)
            {
                int new_m = new_mes - 12;
                new_ano++;
                
                new_mes = new_m;
            }
                
            int new_minutos = prim_toma.getMinuto();
            
            Data new_toma = new Data(new_minutos, new_hora, new_dia, new_mes, new_ano);
                                // minutos da primeira toma 
                                
            tomas_futuras.add(new_toma);
            
            prim_toma = new_toma;
        }
        
        utilizadores.get(idUtilizador).setTomas_Futuras(tomas_futuras);
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

            System.out.print("Qual o código do medicamento? ");
            int id = Read.Int();

            for (i = 0; i < medicamentos.size(); i++) {
                if (id == medicamentos.get(i).getid()) {
                    System.out.println(medicamentos.get(i).toString() + "\n");
                }
            }

            if (i > medicamentos.size()) {
                throw new ArrayVazio("Medicamento não existe!");
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
                throw new ArrayVazio("Não existem medicamentos!");

            System.out.println("Medicamentos registados\n");
            for (i = 0; i < medicamentos.size(); i++) 
                System.out.println("Nome: " + medicamentos.get(i).getNome()
                        + "\nCódigo: " + medicamentos.get(i).getid() + "\n");

            System.out.print("Qual o código do medicamento? ");
            int id = Read.Int();

            for (i = 0; i < medicamentos.size(); i++)
                if (id == medicamentos.get(i).getid())
                    medicamentos.remove(i);
            
            if (i > medicamentos.size())
                throw new ArrayVazio("Medicamento não existe!");
            
        } catch (ArrayVazio e) {
            System.out.println(e.getMessage());
        }
    }

    private static void showMedicamentos(ArrayList<Medicamento> medicamentos){
        int i;
        System.out.println("<><><><><><><><><><><><><><><><><><>");
        System.out.println("   Escolha o que pretende alterar   ");
        System.out.println("<><><><><><><><><><><><><><><><><><>");
        for (i = 0; i < medicamentos.size(); i++)
            System.out.println( medicamentos.get(i));
        System.out.println((medicamentos.size() +1) + " - " + " Sair" );
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