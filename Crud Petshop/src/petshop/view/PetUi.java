
package petshop.view;

import dao.PetDao;
import java.util.InputMismatchException;
import java.util.List;
import petshop.dao.impl_bd.ClienteDaoBD;
import petshop.dao.impl_bd.PetDaoBD;
import petshop.dominio.Cliente;
import petshop.dominio.Pet;
import petshop.util.Console;
import petshop.view.menu.ClienteMenu;
import petshop.view.menu.PetMenu;


public class PetUi {
   private PetDao petDao;
   
       public PetUi() {
        petDao = new PetDaoBD();
    }

    public void menu() {
        int opcao = -1;
        do {
            try {
                System.out.println(PetMenu.getOpcoes());
                opcao = Console.scanInt("Digite sua opção:");
                switch (opcao) {
                    case PetMenu.OP_CADASTRAR:
                        cadastrarPet();
                        break;
                    case PetMenu.OP_DELETAR:
                        deletarPet();
                        break;
                    case PetMenu.OP_ATUALIZAR:
                        atualizarPet();
                        break;
                    case PetMenu.OP_LISTAR:
                        mostrarPets();
                        break;
                    case PetMenu.OP_CONSULTAR:
                        consultarPetsPorNome();
                        break;
                    case PetMenu.OP_SAIR:
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

    private void cadastrarPet() {
       String nome = Console.scanString("nome");
       String tp_animal = Console.scanString("tipo animal");
       Cliente cli = listar();
    }

    private void deletarPet() {
       String nome = Console.scanString("Nome do pet a ser deletado: ");
       Pet pet = petDao.procurarPorNome(nome);
        this.mostrarPets(pet);
    }

    private void atualizarPet() {
        
    }

    private void mostrarPets() {
        List<Pet> listaPets = petDao.listar();
        this.mostrarPets(listaPets);
       
    }

    private void consultarPetsPorNome() {
        String nome = Console.scanString("Nome: ");
        List<Pet> listaPet = petDao.listarPorNome(nome);
        this.mostrarPets(listaPet);
    }
     
    private void mostrarPet(Pet p){
        System.out.println("-----------------------------");
        System.out.println("Pet");
        System.out.println("Nome: " + p.getNome());
        System.out.println("Tipo animal: " + p.getTp_animal());
        System.out.println("-----------------------------");
    }
    private void mostrarPets(List<Pet> listaPets) {
      if(listaPets.isEmpty())  {
          System.out.println("Pets não encontrados");
      }else {
            System.out.println("-----------------------------\n");
            System.out.println(String.format("%-10s", "RG") + "\t"
                    + String.format("%-20s", "|NOME") + "\t"
                    + String.format("%-20s", "|TIPO ANIMAL"));
            for (Pet pet : listaPets) {
                System.out.println(String.format("%-20s", pet.getNome()) + "\t"
                        + String.format("%-20s", "|" + pet.getTp_animal()));
                       
            }
        }
    }
    }



