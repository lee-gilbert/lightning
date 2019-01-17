package com.leeGilbert.ltk.repository;

import com.leeGilbert.ltk.domain.TopicProposal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicProposalRepository extends JpaRepository<TopicProposal, Long> {


    Page<TopicProposal> findByTopicContainingAllIgnoringCase(String topic, Pageable pageable);

    TopicProposal findByTopicAllIgnoringCase(String topic);


}
