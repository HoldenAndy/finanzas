package com.example.proyecto1.services;

import jakarta.mail.MessagingException;

public interface EmailService {
    void enviarEmailConfirmacion(String emailDestino, String codigo) throws MessagingException;
}
