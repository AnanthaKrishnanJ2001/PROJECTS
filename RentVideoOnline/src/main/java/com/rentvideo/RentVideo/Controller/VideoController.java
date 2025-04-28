package com.rentvideo.RentVideo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rentvideo.RentVideo.Model.Video;
import com.rentvideo.RentVideo.Service.VideoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/video-rental")
public class VideoController {
        @Autowired
    private VideoService videoService;

    // Public endpoint: View available videos (accessible to both roles)
    @GetMapping("/
    
")
    public ResponseEntity<List<Video>> getAvailableVideos() {
        List<Video> videos = videoService.getAvailableVideos();
        return ResponseEntity.ok(videos);
    }

    // Private endpoint: Create video (ADMIN only)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<Video> createVideo(@RequestBody @Valid Video video) {
        System.out.println("in");
        Video createdVideo = videoService.createVideo(video);
        System.out.println("out");
        return ResponseEntity.status(HttpStatus.CREATED).body(createdVideo);
    }

    // Private endpoint: Update video (ADMIN only)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/update/{title}")
    public ResponseEntity<Video> updateVideo(@PathVariable("title") String title, @RequestBody @Valid Video video) {
        Video updatedVideo = videoService.updateVideo(title, video);
        return ResponseEntity.ok(updatedVideo);
    }

    // Private endpoint: Delete video (ADMIN only)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete/{title}")
    public ResponseEntity<Void> deleteVideo(@PathVariable("title") String title) {
        videoService.deleteVideo(title);
        return ResponseEntity.noContent().build();
    }

}
