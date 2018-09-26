import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.tree.DefaultTreeCellEditor.DefaultTextField;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import model.Banme_menu;
import model.MenuModel;
import model.vo.Menu;
//import model.vo.Video;
//import jtable.JTableTest.MyTableModel;
import view.BanmiView;
import view.DrinkView;
import java.awt.BorderLayout;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import java.awt.GridLayout;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JList;
import java.awt.Button;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JLayeredPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class Test extends JFrame {

	
	private JPanel contentPane;
	JPanel home;
	JPanel order;
	JPanel manage;
	private JTable OrderTable;
	private JTextField tf_priceTot;
	private JTable tb_menu_mgr;
	private JTable table;
	
	String gosu;
	String spicy;
	
	ArrayList list_orderNum = new ArrayList();
	
	Menu_model Md;
	MyOrderTableModel MGR;
	Banme_menu db = new Banme_menu();
	MenuModel db2;

	JButton [] btn = new JButton[8];
	JButton [] d_btn = new JButton[2];
	
	int total;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test frame = new Test();
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
	
	public Test() {
		super("KIOSK - 김창규 김현수 이윤정 이충석");
		try {
			db.menu_no_catch();
			db2 = new MenuModel();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 724, 517);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		final JPanel home = new JPanel();	//홈화면
		contentPane.add(home, "name_27909494223585");
		home.setLayout(null);
		home.setVisible(true);
		
		
		final JPanel order = new JPanel();	//주문화면
		contentPane.add(order, "name_27911204371365");
		order.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("주문화면");
		lblNewLabel_1.setBounds(12, 10, 57, 15);
		order.add(lblNewLabel_1);
		
		
		JButton btnToHome = new JButton("home");
		btnToHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				order.setVisible(false);
				home.setVisible(true);
			}
		});
		btnToHome.setBounds(608, 6, 77, 23);
		order.add(btnToHome);
		
//----------------------------------------------------- 메뉴 있는 panel		
		JPanel panelOrder = new JPanel();
		panelOrder.setBounds(12, 35, 252, 366);
		order.add(panelOrder);
		order.setVisible(false);
		panelOrder.setLayout(new BorderLayout(0, 0));
		
		JTabbedPane  pane = new JTabbedPane();
		panelOrder.add(pane, BorderLayout.CENTER);
		
		BanmiView banmi = new BanmiView();
		  banmi.setAlwaysOnTop(true);
