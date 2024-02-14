/* ***************************************************************
 * Autor............: Guilherme Dias Sousa
 * Matricula........: 202211033
 * Inicio...........: 15/10/2023
 * Ultima alteracao.: 29/10/2023
 * Nome.............: MainController
 * Funcao...........: Classe controladora do programa
 *************************************************************** */
package controle; // Pacote ao qual a classe pertence

import java.net.URL; // Importação da classe URL
import java.util.ResourceBundle; // Importação da classe ResourceBundle
import java.util.concurrent.Semaphore; // Importação da classe Semaphore
import javafx.application.Platform; // Importação da classe Platform
import javafx.fxml.FXML; // Importação da classe FXML
import javafx.fxml.Initializable; // Importação da classe Initializable
import javafx.scene.control.Button; // Importação da classe Button
import javafx.scene.control.Label; // Importação da classe Label
import javafx.scene.image.Image; // Importação da classe Image
import javafx.scene.image.ImageView; // Importação da classe ImageView
import javafx.scene.media.Media; // Importação da classe Media
import javafx.scene.media.MediaPlayer; // Importação da classe MediaPlayer
import javafx.util.Duration; // Importação da classe Duration
import model.Filosofo; // Importação da classe Filosofo

/*
 * Classe: MainController
 * Função: Classe controladora do programa
 * Métodos: initialize, botaoIniciar, stopAudio, pausarRetomar_filosofo1, aumentarPensar_filosofo1, diminuirPensar_filosofo1, aumentarComer_filosofo1, diminuirComer_filosofo1, pausarRetomar_filosofo2, aumentarPensar_filosofo2, diminuirPensar_filosofo2, aumentarComer_filosofo2, diminuirComer_filosofo2, pausarRetomar_filosofo3, aumentarPensar_filosofo3, diminuirPensar_filosofo3, aumentarComer_filosofo3, diminuirComer_filosofo3, pausarRetomar_filosofo4, aumentarPensar_filosofo4, diminuirPensar_filosofo4, aumentarComer_filosofo4, diminuirComer_filosofo4, pausarRetomar_filosofo5, aumentarPensar_filosofo5, diminuirPensar_filosofo5, aumentarComer_filosofo5, diminuirComer_filosofo5, reset, pensar, comer, fome, atualizarVelocidadeLabels, mapearVelocidade, colocaGarfos
 */
public class MainController implements Initializable {
    // Atributos da classe
    public Semaphore mutex; // Semáforo para controle de região crítica
    public  Semaphore[] semaforos; // Semáforos para controle de estado dos filósofos
    public int[] estado; // Vetor de estados dos filósofos
    public int x = 5; // Quantidade de filósofos
    Filosofo f0, f1, f2, f3, f4; // Filósofos 
		private boolean simulacaoIniciada = false; // Flag para controle de início da simulação
    public ImageView[] garfos = new ImageView[x]; // Vetor de garfos

    
    // Elementos da interface gráfica
    // Métodos do filósofo Saturno/1
    @FXML private Button aumentarPensar_filosofo1; // Botão para aumentar o tempo de pensar do filósofo 1
    @FXML private Button diminuirPensar_filosofo1; // Botão para diminuir o tempo de pensar do filósofo 1
    @FXML private Button aumentarComer_filosofo1; // Botão para aumentar o tempo de comer do filósofo 1
    @FXML private Button diminuirComer_filosofo1; // Botão para diminuir o tempo de comer do filósofo 1
    @FXML private Button pausarRetomar_filosofo1; // Botão para pausar e retomar o filósofo 1
    @FXML private ImageView filosofo1; // Imagem do filósofo 1
    @FXML public Label estado_filosofo1; // Label para exibir o estado do filósofo 1
    @FXML public Label velocidadePensar_f1; // Label para exibir a velocidade de pensar do filósofo 1
    @FXML public Label velocidadeComer_f1; // Label para exibir a velocidade de comer do filósofo 1
    @FXML public ImageView balao_f1; // Imagem do balão de fala do filósofo 1
    @FXML public ImageView prato_f1; // Imagem do prato do filósofo 1

    // Métodos do filósofo Júpiter/2
    @FXML private Button aumentarPensar_filosofo2; // Botão para aumentar o tempo de pensar do filósofo 2
    @FXML private Button diminuirPensar_filosofo2; // Botão para diminuir o tempo de pensar do filósofo 2
    @FXML private Button aumentarComer_filosofo2; // Botão para aumentar o tempo de comer do filósofo 2
    @FXML private Button diminuirComer_filosofo2; // Botão para diminuir o tempo de comer do filósofo 2
    @FXML private Button pausarRetomar_filosofo2; // Botão para pausar e retomar o filósofo 2
    @FXML private ImageView filosofo2; // Imagem do filósofo 2
    @FXML public Label estado_filosofo2; // Label para exibir o estado do filósofo 2
    @FXML public Label velocidadePensar_f2; // Label para exibir a velocidade de pensar do filósofo 2
    @FXML public Label velocidadeComer_f2; // Label para exibir a velocidade de comer do filósofo 2
    @FXML public ImageView balao_f2; // Imagem do balão de fala do filósofo 2
    @FXML public ImageView prato_f2; // Imagem do prato do filósofo 2

