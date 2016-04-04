
/**
 * Write a description of class AlgoritmosAnimados here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AlgoritmosAnimados {
	
    /**
     * Algoritmo de busca sequencial
     * 
     * @param arranjo
     * @param chave
     * @return
     */
    public static Gravador buscaSequencial(int[] arranjo, int chave) {
        Gravador anim = new Gravador();
        anim.gravarArranjo(arranjo, "Inicio de busca sequencial");      
        
        int i = 0;
        anim.gravarIndiceDestacado(arranjo, i, "Busca sequencial");
        while (i < arranjo.length && arranjo[i] != chave) {
            i++;
            anim.gravarIndiceDestacado(arranjo, i, "Busca sequencial");
        }
        
        if (i < arranjo.length) {
            anim.gravarIndiceDestacado(arranjo, i, "Chave encontrada");
        } else {
            anim.gravarArranjo(arranjo, "Chave não encontrada");
        }
        
        return anim;
    }
    
    /**
     * Algoritmo de bubblesort
     * 
     * @param arranjo
     * @return
     */
    public static Gravador bolha(int[] arranjo) {
        Gravador anim = new Gravador();
        
        anim.gravarArranjo(arranjo, "Disposição inicial");
    	boolean trocou;
    	do {
			trocou = false;
			for (int i = 0; i < arranjo.length-1; i++) {
				if( arranjo[i+1] < arranjo[i]  ){
					anim.gravarComparaçãoSimples(arranjo, i, i+1);
					
					int aux = arranjo[i];
					arranjo[i] = arranjo[i+1];
					arranjo[i+1] = aux;
					trocou = true;
					anim.gravarPosTrocas(arranjo, i, i+1);
				}
			}
		} while (trocou);
    	
        anim.gravarArranjo(arranjo, "Disposição final");
        return anim;
    }
    
    /**
     * Algoritmo selectionsort
     * 
     * @param arranjo
     * @return
     */
    public static Gravador selectionSort(int[] arranjo) {
        Gravador anim = new Gravador();
        
        anim.gravarArranjo(arranjo, "Disposição inicial");
    	int menor;
    	for (int i = 0; i < arranjo.length-1; i++) {
			menor = i;
			for (int j = i+1; j < arranjo.length; j++) {
		        anim.gravarComparaçãoSimples(arranjo, j, menor);
				if( arranjo[j] < arranjo[menor] ){ 
					menor = j;
				}
			}
			int aux = arranjo[menor];
			arranjo[menor] = arranjo[i];
			arranjo[i] = aux;
			if( aux != arranjo[menor] )
				anim.gravarPosTrocas(arranjo, i, menor);
		}
        anim.gravarArranjo(arranjo, "Disposição final");
    	return anim;
	}
    
    /**
     * Metodo de busca binaria
     * 
     * @param arranjo
     * @param chave
     * @return
     */
    public static Gravador buscaBinaria(int[] arranjo, int chave) {
    	Gravador anim = new Gravador();
    	
        anim.gravarArranjo(arranjo, "Disposição inicial");

        boolean achou = false;
    	int inicio = 0, fim = arranjo.length;
    	do {
	        anim.gravarInicioFim(arranjo, inicio, fim-1);
    		int pivo = ((fim-inicio)/2)+inicio;
            anim.gravarIndiceDestacado(arranjo, pivo, "Posição atual");
    		if( arranjo[pivo] == chave ){
    			anim.gravarIndiceDestacado(arranjo, pivo, "Chave encontrada");
    			achou = true;
    		} else if( chave > arranjo[pivo] ){
    			inicio = pivo+1;
    		} else {
				fim = pivo;
    		}

    	} while ((fim-inicio) > 0 && !achou);

    	if( !achou ){
            anim.gravarArranjo(arranjo, "Chave não encontrada");
    	}
    	
    	return anim;
	}
    
    /**
     * Metodo insertion de ordenacao
     * 
     * @param arranjo
     * @return
     */
    public static Gravador insertionSort(int[] arranjo) {
		Gravador anim = new Gravador();
		
        anim.gravarArranjo(arranjo, "Disposição inicial");

		for (int i = 1; i < arranjo.length; i++) {
			int atual = arranjo[i];
			int j = i-1;
	        anim.gravarComparaçãoSimples(arranjo, j, i);
			while((j >= 0) && (atual < arranjo[j])){
	            anim.gravarIndiceDestacado(arranjo, j+1, "Deslocando");
				arranjo[j+1] = arranjo[j];
				j--;
			}
			arranjo[j+1] = atual;
	        anim.gravarIndiceDestacado(arranjo, j+1, "Posição final");

		}
        anim.gravarArranjo(arranjo, "Disposição final");

		return anim;
	}
    
    /**
     * @param arranjo
     * @param inicio
     * @param fim
     * @param anim
     * @return
     */
    public static Gravador particionaMS(int[] arranjo, int inicio, int fim, Gravador anim) {

		if (inicio < fim){
		    int meio = inicio+(fim-inicio)/2;
		    anim.gravarInicioFim(arranjo, inicio, fim);
		    particionaMS(arranjo, inicio, meio, anim);
		    particionaMS(arranjo, meio+1, fim, anim);
		    
		    mergeSort(arranjo, inicio, meio, fim, anim);
		}
		return anim;
	}

	/**
	 * Metodo de ordenacao merge
	 * 
	 * @param arranjo
	 * @param inicio
	 * @param fim
	 */
	public static void mergeSort(int[] arranjo, int inicio, int meio, int fim, Gravador anim) {

	    int a1 = meio - inicio + 1;
	    int a2 =  fim - meio;
	   		
		int[] esquerda = new int[a1];
		int[] direita = new int[a2];
		for (int i = 0; i <= meio - inicio; i++) {
			esquerda[i] = arranjo[inicio + i];
		}
		for (int i = 0; i <= fim - meio - 1; i++) {
			direita[i] = arranjo[meio + 1 + i];
		}
		int i = 0;
		int j = 0;
		for (int k = inicio; k <= fim; k++) {
			if (i < a1 && j < a2) {
				anim.gravarComparaçãoSimples(arranjo, i, j);
				if (esquerda[i] < direita[j]) {
					arranjo[k] = esquerda[i++];
				} else {
					arranjo[k] = direita[j++];
				}
			} else if (i < a1) {
				anim.gravarComparaçãoSimples(arranjo, i, j);
				arranjo[k] = esquerda[i++];
				
			} else if (j < a2) {
				anim.gravarComparaçãoSimples(arranjo, i, j);
				arranjo[k] = direita[j++];
			}
			anim.gravarIndiceDestacado(arranjo, k, "Intercalaçao");
		}
	}
    
	/**
	 * Particiona/Cria os sub arranjos do quicksort
	 * 
	 * @param arranjo
	 * @param inicio
	 * @param fim
	 */
	public static Gravador particionaQS(int[] arranjo, int inicio, int fim, Gravador anim){
		if (inicio < fim){
			int pPivo = quickSort(arranjo, inicio, fim, anim);
			particionaQS(arranjo, inicio, pPivo - 1, anim);	
			particionaQS(arranjo, pPivo + 1, fim, anim);
		}
		return anim;
	}

	/**
	 * Rearranjar os arranjos do quicksort
	 * 
	 * @param arranjo
	 * @param inicio
	 * @param fim
	 * @return
	 */
	private static int quickSort(int[] arranjo, int inicio, int fim, Gravador anim)
	{
		anim.gravarInicioFim(arranjo, inicio, fim);

		int pivo = arranjo[inicio];
		int i = inicio + 1, f = fim;
		while (i <= f)
		{
			if (arranjo[i] <= pivo){
				anim.gravarComparaçãoSimples(arranjo, inicio, i);
				i++;
			} else if (pivo < arranjo[f]) {
				anim.gravarComparaçãoSimples(arranjo, inicio, f);
				f--;
			} else {
				
				int troca = arranjo[i];
				arranjo[i] = arranjo[f];
				arranjo[f] = troca;
				i++;
				f--;
				anim.gravarPosTrocas(arranjo, i, f);
			}
		}
		arranjo[inicio] = arranjo[f];
		arranjo[f] = pivo;
        anim.gravarIndiceDestacado(arranjo, f, "Novo Pivô");

		return f;
	}

}
// 32,16,3,23,29,38,18,22,14,22,19,9,42,2,1,13,34,12,36,47








