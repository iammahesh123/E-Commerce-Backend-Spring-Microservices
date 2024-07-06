package com.gfg.userservice.service;

public interface EmailService {
    void sendSimpleMessage(String to, String subject, String text);
}

