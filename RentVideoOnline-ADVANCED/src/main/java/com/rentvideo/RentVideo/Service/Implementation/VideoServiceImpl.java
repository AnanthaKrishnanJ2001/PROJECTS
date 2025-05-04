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
    public Video updateVideo(Long id, Video videoRequest) {
        Video video = videoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Video Not found with id:" + id));
        video.setTitle(videoRequest.getTitle());
        video.setGenre(videoRequest.getGenre());
        video.setDirector(videoRequest.getDirector());
        video.setAvailableForRent(videoRequest.isAvailableForRent());
        videoRepository.save(video);

        return video;
    }

    @Override
    public void deleteVideo(Long title) {
        videoRepository.deleteById(title);
    }


    
}
