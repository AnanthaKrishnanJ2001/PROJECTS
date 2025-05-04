package com.rentvideo.RentVideo.Service.Implementation;

import com.rentvideo.RentVideo.Exception.RentalLimitExceededException;
import com.rentvideo.RentVideo.Exception.ResourceNotFoundException;
import com.rentvideo.RentVideo.Exception.VideoAlreadyRentedException;
import com.rentvideo.RentVideo.Model.Rental;
import com.rentvideo.RentVideo.Model.User;
import com.rentvideo.RentVideo.Model.Video;
import com.rentvideo.RentVideo.Repository.RentalRepository;
import com.rentvideo.RentVideo.Repository.VideoRepository;
import com.rentvideo.RentVideo.Service.RentalService;
import com.rentvideo.RentVideo.Util.AuthUtil; // utility to get logged-in user
import jakarta.persistence.EntityNotFoundException;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RentalServiceImpl implements RentalService {

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private AuthUtil authUtil;

    @Override
    public void rentVideo(Long videoId) {
        User user = authUtil.getLoggedInUser();

        List<Rental> activeRentals = rentalRepository.findByUserAndReturnedFalse(user);
        if (activeRentals.size() >= 2) {
            throw new RentalLimitExceededException("Maximum of 2 active rentals allowed.");
        }

        Video video = videoRepository.findById(videoId)
                .orElseThrow(() -> new ResourceNotFoundException("Video not found"));

        Rental existing = rentalRepository.findByVideoIdAndReturnedFalse(videoId);
        if (existing != null) {
            throw new VideoAlreadyRentedException("Video is already rented.");
        }

        Rental rental = new Rental();
        rental.setUser(user);
        rental.setVideo(video);
        rental.setRentalDate(LocalDateTime.now());
        rental.setReturned(false);

        rentalRepository.save(rental);
    }

    @Override
    public Video returnVideo(Long videoId) {
        User user = authUtil.getLoggedInUser();

        Rental rental = rentalRepository.findByVideoIdAndReturnedFalse(videoId);
        if (rental == null || !rental.getUser().getEmail().equals(user.getEmail())) {
            throw new RuntimeException("Rental not found for this user.");
        }

        rental.setReturned(true);
        rentalRepository.save(rental);

        return rental.getVideo();
    }
}
