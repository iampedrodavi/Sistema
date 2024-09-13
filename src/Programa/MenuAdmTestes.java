package Programa;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.swing.*;
import java.awt.event.ActionListener;

public class MenuAdmTestes {

    private MenuAdm menuAdm;

    @BeforeEach
    public void setUp() {
        // Inicializa o MenuAdm antes de cada teste
        menuAdm = new MenuAdm();
    }

    @Test
    public void testMenuAdmFrameCreation() {
        // Testa se o frame foi criado corretamente
        assertNotNull(menuAdm);
        assertEquals("Sistema RH", menuAdm.getTitle());
        assertEquals(600, menuAdm.getWidth());
        assertEquals(450, menuAdm.getHeight());
        assertFalse(menuAdm.isResizable());
    }

    @Test
    public void testButtonsExist() {
        // Testa se os botões foram adicionados corretamente ao painel
        JPanel panel = (JPanel) menuAdm.getContentPane().getComponent(0); // Pega o primeiro painel
        assertEquals(6, panel.getComponentCount()); // Deve ter 6 botões

        JButton btnAdicionar = (JButton) panel.getComponent(0);
        JButton btnListar = (JButton) panel.getComponent(1);
        JButton btnModificar = (JButton) panel.getComponent(2);
        JButton btnFolhaSalarial = (JButton) panel.getComponent(3);
        JButton btnRemover = (JButton) panel.getComponent(4);
        JButton btnSair = (JButton) panel.getComponent(5);

        assertEquals("Adicionar Funcionário", btnAdicionar.getText());
        assertEquals("Listar Funcionários", btnListar.getText());
        assertEquals("Modificar Funcionário", btnModificar.getText());
        assertEquals("Folha Salarial", btnFolhaSalarial.getText());
        assertEquals("Remover Funcionário", btnRemover.getText());
        assertEquals("Sair", btnSair.getText());
    }

    @Test
    public void testButtonActionListeners() {
        // Verifica se os ActionListeners estão presentes nos botões

        JPanel panel = (JPanel) menuAdm.getContentPane().getComponent(0);

        JButton btnAdicionar = (JButton) panel.getComponent(0);
        ActionListener[] adicionarListeners = btnAdicionar.getActionListeners();
        assertEquals(1, adicionarListeners.length);

        JButton btnListar = (JButton) panel.getComponent(1);
        ActionListener[] listarListeners = btnListar.getActionListeners();
        assertEquals(1, listarListeners.length);

        JButton btnModificar = (JButton) panel.getComponent(2);
        ActionListener[] modificarListeners = btnModificar.getActionListeners();
        assertEquals(1, modificarListeners.length);

        JButton btnFolhaSalarial = (JButton) panel.getComponent(3);
        ActionListener[] folhaListeners = btnFolhaSalarial.getActionListeners();
        assertEquals(1, folhaListeners.length);

        JButton btnRemover = (JButton) panel.getComponent(4);
        ActionListener[] removerListeners = btnRemover.getActionListeners();
        assertEquals(1, removerListeners.length);

        JButton btnSair = (JButton) panel.getComponent(5);
        ActionListener[] sairListeners = btnSair.getActionListeners();
        assertEquals(1, sairListeners.length);
    }

    

    @Test
    public void testLabelsExist() {
        // Verifica se o label de "Bem-Vindo" está presente
        JLabel lblBemVindo = (JLabel) menuAdm.getContentPane().getComponent(1); // O segundo componente é o JLabel
        assertEquals("Bem-Vindo ao Sistema RH", lblBemVindo.getText());
        assertEquals(SwingConstants.CENTER, lblBemVindo.getHorizontalAlignment());
        assertEquals(34, lblBemVindo.getFont().getSize());
    }
}
