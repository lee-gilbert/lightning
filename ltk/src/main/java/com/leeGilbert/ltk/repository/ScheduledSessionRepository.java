package com.leeGilbert.ltk.repository;

import com.leeGilbert.ltk.domain.ScheduledSession;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface ScheduledSessionRepository  extends JpaRepository<ScheduledSession, Long> {

    Optional<ScheduledSession> findByLightningTalkDate(LocalDate targetLightningTalkDate);

}
