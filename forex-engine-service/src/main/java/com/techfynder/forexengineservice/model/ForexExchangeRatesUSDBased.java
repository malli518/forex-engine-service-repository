package com.techfynder.forexengineservice.model;

import java.util.Date;
import java.util.Map;

public class ForexExchangeRatesUSDBased {

	private Map<String, Float> rates;

	public Map<String, Float> getRates() {
		return rates;
	}

	public void setRates(Map<String, Float> rates) {
		this.rates = rates;
	}

	private String base;
	private Date date;

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
