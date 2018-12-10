package comprimidos;

import java.util.*;

public class Main {
    
    public static void menuLogin() {
        System.out.println(
                  "|=====================|\n"
                + "|      Bem-vindo      |\n"
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
            
            System.out.println((i+1) + " - Sair");
            System.out.print("\nIntroduza o ID do utilizador para fazer login: ");
            int u = Read.Int();
            
            
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
                        System.out.println("\nBem-vindo de volta, " + utilizadores.get(i).getNome() + "!\n");
                        GerirUtilizadores.gerirUtilizadores(i, utilizadores);   // menuUtilizadores
                        break;
                    }
                    
                }
                if(tentativas >= 3)
                System.out.println("Excedeu limite de tentativas!\nAté à próxima!");
                return;
            }
            
            if ( u == (utilizadores.size()+1))
                
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
                Admin.Admin_menuInicial();
                int x = Read.Int();
                while(x != 3){
                    switch(x){
                        case 1:
                            break;
                        case 2:
                            break;
                        case 3:
                            break;
                        default:
                            System.out.println("Opção inválida\n");
                            
                    }
                    Admin.Admin_menuInicial();
                    x = Read.Int();
                }
                break;
            }
        }
            if(tentativas >= 3)
                System.out.println("Excedeu limite de tentativas!\nAté à próxima!");
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

        System.out.print("Password: ");
        NovoUtilizador.setPassword(Read.String());

        System.out.println("Data de nascimento (dia, mês, ano):");
        int dia = Read.Int();
        int mes = Read.Int();
        int ano = Read.Int();
        Data d = new Data(dia, mes, ano);
        NovoUtilizador.setDataNascimento(d);

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