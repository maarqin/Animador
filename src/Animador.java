import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.util.ListIterator;


public class Animador extends JFrame {  
	
    // Elementos (widgets) da nossa interface
    private Tocador tela;
    private JButton btnCarregar, btnProx, btnAnt;
    private JComboBox<String> boxListaAlgoritmos;
    private JTextField txfEntradaArranjo;
    private JTextField txfEntradaChaveBusca;
    
    /**
     * Ponto de início do programa. Simplesmente informa que é um aplicativo gráfico e passa o
     * controle para o construtor do método. Utiliza uma segunda thread para isso.
     * 
     * @param args Argumentos recebidos da linha de comando.
     */
    public static void main(String[] args) {
        // Código que inicializa o aplicativo gráfico
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Animador();
            }
        });
    }
    
    /**
     * O construtor do aplicativo Animador. É aqui que todos os elementos da interface gráfica são
     * construídos, configurados e posicionados. Por enquanto, aqui também é feito o tratamento de 
     * eventos (input de usuário através de mouse e teclado), utilizando classes anônimas.
     * 
     * @see javax.swing.JFrame
     */
    public Animador() {
        
        // Cria e configura botão Desenhar Arranjo
        btnCarregar = new JButton("Carregar");
        btnCarregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onBtnCarregarPressionado();
            } 
        });
        
        // Cria btn anterior
        btnAnt = new JButton("Ant");
        btnAnt.setEnabled(false);
        btnAnt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onBtnAntPressionado();
            } 
        });
        
        // Cria btn proximo
        btnProx = new JButton("Prox");
        btnProx.setEnabled(false);
        btnProx.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onBtnProxPressionado();
            }
        });
        
        // Cria e configura o campo de seleção de algoritmos animados
        String algsAnimados[] = {
            "Busca Sequencial", "Busca Binária",
            "Bolha", "Seleção", "Inserção", "Mergesort", "Quicksort" };
        boxListaAlgoritmos = new JComboBox<String>(algsAnimados);
        boxListaAlgoritmos.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                String item = (String) e.getItem();
                novoAlgoritmoSelecionado(item);
            }
        });
        
        final int COMPRIMENTO_ENTRADA_ARRANJO = 20;
        txfEntradaArranjo = new JTextField("", COMPRIMENTO_ENTRADA_ARRANJO);
        
        final int COMPRIMENTO_ENTRADA_CHAVE_BUSCA = 3;
        txfEntradaChaveBusca = new JTextField("", COMPRIMENTO_ENTRADA_CHAVE_BUSCA);
        
        // Campo para abrigar e organizar os botões e campos de entrada
        JPanel pnlBotoes = new JPanel(new FlowLayout());
        pnlBotoes.add(new JLabel("Arranjo:"));
        pnlBotoes.add(txfEntradaArranjo);
        pnlBotoes.add(new JLabel("Chave:"));
        pnlBotoes.add(txfEntradaChaveBusca);
        pnlBotoes.add(boxListaAlgoritmos);
        pnlBotoes.add(btnCarregar);
        pnlBotoes.add(btnAnt);
        pnlBotoes.add(btnProx);
        
        // Cria e configura a tela do desenhista
        tela = new Tocador();
        tela.setPreferredSize(new Dimension(800, 600));

        // Container que organiza o posicionamento dos botões e da tela
        Container organizacao = getContentPane();
        organizacao.setLayout(new BorderLayout());
        organizacao.add(tela, BorderLayout.CENTER);
        organizacao.add(pnlBotoes, BorderLayout.SOUTH);

        // Configurações de comportamento do aplicativo
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Animador - Lab. Comp. II");
        pack();
        setVisible(true);
        requestFocus();
    }
    
    /**
     * Carregar arranjo que foi digitado pelo usuario
     */
    public void onBtnCarregarPressionado() {
        // Verifica o desenho escolhido no ComboBox e repassa à tela para pintura
        int[] arranjo = textoParaArranjo(txfEntradaArranjo.getText());
        String algoritmo = (String) boxListaAlgoritmos.getSelectedItem();
        
        int chave = 0;
        if( !txfEntradaChaveBusca.getText().equals("") ){
        	chave = Integer.parseInt(txfEntradaChaveBusca.getText());
        }
    	Gravador anim = new Gravador();
        Gravador novoFilme = null;
        switch (algoritmo) {
            case "Busca Sequencial":
                novoFilme = AlgoritmosAnimados.buscaSequencial(arranjo, chave);
                break;

            case "Bolha":
                novoFilme = AlgoritmosAnimados.bolha(arranjo);
                break;

            case "Seleção":
                novoFilme = AlgoritmosAnimados.selectionSort(arranjo);
                break;

            case "Busca Binária":
                novoFilme = AlgoritmosAnimados.buscaBinaria(arranjo, chave);
                break;
                
            case "Inserção":
                novoFilme = AlgoritmosAnimados.insertionSort(arranjo);
                break;
                
            case "Mergesort":
                anim.gravarArranjo(arranjo, "Disposição inicial");
                novoFilme = AlgoritmosAnimados.particionaMS(arranjo, 0, arranjo.length-1, anim);
                anim.gravarArranjo(arranjo, "Disposição final");
            	break;
            	
            case "Quicksort":
                anim.gravarArranjo(arranjo, "Disposição inicial");
                novoFilme = AlgoritmosAnimados.particionaQS(arranjo, 0, arranjo.length-1, anim);
                anim.gravarArranjo(arranjo, "Disposição final");
            	break;
                
            default:
                throw new UnsupportedOperationException("Funcionalidade ainda não implementada pelo aluno");
        }
          
        if (novoFilme != null) {
            tela.carregarFilme(novoFilme.getFilme());
            btnProx.setEnabled(true);
            btnAnt.setEnabled(true);
        }
        
        onBtnProxPressionado();
    }
    
    /**
     * Clicou em btn proximo
     */
    public void onBtnProxPressionado() {
        tela.avancarFilme();
        tela.repaint();
        requestFocusInWindow();
    }
    
    /**
     * Clicou em btn anterior
     */
    public void onBtnAntPressionado() {    
        tela.voltarFilme();
        tela.repaint();
        requestFocusInWindow();
    }
    
    /**
     * Ativa ou desabita o campo txfEntradaChaveBusca para os algoritmos de busca
     * 
     * @param alg
     */
    public void novoAlgoritmoSelecionado(String alg) {
        if (alg.equals("Busca Sequencial") || alg.equals("Busca Binária")) {
            txfEntradaChaveBusca.setEnabled(true);
        } else {
            txfEntradaChaveBusca.setEnabled(false);
        }
    }    
    
    /**
     * Método auxiliar para a classe que converte para um arranjo de ints uma sequencia de valores em
     * String, separados por ",". Valores não numéricos são considerados como 0.
     * 
     * @param textoArranjo String com valores separados por ",".
     * @return Arranjo de inteiros de acordo com a String de entrada.
     */
    private static int[] textoParaArranjo(String textoArranjo) {
        String[] numeros = textoArranjo.split(",");
        int[] arranjo = new int[numeros.length];
        
        for (int i = 0; i < numeros.length; i++) {
            try {
                arranjo[i] = Integer.parseInt(numeros[i]);
            } catch (NumberFormatException nfe) {
                String entradaErrada = nfe.getMessage();
                int posIni = entradaErrada.indexOf("\"") + 1;
                int posFim = entradaErrada.lastIndexOf("\"");
                entradaErrada = entradaErrada.substring(posIni, posFim);
                
                System.err.println("Ignorando entrada '" + entradaErrada + "'. Utilizado valor 0.");
                arranjo[i] = 0;
            }
        }
        
        return arranjo;
    }
    
}