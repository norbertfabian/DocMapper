package cz.xfabian.docmapper.service.impl;

import cz.xfabian.docmapper.enums.FilePathEnums;
import cz.xfabian.docmapper.service.DocxService;
import org.springframework.stereotype.Service;
import pl.jsolve.templ4docx.core.Docx;
import pl.jsolve.templ4docx.core.VariablePattern;
import pl.jsolve.templ4docx.variable.TextVariable;
import pl.jsolve.templ4docx.variable.Variables;

import java.util.Map;

/**
 * Created by Norbert Fabian on 26.12.2016.
 */

@Service
public class DocxServiceImpl implements DocxService{

    public static final String PREFIX = "${";
    public static final String SUFFIX = "}";

    @Override
    public void fillTemplate(String template, String output, Map<String, String> variablesMap) {
        Docx docx = new Docx(template);
        docx.setVariablePattern(new VariablePattern(PREFIX, SUFFIX));
        Variables variables = convertToDocxVariables(variablesMap);
        docx.fillTemplate(variables);
        docx.save(FilePathEnums.OUTPUT_DOCX + output + ".docx");
    }

    /**
     * Converts variables from map to a docx variables.
     *
     * @param variablesMap Map of String key-value variables to convert
     * @return Docx variables
     */
    private Variables convertToDocxVariables(Map<String, String> variablesMap) {
        Variables vars = new Variables();
        for(String key: variablesMap.keySet()) {
            vars.addTextVariable(new TextVariable(PREFIX + key + SUFFIX, variablesMap.get(key)));
        }
        return vars;
    }
}
