package Programa;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Adicionar extends JFrame {

    private static final long serialVersionUID = 1L;
    private Conexao conexao = new Conexao();

    public Adicionar() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(Adicionar.class.getResource("/Programa/Imagens/recursos-humanos.png")));
        setTitle("Cadastro de Funcionários");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panelInput = new JPanel();
        panelInput.setLayout(new GridLayout(6, 2));

        JLabel label = new JLabel("Nome:");
        label.setFont(new Font("Times New Roman", Font.BOLD, 20));
        panelInput.add(label);
        JTextField txtNome = new JTextField();
        panelInput.add(txtNome);

        JLabel label_1 = new JLabel("Idade:");
        label_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
        panelInput.add(label_1);
        JTextField txtIdade = new JTextField();
        panelInput.add(txtIdade);

        JLabel label_2 = new JLabel("CPF:");
        label_2.setFont(new Font("Times New Roman", Font.BOLD, 20));
        panelInput.add(label_2);
        JTextField txtCpf = new JTextField();
        panelInput.add(txtCpf);

        JLabel label_3 = new JLabel("Email:");
        label_3.setFont(new Font("Times New Roman", Font.BOLD, 20));
        panelInput.add(label_3);
        JTextField txtEmail = new JTextField();
        panelInput.add(txtEmail);

        JLabel label_4 = new JLabel("Cargo:");
        label_4.setFont(new Font("Times New Roman", Font.BOLD, 20));
        panelInput.add(label_4);
        JTextField txtCargo = new JTextField();
        panelInput.add(txtCargo);

        JButton btnNewButton = new JButton("Voltar");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Menu().setVisible(true);
                dispose();
            }
        });
        panelInput.add(btnNewButton);

        JButton btnAdicionar = new JButton("Adicionar");
        panelInput.add(btnAdicionar);

        btnAdicionar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nome = txtNome.getText();
                String idadeStr = txtIdade.getText();
                String cpf = txtCpf.getText();
                String email = txtEmail.getText();
                String cargo = txtCargo.getText();
                int idade = Integer.parseInt(idadeStr);
             
                if (conexao.verificarCpfExistente(cpf)) {
                    JOptionPane.showMessageDialog(null, "CPF já cadastrado!", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (cpf.length() != 11) {
                    JOptionPane.showMessageDialog(null, "CPF inválido. O CPF deve conter 11 dígitos.");
                    return;
                }

                Funcionario funcionario = new Funcionario(0, nome, idade, cpf, email, cargo);
                conexao.inserirNovoFuncionario(funcionario);

                JOptionPane.showMessageDialog(null, "Funcionário cadastrado com sucesso!");
                new Menu().setVisible(true);
                dispose();
            }
        });

        getContentPane().add(panelInput, BorderLayout.CENTER);
    }
}
