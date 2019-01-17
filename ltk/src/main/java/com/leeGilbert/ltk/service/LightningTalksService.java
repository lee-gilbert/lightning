package com.leeGilbert.ltk.service;

import com.leeGilbert.ltk.domain.TopicProposal;
import com.leeGilbert.ltk.repository.ScheduledSessionRepository;
import com.leeGilbert.ltk.repository.SubmissionRepository;
import com.leeGilbert.ltk.repository.TopicProposalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Component("lightningTalksService")
@Transactional
public class LightningTalksService {

    @Autowired
    private TopicProposalRepository topicProposalRepository;

    @Autowired
    private SubmissionRepository submissionRepository;

    @Autowired
    private ScheduledSessionRepository scheduledSessionRepository;

    public TopicProposal getTalkProposal(String topic) {
        Assert.notNull(topic, "Topic must not be null");
        return this.topicProposalRepository.findByTopicAllIgnoringCase(topic);
    }
}
