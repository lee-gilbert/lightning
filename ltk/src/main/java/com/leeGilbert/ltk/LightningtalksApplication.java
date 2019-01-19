package com.leeGilbert.ltk;

import com.leeGilbert.ltk.domain.TopicProposal;
import com.leeGilbert.ltk.service.LightningTalksService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.util.StringUtils;

import java.util.Arrays;

@SpringBootApplication
public class LightningtalksApplication {

	public static void main(String[] args) {
		SpringApplication.run(LightningtalksApplication.class, args);
	}

	public void run(String... args) {

	}

	@Bean
	CommandLineRunner init(LightningTalksService lts) {
		return (e) -> {
			TopicProposal tp = new TopicProposal(null,
					"yay",
					"desc",
					"a@b.com", false, false);
			lts.updateTopicProposal(tp);
		};
	}

}