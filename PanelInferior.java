import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;

public class PanelInferior extends JPanel implements Observador {
	private JTextField txtCartasEnMano_orig; 
	private JTextField txtCartasEnMano; 
	private JTextField txtCartasBoard; 
	private Controlador control;
	private ArrayList<String> cards_onBoard;

	public PanelInferior(Controlador control) {
		this.control = control;
		this.cards_onBoard = new ArrayList<String>();
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

	public String getCardsOnBoard() {
		String cards = "";

		for (int i = 0; i < cards_onBoard.size(); i++) 
			cards = cards_onBoard.get(i);

		return cards;
	}

	private void borrarTextOnBoard() {
		String cards_text = "";
		for (int i = 0; i < cards_onBoard.size(); i++) {
			if (i == 0)
				cards_text = cards_onBoard.get(i);	
			else
				cards_text += ", " + cards_onBoard.get(i);
		}
		txtCartasBoard.setText(cards_text);
	}

	private void mostrarTextOnBoard() {
		String cards_text = "";
		for (int i = 0; i < cards_onBoard.size(); i++) {
			if (i == 0)
				cards_text = cards_onBoard.get(i);	
			else
				cards_text += ", " + cards_onBoard.get(i);

			txtCartasBoard.setText(cards_text);
		}
	}
	@Override
	public void onSelectCard(final String combo) { 
		txtCartasEnMano_orig.setText(combo);
		txtCartasEnMano.setText(combo);
	}

	@Override
	public void onSelectCardBoard(final String card) { 
		if (cards_onBoard.size() < 5) {
			cards_onBoard.add(card);
			mostrarTextOnBoard();
		}
	}

	@Override public void onDeselectCard(final String card) {}
	@Override public void onSliderChange(final int value) {}
	@Override public void onDeselectCardBoard(final String card) {
		cards_onBoard.remove(card);	
		borrarTextOnBoard();
	}
}

