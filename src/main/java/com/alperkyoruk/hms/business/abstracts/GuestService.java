package com.alperkyoruk.hms.business.abstracts;

import com.alperkyoruk.hms.core.result.DataResult;
import com.alperkyoruk.hms.core.result.Result;
import com.alperkyoruk.hms.entities.DTOs.Guest.CreateGuestDto;
import com.alperkyoruk.hms.entities.DTOs.Guest.GetGuestDto;
import com.alperkyoruk.hms.entities.Guest;

import java.util.List;

public interface GuestService {

    Result addGuest(CreateGuestDto createGuestDto);

    Result deleteGuest(int guestId);

    Result updateGuest(GetGuestDto getGuestDto);

    DataResult<List<GetGuestDto>> getAllGuests();

    DataResult<GetGuestDto> getById(int guestId);

    DataResult<List<GetGuestDto>> getGuestsByFirstNameAndLastname(String firstName, String lastName);

    DataResult<GetGuestDto> getGuestByPhoneNumber(String phoneNumber);

    DataResult<GetGuestDto> getGuestDtoByEmail(String email);

    DataResult<GetGuestDto> getGuestByPassportNumber(String passportNumber);

    DataResult<Guest> getGuestById(int id);

    Result checkOutGuest(int guestId);

    DataResult<List<GetGuestDto>> getGuestsByStatus(String status);

    DataResult<Guest> getGuestByEmail(String email);




}
