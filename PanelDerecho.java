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

	public PanelDerecho(Controlador control) {
		this.control = control;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setPreferredSize(new Dimension(300, 480));	
		setAlignmentX(Component.LEFT_ALIGNMENT);
		int marginLeft = 20, marginRight = 15, marginTop = 2;

		lblTexto1 = new JLabel("Hay que ajustar el ancho al texto");
		lblTexto1.setBorder(BorderFactory.createEmptyBorder(5, marginLeft, 0, marginRight));

		lblTexto2= new JLabel("Texto a mostrar 2");
		lblTexto2.setBorder(BorderFactory.createEmptyBorder(marginTop, marginLeft, 0, marginRight));
		
		lblTexto3 = new JLabel("Texto a mostrar 3");
		lblTexto3.setBorder(BorderFactory.createEmptyBorder(marginTop, marginLeft, 0, marginRight));
		
		lblTexto4 = new JLabel("Texto a mostrar 4");
		lblTexto4.setBorder(BorderFactory.createEmptyBorder(marginTop, marginLeft, 0, marginRight));
		
		lblTexto5 = new JLabel("Texto a mostrar 5");
		lblTexto5.setBorder(BorderFactory.createEmptyBorder(marginTop, marginLeft, 0, marginRight));
		
		lblTexto6 = new JLabel("Texto a mostrar 6");
		lblTexto6.setBorder(BorderFactory.createEmptyBorder(marginTop, marginLeft, 0, marginRight));

		add(lblTexto1);
		add(lblTexto2);
		add(lblTexto3);
		add(lblTexto4);
		add(lblTexto5);
		add(lblTexto6);

		this.control.addObservador(this);
	}

	@Override public void onSliderChange(int value) {}
	@Override public void onSelectCard(String combo) {}
	@Override public void onSelectCardBoard(final String card) {} 
	@Override public void onDeselectCardBoard(final String card) {}
	@Override public void onDeselectCard(final String card) {}
}

