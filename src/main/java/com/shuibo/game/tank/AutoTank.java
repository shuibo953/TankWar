package com.shuibo.game.tank;

import com.shuibo.game.Dir;
import com.shuibo.game.Group;

import java.awt.*;
import java.util.Random;

public class AutoTank extends Tank {
    static private final Random random = new Random();

    public AutoTank(int x, int y) {
        super(x, y);
    }

    @Override
    public void paint(Graphics graphics) {
        paintHelper(graphics);
        setIsFiring(random.nextInt(10) > 8);//下次是否发射
        if (random.nextInt(100) > 95) setDir(Dir.values()[random.nextInt(4)]);//下次的移动方向
        move();//自动前进
    }
}
