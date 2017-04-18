package cz.xfabian.docmapper.entity;

/**
 * Created by Norbert Fabian on 26.12.2016.
 */
public class Partner {

    private String pic;
    private String representativeFirstName;
    private String representativeSurname;
    private String name;
    private String acronomy;
    private String country;
    private String city;
    private String vat;
    private String address;
    private String legalStatus;
    private String registrationNumber;
    private String representativeFunction;
    private String zip;
    private String email;


    public String getPic() {
        return pic;
    }

    public Partner setPic(String pic) {
        this.pic = pic;
        return this;
    }

    public String getRepresentativeFirstName() {
        return representativeFirstName;
    }

    public Partner setRepresentativeFirstName(String representativeFirstName) {
        this.representativeFirstName = representativeFirstName;
        return this;
    }

    public String getRepresentativeSurname() {
        return representativeSurname;
    }

    public Partner setRepresentativeSurname(String representativeSurname) {
        this.representativeSurname = representativeSurname;
        return this;
    }

    public String getName() {
        return name;
    }

    public Partner setName(String name) {
        this.name = name;
        return this;
    }

    public String getAcronomy() {
        return acronomy;
    }

    public Partner setAcronomy(String acronomy) {
        this.acronomy = acronomy;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public Partner setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getCity() {
        return city;
    }

    public Partner setCity(String city) {
        this.city = city;
        return this;
    }

    public String getVat() {
        return vat;
    }

    public Partner setVat(String vat) {
        this.vat = vat;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Partner setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getLegalStatus() {
        return legalStatus;
    }

    public Partner setLegalStatus(String legalStatus) {
        this.legalStatus = legalStatus;
        return this;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public Partner setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
        return this;
    }

    public String getRepresentativeFunction() {
        return representativeFunction;
    }

    public Partner setRepresentativeFunction(String representativeFunction) {
        this.representativeFunction = representativeFunction;
        return this;
    }

    public String getZip() {
        return zip;
    }

    public Partner setZip(String zip) {
        this.zip = zip;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Partner setEmail(String email) {
        this.email = email;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Partner)) return false;

        Partner partner = (Partner) o;

        if (getPic() != null ? !getPic().equals(partner.getPic()) : partner.getPic() != null) return false;
        if (getRepresentativeFirstName() != null ? !getRepresentativeFirstName().equals(partner.getRepresentativeFirstName()) : partner.getRepresentativeFirstName() != null)
            return false;
        if (getRepresentativeSurname() != null ? !getRepresentativeSurname().equals(partner.getRepresentativeSurname()) : partner.getRepresentativeSurname() != null)
            return false;
        if (getName() != null ? !getName().equals(partner.getName()) : partner.getName() != null) return false;
        if (getAcronomy() != null ? !getAcronomy().equals(partner.getAcronomy()) : partner.getAcronomy() != null)
            return false;
        if (getCountry() != null ? !getCountry().equals(partner.getCountry()) : partner.getCountry() != null)
            return false;
        if (getCity() != null ? !getCity().equals(partner.getCity()) : partner.getCity() != null) return false;
        if (getVat() != null ? !getVat().equals(partner.getVat()) : partner.getVat() != null) return false;
        if (getAddress() != null ? !getAddress().equals(partner.getAddress()) : partner.getAddress() != null)
            return false;
        if (getLegalStatus() != null ? !getLegalStatus().equals(partner.getLegalStatus()) : partner.getLegalStatus() != null)
            return false;
        if (getRegistrationNumber() != null ? !getRegistrationNumber().equals(partner.getRegistrationNumber()) : partner.getRegistrationNumber() != null)
            return false;
        if (getRepresentativeFunction() != null ? !getRepresentativeFunction().equals(partner.getRepresentativeFunction()) : partner.getRepresentativeFunction() != null)
            return false;
        return getZip() != null ? getZip().equals(partner.getZip()) : partner.getZip() == null;
    }

    @Override
    public int hashCode() {
        int result = getPic() != null ? getPic().hashCode() : 0;
        result = 31 * result + (getRepresentativeFirstName() != null ? getRepresentativeFirstName().hashCode() : 0);
        result = 31 * result + (getRepresentativeSurname() != null ? getRepresentativeSurname().hashCode() : 0);
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getAcronomy() != null ? getAcronomy().hashCode() : 0);
        result = 31 * result + (getCountry() != null ? getCountry().hashCode() : 0);
        result = 31 * result + (getCity() != null ? getCity().hashCode() : 0);
        result = 31 * result + (getVat() != null ? getVat().hashCode() : 0);
        result = 31 * result + (getAddress() != null ? getAddress().hashCode() : 0);
        result = 31 * result + (getLegalStatus() != null ? getLegalStatus().hashCode() : 0);
        result = 31 * result + (getRegistrationNumber() != null ? getRegistrationNumber().hashCode() : 0);
        result = 31 * result + (getRepresentativeFunction() != null ? getRepresentativeFunction().hashCode() : 0);
        result = 31 * result + (getZip() != null ? getZip().hashCode() : 0);
        return result;
    }
}
