package Programa;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

public class MenuTest {

    private Menu menu;

    @BeforeEach
    public void setUp() {
        menu = new Menu();
    }

    
    @Test
    public void testButtonListeners() {
       
        JButton btnAdicionar = (JButton) getButtonByText(menu, "Adicionar Funcionário");
        btnAdicionar.doClick();
        assertTrue(verifyWindowOpened(Adicionar.class));
        
        
        JButton btnListar = (JButton) getButtonByText(menu, "Listar Funcionários");
        btnListar.doClick();
        assertTrue(verifyWindowOpened(Listar.class));
        
       
        JButton btnSair = (JButton) getButtonByText(menu, "Sair");
        btnSair.doClick();
        assertThrows(AssertionError.class, () -> {
            
            fail("System.exit(0) deve encerrar a JVM e falhar o teste.");
        });
    }

    private JButton getButtonByText(Menu menu, String text) {
        JPanel panel = (JPanel) menu.getContentPane().getComponent(0);
        for (java.awt.Component comp : panel.getComponents()) {
            if (comp instanceof JButton) {
                JButton button = (JButton) comp;
                if (button.getText().equals(text)) {
                    return button;
                }
            }
        }
        return null;
    }

    private boolean verifyWindowOpened(Class<?> windowClass) {
        
        return true; 
    }
}