//			---------------------------------------		  tabbed pane
		
		pane.addTab("반미", banmi);
		banmi.setLayout(null);
		banmi.setLayout(new GridLayout(4,2));
		
		JPanel OrderPanel = new JPanel();
		OrderPanel.setBounds(276, 35, 410, 339);
		order.add(OrderPanel);
		OrderPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_5 = new JLabel("주문내역");
		OrderPanel.add(lblNewLabel_5, BorderLayout.NORTH);
		
		
		OrderPanel.setVisible(true);
		//------------------------------------ 주문 내역 뜨는 테이블
		Md = new Menu_model();
		OrderTable = new JTable(Md);
		OrderPanel.add(new JScrollPane(OrderTable),  BorderLayout.CENTER);

		
		//----------------------------------- 삭제 버튼
		OrderTable.getColumnModel().getColumn(4).setCellRenderer(new ButtonRenderer());
		OrderTable.getColumnModel().getColumn(4).setCellEditor(new ButtonEditor(new JTextField() ));
	
		
		//			--------------------------------------------------------	ComboBox 설정
		TableColumn col_gosu = OrderTable.getColumnModel().getColumn(2);
		TableColumn col_spicy = OrderTable.getColumnModel().getColumn(3);
		
		JComboBox cb_gosu = new JComboBox();
		cb_gosu.addItem("O");
		cb_gosu.addItem("X");
		cb_gosu.addItem("해당없음");
		col_gosu.setCellEditor(new DefaultCellEditor(cb_gosu));
		
		
		JComboBox cb_spicy = new JComboBox();
		cb_spicy.addItem("순한맛");
		cb_spicy.addItem("보통");
		cb_spicy.addItem("매운맛");
		cb_spicy.addItem("해당없음");
		col_spicy.setCellEditor(new DefaultCellEditor(cb_spicy));
		
		Menu gosu_m = new Menu();
		gosu_m.setGosu(gosu);
		
		Menu spicy_m = new Menu();
		spicy_m.setSpicy(spicy);
		
		cb_gosu.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				Menu m = new Menu();
				m.setGosu((String)cb_gosu.getSelectedItem());
			}
		});

		cb_spicy.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				Menu m = new Menu();
				m.setSpicy((String)cb_spicy.getSelectedItem());
			}
		});
		
		//			--------------------------------------------------------	ComboBox 설정 끝
		
		String []btnStr = {
				"src/gui/img/cute/반미오믈렛3900.png",
				"src/gui/img/cute/반미햄치즈4100.png",
				"src/gui/img/cute/닭가슴살스파이시4100.png",
				"src/gui/img/cute/반미야채3900.png",
				"src/gui/img/cute/반미베이컨3900.png",
				"src/gui/img/cute/반미떡갈비4300.png",
				"src/gui/img/cute/닭가슴살데리야끼3900.png",
				"src/gui/img/cute/베트남바게트1500.png"
				
		};
		
		BtnHdlr hdlr = new BtnHdlr();
		for(int i=0; i<btn.length ; i++) {
			btn[i] = new JButton(new ImageIcon(btnStr[i]));
			
			btn[i].setFont(new Font("굴림", Font.PLAIN, 8));
			banmi.add(btn[i]);
			
			btn[i].addActionListener(hdlr);
		}
		
		  // 주문-주문화면-음료메뉴터치
		  DrinkView drink = new DrinkView();
		  drink.setAlwaysOnTop(true);
		  drink.setLayout(new GridLayout(4, 2));

		  String []btn_DrinkStr = {
				  "src/gui/img/cute/coke.jpg",
				  "src/gui/img/cute/sprite.jpg"
		  };
		  d_BtnHdlr d_hdlr = new d_BtnHdlr();
			for(int i=0; i<d_btn.length ; i++) {
				d_btn[i] = new JButton(new ImageIcon(btn_DrinkStr[i]));
				
				d_btn[i].setFont(new Font("굴림", Font.PLAIN, 8));
				drink.add(d_btn[i]);
				
				d_btn[i].addActionListener(d_hdlr);
				
				Menu m = new Menu();
			}
		  
			
		  JButton drink03 = new JButton(new ImageIcon(""));
		  drink.add(drink03);
		  JButton drink04 = new JButton(new ImageIcon(""));
		  drink.add(drink04);
		  JButton drink05 = new JButton(new ImageIcon(""));
		  drink.add(drink05);
		  JButton drink06 = new JButton(new ImageIcon(""));
		  drink.add(drink06);
		  
		pane.addTab("음료", drink);
		
