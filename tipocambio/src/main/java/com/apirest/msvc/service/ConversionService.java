package com.apirest.msvc.service;

import com.apirest.msvc.model.Conversion;
import com.apirest.msvc.model.ConversionRequest;

import java.util.List;

public interface ConversionService {

    public List<Conversion> listar();
    public Conversion convertCurrency(String monedaBase,ConversionRequest request);
}
