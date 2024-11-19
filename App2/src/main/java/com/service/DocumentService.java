package com.service;

import com.model.documents.*;
import com.model.exceptions.DocumentNotFoundException;
import com.repository.DocumentRepository;

import java.util.List;

public class DocumentService {
    /**
     * Add doc.
     *
     * @param doc document
     */
    public void addDocument(Document doc) {
        DocumentRepository.addDocument(doc);
    }

    /**
     * Remove doc.
     *
     * @param ISBN doc ISBN
     * @param docType doc type
     * @param batch photo batch
     */
    public void removeDocument(String ISBN, String docType, int batch)  {
        DocumentRepository.removeDocument(ISBN, docType, batch);
    }

    /**
     * Update doc.
     *
     * @param doc document
     */
    public void updateDocument(Document doc) {
        DocumentRepository.updateDocument(doc);
    }

    /**
     * Find doc.
     *
     * @param keyword word to find doc
     * @throws DocumentNotFoundException Doc related
     * @return list
     */
    public List<Document> findDocument(String keyword)
            throws DocumentNotFoundException {
        return DocumentRepository.findDocument(keyword);
    }

    /**
     * get all doc.
     *
     * @return list
     */
    public List<Document> viewAllDocuments() {
        return DocumentRepository.viewAllDocuments();
    }
}

