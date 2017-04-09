package cz.xfabian.docmapper.facade.impl;

import cz.xfabian.docmapper.enums.FilePathEnums;
import cz.xfabian.docmapper.facade.DocumentFacade;
import cz.xfabian.docmapper.service.FileService;
import cz.xfabian.docmapper.service.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author Norbert Fabian
 */
@Service
public class DocumentFacadeImpl implements DocumentFacade {

    @Autowired
    private FileService fileService;

    @Autowired
    private PdfService pdfService;

    @Override
    public List<String> getDocuments() {
        List<String> documents = new ArrayList<>();
        List<String> pdfs = fileService.listAllFiles(FilePathEnums.OUTPUT_PDF);
        pdfs.addAll(fileService.listAllFiles(FilePathEnums.OUTPUT_DOCX));
        pdfs.forEach(d -> documents.add((d != null && !d.isEmpty()) ? d.split("\\.")[0] : d));
        return distinct(documents);
    }

    @Override
    public void mergePdfs() throws IOException {
        pdfService.mergePdf();
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
    public void deleteAll() {
        List<String> documents = getDocuments();
        documents.forEach(d -> delete(d));
    }

    @Override
    public void delete(String docName) {
        fileService.delete(docName);
    }

    private List<String> distinct(List<String> list) {
        Set<String> hs = new HashSet<>();
        hs.addAll(list);
        list.clear();
        list.addAll(hs);
        return list;
    }
}
