package ru.gb.market.auth.repositories;

import ru.gb.market.auth.entities.UserEntity;

import java.util.Map;

public class IdentityMap {

    private Map <Long, UserEntity> map;

    public void add(UserEntity user){
        map.put(user.getId(), user);
    }


}
