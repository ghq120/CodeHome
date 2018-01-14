package com.ghq.model.entity;

public class Customer {
	//��ʶ����
	private int id;
	//�ͻ���
	private String custName;
	//��ʵ��
	private String custRealName;
	//����
	private String custPassword;
	//�ֻ�
	private String custTel;
	//����
	private String custEmail;
	//��������
	private String custPostcode;
	//֤������
	private String custCardtype;
	//֤����
	private String custCardno;
	//���ڳ���
	private String custCity;
	//��ַ
	private String custAdd;
	//�ȼ�
	private int custGrade;
	//֮ǰ���ѵĽ��
	private double custAmount;
	//�Ƿ񱻶��� 0 ���� 1 δ����
	private int custFreeze;
	public Customer(int id, String custName, String custRealName,
			String custPassword, String custTel, String custEmail,
			String custPostcode, String custCardtype, String custCardno,
			String custCity, String custAdd, int custGrade, double custAmount,
			int custFreeze) {
		super();
		this.id = id;
		this.custName = custName;
		this.custRealName = custRealName;
		this.custPassword = custPassword;
		this.custTel = custTel;
		this.custEmail = custEmail;
		this.custPostcode = custPostcode;
		this.custCardtype = custCardtype;
		this.custCardno = custCardno;
		this.custCity = custCity;
		this.custAdd = custAdd;
		this.custGrade = custGrade;
		this.custAmount = custAmount;
		this.custFreeze = custFreeze;
	}
	public Customer(String custName, String custRealName,
			String custPassword, String custTel, String custEmail,
			String custPostcode, String custCardtype, String custCardno,
			String custCity, String custAdd, int custGrade, double custAmount,
			int custFreeze) {
		
		this.custName = custName;
		this.custRealName = custRealName;
		this.custPassword = custPassword;
		this.custTel = custTel;
		this.custEmail = custEmail;
		this.custPostcode = custPostcode;
		this.custCardtype = custCardtype;
		this.custCardno = custCardno;
		this.custCity = custCity;
		this.custAdd = custAdd;
		this.custGrade = custGrade;
		this.custAmount = custAmount;
		this.custFreeze = custFreeze;
	}
	public Customer(String custName, String custRealName,
			String custPassword, String custTel, String custEmail) {
		
		this.custName = custName;
		this.custRealName = custRealName;
		this.custPassword = custPassword;
		this.custTel = custTel;
		this.custEmail = custEmail;
	}
	
	public Customer(String custName,String custPassword) {
		this.custName = custName;
		this.custPassword = custPassword;
	}
	
	public Customer() {
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getCustRealName() {
		return custRealName;
	}
	public void setCustRealName(String custRealName) {
		this.custRealName = custRealName;
	}
	public String getCustPassword() {
		return custPassword;
	}
	public void setCustPassword(String custPassword) {
		this.custPassword = custPassword;
	}
	public String getCustTel() {
		return custTel;
	}
	public void setCustTel(String custTel) {
		this.custTel = custTel;
	}
	public String getCustEmail() {
		return custEmail;
	}
	public void setCustEmail(String custEmail) {
		this.custEmail = custEmail;
	}
	public String getCustPostcode() {
		return custPostcode;
	}
	public void setCustPostcode(String custPostcode) {
		this.custPostcode = custPostcode;
	}
	public String getCustCardtype() {
		return custCardtype;
	}
	public void setCustCardtype(String custCardtype) {
		this.custCardtype = custCardtype;
	}
	public String getCustCardno() {
		return custCardno;
	}
	public void setCustCardno(String custCardno) {
		this.custCardno = custCardno;
	}
	public String getCustCity() {
		return custCity;
	}
	public void setCustCity(String custCity) {
		this.custCity = custCity;
	}
	public String getCustAdd() {
		return custAdd;
	}
	public void setCustAdd(String custAdd) {
		this.custAdd = custAdd;
	}
	public int getCustGrade() {
		return custGrade;
	}
	public void setCustGrade(int custGrade) {
		this.custGrade = custGrade;
	}
	public double getCustAmount() {
		return custAmount;
	}
	public void setCustAmount(double custAmount) {
		this.custAmount = custAmount;
	}
	public int getCustFreeze() {
		return custFreeze;
	}
	public void setCustFreeze(int custFreeze) {
		this.custFreeze = custFreeze;
	}
}
