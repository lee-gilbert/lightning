package com.leeGilbert.ltk.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Lombox issue https://github.com/rzwitserloot/lombok/issues/1703 prevents use here
 */
@Entity(name = "topicProposal")
@ToString
public class TopicProposal implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String topic;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private Boolean submitted ;

    @Column(nullable = true)
    private Boolean deleted ;

    protected TopicProposal() {}

    public TopicProposal(Long id, String topic, String description, String email, Boolean submitted, Boolean deleted) {
        this.id = id;
        this.topic = topic;
        this.description = description;
        this.email = email;
        this.submitted = submitted;
        this.deleted = deleted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getSubmitted() {
        return submitted;
    }

    public void setSubmitted(Boolean submitted) {
        this.submitted = submitted;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}


