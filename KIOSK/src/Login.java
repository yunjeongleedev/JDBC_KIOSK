import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField tf_id;
	private JPasswordField pf_pw;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		
		super("LOGIN");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ID                     :");
		lblNewLabel.setBounds(61, 72, 107, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("PASSWORD	  :");
		lblNewLabel_1.setBounds(61, 126, 79, 15);
		contentPane.add(lblNewLabel_1);
		
		tf_id = new JTextField();
		tf_id.setBounds(162, 69, 116, 21);
		contentPane.add(tf_id);
		tf_id.setColumns(10);
		
		JButton bt_login = new JButton("LOG IN");
		bt_login.setBounds(162, 180, 97, 23);
		contentPane.add(bt_login);
		
		pf_pw = new JPasswordField();
		pf_pw.setBounds(162, 123, 116, 21);
		contentPane.add(pf_pw);
		
		String id = "myteam";
		String pw = "1234";
		
		
		bt_login.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(tf_id.getText().equals(id) && pf_pw.getText().equals(pw)) {
					JOptionPane.showMessageDialog(null, "Login Succesful");
					
					//Test main = new Test();
					//main.setVisible(false);
					//main.manage.setVisible(true);
					
					exitFrame();
				}
				else {
					JOptionPane.showMessageDialog(null, "Login Denied");
					tf_id.setText(null);
					pf_pw.setText(null);
				}
				
			}
		});
	}

	void exitFrame() {
		setVisible(false);
		dispose();
	}
}