    // Métodos do filósofo Marte/3
    @FXML private Button aumentarPensar_filosofo3; // Botão para aumentar o tempo de pensar do filósofo 3
    @FXML private Button diminuirPensar_filosofo3; // Botão para diminuir o tempo de pensar do filósofo 3
    @FXML private Button aumentarComer_filosofo3; // Botão para aumentar o tempo de comer do filósofo 3
    @FXML private Button diminuirComer_filosofo3; // Botão para diminuir o tempo de comer do filósofo 3
    @FXML private Button pausarRetomar_filosofo3; // Botão para pausar e retomar o filósofo 3
    @FXML private ImageView filosofo3; // Imagem do filósofo 3
    @FXML public Label estado_filosofo3; // Label para exibir o estado do filósofo 3
    @FXML public Label velocidadePensar_f3; // Label para exibir a velocidade de pensar do filósofo 3
    @FXML public Label velocidadeComer_f3; // Label para exibir a velocidade de comer do filósofo 3
    @FXML public ImageView balao_f3; // Imagem do balão de fala do filósofo 3
    @FXML public ImageView prato_f3; // Imagem do prato do filósofo 3

    // Métodos do filósofo Plutão/4
    @FXML private Button aumentarPensar_filosofo4; // Botão para aumentar o tempo de pensar do filósofo 4
    @FXML private Button diminuirPensar_filosofo4; // Botão para diminuir o tempo de pensar do filósofo 4
    @FXML private Button aumentarComer_filosofo4; // Botão para aumentar o tempo de comer do filósofo 4
    @FXML private Button diminuirComer_filosofo4; // Botão para diminuir o tempo de comer do filósofo 4
    @FXML private Button pausarRetomar_filosofo4; // Botão para pausar e retomar o filósofo 4
    @FXML private ImageView filosofo4; // Imagem do filósofo 4
    @FXML public Label estado_filosofo4; // Label para exibir o estado do filósofo 4
    @FXML public Label velocidadePensar_f4; // Label para exibir a velocidade de pensar do filósofo 4
    @FXML public Label velocidadeComer_f4; // Label para exibir a velocidade de comer do filósofo 4
    @FXML public ImageView balao_f4; // Imagem do balão de fala do filósofo 4
    @FXML public ImageView prato_f4; // Imagem do prato do filósofo 4
 
    // Métodos do filósofo Netuno/5
    @FXML private Button aumentarPensar_filosofo5; // Botão para aumentar o tempo de pensar do filósofo 5
    @FXML private Button diminuirPensar_filosofo5; // Botão para diminuir o tempo de pensar do filósofo 5
    @FXML private Button aumentarComer_filosofo5; // Botão para aumentar o tempo de comer do filósofo 5
    @FXML private Button diminuirComer_filosofo5; // Botão para diminuir o tempo de comer do filósofo 5
    @FXML private Button pausarRetomar_filosofo5; // Botão para pausar e retomar o filósofo 5
    @FXML private ImageView filosofo5; // Imagem do filósofo 5
    @FXML public Label estado_filosofo5; // Label para exibir o estado do filósofo 5
    @FXML public Label velocidadePensar_f5; // Label para exibir a velocidade de pensar do filósofo 5
    @FXML public Label velocidadeComer_f5; // Label para exibir a velocidade de comer do filósofo 5
    @FXML public ImageView balao_f5; // Imagem do balão de fala do filósofo 5
    @FXML public ImageView prato_f5; // Imagem do prato do filósofo 5

		// Nota: os fílosofos estão listados de 1 a 5 mas no array de filósofos eles estão de 0 a 4

    // Garfos
    @ FXML public ImageView garfo0; // Imagem do garfo 0
    @ FXML public ImageView garfo1; // Imagem do garfo 1 
    @ FXML public ImageView garfo2; // Imagem do garfo 2
    @ FXML public ImageView garfo3; // Imagem do garfo 3
    @ FXML public ImageView garfo4; // Imagem do garfo 4

		// Nota: os garfos estão nomeados como garfos, mas na verdade são hashis pois faz mais sentido utilizar dois hashis do que dois garfos para comer

    // Botões iniciar e reset
    @FXML private Button iniciar; // Botão para iniciar a simulação
    @FXML private Button reset; // Botão para resetar a simulação
    @FXML private Button stopMusic; // Botão para parar a música

		// Métodos da classe
    private MediaPlayer mediaPlayer; // Reprodutor de mídia

