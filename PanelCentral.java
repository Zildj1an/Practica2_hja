import javax.swing.plaf.metal.MetalToggleButtonUI;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.event.*;
import javax.swing.*;
import java.awt.*;
import java.lang.StringBuilder;

public class PanelCentral extends JPanel {

	private JPanel pnlCartas;
	private JPanel pnlDatos;

	public PanelCentral(JFrame frame) {
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(300, 480));	
		setBorder(BorderFactory.createEmptyBorder(2, 15, 0, 0));

		generarPanelCartas();
		generarPanelDatos();

		add(pnlCartas, BorderLayout.WEST);
		add(pnlDatos, BorderLayout.CENTER);

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
						return cambiarColorBoton(jtb, true, jtb.getText().charAt(1));
					}
				});

				//ItemListener itemListener = new ItemListener() {
					//public void itemStateChanged(ItemEvent ev) {
						//JToggleButton jtb1 = (JToggleButton)ev.getSource();
						//if(ev.getStateChange() == ItemEvent.SELECTED)
							//cambiarColorBoton(jtb1, true, jtb1.getText());
						//else if(ev.getStateChange() == ItemEvent.DESELECTED)
							//cambiarColorBoton(jtb1, false, jtb1.getText());
				//}};	

				//jtb.addItemListener(itemListener);
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
		JLabel lbl   = new JLabel(">=str. flush");
		lbl.setBorder(BorderFactory.createEmptyBorder(5, marginLeft, 0, 0));
		JLabel lbl1  = new JLabel("flush");
		lbl1.setBorder(BorderFactory.createEmptyBorder(0, marginLeft, 0, 0));
		JLabel lbl2  = new JLabel("straight");
		lbl2.setBorder(BorderFactory.createEmptyBorder(0, marginLeft, 0, 0));
		JLabel lbl3  = new JLabel("set");
		lbl3.setBorder(BorderFactory.createEmptyBorder(0, marginLeft, 0, 0));
		JLabel lbl4  = new JLabel("two pair");
		lbl4.setBorder(BorderFactory.createEmptyBorder(0, marginLeft, 0, 0));
		JLabel lbl5  = new JLabel("overpair");
		lbl5.setBorder(BorderFactory.createEmptyBorder(0, marginLeft, 0, 0));
		JLabel lbl6  = new JLabel("top pair");
		lbl6.setBorder(BorderFactory.createEmptyBorder(0, marginLeft, 0, 0));
		JLabel lbl7  = new JLabel("pp below tp");
		lbl7.setBorder(BorderFactory.createEmptyBorder(0, marginLeft, 0, 0));
		JLabel lbl8  = new JLabel("middle pair");
		lbl8.setBorder(BorderFactory.createEmptyBorder(0, marginLeft, 0, 0));
		JLabel lbl9  = new JLabel("weak pair");
		lbl9.setBorder(BorderFactory.createEmptyBorder(0, marginLeft, 0, 0));
		JLabel lbl10 = new JLabel("ace high");
		lbl10.setBorder(BorderFactory.createEmptyBorder(0, marginLeft, 0, 0));
		JLabel lbl11 = new JLabel("no made hand");
		lbl11.setBorder(BorderFactory.createEmptyBorder(0, marginLeft, 0, 0));
		pnlDatos.add(lbl);
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


}

