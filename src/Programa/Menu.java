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
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

      
        JPanel panelButtons = new JPanel();
        panelButtons.setBounds(192, 147, 400, 300);

        JButton btnAdicionar = new JButton("Adicionar Funcionário");
        panelButtons.setLayout(new GridLayout(0, 1, 0, 0));

        panelButtons.add(btnAdicionar);
        JButton btnListar = new JButton("Listar Funcionários");
        panelButtons.add(btnListar);
        
        btnListar.addActionListener(new ActionListener() {
                  
                    public void actionPerformed(ActionEvent e) {
                        new Listar().setVisible(true);
                        dispose();
                    }
                });
        
        JButton btnSair = new JButton("Sair");
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
        lblNewLabel.setBounds(192, 51, 400, 70);
        getContentPane().add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\Aluno.EDU\\Downloads\\SistemaRhFinalizado-20240826T213320Z-001\\SistemaRhFinalizado\\recursos.png"));
        lblNewLabel_1.setBounds(10, 11, 172, 150);
        getContentPane().add(lblNewLabel_1);
        
   

       
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