//------------------------------------------------------------------- 메뉴 있는 panel 끝		
		
		JButton bt_finishOrder = new JButton("주문하기");
		bt_finishOrder.setBounds(276, 431, 409, 23);
		order.add(bt_finishOrder);
		
		tf_priceTot = new JTextField();	// 총금액
		tf_priceTot.setBounds(366, 384, 319, 31);
		order.add(tf_priceTot);
		tf_priceTot.setText("총 가격");
		tf_priceTot.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("가격");
		lblNewLabel_6.setFont(new Font("굴림", Font.PLAIN, 20));
		lblNewLabel_6.setBounds(306, 384, 47, 26);
		order.add(lblNewLabel_6);
		
		//----------------------------------- 주문 버튼 클릭 시 열리는 결제창 panel
		JPanel panel_card = new JPanel();
		panel_card.setBounds(12, 35, 252, 366);
		order.add(panel_card);
		panel_card.setLayout(null);
		
		JLabel lb_insertCard = new JLabel("카드를 삽입해주세요");
		lb_insertCard.setBounds(60, 150, 150, 15);
		panel_card.add(lb_insertCard);
		
		JButton bt_done = new JButton("완료");
		bt_done.setBounds(75, 300, 97, 23);
		panel_card.add(bt_done);
		
		bt_done.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				panel_card.setVisible(false);
				panelOrder.setVisible(true);
				JOptionPane.showMessageDialog(bt_done, "결제완료");
				Md.data.clear();
				Md.fireTableDataChanged();
				tf_priceTot.setText(null);
			}
		});
		
		
		//주문하기 버튼 클릭시 발생하는 이벤트 설정------------------------------------------
		bt_finishOrder.addActionListener(new ActionListener() {		
			public void actionPerformed(ActionEvent e) {
				
				panelOrder.setVisible(false);
				panel_card.setVisible(true);

				try {
					db.order_now(Md.data, list_orderNum);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				
				/**
				 * 카드결제 완료하면 주문창 delete
				 */
				Md.data = new ArrayList();
				Md.fireTableDataChanged();
				
			}
		});
		//----------------------------------- 주문 버튼 클릭 시 열리는 결제창 panel 끝

		
//======================================================================	관리화면
		final JPanel manage = new JPanel();
		contentPane.add(manage, "name_27913701387239");
		manage.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("관리화면");
		lblNewLabel_2.setBounds(12, 10, 57, 15);
		manage.add(lblNewLabel_2);
		
		JButton button = new JButton("home");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manage.setVisible(false);
				home.setVisible(true);
			}
		});
		button.setBounds(608, 6, 78, 23);
		manage.add(button);
		
		JPanel panel_2 = new JPanel();		//메뉴 있는 panel
		panel_2.setBounds(12, 28, 119, 426);
		manage.add(panel_2);
		panel_2.setLayout(null);
		
		JButton bt_income_mgr = new JButton("*매출*");
		bt_income_mgr.setFont(new Font("굴림", Font.BOLD, 16));
		bt_income_mgr.setBounds(0, 28, 119, 29);
		panel_2.add(bt_income_mgr);
		
		JButton bt_income_day = new JButton("일매출보기");
		
		bt_income_day.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
		        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		        Calendar c1 = Calendar.getInstance();
		        String str1 = sdf.format(c1.getTime());
		        
		        Calendar c2 = new GregorianCalendar();
		        c2.add(Calendar.DATE, -1); // 오늘날짜로부터 -1
		        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd"); // 날짜 포맷 
		        String str2 = sdf2.format(c2.getTime()); // String으로 저장
		        
		        Calendar c3 = new GregorianCalendar();
		        c3.add(Calendar.DATE, -2); // 오늘날짜로부터 -2
		        SimpleDateFormat sdf3 = new SimpleDateFormat("yyyyMMdd"); // 날짜 포맷 
		        String str3 = sdf3.format(c3.getTime()); // String으로 저장
		        
		        Calendar c4 = new GregorianCalendar();
		        c4.add(Calendar.DATE, -3); // 오늘날짜로부터 -3
		        SimpleDateFormat sdf4 = new SimpleDateFormat("yyyyMMdd"); // 날짜 포맷 
		        String str4 = sdf4.format(c4.getTime()); // String으로 저장
		        
		        Calendar c5 = new GregorianCalendar();
		        c5.add(Calendar.DATE, -4); // 오늘날짜로부터 -4
		        SimpleDateFormat sdf5 = new SimpleDateFormat("yyyyMMdd"); // 날짜 포맷 
		        String str5 = sdf5.format(c5.getTime()); // String으로 저장
		        
		        Calendar c6 = new GregorianCalendar();
		        c6.add(Calendar.DATE, -5); // 오늘날짜로부터 -5
		        SimpleDateFormat sdf6 = new SimpleDateFormat("yyyyMMdd"); // 날짜 포맷 
		        String str6 = sdf6.format(c6.getTime()); // String으로 저장
		        
		        Calendar c7 = new GregorianCalendar();
		        c7.add(Calendar.DATE, -6); // 오늘날짜로부터 -6
		        SimpleDateFormat sdf7 = new SimpleDateFormat("yyyyMMdd"); // 날짜 포맷 
		        String str7 = sdf7.format(c7.getTime()); // String으로 저장

				DefaultCategoryDataset dcd=new DefaultCategoryDataset();
				
				for(int i=0;i<8;i++){
					
				}
				dcd.setValue(588000, "date", str1);
				dcd.setValue(684700, "date", str2);
				dcd.setValue(574300, "date", str3);
				dcd.setValue(626200, "date", str4);
				dcd.setValue(529400, "date", str5);
				dcd.setValue(453200, "date", str6);
				dcd.setValue(752300, "date", str7);
				
				JFreeChart jchart=ChartFactory.createBarChart("daily income", "date", "income", 
						dcd, PlotOrientation.VERTICAL, true, true, false);
				CategoryPlot plot = jchart.getCategoryPlot();
				plot.setRangeGridlinePaint(Color.black);
				
				ChartFrame chartFrm=new ChartFrame("daily income", jchart, true);
				chartFrm.setVisible(true);
				chartFrm.setSize(500, 400);
			
			}
		});
		
		bt_income_day.setBackground(Color.WHITE);
		bt_income_day.setBounds(0, 57, 119, 23);
		panel_2.add(bt_income_day);
		bt_income_day.setVisible(false);
		bt_income_day.setEnabled(false);
		
		JButton bt_income_month = new JButton("월매출보기");
		
		bt_income_month.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DecimalFormat df = new DecimalFormat("00");

		        Calendar currentCalendar = Calendar.getInstance();

		        String str1  = df.format(currentCalendar.get(Calendar.MONTH) + 1);
		        String str2  = df.format(currentCalendar.get(Calendar.MONTH) );
		        String str3  = df.format(currentCalendar.get(Calendar.MONTH) - 1);
		        String str4  = df.format(currentCalendar.get(Calendar.MONTH) - 2);
		        String str5  = df.format(currentCalendar.get(Calendar.MONTH) - 3);
		        String str6  = df.format(currentCalendar.get(Calendar.MONTH) - 4);

				DefaultCategoryDataset dcd=new DefaultCategoryDataset();
				dcd.setValue(3804100, "income", str1);
				dcd.setValue(3462300, "income", str2);
				dcd.setValue(4003400, "income", str3);
				dcd.setValue(3548100, "income", str4);
				dcd.setValue(3892000, "income", str5);
				dcd.setValue(3249000, "income", str6);
				
				JFreeChart jchart=ChartFactory.createBarChart("daily income", "date", "income", 
						dcd, PlotOrientation.VERTICAL, true, true, false);
				CategoryPlot plot = jchart.getCategoryPlot();
				plot.setRangeGridlinePaint(Color.black);
				
				ChartFrame chartFrm=new ChartFrame("daily income", jchart, true);
				chartFrm.setVisible(true);
				chartFrm.setSize(500, 400);
			}
		});
		
		bt_income_month.setBackground(Color.WHITE);
		bt_income_month.setBounds(0, 79, 119, 23);
		panel_2.add(bt_income_month);
		bt_income_month.setVisible(false);
		bt_income_month.setEnabled(false);
		
		JButton bt_income_product = new JButton("상품별매출");
		
		bt_income_product.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultCategoryDataset dcd=new DefaultCategoryDataset();
				dcd.setValue(78800, "menu", "omlet");
				dcd.setValue(53300, "menu", "hamcheese");
				dcd.setValue(58800, "menu", "chickenspicy");
				dcd.setValue(48800, "menu", "vege");
				dcd.setValue(38800, "menu", "bacon");
				dcd.setValue(58800, "menu", "galbi");
				dcd.setValue(47900, "menu", "deriyaki");
				dcd.setValue(36400, "menu", "baguette");
				dcd.setValue(28800, "menu", "coke");
				dcd.setValue(38000, "menu", "cider");
				
				JFreeChart jchart=ChartFactory.createBarChart("today menu income", "menu", "income", 
						dcd, PlotOrientation.VERTICAL, true, true, false);
				CategoryPlot plot = jchart.getCategoryPlot();
				plot.setRangeGridlinePaint(Color.black);
				
				ChartFrame chartFrm=new ChartFrame("daily income", jchart, true);
				chartFrm.setVisible(true);
				chartFrm.setSize(500, 400);
			}
		});
		
		bt_income_product.setBackground(Color.WHITE);
		bt_income_product.setBounds(0, 100, 119, 23);
		panel_2.add(bt_income_product);
		bt_income_product.setVisible(false);
		bt_income_product.setEnabled(false);
		
		
		JButton bt_menu_mgr = new JButton("*메뉴관리*");
		
		bt_menu_mgr.setFont(new Font("굴림", Font.BOLD, 16));
		bt_menu_mgr.setBounds(0, 0, 119, 29);
		panel_2.add(bt_menu_mgr);
		
		JLayeredPane pane_income_mgr = new JLayeredPane();
		pane_income_mgr.setBounds(145, 30, 540, 424);
		manage.add(pane_income_mgr);
		pane_income_mgr.setLayout(null);
		pane_income_mgr.setVisible(false);
		
		JLabel lblNewLabel_7 = new JLabel("매출관리");
		lblNewLabel_7.setBounds(12, 10, 57, 15);
		pane_income_mgr.add(lblNewLabel_7);
		
