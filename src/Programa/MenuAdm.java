package Programa;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuAdm extends JFrame {

	private static final long serialVersionUID = 1L;

	public MenuAdm() {
    	setIconImage(Toolkit.getDefaultToolkit().getImage(MenuAdm.class.getResource("/Programa/Imagens/recursos-humanos.png")));
        setTitle("Sistema RH");
        setSize(600, 450);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

      
        JPanel panelButtons = new JPanel();
        panelButtons.setBounds(92, 92, 400, 300);

        JButton btnAdicionar = new JButton("Adicionar Funcionário");
        btnAdicionar.setFont(new Font("Times New Roman", Font.BOLD, 14));
        panelButtons.setLayout(new GridLayout(0, 1, 0, 0));

        panelButtons.add(btnAdicionar);
        JButton btnListar = new JButton("Listar Funcionários");
        btnListar.setFont(new Font("Times New Roman", Font.BOLD, 14));
        panelButtons.add(btnListar);
        
        btnListar.addActionListener(new ActionListener() {
                  
                    public void actionPerformed(ActionEvent e) {
                        new ListarAdm().setVisible(true);
                        dispose();
                    }
                });
        JButton btnModificar = new JButton("Modificar Funcionário");
        btnModificar.setFont(new Font("Times New Roman", Font.BOLD, 14));
        panelButtons.add(btnModificar);
        
                btnModificar.addActionListener(new ActionListener() {
                 
                    public void actionPerformed(ActionEvent e) {
                        new ModificarAdm().setVisible(true);
                        dispose();
                    }
                });
        
        JButton btnNewButton = new JButton("Folha Salarial");
        btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		new FolhaSalarial().setVisible(true);
                dispose();
        	}
        });
        panelButtons.add(btnNewButton);
        JButton btnRemover = new JButton("Remover Funcionário");
        btnRemover.setFont(new Font("Times New Roman", Font.BOLD, 14));
        panelButtons.add(btnRemover);
        
                btnRemover.addActionListener(new ActionListener() {
                
                    public void actionPerformed(ActionEvent e) {
                        new RemoverAdm().setVisible(true);
                        dispose();
                    }
                });
        
        JButton btnSair = new JButton("Sair");
        btnSair.setFont(new Font("Times New Roman", Font.BOLD, 14));
        btnSair.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		System.exit(0);
        	}
        });
        getContentPane().setLayout(null);
        panelButtons.add(btnSair);

        getContentPane().add(panelButtons);
        
        JLabel lblNewLabel = new JLabel("Bem-Vindo ao Sistema RH");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 34));
        lblNewLabel.setBounds(92, 11, 400, 70);
        getContentPane().add(lblNewLabel);
        
   

       
        btnAdicionar.addActionListener(new ActionListener() {
         
            public void actionPerformed(ActionEvent e) {
                new AdicionarAdm().setVisible(true);
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
        
            public void run() {
                new MenuAdm().setVisible(true);
            }
        });
    }
}
