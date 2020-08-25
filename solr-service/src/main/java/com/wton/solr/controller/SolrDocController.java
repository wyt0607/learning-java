package com.wton.solr.controller;

import com.wton.solr.entity.SolrDoc;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.data.solr.server.support.SolrClientUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author wton
 */
@RestController
@RequestMapping("/")
public class SolrDocController {

    private SolrTemplate solrTemplate;

    public SolrDocController(SolrTemplate solrTemplate) {
        this.solrTemplate = solrTemplate;
    }


    @ApiIgnore
    @GetMapping("doc")
    public ModelAndView toDoc() {
        return new ModelAndView("doc.html");
    }

    @GetMapping
    public String getSolrDoc(SolrDoc solrDoc) {
        SimpleQuery simpleQuery = new SimpleQuery()
                .addCriteria(Criteria.where("msg").is(solrDoc.getMsg()));
        solrTemplate.query(SolrClientUtils.resolveSolrCoreName(SolrDoc.class), simpleQuery, SolrDoc.class);
        return "1";
    }

    @PostMapping
    public String addSolrDoc(SolrDoc solrDoc) {
        solrTemplate.saveBean(SolrClientUtils.resolveSolrCoreName(SolrDoc.class), solrDoc);
        return "1";
    }

    @PutMapping
    public String updateSolrDoc(SolrDoc solrDoc) {
        solrTemplate.saveBean(SolrClientUtils.resolveSolrCoreName(SolrDoc.class), solrDoc);
        return "1";
    }

    @DeleteMapping
    public String delSolrDoc(String id) {
        solrTemplate.deleteByIds(SolrClientUtils.resolveSolrCoreName(SolrDoc.class), id);
        return "1";
    }


}
