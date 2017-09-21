package com.yucheng.cmis.batch.complex.reader.pojo;

import java.util.List;

import com.yucheng.cmis.batch.common.pojo.TCredit;


public class TCreditComplex {

	private TCredit tCredit;
	
	private List<TCredit> tCreditList;

	public TCredit gettCredit() {
		return tCredit;
	}

	public void settCredit(TCredit tCredit) {
		this.tCredit = tCredit;
	}

	public List<TCredit> gettCreditList() {
		return tCreditList;
	}

	public void settCreditList(List<TCredit> tCreditList) {
		this.tCreditList = tCreditList;
	}
	
}
