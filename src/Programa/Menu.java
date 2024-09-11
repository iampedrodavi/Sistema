package Programa;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame {

    private static final long serialVersionUID = 1L;
 
    public Menu() {
    	setIconImage(Toolkit.getDefaultToolkit().getImage(Menu.class.getResource("/Programa/Imagens/recursos-humanos.png")));
        setTitle("Sistema RH");
        setSize(660, 450);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

      
        JPanel panelButtons = new JPanel();
        panelButtons.setBounds(122, 85, 400, 300);

        JButton btnAdicionar = new JButton("Adicionar Funcionário");
        btnAdicionar.setFont(new Font("Times New Roman", Font.BOLD, 14));
        panelButtons.setLayout(new GridLayout(0, 1, 0, 0));

        panelButtons.add(btnAdicionar);
        JButton btnListar = new JButton("Listar Funcionários");
        btnListar.setFont(new Font("Times New Roman", Font.BOLD, 14));
        panelButtons.add(btnListar);
        
        btnListar.addActionListener(new ActionListener() {
                  
                    public void actionPerformed(ActionEvent e) {
                        new Listar().setVisible(true);
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
        lblNewLabel.setBounds(122, 11, 400, 70);
        getContentPane().add(lblNewLabel);
        
   

       
        btnAdicionar.addActionListener(new ActionListener() {
         
            public void actionPerformed(ActionEvent e) {
                new Adicionar().setVisible(true);
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
        
            public void run() {
                new Menu().setVisible(true);
            }
        });
    }
}

