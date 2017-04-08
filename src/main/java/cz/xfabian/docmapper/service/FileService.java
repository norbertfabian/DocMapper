package cz.xfabian.docmapper.service;

import java.util.List;

/**
 * Created by Norbert Fabian on 27.12.2016.
 */
public interface FileService {

    /**
     * Returns names of all files in a directory as list.
     *
     * @param dir Path to the directory
     * @return List of all file names
     */
    List<String> listAllFiles(String dir);

    void delete(String docName);
}
