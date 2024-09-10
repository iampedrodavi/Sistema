package Programa;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ModificarAdm extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField txtbus;
    private JTable tabela;
    private JScrollPane scrollPane;

    public ModificarAdm() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(ModificarAdm.class.getResource("/Programa/Imagens/recursos-humanos.png")));
        setTitle("Modificar Funcionário");
        setSize(1234, 353);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panelInput = new JPanel();
        panelInput.setBounds(0, 52, 384, 261);
        panelInput.setLayout(new GridLayout(7, 2));

        JLabel label = new JLabel("ID do Funcionário:");
        label.setFont(new Font("Times New Roman", Font.BOLD, 20));
        panelInput.add(label);
        JTextField txtId = new JTextField();
        panelInput.add(txtId);

        JLabel label_1 = new JLabel("Nome:");
        label_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
        panelInput.add(label_1);
        JTextField txtNome = new JTextField();
        panelInput.add(txtNome);

        JLabel label_2 = new JLabel("Idade:");
        label_2.setFont(new Font("Times New Roman", Font.BOLD, 20));
        panelInput.add(label_2);
        JTextField txtIdade = new JTextField();
        panelInput.add(txtIdade);

        JLabel label_3 = new JLabel("CPF:");
        label_3.setFont(new Font("Times New Roman", Font.BOLD, 20));
        panelInput.add(label_3);
        JTextField txtCpf = new JTextField();
        panelInput.add(txtCpf);

        JLabel label_4 = new JLabel("Email:");
        label_4.setFont(new Font("Times New Roman", Font.BOLD, 20));
        panelInput.add(label_4);
        JTextField txtEmail = new JTextField();
        panelInput.add(txtEmail);

        JLabel label_5 = new JLabel("Cargo:");
        label_5.setFont(new Font("Times New Roman", Font.BOLD, 20));
        panelInput.add(label_5);
        JTextField txtCargo = new JTextField();
        panelInput.add(txtCargo);
        getContentPane().setLayout(null);
       
        txtbus = new JTextField();
        txtbus.setBounds(10, 11, 200, 30);
        getContentPane().add(txtbus);
       
        JButton btnbus = new JButton("Buscar");
        btnbus.setBounds(241, 11, 100, 30);
        getContentPane().add(btnbus);

       
        JButton btnModificar = new JButton("Modificar");
        panelInput.add(btnModificar);

        getContentPane().add(panelInput);
        
        JButton btnBack = new JButton("Voltar");
        panelInput.add(btnBack);
                btnBack.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        new MenuAdm().setVisible(true);
                        dispose();
                    }
                });

       
        atualizarTabela("");

     
        btnbus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String buscar = txtbus.getText();
                atualizarTabela(buscar);
            }
        });

       
        btnModificar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String idStr = txtId.getText();
                
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

                Conexao conexao = new Conexao();
                Funcionario funcionario = conexao.buscarFuncionarioPorId(id);

                if (funcionario != null) {
                    String nome = txtNome.getText();
                    if (!nome.isEmpty()) {
                        funcionario.setNomeFun(nome);
                    }

                    String idadeStr = txtIdade.getText();
                    if (!idadeStr.isEmpty()) {
                        funcionario.setIdadeFun(Integer.parseInt(idadeStr));
                    }

                    String cpf = txtCpf.getText();
                    if (!cpf.isEmpty()) {
                        if (cpf.length() == 11) {
                            funcionario.setCpfFun(cpf);
                        } else {
                            JOptionPane.showMessageDialog(null, "CPF inválido. O CPF deve conter 11 dígitos.");
                            return;
                        }
                    }

                    String email = txtEmail.getText();
                    if (!email.isEmpty()) {
                        funcionario.setEmailFun(email);
                    }

                    String cargo = txtCargo.getText();
                    if (!cargo.isEmpty()) {
                        funcionario.setCargoFun(cargo);
                    }

                    conexao.modificarFuncionario(funcionario);
                    JOptionPane.showMessageDialog(null, "Funcionário atualizado com sucesso!");
                    new MenuAdm().setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Funcionário não encontrado com o ID: " + id);
                }
            }
        });
    }

    private void atualizarTabela(String buscar) {
        Conexao conexao = new Conexao();
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
        tabela.setDefaultEditor(Object.class, null);
        scrollPane = new JScrollPane(tabela);
        scrollPane.setBounds(384, 0, 834, 427);
        getContentPane().add(scrollPane);

        revalidate();
        repaint();
    }
}
