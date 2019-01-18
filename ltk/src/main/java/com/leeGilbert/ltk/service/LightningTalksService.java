package com.leeGilbert.ltk.service;

import com.leeGilbert.ltk.domain.TopicProposal;
import com.leeGilbert.ltk.repository.ScheduledSessionRepository;
import com.leeGilbert.ltk.repository.SubmissionRepository;
import com.leeGilbert.ltk.repository.TopicProposalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

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

}
