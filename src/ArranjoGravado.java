import javax.swing.JPanel;

import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Color;

/**
 * Write a description of class ArranjoGravado here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ArranjoGravado implements Transparencia
{
    private int[] arranjo;
    private Color[] corIdxs;
    private String nome;
    
    /**
     * Constructor for objects of class ArranjoGravado
     */
	public ArranjoGravado(int[] arranjo, Color[] corIdxs, String nome) {
        this.arranjo = arranjo;
        this.corIdxs = corIdxs;
        this.nome = nome;
    }
    
    
    public void pintar(Graphics2D pincel, JPanel contexto) {
        Dimension dim = contexto.getSize();

        pincel.drawString(this.nome, PontoRecursivo.getPonto(.4f, dim.getWidth()),
        		PontoRecursivo.getPonto(.98f, dim.getHeight()));
        
        final int PROPORCAO = PontoRecursivo.getPonto(.45f, dim.getHeight());
        
        // Seu código desenhista começa aqui!
        pincel.setStroke(new BasicStroke(5));
        
        // Maior coluna
        int maior = encontraMaior(this.arranjo);
        
        // Pega a largura e a altura
        int qtdeCol = this.arranjo.length;        // total de coluna
        int largura = dim.width;
        int largPCol = (largura)/qtdeCol;
        
        // Criando as colunas
        int distParede = (int) (2.5f*(largura)/100);
        int largCol = (int) (59.01f*(largPCol)/100);
        int distCol = (int) (40.99f*(largPCol)/100);
        int posicaoInicial = PontoRecursivo.getPonto(.85f, dim.getHeight());
        int x = 0;
        for (int i = 0; i < arranjo.length; i++) {
        	
        	// Calcula a largura da coluna
        	int alturaC = (int) (arranjo[i]*PROPORCAO)/maior;   
        	
        	// Definindo a posicao da coluna
    		x += ( i == 0 ) ? distParede : (largCol+distCol);
    		pincel.drawRect(x, posicaoInicial-alturaC, largCol, alturaC);
    		pincel.setColor( ( this.corIdxs[i] == null ) ? Color.BLUE : this.corIdxs[i]);
    		pincel.fillRect(x, posicaoInicial-alturaC, largCol, alturaC);
    		pincel.setColor(Color.BLACK);
    		pincel.drawString(arranjo[i]+"", x+(largCol/2), posicaoInicial+20);
		}
        
         
        // throw new UnsupportedOperationException("Funcionalidade ainda não implementada pelo aluno");
    }
    
    /**
     * Este método encontra o maior elemento de um arranjo de entrada.
     * 
     * @param arranjo Arranjo a ser procurado pelo seu maior elemento.
     * @return Valor do maior elemento.
     */
    private static int encontraMaior(int[] arranjo) {
		int maior = 0;
		for (int i = 0; i < arranjo.length; i++) {
			if( arranjo[i] > maior ){
				maior = arranjo[i];
			}
		}
		return maior;	
    }

}
