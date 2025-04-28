package com.rentvideo.RentVideo.Service.Implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rentvideo.RentVideo.Exception.ResourceNotFoundException;
import com.rentvideo.RentVideo.Model.Video;
import com.rentvideo.RentVideo.Repository.VideoRepository;
import com.rentvideo.RentVideo.Service.VideoService;

@Service
public class VideoServiceImpl implements VideoService{

    @Autowired
    VideoRepository videoRepository;

    @Override
    public List<Video> getAvailableVideos() {
       return videoRepository.findAll();
    }

    @Override
    public Video createVideo(Video video) {
        return videoRepository.save(video);
    }

    @Override
    public Video updateVideo(String title, Video videoRequest) {
        Video video = videoRepository.findByTitle(title).orElseThrow(() -> new ResourceNotFoundException("Video Not found with title:" + title));
        video.setGenre(videoRequest.getGenre());
        video.setDirector(videoRequest.getDirector());
        video.setAvailableForRent(videoRequest.isAvailableForRent());
        videoRepository.save(video);

        return video;
    }

    @Override
    public void deleteVideo(String title) {
        videoRepository.deleteById(title);
    }


    
}
