package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.vo.Menu;
import view.BanmiView;
import view.DrinkView;

public class MenuModel {
	String menuimg;
	String menu_no;
	int price;
	int menu_length;
	String menuname;
	String menu_num[] = new String[20];

	public MenuModel() throws Exception{
		
		// 1. 드라이버로딩
		Class.forName("oracle.jdbc.driver.OracleDriver");		
	}
	String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	String user = "myteam";
	String pass = "1234";
	
	
	public void insertMenu(Menu dao) throws Exception{
		
		// 2. Connection 연결객체 얻어오기
		Connection con = DriverManager.getConnection(url, user, pass);
		
		// 3. sql 문장 만들기
		String sql = "INSERT INTO MENU(MENUNO, MENUIMG, MENUNAME, PRICE, MENUCATE) "
				+ " VALUES(?,?,?,?,?)";
		
		// 4. sql 전송객체 (PreparedStatement)		
		PreparedStatement st = con.prepareStatement(sql);
		
		st.setString(1, dao.getMenuno());
		st.setString(2, dao.getMenuno());
		//st.setString(2, null);
		st.setString(3, dao.getMenuname());
		st.setString(4, dao.getPrice());
		st.setString(5, dao.getMenucate());
		
		// 5. sql 전송
		st.executeUpdate();
		
		// 6. 닫기 
		st.close();
		con.close();
	}
	
	public int updateMenu(ArrayList list) throws Exception{
		
		int result=0;
		Connection con = DriverManager.getConnection(url, user, pass);
		
		// 3. sql 문장 만들기
		String sql = "UPDATE MENU SET  MENUNAME=?, PRICE=? WHERE MENUNO=? ";

		// 4. sql 전송객체 (PreparedStatement)		
		PreparedStatement st = con.prepareStatement(sql);
		
		for(int i=0; i<list.size(); i++){
			// [화면] 0:상품번호, 1: 상품사진, 2:싱품명, 3: 가격
			ArrayList temp = (ArrayList)list.get(i);

			st.setString(1, (String)temp.get(2));
			st.setInt(2, Integer.parseInt((String)temp.get(3)));
			st.setString(3, (String)temp.get(0));
		
			// 5. sql 전송
			st.executeUpdate();
			System.out.println("메뉴수정");
		}
		
		// 6. 닫기 
		st.close();
		con.close();
		
		return result;
	}
	
	public void deleteMenu(String mNum) throws Exception{
			
			//2. 연결객체
			Connection con = DriverManager.getConnection(url, user, pass);
				
			String sql = "DELETE FROM MENU  WHERE MENUNO=? ";

			//4. 전송객체
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, mNum);

			//5. 
			st.executeUpdate();
			System.out.println("메뉴삭제");
			
			//7. 닫기
			st.close();
			con.close();
		}
	
	public int dailyincome(ArrayList list) throws Exception{
		int result=0;
		Connection con = DriverManager.getConnection(url, user, pass);
		
		String sql="select sum(m.price) sum, to_char(orderdate,'YYYY-MM-DD') orderdate from menu m, orderer o where m.menuno=o.menuno group by to_char(orderdate,'YYYY-MM-DD') ";

		//4. 전송객체
		PreparedStatement st = con.prepareStatement(sql);

		//5. 
		st.executeUpdate();
		
		//7. 닫기
		st.close();
		con.close();
		return result;
	}
	
	public ArrayList MenuMG_print() throws Exception {
		
		Connection con = DriverManager.getConnection(url, user, pass);
		PreparedStatement st = null;
		ArrayList MenuMGR_list  = new ArrayList();
		int i =0;
		String sql = null;
		try {

			sql = "  SELECT MENUNO, MENUIMG, MENUNAME, PRICE FROM MENU ";
			st = con.prepareStatement(sql);
			
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				ArrayList temp = new ArrayList();
				temp.add(rs.getString("menuno"));				
				temp.add(rs.getString("menuimg"));
				temp.add(rs.getString("menuname"));
				temp.add(rs.getString("price"));
			
				MenuMGR_list.add(temp);
			}

		} // 6. 닫기
		finally {
			st.close();
			con.close();

		}
		return MenuMGR_list;
	}
	
}
