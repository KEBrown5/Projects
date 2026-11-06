package com.devtiro.tickets.services;

import java.util.UUID;

import com.devtiro.tickets.domain.entities.QrCode;
import com.devtiro.tickets.domain.entities.Ticket;

public interface QrCodeService {

    QrCode generateQrCode(Ticket ticket);

    byte[] getQrCodeImageForUserAndTicket(UUID userId, UUID ticketId);
}
