package com.example.fooddelivery.exceptions;

public class DeliveryFeeException extends RuntimeException{
    public DeliveryFeeException(String errorMessage){
        super(errorMessage);
    }

}
