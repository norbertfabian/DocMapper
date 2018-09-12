package cz.xfabian.docmapper.service;

import cz.xfabian.docmapper.entity.Partner;

import java.io.IOException;
import java.util.List;

public interface PartnerService {

    List<Partner> getPartners() throws IOException;

    String findLatestPartnersVersionDoc();
}
