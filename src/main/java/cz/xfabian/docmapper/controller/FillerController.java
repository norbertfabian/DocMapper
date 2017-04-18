package cz.xfabian.docmapper.controller;

import cz.xfabian.docmapper.dto.OrganizationsInfoDto;
import cz.xfabian.docmapper.entity.Partner;
import cz.xfabian.docmapper.enums.FilePathEnums;
import cz.xfabian.docmapper.facade.DocxFillerFacade;
import cz.xfabian.docmapper.service.FileService;
import cz.xfabian.docmapper.service.XlsxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.List;

/**
 * Created by Norbert Fabian on 26.12.2016.
 */

@Controller
public class FillerController {

    @Autowired
    private DocxFillerFacade docxFillerFacade;

    @Autowired
    private XlsxService xlsxService;

    @Autowired
    private FileService fileService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getPage(Model model) throws IOException {
        model.addAttribute("values", new OrganizationsInfoDto());
        return "index";
    }

    @RequestMapping(value = "/process", method = RequestMethod.POST)
    public String process(@ModelAttribute("values") OrganizationsInfoDto values,
                          RedirectAttributes redirectAttributes,
                          UriComponentsBuilder uriBuilder) throws IOException {
        String emails = docxFillerFacade.FillData(values);
        redirectAttributes.addFlashAttribute("emails", emails);
        return  "redirect:" + uriBuilder.path("/documents").toUriString();
    }

    @ModelAttribute("partners")
    public List<Partner> partners() throws IOException {
        return xlsxService.readOrganizations(FilePathEnums.PARTNERS);
    }

    @ModelAttribute("templates")
    public List<String> templates() {
        return docxFillerFacade.getTemplates();
    }
}
