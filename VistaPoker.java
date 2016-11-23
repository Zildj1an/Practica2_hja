import javax.swing.*;
import java.awt.*;  
import java.awt.event.*;  

public class VistaPoker extends JFrame {
	
	public VistaPoker() {
		super("Practica 2");
		setWindowProperties();

		// Layout (Disposicion de componentes en la ventana)
		Container content = getContentPane();
		content.setLayout(new BorderLayout());

		// Panel Izquierdo
		PanelIzquierdo pnlIzdo = new PanelIzquierdo();
		content.add(pnlIzdo, BorderLayout.WEST);

		// Panel Central 
		PanelCentral pnlCentral = new PanelCentral(this);
		content.add(pnlCentral, BorderLayout.CENTER);

		// Panel Derecho 
		PanelDerecho pnlDer = new PanelDerecho();
		content.add(pnlDer, BorderLayout.EAST);

		// Panel Inferior 
		PanelInferior pnlInf = new PanelInferior();
		content.add(pnlInf, BorderLayout.SOUTH);

		pack();
	}

	private void setWindowProperties() {
		setSize(1400, 600);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}
