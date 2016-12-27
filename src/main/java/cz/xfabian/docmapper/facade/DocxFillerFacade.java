package cz.xfabian.docmapper.facade;

import cz.xfabian.docmapper.dto.OrganizationsInfoDto;

import java.io.IOException;
import java.util.List;

/**
 * Created by Norbert Fabian on 26.12.2016.
 */
public interface DocxFillerFacade {

    void FillData(OrganizationsInfoDto values) throws IOException;

    public List<String> getTemplates();
}
