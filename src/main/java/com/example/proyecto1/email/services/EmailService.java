package com.example.proyecto1.email.services;

import jakarta.mail.MessagingException;

public interface EmailService {
    void enviarEmailConfirmacion(String emailDestino, String codigo) throws MessagingException;
}
