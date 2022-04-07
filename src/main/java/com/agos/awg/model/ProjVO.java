package com.agos.awg.model;

public class ProjVO {

	private int proj_idx;
	private int proj_code;
	private String proj_nm;
	private int proj_amount;
	private String proj_buyer;
	private String proj_start_date;
	private String proj_end_date;
	
	
	public int getProj_idx() {
		return proj_idx;
	}
	public void setProj_idx(int proj_idx) {
		this.proj_idx = proj_idx;
	}
	public int getProj_code() {
		return proj_code;
	}
	public void setProj_code(int proj_code) {
		this.proj_code = proj_code;
	}
	public String getProj_nm() {
		return proj_nm;
	}
	public void setProj_nm(String proj_nm) {
		this.proj_nm = proj_nm;
	}

	public int getProj_amount() {
		return proj_amount;
	}
	public void setProj_amount(int proj_amount) {
		this.proj_amount = proj_amount;
	}
	public String getProj_buyer() {
		return proj_buyer;
	}
	public void setProj_buyer(String proj_buyer) {
		this.proj_buyer = proj_buyer;
	}
	public String getProj_start_date() {
		return proj_start_date;
	}
	public void setProj_start_date(String proj_start_date) {
		this.proj_start_date = proj_start_date;
	}
	public String getProj_end_date() {
		return proj_end_date;
	}
	public void setProj_end_date(String proj_end_date) {
		this.proj_end_date = proj_end_date;
	}	
	
}
