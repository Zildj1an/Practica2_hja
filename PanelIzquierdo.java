import javax.swing.plaf.metal.MetalToggleButtonUI;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import javax.swing.event.*;
import javax.swing.*;
import java.awt.*;

public class PanelIzquierdo extends JPanel implements Observador {

	private Controlador control;
	private JPanel pnlBotones;
	private JPanel pnlSliderDer;
	private JPanel pnlSliderInferior;

	private JSlider sliderVer;
	private JSlider sliderHor;
	private JLabel lblPorcentajeVer;
	private JLabel lblPorcentajeHor;

	private JToggleButton[][] jtbArray;	


	public PanelIzquierdo(Controlador control) {
		this.control = control;
		setLayout(new BorderLayout()); 
		setBorder(BorderFactory.createEmptyBorder(2, 2, 0, 0));
		this.jtbArray = new JToggleButton[13][13];

		generarPanelBotones();
		generarSliders();
		
		add(pnlBotones, BorderLayout.WEST);
		add(pnlSliderDer, BorderLayout.EAST);
		add(pnlSliderInferior, BorderLayout.SOUTH);

		this.control.addObservador(this);
	}

	private void generarPanelBotones() {
		String[] rango = {"AA","AKs","AQs","AJs","ATs","A9s","A8s","A7s","A6s","A5s","A4s","A3s","A2s","AKo","KK","KQs","KJs","KTs","K9s","K8s","K7s","K6s","K5s","K4s","K3s","K2s","AQo","KQo","QQ","QJs","QTs","Q9s","Q8s","Q7s","Q6s","Q5s","Q4s","Q3s","Q2s","AJo","KJo","QJo","JJ","JTs","J9s","J8s","J7s","J6s","J5s","J4s","J3s","J2s","ATo","KTo","QTo","JTo","TT","T9s","T8s","T7s","T6s","T5s","T4s","T3s","T2s","A9o","K9o","Q9o","J9o","T9o","99","98s","97s","96s","95s","94s","93s","92s","A8o","K8o","Q8o","J8o","T8o","98o","88","87s","86s","85s","84s","83s","82s","A7o","K7o","Q7o","J7o","T7o","97o","87o","77","76s","75s","74s","73s","72s","A6o","K6o","Q6o","J6o","T6o","96o","86o","76o","66","65s","64s","63s","62s","A5o","K5o","Q5o","J5o","T5o","95o","85o","75o","65o","55","54s","53s","52s","A4o","K4o","Q4o","J4o","T4o","94o","84o","74o","64o","54o","44","43s","42s","A3o","K3o","Q3o","J3o","T3o","93o","83o","73o","63o","53o","43o","33","32s","A2o","K2o","Q2o","J2o","T2o","92o","82o","72o","62o","52o","42o","32o","22"};
		pnlBotones = new JPanel(new GridLayout(13, 13, 1, 2)); 

		for (int i = 0; i < 13; i++) {
		for (int j = 0; j < 13; j++) {
			JToggleButton jtb = new JToggleButton();
			jtb.setText(rango[(i*13)+j]);
			jtb.setMargin(new Insets(0, 0, 0, 0));
			jtb.setPreferredSize(new Dimension(40, 40));
			
			cambiarColorBoton(jtb, false, rango[(i*13)+j]);

			jtb.setUI(new MetalToggleButtonUI() {
				@Override protected Color getSelectColor() { 
					return cambiarColorBoton(jtb, true, jtb.getText());
			}});

			ActionListener actionListener = new ActionListener() {
				public void actionPerformed(ActionEvent actionEvent) {
					JToggleButton jtb1 = (JToggleButton)actionEvent.getSource();
					if (jtb.isSelected()) { 
						control.poner(jtb1.getText(), "hand");	
						jtb.setForeground(Color.WHITE);
					} else { 
						control.desponer(jtb1.getText(), "hand");	
						jtb.setForeground(Color.BLACK);
					}
			}};

			jtbArray[i][j] = jtb;
			jtb.addActionListener(actionListener);
			pnlBotones.add(jtb);
		}}
	}

	private Color cambiarColorBoton(JToggleButton jtb, boolean isSelected, String rango) {
		// Asigna color de fondo
		Color c = null;
		if (jtb.isSelected())
			jtb.setForeground(Color.WHITE);
		else 
			jtb.setForeground(Color.BLACK);
		
		
		if (isSelected) {
			if (rango.charAt(0) == rango.charAt(1)) 
				c = new Color(42, 110, 202);
			else if (rango.charAt(2) == 's')
				c = new Color(242, 195, 13);
			else
				c = new Color(239, 56, 11);
		} else {

			if (rango.charAt(0) == rango.charAt(1)) 
				jtb.setBackground(new Color(200, 224, 255));
			else if (rango.charAt(2) == 's')
				jtb.setBackground(new Color(255, 241, 155));
			else
				jtb.setBackground(new Color(255, 197, 187));
		}
		
		return c;
	}

