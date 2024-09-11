package Programa;

import javax.swing.*;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;


public class FolhaSalarial extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField txtSearch;
    private JTable tabela;
    private JScrollPane scrollPane;

    public FolhaSalarial() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(FolhaSalarial.class.getResource("/Programa/Imagens/recursos-humanos.png")));
        setTitle("Lista de Funcionários");
        setSize(702, 558);
        setResizable(false);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);

       
        txtSearch = new JTextField();
        txtSearch.setBounds(136, 400, 200, 30);
        getContentPane().add(txtSearch);

       
        JButton btnSearch = new JButton("Buscar");
        btnSearch.setBounds(346, 400, 100, 30);
        getContentPane().add(btnSearch);

       
        JButton btnAposentadoria = new JButton("Aposentadoria");
        btnAposentadoria.setToolTipText("Para resetar a tabela, clicar novamente no botão BUSCAR!");
        btnAposentadoria.setBounds(456, 400, 121, 30);
        getContentPane().add(btnAposentadoria);

       
        JButton btnBack = new JButton("Voltar");
        btnBack.setBounds(10, 478, 100, 30);
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
        scrollPane.setBounds(0, 0, 684, 395);
        getContentPane().add(scrollPane);
        
        JLabel lblNewLabel = new JLabel("Nome \r\ndo Funcionário:");
        lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
        lblNewLabel.setBounds(10, 400, 136, 30);
        getContentPane().add(lblNewLabel);
        
        JButton btnFolha = new JButton("Gerar Folha");
        btnFolha.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int linha = tabela.getSelectedRow(); 
                
                if (linha != -1) {
                    
                    String nome = tabela.getValueAt(linha, 1).toString();
                    String salarioStr = tabela.getValueAt(linha, 4).toString();
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
  
                    double totalDescontos = inss  + valeTransporte;
             
                    double salarioLiquido = salario - totalDescontos;

                    String mensagem = String.format(
                        "Funcionário: %s\n" +
                        "Salário Bruto: R$ %.2f\n" +
                        "Desconto INSS: R$ %.2f\n" +
                        "Vale Transporte: R$ %.2f\n" +
                        "Total de Descontos: R$ %.2f\n" +
                        "Salário Líquido: R$ %.2f", 
                        nome, salario, inss, valeTransporte, totalDescontos, salarioLiquido
                    );
                    
                    JOptionPane.showMessageDialog(null, mensagem, "Folha Salarial", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione um funcionário para gerar a folha salarial.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
        	}
        });
        btnFolha.setBounds(587, 400, 89, 30);
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
        scrollPane.setBounds(0, 0, 684, 395);
        getContentPane().add(scrollPane);

        revalidate();
        repaint();
    }
}
