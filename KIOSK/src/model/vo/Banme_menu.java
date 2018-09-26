package model.vo;

public class Banme_menu {
	int count =0; 
	int price;
	int total_price2;
	int menu_length;
	int ordernum =0;
	String menuname;
	String menu_num[] = new String[8];
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getTotal_price2() {
		return total_price2;
	}
	public void setTotal_price2(int total_price2) {
		this.total_price2 = total_price2;
	}
	public int getMenu_length() {
		return menu_length;
	}
	public void setMenu_length(int menu_length) {
		this.menu_length = menu_length;
	}
	public int getOrdernum() {
		return ordernum;
	}
	public void setOrdernum(int ordernum) {
		this.ordernum = ordernum;
	}
	public String getMenuname() {
		return menuname;
	}
	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}
	public String[] getMenu_num() {
		return menu_num;
	}
	public void setMenu_num(String[] menu_num) {
		this.menu_num = menu_num;
	}
}
