/**
 * This is an open source software developed as a study of development using Eclipse IDE amd its
 * WindowBuilder Pro GUI Designer plugin. The app has some interesting features:
 * - default status is showing only time, but the interface allow the user select for showing also
 * date and day of week selecting check boxes in the Config menu.
 * - user can click in Help menu and know some operation info and about the app
 * - user can quit the app clicking Quit.
 * 
 * @author Hamilton G. Jr (hamiltonjr2010@gmail.com)
 * @version 2018 11 24
 */
package clock;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

public class ClockGUIInterface implements ActionListener {
	private JFrame frmDesktopClock;
	private JTextField txtDisplay;
	private JTextField txtDate;
	private JTextField txtDayOfWeek;
	private JMenuBar menuBar;
	private JMenu menuConfig;
	private JCheckBox cbDate;
	private JCheckBox cbDayOfWeek;
	private JMenu menuHelp;
	private JMenuItem menuItemAbout;
	private JMenuItem menuItemInfo;
	private JMenuItem menuItemQuit;
	private SimpleDateFormat sdfDate;
	private SimpleDateFormat sdfHour;
	private SimpleDateFormat sdfDay;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClockGUIInterface window = new ClockGUIInterface();
					window.frmDesktopClock.setVisible(true);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "ERROR trying to open window!");
				}
			}			
		});
	}

	/**
	 * Create the application: this is the constructor that creates the app frame
	 */
	public ClockGUIInterface() {
		initialize();
	}	
	
	/**
	 * Initialize the contents of the frame (including the listeners).
	 */
	private void initialize() {
		// frame
		frmDesktopClock = new JFrame();
		frmDesktopClock.setBounds(new Rectangle(20, 28, 0, 0));
		frmDesktopClock.setFont(new Font("DejaVu Sans", Font.PLAIN, 24));
		frmDesktopClock.setForeground(Color.CYAN);
		frmDesktopClock.setBackground(Color.DARK_GRAY);
		frmDesktopClock.setTitle("Desktop Clock");
		frmDesktopClock.setResizable(false);
		frmDesktopClock.setBounds(100, 100, 360, 168);
		frmDesktopClock.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// time display (central part of entire display)
		txtDisplay = new JTextField();
		txtDisplay.setBorder(null);
		txtDisplay.setEditable(false);
		txtDisplay.setForeground(Color.RED);
		txtDisplay.setBackground(Color.BLACK);
		txtDisplay.setHorizontalAlignment(SwingConstants.CENTER);
		txtDisplay.setText("00:00:00");
		txtDisplay.setFont(new Font("DejaVu Sans", Font.BOLD, 60));
		frmDesktopClock.getContentPane().add(txtDisplay, BorderLayout.CENTER);
		txtDisplay.setColumns(10);
		
		// date display (top part of entire display)
		txtDate = new JTextField();
		txtDate.setBorder(new EmptyBorder(2, 10, 2, 0));
		txtDate.setFont(new Font("DejaVu Sans", Font.BOLD, 16));
		txtDate.setForeground(Color.CYAN);
		txtDate.setEditable(false);
		txtDate.setBackground(Color.BLACK);
		txtDate.setHorizontalAlignment(SwingConstants.LEFT);
		frmDesktopClock.getContentPane().add(txtDate, BorderLayout.NORTH);
		txtDate.setColumns(10);
		
		// day of week display (bottom part of entire display)
		txtDayOfWeek = new JTextField();
		txtDayOfWeek.setBorder(new EmptyBorder(2, 0, 2, 10));
		txtDayOfWeek.setForeground(Color.CYAN);
		txtDayOfWeek.setFont(new Font("DejaVu Sans", Font.BOLD, 16));
		txtDayOfWeek.setEditable(false);
		txtDayOfWeek.setBackground(Color.BLACK);
		txtDayOfWeek.setHorizontalAlignment(SwingConstants.RIGHT);
		frmDesktopClock.getContentPane().add(txtDayOfWeek, BorderLayout.SOUTH);
		txtDayOfWeek.setColumns(10);
		
		// menu bar
		menuBar = new JMenuBar();
		frmDesktopClock.setJMenuBar(menuBar);
		
		// menu config and items
		menuConfig = new JMenu("Config");
		menuBar.add(menuConfig);
		cbDate = new JCheckBox("Date");
		menuConfig.add(cbDate);
		cbDayOfWeek = new JCheckBox("Day of week");
		menuConfig.add(cbDayOfWeek);
		
		/ menu Help and items
		menuHelp = new JMenu("Help");
		menuBar.add(menuHelp);
		menuItemInfo = new JMenuItem("Info");
		menuItemInfo.addActionListener(new ActionListener() {
			// show Info message
			public void actionPerformed(ActionEvent e) {
				String text = 	"Info\n" +
								"The default format is showing only the clock. If the user\n" +
								"also wants to see the date, just click on the Config menu\n" +
								"and select the Date check box. If you also want to see the\n" +
								"day of the week, just click the Config menu and select the\n" +
								"Day of the Week check box.\n\n" + 
								"The Help menu gives you access to this  (Info)  and About\n" +
								"(app) information. If you want to exit the app, simply\n" +
								"click on Quit.";
				JOptionPane.showMessageDialog(null, text);
			}
		});
		menuHelp.add(menuItemInfo);
		menuItemAbout = new JMenuItem("About");
		menuItemAbout.addActionListener(new ActionListener() {
			// show About message
			public void actionPerformed(ActionEvent e) {
				String text = 	"About Desktop Clock\n"+
								"Desktop Clock is an open source software implemented\n" + 
								"as a study of graphical user interface (GUI) in Java.\n" + 
								"Eclipse IDE (Photon) was used in its development with\n" + 
								"the WindowBuilder Pro GUI Designer plugin.\n\n" +
								"Author: Hamilton G. Jr\n" + 
								"Version: 2018 11 24";
				JOptionPane.showMessageDialog(null, text);
			}
		});
		menuHelp.add(menuItemAbout);
		
		// menu item Quit
		menuItemQuit = new JMenuItem("Quit");
		menuItemQuit.addActionListener(new ActionListener() {
			// quit the app
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		menuBar.add(menuItemQuit);
		
		// instantiate the date formats
		sdfDate = new SimpleDateFormat("dd/MM/yyyy"); 	// date Brazilian-like
		sdfHour = new SimpleDateFormat("HH:mm:ss");		// time European-model
		sdfDay = new SimpleDateFormat("EEEE");			// day of week (by extension)
		
		// run 1 second and start
		Timer t = new Timer(1000, this);
	    t.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	    Date d = new Date();
	    
	    // show date if Date check box is selected
	    if (cbDate.isSelected())
	    	txtDate.setText(sdfDate.format(d));
	    else
	    	txtDate.setText("");
	    
	    // show time (always)
	    txtDisplay.setText(sdfHour.format(d));
	    
	    // show day of week if Day of Week check box is selected
	    if (cbDayOfWeek.isSelected())
	    	txtDayOfWeek.setText(sdfDay.format(d));
	    else
	    	txtDayOfWeek.setText("");
	}
}
