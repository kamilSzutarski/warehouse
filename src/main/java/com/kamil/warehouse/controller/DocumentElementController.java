package com.kamil.warehouse.controller;

import com.kamil.warehouse.domain.DocumentElement;
import com.kamil.warehouse.service.DocumentElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/document-element")

public class DocumentElementController {

    @Autowired
    private DocumentElementService documentElementService;

    @PostMapping
    public DocumentElement saveDocumentElement(@RequestBody DocumentElement documentElement) {
        return documentElementService.save(documentElement);
    }

    @GetMapping("/{id}")
    public DocumentElement getDocumentElement(@PathVariable Long id) {
        return documentElementService.getById(id);
    }

    @GetMapping
    public Page<DocumentElement> getPage(@RequestParam Integer page, @RequestParam Integer size) {
        return documentElementService.getPage(PageRequest.of(page, size));
    }
}
