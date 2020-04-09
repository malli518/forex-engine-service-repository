package com.techfynder.forexengineservice.model;

import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "forexexchangecurrency")
public class ForexExchangeCurrency {

	@Id
	private String id;

	private Map<String, Float> rates;

	public Map<String, Float> getRates() {
		return rates;
	}

	public void setRates(Map<String, Float> rates) {
		this.rates = rates;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