	private void generarSliders() {
		// Slider Vertical
		pnlSliderDer = new JPanel();
		pnlSliderDer.setLayout(new BoxLayout(pnlSliderDer, BoxLayout.Y_AXIS));
		pnlSliderDer.setPreferredSize(new Dimension(65, 480));	
		pnlSliderDer.setBorder(BorderFactory.createEmptyBorder(5, 10, 0, 0));

		lblPorcentajeVer = new JLabel("0%");

		sliderVer = new JSlider(JSlider.VERTICAL, 0, 100, 0);
		sliderVer.setPaintTicks(true);
		sliderVer.setMajorTickSpacing(10);
		sliderVer.setMinorTickSpacing(5); 
		sliderVer.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
		sliderVer.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				lblPorcentajeVer.setText(String.valueOf(sliderVer.getValue()) + "%");
			}
		});
		
		pnlSliderDer.add(lblPorcentajeVer);
		pnlSliderDer.add(sliderVer);

		// Slider Horizontal
		pnlSliderInferior = new JPanel();
		pnlSliderInferior.setLayout(new BoxLayout(pnlSliderInferior, BoxLayout.X_AXIS));
		sliderHor = new JSlider(JSlider.HORIZONTAL, 0, 100, 0); 	
		sliderHor.setPaintTicks(true);
		sliderHor.setMajorTickSpacing(10);
		sliderHor.setMinorTickSpacing(5); 
		lblPorcentajeHor = new JLabel("0%");
		sliderHor.setBorder(BorderFactory.createEmptyBorder(9, 10, 5, 40));
		sliderHor.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				lblPorcentajeHor.setText(String.valueOf(sliderHor.getValue()) + "%");
			}
		});

		pnlSliderInferior.add(sliderHor);
		pnlSliderInferior.add(lblPorcentajeHor);
	}

	private int valorCarta(char letra) {
        int numero;
        
        switch (letra) {
            case 'T':
                numero = 10;
                break;
            case 'J':
                numero = 11;
                break;
            case 'Q':
                numero = 12;
                break;
            case 'K':
                numero = 13;
                break;
            case 'A':
                numero = 14;
                break;
            default:
                numero = Character.getNumericValue(letra);
                break;
        }
        
        return numero;
    }

	@Override 
	public void onRangeProccess(final ArrayList<String> cards) {
		if (cards.size() > 0) {
			for (int index = 0; index < cards.size(); index++) {
				boolean salir = false;
				for (int i = 0; i < 13 && !salir; i++) {
				for (int j = 0; j < 13 && !salir; j++) {
					if (jtbArray[i][j].getText().equals(cards.get(index))) {
						jtbArray[i][j].setSelected(true);
						cambiarColorBoton(jtbArray[i][j], false, jtbArray[i][j].getText());
						salir = true;
					}
				}}
			}
		}	

		//Ordenar AA AKs AQs
		//Comprobar longitud string
		//Si la longitud es 2
		//	Comprueba si hay otra pareja y hace la diagonal
		//Si la longitud es 3
		//	Comprobar el 3 carcter (charAt(2))	
		//		Si es == o
		//			Si hay otra carta con el caracter 1 charAt(0) igual
		//				Hace barrido en columna y colorea entre medias	
		//		Si es == s
		//			Si hay otra carta con el caracter 1 charAt(0) igual
		//				Hace barrido en fila y colorea entre medias	
	}

	@Override 
	public void onClearCards() {
		for (int i = 0; i < 13; i++) {
		for (int j = 0; j < 13; j++) {
			JToggleButton jtb = jtbArray[i][j];
			jtb.setForeground(Color.BLACK);

			if (jtb.getText().charAt(0) == jtb.getText().charAt(1))
				jtb.setBackground(new Color(200, 224, 255));
			else if (jtb.getText().charAt(2) == 's')
				jtb.setBackground(new Color(255, 241, 155));
			else
				jtb.setBackground(new Color(255, 197, 187));
			//cambiarColorBoton(jtb, false, jtb.getText()); 
			//System.out.println(jtb.getText());
		}}
	}

	@Override public void onSelectCard(final String card) {}
	@Override public void onDeselectCardBoard(final String card) {}
	@Override public void onDeselectCard(final String card) {}
	@Override public void onSliderChange(final int value) {}
	@Override public void onSelectCardBoard(final String card) {} 
}
