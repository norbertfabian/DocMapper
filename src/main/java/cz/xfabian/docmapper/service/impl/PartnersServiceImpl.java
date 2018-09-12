package cz.xfabian.docmapper.service.impl;

import cz.xfabian.docmapper.entity.Partner;
import cz.xfabian.docmapper.enums.FilePathEnums;
import cz.xfabian.docmapper.service.FileService;
import cz.xfabian.docmapper.service.PartnerService;
import cz.xfabian.docmapper.service.XlsxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class PartnersServiceImpl implements PartnerService {

    @Autowired
    private FileService fileService;

    @Autowired
    private XlsxService xlsxService;

    @Override
    public List<Partner> getPartners() throws IOException {
        String partnersDoc = findLatestPartnersVersionDoc();
        String file = FilePathEnums.PARTNERS + partnersDoc;
        return xlsxService.readOrganizations(file);
    }

    @Override
    public String findLatestPartnersVersionDoc() {
        List<String> partnersFiles = fileService.listAllFiles(FilePathEnums.PARTNERS);
        int maxVer = 0;

        for(String partnersFile : partnersFiles) {
            String versionNum = partnersFile.replaceAll("[^0-9]", "");
            maxVer = Math.max(maxVer, Integer.valueOf(versionNum));
        }

        return "Partners.v" + maxVer + ".xlsx";
    }
}
