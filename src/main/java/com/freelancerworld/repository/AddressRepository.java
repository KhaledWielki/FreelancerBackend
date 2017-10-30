package com.freelancerworld.repository;

import com.freelancerworld.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by AdamR on 2017-10-30.
 */

@Repository("addressRepository")
public interface AddressRepository extends JpaRepository<Address, Long> {
    Address findAddressByCityAndStreetAndBuildingNumberAndOccupationNumber(String city, String street, String buildingNumber, String occupationNumber);

}
