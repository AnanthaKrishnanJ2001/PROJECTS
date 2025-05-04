package com.rentvideo.RentVideo.Service;

import com.rentvideo.RentVideo.Model.Video;

public interface RentalService {

    Video returnVideo(Long videoId);

    void rentVideo(Long videoId);
    
}
