package com.alperkyoruk.hms.business.abstracts;

import com.alperkyoruk.hms.core.result.DataResult;
import com.alperkyoruk.hms.core.result.Result;
import com.alperkyoruk.hms.entities.DTOs.Guest.CreateGuestDto;
import com.alperkyoruk.hms.entities.DTOs.Guest.GetGuestDto;

import java.util.List;

public interface GuestService {

    Result addGuest(CreateGuestDto createGuestDto);

    Result deleteGuest(int guestId);

    Result updateGuest(GetGuestDto getGuestDto);

    DataResult<List<GetGuestDto>> getAllGuests();

    DataResult<GetGuestDto> getGuestById(int guestId);

    DataResult<List<GetGuestDto>> getGuestsByFirstNameAndLastname(String firstName, String lastName);

    DataResult<GetGuestDto> getGuestByPhoneNumber(String phoneNumber);

    DataResult<GetGuestDto> getGuestByEmail(String email);

    DataResult<GetGuestDto> getGuestByPassportNumber(String passportNumber);




}
