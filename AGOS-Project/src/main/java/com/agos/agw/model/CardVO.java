package com.agos.agw.model;

public class CardVO {
	private String card_idx;	// ī�� ���� �ε���
	private String card_no;		// ī�� ��ȣ
	private String card_id;		// ī��  ���� ����
	private Integer card_pw;	// ī�� ��й�ȣ?
	private String card_name;	// ī�� ����
	private String card_ep; 	// ī�� ��ȿ �Ⱓ
	private int card_type; 	// ī�� ���� (0: ����/ 1: ����)
	
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
