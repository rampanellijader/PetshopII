
package petshop.dominio;


public class Pet {
  private int id;  
  private String nome; 
  private String tp_animal;
  private Cliente cliente;

  
   public Pet(int id, String nome, String tp_animal, Cliente cliente){
      this.id = id;
      this.nome = nome;
      this.tp_animal = tp_animal;
      this.cliente =  cliente;
      
 }
   
  public Pet(String nome, String tp_animal, Cliente cliente){
      this.nome = nome;
      this.tp_animal = tp_animal;
      this.cliente =  cliente;
      
 }

    public Pet(int id, String nome, String tp_animal, String rg_cli, Cliente c) {
      this.id = id;
      this.nome = nome;
      this.tp_animal = tp_animal;
      this.cliente =  c;
        
    }
    public String getNome() {
        return nome;
    }

   
    public String getTp_animal() {
        return tp_animal;
    }

 
    public Cliente getCliente() {
        return cliente;
    }
  
  @Override
    public String toString(){
    return nome +" "+ tp_animal+ " "+ cliente.getNome();
  }


   
    public int getId() {
        return id;
    }

  
    public void setId(int id) {
        this.id = id;
    }

    public void setTp_animal(String tp_animal) {
        this.tp_animal = tp_animal;
    }

    public boolean isEmpty() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

     
    }
