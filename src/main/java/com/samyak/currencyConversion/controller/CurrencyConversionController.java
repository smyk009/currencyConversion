package com.samyak.currencyConversion.controller;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.samyak.currencyConversion.model.CurrencyConversionModel;
import com.samyak.currencyConversion.service.CurrencyExchangeServiceProxy;

@RestController
public class CurrencyConversionController {

	@Autowired
	CurrencyExchangeServiceProxy currencyExchangeServiceProxy;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionModel convertCurrency(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {

		CurrencyConversionModel response = currencyExchangeServiceProxy.retrieveExchangeValue(from, to);
		return new CurrencyConversionModel(response.getId(), from, to, response.getConversionMultiple(), quantity,
				quantity.multiply(response.getConversionMultiple()), response.getPort());
	}

}
