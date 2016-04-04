import java.awt.Dimension;

/**
 * Classe responsavel por calcular as dimensoes do objeto 
 * enquanto o usuario redimensiona a tela
 * 
 * @author Marcos Thomaz
 */
public class PontoRecursivo {
	
	/**
	 * Calcula um ponto recursivo
	 * 
	 * @param x
	 * @param dim
	 * @return
	 */
	public static int getPonto(float posicao, double tela) {
		return (int)(posicao*tela);
	}
	
}
