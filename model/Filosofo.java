/* ***************************************************************
 * Autor............: Guilherme Dias Sousa
 * Matricula........: 202211033
 * Inicio...........: 15/10/2023
 * Ultima alteracao.: 29/10/2023
 * Nome.............: Filosofo
 * Funcao...........: Classe modelo para representar os filosofos	
 *************************************************************** */
package model; // Pacote para agrupamento das classes

import controle.MainController; // Importação da classe MainController
import javafx.scene.image.ImageView; // Importação da classe ImageView

/*
 * Classe Filosofo
 * Funcao...........: Representar os filosofos
 * Super classe.....: Thread
 * Metodos..........: pegarGarfo, devolveGarfos, testa, aumentarTempoPensando, diminuirTempoPensando, aumentarTempoComendo, diminuirTempoComendo, setGarfos, setId, getid, getTComendo, getTPensando, setTempoComendo, setTempoPensando, setPausar, stopThread, run, setController
 */
public class Filosofo extends Thread {
    // Atributos da classe filósofo
    public ImageView garfoDir, garfoEsq; // Imagens dos garfos
    private MainController controle; // Objeto de controle
    private int id; // Identificador do filósofo
    private int T_COMENDO = 1000, T_PENSANDO = 1000; // Tempo de comer e pensar
    private boolean pausar = false; // Pausar a thread
    private int PENSANDO = 0, FOME = 1, COMENDO = 2; // Estados do filósofo
    private int estado = PENSANDO; // Estado inicial do filósofo

    /*
     * Método: Filosofo
     * Funcao: Construtor da classe
     * Parametros: MainController controle
     * Retorno: void
     */
    public Filosofo(MainController controle) {
        this.controle = controle; // Inicialização do objeto de controle
    } // Fim do método construtor

    /*
     * Método: pegarGarfo
     * Funcao: Pegar os garfos utilizando o algoritmo do jantar dos filósofos
     * Parametros: void
     * Retorno: void
     */
    public void pegarGarfo() throws InterruptedException {
        System.out.println("Entrando em pegarGarfo: " + id); // Mensagem de debug
        // Verificar se os objetos foram inicializados
        if (controle != null && controle.mutex != null && controle.estado != null && controle.semaforos != null) {
            controle.mutex.acquire(); // Adquirir o mutex
            System.out.println("Mutex adquirido por: " + id); // Mensagem de debug
            controle.estado[id] = FOME; // Mudar o estado do filósofo
            controle.fome(id); // Chama o método fome do objeto de controle 
            testa(id); // Testa se o filósofo pode comer
            controle.mutex.release(); // Libera o mutex
            System.out.println("Mutex liberado por: " + id); // Mensagem de debug
            // Verificar se o id é válido
            if (id >= 0 && id < controle.semaforos.length) {
                controle.semaforos[id].acquire(); // Adquirir o semáforo
                System.out.println("Garfo adquirido por: " + id); // Mensagem de debug
            } // Fim do if
        } // Fim do if
    } // Fim do método pegarGarfo
    
    /*
     * Método: devolveGarfos
     * Funcao: Devolver os garfos utilizando o algoritmo do jantar dos filósofos
     * Parametros: void
     * Retorno: void
     */
    public void devolveGarfos() throws InterruptedException {
        if (controle == null) {
            throw new IllegalStateException("Objeto de controle é nulo."); // Lançar exceção
        }
        controle.mutex.acquire(); // Adquirir o mutex
        controle.estado[id] = PENSANDO; // Mudar o estado do filósofo
        controle.colocaGarfos(this);// Chama o método colocaGarfos do objeto de controle
        testa(controle.verificarEsq(id)); // Testa se o garfo da esquerda está disponível
        testa(controle.verificarDir(id)); // Testa se o garfo da direita está disponível
        controle.mutex.release(); // Libera o mutex
    }

    /*
     * Método: testa
     * Funcao: Testar se o filósofo pode comer utilizando o algoritmo do jantar dos filósofos
     * Parametros: int i
     * Retorno: void
     */
    public void testa(int i) throws InterruptedException {
        if (controle.estado[i] == FOME && controle.estado[controle.verificarEsq(i)] != COMENDO
                && controle.estado[controle.verificarDir(i)] != COMENDO) { // Verificar se o filósofo pode comer
            controle.estado[i] = COMENDO; // Mudar o estado do filósofo
            controle.semaforos[i].release(); // Libera o semáforo
        } // Fim do if
    } // Fim do método testa

    /*
     * Método: aumentarTempoPensando
     * Funcao: Aumentar o tempo de pensar
     * Parametros: void
     * Retorno: void
     */
    public void aumentarTempoPensando() {
        if (T_PENSANDO < 10000) { // Verificar se o tempo é menor que 10 segundos
            T_PENSANDO += 1000; // Aumentar o tempo de pensar
        } // Fim do if
    } // Fim do método aumentarTempoPensando

    /*
     * Método: diminuirTempoPensando
     * Funcao: Diminuir o tempo de pensar
     * Parametros: void
     * Retorno: void
     */
    
    public void diminuirTempoPensando() {
        if (T_PENSANDO > 1000) { // Verificar se o tempo é maior que 1 segundo
            T_PENSANDO -= 1000; // Diminuir o tempo de pensar
        } // Fim do if
     } // Fim do método diminuirTempoPensando