		/*
		 * Método: botaoIniciar
		 * Função: Iniciar a simulação
		 * Parâmetros: void
		 * Retorno: void
		 */
    public void botaoIniciar() {
        f0 = new Filosofo(this); // Instanciar o filósofo 1
        f1 = new Filosofo(this); // Instanciar o filósofo 2
        f2 = new Filosofo(this); // Instanciar o filósofo 3
        f3 = new Filosofo(this); // Instanciar o filósofo 4
        f4 = new Filosofo(this); // Instanciar o filósofo 5

				// Ao iniciar a simulação, os botões de controle dos filósofos são habilitados
				// Eles podem ser usados apenas após o início da simulação para que não haja erros
				// Habilitar os  botões do filósofo 1
				pausarRetomar_filosofo1.setDisable(false); // Habilitar o botão de pausar e retomar o filósofo 1
				aumentarPensar_filosofo1.setDisable(false); // Habilitar o botão de aumentar o tempo de pensar do filósofo 
				diminuirPensar_filosofo1.setDisable(false); // Habilitar o botão de diminuir o tempo de pensar do filósofo
				aumentarComer_filosofo1.setDisable(false); // Habilitar o botão de aumentar o tempo de comer do filósofo
				diminuirComer_filosofo1.setDisable(false); // Habilitar o botão de diminuir o tempo de comer do filósofo

				// Habilitar os botões do filósofo 2
				pausarRetomar_filosofo2.setDisable(false); // Habilitar o botão de pausar e retomar o filósofo 2
				aumentarPensar_filosofo2.setDisable(false); // Habilitar o botão de aumentar o tempo de pensar do filósofo 2
				diminuirPensar_filosofo2.setDisable(false); // Habilitar o botão de diminuir o tempo de pensar do filósofo 2
				aumentarComer_filosofo2.setDisable(false); // Habilitar o botão de aumentar o tempo de comer do filósofo 2
				diminuirComer_filosofo2.setDisable(false); // Habilitar o botão de diminuir o tempo de comer do filósofo 2

				// Habilitar os botões do filósofo 3
				pausarRetomar_filosofo3.setDisable(false); // Habilitar o botão de pausar e retomar o filósofo 3
				aumentarPensar_filosofo3.setDisable(false); // Habilitar o botão de aumentar o tempo de pensar do filósofo 3
				diminuirPensar_filosofo3.setDisable(false); // Habilitar o botão de diminuir o tempo de pensar do filósofo 3
				aumentarComer_filosofo3.setDisable(false); // Habilitar o botão de aumentar o tempo de comer do filósofo 3
				diminuirComer_filosofo3.setDisable(false); // Habilitar o botão de diminuir o tempo de comer do filósofo 3
				
				// Habilitar os botões do filósofo 4
				pausarRetomar_filosofo4.setDisable(false); // Habilitar o botão de pausar e retomar o filósofo 4
				aumentarPensar_filosofo4.setDisable(false); // Habilitar o botão de aumentar o tempo de pensar do filósofo 4
				diminuirPensar_filosofo4.setDisable(false); // Habilitar o botão de diminuir o tempo de pensar do filósofo 4
				aumentarComer_filosofo4.setDisable(false); // Habilitar o botão de aumentar o tempo de comer do filósofo 4
				diminuirComer_filosofo4.setDisable(false); // Habilitar o botão de diminuir o tempo de comer do filósofo 4

				// Habilitar os botões do filósofo 5
				pausarRetomar_filosofo5.setDisable(false); // Habilitar o botão de pausar e retomar o filósofo 5
				aumentarPensar_filosofo5.setDisable(false); // Habilitar o botão de aumentar o tempo de pensar do filósofo 5
				diminuirPensar_filosofo5.setDisable(false); // Habilitar o botão de diminuir o tempo de pensar do filósofo 5
				aumentarComer_filosofo5.setDisable(false); // Habilitar o botão de aumentar o tempo de comer do filósofo 5
				diminuirComer_filosofo5.setDisable(false); // Habilitar o botão de diminuir o tempo de comer do filósofo 5

        reset.setDisable(false); // Habilitar o botão de resetar a simulação
				iniciar.setVisible(simulacaoIniciada);
				
				simulacaoIniciada = true; // A simulação foi iniciada

        // Iniciar os filósofos com suas respectivas Threads

				// Filósofo 1 [0]
        f0.setController(this); // Setar o controlador
        f0.setGarfos(garfo0, garfo1); // Setar os garfos
        f0.setName("Filosofo 1"); // Setar o nome da Thread
        f0.setId(0); // Setar o id do filósofo
        f0.start(); // Iniciar a Thread

				// Filósofo 2 [1]
        f1.setController(this); // Setar o controlador
        f1.setName("Filosofo 2"); // Setar o nome da Thread
        f1.setId(1); // Setar o id do filósofo
        f1.setGarfos(garfo1, garfo2); // Setar os garfos
        f1.start(); // Iniciar a Thread

				// Filósofo 3 [2]
        f2.setController(this); // Setar o controlador
        f2.setName("Filosofo 3"); // Setar o nome da Thread
        f2.setId(2); // Setar o id do filósofo
        f2.setGarfos(garfo2, garfo3);  // Setar os garfos
        f2.start(); // Iniciar a Thread

				// Filósofo 4 [3]
        f3.setController(this); // Setar o controlador
        f3.setName("Filosofo 4"); // Setar o nome da Thread
        f3.setId(3); // Setar o id do filósofo
        f3.setGarfos(garfo3, garfo4); // Setar os garfos
        f3.start(); // Iniciar a Thread

				// Filósofo 5 [4]
        f4.setController(this); // Setar o controlador
        f4.setName("Filosofo 5"); // Setar o nome da Thread
        f4.setId(4); // Setar o id do filósofo
        f4.setGarfos(garfo4, garfo0); // Setar os garfos
        f4.start(); // Iniciar a Thread
    } // Fim do método botaoIniciar
		/*
		 * Método: initialize
		 * Função: Inicializa a música e desabilita os botões de controle dos filósofos para serem acessados apenas ao clicar no botão iniciar
		 * Parâmetros: URL location, ResourceBundle resources
		 * Retorno: void
		 */
    @Override public void initialize(URL location, ResourceBundle resources) {
			// Desabilita os botões de controle dos filósofos
			// Eles podem ser usados apenas após o início da simulação para que não haja erros
			// Desabilitar os  botões do filósofo 1
			aumentarPensar_filosofo1.setDisable(true); // Desabilitar o botão de aumentar o tempo de pensar do filósofo
			diminuirPensar_filosofo1.setDisable(true); // Desabilitar o botão de diminuir o tempo de pensar do filósofo
			aumentarComer_filosofo1.setDisable(true); // Desabilitar o botão de aumentar o tempo de comer do filósofo
			diminuirComer_filosofo1.setDisable(true); // Desabilitar o botão de diminuir o tempo de comer do filósofo
			pausarRetomar_filosofo1.setDisable(true); // Desabilitar o botão de pausar e retomar o filósofo
      
			// Desabilitar os botões do filósofo 2
			aumentarPensar_filosofo2.setDisable(true); // Desabilitar o botão de aumentar o tempo de pensar do filósofo
			diminuirPensar_filosofo2.setDisable(true); // Desabilitar o botão de diminuir o tempo de pensar do filósofo
			aumentarComer_filosofo2.setDisable(true); // Desabilitar o botão de aumentar o tempo de comer do filósofo
			diminuirComer_filosofo2.setDisable(true); // Desabilitar o botão de diminuir o tempo de comer do filósofo
			pausarRetomar_filosofo2.setDisable(true);	// Desabilitar o botão de pausar e retomar o filósofo

			// Desabilitar os botões do filósofo 3
			aumentarPensar_filosofo3.setDisable(true); // Desabilitar o botão de aumentar o tempo de pensar do filósofo
			diminuirPensar_filosofo3.setDisable(true); // Desabilitar o botão de diminuir o tempo de pensar do filósofo
			aumentarComer_filosofo3.setDisable(true);  // Desabilitar o botão de aumentar o tempo de comer do filósofo
			diminuirComer_filosofo3.setDisable(true); // Desabilitar o botão de diminuir o tempo de comer do filósofo
			pausarRetomar_filosofo3.setDisable(true); // Desabilitar o botão de pausar e retomar o filósofo

			// Desabilitar os botões do filósofo 4
			aumentarPensar_filosofo4.setDisable(true); // Desabilitar o botão de aumentar o tempo de pensar do filósofo
			diminuirPensar_filosofo4.setDisable(true); // Desabilitar o botão de diminuir o tempo de pensar do filósofo
			aumentarComer_filosofo4.setDisable(true); // Desabilitar o botão de aumentar o tempo de comer do filósofo
			diminuirComer_filosofo4.setDisable(true); // Desabilitar o botão de diminuir o tempo de comer do filósofo
			pausarRetomar_filosofo4.setDisable(true); // Desabilitar o botão de pausar e retomar o filósofo

			// Desabilitar os botões do filósofo 5
			aumentarPensar_filosofo5.setDisable(true); // Desabilitar o botão de aumentar o tempo de pensar do filósofo
			diminuirPensar_filosofo5.setDisable(true); // Desabilitar o botão de diminuir o tempo de pensar do filósofo
			aumentarComer_filosofo5.setDisable(true); // Desabilitar o botão de aumentar o tempo de comer do filósofo
			diminuirComer_filosofo5.setDisable(true); // Desabilitar o botão de diminuir o tempo de comer do filósofo
			pausarRetomar_filosofo5.setDisable(true); // Desabilitar o botão de pausar e retomar o filósofo

      reset.setDisable(true); // Desabilitar o botão de resetar a simulação

			// Inicializar a música
      Media sound = new Media(getClass().getResource("onepiece.mp3").toExternalForm()); // Carregar o arquivo de áudio
      mediaPlayer = new MediaPlayer(sound); // Instanciar o reprodutor de mídia
      mediaPlayer.play(); // Iniciar a música
      mediaPlayer.setVolume(0.5); // Definir o volume
      mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Definir o ciclo de repetição
      stopMusic.setOnMouseClicked(event -> { // Ao clicar no botão de parar a música
        if (mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) { // Se a música estiver tocando
          mediaPlayer.pause(); // Pausar a música 
        } else {
          mediaPlayer.play(); // Retomar a música
        } // Fim do if
      }); // Fim do setOnMouseClicked
    }  // Fim do método initialize

