import java.util.ArrayList;

public class Controlador {

	private CalcularCombos calcularCombos;
	private ArrayList<Observador> observadores;
	
	public Controlador() {
		observadores = new ArrayList<Observador>();	
		calcularCombos = new CalcularCombos();
	}

	public void addObservador(Observador ob) {
		observadores.add(ob);
	}

	public void poner(String card, String tablero) {
		if (tablero == "board") {
			for (Observador ob : observadores)
				ob.onSelectCardBoard(card);
		} else if (tablero == "hand") {
			for (Observador ob : observadores) {
				ob.onAddCardHand(card);
				ob.onShowText(card);
			}
		}
	}

	public void mostrar(String text) {
		for (Observador ob : observadores)
			ob.onSelectCard(text);
	}

	public void desponer(String card, String tablero) {
		if (tablero == "board") {
			for (Observador ob : observadores)
				ob.onDeselectCardBoard(card);
		} else if (tablero == "hand") {
			for (Observador ob : observadores) {
				ob.onShowText(card);
				ob.onDeselectCard(card);
			}
		}
	}

	public void procesar(ArrayList<String> cards) {
		for (Observador ob : observadores)
			ob.onRangeProccess(cards);
	}

	public void clear(boolean hands) {
		for (Observador ob : observadores)
			ob.onClearCards(hands);
	}

	public void updateText(ArrayList<String> cards) {
		for (Observador ob : observadores)
			ob.onRangeProcessShow(cards);
	}

	public void mostrarResultado(CalcularCombos calcularCombos) {
		for (Observador ob : observadores)
			ob.onShowResults(calcularCombos);
	}
}
