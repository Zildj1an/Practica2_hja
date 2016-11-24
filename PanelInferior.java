import javax.swing.*;
import java.awt.*;

public class PanelInferior extends JPanel implements Observador {
	private JTextField txtCartasEnMano_orig; 
	private JTextField txtCartasEnMano; 
	private JTextField txtCartasBoard; 
	private Controlador control;

	public PanelInferior(Controlador control) {
		this.control = control;
		txtCartasEnMano_orig = new JTextField(30);
		txtCartasEnMano = new JTextField(30);
		txtCartasBoard = new JTextField(30);
		JButton btnRango = new JButton("Obtener");

		txtCartasBoard.setEditable(false);

		add(txtCartasEnMano_orig);
		add(txtCartasEnMano);
		add(txtCartasBoard);
		add(btnRango);

		this.control.addObservador(this);
	}

	@Override
	public void onSelectCard(final String combo) { 
		txtCartasEnMano_orig.setText(combo);
		txtCartasEnMano.setText(combo);
	}

	@Override
	public void onSelectCardBoard(final String card) { 
		txtCartasBoard.setText(card);
	}

	@Override public void onSliderChange(final int value) {}
}

