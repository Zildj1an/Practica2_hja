import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;

public class PanelDerecho extends JPanel implements Observador {

	JPanel pnlDatos;
	private JLabel lblTexto1;
	private JLabel lblTexto2;
	private JLabel lblTexto3;
	private JLabel lblTexto4;
	private JLabel lblTexto5;
	private JLabel lblTexto6;
	private JLabel lblTexto7;
	private Controlador control;
	private CalcularCombos combos;

	private JLabel lblTxt; 
	private JLabel lblTxt1; 
	private JLabel lblTxt2; 
	private JLabel lblTxt3; 
	private JLabel lblTxt4; 
	private JLabel lblTxt5; 
	private JLabel lblTxt6; 
	private JLabel lblTxt7; 
	private JLabel lblTxt8; 
	private JLabel lblTxt9; 
	private JLabel lblTxt10; 
	private JLabel lblTxt11; 
	private JLabel lblTxt12; 
	private JLabel lblTxt13; 

	private ArrayList<JLabel> lbls;

	public PanelDerecho(Controlador control) {
		this.control = control;
		this.lbls = new ArrayList<JLabel>();
		int marginLeft = 20, marginRight = 15, marginTop = 2;
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setPreferredSize(new Dimension(420, 480));	
		setAlignmentX(Component.LEFT_ALIGNMENT);
		//this.setBorder(BorderFactory.createLineBorder(Color.black));

		JLabel titulo = new JLabel("STATICS");
		titulo.setFont(new Font("Serif", Font.BOLD, 16));
		titulo.setForeground(Color.BLUE);
		add(titulo);

		lblTxt   = new JLabel(); //">= str. flush"); // Escalera de color 
		lblTxt1  = new JLabel(); //"flush");  // Color
		lblTxt2  = new JLabel(); //"straight"); // Escalera
		lblTxt3  = new JLabel(); //"set"); 
		lblTxt4  = new JLabel(); //"two pair"); 
		lblTxt5  = new JLabel(); //"overpair"); 
		lblTxt6  = new JLabel(); //"top pair");  // Pareja
		lblTxt7  = new JLabel(); //"pp below tp");  // Pareja
		lblTxt8  = new JLabel(); //"middle pair"); // Pareja
		lblTxt9  = new JLabel(); //"weak pair"); // Pareja
		lblTxt10 = new JLabel(); //"ace high");	// Carta alta 
		lblTxt11 = new JLabel(); //"no made hand");  // No made hand
		lblTxt12 = new JLabel(); //"no made hand");  // No made hand
		lblTxt13 = new JLabel(); //"no made hand");  // No made hand

		lbls.add(lblTxt);
		lbls.add(lblTxt1);
		lbls.add(lblTxt2);
		lbls.add(lblTxt3);
		lbls.add(lblTxt4);
		lbls.add(lblTxt5);
		lbls.add(lblTxt6);
		lbls.add(lblTxt7);
		lbls.add(lblTxt8);
		lbls.add(lblTxt9);
		lbls.add(lblTxt10);
		lbls.add(lblTxt11);
		lbls.add(lblTxt12);
		lbls.add(lblTxt13);

		for (int i = 0; i < lbls.size(); i++) {
			add(lbls.get(i));
			lbls.get(i).setBorder(BorderFactory.createEmptyBorder(0, marginLeft, 0, marginRight));
		}

		this.control.addObservador(this);
	}

