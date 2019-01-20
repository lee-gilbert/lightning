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
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import java.util.Set;
import java.util.function.Function;

import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;


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


//	private static final Function<Long, String> repeaterX = (cnt) -> Stream.generate(() -> "X").limit((long)cnt).collect(joining());
//	private String s81 = repeaterX.apply(81L);
//	private String s121 = repeaterX.apply(121L);
//	private String s256 = repeaterX.apply(256L);

	@Test
	public void contextLoads() {
	}

	@Test
	public void crudTopicProposal() {////TODO include all crud ops & use tx boundaries between save & retrieve
		topicProposalRepository.deleteAll();
		List<TopicProposal> data = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			TopicProposal item = new TopicProposal(null,"topic" + i, "description","email", false, false);
			data.add(item);
		}

		topicProposalRepository.saveAll(data);
		topicProposalRepository.flush();
		List<TopicProposal> saved = topicProposalRepository.findAll();
		Assert.assertThat("# of TopicProposals found " , saved.size(), is(10));
		saved.forEach(b -> log.info(b.toString()));
	}


	@Test
	public void crudSubmission() { //TODO include all crud ops & use tx boundaries between save & retrieve
		submissionRepository.deleteAll();
		LocalDate targetLightningTalkDate = LocalDate.now();
		List<Submission> data = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			Submission item = new Submission("topic" + i, "description","email", targetLightningTalkDate, LocalDateTime.now());
			data.add(item);
		}
		submissionRepository.saveAll(data);
		topicProposalRepository.flush();
		List<Submission> saved = submissionRepository.findAll();
		Assert.assertThat("# of Submissions found " , saved.size(), is(10));
		saved.forEach(b -> log.info(b.toString()));
	}


	@Test
	public void crudScheduledSession() {//TODO include all crud ops & use tx boundaries between save & retrieve
		submissionRepository.deleteAll();
		topicProposalRepository.deleteAll();
		LocalDate targetLightningTalkDate = LocalDate.now();

		create3Submissions(targetLightningTalkDate);

		final Set<Submission> savedSubmissions = new HashSet<>();
		savedSubmissions.addAll(submissionRepository.findAll());
		Assert.assertThat("# of submissions found " , savedSubmissions.size(), is(3));

		ScheduledSession newScheduledSession = new ScheduledSession(targetLightningTalkDate, "email");
		scheduledSessionRepository.saveAndFlush(newScheduledSession);
		ScheduledSession savedScheduledSession = scheduledSessionRepository.findAll().get(0);
		Assert.assertThat(" savedScheduledSession found " , savedScheduledSession, notNullValue());

		savedScheduledSession.setSubmissions(savedSubmissions);
		savedSubmissions.forEach(s->submissionRepository.saveAndFlush(s)); // needed
		//scheduledSessionRepository.saveAndFlush(savedScheduledSession); // not needed

		ScheduledSession savedScheduledSession2 = scheduledSessionRepository.findById(savedScheduledSession.getId()).get();
		Assert.assertThat("# of submissions attached to ScheduledSession" , savedScheduledSession2.getSubmissions().size(), is(3));
		log.info(savedScheduledSession2.toString());
		savedScheduledSession2.getSubmissions().forEach(s->log.info("> " + s.toString()));

		List<Submission> updatedSubmissions = submissionRepository.findAll();
		updatedSubmissions.forEach(x -> Assert.assertThat(x.getScheduledSession(), notNullValue()));
		updatedSubmissions.forEach(b -> log.info(b.toString()));
	}

	private void create3Submissions(LocalDate targetLightningTalkDate) {
		List<Submission> data = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			Submission item = new Submission("topic" + i, "description","email", targetLightningTalkDate, LocalDateTime.now());
			data.add(item);
		}
		submissionRepository.saveAll(data);
		submissionRepository.flush();
	}

}

