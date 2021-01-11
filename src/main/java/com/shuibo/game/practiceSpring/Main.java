package com.shuibo.game.practiceSpring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("practiceSpringApp.xml");
        context.getBean("driver");
        context.getBean("tank");
    }
}
