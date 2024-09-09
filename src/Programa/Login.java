package Programa;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField boxuser;
	private JTextField boxpass;
	private Conexao conexao = new Conexao("jdbc:mysql://localhost:3306/sistema", "root", "290600");

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public Login() {
		setTitle("LOGIN - RH");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/Programa/Imagens/recursos-humanos.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 305, 157);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel txtuser = new JLabel("Usuário: ");
		txtuser.setFont(new Font("Times New Roman", Font.BOLD, 12));
		txtuser.setBounds(10, 11, 56, 14);
		contentPane.add(txtuser);
		
		JLabel txtsen = new JLabel("Senha: ");
		txtsen.setFont(new Font("Times New Roman", Font.BOLD, 12));
		txtsen.setBounds(10, 52, 56, 14);
		contentPane.add(txtsen);
		
		boxuser = new JTextField();
		boxuser.setBounds(60, 8, 86, 20);
		contentPane.add(boxuser);
		boxuser.setColumns(10);
		
		boxpass = new JTextField();
		boxpass.setBounds(60, 49, 86, 20);
		contentPane.add(boxpass);
		boxpass.setColumns(10);
		
		JButton btnLog = new JButton("Entrar");
		btnLog.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nome = boxuser.getText();
		        String senha = boxpass.getText();

		        if (nome.isEmpty() || senha.isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Nome de usuário e senha são obrigatórios!", "Erro", JOptionPane.ERROR_MESSAGE);
		            return;
		        }

		        User user = conexao.buscarUser(nome, senha);

		        if (user != null) {
		            if ("Adm".equals(user.getTipo())) {
		                new MenuAdm().setVisible(true); 
		            } else {
		                new Menu().setVisible(true); 
		            }
		            dispose();
		        } else {
		            JOptionPane.showMessageDialog(null, "Nome de usuário ou senha inválidos!", "Erro", JOptionPane.ERROR_MESSAGE);
		        }
				
			}
		});
		btnLog.setBounds(190, 7, 89, 23);
		contentPane.add(btnLog);
		
		JButton btnSair = new JButton("Sair");
		btnSair.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSair.setBounds(190, 87, 89, 23);
		contentPane.add(btnSair);
		
		JButton btnNewButton = new JButton("Cadastrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Cadastro().setVisible(true);
	       		 dispose();
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnNewButton.setBounds(190, 47, 89, 23);
		contentPane.add(btnNewButton);
	}
}
