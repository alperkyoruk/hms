package com.alperkyoruk.hms.WebAPI.controllers;

import com.alperkyoruk.hms.business.abstracts.TicketService;
import com.alperkyoruk.hms.core.result.DataResult;
import com.alperkyoruk.hms.core.result.Result;
import com.alperkyoruk.hms.entities.DTOs.Ticket.CreateTicketDto;
import com.alperkyoruk.hms.entities.DTOs.Ticket.GetTicketDto;
import com.alperkyoruk.hms.entities.Room;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {
    private TicketService ticketService;

    public TicketController(TicketService ticketService){
        this.ticketService = ticketService;
    }


    @PostMapping("/addTicket")
    public Result addTicket(@RequestBody CreateTicketDto createTicketDto){
        return ticketService.addTicket(createTicketDto);
    }

    @PostMapping("/deleteTicket")
    public Result deleteTicket(@RequestParam int ticketId){
        return ticketService.deleteTicket(ticketId);
    }

    @PostMapping("/updateTicket")
    public Result updateTicket(@RequestBody GetTicketDto getTicketDto){
        return ticketService.updateTicket(getTicketDto);
    }

    @GetMapping("/getById")
    public DataResult<GetTicketDto> getById(@RequestParam int ticketId){
        return ticketService.getById(ticketId);
    }

    @GetMapping("/getTickets")
    public DataResult<List<GetTicketDto>> getTickets(){
        return ticketService.getAll();
    }

    @GetMapping("/getTicketByTicketNumber")
    public DataResult<GetTicketDto> getTicketByTicketNumber(@RequestParam String ticketNumber){
        return ticketService.getByTicketNumber(ticketNumber);
    }

    @GetMapping("/getTicketsByCategory")
    public DataResult<List<GetTicketDto>> getTicketsByCategory(@RequestParam String category){
        return ticketService.getAllByCategory(category);
    }

    @GetMapping("/getTicketsByStaffBadgeNumber")
    public DataResult<List<GetTicketDto>> getTicketsByStaffBadgeNumber(@RequestParam String staffBadgeNumber){
        return ticketService.getAllByStaffBadgeNumber(staffBadgeNumber);
    }

    @GetMapping("/getTicketsByGuestId")
    public DataResult<List<GetTicketDto>> getTicketsByGuestId(@RequestParam int guestId){
        return ticketService.getAllByGuestId(guestId);
    }

    @GetMapping("/getTicketsByCreatedDateAfter")
    public DataResult<List<GetTicketDto>> getTicketsByCreatedDateAfter(@RequestBody Date createdDate){
        return ticketService.getAllByCreatedDateAfter(createdDate);
    }

    @GetMapping("/getTicketsByCreatedDateBefore")
    public DataResult<List<GetTicketDto>> getTicketsByCreatedDateBefore(@RequestBody Date createdDate){
        return ticketService.getAllByCreatedDateBefore(createdDate);
    }

    @GetMapping("/getTicketsByResolvedDateBefore")
    public DataResult<List<GetTicketDto>> getTicketsByResolvedDateBefore(@RequestBody Date resolvedDate){
        return ticketService.getAllByResolvedDateBefore(resolvedDate);
    }

    @GetMapping("/getTicketsByResolvedDateAfter")
    public DataResult<List<GetTicketDto>> getTicketsByResolvedDateAfter(@RequestBody Date resolvedDate){
        return ticketService.getAllByResolvedDateAfter(resolvedDate);
    }

    @GetMapping("/getTicketsByIssue")
    public DataResult<List<GetTicketDto>> getTicketsByIssue(@RequestBody String issue){
        return ticketService.getAllByIssue(issue);
    }

    @PostMapping("/assignTicketToStaff")
    public Result assignTicketToStaff(@RequestBody String ticketNumber,@RequestBody String badgeNumber){
        return ticketService.assignTicketToStaff(ticketNumber, badgeNumber);
    }

    @PostMapping("/cleaningDone")
    public Result cleaningDone(@RequestParam String ticketNumber){
        return ticketService.cleaningDone(ticketNumber);
    }

    @PostMapping("/changeTicketStatus")
    public Result changeTicketStatus(@RequestParam String ticketNumber,@RequestParam String status){
        return ticketService.changeTicketStatus(ticketNumber, status);
    }

    @PostMapping("/addTicketForHouseKeeping")
    public Result addTicketForHouseKeeping(@RequestBody Room room){
        return ticketService.addTicketForHouseKeeping(room);
    }

    @GetMapping("/getTicketsByStatus")
    public DataResult<List<GetTicketDto>> getTicketsByStatus(@RequestParam String status){
        return ticketService.getAllByStatus(status);
    }

    @GetMapping("/getTicketsByGuest")
    public DataResult<List<GetTicketDto>> getTicketsByGuest(){
        return ticketService.getAllByGuest();
    }

    @GetMapping("/getTicketsByStaff")
    public DataResult<List<GetTicketDto>> getTicketsByStaff(){
        return ticketService.getAllByStaff();
    }


}
