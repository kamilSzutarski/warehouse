package com.kamil.warehouse.controller;

import com.kamil.warehouse.domain.DocumentHeader;
import com.kamil.warehouse.service.DocumentHeaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/document-header")

public class DocumentHeaderController {

    @Autowired
    private DocumentHeaderService documentHeaderService;

    @PostMapping
    public DocumentHeader saveDocumentHeader(@RequestBody DocumentHeader documentHeader) {
        return documentHeaderService.save(documentHeader);
    }

    @GetMapping("/{id}")
    public DocumentHeader getDocumentHeader(@PathVariable Long id) {
        return documentHeaderService.getById(id);
    }

    @GetMapping
    public Page<DocumentHeader> getPage(@RequestParam Integer page, @RequestParam Integer size) {
        return documentHeaderService.getPage(PageRequest.of(page, size));
    }

    @GetMapping("/ident/{ident}")
    public List<DocumentHeader> findByOperator(@PathVariable String ident){
        return documentHeaderService.findByOperatorContaining(ident);
    }

    @GetMapping("/amount/{amount}")
    public List<DocumentHeader> findByAmount(@PathVariable Double amount){
        return documentHeaderService.findByAmountContaining(amount);
    }

    @GetMapping("/year/{year}")
    public List<DocumentHeader> findByYear(@PathVariable Integer year){
        return documentHeaderService.findByYearContaining(year);
    }

    @GetMapping("/month/{month}")
    public List<DocumentHeader> findByMonth(@PathVariable Integer month){
        return documentHeaderService.findByMonthContaining(month);
    }

    @GetMapping("/number/{number}")
    public List<DocumentHeader> findByNumber(@PathVariable Integer number){
        return documentHeaderService.findByNumberContaining(number);
    }

    @GetMapping("/series/{series}")
    public List<DocumentHeader> findBySeries(@PathVariable String series){
        return documentHeaderService.findBySeriesContaining(series);
    }

    @GetMapping("/source/{source}")
    public List<DocumentHeader> findBySourceWh(@PathVariable String source){
        return documentHeaderService.findBySourceWhContaining(source);
    }

    @GetMapping("/target/{target}")
    public List<DocumentHeader> findByTargetWh(@PathVariable String target){
        return documentHeaderService.findByTargetWhContaining(target);
    }
}
