package com.alperkyoruk.hms.business.concretes;

import com.alperkyoruk.hms.business.abstracts.GuestService;
import com.alperkyoruk.hms.business.abstracts.RoomService;
import com.alperkyoruk.hms.business.constants.GuestMessages;
import com.alperkyoruk.hms.core.result.*;
import com.alperkyoruk.hms.dataAccess.GuestDao;
import com.alperkyoruk.hms.entities.DTOs.Guest.CreateGuestDto;
import com.alperkyoruk.hms.entities.DTOs.Guest.GetGuestDto;
import com.alperkyoruk.hms.entities.Guest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuestManager implements GuestService {


    private GuestDao guestDao;
    private RoomService roomService;

    public GuestManager(GuestDao guestDao, RoomService roomService) {
        this.guestDao = guestDao;
        this.roomService = roomService;
    }

    @Override
    public Result addGuest(CreateGuestDto createGuestDto) {

        var room = roomService.getRoomByRoomNumber(createGuestDto.getRoomNumber()).getData();
        if(room == null){
            return new ErrorResult(GuestMessages.roomNotFound);
        }

        Guest guest = Guest.builder()
                .firstName(createGuestDto.getFirstName())
                .lastName(createGuestDto.getLastName())
                .email(createGuestDto.getEmail())
                .phoneNumber(createGuestDto.getPhoneNumber())
                .passportNumber(createGuestDto.getPassportNumber())
                .dateOfBirth(createGuestDto.getDateOfBirth())
                .checkInDate(createGuestDto.getCheckInDate())
                .checkOutDate(createGuestDto.getCheckOutDate())
                .vipStatus(createGuestDto.getVipStatus())
                .loyaltyCardNumber(createGuestDto.getLoyaltyCardNumber())
                .sex(createGuestDto.getSex())
                .reservation(null)
                .room(room)
                .build();

        guestDao.save(guest);
        return new SuccessResult(GuestMessages.guestAdded);
    }

    @Override
    public Result deleteGuest(int guestId) {

        var guestResponse = guestDao.findById(guestId);

        if(guestResponse == null){
            return new ErrorResult(GuestMessages.guestNotFound);
        }

        guestDao.delete(guestResponse);

        return new SuccessResult(GuestMessages.guestDeleted);
    }

    @Override
    public Result updateGuest(GetGuestDto getGuestDto) {
        var guestResponse = guestDao.findById(getGuestDto.getId());

        if(guestResponse == null){
            return new ErrorResult(GuestMessages.guestNotFound);
        }

        var room = roomService.getRoomByRoomNumber(getGuestDto.getRoomNumber()).getData();
        if(room == null){
             return new ErrorResult(GuestMessages.roomNotFound);
         }



        guestResponse.setEmail(getGuestDto.getEmail() == null ? guestResponse.getEmail() : getGuestDto.getEmail());
        guestResponse.setPhoneNumber(getGuestDto.getPhoneNumber() == null ? guestResponse.getPhoneNumber() : getGuestDto.getPhoneNumber());
        guestResponse.setPassportNumber(getGuestDto.getPassportNumber() == null ? guestResponse.getPassportNumber() : getGuestDto.getPassportNumber());
        guestResponse.setCheckInDate(getGuestDto.getCheckInDate() == null ? guestResponse.getCheckInDate() : getGuestDto.getCheckInDate());
        guestResponse.setCheckOutDate(getGuestDto.getCheckOutDate() == null ? guestResponse.getCheckOutDate() : getGuestDto.getCheckOutDate());
        guestResponse.setVipStatus(getGuestDto.getVipStatus() == null ? guestResponse.getVipStatus() : getGuestDto.getVipStatus());
        guestResponse.setLoyaltyCardNumber(getGuestDto.getLoyaltyCardNumber() == null ? guestResponse.getLoyaltyCardNumber() : getGuestDto.getLoyaltyCardNumber());
        guestResponse.setSex(getGuestDto.getSex() == null ? guestResponse.getSex() : getGuestDto.getSex());
        guestResponse.setFirstName(getGuestDto.getFirstName() == null ? guestResponse.getFirstName() : getGuestDto.getFirstName());
        guestResponse.setLastName(getGuestDto.getLastName() == null ? guestResponse.getLastName() : getGuestDto.getLastName());
        guestResponse.setDateOfBirth(getGuestDto.getDateOfBirth() == null ? guestResponse.getDateOfBirth() : getGuestDto.getDateOfBirth());
        guestResponse.setRoom(room);

        return new SuccessResult(GuestMessages.guestUpdated);
    }

    @Override
    public DataResult<List<GetGuestDto>> getAllGuests() {
        var result = guestDao.findAll();

        if(result.isEmpty()){
            return new ErrorDataResult<>(GuestMessages.guestsNotFound);
        }

        List<GetGuestDto> returnList = new GetGuestDto().buildListGetGuestDto(result);
        return new SuccessDataResult<>(returnList, GuestMessages.guestsSuccessfullyBrought);
    }

    @Override
    public DataResult<GetGuestDto> getById(int guestId) {
        var guest = guestDao.findById(guestId);

        if(guest == null){
            return new ErrorDataResult<>(GuestMessages.guestNotFound);
        }

        var returnGuest = new GetGuestDto(guest);

        return new SuccessDataResult<>(returnGuest, GuestMessages.guestSuccessfullyBrought);
    }

    @Override
    public DataResult<List<GetGuestDto>> getGuestsByFirstNameAndLastname(String firstName, String lastName) {
        var guestList = guestDao.findAllByFirstNameAndLastNameContainsIgnoreCase(firstName, lastName);

        if(guestList.isEmpty()){
            return new ErrorDataResult<>(GuestMessages.guestsNotFound);
        }

        List<GetGuestDto> returnList = new GetGuestDto().buildListGetGuestDto(guestList);

        return new SuccessDataResult<>(returnList, GuestMessages.guestsSuccessfullyBrought);
    }

    @Override
    public DataResult<GetGuestDto> getGuestByPhoneNumber(String phoneNumber) {
        var guest = guestDao.findByPhoneNumber(phoneNumber);

        if(guest == null){
            return new ErrorDataResult<>(GuestMessages.guestNotFound);
        }

        var returnGuest = new GetGuestDto(guest);

        return new SuccessDataResult<>(returnGuest, GuestMessages.guestSuccessfullyBrought);
    }

    @Override
    public DataResult<GetGuestDto> getGuestByEmail(String email) {
        var guest = guestDao.findByEmail(email);

        if(guest == null){
            return new ErrorDataResult<>(GuestMessages.guestNotFound);
        }

        var returnGuest = new GetGuestDto(guest);

        return new SuccessDataResult<>(returnGuest, GuestMessages.guestSuccessfullyBrought);
    }

    @Override
    public DataResult<GetGuestDto> getGuestByPassportNumber(String passportNumber) {
        var guest = guestDao.findByPassportNumber(passportNumber);

        if(guest == null){
            return new ErrorDataResult<>(GuestMessages.guestNotFound);
        }

        var returnGuest = new GetGuestDto(guest);

        return new SuccessDataResult<>(returnGuest, GuestMessages.guestSuccessfullyBrought);
    }
    @Override
    public DataResult<Guest> getGuestById(int id) {
        var result = guestDao.findById(id);
        if(result == null){
            return new ErrorDataResult<>(GuestMessages.guestNotFound);
        }

        return new SuccessDataResult<>(result,GuestMessages.guestSuccessfullyBrought);
    }
}
