package com.abdelhadielomari.ordonnanceservice.Exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class StockInsuffisantException extends RuntimeException{
    public StockInsuffisantException(String message){
        super(message);
    }
}
