package cz.xfabian.docmapper.service.impl;

import com.documents4j.api.DocumentType;
import com.documents4j.api.IConverter;
import com.documents4j.job.LocalConverter;
import cz.xfabian.docmapper.enums.FilePathEnums;
import cz.xfabian.docmapper.service.PdfService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Created by Norbert Fabian on 29.12.2016.
 */

@Service
public class PdfServiceImpl implements PdfService {

    public void docxToPdf(String fileName) throws FileNotFoundException {
        File input = new File(FilePathEnums.OUTPUT_DOCX + fileName + ".docx");
        File output = new File(FilePathEnums.OUTPUT_PDF + fileName + ".pdf");

        IConverter converter = LocalConverter.builder()
                .workerPool(20, 25, 2, TimeUnit.SECONDS)
                .processTimeout(5, TimeUnit.SECONDS)
                .build();

        Future<Boolean> conversion = converter.convert(input).as(DocumentType.MS_WORD)
                .to(output).as(DocumentType.PDF)
                .prioritizeWith(1000)
                .schedule();
    }
}
