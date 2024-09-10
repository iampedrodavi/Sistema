package Programa;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class RemoverAdm extends JFrame {

    private static final long serialVersionUID = 1L;
    private Conexao conexao = new Conexao("jdbc:mysql://localhost:3306/sistema", "root", "290600");
    private JTextField textId;
    private JTextField txtbus;
    private JTable tabela;
    private JScrollPane scrollPane;

    public RemoverAdm() {
    	setTitle("Remover Funcionário");
        setIconImage(Toolkit.getDefaultToolkit().getImage(RemoverAdm.class.getResource("/Programa/Imagens/recursos-humanos.png")));
        setSize(690, 438);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);
        
        JLabel lblDigiteOId = new JLabel("Digite o ID para remover:");
        lblDigiteOId.setBounds(330, 18, 157, 14);
        getContentPane().add(lblDigiteOId);
        
        textId = new JTextField();
        textId.setBounds(497, 10, 176, 30);
        getContentPane().add(textId);
        
        txtbus = new JTextField();
        txtbus.setBounds(10, 5, 200, 30);
        getContentPane().add(txtbus);
        
        JButton btnbus = new JButton("Buscar");
        btnbus.setBounds(220, 5, 100, 30);
        getContentPane().add(btnbus);

        JButton btnRemover = new JButton("Remover");
        btnRemover.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String idStr = textId.getText();
                
                if (idStr.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, insira o ID do funcionário.");
                    return;
                }

                int id;
                try {
                    id = Integer.parseInt(idStr);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "ID inválido. Por favor, insira um número inteiro.");
                    return;
                }

                Funcionario funcionario = conexao.buscarFuncionarioPorId(id);

                if (funcionario != null) {
                    conexao.removerFuncionario(funcionario);
                    JOptionPane.showMessageDialog(null, "Funcionário removido com sucesso!");
                    atualizarTabela("");
                } else {
                    JOptionPane.showMessageDialog(null, "Funcionário não encontrado com o ID: " + id);
                }
            }
        });
        btnRemover.setBounds(555, 361, 109, 35);
        getContentPane().add(btnRemover);

        JButton btnBack = new JButton("Voltar");
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new MenuAdm().setVisible(true);
                dispose();
            }
        });
        btnBack.setBounds(10, 361, 109, 35);
        getContentPane().add(btnBack);

        atualizarTabela("");

        btnbus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String buscar = txtbus.getText();
                atualizarTabela(buscar);
            }
        });
    }

    private void atualizarTabela(String buscar) {
        List<Funcionario> funcionarios = buscar.isEmpty() ? conexao.buscarFuncionarios() : conexao.buscarFuncionariosPorNome(buscar);

        String[] colunas = {"ID", "Nome", "Idade", "CPF", "E-mail", "Cargo"};
        String[][] dados = new String[funcionarios.size()][6];

        for (int i = 0; i < funcionarios.size(); i++) {
            Funcionario func = funcionarios.get(i);
            dados[i][0] = String.valueOf(func.getIdFun());
            dados[i][1] = func.getNomeFun();
            dados[i][2] = String.valueOf(func.getIdadeFun());
            dados[i][3] = func.getCpfFun();
            dados[i][4] = func.getEmailFun();
            dados[i][5] = func.getCargoFun();
        }

        if (scrollPane != null) {
            getContentPane().remove(scrollPane); 
        }

        tabela = new JTable(dados, colunas);
        scrollPane = new JScrollPane(tabela);
        scrollPane.setBounds(10, 46, 663, 304);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        revalidate();
        repaint();
    }
}
