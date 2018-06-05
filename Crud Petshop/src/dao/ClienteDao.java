
package dao;

import petshop.dominio.Cliente;
import java.util.List;

public interface ClienteDao {
    
    public void salvar(Cliente cliente);
    public void deletar(Cliente cliente);
    public void atualizar(Cliente cliente);
    public List<Cliente> listar();
    public Cliente procurarPorId(int id);
    //Adicionado
    public Cliente procurarPorRg(String rg);
    public List<Cliente> listarPorNome(String nome);
    
}
