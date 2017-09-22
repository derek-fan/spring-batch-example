package com.yucheng.cmis.batch.complex.readers.pojo;

import java.math.BigDecimal;
import java.util.List;

import com.yucheng.cmis.batch.common.pojo.TTradeRecord;

public class TCreditComplex {

	private List<TTradeRecord> ttradeRecordList;

	private String id;

	private String accountid;

	private String name;

	private BigDecimal amount;

	private String date;

	private String address;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getAccountid() {
		return accountid;
	}

	public void setAccountid(String accountid) {
		this.accountid = accountid == null ? null : accountid.trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date == null ? null : date.trim();
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address == null ? null : address.trim();
	}

	public List<TTradeRecord> getTtradeRecordList() {
		return ttradeRecordList;
	}

	public void setTtradeRecordList(List<TTradeRecord> ttradeRecordList) {
		this.ttradeRecordList = ttradeRecordList;
	}
}
