package com.leeGilbert.ltk.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;

@Entity(name = "scheduledSession")
@Data
@RequiredArgsConstructor
@NoArgsConstructor
@ToString
public class ScheduledSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    @NonNull
    private LocalDate lightningTalkDate;

    @Column(nullable = false)
    @NonNull
    private String contactEmail;

    @OneToMany(mappedBy="scheduledSession", targetEntity=Submission.class, fetch= FetchType.EAGER)
    private Collection submissions;

}
