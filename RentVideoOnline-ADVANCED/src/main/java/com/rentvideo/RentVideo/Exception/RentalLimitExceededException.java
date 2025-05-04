package com.rentvideo.RentVideo.Exception;

public class RentalLimitExceededException extends RuntimeException {
    public RentalLimitExceededException(String message) {
        super(message);
    }
}