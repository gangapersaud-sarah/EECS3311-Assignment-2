import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.SwingConstants;

public class View_Bookings extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View_Bookings frame = new View_Bookings();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public View_Bookings() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblViewBooking = new JLabel("View Booking");
		lblViewBooking.setHorizontalAlignment(SwingConstants.CENTER);
		lblViewBooking.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblViewBooking.setBackground(Color.LIGHT_GRAY);
		lblViewBooking.setBounds(39, 0, 701, 100);
		contentPane.add(lblViewBooking);
		
		JButton btnNewButton = new JButton("Extend Booking");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(63, 416, 200, 100);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Edit Booking");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_1.setBounds(288, 416, 200, 100);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Cancel Booking");
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_2.setBounds(513, 416, 200, 100);
		contentPane.add(btnNewButton_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(63, 131, 650, 245);
		contentPane.add(scrollPane);
		
		table = new JTable();
		DefaultTableModel tableModel = new DefaultTableModel(
				new Object[][] {
					
				},
				new String[] {
					"License Plate", "Parking Lot", "Parking Space", "date", "time", "duration"
				}
			) 
		{
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		    @Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		};
		
		ArrayList<ArrayList<String>> returnList = new ArrayList<ArrayList<String>>();
		returnList = ReadCSV.allBookings("Booking.txt");
		String[] list = new String[6];
		for (int i = 0; i < returnList.size(); i++) {
            for (int j = 0; j < returnList.get(i).size()-1; j++) {
                list[j] = returnList.get(i).get(j+1);
            }
            tableModel.addRow(list);
        }
		
		//String[] list2 = {"1", "2", "3", "4", "5", "6"};
		//tableModel.addRow(l);
		
		scrollPane.setViewportView(table);
		table.setModel(tableModel);
		
		textField = new JTextField();
		textField.setBackground(new Color(192, 192, 192));
		textField.setBounds(0, 0, 800, 100);
		contentPane.add(textField);
		textField.setColumns(10);
		table.getColumnModel().getColumn(4).setPreferredWidth(107);
	}
}