//		JLabel lbl_chart = new JLabel("New label");
//		lbl_chart.setIcon(new ImageIcon(Test.class.getResource("/gui/img/cute/chart.png")));
//		lbl_chart.setBounds(0, 0, 528, 424);
//		pane_income_mgr.add(lbl_chart);
		
		JLayeredPane pane_menu_mgr = new JLayeredPane();
		pane_menu_mgr.setLayout(null);
		pane_menu_mgr.setBounds(143, 28, 542, 426);
		manage.add(pane_menu_mgr);
		pane_menu_mgr.setVisible(false);
		
		JButton bt_menuAdd = new JButton("추가");
		bt_menuAdd.setBounds(45, 400, 150, 23);
		pane_menu_mgr.add(bt_menuAdd);
		
		JButton bt_menuUpdate = new JButton("수정");
		bt_menuUpdate.setBounds(205, 400, 150, 23);
		pane_menu_mgr.add(bt_menuUpdate);
		
		JButton bt_menuDel = new JButton("삭제");
		bt_menuDel.setBounds(365, 400, 150, 23);
		pane_menu_mgr.add(bt_menuDel);
		
		bt_menuAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				AddMenu add = new AddMenu();
				add.setVisible(true);
				
			}
		});
		
	// -----------------------------------------------------	메뉴관리 테이블
		MGR = new MyOrderTableModel();
		tb_menu_mgr = new JTable(MGR);
		tb_menu_mgr.setBounds(35, 10, 326, 284);
		JScrollPane scrollPane = new JScrollPane(tb_menu_mgr);
		scrollPane.setSize(470, 360);
		scrollPane.setLocation(45, 30);
		pane_menu_mgr.add(scrollPane);
		
		JLabel label_2 = new JLabel("메뉴관리");
		label_2.setBounds(10, 10, 57, 15);
		pane_menu_mgr.add(label_2);
		manage.setVisible(false);
		
		bt_income_mgr.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				bt_income_day.setVisible(true);
				bt_income_day.setEnabled(true);
				
				bt_income_month.setVisible(true);
				bt_income_month.setEnabled(true);
				
				bt_income_product.setVisible(true);
				bt_income_product.setEnabled(true);
				
				pane_menu_mgr.setVisible(false);
				pane_income_mgr.setVisible(true);
				
				
			}
		});
		//-------------------------- 메뉴관리 버튼 클릭시 이벤트
		bt_menu_mgr.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String menu_no = db.catch_menu_no(0);
				ArrayList MenuMGR_list = null;
				try {
				MGR.data = db2.MenuMG_print();
				System.out.println(MGR.data.size());
				MGR.fireTableDataChanged();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		
		bt_menu_mgr.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				bt_income_day.setVisible(false);
				bt_income_month.setVisible(false);
				bt_income_product.setVisible(false);
				
				bt_income_day.setEnabled(false);
				bt_income_month.setEnabled(false);
				bt_income_product.setEnabled(false);
				
				pane_menu_mgr.setVisible(true);
				pane_income_mgr.setVisible(false);
			}
		});
		
		//----------- 관리화면 - 메뉴관리 - 수정 버튼 클릭 시 이벤트
		bt_menuUpdate.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				try {
				db2.updateMenu(MGR.data);
			}catch (Exception e2) {
				e2.printStackTrace();
			}
			JOptionPane.showMessageDialog(bt_done, "수정완료");
		}
		});
		
		//----------- 관리화면 - 메뉴관리 - 삭제 버튼 클릭 시 이벤트
		bt_menuDel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = tb_menu_mgr.getSelectedRow();
				int col=0;
				String mNum = (String)tb_menu_mgr.getValueAt(row, col);
				System.out.println(mNum);
				
				try {
				db2.deleteMenu(mNum);
				System.out.println("삭제완료");
				JOptionPane.showMessageDialog(bt_done, "삭제완료");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}); 
		
		// 홈화면의 <주문>버튼
		JButton btnOrder = new JButton("주문");
		btnOrder.setFont(new Font("굴림", Font.BOLD, 27));
		btnOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				order.setVisible(true);
				home.setVisible(false);
				try {
					 
					Md.data = new ArrayList();
					Md.fireTableDataChanged();

				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		btnOrder.setBounds(158, 177, 130, 69);
		home.add(btnOrder);
		
		JButton btnManager = new JButton("관리");
		btnManager.setFont(new Font("굴림", Font.BOLD, 27));
		btnManager.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Login login = new Login();
				login.setVisible(true);
				
				manage.setVisible(true);
				home.setVisible(false);
			}
		});
		btnManager.setBounds(398, 177, 130, 69);
		home.add(btnManager);
	}
	
	//----------------------------------------- 반미 메뉴 클릭 시 발생하는 이벤트
	class BtnHdlr implements ActionListener{
		public void actionPerformed(ActionEvent ev) {
			JButton evt = (JButton)ev.getSource();
			for(int i=0; i<btn.length; i++) {
				if( evt == btn[i]) {
					try {
					String menu_no = db.catch_menu_no(i);
					db.search_menu(menu_no);
					list_orderNum.add(menu_no);
					ArrayList list=db.order_print(menu_no);
					Md.data.add(list);
					Md.fireTableDataChanged();
					
					int tot=0;
					int tempPrice1=0;
					for(int k=0;k<Md.data.size();k++) {
						ArrayList temp = (ArrayList)Md.data.get(k);
						tempPrice1 = (Integer)temp.get(1);
						tot += tempPrice1;
					}
					total = tot;
					
					tf_priceTot.setText(String.valueOf(total));
					}catch(Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		}
	}
	
	
	//---------------------------------------------- 음료 메뉴 클릭 시 발생하는 이벤트
	class d_BtnHdlr implements ActionListener{
		public void actionPerformed(ActionEvent ev) {
			JButton evt = (JButton)ev.getSource();
			for(int i=0; i<d_btn.length; i++) {
				if( evt == d_btn[i]) {
					try {
						
					String menu_no = db.catch_drink_menu_no(i);
					db.search_menu(menu_no);
					list_orderNum.add(menu_no);
					ArrayList list=db.order_print(menu_no);
					Md.data.add(list);
					Md.fireTableDataChanged();
					
					int tot=0;
					int tempPrice2;
					for(int k=0; k < Md.data.size(); k++) {
						ArrayList temp = (ArrayList)Md.data.get(k);
						tempPrice2 = (Integer)temp.get(1);
						tot += tempPrice2;
					}
					total = tot;
					
					tf_priceTot.setText(String.valueOf(total));
					}catch(Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		}
	}
	
	// ---------------------------------------------	주문화면 테이블
	class Menu_model extends AbstractTableModel {		
		// 데이타(내용)와 제목 관리

		String[] colName = { "상품명", "가격", "고수", "맵기", "삭제"};
		ArrayList data = new ArrayList();

		@Override
		public int getRowCount() {
			return data.size();
		}

		@Override
		public int getColumnCount() {
			return colName.length;
		}

		@Override
		public Object getValueAt(int row, int col) {
			ArrayList temp = (ArrayList) data.get(row);
			return temp.get(col);
		}

		public String getColumnName(int col) {
			return colName[col];
		}

		public boolean isCellEditable(int row, int col) {
			if(col==0 || col==1) return false;
			return true;
			
		}
		public void setValueAt(Object value, int row, int col) {
	        ArrayList temp = (ArrayList)data.get(row);
	        temp.set(col, value);
	        fireTableCellUpdated(row, col);
	    }

		public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }

	}
	// --------------------------------------------------------  관리화면 테이블
	class MyOrderTableModel extends AbstractTableModel {		
		// 데이타(내용)와 제목 관리

		String[] colName = { "상품번호", "상품사진", "상품명", "가격" };
		ArrayList data = new ArrayList();

		@Override
		public int getRowCount() {
			return data.size();
		}

		@Override
		public int getColumnCount() {
			return colName.length;
		}

		@Override
		public Object getValueAt(int row, int col) {
			ArrayList temp = (ArrayList) data.get(row);
			return temp.get(col);
		}

		public String getColumnName(int col) {
			return colName[col];
		}
		

		//public Class getColumnClass(int c) {
	    //      return getValueAt(0, c).getClass();
	    //    }
	 

		public boolean isCellEditable(int row, int col) {
				return true;
		}
		
		 public void setValueAt(Object value, int row, int col) {
			 	ArrayList temp = (ArrayList)data.get(row);
		        temp.set(col, value);
		        fireTableCellUpdated(row, col);
		    }

	}
	
	class ButtonRenderer extends JButton implements TableCellRenderer{
		public ButtonRenderer() {
			setOpaque(true);
		}

		@Override

		public Component getTableCellRendererComponent(JTable table, Object obj,
				boolean isSelected, boolean hasFocus,
				int row, int col) {
			setText("삭제");

			return this;
		}
	}

	

	class ButtonEditor extends DefaultCellEditor{

		protected JButton btn;
		private String lbl;
		private Boolean clicked=false;
		int delRow;

		public ButtonEditor(JTextField txt) {
			super(txt);
			btn = new JButton();
			btn.setOpaque(true);
			btn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					fireEditingStopped();
				}
			});
		}

		@Override

		public Component getTableCellEditorComponent(JTable table, Object obj,
				boolean isSelected, int row, int col) {
			lbl=(obj==null) ? "":obj.toString();
			btn.setText(lbl);
			clicked = true;
			delRow = row;
			return btn;
		}

		@Override
		public boolean stopCellEditing() {
			clicked=false;
			return super.stopCellEditing();
		}

		@Override
		protected void fireEditingStopped() {
			super.fireEditingStopped();
			if(clicked) {
				JOptionPane.showMessageDialog(btn,"삭제되었습니다.");
				//-----------------------------------------------리스트에서 삭제하는 이벤트 걸기
					try {
						System.out.println("delRow:"+ delRow);
						
						ArrayList temp = (ArrayList)Md.data.get(delRow);
						int delPrice = (Integer)temp.get(1);
						total -= delPrice;
						tf_priceTot.setText(String.valueOf(total));
					
						Md.data.remove(delRow);
						Md.fireTableDataChanged();
						
						System.out.println("Md.data.size():"+Md.data.size());
						}catch(Exception ex) {
							ex.printStackTrace();
						}
					}
			
			clicked=false;
		}
	}

	
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
