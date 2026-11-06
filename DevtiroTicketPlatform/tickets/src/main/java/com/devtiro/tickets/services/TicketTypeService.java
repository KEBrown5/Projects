package com.devtiro.tickets.services;

import java.util.UUID;

import com.devtiro.tickets.domain.entities.Ticket;

public interface TicketTypeService {

    Ticket purchaseTicket(UUID userId, UUID ticketTypeId);
    
}
