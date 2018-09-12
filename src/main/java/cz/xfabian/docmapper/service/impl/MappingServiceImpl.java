package cz.xfabian.docmapper.service.impl;

import cz.xfabian.docmapper.entity.Partner;
import cz.xfabian.docmapper.enums.VariableEnums;
import cz.xfabian.docmapper.service.MappingService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Norbert Fabian on 26.12.2016.
 */

@Service
public class MappingServiceImpl implements MappingService {

    @Override
    public List<Map<String, String>> partnersToMaps(List<Partner> partners) {
        List<Map<String, String>> partnerMaps = new ArrayList<Map<String, String>>();
        for(Partner partner: partners) {
            partnerMaps.add(partnerToMap(partner));
        }
        return partnerMaps;
    }

    private Map<String,String> partnerToMap(Partner partner) {
        Map<String, String> map = new HashMap<String, String>();
        map.put(VariableEnums.PARTNER_PIC, getValueOrEmpty(partner.getPic()));
        map.put(VariableEnums.PARTNER_ADDRESS, getValueOrEmpty(partner.getAddress()));
        map.put(VariableEnums.PARTNER_CITY, getValueOrEmpty(partner.getCity()));
        map.put(VariableEnums.PARTNER_COUNTRY, getValueOrEmpty(partner.getCountry()));
        map.put(VariableEnums.PARTNER_LEGAL_SATUS, getValueOrEmpty(partner.getLegalStatus()));
        map.put(VariableEnums.PARTNER_ORGANISATION_ACRONOMY, getValueOrEmpty(partner.getAcronomy()));
        map.put(VariableEnums.PARTNER_REPRESENTATIVE_FIRSTNAME, getValueOrEmpty(partner.getRepresentativeFirstName()));
        map.put(VariableEnums.PARTNER_REPRESENTATIVE_SURENAME, getValueOrEmpty(partner.getRepresentativeSurname()));
        map.put(VariableEnums.PARTNER_REPRESENTATIVE_FUNCTION, getValueOrEmpty(partner.getRepresentativeFunction()));
        map.put(VariableEnums.PARTNER_REGISTRATION_NO, getValueOrEmpty(partner.getRegistrationNumber()));
        map.put(VariableEnums.PARTNER_ORGANISATION_NAME, getValueOrEmpty(partner.getName()));
        map.put(VariableEnums.PARTNER_VAT, getValueOrEmpty(partner.getVat()));
        map.put(VariableEnums.PARTNER_DESCRIPTION, getValueOrEmpty(partner.getDescription()));
        map.put(VariableEnums.PARTNER_ZIP, getValueOrEmpty(partner.getZip()));
        map.put(VariableEnums.PARTNER_EMAIL, getValueOrEmpty(partner.getEmail()));
        map.put(VariableEnums.PARTNER_PHONE, getValueOrEmpty(partner.getPhone()));
        return map;
    }

    private String getValueOrEmpty(String value) {
        if(value != null) {
            return value;
        }
        return "";
    }
}
