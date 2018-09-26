package model.vo;

public class LastOrderer {
	String ordernum;
	char isadd;
	char isselect;
	int totalprice;
	String odate;
	public String getOrdernum() {
		return ordernum;
	}
	public void setOrdernum(String ordernum) {
		this.ordernum = ordernum;
	}
	public char getIsadd() {
		return isadd;
	}
	public void setIsadd(char isadd) {
		this.isadd = isadd;
	}
	public char getIsselect() {
		return isselect;
	}
	public void setIsselect(char isselect) {
		this.isselect = isselect;
	}
	public int getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(int totalprice) {
		this.totalprice = totalprice;
	}
	public String getOdate() {
		return odate;
	}
	public void setOdate(String odate) {
		this.odate = odate;
	}
	
	
}
