package com.leeGilbert.ltk.rest;

import com.leeGilbert.ltk.service.LightningTalksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LightningTalksController {

    @Autowired
    private LightningTalksService lightningTalksService;

    @GetMapping("/")
    @ResponseBody
    @Transactional(readOnly = true)
    public String helloWorld() { //FIXME just a sanity check
        return this.lightningTalksService.getTalkProposal("Microservices").getTopic();
    }

}
