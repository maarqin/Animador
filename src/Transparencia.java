import javax.swing.JPanel;
import java.awt.Graphics2D;

/**
 * Write a description of class FramedArray here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public interface Transparencia
{
    /**
     * Monta as barras na tela
     * 
     * @param g2d
     * @param contexto
     */
    void pintar(Graphics2D g2d, JPanel contexto);
}
