package org.uniken.dto;

import java.io.Serializable;

public class Corporate implements Serializable {
	//All fields 
	private Integer corporateRID;
	private Long accountNo;
	
	//Setter and Getter methods
	public Integer getCorporateRID() {
		return corporateRID;
	}
	public void setCorporateRID(Integer corporateRID) {
		this.corporateRID = corporateRID;
	}
	public Long getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(Long accountNo) {
		this.accountNo = accountNo;
	}
	@Override
	public String toString() {
		return "Corporate [corporateRID=" + corporateRID + ", accountNo=" + accountNo + "]";
	}
	
}
