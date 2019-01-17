package com.leeGilbert.ltk.domain;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity(name = "submission")
@Data
@RequiredArgsConstructor
@NoArgsConstructor
@ToString
public class Submission implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    @NonNull
    private String topic;

    @Column(nullable = false)
    @NonNull
    private String description;

    @Column(nullable = false)
    @NonNull
    private String email;

    @Column(nullable = false)
    @NonNull
    private LocalDate targetLightningTalkDate;

    @Column(nullable = true)
    @NonNull
    private LocalDateTime submitted;

    @Column(nullable = true)
    private Boolean approved;

    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    @JoinColumn(name="scheduledSession", referencedColumnName="id")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonIgnore
    private ScheduledSession scheduledSession;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

}
