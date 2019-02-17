package com.leeGilbert.ltk.repository;

import com.leeGilbert.ltk.domain.ScheduledSession;
import com.leeGilbert.ltk.domain.Submission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface SubmissionRepository extends JpaRepository<Submission, Long> {

    Page<Submission> findAll(Pageable pageable);

    Optional<Submission> findByTopicIgnoringCase(String topic);

    @Query("select s from submission s where s.targetLightningTalkDate = :filterDate")
    List<Submission> findAllWithTargetLightningTalkDateOn(@Param("filterDate") LocalDate talkDate);
}
