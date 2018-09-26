package model.vo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import model.vo.Menu;
import model.vo.Orderer;

public class Orderer {
	String onumber;
	String ordernum;
	String menuno;
	String count;
	public String getOnumber() {
		return onumber;
	}
	public void setOnumber(String onumber) {
		this.onumber = onumber;
	}
	public String getOrdernum() {
		return ordernum;
	}
	public void setOrdernum(String ordernum) {
		this.ordernum = ordernum;
	}
	public String getMenuno() {
		return menuno;
	}
	public void setMenuno(String menuno) {
		this.menuno = menuno;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	
	
}
