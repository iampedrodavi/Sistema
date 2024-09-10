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
	private Conexao conexao = new Conexao();

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
		
		JButton btnLog = new JButton("Entrar");
		btnLog.setBounds(190, 7, 89, 23);
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
		contentPane.add(btnLog);
		
		JButton btnSair = new JButton("Sair");
		btnSair.setBounds(190, 87, 89, 23);
		btnSair.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		contentPane.add(btnSair);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setBounds(190, 47, 89, 23);
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Cadastro().setVisible(true);
	       		 dispose();
			}
		});
		btnCadastrar.setFont(new Font("Times New Roman", Font.BOLD, 12));
		contentPane.add(btnCadastrar);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 170, 86);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel txtuser = new JLabel("Usuário: ");
		txtuser.setBounds(0, 0, 85, 43);
		panel.add(txtuser);
		txtuser.setFont(new Font("Times New Roman", Font.BOLD, 12));
		
		boxuser = new JTextField();
		boxuser.setBounds(70, 11, 100, 20);
		panel.add(boxuser);
		boxuser.setColumns(10);
		
		JLabel txtsen = new JLabel("Senha: ");
		txtsen.setBounds(0, 43, 85, 43);
		panel.add(txtsen);
		txtsen.setFont(new Font("Times New Roman", Font.BOLD, 12));
		
		boxpass = new JTextField();
		boxpass.setBounds(70, 54, 100, 20);
		panel.add(boxpass);
		boxpass.setColumns(10);
		
	}
}
