package com.alperkyoruk.hms.dataAccess;

import com.alperkyoruk.hms.entities.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StaffDao extends JpaRepository<Staff, Integer> {

    Staff findById(int id);

    List<Staff> findAllByFirstNameAndLastNameContainsIgnoreCase(String firstName, String lastName);

    Staff findAllByLastName(String lastName);

    Staff findByEmail(String email);

    List<Staff> findAllByPosition(String position);

    Staff findByPhoneNumber(String phoneNumber);

    List<Staff> findAllByStatus(String status);

    List<Staff> findAllByDepartment(String department);

    List<Staff> findAllByPerformanceRatingGreaterThanEqual(double performanceRating);


    Staff findByBadgeNumber(String badgeNumber);
}
