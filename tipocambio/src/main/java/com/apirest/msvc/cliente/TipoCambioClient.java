package com.apirest.msvc.cliente;

import com.apirest.msvc.model.Conversion;
import com.apirest.msvc.model.TipoCambioResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

//@FeignClient(name = "tipocambio", url = "${exchange.api.url}")
@FeignClient(name = "tipocambio", url = "https://open.er-api.com/v6/latest")
public interface TipoCambioClient {
    @GetMapping("/{codigoMoneda}")
    TipoCambioResponse getLatestExchangeRates(@PathVariable("codigoMoneda") String codigoMoneda);
    @GetMapping("/listado")
    public List<Conversion> listar();

    @GetMapping
    TipoCambioResponse getLatestExchangeRates_();

}
