package Programa;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.swing.*;
import java.awt.event.ActionListener;

public class MenuTestes {

    private Menu menu;

    @BeforeEach
    public void setUp() {
        // Inicializa o Menu antes de cada teste
        menu = new Menu();
    }

    @Test
    public void testMenuFrameCreation() {
        // Testa se o frame foi criado corretamente
        assertNotNull(menu);
        assertEquals("Sistema RH", menu.getTitle());
        assertEquals(660, menu.getWidth());
        assertEquals(450, menu.getHeight());
        assertFalse(menu.isResizable());
    }

    @Test
    public void testButtonsExist() {
        // Testa se os botões foram adicionados corretamente ao painel
        JPanel panel = (JPanel) menu.getContentPane().getComponent(0); // Pega o primeiro painel
        assertEquals(3, panel.getComponentCount()); // Deve ter 3 botões

        JButton btnAdicionar = (JButton) panel.getComponent(0);
        JButton btnListar = (JButton) panel.getComponent(1);
        JButton btnSair = (JButton) panel.getComponent(2);

        assertEquals("Adicionar Funcionário", btnAdicionar.getText());
        assertEquals("Listar Funcionários", btnListar.getText());
        assertEquals("Sair", btnSair.getText());
    }

    @Test
    public void testButtonActionListeners() {
        // Verifica se os ActionListeners estão presentes nos botões

        JPanel panel = (JPanel) menu.getContentPane().getComponent(0);

        JButton btnAdicionar = (JButton) panel.getComponent(0);
        ActionListener[] adicionarListeners = btnAdicionar.getActionListeners();
        assertEquals(1, adicionarListeners.length);

        JButton btnListar = (JButton) panel.getComponent(1);
        ActionListener[] listarListeners = btnListar.getActionListeners();
        assertEquals(1, listarListeners.length);

        JButton btnSair = (JButton) panel.getComponent(2);
        ActionListener[] sairListeners = btnSair.getActionListeners();
        assertEquals(1, sairListeners.length);
    }
    
    @Test
    public void testLabelsExist() {
        // Verifica se o label de "Bem-Vindo" está presente
        JLabel lblBemVindo = (JLabel) menu.getContentPane().getComponent(1); // O segundo componente é o JLabel
        assertEquals("Bem-Vindo ao Sistema RH", lblBemVindo.getText());
        assertEquals(SwingConstants.CENTER, lblBemVindo.getHorizontalAlignment());
        assertEquals(34, lblBemVindo.getFont().getSize());
    }
}
