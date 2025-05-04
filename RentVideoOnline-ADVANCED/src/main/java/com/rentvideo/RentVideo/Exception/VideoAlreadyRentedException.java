package com.rentvideo.RentVideo.Exception;

public class VideoAlreadyRentedException extends RuntimeException {
    public VideoAlreadyRentedException(String message) {
        super(message);
    }
}