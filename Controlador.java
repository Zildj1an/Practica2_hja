import java.util.ArrayList;

public class Controlador {

	private ArrayList<Observador> observadores;
	
	public Controlador() {
		observadores = new ArrayList<Observador>();	
	}

	public void addObservador(Observador ob) {
		observadores.add(ob);
	}

	public void poner(String combo, String tablero) {
		if (tablero == "board") {
			for (Observador ob : observadores)
				ob.onSelectCardBoard(combo);
		} else if (tablero == "hand") {
			for (Observador ob : observadores)
				ob.onSelectCard(combo);
		}
	}
}
