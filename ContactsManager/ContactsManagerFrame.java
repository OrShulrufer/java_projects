import javax.swing.JFrame;
import javax.swing.JPanel;

public class ContactsManagerFrame extends JFrame {
	public final static int FRAME_WIDTH = 550;
	public final static int FRAME_HEIGHT = 550;
	private String frameTitle = "Contacts manager";

	private static final long serialVersionUID = 1L;
	
	public ContactsManagerFrame(ContactsManager cm) {
	//	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(FRAME_WIDTH,  FRAME_HEIGHT);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setAlwaysOnTop(true);
		setResizable(false);
		setTitle(frameTitle);
		JPanel mPanle = new ContactsManagerPanel(cm);
		add(mPanle);
		//pack();
		setVisible(true);	
	}

	public void init() {
		
	}

	
}