		/*
		 * Método: MainController
		 * Função: Construtor da classe
		 * Parâmetros: void
		 * Retorno: void
		 */
    public MainController () {
			// Inicializar os garfos
      garfos[0] = garfo0; // Garfo 0
      garfos[1] = garfo1; // Garfo 1
      garfos[2] = garfo2; // Garfo 2
      garfos[3] = garfo3; // Garfo 3
      garfos[4] = garfo4; // Garfo 4

				// Inicializar os semáforos
        mutex = new Semaphore(1); // Semáforo para controle de região crítica
        semaforos = new Semaphore[x]; // Semáforos para controle de estado dos filósofos
        estado = new int[x]; // Vetor de estados dos filósofos
        for (int i = 0; i < x; i++) { // Para cada filósofo
            estado[i] = 0; // Definir o estado como 0
            semaforos[i] = new Semaphore(0); // Inicializar o semáforo
        } // Fim do for
    } // Fim do construtor

		/*
		 * Método: verificarEsq
		 * Função: Verificar o garfo da esquerda
		 * Parâmetros: int i
		 * Retorno: int
		 */
    public int verificarEsq(int i) { // Verificar o garfo da esquerda
      if (i + 1 < x) { // 3+1=4 4<x(5) // Se o filósofo não for o último
        return i + 1;// 3+1=4 // Retornar o garfo da direita
      } else { // Se o filósofo for o último
        return 0; // Retornar o primeiro garfo
      } // Fim do if
    } // Fim do método verificarEsq

