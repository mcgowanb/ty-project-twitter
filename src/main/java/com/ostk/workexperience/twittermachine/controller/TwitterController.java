package com.ostk.workexperience.twittermachine.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ostk.workexperience.twittermachine.service.Twitter;

import lombok.extern.log4j.Log4j2;
import twitter4j.Status;

@Log4j2
@RestController
@RequestMapping(value = "/")
public class TwitterController {

  @Inject
  private Twitter twitter;

  @PostMapping(value = "tweet")
  public ResponseEntity sendTweet(final String tweetMessage) {
    log.info("Message received: {}", tweetMessage);
    final String myMessage = null;
    twitter.publish(myMessage);
    return ResponseEntity.ok("Message Successfully published");
  }

  @PostMapping(value = "hello-world")
  public ResponseEntity helloWorld(final String message) {
    log.info("Message received: {}", message);
    return ResponseEntity.ok("you said: " + message);
  }

  @PostMapping(value = "challenge")
  public ResponseEntity sendTweet(final String[] messages) {
    log.info("Message received: {}", messages);
    final String myMessage = null;
    twitter.publish(myMessage);
    return ResponseEntity.ok("Message Successfully published");
  }

  @PostMapping(value = "search")
  public ResponseEntity searchTweets(final String queryString) {
    log.info("Searching for: {}", queryString);
    final String myMessage = null;
    final List<Status> tweets = twitter.search(myMessage); // <- Something in there might be broken
    return ResponseEntity.ok("Got a bunch of tweets");
  }

  @PostMapping(value = "timeline")
  public ResponseEntity fetchTimeline() {
    log.info("Fetching Timeline");
    //TODO: Response from twitter needs to be stored and returned
    twitter.fetchTimeline();
    return ResponseEntity.ok().build();
  }

  private File multiToFile(final MultipartFile multipartFile) throws IOException {
    final File file = new File(multipartFile.getOriginalFilename());
    if(file.createNewFile()) {
      try (FileOutputStream fos = new FileOutputStream(file)) {
        fos.write(multipartFile.getBytes());
      }
      return file;
    }
    return null;
  }
}
