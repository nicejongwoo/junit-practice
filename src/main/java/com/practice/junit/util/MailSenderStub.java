package com.practice.junit.util;

import org.springframework.stereotype.Component;

@Component
public class MailSenderStub implements MailSender {

    @Override
    public boolean send() {
        return true;
    }

}
