package com.example.springboot.services;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author Manoel Campos
 */
@Service @Qualifier("v2")
public class MessageServiceV2 implements MessageService {
    @Override
    public String say(String message) {
        return getClass().getSimpleName() + ": " + message.replaceAll(" ", "-");
    }
}
