package com.leeGilbert.ltk.service;

import com.leeGilbert.ltk.domain.ScheduledSession;
import com.leeGilbert.ltk.domain.Submission;
import com.leeGilbert.ltk.domain.TopicProposal;
import com.leeGilbert.ltk.repository.ScheduledSessionRepository;
import com.leeGilbert.ltk.repository.SubmissionRepository;
import com.leeGilbert.ltk.repository.TopicProposalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component("lightningTalksService")
@Transactional
public class LightningTalksService {

    @Autowired
    private TopicProposalRepository topicProposalRepository;

    @Autowired
    private SubmissionRepository submissionRepository;

    @Autowired
    private ScheduledSessionRepository scheduledSessionRepository;


    //** TopicProposal *************************************************************************************************

    public List<TopicProposal> findAllTopicProposal() {
        return this.topicProposalRepository.findAll();
    }

    public Optional<TopicProposal> findTopicProposalById(Long id) {
        Assert.notNull(id, "TopicProposal id must not be null");
        return this.topicProposalRepository.findById(id);
    }

    public Optional<TopicProposal> findTopicProposalByTopic(String topic) {
        Assert.notNull(topic, "Topic must not be null");
        return this.topicProposalRepository.findByTopicIgnoringCase(topic);
    }

    public TopicProposal updateTopicProposal(TopicProposal tp) {
        Assert.notNull(tp, "TopicProposal must not be null");
        return this.topicProposalRepository.saveAndFlush(tp);
    }

    public void deleteTopicProposalById(Long id) {
        Assert.notNull(id, "TopicProposal id must not be null");
        this.topicProposalRepository.deleteById(id);
    }


    //** Submission *************************************************************************************************

    public List<Submission> findAllSubmission() {
        return this.submissionRepository.findAll();
    }

    public Optional<Submission> findSubmissionById(Long id) {
        Assert.notNull(id, "Submission id must not be null");
        return this.submissionRepository.findById(id);
    }

    public Optional<Submission> findSubmissionByTopic(String topic) {
        Assert.notNull(topic, "Submission must not be null");
        return this.submissionRepository.findByTopicIgnoringCase(topic);
    }

    public Submission updateSubmission(Submission tp) {
        Assert.notNull(tp, "Submission must not be null");
        return this.submissionRepository.saveAndFlush(tp);
    }

    public void deleteSubmissionlById(Long id) {
        Assert.notNull(id, "Submission id must not be null");
        this.submissionRepository.deleteById(id);
    }

    //** ScheduledSession **********************************************************************************************

    public List<ScheduledSession> findAllScheduledSession() {
        return this.scheduledSessionRepository.findAll();
    }

    public Optional<ScheduledSession> findScheduledSessionById(Long id) {
        Assert.notNull(id, "ScheduledSession id must not be null");
        return this.scheduledSessionRepository.findById(id);
    }

    public Optional<ScheduledSession> findScheduledSessionnByTopic(LocalDate ltd) {
        Assert.notNull(ltd, "LightningTalkDate must not be null");
        return this.scheduledSessionRepository.findByLightningTalkDate(ltd);
    }

    public ScheduledSession updateScheduledSession(ScheduledSession tp) {
        Assert.notNull(tp, "ScheduledSession must not be null");
        return this.scheduledSessionRepository.saveAndFlush(tp);
    }

    public void deleteScheduledSessionById(Long id) {
        Assert.notNull(id, "ScheduledSession id must not be null");
        this.scheduledSessionRepository.deleteById(id);
    }
}
