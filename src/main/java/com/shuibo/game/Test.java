package com.shuibo.game;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> System.out.println(new ClassPathXmlApplicationContext("practiceSpringApp.xml")
                    .getBean("imageFactory").hashCode())).start();
        }
    }
}
