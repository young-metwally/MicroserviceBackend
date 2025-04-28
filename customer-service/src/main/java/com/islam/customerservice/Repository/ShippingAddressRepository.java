package com.islam.customerservice.Repository;

import com.islam.customerservice.entity.ShippingAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ShippingAddressRepository extends JpaRepository<ShippingAddressEntity, Integer> {
}
