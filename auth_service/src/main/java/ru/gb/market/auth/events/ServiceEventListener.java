package ru.gb.market.auth.events;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ServiceEventListener {

    @EventListener(UserCreateEvent.class)
    public void reportUserCreation(UserCreateEvent event){
        log.info("New user with USERNAME " + event.getUsername() + " and ID " + event.getId() + " is created!");
    }

}
