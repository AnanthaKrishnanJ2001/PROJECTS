package com.rentvideo.RentVideo.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.rentvideo.RentVideo.Model.Rental;
import com.rentvideo.RentVideo.Model.User;

public interface RentalRepository extends JpaRepository<Rental, Long> {
    List<Rental> findByUserAndReturnedFalse(User user);
    Rental findByVideoIdAndReturnedFalse(Long videoId);
}
