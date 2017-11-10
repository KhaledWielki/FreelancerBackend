package com.freelancerworld.service;

import com.freelancerworld.model.Address;

/**
 * Created by AdamR on 2017-10-30.
 */
public interface AddressService {

    public Address findAddress(String city, String street, String buildingNumber, int houseNumber);
    public void saveAddress(Address address);
}