     /*
      * Método: aumentarTempoComendo
      * Funcao: Aumentar o tempo de comer
      * Parametros: void
      * Retorno: void
      */
    
    public void aumentarTempoComendo() {
        if (T_COMENDO < 10000) { // Verificar se o tempo é menor que 10 segundos
            T_COMENDO += 1000; // Aumentar o tempo de comer
        } // Fim do if
    } // Fim do método aumentarTempoComendo

    /*
     * Método: diminuirTempoComendo
     * Funcao: Diminuir o tempo de comer
     * Parametros: void
     * Retorno: void
     */
    
    public void diminuirTempoComendo() {
        if (T_COMENDO > 1000) {
            T_COMENDO -= 1000;
        } // Fim do if
    } // Fim do método diminuirTempoComendo
    
    /*
     * Método: setGarfos
     * Funcao: Definir os garfos
     * Parametros: ImageView garfoEsq, ImageView garfoDir
     * Retorno: void
     */
    public void setGarfos(ImageView garfoEsq, ImageView garfoDir) {
        this.garfoDir = garfoDir; // Definir o garfo da direita
        this.garfoEsq = garfoEsq; // Definir o garfo da esquerda
    } // Fim do método setGarfos

    /*
     * Método: setId
     * Funcao: Definir o id dos filósofos
     * Parametros: int id
     * Retorno: void
     */
    public void setId(int id) {
        this.id = id; // Definir o id
    } // Fim do método setId

    /*
     * Método: getid
     * Funcao: Obter o id dos filósofos
     * Parametros: void
     * Retorno: int
     */
    public int getid () {
        return id; // Retornar o id
    } // Fim do método getid

    /*
     * Método: getTComendo
     * Funcao: Obter o tempo de comer
     * Parametros: void
     * Retorno: int
     */
    public int getTComendo() {
        return T_COMENDO; // Retornar o tempo de comer
    } // Fim do método getTComendo
    
    /*
     * Método: getTPensando
     * Funcao: Obter o tempo de pensar
     * Parametros: void
     * Retorno: int
     */
    public int getTPensando() {
        return T_PENSANDO; // Retornar o tempo de pensar
    } // Fim do método getTPensando

    /*
     * Método: setTempoComendo
     * Funcao: Definir o tempo de comer
     * Parametros: int tempoComendo
     * Retorno: void
     */
    public void setTempoComendo(int tempoComendo) {
        this.T_COMENDO = tempoComendo * 1000; // Definir o tempo de comer
    } // Fim do método setTempoComendo
    /*
     * Método: setTempoPensando
     * Funcao: Definir o tempo de pensar
     * Parametros: int tempoPensando
     * Retorno: void
     */
    public void setTempoPensando(int tempoPensando) {
        this.T_PENSANDO = tempoPensando * 1000; // Definir o tempo de pensar
    } // Fim do método setTempoPensando

    /*
     * Método: setPausar
     * Funcao: Pausar a thread
     * Parametros: void
     * Retorno: void
     */
    public void setPausar() {
        this.pausar = !pausar; // Inverter o valor de pausar que foi declarado como false
    } // Fim do método setPausar
    /*
     * Método: stopThread
     * Funcao: Ele não para a Thread exatamente, apenas retorna os valores de comer e pensar para o valor inicial
     * Parametros: void
     * Retorno: void
     */
    public void stopThread() {
        T_COMENDO = 1000; // Definir o tempo de comer para o valor inicial
        T_PENSANDO = 1000; // Definir o tempo de pensar para o valor inicial
        estado = -1; // Definir o estado para -1
    }
    
    /*
     * Método: run
     * Funcao: Executar a thread utilizando um while para verificar se a thread está pausada
     * Parametros: void
     * Retorno: void
     */
    public void run() {
        while (estado >= 0) {
            try {
                while (pausar) {
                    System.out.println("Thread pausada por Filósofo " + id); // Mensagem de debug
                    sleep(1000); // Aguardar um curto período para verificar
                } // Fim do while
    
                if (estado == PENSANDO) { // Verificar se o filósofo está pensando
                    controle.pensar(this); // Chama o método pensar do objeto de controle
                } // Fim do if
                if (estado == FOME) { // Verificar se o filósofo está com fome
                    pegarGarfo(); // Chama o método pegarGarfo
                } // Fim do if
                if (estado == COMENDO) { // Verificar se o filósofo está comendo
                    controle.comer(this); // Chama o método comer do objeto de controle
                } // Fim do if
                if (estado == 3) { // Verificar se o estado é 3
                    estado = -1; // Definir o estado para -1
                    devolveGarfos(); // Chama o método devolveGarfos
                } // Fim do if
                estado++; // Incrementar o estado
            } catch (InterruptedException e) { // Tratamento de exceção
                e.printStackTrace(); // Imprimir o erro
            } // Fim do try/catch
        } // Fim do while
    } // Fim do método run
    
    /*
     * Método: setController
     * Funcao: Definir o objeto de controle
     * Parametros: MainController mainController
     * Retorno: void
     */
    public void setController(MainController mainController) {
        this.controle = mainController; // Definir o objeto de controle
    } // Fim do método setController
} // Fim da classe Filosofo