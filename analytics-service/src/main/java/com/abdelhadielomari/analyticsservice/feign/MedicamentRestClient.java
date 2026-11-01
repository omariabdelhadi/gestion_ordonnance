package com.abdelhadielomari.analyticsservice.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "medicament-service")
public interface MedicamentRestClient {
    @GetMapping("/medicament/{id}/stock")
    int getStock(@PathVariable Long id);
}
