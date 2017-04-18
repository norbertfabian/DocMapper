package cz.xfabian.docmapper.facade;

import java.io.IOException;
import java.util.List;

/**
 * @author Norbert Fabian
 */
public interface DocumentFacade {

    public List<String> getDocuments();

    public String getDocumentLocation(String docName);

    public void delete(String docName);

    public void deleteAll();
}
