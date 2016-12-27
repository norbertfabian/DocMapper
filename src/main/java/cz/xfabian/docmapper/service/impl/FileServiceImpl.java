package cz.xfabian.docmapper.service.impl;

import cz.xfabian.docmapper.service.FileService;
import org.springframework.stereotype.Service;

import java.io.File;
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
}
