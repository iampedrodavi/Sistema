package Programa;

import javax.swing.*;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class ListarAdm extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField txtSearch;
    private JTable tabela;
    private JScrollPane scrollPane;
    
    public ListarAdm() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(ListarAdm.class.getResource("/Programa/Imagens/recursos-humanos.png")));
        setTitle("Listar Funcionários");
        setSize(700, 500);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);
        
     
        txtSearch = new JTextField();
        txtSearch.setBounds(10, 400, 200, 30);
        getContentPane().add(txtSearch);
        
     
        JButton btnSearch = new JButton("Buscar");
        btnSearch.setBounds(220, 400, 100, 30);
        getContentPane().add(btnSearch);
        

        JButton btnBack = new JButton("Voltar");
        btnBack.setBounds(330, 400, 150, 30);
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

        setVisible(true);
    }

    private void atualizarTabela(String buscar) {
        Conexao conexao = new Conexao("jdbc:mysql://localhost:3306/sistema", "root", "290600");
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
        scrollPane.setBounds(0, 0, 684, 395);
        getContentPane().add(scrollPane);
        
        revalidate();
        repaint();
    }
}
