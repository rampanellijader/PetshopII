
package petshop.dao.impl_bd;


import dao.PetDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import petshop.dominio.Cliente;
import petshop.dominio.Pet;


public class PetDaoBD implements PetDao{
   
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
            String sql = "INSERT INTO pet (nome, tp_animal, cli_id) "
                    + "VALUES (?,?,?)";

  //criado um novo método conectar para obter o id
            conectarObtendoId(sql);
            comando.setString(1, pet.getNome());
            comando.setString(2, pet.getTp_animal());
            comando.setInt(3, pet.getCliente().getId());
            comando.executeUpdate();
            
            
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
            System.err.println("Erro de Sistema - Problema ao salvar pet no Banco de Dados!");
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
            System.err.println("Erro de Sistema - Problema ao deletar pet no Banco de Dados!");
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
            comando.setInt(3, pet.getCliente().getId());
            comando.setInt(4, pet.getId());
            comando.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao atualizar pet no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }

    }

     public List<Pet> listarPets() {
        List<Pet> listapets = new ArrayList<>();

        String sql = "SELECT * FROM pet";

        try {
            conectar(sql);

            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                int id = resultado.getInt("id");
                String nome = resultado.getString("nome");
                String tp_animal = resultado.getString("tipo animal");
                int idCliente = resultado.getInt("id cliente");
                
                Cliente cli_id = new ClienteDaoBD().procurarPorId(id);
                Pet pet = new Pet(id, nome, tp_animal, cli_id);

                listapets.add(pet);

            }

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar os pets do Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }

        return (listapets);
    }


    @Override
    public Pet procurarPorId(int id) {
         String sql = "SELECT * FROM pet WHERE id = ?";

        try {
            conectar(sql);
            comando.setInt(1, id);

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                String nome = resultado.getString("nome");
                String tp_animal = resultado.getString("tp_animal");
               int idCliente = resultado.getInt("id cliente");
                
                Cliente cli_id = new ClienteDaoBD().procurarPorId(id);
                Pet pet = new Pet(id, nome, tp_animal, cli_id);
                
                return (pet);              

            }

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar o pet pelo id do Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }

        return (null);  
         
    }

    @Override
    public Pet procurarPorNome(String nome) {
         String sql = "SELECT * FROM pet WHERE nome LIKE ?";

        try {
            conectar(sql);
            comando.setString(1, "%" + nome + "%");
            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                int id = resultado.getInt("id");
                String nomeX = resultado.getString("nome");
                String tp_animal = resultado.getString("tipo animal");
                int idCliente = resultado.getInt("id cliente");
                
                Cliente cli_id = new ClienteDaoBD().procurarPorId(id);
                Pet pet = new Pet(id, nome, tp_animal, cli_id);
                
                return(pet); 

            }

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar os pets pelo nome do Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
        return (null);
    
    }

    @Override
    public List<Pet> listarPorNome(String nome) {
         List<Pet> listaPets = new ArrayList<>();
        String sql = "SELECT * FROM pet WHERE nome LIKE ?";

        try {
            conectar(sql);
            comando.setString(1, "%" + nome + "%");
            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                int id = resultado.getInt("id");
                String nomeX = resultado.getString("nome");
                String tp_animal = resultado.getString("tipo animal");
                 int idCliente = resultado.getInt("id cliente");
                
                Cliente cli_id = new ClienteDaoBD().procurarPorId(id);
                Pet pet = new Pet(id, nome, tp_animal, cli_id);
                
                listaPets.add(pet); 

            }

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar os pets pelo nome do Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
        return (listaPets);
    }

 
    }



  

    
