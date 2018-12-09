package comprimidos;

import java.util.ArrayList;

public class Admin extends Utilizador{
    
    
    public Admin(Utilizador admino){
        super(admino.getNome());
    }
    
    
    public static void Adminremove(ArrayList<Utilizador> lista) {
        
        System.out.print("Qual o utilizador que quer remover? ");
        int index = Read.Int();
                
        System.out.print("Tem a certeza que quer remover?? (y/n): ");
        char op;
        op = Read.Char();
        
        while ((op != 'y') || (op != 'n')) {
            switch (op) {
                case 'y':
                    lista.remove(index);
                    return;
                case 'n':
                    return;
                default:
                    System.out.println("Opção invalida!");
                    break;
            }
        }    
    }
    
    
    
    
}