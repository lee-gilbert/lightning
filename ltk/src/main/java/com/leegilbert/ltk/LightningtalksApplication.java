package com.leegilbert.ltk;

import com.leegilbert.ltk.domain.Submission;
import com.leegilbert.ltk.domain.TopicProposal;
import com.leegilbert.ltk.service.LightningTalksService;
import com.leegilbert.ltk.util.TalkDateStreamUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.time.LocalDateTime;
import java.util.Arrays;


@SpringBootApplication(exclude = ErrorMvcAutoConfiguration.class )
public class LightningtalksApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(LightningtalksApplication.class, args);


		System.out.println("Beans provided by Spring Boot:");

		String[] beanNames = ctx.getBeanDefinitionNames();
		Arrays.sort(beanNames);
		for (String beanName : beanNames) {
			System.out.println(beanName);
		}
	}


	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*");
			}
		};
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