import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Menu;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.MenuModel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;

public class AddMenu extends JFrame {

	private JPanel contentPane;
	private JTextField menuno;
	private JTextField menuimg;
	private JTextField menuname;
	private JTextField price;
	JRadioButton rb_cate_banmi = new JRadioButton("반미");
	JRadioButton rb_cate_drink = new JRadioButton("음료");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddMenu frame = new AddMenu();
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
	public AddMenu() {
		super("메뉴 추가");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
/*		JLabel label = new JLabel("메뉴 추가");
		label.setBounds(12, 10, 57, 15);
		contentPane.add(label);*/
		
		JLabel lblNewLabel = new JLabel("상품번호");
		lblNewLabel.setBounds(74, 45, 57, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("상품사진");
		lblNewLabel_1.setBounds(74, 88, 57, 15);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("상품명");
		lblNewLabel_2.setBounds(74, 132, 57, 15);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("가격");
		lblNewLabel_3.setBounds(74, 179, 57, 15);
		contentPane.add(lblNewLabel_3);
		
		menuno = new JTextField();
		menuno.setBounds(143, 42, 116, 21);
		contentPane.add(menuno);
		menuno.setColumns(10);
		
		menuimg = new JTextField();
		menuimg.setBounds(143, 85, 116, 21);
		contentPane.add(menuimg);
		menuimg.setColumns(10);
		
		menuname = new JTextField();
		menuname.setBounds(143, 129, 116, 21);
		contentPane.add(menuname);
		menuname.setColumns(10);
		
		price = new JTextField();
		price.setBounds(143, 176, 116, 21);
		contentPane.add(price);
		price.setColumns(10);
		
		JButton bt_add = new JButton("추가");
		bt_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "추가되었습니다.");
				insert();
				exitFrame();
			}
		});
		bt_add.setBounds(312, 229, 97, 23);
		contentPane.add(bt_add);
		
		JLabel lb_cate = new JLabel("카테고리");
		lb_cate.setBounds(74, 216, 57, 15);
		contentPane.add(lb_cate);
		
		rb_cate_banmi.setBounds(143, 212, 57, 23);
		contentPane.add(rb_cate_banmi);
		
		rb_cate_drink.setBounds(204, 212, 55, 23);
		contentPane.add(rb_cate_drink);
		
		rb_cate_banmi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				rb_cate_drink.setSelected(false);
				
			}
		});
		
		rb_cate_drink.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				rb_cate_banmi.setSelected(false);
				
			}
		});
		
		
	}
	
	void insert(){
		// 각 텍스트 필더의입력값 얻어오기
		// Menu의 멤버 지정
		model.vo.Menu m = new model.vo.Menu();
		m.setMenuno(menuno.getText());
		m.setMenuimg(menuimg.getText());
		m.setMenuname(menuname.getText());
		m.setPrice(price.getText());
		String cate = "";
		if(rb_cate_banmi.isSelected())
		m.setMenucate(rb_cate_banmi.getText());
		else m.setMenucate(rb_cate_drink.getText());
		
		// MenuModel의 입력함수 호출
		try {
			MenuModel mm=new MenuModel();
			mm.insertMenu(m);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	void exitFrame() {
		setVisible(false);
		dispose();
	}
}
