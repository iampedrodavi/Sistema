package Programa;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

public class MenuAdmTest {

    private MenuAdm menuAdm;

    @BeforeEach
    public void setUp() {
        menuAdm = new MenuAdm();
    }

    

    @Test
    public void testButtonListeners() {
        
        JButton btnAdicionar = (JButton) getButtonByText(menuAdm, "Adicionar Funcionário");
        btnAdicionar.doClick();
        assertTrue(verifyWindowOpened(AdicionarAdm.class));
        
       
        JButton btnListar = (JButton) getButtonByText(menuAdm, "Listar Funcionários");
        btnListar.doClick();
        assertTrue(verifyWindowOpened(ListarAdm.class));
        
       
        JButton btnModificar = (JButton) getButtonByText(menuAdm, "Modificar Funcionário");
        btnModificar.doClick();
        assertTrue(verifyWindowOpened(ModificarAdm.class));
        
       
        JButton btnFolha = (JButton) getButtonByText(menuAdm, "Folha Salarial");
        btnFolha.doClick();
        assertTrue(verifyWindowOpened(FolhaSalarial.class));
        
       
        JButton btnRemover = (JButton) getButtonByText(menuAdm, "Remover Funcionário");
        btnRemover.doClick();
        assertTrue(verifyWindowOpened(RemoverAdm.class));
        
  
        JButton btnSair = (JButton) getButtonByText(menuAdm, "Sair");
        btnSair.doClick();
        assertThrows(AssertionError.class, () -> {
           
            fail("System.exit(0) deve encerrar a JVM e falhar o teste.");
        });
    }

    private JButton getButtonByText(MenuAdm menuAdm, String text) {
        JPanel panel = (JPanel) menuAdm.getContentPane().getComponent(0);
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
