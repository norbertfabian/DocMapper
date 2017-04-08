package cz.xfabian.docmapper.controller;

import cz.xfabian.docmapper.enums.DocumentEnum;
import cz.xfabian.docmapper.facade.DocumentFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
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
        model.addAttribute("documents", documentFacade.getDocuments(DocumentEnum.ALL));
        return "documents";
    }

    @RequestMapping(value = "/documents/{type}/{fileName}", method = RequestMethod.GET)
    public void getFile(
            @PathVariable("type") String type,
            @PathVariable("fileName") String fileName,
            Model model,
            HttpServletResponse response) {
        try {
            String fileLoc = documentFacade.getDocumentLocation(fileName + "." + type);
            InputStream is = new FileInputStream(fileLoc);
            org.apache.commons.io.IOUtils.copy(is, response.getOutputStream());
            response.flushBuffer();
        } catch (IOException ex) {
            throw new RuntimeException("File doesn't exist!");
        }
    }

    @RequestMapping(value = "/documents/delete/{fileName}", method = RequestMethod.GET)
    public String deleteFile(@PathVariable("fileName") String fileName,
                             UriComponentsBuilder uriBuilder) {
        documentFacade.delete(fileName);
        return "redirect:" + uriBuilder.path("/documents").toUriString();
    }
}
