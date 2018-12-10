package comprimidos;

import java.util.*;

public class Main {
    
    public static void menuLogin() {
        System.out.println(
                  "|=====================|\n"
                + "|       EasyPill      |\n"
                + "|---------------------|\n"
                + "|   1.) Login         |\n"
                + "|   2.) Registar      |\n"
                + "|   3.) Acesso Admin  |\n"
                + "|   4.) Sair          |\n"
                + "|---------------------|\n");
    }

    /*
    login:
    - chamado no primeiro menu para o utilizador iniciar a sessao
    - nao retorna nada e nao recebe parametros
    - 
     */
    public static void login() throws ArrayVazio {
        ArrayList<Utilizador> utilizadores = Ficheiro.abrir();

        if (utilizadores.isEmpty()) throw new ArrayVazio("Não existem utilizadores registados!");
            
        int i;
        while (true) {
            System.out.println("Utilizadores disponiveis: \n");

            for (i = 0; i < utilizadores.size(); i++) {
                System.out.println(utilizadores.get(i).getId() + " - "
                        + utilizadores.get(i).getNome());
            }
            
            System.out.println(0 + " - Sair");
            System.out.print("\nIntroduza o Id do utilizador para fazer login: ");
            int u = Read.Int();
            
            if (u == 0) {
                System.out.println("Até à próxima!");
                System.exit(0);
            }
            
            for (i = 0; i < utilizadores.size(); i++) {
                if (u == utilizadores.get(i).getId()) {
                    break;
                }
            }

            String pass;
            if (i < utilizadores.size()) {
                int tentativas;
                for (tentativas = 0; tentativas < 3; tentativas++) {
                    System.out.print("Introduza password do utilizador " + utilizadores.get(i).getNome() + ": ");
                    pass = Read.String();
                    if (utilizadores.get(i).testarPassword(pass)) {
                        System.out.println("\nBem-vindo/a de volta, " + utilizadores.get(i).getNome() + "!\n");
                        GerirUtilizadores.gerirUtilizadores(i, utilizadores);   // menuUtilizadores
                        break;
                    }
                    
                    else
                        System.out.println("Password incorreta. Tem mais " + (2-tentativas) + " tentativa(s).");
                    
                }
                if(tentativas >= 3)
                System.out.println("Excedeu limite de tentativas!\nAté à próxima!");
                return;
            }
            
            if ( u == utilizadores.size())
                
                return;
            else{
            System.out.println("Esse utilizador não existe.\n");
            return;
            }
        }
    }
    
    public static void loginAdmin() {
        
        String pass;
        int tentativas;
        for (tentativas = 0; tentativas < 3; tentativas++) {
            System.out.print("#Admin: Introduza password: ");
            pass = Read.String();
            if (pass.equals("admin")) {
                System.out.println("\n#Admin: Entrou em modo admin.\n");
                
                ArrayList<Utilizador> lista = Ficheiro.abrir();
                
                while(true){
                    Admin.Admin_menuInicial();
                    System.out.print("Introduza uma opção: ");
                    int x = Read.Int();
                    
                    switch(x){
                        case 1:{
                            Admin.Admin_modificar(lista);
                            break;
                        }
                        case 2:{
                            Admin.Admin_remove(lista);
                            Ficheiro.escrever(lista);
                            return;
                        }
                        case 3:
                            return;
                        default:
                            System.out.println("Opção inválida\n");
                            
                    }
                }
            }
            
            else
                System.out.println("Password incorreta. Tem mais " + (2-tentativas) + " tentativa(s).");
        }
            if(tentativas >= 3)
                System.out.println("Excedeu limite de tentativas!\n");
                return;
    }

    public static void registar() {
        ArrayList<Utilizador> utilizadores = Ficheiro.abrir();
        Utilizador NovoUtilizador = new Utilizador();

        System.out.print("Nome: ");
        NovoUtilizador.setNome(Read.String());

        System.out.print("Genero: ");
        NovoUtilizador.setGenero(Read.String());

        System.out.print("Idade: ");
        NovoUtilizador.setIdade(Read.Int());

        System.out.println("Data de nascimento (dia, mês, ano):");
        int dia = Read.Int();
        int mes = Read.Int();
        int ano = Read.Int();
        Data d = new Data(dia, mes, ano);
        NovoUtilizador.setDataNascimento(d);

        System.out.print("Password: ");
        NovoUtilizador.setPassword(Read.String());
        NovoUtilizador.setId(utilizadores.size()+1);

        utilizadores.add(NovoUtilizador);
        Ficheiro.escrever(utilizadores);
        
        System.out.println("Utilizador registado com sucesso!");
    }

    public static void main(String[] args) {
        int op;
        GerirUtilizadores g = new GerirUtilizadores();
        
        //XMLSave.saveToXML("axml.xml");

        while (true) {
            menuLogin();
            System.out.print("Introduza uma opção: ");
            op = Read.Int();

            switch (op) {
                case 1:
                    try{
                    login();  
                    }
                    catch(ArrayVazio e){
                        System.out.println(e.getMessage());
                    }
                    break;
                    
                case 2:
                    registar();
                    break;
                    
                case 3:
                {
                    loginAdmin();
                    break;
                }
                
                case 4:
                    return;
                    
                default:
                    System.out.println("Opção inválida! Introduza nova opção.\n");
                    break;
            }
        }
    }
}