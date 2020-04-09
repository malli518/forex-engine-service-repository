package com.techfynder.forexengineservice.resource;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.techfynder.forexengineservice.model.ExangeAmount;
import com.techfynder.forexengineservice.model.ExchangeValue;
import com.techfynder.forexengineservice.model.ForexExchangeRatesUSDBased;
import com.techfynder.forexengineservice.model.ForexExchangeCurrency;
import com.techfynder.forexengineservice.repository.ForexExchangeRepository;

@RestController()
@RequestMapping("/forex")
public class ForexEngineController {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private ForexExchangeRepository forexExchangeRepository;

	@RequestMapping("/getCurrencyValue/{currecnyType}")
	public ForexExchangeCurrency getForexOnUSDBased(@PathVariable("currecnyType") String currecnyType) {
		String url="https://api.exchangeratesapi.io/latest?base=USD&symbols="+currecnyType;		
		ForexExchangeRatesUSDBased forexExchange = restTemplate.getForObject(url, ForexExchangeRatesUSDBased.class);
		ForexExchangeCurrency forexExchangeType=new ForexExchangeCurrency();
		forexExchangeType.setId(currecnyType);
		forexExchangeType.setRates(forexExchange.getRates());
		forexExchangeType = forexExchangeRepository.save(forexExchangeType);
		return forexExchangeType;		
	}
	
	@RequestMapping("/exchangecurrency")
	public ExangeAmount getExchangeCurrencyBasedOnUSD(@RequestBody ExchangeValue exchangeValue) {
		
		ExangeAmount exangeAmount=new ExangeAmount();
		//FormCurrency
		String urlForm="https://api.exchangeratesapi.io/latest?base=USD&symbols="+exchangeValue.getFrom();	
		System.out.println("urlForm   ::   "+urlForm );
		ForexExchangeRatesUSDBased formForexExchangeRatesUSDBased = restTemplate.getForObject(urlForm, ForexExchangeRatesUSDBased.class);
		Map<String, Float> formRates=formForexExchangeRatesUSDBased.getRates();
		float formCurrencyValueBasedOnUSD=formRates.get(exchangeValue.getFrom());
		float amount=Float.parseFloat(exchangeValue.getAmount());
		amount=amount/formCurrencyValueBasedOnUSD;
		
		//ToCurrency
		String urlTo="https://api.exchangeratesapi.io/latest?base=USD&symbols="+exchangeValue.getTo();	
		System.out.println("urlTo   ::   "+urlTo );
		ForexExchangeRatesUSDBased toForexExchangeRatesUSDBased = restTemplate.getForObject(urlTo, ForexExchangeRatesUSDBased.class);
		Map<String, Float> toRates=toForexExchangeRatesUSDBased.getRates();
		float toCurrencyValueBasedOnUSD=toRates.get(exchangeValue.getTo());		
		amount=amount*toCurrencyValueBasedOnUSD;
		
		//Exchange Amount
		exangeAmount.setExchangeAmount(amount);
		return exangeAmount;		
	}
	
	

}
