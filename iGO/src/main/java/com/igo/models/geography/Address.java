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
        return new Address(street,city,province,postalCode,streetAddressNumber);
    }

    @Override
    public String toString() {
        return streetAddressNumber + ", "  + street + ", " + city + ", " + province + ", " + postalCode;
    }
}
