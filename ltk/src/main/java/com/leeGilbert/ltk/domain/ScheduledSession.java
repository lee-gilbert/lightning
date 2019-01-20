package com.leeGilbert.ltk.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Scheduled talk event.
 */
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
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @Size(max=3)  //TODO make max size externally configurable?
    private Set<Submission> submissions = new HashSet<>();

    public void setSubmissions(final List<Submission> submissionsIn) {
        submissionsIn.forEach(sub -> {sub.setScheduledSession(this); sub.setApproved(true);});
        submissions.clear();
        submissions.addAll(submissionsIn);
    }
}
