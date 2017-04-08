package cz.xfabian.docmapper.service.impl;

import cz.xfabian.docmapper.enums.FilePathEnums;
import cz.xfabian.docmapper.service.FileService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Norbert Fabian on 27.12.2016.
 */

@Service
public class FileServiceImpl implements FileService {

    @Override
    public List<String> listAllFiles(String dir) {
        File folder = new File(dir);
        File[] listOfFiles = folder.listFiles();
        List<String> fileNames = new ArrayList<>();

        for (int i = 0; i < listOfFiles.length; i++) {
            fileNames.add(listOfFiles[i].getName());
        }
        return fileNames;
    }

    @Override
    public void delete(String docName) {
        new File(FilePathEnums.OUTPUT_DOCX + docName + ".docx").delete();
        new File(FilePathEnums.OUTPUT_PDF + docName + ".pdf").delete();
    }
}
