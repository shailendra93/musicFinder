package com.shail.musicfinder.musicFinder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MusicFinderApplication {

	public static void main(String[] args) {
		SpringApplication.run(MusicFinderApplication.class, args);
	}

}

