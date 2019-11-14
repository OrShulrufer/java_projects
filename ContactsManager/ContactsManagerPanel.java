import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;


public class ContactsManagerPanel extends JPanel{
	private ContactsManager	cm;
	
	private JPanel upPanel;
	private JPanel middlePanel;
	private JPanel downPanel;
	
	private String[] labelsArr = {"First name:", "Last name:", "phone number", 
			"file path"};
	private JLabel firstNameL1;
	private JLabel lastNameL1;
	private JLabel phoneNumberL1;
	private JLabel firstNameL2;
	private JLabel lastNameL2;
	private JLabel phoneNumberL2;
	private static JLabel firstNameL3;
	private static JLabel lastNameL3;
	private static JLabel phoneNumberL3;
	private JLabel filePathL;
	
	private String[] buttonsArr = {"Create", "Update", "Edit contact", "Export",
			"load file", ">", "<",  "First", "Last"};
	private JButton createB;
	private JButton updateB;
	private JButton editContactB;
	private JButton exportB;
	private JButton loadFileB;
	private JButton forwardB;
	private JButton backwardB;
	private JButton firstB;
	private JButton lastB;
	
	private JTextField firstNameTF;
	private JTextField lastNameTF;
	private JTextField phoneNumberTF;
	private JTextField filePathTF;
	
	private String[] comboboxFormatsArr = {"txt", "obj.dat", "byte.dat"};
	private JComboBox formatCB;
	
	


	
	public ContactsManagerPanel(ContactsManager cm) {
		this.cm = cm;

		createUpPanel();
		createMiddlePanel();
		createDownPanel();

		SpringLayout theLayout = new SpringLayout();
		this.setLayout(theLayout); 

		add(upPanel);
		add(middlePanel);
		add(downPanel);

		theLayout.putConstraint(SpringLayout.WEST, upPanel, 5,
				SpringLayout.WEST, this);
		theLayout.putConstraint(SpringLayout.NORTH, upPanel, 5,
				SpringLayout.NORTH, this);
		theLayout.putConstraint(SpringLayout.EAST, upPanel, -5,
				SpringLayout.EAST, this);
		theLayout.putConstraint(SpringLayout.SOUTH, upPanel, 150,
				SpringLayout.NORTH, this);
		
		theLayout.putConstraint(SpringLayout.WEST, middlePanel, 5,
				SpringLayout.WEST, this);
		theLayout.putConstraint(SpringLayout.NORTH, middlePanel, 0,
				SpringLayout.SOUTH, upPanel);
		theLayout.putConstraint(SpringLayout.EAST, middlePanel, -5,
				SpringLayout.EAST, this);
		theLayout.putConstraint(SpringLayout.SOUTH, middlePanel, 220,
				SpringLayout.SOUTH, upPanel);
		
		theLayout.putConstraint(SpringLayout.WEST, downPanel, 5,
				SpringLayout.WEST, this);
		theLayout.putConstraint(SpringLayout.NORTH, downPanel, 0,
				SpringLayout.SOUTH, middlePanel);
		theLayout.putConstraint(SpringLayout.EAST, downPanel, -5,
				SpringLayout.EAST, this);
		theLayout.putConstraint(SpringLayout.SOUTH, downPanel, 150,
				SpringLayout.SOUTH, middlePanel);
	}
	
