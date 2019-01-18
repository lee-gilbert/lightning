package com.leeGilbert.ltk.rest;

import com.leeGilbert.ltk.domain.TopicProposal;
import com.leeGilbert.ltk.service.LightningTalksService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 *  REST endpoints for LightningTalks.
 */
@RestController
@RequestMapping("/api")
@Slf4j
public class LightningTalksController {

    @Autowired
    private LightningTalksService lightningTalksService;

    //** TopicProposal *************************************************************************************************
    /**
     * GET  /proposal -> Retrieves TopicProposal list.
     */
    @GetMapping(value = "/proposal", produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional(readOnly = true)
    @CrossOrigin(origins = "*")
    public List<TopicProposal> findAllTopicProposal() {
        log.debug("REST-GET request to retrieve TopicProposal ");
        return lightningTalksService.findAllTopicProposal();
    }

    /**
     * GET  /proposal/{id} -> Retrieves a TopicProposal.
     */
    @GetMapping(value = "/proposal/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional(readOnly = true)
    @CrossOrigin(origins = "*")
    public ResponseEntity<TopicProposal> findTopicProposalById(@PathVariable Long id) {
        log.debug("REST-GET request to retrieve TopicProposal id: {}", id);
        return lightningTalksService.findTopicProposalById(id)
                .map(found -> new ResponseEntity<>(found, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * POST  /proposal -> Create a new TopicProposal.
     */
    @PostMapping(value = "/proposal", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity<Void> create(@RequestBody TopicProposal tp) throws URISyntaxException {
        log.debug("REST-POST request to save new TopicProposal : {}", tp);
        if (tp.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "New object cannot already have an id.").build();
        }
        tp = lightningTalksService.updateTopicProposal(tp);
        return ResponseEntity.created(new URI("/api/proposal/" + tp.getId())).build();
    }

    /**
     * PUT  /proposal/{id} -> Updates an existing TopicProposal.
     */
    @PutMapping(value = "/proposal/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
     public ResponseEntity<TopicProposal> updateTopicProposal(@PathVariable("id") long id, @RequestBody TopicProposal proposalIn)  {
        log.debug("REST-PUT request to update TopicProposal : {}", proposalIn);
        return lightningTalksService.findTopicProposalById(id)
                .map(foundForUpdate -> {
                    BeanUtils.copyProperties(proposalIn, foundForUpdate);
                    TopicProposal updated = lightningTalksService.updateTopicProposal(foundForUpdate);
                    return new ResponseEntity<>(updated, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    /**
     * DELETE  /proposal/:id -> delete TopicProposal by "id".
     */
    @RequestMapping(value = "/proposal/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity<Void>  deleteTopicProposal(@PathVariable Long id) {
        log.debug("REST request to delete Things : {}", id);
        lightningTalksService.deleteTopicProposalById(id);
        return ResponseEntity.ok().build();
    }
}