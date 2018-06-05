
package dao;

import java.util.List;
import petshop.dominio.Pet;


public interface PetDao {
    public void salvar(Pet pet);
    public void deletar(Pet pet);
    public void atualizar(Pet pet);
    public List<Pet> listar();
    public Pet procurarPorId(int id);
    //Adicionado
    public Pet procurarPorNome(String nome);
    public List<Pet> listarPorNome(String nome);
    
}
