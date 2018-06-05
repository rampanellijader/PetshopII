
package petshop.view.menu;


public class PetMenu {
    public static final int OP_CADASTRAR = 1;
    public static final int OP_DELETAR = 2;
    public static final int OP_ATUALIZAR = 3;
    public static final int OP_LISTAR = 4;
    public static final int OP_CONSULTAR = 5;
    public static final int OP_SAIR = 0;

    public static String getOpcoes() {
        return ("\n--------------------------------------\n"
                + "1- Cadastrar Pets\n"
                + "2- Deletar Pets\n"
                + "3- Atualizar dados do Pet\n"
                + "4- Listar Pets\n"
                + "5- Consultar Pet por Nome\n"
                + "0- Sair"
                + "\n--------------------------------------");
    }    
}
