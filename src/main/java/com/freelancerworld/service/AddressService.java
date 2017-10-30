package com.freelancerworld.service;

import com.freelancerworld.model.Address;

/**
 * Created by AdamR on 2017-10-30.
 */
public interface AddressService {

    public Address findAddressByCityAndStreetAndBuildingNumber(String city, String street, String buildingNumber);
    public Address findAddressByCityAndStreetAndBuildingNumberAndOccupationNumber(String city, String street, String buildingNumber, String occupationNumber);
    public void saveAddress(Address address);
}
