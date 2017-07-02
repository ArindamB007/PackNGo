package com.png.com.png.messages;


import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * Created by Arindam on 7/2/2017.
 */
@Component
public class CustomMessages implements MessageSourceAware{
    private MessageSource messageSource;
    public void setMessageSource(MessageSource messageSource){
        this.messageSource = messageSource;
    }
    public String getMessage(String msgId){
        return messageSource.getMessage(msgId,null, Locale.US);
    }
}
