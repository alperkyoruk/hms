package com.alperkyoruk.hms.business.concretes;

import com.alperkyoruk.hms.business.abstracts.GuestService;
import com.alperkyoruk.hms.business.abstracts.RoomService;
import com.alperkyoruk.hms.business.abstracts.TicketService;
import com.alperkyoruk.hms.business.constants.GuestMessages;
import com.alperkyoruk.hms.business.constants.StaffMessages;
import com.alperkyoruk.hms.business.constants.TicketMessages;
import com.alperkyoruk.hms.core.result.*;
import com.alperkyoruk.hms.dataAccess.StaffDao;
import com.alperkyoruk.hms.dataAccess.TicketDao;
import com.alperkyoruk.hms.entities.DTOs.Ticket.CreateTicketDto;
import com.alperkyoruk.hms.entities.DTOs.Ticket.GetTicketDto;
import com.alperkyoruk.hms.entities.Room;
import com.alperkyoruk.hms.entities.Staff;
import com.alperkyoruk.hms.entities.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TicketManager implements TicketService {


    @Autowired
    private TicketDao ticketDao;

    @Autowired
    private GuestService guestService;

    @Autowired
    private StaffDao staffDao;

    @Autowired
    RoomService roomService;

    @Override
    public Result addTicket(CreateTicketDto createTicketDto) {

        var departmentLetter = createTicketDto.getCategory().toUpperCase().charAt(0);

        SecureRandom secureRandom = new SecureRandom();
        int number = secureRandom.nextInt(90000) + 10000; // 10000 to 99999
        String ticketNumber = departmentLetter  + String.valueOf(number);


        var guestResponse = guestService.getGuestById(createTicketDto.getGuestId());

        if(guestResponse.getData() == null){
            return new ErrorResult(GuestMessages.guestNotFound);
        }


        List<Staff> availableStaff = staffDao.findAllByStatusAndDepartment("ACTIVE", createTicketDto.getCategory());

        if(availableStaff.isEmpty()){
            return new ErrorResult(StaffMessages.StaffNotFound);
        }

        Map<Staff, Long> staffActiveTicketCounts = availableStaff.stream()
                .collect(Collectors.toMap(
                        staff -> staff,
                        staff -> (long) ticketDao.findAllByStaffAndStatus(staff, "CREATED").size()
                ));

        Staff selectedStaff = staffActiveTicketCounts.entrySet().stream()
                .min(Comparator.comparingLong(Map.Entry::getValue))
                .get()
                .getKey();


        Ticket ticket = Ticket.builder()
                .ticketNumber(ticketNumber)
                .status(createTicketDto.getStatus())
                .category(createTicketDto.getCategory())
                .guest(guestResponse.getData())
                .createdDate(new Date())
                .issue(createTicketDto.getIssue())
                .description(createTicketDto.getDescription())
                .comment(createTicketDto.getComment())
                .resolvedDate(createTicketDto.getResolvedDate())
                .issue(createTicketDto.getIssue())
                .staff(selectedStaff)
                .room(guestResponse.getData().getRoom())
                .build();

        ticketDao.save(ticket);
        return new SuccessResult(TicketMessages.ticketAddedSuccessfully);
    }

    @Override
    public Result deleteTicket(int id) {
        var ticketResponse = ticketDao.findById(id);
        if(ticketResponse == null){
            return new ErrorResult(TicketMessages.ticketNotFound);
        }

        ticketDao.delete(ticketResponse);
        return new SuccessResult(TicketMessages.ticketDeletedSuccessfully);
    }

    @Override
    public Result updateTicket(GetTicketDto getTicketDto) {
        var ticketResponse = ticketDao.findByTicketNumber(getTicketDto.getTicketNumber());
        if(ticketResponse == null){
            return new ErrorResult(TicketMessages.ticketNotFound);
        }

        ticketResponse.setIssue(getTicketDto.getIssue() == null ? ticketResponse.getIssue() : getTicketDto.getIssue());
        ticketResponse.setStatus(getTicketDto.getStatus() == null ? ticketResponse.getStatus() : getTicketDto.getStatus());
        ticketResponse.setCategory(getTicketDto.getCategory() == null ? ticketResponse.getCategory() : getTicketDto.getCategory());
        ticketResponse.setResolvedDate(getTicketDto.getResolvedDate() == null ? ticketResponse.getResolvedDate() : getTicketDto.getResolvedDate());
        ticketResponse.setDescription(getTicketDto.getDescription() == null ? ticketResponse.getDescription() : getTicketDto.getDescription());
        ticketResponse.setComment(getTicketDto.getComment() == null ? ticketResponse.getComment() : getTicketDto.getComment());
        ticketResponse.setGuest(getTicketDto.getGuestId() == 0 ? ticketResponse.getGuest() : guestService.getGuestById(getTicketDto.getGuestId()).getData());

        ticketDao.save(ticketResponse);
        return new SuccessResult(TicketMessages.ticketUpdatedSuccessfully);
    }

    @Override
    public DataResult<GetTicketDto> getById(int id) {
        var ticketResponse = ticketDao.findById(id);
        if(ticketResponse == null){
            return new ErrorDataResult<>(TicketMessages.ticketNotFound);
        }

        var returnTicket = new GetTicketDto(ticketResponse);
        return new SuccessDataResult<>(returnTicket, TicketMessages.ticketSuccessfullyBrought);
    }

    @Override
    public DataResult<GetTicketDto> getByTicketNumber(String ticketNumber) {
        var ticketResponse = ticketDao.findByTicketNumber(ticketNumber);
        if(ticketResponse == null){
            return new ErrorDataResult<>(TicketMessages.ticketNotFound);
        }

        var returnTicket = new GetTicketDto(ticketResponse);
        return new SuccessDataResult<>(returnTicket, TicketMessages.ticketSuccessfullyBrought);
    }

    @Override
    public DataResult<List<GetTicketDto>> getAllByStatus(String status) {
        var ticketResponse = ticketDao.findAllByStatus(status);
        if(ticketResponse.isEmpty()){
            return new ErrorDataResult<>(TicketMessages.ticketNotFound);
        }

        List<GetTicketDto> returnList = GetTicketDto.buildListGetTicketDto(ticketResponse);
        return new SuccessDataResult<>(returnList, TicketMessages.ticketsSuccessfullyBrought);
    }

    @Override
    public DataResult<List<GetTicketDto>> getAllByCategory(String Category) {
        var ticketResponse = ticketDao.findAllByCategory(Category);
        if(ticketResponse.isEmpty()){
            return new ErrorDataResult<>(TicketMessages.ticketNotFound);
        }

        List<GetTicketDto> returnList = GetTicketDto.buildListGetTicketDto(ticketResponse);
        return new SuccessDataResult<>(returnList, TicketMessages.ticketsSuccessfullyBrought);
    }


    @Override
    public DataResult<List<GetTicketDto>> getAllByStaffBadgeNumber(String badgeNumber) {
        var ticketResponse = ticketDao.findAllByStaffBadgeNumber(badgeNumber);
        if(ticketResponse.isEmpty()){
            return new ErrorDataResult<>(TicketMessages.ticketNotFound);
        }

        List<GetTicketDto> returnList = GetTicketDto.buildListGetTicketDto(ticketResponse);
        return new SuccessDataResult<>(returnList, TicketMessages.ticketsSuccessfullyBrought);
    }

    @Override
    public DataResult<List<GetTicketDto>> getAllByGuestId(int guestId) {
        var ticketResponse = ticketDao.findAllByGuestId(guestId);
        if(ticketResponse.isEmpty()){
            return new ErrorDataResult<>(TicketMessages.ticketNotFound);
        }

        List<GetTicketDto> returnList = GetTicketDto.buildListGetTicketDto(ticketResponse);
        return new SuccessDataResult<>(returnList, TicketMessages.ticketsSuccessfullyBrought);
    }

    @Override
    public DataResult<List<GetTicketDto>> getAll() {
        var ticketResponse = ticketDao.findAll();
        if(ticketResponse.isEmpty()){
            return new ErrorDataResult<>(TicketMessages.ticketNotFound);
        }

        List<GetTicketDto> returnList = GetTicketDto.buildListGetTicketDto(ticketResponse);
        return new SuccessDataResult<>(returnList, TicketMessages.ticketsSuccessfullyBrought);
    }

    @Override
    public DataResult<List<GetTicketDto>> getAllByCreatedDateAfter(Date createdDate) {
        var ticketResponse = ticketDao.findAllByCreatedDateAfter(createdDate);
        if(ticketResponse.isEmpty()){
            return new ErrorDataResult<>(TicketMessages.ticketNotFound);
        }

        List<GetTicketDto> returnList = GetTicketDto.buildListGetTicketDto(ticketResponse);
        return new SuccessDataResult<>(returnList, TicketMessages.ticketsSuccessfullyBrought);
    }

    @Override
    public DataResult<List<GetTicketDto>> getAllByCreatedDateBefore(Date createdDate) {
        var ticketResponse = ticketDao.findAllByCreatedDateBefore(createdDate);
        if(ticketResponse.isEmpty()){
            return new ErrorDataResult<>(TicketMessages.ticketNotFound);
        }

        List<GetTicketDto> returnList = GetTicketDto.buildListGetTicketDto(ticketResponse);
        return new SuccessDataResult<>(returnList, TicketMessages.ticketsSuccessfullyBrought);
    }

    @Override
    public DataResult<List<GetTicketDto>> getAllByResolvedDateAfter(Date resolvedDate) {
        var ticketResponse = ticketDao.findAllByResolvedDateAfter(resolvedDate);
        if(ticketResponse.isEmpty()){
            return new ErrorDataResult<>(TicketMessages.ticketNotFound);
        }

        List<GetTicketDto> returnList = GetTicketDto.buildListGetTicketDto(ticketResponse);
        return new SuccessDataResult<>(returnList, TicketMessages.ticketsSuccessfullyBrought);
    }

    @Override
    public DataResult<List<GetTicketDto>> getAllByResolvedDateBefore(Date resolvedDate) {
        var ticketResponse = ticketDao.findAllByResolvedDateBefore(resolvedDate);
        if(ticketResponse.isEmpty()){
            return new ErrorDataResult<>(TicketMessages.ticketNotFound);
        }

        List<GetTicketDto> returnList = GetTicketDto.buildListGetTicketDto(ticketResponse);
        return new SuccessDataResult<>(returnList, TicketMessages.ticketsSuccessfullyBrought);
    }

    @Override
    public DataResult<List<GetTicketDto>> getAllByIssue(String issue) {
        var ticketResponse = ticketDao.findAllByIssue(issue);
        if(ticketResponse.isEmpty()){
            return new ErrorDataResult<>(TicketMessages.ticketNotFound);
        }

        List<GetTicketDto> returnList = GetTicketDto.buildListGetTicketDto(ticketResponse);
        return new SuccessDataResult<>(returnList, TicketMessages.ticketsSuccessfullyBrought);
    }

    @Override
    public Result assignTicketToStaff(String ticketNumber, String badgeNumber) {
        var ticketResponse = ticketDao.findByTicketNumber(String.valueOf(ticketNumber));
        if(ticketResponse == null){
            return new ErrorResult(TicketMessages.ticketNotFound);
        }

        var staffResponse = staffDao.findByBadgeNumber(badgeNumber);
        if(staffResponse == null){
            return new ErrorResult(StaffMessages.StaffNotFound);
        }

        ticketResponse.setStaff(ticketResponse.getStaff() == null ? staffResponse : ticketResponse.getStaff());

        ticketDao.save(ticketResponse);
        return new SuccessResult(TicketMessages.ticketAssignedSuccessfully);
    }

    @Override
    public Result changeTicketStatus(String ticketNumber, String status) {
        var ticketResponse = ticketDao.findByTicketNumber(ticketNumber);
        if(ticketResponse == null){
            return new ErrorResult(TicketMessages.ticketNotFound);
        }

        if(ticketResponse.getStatus().equals(status)){
            return new ErrorResult(TicketMessages.ticketStatusNotChanged);
        }


        ticketResponse.setStatus(status);
        if(status.equals("RESOLVED")){
            ticketResponse.setResolvedDate(new Date());
        }
        ticketDao.save(ticketResponse);
        return new SuccessResult(TicketMessages.ticketStatusUpdatedSuccessfully);
    }

    @Override
    public Result addTicketForHouseKeeping(Room room) {

        SecureRandom secureRandom = new SecureRandom();
        int number = secureRandom.nextInt(90000) + 10000; // 10000 to 99999
        String ticketNumber = "H"  + String.valueOf(number);

        List<Staff> availableStaff = staffDao.findAllByStatusAndDepartment("ACTIVE", "Housekeeping");

        if(availableStaff.isEmpty()){
            return new ErrorResult(StaffMessages.StaffNotFound);
        }

        Map<Staff, Long> staffActiveTicketCounts = availableStaff.stream()
                .collect(Collectors.toMap(
                        staff -> staff,
                        staff -> (long) ticketDao.findAllByStaffAndStatus(staff, "CREATED").size()
                ));

        Staff selectedStaff = staffActiveTicketCounts.entrySet().stream()
                .min(Comparator.comparingLong(Map.Entry::getValue))
                .get()
                .getKey();

        var guest = guestService.getGuestById(2).getData();


        Ticket ticket = Ticket.builder()
                .ticketNumber(ticketNumber)
                .status("CREATED")
                .category("Housekeeping")
                .guest(guest)
                .createdDate(new Date())
                .issue("CLEANING")
                .description("Room cleaning is requested.")
                .comment("Room cleaning is requested.")
                .resolvedDate(null)
                .staff(selectedStaff)
                .room(room)
                .build();

        ticketDao.save(ticket);
        return new SuccessResult(TicketMessages.ticketAddedSuccessfully);
    }

    @Override
    public Result cleaningDone(String ticketNumber) {
        var ticketResponse = ticketDao.findByTicketNumber(ticketNumber);
        if(ticketResponse == null){
            return new ErrorResult(TicketMessages.ticketNotFound);
        }

        if(!ticketResponse.getIssue().equals("CLEANING")){
            return new ErrorResult(TicketMessages.ticketIssueNotCleaning);
        }

        ticketResponse.setStatus("RESOLVED");
        ticketResponse.setResolvedDate(new Date());
        ticketDao.save(ticketResponse);
        roomService.updateRoomStatus(ticketResponse.getRoom(), "ACTIVE");

        return new SuccessResult(TicketMessages.ticketStatusUpdatedSuccessfully);
    }


}
