package com.prodapt.restfulapp.repository;

import com.prodapt.restfulapp.entity.Booking;
import com.prodapt.restfulapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUser(User user);


}