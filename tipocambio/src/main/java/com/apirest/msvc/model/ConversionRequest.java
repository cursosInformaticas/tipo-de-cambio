package com.apirest.msvc.model;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.Map;


public class ConversionRequest {

    @NotNull
    @Positive
    private double monto;

    @Column(name = "monedaOrigen", nullable = false)
    @NotEmpty(message = "El campo 'monedaOrigen' no debe estar vacío")
    @Size(max = 3, message = "El campo 'monedaOrigen' debe tener como máximo 3 caracteres")
    private String monedaOrigen;

    @Column(name = "monedaDestino", nullable = false)
    @NotEmpty(message = "El campo 'monedaOrigen' no debe estar vacío")
    @Size(max = 3, message = "El campo 'monedaOrigen' debe tener como máximo 3 caracteres")
    private String monedaDestino;



    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getMonedaOrigen() {
        return monedaOrigen;
    }

    public void setMonedaOrigen(String monedaOrigen) {
        this.monedaOrigen = monedaOrigen;
    }

    public String getMonedaDestino() {
        return monedaDestino;
    }

    public void setMonedaDestino(String monedaDestino) {
        this.monedaDestino = monedaDestino;
    }




}
