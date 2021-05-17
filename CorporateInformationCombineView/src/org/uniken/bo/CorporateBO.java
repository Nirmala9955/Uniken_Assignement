package org.uniken.bo;

public class CorporateBO {
	//All fields 
	private Integer corporateRID;
	private String corporateName;
	private String corporateId;
	private Long accountNo;
	
	//Setter and Getter methods
	public Integer getCorporateRID() {
		return corporateRID;
	}
	public void setCorporateRID(Integer corporateRID) {
		this.corporateRID = corporateRID;
	}
	public String getCorporateName() {
		return corporateName;
	}
	public void setCorporateName(String corporateName) {
		this.corporateName = corporateName;
	}
	public String getCorporateId() {
		return corporateId;
	}
	public void setCorporateId(String corporateId) {
		this.corporateId = corporateId;
	}
	public Long getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(Long accountNo) {
		this.accountNo = accountNo;
	}
}
