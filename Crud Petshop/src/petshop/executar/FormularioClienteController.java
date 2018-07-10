/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petshop.executar;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import petshop.dominio.Cliente;
import petshop.negocio.ClienteNegocio;
import petshop.negocio.NegocioException;

/**
 *
 * @author Jader
 */

    public class FormularioClienteController implements Initializable {
        private  ClienteNegocio clienteNegocio;
    
    @FXML
    private TextField textRg;
    @FXML
    private TextField textNome;
    @FXML
    private TextField telefone;
    
    public FormularioClienteController(){
        clienteNegocio = new ClienteNegocio();
    }
    
    @FXML
    private void salvarCliente(ActionEvent event) {
        try {
            clienteNegocio.salvar(new Cliente(
                    textRg.getText(),
                    textNome.getText(),
                    telefone.getText()
            ));
            limparDados();
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Sucesso!");
            alerta.setHeaderText(null);
            alerta.setContentText(textNome.getText()+" salvo com sucesso!");
            alerta.showAndWait();
        } catch (NegocioException ex) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("ERRO!");
            alerta.setHeaderText("Erro!");
            alerta.setContentText(ex.getMessage());
            alerta.showAndWait();
        }
    }
    
    @FXML
    private void cancelar(ActionEvent event) {
        limparDados();
        
    }

        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        limparDados();

    }    

    private void limparDados() {
        textRg.clear();
        textNome.clear();
        telefone.clear();
    }
    
    }
