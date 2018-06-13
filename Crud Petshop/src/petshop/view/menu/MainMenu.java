
package petshop.view.menu;


public class MainMenu {
    
    public static final int OP_CLIENTE = 1;
    public static final int OP_PET = 2;
    public static final int OP_SAIR = 0;

    public static String getOpcoes() {
        return ("\n--------------------------------------\n"
                + "1- Menu Clientes\n"
                + "2- Menu Pet\n"
                + "0- Sair"
                + "\n--------------------------------------");
    }    
    
}
