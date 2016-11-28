import javax.swing.*;
import java.awt.*;  
import java.awt.event.*;  

public class VistaPoker extends JFrame {
	
	public VistaPoker(Controlador control) {
		super("Practica 2");
		setWindowProperties();

		// Layout (Disposicion de componentes en la ventana)
		Container content = getContentPane();
		content.setLayout(new BorderLayout());

		// Panel Izquierdo
		PanelIzquierdo pnlIzdo = new PanelIzquierdo(control);
		content.add(pnlIzdo, BorderLayout.WEST);

		// Panel Central 
		PanelCentral pnlCentral = new PanelCentral(control);
		content.add(pnlCentral, BorderLayout.CENTER);

		// Panel Derecho 
		PanelDerecho pnlDer = new PanelDerecho(control);
		content.add(pnlDer, BorderLayout.EAST);

		// Panel Inferior 
		PanelInferior pnlInf = new PanelInferior(control);
		content.add(pnlInf, BorderLayout.SOUTH);

		pack();
	}

	private void setWindowProperties() {
		setSize(1500, 600);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}
