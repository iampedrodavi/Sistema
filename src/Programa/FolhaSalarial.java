package Programa;

import javax.swing.*;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Toolkit;


public class FolhaSalarial extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField txtSearch;
    private JTable tabela;
    private JScrollPane scrollPane;

    public FolhaSalarial() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(FolhaSalarial.class.getResource("/Programa/Imagens/recursos-humanos.png")));
        setTitle("Lista de Funcionários");
        setSize(702, 520);
        setResizable(false);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);

       
        txtSearch = new JTextField();
        txtSearch.setBounds(135, 5, 200, 20);
        getContentPane().add(txtSearch);

       
        JButton btnSearch = new JButton("Buscar");
        btnSearch.setBounds(345, 5, 78, 20);
        getContentPane().add(btnSearch);

       
        JButton btnAposentadoria = new JButton("Aposentadoria");
        btnAposentadoria.setToolTipText("Para resetar a tabela, clicar novamente no botão BUSCAR!");
        btnAposentadoria.setBounds(445, 442, 121, 30);
        getContentPane().add(btnAposentadoria);

       
        JButton btnBack = new JButton("Voltar");
        btnBack.setBounds(10, 442, 100, 30);
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new MenuAdm().setVisible(true);
                dispose();
            }
        });
        getContentPane().add(btnBack);

        atualizarTabela(""); 
  
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String buscar = txtSearch.getText();
                atualizarTabela(buscar);
            }
        });

      
        btnAposentadoria.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarFuncionariosPertoAposentadoria();
            }
        });

        setVisible(true);
    }

    private void atualizarTabela(String buscar) {
        Conexao conexao = new Conexao();
        List<Funcionario> funcionarios = buscar.isEmpty() ? conexao.buscarFuncionarios() : conexao.buscarFuncionariosPorNome(buscar);

        String[] colunas = {"ID", "Nome", "Idade", "Cargo", "Salário"};
        String[][] dados = new String[funcionarios.size()][6];

        for (int i = 0; i < funcionarios.size(); i++) {
            Funcionario func = funcionarios.get(i);
            dados[i][0] = String.valueOf(func.getIdFun());
            dados[i][1] = func.getNomeFun();
            dados[i][2] = String.valueOf(func.getIdadeFun());
            dados[i][3] = func.getCargoFun();
            dados[i][4] = String.valueOf(func.getSalarioFun());
        }

        if (scrollPane != null) {
            getContentPane().remove(scrollPane);
        }

        tabela = new JTable(dados, colunas);
        tabela.setDefaultEditor(Object.class, null);
        scrollPane = new JScrollPane(tabela);
        scrollPane.setBounds(0, 36, 684, 395);
        getContentPane().add(scrollPane);
        
        JLabel lblNewLabel = new JLabel("Nome \r\ndo Funcionário:");
        lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
        lblNewLabel.setBounds(10, 5, 152, 20);
        getContentPane().add(lblNewLabel);
        
        JButton btnFolha = new JButton("Gerar Folha");
        btnFolha.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int linha = tabela.getSelectedRow(); 
                
                if (linha != -1) {
                    
                	String nome = tabela.getValueAt(linha, 1).toString();
                	String salarioStr = tabela.getValueAt(linha, 4).toString();

                	try {
                	    double salario = Double.parseDouble(salarioStr);

                	    double inss;
                	    if (salario <= 1302.00) {
                	        inss = salario * 0.075; 
                	    } else if (salario <= 2571.29) {
                	        inss = salario * 0.09; 
                	    } else if (salario <= 3856.94) {
                	        inss = salario * 0.12; 
                	    } else {
                	        inss = salario * 0.14; 
                	    }

                	    double valeTransporte = salario * 0.06;

                	    double irrf = 0;
                	    if (salario <= 1903.98) {
                	        irrf = 0; 
                	    } else if (salario <= 2826.65) {
                	        irrf = salario * 0.075 - 142.80;
                	    } else if (salario <= 3751.05) {
                	        irrf = salario * 0.15 - 354.80;
                	    } else if (salario <= 4664.68) {
                	        irrf = salario * 0.225 - 636.13;
                	    } else {
                	        irrf = salario * 0.275 - 869.36;
                	    }

                	    double totalDescontos = inss + valeTransporte + irrf;

                	    double salarioLiquido = salario - totalDescontos;

                	    JPanel panel = new JPanel(new BorderLayout());
                	    JTextArea textArea = new JTextArea();
                	    textArea.setText(
                	        String.format(
                	            "==================== CONTRACHEQUE ====================\n" +
                	            "Funcionário: %s\n" +
                	            "------------------------------------------------------\n" +
                	            "Salário Bruto:        R$ %.2f\n" +
                	            "Desconto INSS:        R$ %.2f\n" +
                	            "Vale Transporte:      R$ %.2f\n" +
                	            "IRRF:                 R$ %.2f\n" +
                	            "------------------------------------------------------\n" +
                	            "Total de Descontos:   R$ %.2f\n" +
                	            "------------------------------------------------------\n" +
                	            "Salário Líquido:      R$ %.2f\n" +
                	            "======================================================", 
                	            nome, salario, inss, valeTransporte, irrf, totalDescontos, salarioLiquido
                	        )
                	    );
                	    textArea.setEditable(false);

                	    JScrollPane scrollPane = new JScrollPane(textArea);
                	    panel.add(scrollPane, BorderLayout.CENTER);

                	    JButton imprimirButton = new JButton("Imprimir");
                	    imprimirButton.addActionListener(new ActionListener() {
                        	public void actionPerformed(ActionEvent e) {
                	    
                	        try {
                	            textArea.print();  
                	        } catch (PrinterException ex) {
                	            JOptionPane.showMessageDialog(null, "Erro ao tentar imprimir: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                	        }
                        	}});

                	    panel.add(imprimirButton, BorderLayout.SOUTH);

                	    JOptionPane.showMessageDialog(null, panel, "Folha Salarial", JOptionPane.INFORMATION_MESSAGE);

                	} catch (NumberFormatException e1) {
                	    JOptionPane.showMessageDialog(null, "Erro ao converter o valor do salário.", "Erro", JOptionPane.ERROR_MESSAGE);
                	}
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione um funcionário para gerar a folha salarial.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
        	}
        });
        btnFolha.setBounds(576, 442, 100, 30);
        getContentPane().add(btnFolha);

        revalidate();
        repaint();
    }

    
    private void mostrarFuncionariosPertoAposentadoria() {
        Conexao conexao = new Conexao();
        List<Funcionario> funcionariosPertoAposentadoria = conexao.Aposentadoria(); 

        String[] colunas = {"ID", "Nome", "Idade", "Cargo", "Salário", "Anos para Aposentadoria"};
        String[][] dados = new String[funcionariosPertoAposentadoria.size()][7];

        for (int i = 0; i < funcionariosPertoAposentadoria.size(); i++) {
            Funcionario func = funcionariosPertoAposentadoria.get(i);
            dados[i][0] = String.valueOf(func.getIdFun());
            dados[i][1] = func.getNomeFun();
            dados[i][2] = String.valueOf(func.getIdadeFun());
            dados[i][5] = func.getCargoFun();
            dados[i][2] = String.valueOf(func.getIdadeFun());
            dados[i][6] = String.valueOf(65 - func.getIdadeFun()); 
        }

        if (scrollPane != null) {
            getContentPane().remove(scrollPane);
        }

        tabela = new JTable(dados, colunas);
        scrollPane = new JScrollPane(tabela);
        scrollPane.setBounds(0, 36, 684, 395);
        getContentPane().add(scrollPane);

        revalidate();
        repaint();
    }
}
