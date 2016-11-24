import java.awt.EventQueue;

public class Main {
	
	public static void main(String[] args) {
		Controlador control = new Controlador();
		final VistaPoker vista = new VistaPoker(control);
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				vista.setVisible(true);
		}});
	}
}
