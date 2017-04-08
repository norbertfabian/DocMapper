package cz.xfabian.docmapper.facade.impl;

import cz.xfabian.docmapper.enums.DocumentEnum;
import cz.xfabian.docmapper.enums.FilePathEnums;
import cz.xfabian.docmapper.facade.DocumentFacade;
import cz.xfabian.docmapper.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Norbert Fabian
 */
@Service
public class DocumentFacadeImpl implements DocumentFacade {

    @Autowired
    private FileService fileService;

    @Override
    public List<String> getDocuments(DocumentEnum docType) {
        List<String> documents = new ArrayList<>();
        if(docType.equals(DocumentEnum.ALL) || docType.equals(DocumentEnum.PDF)) {
            List<String> docxs = fileService.listAllFiles(FilePathEnums.OUTPUT_PDF);
            docxs.forEach(d -> documents.add(d));
        }
        if(docType.equals(DocumentEnum.ALL) || docType.equals(DocumentEnum.DOCX)) {
            List<String> docxs = fileService.listAllFiles(FilePathEnums.OUTPUT_DOCX);
            docxs.forEach(d -> documents.add(d));
        }
        return documents;
    }

    @Override
    public String getDocumentLocation(String docName) {
        String prefix = "";
        if(docName.endsWith(".pdf"))
            prefix = FilePathEnums.OUTPUT_PDF;
        if(docName.endsWith(".docx"))
            prefix = FilePathEnums.OUTPUT_DOCX;
        return prefix + docName;
    }

    @Override
    public void delete(String docName) {
        fileService.delete(docName);
    }
}
