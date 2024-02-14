/* ***************************************************************
 * Autor............: Guilherme Dias Sousa
 * Matricula........: 202211033
 * Inicio...........: 15/10/2023
 * Ultima alteracao.: 29/10/2023
 * Nome.............: Principal
 * Funcao...........: Aplicação Main que inicia o programa do Janter dos filosofos  
 *************************************************************** */

import controle.MainController; // Importação da classe MainController
import javafx.application.Application; // Importação da classe Application
import javafx.fxml.FXMLLoader; // Importação da classe FXMLLoader   
import javafx.scene.Parent; // Importação da classe Parent
import javafx.scene.Scene; // Importação da classe Scene
import javafx.scene.control.Alert; // Importação da classe Alert
import javafx.scene.control.Alert.AlertType; // Importação da classe AlertType
import javafx.scene.control.Label; // Importação da classe Label
import javafx.scene.control.TextArea; // Importação da classe TextArea
import javafx.scene.layout.GridPane; // Importação da classe GridPane
import javafx.stage.Stage; // Importação da classe Stage

// Classe Principal que herda de Application

public class Principal extends Application {

    /*
     * Método: main
     * Função: Iniciar a aplicação
     * Parametros: String[] args
     * Retorno: void
     */
    public static void main(String[] args) {
        launch(args); // Iniciar a aplicação
    } // Fim do método main
    
    /*
     * Método: start
     * Função: Iniciar a aplicação
     * Parametros: Stage primaryStage
     * Retorno: void
     */
    @Override
    public void start(Stage primaryStage) throws Exception {

        // Cria um alerta de boas-vindas
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Bem-vindo ao Jantar dos Goroseis");
        alert.setHeaderText(null); // Cabeçalho do alerta

        // Conteúdo personalizado do alerta
        Label label = new Label("Olá, seja bem-vindo ao Jantar dos Goroseis, a elite suprema dos mares, caso você seja um pirata procurado eu não recomendaria este local.");
        TextArea textArea = new TextArea("Toda a pixel arte é autoral, feita com muito (muito) tempo e carinho, pixel por pixel, baseando-se nos personagens e universo do anime 'One Piece'.\n\nNessa simulação os goroseis precisarão dividir seus Hashis para comer os sushis de salmão. Quando não estiverem comendo, eles estarão pensando em como deter o Luffy de se tornar o Rei dos Piratas.\n\nPara iniciar basta apertar o botão no canto superior esquerdo, aproveite!");
        textArea.setWrapText(true); // Quebra de linha automática
        textArea.setEditable(false); // Desabilitar edição

        GridPane gridPane = new GridPane(); // Criação do gridPane
        gridPane.add(label, 0, 0); // Adição do label
        gridPane.add(textArea, 0, 1); /// Adição do textArea

        alert.getDialogPane().setContent(gridPane); // Adição do gridPane ao alerta

        // Exibe o alerta 
        alert.showAndWait(); // Exibição do alerta
 
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("controle/interface.fxml")); // Carregar o arquivo FXML
        Parent root = loader.load(); // Carregar a visualização
        MainController  tela = new MainController(); // Instanciar a classe MainController
        // Criação da cena e configuração da janela
        Scene scene = new Scene(root); // Criação da cena
        primaryStage.setScene(scene); // Configuração da janela
        primaryStage.setResizable(false); // Configuração da janela
        primaryStage.setTitle("FILOSOFOS"); // Configuração do titulo da janela
        primaryStage.getIcons().add(new javafx.scene.image.Image(getClass().getResourceAsStream("images/prato_cheio.png"))); // Configuração do icone da janela
        primaryStage.show(); // Exibição da janela
      } // Fim do método start
    

}