	@Override public void onShowResults(CalcularCombos calcularCombos) {
		int marginLeft = 20, marginRight = 15, marginTop = 2;

		// ESCALERACOLOR
		ArrayList<String> escaleracolor = calcularCombos.getEscaleraColor();
		int n_escCol = calcularCombos.getEscaleraColorContador();
		StringBuilder sb9 = new StringBuilder();
		sb9.append("Straight flush: ");
		sb9.append(n_escCol);
		sb9.append(" ");
		for (int i = 0; i < escaleracolor.size(); i++) {
			sb9.append(escaleracolor.get(i));
			sb9.append(" ");
		}
		if (n_escCol > 0)
			lblTxt.setText(sb9.toString());
		//----------------------------------------------------------------------

		// POKER
		ArrayList<String> poker = calcularCombos.getPoker();
		int n_poker = calcularCombos.getPokerContador();
		StringBuilder sb8 = new StringBuilder();
		sb8.append("Four of a kind: ");
		sb8.append(n_poker);
		sb8.append(" ");
		for (int i = 0; i < poker.size(); i++) {
			sb8.append(poker.get(i));
			sb8.append(" ");
		}
		if (n_poker > 0)
			lblTxt1.setText(sb8.toString());
		//----------------------------------------------------------------------

		// FULL HOUSE
		ArrayList<String> full = calcularCombos.getFullHouse();
		int n_full = calcularCombos.getFullHouseContador();
		StringBuilder sb7 = new StringBuilder();
		sb7.append("Full house: ");
		sb7.append(n_full);
		sb7.append(" ");
		for (int i = 0; i < full.size(); i++) {
			sb7.append(full.get(i));
			sb7.append(" ");
		}
		if (n_full > 0)
			lblTxt2.setText(sb7.toString());
		//----------------------------------------------------------------------

		// COLOR
		ArrayList<String> color = calcularCombos.getColor();
		int n_col = calcularCombos.getColorContador();
		StringBuilder sb6 = new StringBuilder();
		sb6.append("Flush: ");
		sb6.append(n_col);
		sb6.append(" ");
		for (int i = 0; i < color.size(); i++) {
			sb6.append(color.get(i));
			sb6.append(" ");
		}
		if (n_col > 0)
			lblTxt3.setText(sb6.toString());
		//----------------------------------------------------------------------

		// ESCALERA
		ArrayList<String> escalera = calcularCombos.getEscalera();
		int n_esc = calcularCombos.getEscaleraContador();
		StringBuilder sb5 = new StringBuilder();
		sb5.append("Straight: ");
		sb5.append(n_esc);
		sb5.append(" ");
		for (int i = 0; i < escalera.size(); i++) {
			sb5.append(escalera.get(i));
			sb5.append(" ");
		}
		if (n_esc > 0)
			lblTxt4.setText(sb5.toString());
		//----------------------------------------------------------------------

		// TRIO
		ArrayList<String> trio = calcularCombos.getTrio();
		int n_trio = calcularCombos.getTrioContador();
		StringBuilder sb4 = new StringBuilder();
		sb4.append("3 of a kind: ");
		sb4.append(n_trio);
		sb4.append(" ");
		for (int i = 0; i < trio.size(); i++) {
			sb4.append(trio.get(i));
			sb4.append(" ");
		}
		if (n_trio > 0)
			lblTxt5.setText(sb4.toString());
		//----------------------------------------------------------------------

		// PAREJA OVERPAIR
		ArrayList<String> overpair = calcularCombos.getOverPair();
		int n_opair = calcularCombos.getOverPairContador();
		StringBuilder sb2 = new StringBuilder();
		sb2.append("Overpair: ");
		sb2.append(n_opair);
		sb2.append(" ");
		for (int i = 0; i < overpair.size(); i++) {
			sb2.append(overpair.get(i));
			sb2.append(" ");
		}
		if (n_opair > 0)
			lblTxt6.setText(sb2.toString());
		//----------------------------------------------------------------------

		// PAREJA TOPPAIR 
		ArrayList<String> toppair = calcularCombos.getTopPair();
		int n_tpair = calcularCombos.getTopPairContador();
		StringBuilder sb10 = new StringBuilder();
		sb10.append("Toppair: ");
		sb10.append(n_tpair);
		sb10.append(" ");
		for (int i = 0; i < toppair.size(); i++) {
			sb10.append(toppair.get(i));
			sb10.append(" ");
		}
		if (n_tpair > 0)
			lblTxt7.setText(sb10.toString());
		//----------------------------------------------------------------------

		// PAREJA POCKETPAIR
		ArrayList<String> pocketpair = calcularCombos.getPocketPair();
		int n_ppair = calcularCombos.getPocketPairContador();
		StringBuilder sb11 = new StringBuilder();
		sb11.append("Pocket pair: ");
		sb11.append(n_ppair);
		sb11.append(" ");
		for (int i = 0; i < pocketpair.size(); i++) {
			sb11.append(pocketpair.get(i));
			sb11.append(" ");
		}
		if (n_ppair > 0)
			lblTxt8.setText(sb11.toString());
		//----------------------------------------------------------------------

		// DOBLES PAREJAS
		ArrayList<String> doblepareja = calcularCombos.getDoblePareja();
		int n_dpair = calcularCombos.getDobleParejaContador();
		StringBuilder sb3 = new StringBuilder();
		sb3.append("Middle pair: ");
		sb3.append(n_dpair);
		sb3.append(" ");
		for (int i = 0; i < doblepareja.size(); i++) {
			sb3.append(doblepareja.get(i));
			sb3.append(" ");
		}
		if (n_dpair > 0)
			lblTxt9.setText(sb3.toString());
		//----------------------------------------------------------------------

		// PAREJA WEAKPAIR
		ArrayList<String> weakpair = calcularCombos.getWeakPair();
		int n_wpair = calcularCombos.getWeakPairContador();
		StringBuilder sb13 = new StringBuilder();
		sb13.append("Weak pair: ");
		sb13.append(n_wpair);
		sb13.append(" ");
		for (int i = 0; i < weakpair.size(); i++) {
			sb13.append(weakpair.get(i));
			sb13.append(" ");
		}
		if (n_wpair > 0)
			lblTxt10.setText(sb13.toString());
		//----------------------------------------------------------------------

		// CARTA ALTA
		int n_calta = calcularCombos.getCartaAltaContador();
		StringBuilder sb1 = new StringBuilder();
		sb1.append("Ace high: ");
		sb1.append(n_calta);
		sb1.append(" ");
		ArrayList<String> cartaAlta = calcularCombos.getCartaAlta();
		for (int i = 0; i < cartaAlta.size(); i++) {
			sb1.append(cartaAlta.get(i));
			sb1.append(" ");
		}
		if (n_calta > 0)
			lblTxt11.setText(sb1.toString());
		//----------------------------------------------------------------------

		// NOMADEHAND
		int n_nmh = calcularCombos.getNoMadeHandContador();
		StringBuilder sb = new StringBuilder();
		sb.append("No made hand: ");
		sb.append(n_nmh);
		ArrayList<String> noMadeHand = calcularCombos.getNoMadeHand();
		for (int i = 0; i < noMadeHand.size(); i++) {
			sb.append(noMadeHand.get(i));
			sb.append(" ");
		}
		if (n_nmh > 0)
			lblTxt12.setText(sb.toString());
		//----------------------------------------------------------------------

	}
	@Override public void onRangeProcessShow(final ArrayList<String> cards) {}
	@Override public void onAddCardHand(final String card) {}
	@Override public void onShowText(final String card) {}
	@Override public void onSliderChange(int value) {}
	@Override public void onSelectCard(final String text) {}
	@Override public void onSelectCardBoard(final String card) {} 
	@Override public void onDeselectCardBoard(final String card) {}
	@Override public void onDeselectCard(final String card) {}
	@Override public void onRangeProccess(final ArrayList<String> cards) {}
	@Override public void onClearCards(boolean hands) {
		for (int i = 0; i < lbls.size(); i++) 
			lbls.get(i).setText("");
	}
}

