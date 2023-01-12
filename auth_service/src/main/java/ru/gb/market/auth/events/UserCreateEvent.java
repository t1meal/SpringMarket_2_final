package ru.gb.market.auth.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class UserCreateEvent extends ApplicationEvent {

    private final String username;
    private final Long id;

    public UserCreateEvent(Object source,Long userId,String username) {
        super(source);
        this.username = username;
        this.id = userId;
    }
}
