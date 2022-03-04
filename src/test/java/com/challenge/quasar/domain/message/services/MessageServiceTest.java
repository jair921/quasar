package com.challenge.quasar.domain.message.services;

import com.challenge.quasar.domain.message.exceptions.MessageException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class MessageServiceTest {

    @Autowired
    private MessageService messageService;

    @Test
    public void getSecretMessage() throws MessageException {
        List<List<String>> messages = new ArrayList<>();

        messages.add((Arrays.asList(new String[]{"este", "", "", "mensaje", ""})));
        messages.add((Arrays.asList(new String[]{"", "es", "", "", "secreto"})));
        messages.add((Arrays.asList(new String[]{"este", "", "un", "", ""})));

        assertEquals(
                messageService.getMessage(messages),
                "este es un mensaje secreto"
        );
    }
}
