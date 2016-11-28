import javax.swing.plaf.metal.MetalToggleButtonUI;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.lang.StringBuilder;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.text.DecimalFormat;
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

	private ArrayList<String> cards_onHand;
	private ArrayList<String> cards_suites;
	private ArrayList<String> cards_diagonal;
	private ArrayList<String> cards_offsuited;

	private JToggleButton[][] jtbArray;
        
        private int iCartas;
        private int ik;

	public PanelIzquierdo(Controlador control) {
		this.control = control;
		setLayout(new BorderLayout()); 
		setBorder(BorderFactory.createEmptyBorder(2, 2, 0, 0));
		this.jtbArray = new JToggleButton[13][13];

		cards_onHand = new ArrayList<String>();
		cards_suites = new ArrayList<String>();
		cards_diagonal = new ArrayList<String>();
		cards_offsuited = new ArrayList<String>();

		generarPanelBotones();
		generarSliders();
		
		add(pnlBotones, BorderLayout.WEST);
		add(pnlSliderDer, BorderLayout.EAST);
		add(pnlSliderInferior, BorderLayout.SOUTH);

		this.control.addObservador(this);
                
                iCartas = 0;
                ik = 0;
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
						cards_onHand.add(jtb1.getText());
						control.poner(jtb1.getText(), "hand");	
						jtb.setForeground(Color.WHITE);
					} else { 
						cards_onHand.remove(jtb1.getText());
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
				seleccionarRango (sliderHor.getValue());
				cards_onHand.clear();
				for (int i = 0; i < 13; i++) {
				for (int j = 0; j < 13; j++) {
					if (jtbArray[i][j].isSelected())
						cards_onHand.add(jtbArray[i][j].getText());
				}}
				onShowText("");
			}
		});

		pnlSliderInferior.add(sliderHor);
		pnlSliderInferior.add(lblPorcentajeHor);
	}
        
        private void seleccionarRango (int percent){
            int nCartas = (percent*(78+312+936))/100;
            
            if(ik < 0) ik = 0;
            
            String[] skla = {"AA","KK","AKs","QQ","AKo","JJ","AQs","TT","AQo","99","AJs","88","ATs",
                             "AJo","77","66","ATo","A9s","55","A8s","KQs","44","A9o","A7s","KJs","A5s",
                             "A8o","A6s","A4s","33","KTs","A7o","A3s","KQo","A2s","A5o","A6o","A4o","KJo",
                             "QJs","A3o","22","K9s","A2o","KTo","QTs","K8s","K7s","JTs","K9o","K6s","QJo",
                             "Q9s","K5s","K8o","K4s","QTo","K7o","K3s","K2s","Q8s","K6o","J9s","K5o","Q9o",
                             "JTo","K4o","Q7s","T9s","Q6s","K3o","J8s","Q5s","K2o","Q8o","Q4s","Q3s","J9o",
                             "T8s","J7s","Q7o","Q2s","Q6o","98s","Q5o","J8o","T9o","J6s","J5s","T7s","Q4o",
                             "J4s","J7o","Q3o","97s","J3s","T8o","T6s","Q2o","J2s","87s","J6o","98o","T7o",
                             "96s","J5o","T5s","T4s","86s","J4o","T3s","T6o","97o","95s","76s","J3o","87o",
                             "T2s","85s","96o","J2o","T5o","94s","75s","T4o","65s","93s","86o","95o","84s",
                             "76o","T3o","92s","74s","54s","T2o","85o","64s","83s","94o","75o","93o","82s",
                             "73s","65o","53s","63s","84o","92o","43s","74o","72s","54o","83o","64o","62s",
                             "52s","42s","82o","73o","63o","53o","32s","72o","43o","62o","52o","42o","32o"};
            
            if(iCartas <= nCartas){
                while (iCartas < nCartas){
                    //System.out.println(ik);
                    //System.out.println(iCartas);
                    //System.out.println(nCartas);
                    seleccionarCarta(skla[ik]);
                    control.poner(skla[ik], "hand");
                    if(skla[ik].length() == 2){
                        iCartas += 6;
                    }else if(skla[ik].charAt(2) == 's'){
                        iCartas += 4;
                    }else{
                        iCartas += 12;
                    }

                    ik++;
                }
            }else{
                while (iCartas > nCartas){
                    ik--;
                    desseleccionarCarta(skla[ik]);
                    control.desponer(skla[ik], "hand");
                    if(skla[ik].length() == 2){
                        iCartas -= 6;
                    }else if(skla[ik].charAt(2) == 's'){
                        iCartas -= 4;
                    }else{
                        iCartas -= 12;
                    }
                }
            }
            
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

	private void seleccionarCarta(String card) {
		boolean salir = false;
		for (int i = 0; i < 13 && !salir; i++) {
		for (int j = 0; j < 13 && !salir; j++) {
			if (jtbArray[i][j].getText().equals(card)) {
				jtbArray[i][j].setSelected(true);
				cambiarColorBoton(jtbArray[i][j], false, card); 
				salir = true;
			}
		}}
	}
        
	private void desseleccionarCarta(String card) {
		boolean salir = false;
		for (int i = 0; i < 13 && !salir; i++) {
		for (int j = 0; j < 13 && !salir; j++) {
			if (jtbArray[i][j].getText().equals(card)) {
				jtbArray[i][j].setSelected(false);
				cambiarColorBoton(jtbArray[i][j], true, card); 
				salir = true;
			}
		}}
	}

	@Override 
	public void onRangeProccess(final ArrayList<String> cards) {
		if (cards.size() > 0) {
			// Busca las manos a colorear
			for (int index = 0; index < cards.size(); index++) {
				String actual_card = cards.get(index);

				if (actual_card.length() == 2) {
					seleccionarCarta(actual_card);
				} else if (actual_card.length() == 3) {
					if (actual_card.charAt(2) == '+') {
						if (actual_card.charAt(0) == actual_card.charAt(1))	{
							StringBuilder sb = new StringBuilder();
							sb.append(actual_card.charAt(0));
							sb.append(actual_card.charAt(1));
							String c = sb.toString();

							boolean salir = false;
							int indice = 0;
							for (int i = 0; i < 13 && !salir; i++) {
								if (jtbArray[i][i].getText().equals(c)) 
									indice = i;	
							}
								
							for (int i = indice; i >=0; i--) {
								jtbArray[i][i].setSelected(true);
								cambiarColorBoton(jtbArray[i][i], false, c); 
							}
						} 
					} else { 
						seleccionarCarta(actual_card);	
					}
				} else if (actual_card.length() == 4) {
					StringBuilder sb = new StringBuilder();
					sb.append(actual_card.charAt(0));
					sb.append(actual_card.charAt(1));
					sb.append(actual_card.charAt(2));
					String c = sb.toString();
					boolean enc = false;
						
					if (actual_card.charAt(2) == 's') {
						for (int i = 0; i < 13 && !enc; i++) {
						for (int j = i + 1; j < 13 && !enc; j++) {
							if (jtbArray[i][j].getText().equals(c)) {
								enc = true;
									
								for (int k = j; k > i; k--) {
									String actual = jtbArray[i][k].getText();		
									jtbArray[i][k].setSelected(true);
									cambiarColorBoton(jtbArray[i][k], false, actual); 
								}	
							}
						}}	
					} else {
						for (int i = 0; i < 13 && !enc; i++) {
						for (int j = 0; j < i  && !enc; j++) {
							if (jtbArray[i][j].getText().equals(c)) {
								enc = true;
									
								for (int k = i; k > j; k--) {
									String actual = jtbArray[k][j].getText();		
									jtbArray[k][j].setSelected(true);
									cambiarColorBoton(jtbArray[k][j], false, actual); 
								}	
							}
								
						}}
						
					}
				} else if (actual_card.length() == 5) {
					if (actual_card.charAt(0) == actual_card.charAt(1) && actual_card.charAt(2) == '-')	{
						// JJ-44
						StringBuilder sb1 = new StringBuilder();
						sb1.append(actual_card.charAt(0));
						sb1.append(actual_card.charAt(1));
						StringBuilder sb2 = new StringBuilder();
						sb2.append(actual_card.charAt(3));
						sb2.append(actual_card.charAt(4));
						String c1 = sb1.toString();
						String c2 = sb2.toString();

						if (valorCarta(actual_card.charAt(0)) < valorCarta(actual_card.charAt(3))) {
							String aux = c1;
							c1 = c2;
							c2 = aux;
						}
						boolean salir = false;
						for (int i = 0; i < 13 && !salir; i++) {
							if (jtbArray[i][i].getText().equals(c1)) {
								for (int j = i; j < 13 && !salir; j++) {
									String actual = jtbArray[j][j].getText();	
									jtbArray[j][j].setSelected(true);
									cambiarColorBoton(jtbArray[j][j], false, actual); 	
									if (jtbArray[j][j].getText().equals(c2))
										salir = true;
								}
							} 
						}
					}
				} else if (actual_card.length() == 7) {
					StringBuilder sb1 = new StringBuilder();
					sb1.append(actual_card.charAt(0));
					sb1.append(actual_card.charAt(1));
					StringBuilder sb2 = new StringBuilder();
					sb2.append(actual_card.charAt(4));
					sb2.append(actual_card.charAt(5));

					if (actual_card.charAt(2) == 's' && actual_card.charAt(6) == 's') {
						sb1.append(actual_card.charAt(2));
						String c1 = sb1.toString();
						sb2.append(actual_card.charAt(6));
						String c2 = sb2.toString();

						if (valorCarta(actual_card.charAt(1)) < valorCarta(actual_card.charAt(5))) {
							String aux = c1;
							c1 = c2;
							c2 = aux;
						}
						
						boolean enc = false;
						for (int i = 0; i < 13 && !enc; i++) {
						for (int j = i + 1; j < 13 && !enc; j++) {
							if (jtbArray[i][j].getText().equals(c1)) {
									
								for (int k = j; k < 13 && !enc; k++) {
									String actual = jtbArray[i][k].getText();		
									jtbArray[i][k].setSelected(true);
									cambiarColorBoton(jtbArray[i][k], false, actual); 
									if (jtbArray[i][k].getText().equals(c2))
										enc= true;
								}	
								enc = true;
							}
						}}	
					} else if (actual_card.charAt(2) == 'o' && actual_card.charAt(6) == 'o') {
						sb1.append(actual_card.charAt(2));
						String c1 = sb1.toString();
						sb2.append(actual_card.charAt(6));
						String c2 = sb2.toString();

						if (valorCarta(actual_card.charAt(1)) < valorCarta(actual_card.charAt(5))) {
							String aux = c1;
							c1 = c2;
							c2 = aux;
						}
						
						boolean enc = false;
						for (int i = 0; i < 13 && !enc; i++) {
						for (int j = 0; j < i  && !enc; j++) {
							if (jtbArray[i][j].getText().equals(c1)) {
									
								for (int k = i; k < 13 && !enc; k++) {
									String actual = jtbArray[k][j].getText();		
									jtbArray[k][j].setSelected(true);
									cambiarColorBoton(jtbArray[k][j], false, actual); 
									if (jtbArray[k][j].getText().equals(c2))
										enc= true;
								}	
								enc = true;
							}
						}}	
					} 
				}
			}
		}	

		ArrayList<String> cards_selected = new ArrayList<String>();
		for (int i = 0; i < 13; i++) {
		for (int j = 0; j < 13; j++) {
			if (jtbArray[i][j].isSelected()) 
				cards_selected.add(jtbArray[i][j].getText());
		}}
		control.updateText(cards_selected);
		onShowText("");
	}

	@Override 
	public void onClearCards(boolean hands) {
		for (int i = 0; i < 13; i++) {
		for (int j = 0; j < 13; j++) {
			JToggleButton jtb = jtbArray[i][j];
			cambiarColorBoton(jtb, false, jtb.getText()); 
			jtb.setSelected(false);
			jtb.setForeground(Color.BLACK);
		}}
	}

	private void checkDiagonalToText(StringBuilder texto) {
		if (cards_diagonal.size() != 0) {
			// Comprobar el +
			int cont = 0;
			if (jtbArray[0][0].isSelected()) {
				boolean enc = false;
				for (int i = 1; i < 13 && !enc; i++) {
					if (jtbArray[i][i].isSelected()) 
						cont += 1;
					else
						enc = true;
				}
				// Encontró rango con +
				if (cont > 0) {
					StringBuilder sb = new StringBuilder();
					if (texto.length() > 0)
						sb.append(", ");
					sb.append(jtbArray[cont][cont].getText());
					sb.append("+");
					texto.append(sb.toString());
				}
			} 
				
			// Comprobar el -
			for (int i = cont; i < 13; i++) {
				int cont2 = 0;
				boolean enc = false;
				if (jtbArray[i][i].isSelected()) {
					for (int j = i + 1; j < 13 && !enc; j++) {
						if (jtbArray[j][j].isSelected()) 
							cont2 +=1;
						else
							enc = true;
					}	
					// Encontró rango con -
					if (cont2 > 0) {
						StringBuilder sb = new StringBuilder();
						if (texto.length() > 0)
							sb.append(", ");
						sb.append(jtbArray[i][i].getText());
						sb.append("-");
						sb.append(jtbArray[cont2+i][cont2+i].getText());
						texto.append(sb.toString());

						i = cont2 + i;
					} 
				}
			}
			
			//Comprobar individuales 
			for (int i = 0; i < 13; i++) {
				if (i == 0) {
					if (jtbArray[i][i].isSelected() && !jtbArray[i+1][i+1].isSelected()) {
						if (texto.length() > 0)
							texto.append(", ");
						texto.append(jtbArray[i][i].getText());
						i = i+1;
					}
				} else if (i < 12) {
					if (jtbArray[i][i].isSelected() && !jtbArray[i+1][i+1].isSelected() && !jtbArray[i-1][i-1].isSelected()) {
						if (texto.length() > 0)
							texto.append(", ");
						texto.append(jtbArray[i][i].getText());
						i = i+1;
					}
				} else {
					if (jtbArray[i][i].isSelected() && !jtbArray[i-1][i-1].isSelected()) {
						if (texto.length() > 0)
							texto.append(", ");
						texto.append(jtbArray[i][i].getText());
					}
				}
			}
		}
	}

	private void checkSuitesToText(StringBuilder texto) {
		if (cards_suites.size() != 0) {
			// Comprobar el +
			int cont = 0;
			for (int i = 0; i < 11; i++) {
				cont = 0;
				if (jtbArray[i][i+1].isSelected()) {
					boolean enc = false;
					for (int j = i+2; j < 13 && !enc; j++) {
						if (jtbArray[i][j].isSelected()) 
							cont += 1;
						else
							enc = true;
					}
					 //Encontró rango con +
					if (cont > 0) {
						StringBuilder sb = new StringBuilder();
						if (texto.length() > 0)
							sb.append(", ");
						sb.append(jtbArray[i][i+cont+1].getText());
						sb.append("+");
						texto.append(sb.toString());
					}
				} 

				// Comprobar el -
				for (int j = i + 2 + cont; j < 13; j++) {
					int cont2 = 0;
					boolean enc = false;
					if (jtbArray[i][j].isSelected()) {
						for (int k = j + 1; k < 13 && !enc; k++) {
							if (jtbArray[i][k].isSelected()) 
								cont2 +=1;
							else
								enc = true;
						}	
						// Encontró rango con -
						if (cont2 > 0) {
							StringBuilder sb = new StringBuilder();
							if (texto.length() > 0)
								sb.append(", ");
							sb.append(jtbArray[i][j].getText());
							sb.append("-");
							sb.append(jtbArray[i][cont2+j].getText());
							texto.append(sb.toString());

							j = cont2 + j;
						} 
					}
				}
			}

			//Comprobar individuales 
			for (int i = 0; i < 13; i++) {
			for (int j = i+1; j < 13; j++) {
				if (j == i+1 && i < 11) {
					if (jtbArray[i][j].isSelected() && !jtbArray[i][j+1].isSelected()) {
						if (texto.length() > 0)
							texto.append(", ");
						texto.append(jtbArray[i][j].getText());
						j = j+1;
					}
				} else if (j < 12) {
					if (jtbArray[i][j].isSelected() && !jtbArray[i][j+1].isSelected() && !jtbArray[i][j-1].isSelected()) {
						if (texto.length() > 0)
							texto.append(", ");
						texto.append(jtbArray[i][j].getText());
						j = j+1;
					}
				} else {
					if (jtbArray[i][j].isSelected() && !jtbArray[i][j-1].isSelected()) {
						if (texto.length() > 0)
							texto.append(", ");
						texto.append(jtbArray[i][j].getText());
					}
				}
			}}
		}
	}

	private void checkOffsuitesToText(StringBuilder texto) {
		if (cards_offsuited.size() != 0) {
			//Comprobar el +
			int cont = 0;
			for (int j = 1; j < 13; j++) {
				cont = 0;
				if (jtbArray[j][j-1].isSelected()) {
					boolean enc = false;
					for (int i = j+1; i < 13 && !enc; i++) {
						if (jtbArray[i][j-1].isSelected()) 
							cont += 1;
						else
							enc = true;
					}
					//Encontró rango con +
					if (cont > 0) {
						StringBuilder sb = new StringBuilder();
						if (texto.length() > 0)
							sb.append(", ");
						sb.append(jtbArray[j+cont][j-1].getText());
						sb.append("+");
						texto.append(sb.toString());
					}
				} 

				// Comprobar con -
				for (int i = j + 1 + cont; i < 13; i++) {
					int cont2 = 0;
					boolean enc = false;
					if (jtbArray[i][j-1].isSelected()) {
						for (int k = i+1; k < 13 && !enc; k++) {
							if (jtbArray[k][j-1].isSelected()) { 
								cont2 +=1;
							} else
								enc = true;
						}	

						 //Encontró rango con -
						if (cont2 > 0) {
							StringBuilder sb = new StringBuilder();
							if (texto.length() > 0)
								sb.append(", ");
							sb.append(jtbArray[i][j-1].getText());
							sb.append("-");
							sb.append(jtbArray[i+cont2][j-1].getText());
							texto.append(sb.toString());

							i = cont2 + i;
							//System.out.println(i);
						} 
					}
				}
			}

			//Comprobar individuales 
			for (int i = 0; i < 12; i++)  {
			for (int j = i + 1; j < 13;  j++) {
				if (j == i && i < 12) {
					if (jtbArray[j][i].isSelected() && !jtbArray[j+1][i].isSelected()) {
						if (texto.length() > 0)
							texto.append(", ");
						texto.append(jtbArray[j][i].getText());
						j = j+1;
					}
				} else if (j < 12) {
					if (jtbArray[j][i].isSelected() && !jtbArray[j+1][i].isSelected() && !jtbArray[j-1][i].isSelected()) {
						if (texto.length() > 0)
							texto.append(", ");
						texto.append(jtbArray[j][i].getText());
						j = j+1;
					}
				} else {
					if (jtbArray[j][i].isSelected() && !jtbArray[j-1][i].isSelected()) {
						if (texto.length() > 0)
							texto.append(", ");
						texto.append(jtbArray[j][i].getText());
					}
				}
			}}
		}
	}

	@Override public void onShowText(final String card) {
		for (int i = 0; i < cards_onHand.size(); i++) {
			if (cards_onHand.get(i).length() == 2) 
				cards_diagonal.add(cards_onHand.get(i));	
			else {
				if (cards_onHand.get(i).charAt(2) == 's')
					cards_suites.add(cards_onHand.get(i));
				else
					cards_offsuited.add(cards_onHand.get(i));
			}
		}
		StringBuilder sb = new StringBuilder();
		checkDiagonalToText(sb);
		checkSuitesToText(sb);	
		checkOffsuitesToText(sb);


		ArrayList<String> cards_selected = new ArrayList<String>();
		for (int i = 0; i < 13; i++) {
		for (int j = 0; j < 13; j++) {
			if (jtbArray[i][j].isSelected()) 
				cards_selected.add(jtbArray[i][j].getText());
		}}
		control.updateText(cards_selected);
		control.mostrar(sb.toString());
		double number = cards_selected.size() * 100.0 / 169.0;
		DecimalFormat df = new DecimalFormat("0.00");
		lblPorcentajeHor.setText(String.valueOf(df.format(number)) + "%");
	}

	@Override public void onRangeProcessShow(final ArrayList<String> cards) {}
	@Override public void onAddCardHand(final String card) {}
	@Override public void onSelectCard(final String text) {}
	@Override public void onDeselectCardBoard(final String card) {}
	@Override public void onDeselectCard(final String card) {}
	@Override public void onSliderChange(final int value) {}
	@Override public void onSelectCardBoard(final String card) {} 
}