		/*
		 * Método: verificarDir
		 * Função: Verificar o garfo da direita
		 * Parâmetros: int i
		 * Retorno: int
		 */
    public int verificarDir(int i) { // Verificar o garfo da direita
      if (i - 1 >= 0) { // 3-1=2 2>0 // Se o filósofo não for o primeiro
        return i - 1; // 2 // Retornar o garfo da esquerda
      } else { // Se o filósofo for o primeiro
        return x - 1; // x=5; 5-1 = 4; // Retornar o último garfo
      }
  
    }

      /*
   * Método: stopAudio
   * Função: Parar a música
   * Parâmetros: void
   * Retorno: void
   */
  @FXML private void stopAudio() { // Parar a música
    if (mediaPlayer != null) { // Se o reprodutor de mídia não for nulo
      mediaPlayer.stop(); // Parar a música
      mediaPlayer.seek(Duration.ZERO); // Voltar para o início da música
    } // Fim do if
  } // Fim do método stopAudio

    // Métodos para interação com a interface gráfica
    // Métodos do filósofo Saturno/1
    @FXML private void pausarRetomar_filosofo1() { // Pausar e retomar o filósofo 1
        f0.setPausar(); // Pausar e retomar o filósofo 1
    } // Fim do método pausarRetomar_filosofo1
    @FXML private void aumentarPensar_filosofo1() { // Aumentar o tempo de pensar do filósofo 1
      if (f0 != null) { // Se o filósofo 1 não for nulo
          f0.aumentarTempoPensando(); // Aumentar o tempo de pensar do filósofo 1
      }  // Fim do if
    } // Fim do método aumentarPensar_filosofo1
    @FXML private void diminuirPensar_filosofo1() { // Diminuir o tempo de pensar do filósofo 1
        f0.diminuirTempoPensando(); // Diminuir o tempo de pensar do filósofo 1
    } // Fim do método diminuirPensar_filosofo1
    @FXML private void aumentarComer_filosofo1() { // Aumentar o tempo de comer do filósofo 1
        f0.aumentarTempoComendo(); // Aumentar o tempo de comer do filósofo 1
    } // Fim do método aumentarComer_filosofo1
    @FXML private void diminuirComer_filosofo1() { // Diminuir o tempo de comer do filósofo 1
        f0.diminuirTempoComendo(); // Diminuir o tempo de comer do filósofo 1
    } // Fim do método diminuirComer_filosofo1

    // Métodos do filósofo Júpiter/2
    @FXML private void pausarRetomar_filosofo2() { // Pausar e retomar o filósofo 2
        f1.setPausar(); // Pausar e retomar o filósofo 2
    } // Fim do método pausarRetomar_filosofo2
    @FXML private void aumentarPensar_filosofo2() { // Aumentar o tempo de pensar do filósofo 2
          f1.aumentarTempoPensando(); // Aumentar o tempo de pensar do filósofo 2
    } // Fim do método aumentarPensar_filosofo2
    @FXML private void diminuirPensar_filosofo2() { // Diminuir o tempo de pensar do filósofo 2
        f1.diminuirTempoPensando(); // Diminuir o tempo de pensar do filósofo 2
    } // Fim do método diminuirPensar_filosofo2
    @FXML private void aumentarComer_filosofo2() { // Aumentar o tempo de comer do filósofo 2
        f1.aumentarTempoComendo(); // Aumentar o tempo de comer do filósofo 2
    } // Fim do método aumentarComer_filosofo2
    @FXML private void diminuirComer_filosofo2() { // Diminuir o tempo de comer do filósofo 2
        f1.diminuirTempoComendo(); // Diminuir o tempo de comer do filósofo 2
    } // Fim do método diminuirComer_filosofo2

    // Métodos do filósofo Marte/3
    @FXML private void pausarRetomar_filosofo3() { // Pausar e retomar o filósofo 3
        f2.setPausar(); // Pausar e retomar o filósofo 3
    } // Fim do método pausarRetomar_filosofo3
    @FXML private void aumentarPensar_filosofo3() { // Aumentar o tempo de pensar do filósofo 3
        f2.aumentarTempoPensando(); // Aumentar o tempo de pensar do filósofo 3
    } // Fim do método aumentarPensar_filosofo3
    @FXML private void diminuirPensar_filosofo3() { // Diminuir o tempo de pensar do filósofo 3
        f2.diminuirTempoPensando(); // Diminuir o tempo de pensar do filósofo 3
    } // Fim do método diminuirPensar_filosofo3
    @FXML private void aumentarComer_filosofo3() { // Aumentar o tempo de comer do filósofo 3
        f2.aumentarTempoComendo(); // Aumentar o tempo de comer do filósofo 3
    }  // Fim do método aumentarComer_filosofo3
    @FXML private void diminuirComer_filosofo3() { // Diminuir o tempo de comer do filósofo 3
        f2.diminuirTempoComendo(); // Diminuir o tempo de comer do filósofo 3
    } // Fim do método diminuirComer_filosofo3

