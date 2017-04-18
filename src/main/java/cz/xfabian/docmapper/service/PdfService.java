package cz.xfabian.docmapper.service;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Norbert Fabian on 29.12.2016.
 */
public interface PdfService {

    void docxToPdf(String fileName) throws FileNotFoundException;

    void mergePdf(String acronym) throws IOException;
}
