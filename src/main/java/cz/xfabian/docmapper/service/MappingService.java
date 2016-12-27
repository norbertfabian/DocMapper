package cz.xfabian.docmapper.service;

import cz.xfabian.docmapper.entity.Partner;

import java.util.List;
import java.util.Map;

/**
 * Created by Norbert Fabian on 26.12.2016.
 */
public interface MappingService {

    List<Map<String, String>> partnersToMaps(List<Partner> partners);
}
