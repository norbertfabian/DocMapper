package cz.xfabian.docmapper.service;

import java.util.Map;

/**
 * Created by Norbert Fabian on 26.12.2016.
 */

public interface DocxService {

    /**
     * Fills the template with the given variables a saves it.
     *
     * @param template Path to the template as String
     * @param output Path and name of the output file as String
     * @param variablesMap Map of String key-value variables to fill into the template
     */
    void fillTemplate(String template, String output, Map<String, String> variablesMap);
}
