package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;

import model.vo.Menu;
//import model.vo.Video;

public class Banme_menu {

	String url = "jdbc:oracle:thin:@localhost:1521:orcl"; // 70.12.115.141
	String user = "myteam";
	String pass = "1234";


	int count =0; 
	int price;
	int total_price2;
	int menu_length;
	String menuname;
	String menu_num[] = new String[20];
	
	
	public Banme_menu() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	public void Banme_menu_order(String menu[], int count, boolean spicy, boolean gosu) {

	}

	public void search_menu(String menu_no) throws Exception {
		Banme_menu bm = null;
		ArrayList Blist = new ArrayList();
		Connection con = DriverManager.getConnection(url, user, pass);
		PreparedStatement st = null;
		String sql = null;
		try {
			System.out.println("asd");
			sql = "select price,menuname  from menu  where menuno = ? ";
			st = con.prepareStatement(sql);

			st.setString(1, menu_no);

			// 5. sql 전송
			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				price = rs.getInt("price");
				menuname = rs.getString("menuname"); //이름 필요할지 의문이지만 일단 넣었습니다.
				//메뉴 넘버에 맞는 가격과 이름 저장
			}
		} // 6. 닫기
		finally {
			st.close();
			con.close();
		}

	}

	public void menu_no_catch() throws Exception {
		Banme_menu bm = null;
		ArrayList Blist = new ArrayList();
		int i =0;
		Connection con = DriverManager.getConnection(url, user, pass);
		PreparedStatement st = null;
		String sql = null;
		try {
			System.out.println("asd");
			sql = "select menuno from menu   ORDER BY  menuno";
			st = con.prepareStatement(sql);

			// 5. sql 전송
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				menu_num[i] = (rs.getString("menuno"));
				i++;
			}
		} // 6. 닫기
		finally {
			st.close();
			con.close();
		}
	}

	public String catch_menu_no(int i) {
		return menu_num[i];
	}
	
	public String catch_drink_menu_no(int i) {
		return menu_num[i+8];
	}

	public ArrayList order_print(String menu_no) throws Exception {
	
		Connection con = DriverManager.getConnection(url, user, pass);
		PreparedStatement st = null;
		ArrayList temp = new ArrayList();
		int i =0;
		String sql = null;
		try {

			sql = "select  menuname as menuname  ,price as price " + 
					"from menu where menuno=?";
			st = con.prepareStatement(sql);
			st.setString(1, menu_no);
			System.out.println(">>"+menu_no);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
							
				temp.add(rs.getString("menuname"));		
				temp.add(rs.getInt("price"));		
				temp.add("고수 선택");
				temp.add("맵기 선택");
				// ==> 고수 체크가 안됨  다같이 바뀌게됨
				temp.add("삭제");
			}

		} // 6. 닫기
		finally {
			st.close();
			con.close();
		}
		return temp;
	}


	public void order_now(ArrayList data, ArrayList list_orderNum) throws Exception{
		
		Connection con = DriverManager.getConnection(url, user, pass);
		
		String sql = "INSERT INTO orderer (ONUMBER, ORDERNUM, MENUNO, ISSADD, ISSELECT, ORDERDATE)"
				+ "  VALUES (onumber.nextval, seq_ordernum.nextval, ?, ?, ?, sysdate)";
		
		PreparedStatement st = con.prepareStatement(sql);
		for(int i=0; i<data.size(); i++) {
			ArrayList temp = (ArrayList)data.get(i);
			st.setString(1, (String)list_orderNum.get(i));
			
			String setStr2=null;
			Boolean find_drink = ((String)temp.get(0)).equals("콜라") || ((String)temp.get(0)).equals("사이다") ? true : false;
			if(find_drink) setStr2 = "해당없음";
			else setStr2 = ((String)temp.get(2)).equals("고수 선택") ? "O" : (String)temp.get(2);
			System.out.println(find_drink+"/"+setStr2);
			st.setString(2, setStr2);
			//st.setString(2, ((String)temp.get(2)).equals("고수 선택") ? "O" : (String)temp.get(2));
			
			String setStr3=null;
			if(find_drink) setStr3 = "해당없음";
			else setStr3 = ((String)temp.get(3)).equals("맵기 선택") ? "보통" : (String)temp.get(3);
			st.setString(3, setStr3);
			//st.setString(3, ((String)temp.get(3)).equals("맵기 선택") ? "보통" : (String)temp.get(3));
			
			st.executeUpdate();
			
		}
		st.close();
		con.close();
	}
	
}
