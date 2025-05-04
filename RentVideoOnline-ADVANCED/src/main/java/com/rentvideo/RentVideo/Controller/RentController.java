package com.rentvideo.RentVideo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.rentvideo.RentVideo.Service.RentalService;

@RestController
@RequestMapping("/api/video-rental")
public class RentController {
    @Autowired
    private RentalService rentalService;

     // Rent a video
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @PostMapping("/videos/{videoId}/rent")
    public ResponseEntity<String> rentVideo(@PathVariable("videoId") Long videoId) {
        rentalService.rentVideo(videoId);
        return ResponseEntity.ok("Video rented successfully.");
    }

    // Return a video
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @PostMapping("/videos/{videoId}/return")
    public ResponseEntity<String> returnVideo(@PathVariable("videoId") Long videoId) {
        rentalService.returnVideo(videoId);
        return ResponseEntity.ok("Video returned successfully.");
    }
    
}
