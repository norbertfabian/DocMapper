package cz.xfabian.docmapper.controller;

import cz.xfabian.docmapper.facade.DocumentFacade;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.*;

/**
 * @author Norbert Fabian
 */
@Controller("/documents")
public class DocumentController {

    @Autowired
    private DocumentFacade documentFacade;

    @RequestMapping(method = RequestMethod.GET)
    public String getDocuments(Model model) {
        model.addAttribute("documents", documentFacade.getDocuments());
        return "documents";
    }

    @RequestMapping(value = "/documents/{type}/{fileName}", method = RequestMethod.GET)
    public HttpEntity<byte[]> getFile(
            @PathVariable("type") String type,
            @PathVariable("fileName") String fileName) throws IOException {
        String fileLoc = documentFacade.getDocumentLocation(fileName + "." + type);
        FileInputStream fileInputStream = new FileInputStream(new File(fileLoc));
        byte[] document = IOUtils.toByteArray(fileInputStream);

        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_PDF);
        header.set(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=" + (fileName + ".pdf").replace(" ", "_"));
        header.setContentLength(document.length);
        fileInputStream.close();
        return new HttpEntity<>(document, header);
    }

    @RequestMapping(value = "/documents/merge", method = RequestMethod.GET)
    public String mergedPdf(UriComponentsBuilder uriBuilder) throws IOException {
        documentFacade.mergePdfs();
        return "redirect:" + uriBuilder.path("/documents").toUriString();
    }

    @RequestMapping(value = "/documents/delete/{fileName}", method = RequestMethod.GET)
    public String deleteFile(@PathVariable("fileName") String fileName,
                             UriComponentsBuilder uriBuilder) {
        documentFacade.delete(fileName);
        return "redirect:" + uriBuilder.path("/documents").toUriString();
    }

    @RequestMapping(value = "/documents/deleteall", method = RequestMethod.GET)
    public String deleteAll(UriComponentsBuilder uriBuilder) {
        documentFacade.deleteAll();
        return "redirect:" + uriBuilder.path("/documents").toUriString();
    }
}
