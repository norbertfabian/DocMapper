package cz.xfabian.docmapper.facade.impl;

import cz.xfabian.docmapper.dto.OrganizationsInfoDto;
import cz.xfabian.docmapper.entity.Partner;
import cz.xfabian.docmapper.enums.FilePathEnums;
import cz.xfabian.docmapper.enums.VariableEnums;
import cz.xfabian.docmapper.facade.DocxFillerFacade;
import cz.xfabian.docmapper.service.DocxService;
import cz.xfabian.docmapper.service.FileService;
import cz.xfabian.docmapper.service.MappingService;
import cz.xfabian.docmapper.service.XlsxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
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

    @Override
    public void FillData(OrganizationsInfoDto values) throws IOException {
        List<Partner> partners = new ArrayList();
        List<Partner> allPartners = xlsxService.readOrganizations(FilePathEnums.PARTNERS);

        for(String pic: values.getPics()) {
            allPartners.stream().filter(p -> p.getPic().equals(pic)).forEach(partners::add);
        }

        List<Map<String, String>> variables = mappingService.partnersToMaps(partners);
        variables = joinProjectName(variables, values.getProjectTitle());
        for(int order = 0; order < variables.size(); order++) {
            docxService.fillTemplate(FilePathEnums.TEMPLATES_DIR + "/" + values.getTemplate(),
                    createOutputName(order + 1, values, variables.get(order)), variables.get(order));
        }
    }

    @Override
    public List<String> getTemplates() {
        return fileService.listAllFiles(FilePathEnums.TEMPLATES_DIR);
    }

    private List<Map<String, String>> joinProjectName(List<Map<String, String>> variablesList, String projectName) {
        for(Map<String, String> vars: variablesList) {
            vars.put(VariableEnums.PROJECT_TITLE, projectName);
        }
        return variablesList;
    }

    private String createOutputName(int order, OrganizationsInfoDto dto, Map<String, String> vars) {
        return FilePathEnums.OUTPUT_DIR + dto.getProjectAcronym() + "_" + String.format("%02d", order) + "_"
                + vars.get(VariableEnums.PARTNER_COUNTRY) + "_"
                + vars.get(VariableEnums.PARTNER_ORGANISATION_NAME) + ".docx";
    }
}
