package com.freelancerworld.service.Implementation;

import com.freelancerworld.model.Address;
import com.freelancerworld.repository.AddressRepository;
import com.freelancerworld.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by AdamR on 2017-10-30.
 */

@Service("addressService")
public class AddressServiceImpl implements AddressService {

    @Autowired
    AddressRepository addressRepository;


    @Override
    public Address findAddressByCityAndStreetAndBuildingNumber(String city, String street, String buildingNumber) {
        return null;
    }

    @Override
    public Address findAddressByCityAndStreetAndBuildingNumberAndOccupationNumber(String city, String street, String buildingNumber, String occupationNumber) {
        return null;
    }

    @Override
    public void saveAddress(Address address) {

    }
}
