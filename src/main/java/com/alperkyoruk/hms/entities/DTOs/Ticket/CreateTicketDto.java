package com.alperkyoruk.hms.entities.DTOs.Ticket;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CreateTicketDto {
    private String issue;
    private String status;
    private String category;
    private Date createdDate;
    private Date resolvedDate;
    private String description;
    private String comment;
    private int guestId;
}
