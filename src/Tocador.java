import javax.swing.JPanel;
import javax.swing.BorderFactory;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.util.ListIterator;

/**
 * Write a description of class Tocador here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tocador extends JPanel
{
    private int numQuadro = 0;
    private Transparencia quadro = null;
    private ListIterator<Transparencia> quadrosFilme = null;
	
    /**
     * Construtor que recebe 
     * 
     * @param quadrosFilme
     */
    public Tocador(ListIterator<Transparencia> quadrosFilme) {
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        carregarFilme(quadrosFilme);
    }
    
    /**
     * Construtor sem parametros
     */
    public Tocador() {
        this(null);
    }
    
    /**
     * @param quadrosFilme
     */
    public void carregarFilme(ListIterator<Transparencia> quadrosFilme) {
        this.quadrosFilme = quadrosFilme;
        this.quadro = null;
        numQuadro = 0;
    }
    
    /**
     * 
     */
    public void avancarFilme() {
        if (quadrosFilme.hasNext()) {
            quadro = quadrosFilme.next();
            numQuadro++;
        }
    }
    
    /**
     * 
     */
    public void voltarFilme() {
        if (quadrosFilme.hasPrevious()) {
            quadro = quadrosFilme.previous();
            numQuadro--;
        }
    }
    
    /**
     * 
     */
    public void rebobinarFilme() {
        while (quadrosFilme.hasPrevious()) {
            quadro = quadrosFilme.previous();
            numQuadro--;
        }
    }
    
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D pincel = (Graphics2D) g;
    	
    	int width = super.getWidth();
    	int height = super.getHeight();
        
        if (quadro != null) {
            quadro.pintar(pincel, this);
        } else {
        	
        	pincel.setColor(Color.GRAY);
        	pincel.setFont(new Font("Tahoma", Font.PLAIN, 30)); 
        	pincel.drawString("O Filme ainda não iniciou.", PontoRecursivo.getPonto(.3f, width),
        			PontoRecursivo.getPonto(.5f, height));
        	pincel.setColor(Color.BLACK);
        	pincel.setFont(new Font("Tahoma", Font.PLAIN, 14)); 

            // ESCREVER NO MEIO DA TELA "O Filme ainda não iniciou."
            // throw new UnsupportedOperationException("O aluno ainda não implementou essa funcionalidade.");
        }
        pincel.drawString((numQuadro)+"", PontoRecursivo.getPonto(.04f, width), PontoRecursivo.getPonto(.95f, height));
        
        // ESCREVER NO CANTO INFERIOR DIREITO DA TELA "Quadro 'numQuadro'"
        // throw new UnsupportedOperationException("O aluno ainda não implementou essa funcionalidade.");
    }

}
