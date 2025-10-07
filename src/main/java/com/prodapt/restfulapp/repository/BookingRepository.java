package com.prodapt.restfulapp.repository;

import com.prodapt.restfulapp.entity.Booking;
import com.prodapt.restfulapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUser(User user);
}