package cz.xfabian.docmapper.facade;

import cz.xfabian.docmapper.enums.DocumentEnum;

import java.util.List;

/**
 * @author Norbert Fabian
 */
public interface DocumentFacade {

    public List<String> getDocuments(DocumentEnum docType);

    public String getDocumentLocation(String docName);

    public void delete(String docName);
}
