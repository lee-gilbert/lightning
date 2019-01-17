package com.leeGilbert.ltk.repository;

import com.leeGilbert.ltk.domain.ScheduledSession;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface ScheduledSessionRepository  extends JpaRepository<ScheduledSession, Long> {

    Page<ScheduledSession> findByLightningTalkDate(LocalDate targetLightningTalkDate, Pageable pageable);

}
