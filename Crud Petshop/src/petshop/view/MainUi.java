package petshop.view;

import java.util.InputMismatchException;
import petshop.util.Console;
import petshop.view.menu.ClienteMenu;
import petshop.view.menu.MainMenu;
import petshop.view.menu.PetMenu;


public class MainUi {
    
    public MainUi(){
        
    }
        public void menu() {
        int opcao = -1;
        do {
            try {
                System.out.println(MainMenu.getOpcoes());
                opcao = Console.scanInt("Digite sua opção:");
                switch (opcao) {
                    case MainMenu.OP_CLIENTE:
                       new ClienteUi().menuCliente() ;
                        break;
                    case MainMenu.OP_PET:
                        PetUi().menuPet();
                        break;
                    case MainMenu.OP_VENDA:
                        VendaUi().menu();
                        break;
                    case ClienteMenu.OP_SAIR:
                        System.out.println("Finalizando a aplicacao..");
                        break;
                    default:
                        System.out.println("Opção inválida..");
                }
            } catch (InputMismatchException ex) {
                UIUtil.mostrarErro("Somente numeros sao permitidos!");
            }

        } while (opcao != ClienteMenu.OP_SAIR);    
}

   
}