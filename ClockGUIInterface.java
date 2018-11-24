package clock;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
	private static Calendar c;
	private JTextField txtDate;
	private JTextField txtDayOfWeek;
	private JMenuBar menuBar;
	private JMenu menuConfig;
	private JCheckBox cbDate;
	private JCheckBox cbDayOfWeek;
	private JMenu menuHelp;
	private JMenuItem menuItemAbout;
	private JMenuItem menuItemInfo;
	private SimpleDateFormat sdfDate;
	private SimpleDateFormat sdfHour;
	private SimpleDateFormat sdfDay;
	private JMenuItem mntmQuit;

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
					//JOptionPane.showMessageDialog(null, "ERROR trying to open window!");
					e.setStackTrace(null);
				}
			}			
		});
	}

	/**
	 * Create the application.
	 */
	public ClockGUIInterface() {
		initialize();
	}	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDesktopClock = new JFrame();
		frmDesktopClock.setBounds(new Rectangle(20, 28, 0, 0));
		frmDesktopClock.setFont(new Font("DejaVu Sans", Font.PLAIN, 24));
		frmDesktopClock.setForeground(Color.CYAN);
		frmDesktopClock.setBackground(Color.DARK_GRAY);
		frmDesktopClock.setTitle("Desktop Clock");
		frmDesktopClock.setResizable(false);
		frmDesktopClock.setBounds(100, 100, 360, 168);
		frmDesktopClock.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
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
		
		txtDate = new JTextField();
		txtDate.setBorder(new EmptyBorder(2, 10, 2, 0));
		txtDate.setFont(new Font("DejaVu Sans", Font.BOLD, 16));
		txtDate.setForeground(Color.CYAN);
		txtDate.setEditable(false);
		txtDate.setBackground(Color.BLACK);
		txtDate.setHorizontalAlignment(SwingConstants.LEFT);
		frmDesktopClock.getContentPane().add(txtDate, BorderLayout.NORTH);
		txtDate.setColumns(10);
		
		txtDayOfWeek = new JTextField();
		txtDayOfWeek.setBorder(new EmptyBorder(2, 0, 2, 10));
		txtDayOfWeek.setForeground(Color.CYAN);
		txtDayOfWeek.setFont(new Font("DejaVu Sans", Font.BOLD, 16));
		txtDayOfWeek.setEditable(false);
		txtDayOfWeek.setBackground(Color.BLACK);
		txtDayOfWeek.setHorizontalAlignment(SwingConstants.RIGHT);
		frmDesktopClock.getContentPane().add(txtDayOfWeek, BorderLayout.SOUTH);
		txtDayOfWeek.setColumns(10);
		
		menuBar = new JMenuBar();
		frmDesktopClock.setJMenuBar(menuBar);
		
		menuConfig = new JMenu("Config");
		menuBar.add(menuConfig);
		cbDate = new JCheckBox("Date");
		menuConfig.add(cbDate);
		cbDayOfWeek = new JCheckBox("Day of week");
		menuConfig.add(cbDayOfWeek);
		
		menuHelp = new JMenu("Help");
		menuBar.add(menuHelp);
		menuItemInfo = new JMenuItem("Info");
		menuItemInfo.addActionListener(new ActionListener() {
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
		
		mntmQuit = new JMenuItem("Quit");
		mntmQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		menuBar.add(mntmQuit);
		
		sdfDate = new SimpleDateFormat("dd/MM/yyyy");
		sdfHour = new SimpleDateFormat("HH:mm:ss");
		sdfDay = new SimpleDateFormat("EEEE");
		
		Timer t = new Timer(1000, this);
	    t.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	    Date d = new Date();
	    
	    if (cbDate.isSelected())
	    	txtDate.setText(sdfDate.format(d));
	    else
	    	txtDate.setText("");
	    
	    txtDisplay.setText(sdfHour.format(d));
	    
	    if (cbDayOfWeek.isSelected())
	    	txtDayOfWeek.setText(sdfDay.format(d));
	    else
	    	txtDayOfWeek.setText("");
	}
}
