package com.agos.awg.model;

public class CardVO {
	private String card_idx;	// 카드 계정 인덱스
	private String card_no;		// 카드 번호
	private String card_id;		// 카드  관리 졔정
	private Integer card_pw;	// 카드 비밀번호?
	private String card_name;	// 카드 명의
	private String card_ep; 	// 카드 유효 기간
	private int card_type; 	// 카드 종류 (0: 법인/ 1: 개인)
	
	/** Getter/Setter **/	
	public String getCard_idx() {
		return card_idx;
	}
	public void setCard_idx(String card_idx) {
		this.card_idx = card_idx;
	}
	public String getCard_no() {
		return card_no;
	}
	public void setCard_no(String card_no) {
		this.card_no = card_no;
	}
	public String getCard_id() {
		return card_id;
	}
	public void setCard_id(String card_id) {
		this.card_id = card_id;
	}
	public Integer getCard_pw() {
		return card_pw;
	}
	public void setCard_pw(Integer card_pw) {
		this.card_pw = card_pw;
	}
	public String getCard_name() {
		return card_name;
	}
	public void setCard_name(String card_name) {
		this.card_name = card_name;
	}
	public String getCard_ep() {
		return card_ep;
	}
	public void setCard_ep(String card_ep) {
		this.card_ep = card_ep;
	}
	public int getCard_type() {
		return card_type;
	}
	public void setCard_type(int card_type) {
		this.card_type = card_type;
	}	
}
