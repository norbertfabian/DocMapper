package cz.xfabian.docmapper.facade.impl;

import cz.xfabian.docmapper.dto.OrganizationsInfoDto;
import cz.xfabian.docmapper.entity.Partner;
import cz.xfabian.docmapper.enums.FilePathEnums;
import cz.xfabian.docmapper.enums.VariableEnums;
import cz.xfabian.docmapper.facade.DocxFillerFacade;
import cz.xfabian.docmapper.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Norbert Fabian on 26.12.2016.
 */

@Service
public class DocxFillerFacadeImpl implements DocxFillerFacade {

    @Autowired
    private DocxService docxService;

    @Autowired
    private MappingService mappingService;

    @Autowired
    private XlsxService xlsxService;

    @Autowired
    private FileService fileService;

    @Autowired
    private PdfService pdfService;

    @Override
    public String FillData(OrganizationsInfoDto values) throws IOException {
        List<Partner> partners = new ArrayList();
        List<Partner> allPartners = xlsxService.readOrganizations(FilePathEnums.PARTNERS);

        for(String pic: values.getPics()) {
            allPartners.stream().filter(p -> p.getPic().equals(pic)).forEach(partners::add);
        }

        List<Map<String, String>> variables = mappingService.partnersToMaps(partners);
        variables = joinProjectName(variables, values.getProjectTitle(), values.getSignatureDate());
        for(int order = 0; order < variables.size(); order++) {
            String outputFile = createOutputName(order + 1, values, variables.get(order));
            docxService.fillTemplate(FilePathEnums.TEMPLATES_DIR + "/" + values.getTemplate(),
                    outputFile, variables.get(order));
            pdfService.docxToPdf(outputFile);
        }
        return getEmails(partners);
    }

    @Override
    public List<String> getTemplates() {
        return fileService.listAllFiles(FilePathEnums.TEMPLATES_DIR);
    }

    private List<Map<String, String>> joinProjectName(List<Map<String, String>> variablesList,
                                                      String projectName, String signatureDate) {
        for(Map<String, String> vars: variablesList) {
            vars.put(VariableEnums.PROJECT_TITLE, projectName);
            String signDate = signatureDate.isEmpty() ? getFormattedCurrentDate() : signatureDate;
            vars.put(VariableEnums.SIGNATURE_DATE, signDate);
        }
        return variablesList;
    }
    
    private String getEmails(List<Partner> partners) {
        StringBuilder stringBuilder = new StringBuilder();
        partners.stream().forEach(p -> stringBuilder.append(p.getEmail() + "; "));
        stringBuilder.setLength(stringBuilder.length() - 2);
        return stringBuilder.toString();
    }

    private String createOutputName(int order, OrganizationsInfoDto dto, Map<String, String> vars) {
        return dto.getProjectAcronym() + "_" + String.format("%02d", order) + "_"
                + vars.get(VariableEnums.PARTNER_COUNTRY);
    }

    private String getFormattedCurrentDate() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("dd.MM.yyyy");
        Date now = new Date();
        return sdfDate.format(now);
    }
}
