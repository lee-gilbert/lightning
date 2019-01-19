package com.leeGilbert.ltk.rest;

import com.leeGilbert.ltk.domain.Submission;
import com.leeGilbert.ltk.domain.TopicProposal;
import com.leeGilbert.ltk.service.LightningTalksService;
import com.leeGilbert.ltk.util.TalkDateStreamUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Context;

import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 *  REST endpoints for LightningTalks.
 */
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
@Slf4j
public class LightningTalksController {

    @Autowired
    private LightningTalksService lightningTalksService;

    //** TopicProposal *************************************************************************************************
    /**
     * GET  /proposal -> Retrieves TopicProposal list.
     */
    @GetMapping(value = "/proposal")
    @Transactional(readOnly = true)
    public ApiResponse<List<TopicProposal>> findAllTopicProposal() {
        log.debug("REST-GET request to retrieve TopicProposal ");
        return new ApiResponse<List<TopicProposal>>(HttpStatus.OK.value(), "success", lightningTalksService.findAllTopicProposal());
    }

    /**
     * GET  /proposal/{id} -> Retrieves a TopicProposal.
     */
    @GetMapping(value = "/proposal/{id}")
    @Transactional(readOnly = true)
    public ApiResponse<TopicProposal> findTopicProposalById(@PathVariable Long id) {
        log.debug("REST-GET request to retrieve TopicProposal id: {}", id);
        return lightningTalksService.findTopicProposalById(id)
                .map(found -> new ApiResponse<TopicProposal>(HttpStatus.OK.value(), "TopicProposal fetched successfully", found))
                .orElse(new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "TopicProposal fetch failed", null));
    }

    /**
     * POST  /proposal -> Add/Create a new TopicProposal.
     */
    @PostMapping(value = "/proposal")
    public ApiResponse<TopicProposal> create(@RequestBody TopicProposal tp, @Context HttpServletResponse resp) throws URISyntaxException {
        log.debug("REST-POST request to save new TopicProposal : {}", tp);
        if (tp.getId() != null) {//TODO refactor into util
            resp.setHeader("Failure", "New object cannot already have an id.");
            resp.setStatus(HttpStatus.BAD_REQUEST.value());
            return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), "New object cannot already have an id.", null);
        }
        if (tp.getSubmitted() == null) {
            tp.setSubmitted(false);
        }
        try {
            tp = lightningTalksService.updateTopicProposal(tp);
        } catch (ConstraintViolationException e) { //TODO refactor into util
            ConstraintViolation<?> next = e.getConstraintViolations().iterator().next();
            String message = next.getMessage();
            String propertyName = next.getPropertyPath().toString();
            resp.setHeader("Failure", propertyName + " " + message);
            resp.setStatus(HttpStatus.BAD_REQUEST.value());
            return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), propertyName + " " + message, null);
        } catch  (DataAccessException dae){
            String message = dae.getMessage();
            resp.setHeader("Failure",  message);
            resp.setStatus(HttpStatus.BAD_REQUEST.value());
            return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(),  message, null);
        }
        resp.setStatus(HttpStatus.CREATED.value());
        return new ApiResponse<>(HttpStatus.CREATED.value(), "TopicProposal created", tp);
    }

    /**
     * PUT  /proposal/{id} -> Update an existing TopicProposal.
     */
    @PutMapping(value = "/proposal/{id}")
     public ApiResponse<TopicProposal> updateTopicProposal(@PathVariable("id") long id, @RequestBody TopicProposal proposalIn)  {
        log.debug("REST-PUT request to update TopicProposal : {}", proposalIn);
        return lightningTalksService.findTopicProposalById(id)
                .map(foundForUpdate -> {
                    BeanUtils.copyProperties(proposalIn, foundForUpdate);
                    TopicProposal updated = lightningTalksService.updateTopicProposal(foundForUpdate);
                    return new ApiResponse<TopicProposal>(HttpStatus.OK.value(), "TopicProposal fetched successfully", updated);
                })
                .orElse(new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "TopicProposal update failed", null));
    }

    /**
     * DELETE  /proposal/:id -> delete TopicProposal by "id".
     */
    @DeleteMapping(value = "/proposal/{id}")
    public ApiResponse<Void>  deleteTopicProposal(@PathVariable Long id) {
        log.debug("REST request to delete Things : {}", id);
        lightningTalksService.deleteTopicProposalById(id);
        return new ApiResponse<>(HttpStatus.OK.value(), "User fetched successfully.", null);
    }


    //** Submission *************************************************************************************************


    /**
     * POST  /submission -> Add/Create a new Submission.
     */
    @PostMapping(value = "/submission")
    public ApiResponse<Submission> create(@RequestBody Submission submission, @Context HttpServletResponse resp) throws URISyntaxException {
        log.debug("REST-POST request to save new Submission : {}", submission);
        if (submission.getId() != null) {//TODO refactor into util
            resp.setHeader("Failure", "New object cannot already have an id.");
            resp.setStatus(HttpStatus.BAD_REQUEST.value());
            return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), "New object cannot already have an id.", null);
        }
        // default some values
        if (submission.getApproved() == null) {
            submission.setApproved(false);
        }
        if (submission.getTargetLightningTalkDate() == null) {
            submission.setTargetLightningTalkDate(TalkDateStreamUtil.nextTalkDate());
        }
        //save
        submission.setSubmitted(LocalDateTime.now());
        try {
            submission = lightningTalksService.updateSubmission(submission);
        } catch (ConstraintViolationException e) { //TODO refactor into common util
            ConstraintViolation<?> next = e.getConstraintViolations().iterator().next();
            String message = next.getMessage();
            String propertyName = next.getPropertyPath().toString();
            resp.setHeader("Failure", propertyName + " " + message);
            resp.setStatus(HttpStatus.BAD_REQUEST.value());
            return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), propertyName + " " + message, null);
        } catch  (DataAccessException dae){
            String message = dae.getMessage();
            resp.setHeader("Failure",  message);
            resp.setStatus(HttpStatus.BAD_REQUEST.value());
            return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(),  message, null);
        }
        resp.setStatus(HttpStatus.CREATED.value());
        return new ApiResponse<>(HttpStatus.CREATED.value(), "TopicProposal created", submission);
    }

}