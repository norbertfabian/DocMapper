package cz.xfabian.docmapper.dto;

import java.util.List;

/**
 * Created by Norbert Fabian on 26.12.2016.
 */
public class OrganizationsInfoDto {

    private List<String> pics;
    private String projectTitle;
    private String projectAcronym;
    private String signatureDate;
    private String template;

    public List<String> getPics() {
        return pics;
    }

    public void setPics(List<String> pics) {
        this.pics = pics;
    }

    public String getProjectTitle() {
        return projectTitle;
    }

    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getProjectAcronym() {
        return projectAcronym;
    }

    public void setProjectAcronym(String projectAcronym) {
        this.projectAcronym = projectAcronym;
    }

    public String getSignatureDate() {
        return signatureDate;
    }

    public void setSignatureDate(String signatureDate) {
        this.signatureDate = signatureDate;
    }
}
