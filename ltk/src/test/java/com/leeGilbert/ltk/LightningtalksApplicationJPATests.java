package com.leeGilbert.ltk;

import com.leeGilbert.ltk.domain.ScheduledSession;
import com.leeGilbert.ltk.domain.Submission;
import com.leeGilbert.ltk.domain.TopicProposal;
import com.leeGilbert.ltk.repository.ScheduledSessionRepository;
import com.leeGilbert.ltk.repository.SubmissionRepository;
import com.leeGilbert.ltk.repository.TopicProposalRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sun.jvm.hotspot.utilities.Assert;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class LightningtalksApplicationJPATests {

	@Autowired
	public TopicProposalRepository topicProposalRepository;

	@Autowired
	public SubmissionRepository submissionRepository;

	@Autowired
	public ScheduledSessionRepository scheduledSessionRepository;

	@Test
	public void contextLoads() {
	}

	@Test
	public void crudTopicProposal() {
		submissionRepository.deleteAll();
		List<TopicProposal> data = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			TopicProposal item = new TopicProposal(null,"topic" + i, "description","email", false, false);
			data.add(item);
		}

		topicProposalRepository.saveAll(data);

		List<TopicProposal> saved = topicProposalRepository.findAll();
		saved.forEach(b -> log.info(b.toString()));  //FIXME add asserts
	}


	@Test
	public void crudSubmission() {
		submissionRepository.deleteAll();
		LocalDate targetLightningTalkDate = LocalDate.now();
		List<Submission> data = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			Submission item = new Submission("topic" + i, "description","email", targetLightningTalkDate, LocalDateTime.now());
			data.add(item);
		}
		submissionRepository.saveAll(data);

		List<Submission> saved = submissionRepository.findAll();
		saved.forEach(b -> log.info(b.toString())); //FIXME add asserts
	}


	@Test
	public void crudScheduledSession() {
		submissionRepository.deleteAll();
		topicProposalRepository.deleteAll();
		LocalDate targetLightningTalkDate = LocalDate.now();
		List<Submission> data = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			Submission item = new Submission("topic" + i, "description","email", targetLightningTalkDate, LocalDateTime.now());
			data.add(item);
		}
		submissionRepository.saveAll(data);
		List<Submission> submissions = submissionRepository.findAll();
		Assert.that(submissions.size()==3, "3 submissions should be found but was " + submissions.size());

		ScheduledSession scheduledSession = new ScheduledSession(targetLightningTalkDate, "email");

		List<ScheduledSession> schedules = new ArrayList<>();
		schedules.add(scheduledSession);
		scheduledSessionRepository.saveAll(schedules);

		// fetch all
		List<ScheduledSession> saved = scheduledSessionRepository.findAll();
		Assert.that(saved.size()==1, "1 ScheduledSession should be found but was " + submissions.size());

		saved.forEach(b -> log.info(b.toString())); //FIXME add asserts
	}


}

