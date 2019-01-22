package com.leeGilbert.ltk;

import com.leeGilbert.ltk.domain.Submission;
import com.leeGilbert.ltk.domain.TopicProposal;
import com.leeGilbert.ltk.service.LightningTalksService;
import com.leeGilbert.ltk.util.TalkDateStreamUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

@SpringBootApplication
public class LightningtalksApplication {

	public static void main(String[] args) {
		SpringApplication.run(LightningtalksApplication.class, args);
	}


	@Bean
	CommandLineRunner init(LightningTalksService lts) {
		return (e) -> {
			//test data
			TopicProposal tp = new TopicProposal(null,
					"Microservices",
					"Microservices, their definition, and pros and cons.",
					"a@b.com", false, false);
			lts.updateTopicProposal(tp);

			Submission sess1 = new Submission(
					"Angular 7 Improvements",
					"Whats new in v7",
					"c@b.com", TalkDateStreamUtil.nextTalkDate(), LocalDateTime.now());
			lts.updateSubmission(sess1);

			Submission sess2 = new Submission(
					"Aria",
					"Supporting Aria in html Forms, issues with legacy apps.",
					"a@b.com", TalkDateStreamUtil.nextTalkDate(), LocalDateTime.now());
			lts.updateSubmission(sess2);

			Submission sess3 = new Submission(
					"Kibana Reporting",
					"Elasticsearch and Kibana",
					"a@b.com", TalkDateStreamUtil.nextTalkDate(), LocalDateTime.now());
			lts.updateSubmission(sess3);

			Submission sess4 = new Submission(
					"Good code design practices",
					"GOF Patterns, SOLID etc",
					"a@b.com", TalkDateStreamUtil.nextTalkDate(), LocalDateTime.now());
			lts.updateSubmission(sess4);

			Submission sess5 = new Submission(
					"Functional Programming Idioms",
					"Useful design idioms from Functional programming land",
					"z@b.com", TalkDateStreamUtil.nextTalkDate(), LocalDateTime.now());
			lts.updateSubmission(sess5);
//
//			LocalDate targetLightningTalkDate2 = TalkDateStreamUtil.forwardTalkDate(2); // advance to next lightning talk
//
//			Submission sess6 = new Submission(
//					"yay6 yay6 yay6 yay6 yay6 yay6 yay6 yay6 yay6 yay6 yay6 yay6 yay6 yay6 yay6",
//					"desc desc desc desc desc desc desc desc desc desc desc desc desc desc desc desc desc desc desc desc desc",
//					"a@b.com", targetLightningTalkDate2, LocalDateTime.now());
//			lts.updateSubmission(sess6);
//
//			Submission sess7 = new Submission(
//					"yay7",
//					"desc desc desc desc desc desc desc desc desc desc desc desc desc desc desc desc desc desc desc desc desc",
//					"a@b.com", targetLightningTalkDate2, LocalDateTime.now());
//			lts.updateSubmission(sess7);
//
//			Submission sess8 = new Submission(
//					"yay8",
//					"desc desc desc desc desc desc desc desc desc desc desc desc desc desc desc desc desc desc desc desc desc",
//					"a@b.com", targetLightningTalkDate2, LocalDateTime.now());
//			lts.updateSubmission(sess8);

		};
	}

}