	private void createDownPanel() {
		downPanel = new JPanel();
		SpringLayout theLayout = new SpringLayout();
		downPanel.setLayout(theLayout); 
		
		formatCB = new JComboBox<>(comboboxFormatsArr);
		downPanel.add(formatCB);
		exportB = new JButton(buttonsArr[3]);
		downPanel.add(exportB);
		filePathL = new JLabel(labelsArr[3]);
		downPanel.add(filePathL);
		filePathTF = new JTextField();
		downPanel.add(filePathTF);
		loadFileB = new JButton(buttonsArr[4]);
		downPanel.add(loadFileB);
		
		theLayout.putConstraint(SpringLayout.EAST, loadFileB, -5,
				SpringLayout.EAST, downPanel);
		theLayout.putConstraint(SpringLayout.WEST, loadFileB, 275,
				SpringLayout.WEST, downPanel);
		theLayout.putConstraint(SpringLayout.SOUTH, loadFileB, -5,
				SpringLayout.SOUTH, downPanel);
		theLayout.putConstraint(SpringLayout.NORTH, loadFileB, -30,
				SpringLayout.SOUTH, downPanel);
		
		theLayout.putConstraint(SpringLayout.WEST, filePathL, 275,
				SpringLayout.WEST, downPanel);
		theLayout.putConstraint(SpringLayout.NORTH, filePathL, 5,
				SpringLayout.NORTH, downPanel);
		
		theLayout.putConstraint(SpringLayout.EAST, filePathTF, -5,
				SpringLayout.EAST, downPanel);
		theLayout.putConstraint(SpringLayout.WEST, filePathTF, 275,
				SpringLayout.WEST, downPanel);
		theLayout.putConstraint(SpringLayout.NORTH, filePathTF, 50,
				SpringLayout.NORTH, downPanel);

		
		theLayout.putConstraint(SpringLayout.WEST, formatCB, 5,
				SpringLayout.WEST, downPanel);
		theLayout.putConstraint(SpringLayout.NORTH, formatCB, 50,
				SpringLayout.NORTH, downPanel);
		theLayout.putConstraint(SpringLayout.SOUTH, formatCB, 70,
				SpringLayout.NORTH, downPanel);
		theLayout.putConstraint(SpringLayout.EAST, formatCB, 120,
				SpringLayout.WEST, downPanel);
		
		theLayout.putConstraint(SpringLayout.WEST, exportB, 10,
				SpringLayout.EAST, formatCB);
		theLayout.putConstraint(SpringLayout.NORTH, exportB, 5,
				SpringLayout.NORTH, downPanel);
		theLayout.putConstraint(SpringLayout.SOUTH, exportB, -5,
				SpringLayout.SOUTH, downPanel);
		theLayout.putConstraint(SpringLayout.EAST, exportB, 250,
				SpringLayout.WEST, downPanel);
		
	}
	private void createUpPanel() {
		upPanel = new JPanel();
		SpringLayout theLayout = new SpringLayout();
		upPanel.setLayout(theLayout); 
		
		firstNameTF = new JTextField();
		upPanel.add(firstNameTF);
		lastNameTF = new JTextField();
		upPanel.add(lastNameTF);
		phoneNumberTF = new JTextField();
		upPanel.add(phoneNumberTF);
		
		firstNameL1 = new JLabel(labelsArr[0]);
		upPanel.add(firstNameL1);
		lastNameL1 = new JLabel (labelsArr[1]);
		upPanel.add(lastNameL1);
		phoneNumberL1 = new JLabel (labelsArr[2]);
		upPanel.add(phoneNumberL1);
		
		createB = new JButton(buttonsArr[0]);	
		createB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					cm.addContact(firstNameTF.getText(), lastNameTF.getText(),
							phoneNumberTF.getText());
				} catch (IOException e1) {
					e1.printStackTrace();
				}			
			}
		});
		
		upPanel.add(createB);
		
		updateB = new JButton(buttonsArr[1]);
		updateB.setEnabled(false);
		updateB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
			
				
			}
		});

		upPanel.add(updateB);
		
		theLayout.putConstraint(SpringLayout.WEST, firstNameL1, 5,
				SpringLayout.WEST, upPanel);
		theLayout.putConstraint(SpringLayout.NORTH, firstNameL1, 5,
				SpringLayout.NORTH, upPanel);
		
		theLayout.putConstraint(SpringLayout.WEST, lastNameL1, 5,
				SpringLayout.WEST, upPanel);
		theLayout.putConstraint(SpringLayout.NORTH, lastNameL1, 20,
				SpringLayout.SOUTH, firstNameL1);
		
		theLayout.putConstraint(SpringLayout.WEST, phoneNumberL1, 5,
				SpringLayout.WEST, upPanel);
		theLayout.putConstraint(SpringLayout.NORTH, phoneNumberL1, 20,
				SpringLayout.SOUTH, lastNameL1);
		
		theLayout.putConstraint(SpringLayout.WEST, firstNameTF, 275,
				SpringLayout.WEST, upPanel);
		theLayout.putConstraint(SpringLayout.EAST, firstNameTF, -5,
				SpringLayout.EAST, upPanel);
		theLayout.putConstraint(SpringLayout.NORTH, firstNameTF, 5,
				SpringLayout.NORTH, upPanel);
		
		theLayout.putConstraint(SpringLayout.WEST, lastNameTF, 275,
				SpringLayout.WEST, upPanel);
		theLayout.putConstraint(SpringLayout.EAST, lastNameTF, -5,
				SpringLayout.EAST, upPanel);
		theLayout.putConstraint(SpringLayout.NORTH, lastNameTF, 10,
				SpringLayout.SOUTH, firstNameTF);
		
    	theLayout.putConstraint(SpringLayout.WEST, phoneNumberTF, 275,
				SpringLayout.WEST, upPanel);
		theLayout.putConstraint(SpringLayout.EAST, phoneNumberTF, -5,
				SpringLayout.EAST, upPanel);
		theLayout.putConstraint(SpringLayout.NORTH, phoneNumberTF, 10,
				SpringLayout.SOUTH, lastNameTF);
		
		theLayout.putConstraint(SpringLayout.WEST, createB, 5,
				SpringLayout.WEST, upPanel);
		theLayout.putConstraint(SpringLayout.EAST, createB, 250,
				SpringLayout.WEST, upPanel);
		theLayout.putConstraint(SpringLayout.NORTH, createB, 20,
				SpringLayout.SOUTH, phoneNumberTF);
		
		theLayout.putConstraint(SpringLayout.WEST, updateB, 25,
				SpringLayout.EAST, createB);
		theLayout.putConstraint(SpringLayout.EAST, updateB, -5,
				SpringLayout.EAST, upPanel);
		theLayout.putConstraint(SpringLayout.NORTH, updateB, 20,
				SpringLayout.SOUTH, phoneNumberTF);
	}
	
	private void createMiddlePanel() {
		middlePanel = new JPanel();
		SpringLayout theLayout = new SpringLayout();
		middlePanel.setLayout(theLayout); 
		
		firstNameL2 = new JLabel(labelsArr[0]);
		middlePanel.add(firstNameL2);
		lastNameL2 = new JLabel(labelsArr[1]);
		middlePanel.add(lastNameL2);
		phoneNumberL2 = new JLabel(labelsArr[2]);
		middlePanel.add(phoneNumberL2);
		firstNameL3 = new JLabel();
		middlePanel.add(firstNameL3);
		lastNameL3 = new JLabel();
		middlePanel.add(lastNameL3);
		phoneNumberL3 = new JLabel();
		middlePanel.add(phoneNumberL3);

		forwardB = new JButton(buttonsArr[5]);
		forwardB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					cm.getNextContact();		
				} catch (IOException e1) {
					e1.printStackTrace();
				}			
			}
		});
		middlePanel.add(forwardB);
		
		backwardB = new JButton(buttonsArr[6]);
		backwardB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					cm.getPreviousContact();			
				} catch (IOException e1) {
					e1.printStackTrace();
				}			
			}
		});
		middlePanel.add(backwardB);
		
		firstB = new JButton(buttonsArr[7]);
		firstB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					cm.getFirsContact();
				} catch (IOException e1) {		
					e1.printStackTrace();
				}			
			}
		});
		middlePanel.add(firstB);
		
		lastB = new JButton(buttonsArr[8]);
		lastB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					cm.getLastContact();
				} catch (IOException e1) {		
					e1.printStackTrace();
				}			
			}
		});
		middlePanel.add(lastB);
		editContactB = new JButton(buttonsArr[2]);
		middlePanel.add(editContactB);
		
		theLayout.putConstraint(SpringLayout.WEST, firstNameL3, 140,
				SpringLayout.EAST, firstNameL2);
		theLayout.putConstraint(SpringLayout.NORTH, firstNameL3, 10,
				SpringLayout.NORTH, middlePanel);
		
		theLayout.putConstraint(SpringLayout.WEST, lastNameL3, 140,
				SpringLayout.EAST, firstNameL2);
		theLayout.putConstraint(SpringLayout.NORTH, lastNameL3, 65,
				SpringLayout.NORTH, middlePanel);
		
		theLayout.putConstraint(SpringLayout.WEST, phoneNumberL3, 140,
				SpringLayout.EAST, firstNameL2);
		theLayout.putConstraint(SpringLayout.NORTH, phoneNumberL3, 130,
				SpringLayout.NORTH, middlePanel);
		
		
		theLayout.putConstraint(SpringLayout.WEST, firstNameL2, 5,
				SpringLayout.EAST, backwardB);
		theLayout.putConstraint(SpringLayout.NORTH, firstNameL2, 10,
				SpringLayout.NORTH, middlePanel);
		
		theLayout.putConstraint(SpringLayout.WEST, lastNameL2, 5,
				SpringLayout.EAST, backwardB);
		theLayout.putConstraint(SpringLayout.NORTH, lastNameL2, 65,
				SpringLayout.NORTH, middlePanel);
		
		theLayout.putConstraint(SpringLayout.WEST, phoneNumberL2, 5,
				SpringLayout.EAST, backwardB);
		theLayout.putConstraint(SpringLayout.NORTH, phoneNumberL2, 130,
				SpringLayout.NORTH, middlePanel);
		
		theLayout.putConstraint(SpringLayout.WEST, editContactB, 5,
				SpringLayout.EAST, backwardB);
		theLayout.putConstraint(SpringLayout.NORTH, editContactB, -20,
				SpringLayout.SOUTH, middlePanel);
		theLayout.putConstraint(SpringLayout.SOUTH, editContactB, 0,
				SpringLayout.SOUTH, middlePanel);
		theLayout.putConstraint(SpringLayout.EAST, editContactB, 150,
				SpringLayout.EAST, backwardB);
		
		theLayout.putConstraint(SpringLayout.WEST, backwardB, 5,
				SpringLayout.WEST, middlePanel);
		theLayout.putConstraint(SpringLayout.NORTH, backwardB, 5,
				SpringLayout.NORTH, middlePanel);
		theLayout.putConstraint(SpringLayout.SOUTH, backwardB, 110,
				SpringLayout.NORTH, middlePanel);
		theLayout.putConstraint(SpringLayout.EAST, backwardB, 70,
				SpringLayout.WEST, middlePanel);
		
		theLayout.putConstraint(SpringLayout.WEST, firstB, 5,
				SpringLayout.WEST, middlePanel);
		theLayout.putConstraint(SpringLayout.NORTH, firstB, 5,
				SpringLayout.SOUTH, backwardB);
		theLayout.putConstraint(SpringLayout.SOUTH, firstB, 110,
				SpringLayout.SOUTH, backwardB);
		theLayout.putConstraint(SpringLayout.EAST, firstB,70,
				SpringLayout.WEST, middlePanel);
		
		theLayout.putConstraint(SpringLayout.EAST, forwardB, -5,
				SpringLayout.EAST, middlePanel);
		theLayout.putConstraint(SpringLayout.NORTH, forwardB, 5,
				SpringLayout.NORTH, middlePanel);
		theLayout.putConstraint(SpringLayout.SOUTH, forwardB, 110,
				SpringLayout.NORTH, middlePanel);
		theLayout.putConstraint(SpringLayout.WEST, forwardB, -70,
				SpringLayout.EAST, middlePanel);
		
		theLayout.putConstraint(SpringLayout.EAST, lastB, -5,
				SpringLayout.EAST, middlePanel);
		theLayout.putConstraint(SpringLayout.NORTH, lastB, 5,
				SpringLayout.SOUTH, forwardB);
		theLayout.putConstraint(SpringLayout.SOUTH, lastB, 110,
				SpringLayout.SOUTH, forwardB);
		theLayout.putConstraint(SpringLayout.WEST, lastB, -70,
				SpringLayout.EAST, middlePanel);
	}

	public static  void setFirstNameL3(String firstName) {
		firstNameL3.setText(firstName);
	}

	public static void setLastNameL3(String lastName) {
		lastNameL3.setText(lastName);
	}

	public static void setPhoneNumberL3(String phoneNumber) {
		phoneNumberL3.setText(phoneNumber);
	}

}
