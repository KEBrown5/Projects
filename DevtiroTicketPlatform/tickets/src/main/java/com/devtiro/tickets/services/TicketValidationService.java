package com.devtiro.tickets.services;

import java.util.UUID;

import com.devtiro.tickets.domain.entities.TicketValidation;

public interface TicketValidationService {
    
    TicketValidation validateTicketByQrCode(UUID qrCodeId);

    TicketValidation validateTicketManually(UUID ticketId);
}
