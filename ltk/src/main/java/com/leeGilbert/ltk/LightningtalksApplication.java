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

			Submission sess = new Submission(
					"yay2",
					"desc",
					"a@b.com", TalkDateStreamUtil.nextTalkDate(), LocalDateTime.now());
			lts.updateSubmission(sess);

		};
	}

}