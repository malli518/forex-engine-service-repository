package com.techfynder.forexengineservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.techfynder.forexengineservice.model.ForexExchangeRatesUSDBased;
import com.techfynder.forexengineservice.model.ForexExchangeCurrency;
//https://bezkoder.com/spring-boot-mongodb-crud/
public interface ForexExchangeRepository extends MongoRepository<ForexExchangeCurrency, String> {
	ForexExchangeCurrency save(ForexExchangeCurrency forexExchange);
}