    // Métodos do filósofo Plutão/4
    @FXML private void pausarRetomar_filosofo4() { // Pausar e retomar o filósofo 4
        f3.setPausar(); // Pausar e retomar o filósofo 4
    } // Fim do método pausarRetomar_filosofo4
    @FXML private void aumentarPensar_filosofo4() { // Aumentar o tempo de pensar do filósofo 4
        f3.aumentarTempoPensando(); // Aumentar o tempo de pensar do filósofo 4
    } // Fim do método aumentarPensar_filosofo4
    @FXML private void diminuirPensar_filosofo4() { // Diminuir o tempo de pensar do filósofo 4
        f3.diminuirTempoPensando(); // Diminuir o tempo de pensar do filósofo 4
    } // Fim do método diminuirPensar_filosofo4
    @FXML private void aumentarComer_filosofo4() { // Aumentar o tempo de comer do filósofo 4
        f3.aumentarTempoComendo(); // Aumentar o tempo de comer do filósofo 4
    } // Fim do método aumentarComer_filosofo4
    @FXML private void diminuirComer_filosofo4() { // Diminuir o tempo de comer do filósofo 4
        f3.diminuirTempoComendo(); // Diminuir o tempo de comer do filósofo 4
    } // Fim do método diminuirComer_filosofo4

		// Métodos do filósofo Netuno/5
    @FXML private void pausarRetomar_filosofo5() { // Pausar e retomar o filósofo 5
        f4.setPausar(); // Pausar e retomar o filósofo 5
    } // Fim do método pausarRetomar_filosofo5
    @FXML private void aumentarPensar_filosofo5() { // Aumentar o tempo de pensar do filósofo 5
        f4.aumentarTempoPensando(); // Aumentar o tempo de pensar do filósofo 5
    } // Fim do método aumentarPensar_filosofo5
    @FXML private void diminuirPensar_filosofo5() { // Diminuir o tempo de pensar do filósofo 5
        f4.diminuirTempoPensando(); // Diminuir o tempo de pensar do filósofo 5
    } // Fim do método diminuirPensar_filosofo5
    @FXML private void aumentarComer_filosofo5() { // Aumentar o tempo de comer do filósofo 5
        f4.aumentarTempoComendo(); // Aumentar o tempo de comer do filósofo 5
    } // Fim do método aumentarComer_filosofo5
    @FXML private void diminuirComer_filosofo5() { // Diminuir o tempo de comer do filósofo 5
        f4.diminuirTempoComendo(); // Diminuir o tempo de comer do filósofo 5
    } // Fim do método diminuirComer_filosofo5

		/*
		 * Método: reset
		 * Função: Resetar a simulação para o ponto inicial
		 * Parâmetros: void
		 * Retorno: void
		 */
    public void reset() throws InterruptedException { // Resetar a simulação para o ponto inicial
        f0.stopThread(); // Chama o método stopThread do filósofo 1
        f1.stopThread(); // Chama o método stopThread do filósofo 2 
        f2.stopThread(); // Chama o método stopThread do filósofo 3
        f3.stopThread(); // Chama o método stopThread do filósofo 4
        f4.stopThread(); // Chama o método stopThread do filósofo 5
        f0.devolveGarfos(); // Chama o método devolveGarfos do filósofo 1
        f1.devolveGarfos(); // Chama o método devolveGarfos do filósofo 2
        f2.devolveGarfos(); // Chama o método devolveGarfos do filósofo 3
        f3.devolveGarfos(); // Chama o método devolveGarfos do filósofo 4
        f4.devolveGarfos(); // Chama o método devolveGarfos do filósofo 5
        botaoIniciar(); // Chama o método botaoIniciar
    } // Fim do método reset
		
		/*
		 * Método: pensar
		 * Função: Atualizar a interface gráfica para o filósofo pensar através do uso de um switch case
		 * Parâmetros: Filosofo f
		 * Retorno: void
		 */
    public void pensar(Filosofo f) { // Atualizar a interface gráfica para o filósofo pensar através do uso de um switch case
        String estado = "PENSANDO"; // Definir o estado como pensando
        int tempoPensando = f.getTPensando(); // Pegar o tempo de pensar do filósofo
        int filosofoId = f.getid(); // Pegar o id do filósofo
        
        Platform.runLater(() -> { // Atualizar a interface gráfica
            atualizarVelocidadeLabels(filosofoId, tempoPensando, f.getTComendo()); // Atualizar as labels de velocidade
            switch (filosofoId) { // Switch case para atualizar a interface gráfica de acordo com o filósofo
                case 0:
                    prato_f1.setImage(new Image("images/prato_vazio.png")); // Atualizar a imagem do prato do filósofo 1
                    balao_f1.setImage(new Image("images/pensamento_direit.png")); // Atualizar a imagem do balão de pensamento do filósofo 1
                    estado_filosofo1.setText(estado); // Atualizar o estado do filósofo 1
                    break; // Sair do switch case
                case 1:
                    prato_f2.setImage(new Image("images/prato_vazio.png")); // Atualizar a imagem do prato do filósofo 2
                    balao_f2.setImage(new Image("images/pensamento_direit.png")); // Atualizar a imagem do balão de pensamento do filósofo 2
                    estado_filosofo2.setText(estado); // Atualizar o estado do filósofo 2
                    break; // Sair do switch case
                case 2:
                    prato_f3.setImage(new Image("images/prato_vazio.png")); // Atualizar a imagem do prato do filósofo 3
                    balao_f3.setImage(new Image("images/pensamento_direit.png")); // Atualizar a imagem do balão de pensamento do filósofo 3
                    estado_filosofo3.setText(estado);  // Atualizar o estado do filósofo 3
                    break;
                case 3:
                    prato_f4.setImage(new Image("images/prato_vazio.png")); // Atualizar a imagem do prato do filósofo 4
                    balao_f4.setImage(new Image("images/pensamento_esquerdo.png")); // Atualizar a imagem do balão de pensamento do filósofo 4
                    estado_filosofo4.setText(estado); // Atualizar o estado do filósofo 4
                    break; // Sair do switch case
                case 4:
                    prato_f5.setImage(new Image("images/prato_vazio.png")); // Atualizar a imagem do prato do filósofo 5
                    balao_f5.setImage(new Image("images/pensamento_esquerdo.png")); // Atualizar a imagem do balão de pensamento do filósofo 5
                    estado_filosofo5.setText(estado); // Atualizar o estado do filósofo 5
                    break; // Sair do switch case
                default:
                    break; // Sair do switch case
            } // Fim do switch case
        }); // Fim do Platform.runLater
        
        f.garfoDir.setVisible(true); // Deixar o garfo da direita visível
        f.garfoEsq.setVisible(true); // Deixar o garfo da esquerda visível
    
        try { // Tratar exceções
            Thread.sleep(tempoPensando); // Esperar o tempo de pensar
        } catch (InterruptedException e) { // Pegar a exceção
            e.printStackTrace(); // Imprimir o erro
        } // Fim do try catch
    } // Fim do método pensar
    
