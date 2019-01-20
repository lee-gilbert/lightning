package com.leeGilbert.ltk.repository;

import com.leeGilbert.ltk.domain.ScheduledSession;
import com.leeGilbert.ltk.domain.Submission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface SubmissionRepository extends JpaRepository<Submission, Long> {

    Page<Submission> findAll(Pageable pageable);

    Page<Submission> findByTopicContainingAllIgnoringCase(String topic, Pageable pageable);

    Submission findByTopicAllIgnoringCase(String topic);

    Optional<Submission> findByTopicIgnoringCase(String topic);

}
