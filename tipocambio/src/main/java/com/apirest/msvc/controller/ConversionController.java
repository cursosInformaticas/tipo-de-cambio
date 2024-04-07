package com.apirest.msvc.controller;

import com.apirest.msvc.model.Conversion;
import com.apirest.msvc.model.ConversionRate;
import com.apirest.msvc.model.ConversionRequest;
import com.apirest.msvc.service.ConversionServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ConversionController {
    @Autowired
    private ConversionServiceImpl conversionService;

    @GetMapping("/listado")
    public ResponseEntity<List<Conversion>> listarTiposCambio() {
        List<Conversion> tiposCambio = conversionService.listar();
        return new ResponseEntity<>(tiposCambio, HttpStatus.OK);
    }

@PostMapping("/convert/{monedaBase}")
public ResponseEntity<?> convertirMoneda(@PathVariable String monedaBase,
                                         @Valid @RequestBody ConversionRequest request,
                                         BindingResult result) {
    if (result.hasErrors()) {
        return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
    }
    // Llamar al servicio de conversión de moneda
    Conversion conversion = conversionService.convertCurrency(monedaBase,request);
    return new ResponseEntity<>(conversion, HttpStatus.OK);
}
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException ex) {
        return ResponseEntity.badRequest().body(ex.getBindingResult().getFieldError().getDefaultMessage());
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    @GetMapping("/money")
    public ResponseEntity<List<ConversionRate>> listCurrencies() {
        List<ConversionRate> currencies = conversionService.listAvailableCurrencies();
        return ResponseEntity.ok(currencies);
    }
}