		/*
		 * Método: comer
		 * Função: Atualizar a interface gráfica para o filósofo comer através do uso de um switch case
		 * Parâmetros: Filosofo f
		 * Retorno: void
		 */
    public void comer(Filosofo f) { // Atualizar a interface gráfica para o filósofo comer através do uso de um switch case
        String estado = "COMENDO"; // Definir o estado como comendo
        int tempoComendo = f.getTComendo(); // Pegar o tempo de comer do filósofo
        int filosofoId = f.getid(); // Pegar o id do filósofo
    
        Platform.runLater(() -> { // Atualizar a interface gráfica
            atualizarVelocidadeLabels(filosofoId, f.getTPensando(), tempoComendo); // Atualizar as labels de velocidade
            switch (filosofoId) { // Switch case para atualizar a interface gráfica de acordo com o filósofo
                case 0:
                    prato_f1.setImage(new Image("images/prato_cheio.png")); // Atualizar a imagem do prato do filósofo 1
                    balao_f1.setImage(new Image("images/balao_vazio_dir.png")); // Atualizar a imagem do balão de pensamento do filósofo 1
                    estado_filosofo1.setText(estado); // Atualizar o estado do filósofo 1
                    break; // Sair do switch case
                case 1:
                    prato_f2.setImage(new Image("images/prato_cheio.png"));  // Atualizar a imagem do prato do filósofo 2
                    balao_f2.setImage(new Image("images/balao_vazio_dir.png")); // Atualizar a imagem do balão de pensamento do filósofo 2
                    estado_filosofo2.setText(estado); // Atualizar o estado do filósofo 2
                    break; // Sair do switch case
                case 2:
                    prato_f3.setImage(new Image("images/prato_cheio.png"));  // Atualizar a imagem do prato do filósofo 3
                    balao_f3.setImage(new Image("images/balao_vazio_dir.png")); // Atualizar a imagem do balão de pensamento do filósofo 3
                    estado_filosofo3.setText(estado); // Atualizar o estado do filósofo 3
                    break; // Sair do switch case
                case 3:
                    prato_f4.setImage(new Image("images/prato_cheio.png")); // Atualizar a imagem do prato do filósofo 4
                    balao_f4.setImage(new Image("images/balao_vazio_esq.png")); // Atualizar a imagem do balão de pensamento do filósofo 4
                    estado_filosofo4.setText(estado); // Atualizar o estado do filósofo 4
                    break;
                case 4:
                    prato_f5.setImage(new Image("images/prato_cheio.png")); // Atualizar a imagem do prato do filósofo 5
                    balao_f5.setImage(new Image("images/balao_vazio_esq.png")); // Atualizar a imagem do balão de pensamento do filósofo 5
                    estado_filosofo5.setText(estado); // Atualizar o estado do filósofo 5
                    break; // Sair do switch case
                default:
                    break; // Sair do switch case
            } // Fim do switch case
        }); // Fim do Platform.runLater

        f.garfoDir.setVisible(false); // Deixar o garfo da direita invisível
        f.garfoEsq.setVisible(false); // Deixar o garfo da esquerda invisível
    
        try { // Tratar exceções
            Thread.sleep(tempoComendo); // Esperar o tempo de comer
        } catch (InterruptedException e) { 
            e.printStackTrace();  // Imprimir o erro
        } // Fim do try catch
    } // Fim do método comer
    
