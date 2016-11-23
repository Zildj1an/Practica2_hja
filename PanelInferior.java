import javax.swing.*;
import java.awt.*;

public class PanelInferior extends JPanel {

	public PanelInferior() {
		JTextField txtRango_orig = new JTextField(45);
		JTextField txtRango = new JTextField(45);
		JButton btnRango = new JButton("Obtener");
		add(txtRango_orig);
		add(txtRango);
		add(btnRango);
	}
}

