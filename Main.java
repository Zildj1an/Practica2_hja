import java.awt.EventQueue;

public class Main {
	
	public static void main(String[] args) {
		final VistaPoker vista = new VistaPoker();

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				vista.setVisible(true);
		}});
	}
}
