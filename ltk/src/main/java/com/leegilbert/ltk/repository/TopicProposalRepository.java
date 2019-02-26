package com.leegilbert.ltk.repository;

import com.leegilbert.ltk.domain.TopicProposal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TopicProposalRepository extends JpaRepository<TopicProposal, Long> {


    Page<TopicProposal> findByTopicContainingAllIgnoringCase(String topic, Pageable pageable);

    Optional<TopicProposal> findByTopicIgnoringCase(String topic);


}
