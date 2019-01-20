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
					"yay",
					"desc",
					"a@b.com", false, false);
			lts.updateTopicProposal(tp);

			Submission sess1 = new Submission(
					"yay1 yay1 yay1",
					"desc",
					"a@b.com", TalkDateStreamUtil.nextTalkDate(), LocalDateTime.now());
			lts.updateSubmission(sess1);

			Submission sess2 = new Submission(
					"yay2",
					"desc",
					"a@b.com", TalkDateStreamUtil.nextTalkDate(), LocalDateTime.now());
			lts.updateSubmission(sess2);

			Submission sess3 = new Submission(
					"yay3",
					"desc",
					"a@b.com", TalkDateStreamUtil.nextTalkDate(), LocalDateTime.now());
			lts.updateSubmission(sess3);

			Submission sess4 = new Submission(
					"yay4",
					"desc",
					"a@b.com", TalkDateStreamUtil.nextTalkDate(), LocalDateTime.now());
			lts.updateSubmission(sess4);

			Submission sess5 = new Submission(
					"yay5 yay5 yay5 yay5 yay5 yay5 yay5 yay5 yay5 yay5 yay5 yay5 yay5 yay5 yay5",
					"desc",
					"a@b.com", TalkDateStreamUtil.nextTalkDate(), LocalDateTime.now());
			lts.updateSubmission(sess5);

			Submission sess6 = new Submission(
					"yay6 yay6 yay6 yay6 yay6 yay6 yay6 yay6 yay6 yay6 yay6 yay6 yay6 yay6 yay6",
					"desc desc desc desc desc desc desc desc desc desc desc desc desc desc desc desc desc desc desc desc desc",
					"a@b.com", TalkDateStreamUtil.nextTalkDate(), LocalDateTime.now());
			lts.updateSubmission(sess6);

		};
	}

}