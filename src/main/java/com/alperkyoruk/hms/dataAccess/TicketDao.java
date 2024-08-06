package com.alperkyoruk.hms.dataAccess;

import com.alperkyoruk.hms.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface TicketDao extends JpaRepository<Ticket, Integer> {

    Ticket findById(int id);

    List<Ticket> findAllByStatus(String status);

    Ticket findByTicketNumber(String ticketNumber);

    List<Ticket> findAllByCategory(String category);

    List<Ticket> findAllByStaffId(int staffId);

    List<Ticket> findAllByGuestId(int guestId);

    List<Ticket> findAllByCreatedDateAfter(Date createdDate);

    List<Ticket> findAllByCreatedDateBefore(Date createdDate);

    List<Ticket>  findAllByResolvedDateBefore(Date resolvedDate);

    List<Ticket>  findAllByResolvedDateAfter(Date resolvedDate);

    List<Ticket> findAllByIssue(String issue);

    List<Ticket> findAllByStaffBadgeNumber(String staffBadgeNumber);
}
