package cz.xfabian.docmapper.comparator;

import cz.xfabian.docmapper.entity.Partner;

import java.util.Comparator;

/**
 * Created by Norbert Fabian on 27.12.2016.
 */
public class PartnerComparator implements Comparator<Partner> {

    @Override
    public int compare(Partner p1, Partner p2) {
        int countryComparingResult = p1.getCountry().compareToIgnoreCase(p2.getCountry());
        return countryComparingResult == 0 ? p1.getName().compareToIgnoreCase(p2.getName()) : countryComparingResult;
    }
}
