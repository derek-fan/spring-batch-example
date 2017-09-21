package com.yucheng.cmis.batch.complex.writers.pojo;

import java.io.Serializable;
import java.math.BigDecimal;

import com.yucheng.cmis.batch.complex.reader.pojo.ComplextPojo;

public class TCredit extends ComplextPojo implements Serializable{
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
        this.map.put("id", id);
    }

    public String getAccountid() {
        return accountid;
    }

    public void setAccountid(String accountid) {
        this.accountid = accountid == null ? null : accountid.trim();
        this.map.put("accountid", accountid);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
        this.map.put("name", name);
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
        this.map.put("amount", amount);
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date == null ? null : date.trim();
        this.map.put("date", date);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
        this.map.put("address", address);
    }
}