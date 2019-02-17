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

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.*;

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


    public List<Submission> findAllSessionsWithTalkDate(LocalDate onDate) {
        Assert.notNull(onDate, "Submission date filter must not be null");
        return this.submissionRepository.findAllWithTargetLightningTalkDateOn(onDate);
    }

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

    /**
     * Creates a Submission & sets proposal status to submitted, if proposal exists.
     * @param submission
     * @param proposalId
     * @return
     */
    public Submission createSubmission(Submission submission, Long  proposalId) {
        Assert.notNull(submission, "Submission must not be null");
        Assert.isNull(submission.getId(), "Submission id should not be pre-assigned");
        if (proposalId != null) {
            Optional<TopicProposal> topicProposalOption = this.findTopicProposalById(proposalId);
            topicProposalOption.ifPresent(tp -> {
            tp.setSubmitted(true);
            topicProposalRepository.save(tp);
            });
        }
       return this.submissionRepository.saveAndFlush(submission);
    }


    public Submission updateSubmission(Submission submission) {
        Assert.notNull(submission, "Submission must not be null");
        return this.submissionRepository.saveAndFlush(submission);
    }

    public Submission approveSubmission(Submission submission, String approverEmail) {
        Assert.notNull(submission, "Submission must not be null");
        if (submission.getScheduledSession() == null ) {
            Optional<ScheduledSession> SSForDate = scheduledSessionRepository.findByLightningTalkDate(submission.getTargetLightningTalkDate());
            Set<Submission> tmp = new HashSet<>();
            tmp.add(submission);
            if (SSForDate.isPresent()) {
                ScheduledSession ss = SSForDate.get();
                Set<Submission> submissions = ss.getSubmissions();
                if (submissions.size() <3) {
                    tmp.addAll(submissions);
                    ss.setSubmissions(tmp);
                    submission.setScheduledSession(ss);
                } else {
                    submission.setApproved(false);
                    return submission;  // TODO should throw exception instead
                }
            } else {
                ScheduledSession newSS = new ScheduledSession();
                newSS.setContactEmail(approverEmail);
                newSS.setLightningTalkDate(submission.getTargetLightningTalkDate());
                scheduledSessionRepository.saveAndFlush(newSS);
                submission.setScheduledSession(newSS);
            }
        }
        submission.setApproved(true);
        return this.submissionRepository.saveAndFlush(submission);
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
