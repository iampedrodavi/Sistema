package Programa;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.Toolkit;

public class Cadastro extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textUser;
    private JTextField textPass;
    private String tipo = "Normal";
    private Conexao conexao = new Conexao();

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Cadastro frame = new Cadastro();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Cadastro() {
    	setIconImage(Toolkit.getDefaultToolkit().getImage(Cadastro.class.getResource("/Programa/Imagens/recursos-humanos.png")));
        setTitle("Cadastro");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 255, 170);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setLocationRelativeTo(null);

        JLabel lblUser = new JLabel("Nome: ");
        lblUser.setFont(new Font("Times New Roman", Font.BOLD, 12));
        lblUser.setBounds(20, 30, 46, 14);
        contentPane.add(lblUser);

        textUser = new JTextField();
        textUser.setBounds(62, 27, 100, 20);
        contentPane.add(textUser);
        textUser.setColumns(10);

        JLabel lblPass = new JLabel("Senha:");
        lblPass.setFont(new Font("Times New Roman", Font.BOLD, 12));
        lblPass.setBounds(20, 58, 46, 14);
        contentPane.add(lblPass);

        textPass = new JTextField();
        textPass.setBounds(62, 55, 100, 20);
        contentPane.add(textPass);
        textPass.setColumns(10);

        JRadioButton rdbtnAdm = new JRadioButton("Adm");
        rdbtnAdm.setFont(new Font("Times New Roman", Font.BOLD, 12));
        rdbtnAdm.setBounds(168, 26, 59, 23);
        contentPane.add(rdbtnAdm);

        JRadioButton rdbtnNormal = new JRadioButton("Normal");
        rdbtnNormal.setFont(new Font("Times New Roman", Font.BOLD, 12));
        rdbtnNormal.setBounds(168, 54, 75, 23);
        contentPane.add(rdbtnNormal);

        ButtonGroup group = new ButtonGroup();
        group.add(rdbtnAdm);
        group.add(rdbtnNormal);

        rdbtnAdm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tipo = "Adm";
            }
        });

        rdbtnNormal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tipo = "Normal";
            }
        });

        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nome = textUser.getText();
                String senha = textPass.getText();
                
                System.out.println("Nome: " + nome + " | Senha: " + senha + "Tipo: " + tipo); 
          
                if (nome.isEmpty() || senha.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Nome e Senha são obrigatórios!", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    if (conexao.verificarUsuarioExistente(nome)) {
                        JOptionPane.showMessageDialog(null, "Nome de usuário já existe!", "Erro", JOptionPane.ERROR_MESSAGE);
                    } else {
                        User user = new User(0, nome, senha, tipo);
                        conexao.inserirNovoUser(user);
                        JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");
                        new Login().setVisible(true);
                        dispose();
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao cadastrar usuário: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnCadastrar.setFont(new Font("Times New Roman", Font.BOLD, 12));
        btnCadastrar.setBounds(119, 97, 89, 23);
        contentPane.add(btnCadastrar);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setFont(new Font("Times New Roman", Font.BOLD, 12));
        btnVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Login().setVisible(true);
                dispose();
            }
        });
        btnVoltar.setBounds(20, 97, 89, 23);
        contentPane.add(btnVoltar);
    }
}
