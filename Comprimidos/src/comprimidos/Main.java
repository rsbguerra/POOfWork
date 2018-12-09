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
                + "|   3.) Sair          |\n"
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
            System.out.println("\nIntroduza o ID do utilizador para fazer login:");
            int u = Read.Int();
            
            
            for (i = 0; i < utilizadores.size(); i++) {
                if (u == utilizadores.get(i).getId()) {
                    break;
                }
            }

            String pass;
            if (i < utilizadores.size()) {
                for (int tentativas = 0; tentativas < 3; tentativas++) {
                    System.out.print("Introduza password do utilizador " + utilizadores.get(i).getNome() + ": ");
                    pass = Read.String();
                    if (utilizadores.get(i).testarPassword(pass)) {
                        GerirUtilizadores.gerirUtilizadores(i, utilizadores);
                    }
                    
                }
                System.out.println("Excedeu limite de tentativas");
                return;
            }
            
            if ( u == utilizadores.size())
                return;
            else{
            System.out.println("Esse utilizador não existe\n");
            return;
            }
        }
    }

    public static void registar() {
        ArrayList<Utilizador> utilizadores = Ficheiro.abrir();
        Utilizador NovoUtilizador = new Utilizador();

        System.out.println("Nome: ");
        NovoUtilizador.setNome(Read.String());

        System.out.println("Genero: ");
        NovoUtilizador.setGenero(Read.String());

        System.out.println("Idade: ");
        NovoUtilizador.setIdade(Read.Int());

        System.out.println("Password: ");
        NovoUtilizador.setPassword(Read.String());

        System.out.println("Data de nascimento (dia, mes, ano):");
        int dia = Read.Int();
        int mes = Read.Int();
        int ano = Read.Int();
        Data d = new Data(dia, mes, ano);
        NovoUtilizador.setDataNascimento(d);

        utilizadores.add(NovoUtilizador);
        Ficheiro.escrever(utilizadores);
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
                    return;
                    
                default:
                    System.out.println("Opção inválida! Introduzir nova opção\n");
                    break;
            }
        }
    }
}