		/*
		 * Método: fome
		 * Função: Atualizar a interface gráfica para o filósofo ficar com fome através do uso de um switch case
		 * Parâmetros: int id
		 * Retorno: void
		 */
		public void fome(int id) { // Atualizar a interface gráfica para o filósofo ficar com fome através do uso de um switch case
    String estado = "FOME"; // Definir o estado como fome

    Platform.runLater(() -> { // Atualizar a interface gráfica
        switch (id) { // Switch case para atualizar a interface gráfica de acordo com o filósofo
            case 0: 
                prato_f1.setImage(new Image("images/prato_vazio.png")); // Atualizar a imagem do prato do filósofo 1
                balao_f1.setImage(new Image("images/fome_direito.png")); // Atualizar a imagem do balão de pensamento do filósofo 1
                estado_filosofo1.setText(estado); // Atualizar o estado do filósofo 1
                break; // Sair do switch case
            case 1:
                prato_f2.setImage(new Image("images/prato_vazio.png")); // Atualizar a imagem do prato do filósofo 2
                balao_f2.setImage(new Image("images/fome_direito.png")); // Atualizar a imagem do balão de pensamento do filósofo 2
                estado_filosofo2.setText(estado); // Atualizar o estado do filósofo 2
                break; // Sair do switch case
            case 2: 
                prato_f3.setImage(new Image("images/prato_vazio.png")); // Atualizar a imagem do prato do filósofo 3
                balao_f3.setImage(new Image("images/fome_direito.png")); // Atualizar a imagem do balão de pensamento do filósofo 3
                estado_filosofo3.setText(estado); // Atualizar o estado do filósofo 3
                break; // Sair do switch case
            case 3:
                prato_f4.setImage(new Image("images/prato_vazio.png")); // Atualizar a imagem do prato do filósofo 4
                balao_f4.setImage(new Image("images/fome_esquerdo.png"));  // Atualizar a imagem do balão de pensamento do filósofo 4
                estado_filosofo4.setText(estado); // Atualizar o estado do filósofo 4
                break; // Sair do switch case
            case 4:
                prato_f5.setImage(new Image("images/prato_vazio.png")); // Atualizar a imagem do prato do filósofo 5
                balao_f5.setImage(new Image("images/fome_esquerdo.png")); // Atualizar a imagem do balão de pensamento do filósofo 5
                estado_filosofo5.setText(estado); // Atualizar o estado do filósofo 5
                break; // Sair do switch case
            default:
                break; // Sair do switch case
        } // Fim do switch case
    }); // Fim do Platform.runLater

    System.out.println("Filósofo " + id + " está " + estado); // Imprimir o estado do filósofo
} // Fim do método Fome

/*
 * Método: atualizarVelocidadeLabels
 * Função: Atualizar as labels de velocidade
 * Parâmetros: int filosofoId, int tempoPensando, int tempoComendo0
 * Retorno: void
 */
public void atualizarVelocidadeLabels(int filosofoId, int tempoPensando, int tempoComendo) { // Atualizar as labels de velocidade
    int velocidadePensar = mapearVelocidade(tempoPensando); // Mapear a velocidade de pensar
    int velocidadeComer = mapearVelocidade(tempoComendo); // Mapear a velocidade de comer

    Platform.runLater(() -> { // Atualizar a interface gráfica
        switch (filosofoId) { // Switch case para atualizar a interface gráfica de acordo com o filósofo
            case 0:
                velocidadePensar_f1.setText(Integer.toString(velocidadePensar)); // Atualizar a velocidade de pensar do filósofo 1
                velocidadeComer_f1.setText(Integer.toString(velocidadeComer)); // Atualizar a velocidade de comer do filósofo 1
                break;
            case 1:
                velocidadePensar_f2.setText(Integer.toString(velocidadePensar)); // Atualizar a velocidade de pensar do filósofo 2
                velocidadeComer_f2.setText(Integer.toString(velocidadeComer)); // Atualizar a velocidade de comer do filósofo 2
                break;
            case 2:
                velocidadePensar_f3.setText(Integer.toString(velocidadePensar)); // Atualizar a velocidade de pensar do filósofo 3
                velocidadeComer_f3.setText(Integer.toString(velocidadeComer)); // Atualizar a velocidade de comer do filósofo 3
                break;
            case 3:
                velocidadePensar_f4.setText(Integer.toString(velocidadePensar)); // Atualizar a velocidade de pensar do filósofo 4
                velocidadeComer_f4.setText(Integer.toString(velocidadeComer)); // Atualizar a velocidade de comer do filósofo 4
                break;
            case 4:
                velocidadePensar_f5.setText(Integer.toString(velocidadePensar)); // Atualizar a velocidade de pensar do filósofo 5
                velocidadeComer_f5.setText(Integer.toString(velocidadeComer)); // Atualizar a velocidade de comer do filósofo 5
                break;
            default:
                break;
        } // Fim do switch case
    }); // Fim do Platform.runLater
} // Fim do método atualizarVelocidadeLabels

/*
 * Método: mapearVelocidade
 * Função: A velocidade de pensar e comer em código são dadas em milissegundos de 1000 a 10000, 
 * esse método faz a conversão para o intervalo de 1 a 10 para que possa ser exibido de forma adequade na interface gráfica
 * Parâmetros: int tempo
 * Retorno: int
 */
public int mapearVelocidade(int tempo) { // 
    // Mapeia o tempo para o intervalo de 1 a 10
    int velocidade = 1 + (10 - 1) * (tempo - 1000) / (10000 - 1000); // O cálculo funciona como uma regra de três
    
    // Garante que a velocidade está dentro dos limites de 1 a 10
    if (velocidade < 1) { // Se a velocidade for menor que 1
        velocidade = 1; // Definir a velocidade como 1
    } else if (velocidade > 10) { // Se a velocidade for maior que 10
        velocidade = 10; // Definir a velocidade como 10
    } // Fim do if

    return velocidade; // Retornar a velocidade
} // Fim do método mapearVelocidade

/*
 * Método: colocarGarfos
 * Função: Colocar os garfos na mesa
 * Parâmetros: Filosofo f
 * Retorno: void
 */
  public void colocaGarfos(Filosofo f) { // Colocar os garfos na mesa
    f.garfoDir.setVisible(true); // Deixar o garfo da direita visível
    f.garfoEsq.setVisible(true); // Deixar o garfo da esquerda visível
  } // Fim do método colocaGarfos
} // Fim da classe MainController, amém 