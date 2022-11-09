package ru.gb.market.core.controllers;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import ru.gb.market.core.utils.StatisticUtil;


import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class StatisticController {



    @GetMapping("/stat")
    public Map<Class<?>, Long> createOrder() {
         return StatisticUtil.getStatistic();
    }
}
