package cz.xfabian.docmapper.service;

import cz.xfabian.docmapper.entity.Partner;

import java.io.IOException;
import java.util.List;

/**
 * Created by Norbert Fabian on 26.12.2016.
 */

public interface XlsxService {

    List<Partner>  readOrganizations(String file) throws IOException;
}
