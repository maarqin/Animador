import java.util.Arrays;
import java.util.LinkedList;
import java.util.ListIterator;
import java.awt.Color;

/**
 * Write a description of class Gravador here.
 * 
 * @author Marcos Thomaz
 * @version 2015-05-24
 */
public class Gravador
{
	
    /**
     * Atributo responsavel por armazenar as sequencias de gravacao
     */
    private LinkedList<Transparencia> seqGravacoes;
	
    /**
     * Construtor da classe
     */
    public Gravador() {
        this.seqGravacoes = new LinkedList<Transparencia>();
    }
    
    /**
     * Metodo responsavel por iniciar e finalizar um array na tela
     * Esse metodo cria uma copia do array atual.
     * Cria um arranjo de cores com o tamanho do arrajo atual
     * E seta um nome no display
     * 
     * @param arranjo
     * @param nome
     */
    public void gravarArranjo(int[] arranjo, String nome) {
        int[] copia = Arrays.copyOf(arranjo, arranjo.length);
        Color[] cores = new Color[arranjo.length];
        ArranjoGravado gravacao = new ArranjoGravado(copia, cores, nome);
        seqGravacoes.add(gravacao);
    }
    
    /**
     * Metodo que informa o usuario com cor distinta o valor/posicao que o pivo se encontra atualmente
     * 
     * @param arranjo
     * @param i
     * @param nome
     */
    public void gravarIndiceDestacado(int[] arranjo, int i, String nome) {
        int[] copia = Arrays.copyOf(arranjo, arranjo.length);
        Color[] cores = new Color[arranjo.length];
        cores[i] = Color.YELLOW;
        ArranjoGravado gravacao = new ArranjoGravado(copia, cores, nome);
        seqGravacoes.add(gravacao);
    }
    
    /**
     * Essa funcao denota de forma simples quando o algoritmo está comparando dois valores atuais
     * 
     * @param arranjo
     * @param i
     * @param j
     */
    public void gravarComparaçãoSimples(int[] arranjo, int i, int j) {
        int[] copia = Arrays.copyOf(arranjo, arranjo.length);
        Color[] cores = new Color[arranjo.length];
        cores[i] = cores[j] = Color.GRAY;
        ArranjoGravado gravacao = new ArranjoGravado(copia, cores, "Comparação");
        seqGravacoes.add(gravacao);
    }
    
    /**
     * Esse metodo pinta as colunas que foras alteradas
     * 
     * @param arranjo
     * @param i
     * @param j
     */
    public void gravarPosTrocas(int[] arranjo, int i, int j) {
        int[] copia = Arrays.copyOf(arranjo, arranjo.length);
        Color[] cores = new Color[arranjo.length];
        cores[i] = cores[j] = Color.YELLOW;
        ArranjoGravado gravacao = new ArranjoGravado(copia, cores, "Pós-troca");
        seqGravacoes.add(gravacao);
    }
    
    /**
     * Metodo que pinta o intervalo que ira ser buscado
     * 
     * @param arranjo
     * @param inicio
     * @param fim
     */
    public void gravarInicioFim(int[] arranjo, int inicio, int fim) {
		int[] copia = Arrays.copyOf(arranjo, arranjo.length);
		Color[] cores = new Color[arranjo.length];
		cores[fim] = cores[inicio] = Color.GREEN;
		ArranjoGravado gravacao = new ArranjoGravado(copia, cores, "Posição inicial e final");
		seqGravacoes.add(gravacao);
	}
    
    /**
     * Retorna um lista contendo os quadros com as execucoes do algoritmo escolhido pelo usuario
     * 
     * @return
     */
    public ListIterator<Transparencia> getFilme() {
        return seqGravacoes.listIterator();
    }
    
}
