
package petshop.dao.impl_bd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import petshop.dominio.Cliente;
import petshop.dominio.Pet;


public class PetDaoBD {
   
      private Connection conexao;
    private PreparedStatement comando;

    
    public Connection conectar(String sql) throws SQLException {
        conexao = ConnectionFactory.getConnection();
        comando = conexao.prepareStatement(sql);
        return conexao;
    }

    
    public void conectarObtendoId(String sql) throws SQLException {
        conexao = ConnectionFactory.getConnection();
        comando = conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
    }  
    
       public void fecharConexao() {
        try {
            if (comando != null) {
                comando.close();
            }
            if (conexao != null) {
                conexao.close();
            }
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Erro ao encerrar a conexao");
            throw new BDException(ex);

        }

    }  
     public void salvar(Pet pet) {
        int id = 0;
        try {
            String sql = "INSERT INTO pet (nome, tp_animal, cliente) "
                    + "VALUES (?,?,?)";

  //criado um novo método conectar para obter o id
            conectarObtendoId(sql);
            comando.setString(1, pet.getNome());
            comando.setString(2, pet.getTp_animal());
            comando.setString(3, pet.getCliente());
            comando.executeUpdate();
            
            //Obtém o resultSet para pegar o id
            ResultSet resultado = comando.getGeneratedKeys();
            if (resultado.next()) {
                //seta o id para o objeto
                id = resultado.getInt(1);
                pet.setId(id);
            }
            else{
                System.err.println("Erro de Sistema - Nao gerou o id conforme esperado!");
                throw new BDException("Nao gerou o id conforme esperado!");
            }

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao salvar paciente no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
    }

    public void deletar(Pet pet) {
        try {
            String sql = "DELETE FROM pet WHERE id = ?";

            conectar(sql);
            comando.setInt(1, pet.getId());
            comando.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao deletar paciente no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }

    }

  
    public void atualizar(Pet pet) {
        try {
            String sql = "UPDATE pet SET nome=?, tp_animal=?, cliente=? "
                    + "WHERE id=?";

            conectar(sql);
            comando.setString(1, pet.getNome());
            comando.setString(2, pet.getTp_animal());
            comando.setString(3, pet.getCliente());
            comando.setInt(4, pet.getId());
            comando.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao atualizar paciente no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }

    }

     public List<Pet> listar() {
        List<Pet> listapets = new ArrayList<>();

        String sql = "SELECT * FROM pet";

        try {
            conectar(sql);

            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                int id = resultado.getInt("id");
                String nome = resultado.getString("nome");
                String tp_animal = resultado.getString("tipo animal");
                Cliente cli = lista.             
                //new Cliente() = listaClientes();
               // Cliente cliente = resultado.getString("cliente");
                Pet pet = new Pet(id, nome, tp_animal, cliente);

                listaPets.add(pet);

            }

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar os pacientes do Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }

        return (listaPets);
    }

    
}
