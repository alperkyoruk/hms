package com.alperkyoruk.hms.business.abstracts;

import com.alperkyoruk.hms.core.result.DataResult;
import com.alperkyoruk.hms.core.result.Result;
import com.alperkyoruk.hms.entities.DTOs.Ticket.CreateTicketDto;
import com.alperkyoruk.hms.entities.DTOs.Ticket.GetTicketDto;
import com.alperkyoruk.hms.entities.Room;

import java.util.Date;
import java.util.List;

public interface TicketService {
    Result addTicket(CreateTicketDto createTicketDto);

    Result deleteTicket(int id);

    Result updateTicket(GetTicketDto getTicketDto);

    DataResult<GetTicketDto> getById(int id);

    DataResult<GetTicketDto> getByTicketNumber(String ticketNumber);

    DataResult<List<GetTicketDto>> getAllByStatus(String status);

    DataResult<List<GetTicketDto>> getAllByCategory(String Category);

    DataResult<List<GetTicketDto>> getAllByStaffBadgeNumber(String staffBadgeNumber);

    DataResult<List<GetTicketDto>> getAllByGuestId(int guestId);

    DataResult<List<GetTicketDto>> getAll();

    DataResult<List<GetTicketDto>> getAllByCreatedDateAfter(Date createdDate);

    DataResult<List<GetTicketDto>> getAllByCreatedDateBefore(Date createdDate);

    DataResult<List<GetTicketDto>> getAllByResolvedDateAfter(Date resolvedDate);

    DataResult<List<GetTicketDto>> getAllByResolvedDateBefore(Date resolvedDate);

    DataResult<List<GetTicketDto>> getAllByIssue(String issue);

    Result assignTicketToStaff(String ticketNumber, String badgeNumber);

    Result changeTicketStatus(String ticketNumber, String status);

    Result addTicketForHouseKeeping(Room room);

    Result cleaningDone(String ticketNumber);

    void deleteAllByResolvedDateBefore();

    DataResult<List<GetTicketDto>> getAllByGuest();

    DataResult<List<GetTicketDto>> getAllByStaff();

}
