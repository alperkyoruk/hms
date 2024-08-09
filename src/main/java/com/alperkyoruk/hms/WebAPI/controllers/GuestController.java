package com.alperkyoruk.hms.WebAPI.controllers;

import com.alperkyoruk.hms.business.abstracts.GuestService;
import com.alperkyoruk.hms.core.result.DataResult;
import com.alperkyoruk.hms.core.result.Result;
import com.alperkyoruk.hms.entities.DTOs.Guest.CreateGuestDto;
import com.alperkyoruk.hms.entities.DTOs.Guest.GetGuestDto;
import com.alperkyoruk.hms.entities.Guest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/guests")
public class GuestController {

    private GuestService guestService;

    public GuestController(GuestService guestService){
        this.guestService = guestService;
    }

    @PostMapping("/addGuest")
    public Result addGuest(@RequestBody CreateGuestDto createGuestDto){
        return guestService.addGuest(createGuestDto);
    }

    @PostMapping("/deleteGuest")
    public Result deleteGuest(@RequestBody int guestId){
        return guestService.deleteGuest(guestId);
    }

    @PostMapping("/updateGuest")
    public Result updateGuest(@RequestBody GetGuestDto getGuestDto){
        return guestService.updateGuest(getGuestDto);
    }

    @GetMapping("/getGuestById")
    public DataResult<Guest> getGuestById(@RequestParam int guestId){
        return guestService.getGuestById(guestId);
    }

    @GetMapping("/getGuests")
    public DataResult<List<GetGuestDto>> getGuests(){
        return guestService.getAllGuests();
    }

    @GetMapping("/getGuestsByFirstNameAndLastName")
    public DataResult<List<GetGuestDto>> getGuestsByFirstNameAndLastName(@RequestParam String firstName, @RequestParam String lastName){
        return guestService.getGuestsByFirstNameAndLastname(firstName, lastName);
    }

    @GetMapping("/getGuestByPhoneNumber")
    public DataResult<GetGuestDto> getGuestByPhoneNumber(@RequestParam String phoneNumber){
        return guestService.getGuestByPhoneNumber(phoneNumber);
    }

    @GetMapping("/getGuestByEmail")
    public DataResult<GetGuestDto> getGuestByEmail(@RequestParam String email){
        return guestService.getGuestByEmail(email);
    }

    @GetMapping("/getGuestByPassportNumber")
    public DataResult<GetGuestDto> getGuestByPassportNumber(@RequestParam String passportNumber){
        return guestService.getGuestByPassportNumber(passportNumber);
    }

    @GetMapping("/getById")
    public DataResult<GetGuestDto> getById(@RequestParam int id){
        return guestService.getById(id);
    }

    @PostMapping("/checkOut")
    public Result checkOut(@RequestParam int guestId){
        return guestService.checkOutGuest(guestId);
    }

}
