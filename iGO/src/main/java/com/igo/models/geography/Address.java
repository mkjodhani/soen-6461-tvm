package com.igo.models.geography;

/**
 * @author mkjodhani
 * @project SDM TVM Project
 * @since 02/03/23
 */
public class Address {
    private String street, city, province, postalCode;
    private int streetAddressNumber;

    private Address(String street, String city, String province, String postalCode, int streetAddressNumber) {
        this.street = street;
        this.city = city;
        this.province = province;
        this.postalCode = postalCode;
        this.streetAddressNumber = streetAddressNumber;
    }
    private Address generateAddress(String street, String city, String province, String postalCode, int streetAddressNumber) {
//        TODO validate the address
        return new Address(street,city,province,postalCode,streetAddressNumber);
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getProvince() {
        return province;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public int getStreetAddressNumber() {
        return streetAddressNumber;
    }

    @Override
    public String toString() {
        return streetAddressNumber + ", "  + street + ", " + city + ", " + province + ", " + postalCode;
    }
}
