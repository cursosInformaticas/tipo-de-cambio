package com.apirest.msvc.service;

import com.apirest.msvc.cliente.TipoCambioClient;
import com.apirest.msvc.dao.ConversionDAO;
import com.apirest.msvc.model.Conversion;
import com.apirest.msvc.model.ConversionRate;
import com.apirest.msvc.model.ConversionRequest;
import com.apirest.msvc.model.TipoCambioResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ConversionServiceImpl implements ConversionService{

    @Autowired
    private ConversionDAO conversionRepository;
    @Autowired
    private TipoCambioClient exchangeRateClient;
    private Set<String> availableCurrencies = new HashSet<>();
    String monedaBase="";
    public ConversionServiceImpl(TipoCambioClient exchangeRateClient) {
        this.exchangeRateClient = exchangeRateClient;
        //refreshAvailableCurrencies(this.monedaBase);
    }
    public Conversion convertCurrency(String monedaBase,ConversionRequest request) {
        List<ConversionRate> availableCurrencies = listAvailableCurrencies();
        if (isCurrencyValid(request.getMonedaOrigen(), availableCurrencies) && isCurrencyValid(request.getMonedaDestino(), availableCurrencies)) {
            TipoCambioResponse exchangeRates = exchangeRateClient.getLatestExchangeRates(monedaBase);
            double exchangeRate = exchangeRates.getRates().get(request.getMonedaDestino());
            double convertedAmount = request.getMonto() * exchangeRate;

            Conversion conversion = new Conversion();
            conversion.setMonto(request.getMonto());
            conversion.setMonedaOrigen(request.getMonedaOrigen());
            conversion.setMonedaDestino(request.getMonedaDestino());
            conversion.setCantidadConvertida(convertedAmount);
            conversion.setTipoCambio(exchangeRate);
            return conversionRepository.save(conversion);
        } else {
            throw new IllegalArgumentException("Una o ambas monedas especificadas no son válidas.");
        }
    }
    private void refreshAvailableCurrencies(String monedaBase) {
        TipoCambioResponse exchangeRates = exchangeRateClient.getLatestExchangeRates(monedaBase);
        Map<String, Double> rates = exchangeRates.getRates();
        availableCurrencies.clear(); // Limpiar el conjunto existente
        // Agregar las monedas disponibles de la respuesta al conjunto
        availableCurrencies.addAll(rates.keySet());
    }
    public boolean isValidCurrency(String currency) {
        return availableCurrencies.contains(currency);
    }
    private boolean isCurrencyValid(String currency, List<ConversionRate> availableCurrencies) {
        return availableCurrencies.stream().anyMatch(cr -> cr.getCurrency().equalsIgnoreCase(currency));
    }
    public List<Conversion> listar() {
        return (List<Conversion>) conversionRepository.findAll();
    }

    public List<ConversionRate> listAvailableCurrencies() {
        TipoCambioResponse response = exchangeRateClient.getLatestExchangeRates_();
        return response.getRates().entrySet().stream()
                .map(entry -> new ConversionRate(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }
}
