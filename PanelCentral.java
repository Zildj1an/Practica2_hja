import javax.swing.plaf.metal.MetalToggleButtonUI;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.lang.StringBuilder;

public class PanelCentral extends JPanel implements Observador {

	private JPanel pnlCartas;
	private JPanel pnlDatos;
	private Controlador control;
	private int num_cards_selected;
	private int max_cards_selected;
	private String[] cards_onBoard;
	private ArrayList<JToggleButton> btns;

	public PanelCentral(Controlador control) {
		this.control = control;
		this.num_cards_selected = 0;
		this.max_cards_selected = 5;
		this.btns = new ArrayList<JToggleButton>();
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(300, 480));	
		setBorder(BorderFactory.createEmptyBorder(2, 15, 0, 0));

		cards_onBoard = new String[5];

		generarPanelCartas();
		generarPanelDatos();

		add(pnlCartas, BorderLayout.WEST);
		add(pnlDatos, BorderLayout.CENTER);

		this.control.addObservador(this);
	}

	private void generarPanelCartas() {
		String suites = "hcds";
		pnlCartas = new JPanel(new GridLayout(13, 4, 4, 4));

		for (int i = 14; i > 1; i--) {
			for (int j = 0; j < 4; j++) {
				JToggleButton jtb = new JToggleButton();
				
				// Parsea la carta
				StringBuilder c = new StringBuilder();
				c.append(valorCarta(i));
				c.append(suites.charAt(j));
				jtb.setText(c.toString());

				// Quita los margenes del boton y ajusta el ancho y alto
				jtb.setMargin(new Insets(0, 0, 0, 0));
				jtb.setPreferredSize(new Dimension(35, 35));

				cambiarColorBoton(jtb, false, suites.charAt(j));

				jtb.setUI(new MetalToggleButtonUI() {
					@Override
					protected Color getSelectColor() {
						Color c = null;
						if (num_cards_selected <= max_cards_selected) {
							c = cambiarColorBoton(jtb, true, jtb.getText().charAt(1));
						}
						return c;
					}
				});

				btns.add(jtb);

				ActionListener actionListener = new ActionListener() {
					public void actionPerformed(ActionEvent actionEvent) {
						JToggleButton jtb1 = (JToggleButton)actionEvent.getSource();
						if ((num_cards_selected < max_cards_selected) && jtb1.isSelected()) {
							num_cards_selected += 1;	
							control.poner(jtb1.getText(), "board");	

							if (num_cards_selected == 5) {
								for (JToggleButton j : btns) {
									if (j.isSelected() == false) 
										j.setEnabled(false);
								}		
							}
						}
						else if (num_cards_selected == 5 && !jtb1.isSelected()) { 
							if (num_cards_selected == 5) {
								int cont = 0;
								for (JToggleButton j : btns) {
									if (!j.isSelected()) {
										j.setEnabled(true);
									}
								}		
							}
							
							num_cards_selected -= 1;
						} else if (num_cards_selected > 0 && !jtb1.isSelected()) {
							num_cards_selected -= 1;
						}
						
						if (!jtb.isSelected()) {
							control.desponer(jtb1.getText(), "board");	
						}

				}};

				jtb.addActionListener(actionListener);
				pnlCartas.add(jtb);
			}
		}		
	}

	private Color cambiarColorBoton(JToggleButton jtb, boolean isSelected, char j) {
		// Asigna color de fondo
		Color c = null;
		if (isSelected) {
			jtb.setForeground(Color.WHITE);
			if (j == 'h')
				c = new Color(223, 0, 0);
			else if (j == 'c')
				c = new Color(17, 128, 0);
			else if (j == 'd')
				c = new Color(0, 110, 212);
			else
				c = new Color(109, 109, 109);
		} else {
			jtb.setForeground(Color.BLACK);
			if (j == 'h')
				jtb.setBackground(new Color(255,197,187));
			else if (j == 'c')
				jtb.setBackground(new Color(91,241,130));
			else if (j == 'd')
				jtb.setBackground(new Color(200,224,255));
			else
				jtb.setBackground(new Color(208,208,208));
		}
		return c;
	}


	private void generarPanelDatos() {
		pnlDatos = new JPanel();
		pnlDatos.setLayout(new BoxLayout(pnlDatos, BoxLayout.Y_AXIS));

		int marginLeft = 20;
		JLabel lbl1  = new JLabel("Straight flush");
		lbl1.setBorder(BorderFactory.createEmptyBorder(0, marginLeft, 0, 0));
		JLabel lbl2 = new JLabel("Four of a Kind");
		lbl2.setBorder(BorderFactory.createEmptyBorder(0, marginLeft, 0, 0));
		JLabel lbl3 = new JLabel("Full house");
		lbl3.setBorder(BorderFactory.createEmptyBorder(0, marginLeft, 0, 0));
		JLabel lbl4  = new JLabel("flush");
		lbl4.setBorder(BorderFactory.createEmptyBorder(0, marginLeft, 0, 0));
		JLabel lbl5  = new JLabel("straight");
		lbl5.setBorder(BorderFactory.createEmptyBorder(0, marginLeft, 0, 0));
		JLabel lbl6  = new JLabel("set");
		lbl6.setBorder(BorderFactory.createEmptyBorder(0, marginLeft, 0, 0));
		JLabel lbl7  = new JLabel("two pair");
		lbl7.setBorder(BorderFactory.createEmptyBorder(0, marginLeft, 0, 0));
		JLabel lbl8  = new JLabel("overpair");
		lbl8.setBorder(BorderFactory.createEmptyBorder(0, marginLeft, 0, 0));
		JLabel lbl9  = new JLabel("top pair");
		lbl9.setBorder(BorderFactory.createEmptyBorder(0, marginLeft, 0, 0));
		JLabel lbl10  = new JLabel("pp below tp");
		lbl10.setBorder(BorderFactory.createEmptyBorder(0, marginLeft, 0, 0));
		JLabel lbl11  = new JLabel("middle pair");
		lbl11.setBorder(BorderFactory.createEmptyBorder(0, marginLeft, 0, 0));
		JLabel lbl12  = new JLabel("weak pair");
		lbl12.setBorder(BorderFactory.createEmptyBorder(0, marginLeft, 0, 0));
		JLabel lbl13 = new JLabel("ace high");
		lbl13.setBorder(BorderFactory.createEmptyBorder(0, marginLeft, 0, 0));
		JLabel lbl14 = new JLabel("no made hand");
		lbl14.setBorder(BorderFactory.createEmptyBorder(0, marginLeft, 0, 0));

		pnlDatos.add(lbl1);
		pnlDatos.add(lbl2);
		pnlDatos.add(lbl3);
		pnlDatos.add(lbl4);
		pnlDatos.add(lbl5);
		pnlDatos.add(lbl6);
		pnlDatos.add(lbl7);
		pnlDatos.add(lbl8);
		pnlDatos.add(lbl9);
		pnlDatos.add(lbl10);
		pnlDatos.add(lbl11);
		pnlDatos.add(lbl12);
		pnlDatos.add(lbl13);
		pnlDatos.add(lbl14);

	}

    private char valorCarta(int num) {
        char letra;

        switch (num) {
            case 10:
                letra = 'T';
                break;
            case 11:
                letra = 'J';
                break;
            case 12:
                letra = 'Q';
                break;
            case 13:
                letra = 'K';
                break;
            case 14:
                letra = 'A';
                break;
			default:
				letra = Integer.toString(num).charAt(0);
        }

        return letra;
    }

	@Override public void onSelectCard(final String combo) {}
	@Override public void onSliderChange(final int value) {}
	@Override public void onSelectCardBoard(final String card) {} 
	@Override public void onDeselectCardBoard(final String card) {}
	@Override public void onDeselectCard(final String card) {}
}

