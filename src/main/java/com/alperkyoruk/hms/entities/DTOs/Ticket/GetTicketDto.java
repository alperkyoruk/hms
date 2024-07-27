package com.alperkyoruk.hms.entities.DTOs.Ticket;

import com.alperkyoruk.hms.entities.Ticket;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetTicketDto {
    private String issue;
    private String ticketNumber;
    private String status;
    private String category;
    private Date createdDate;
    private Date resolvedDate;
    private String description;
    private String comment;
    private int guestId;

    public GetTicketDto(Ticket ticket) {
        this.issue = ticket.getIssue();
        this.ticketNumber = ticket.getTicketNumber();
        this.status = ticket.getStatus();
        this.category = ticket.getCategory();
        this.createdDate = ticket.getCreatedDate();
        this.resolvedDate = ticket.getResolvedDate();
        this.description = ticket.getDescription();
        this.comment = ticket.getComment();
        this.guestId = ticket.getGuest().getId();
    }

    public List<GetTicketDto> buildListGetTicketDto(List<Ticket> tickets) {
        return tickets.stream()
                .map(GetTicketDto::new)
                .collect(Collectors.toList());
    }
}
