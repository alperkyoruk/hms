package com.alperkyoruk.hms.entities.DTOs.Ticket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TicketStatusDto {
    private String ticketNumber;
    private String status;
    private String comment;

}
