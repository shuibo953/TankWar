package com.shuibo.game.tank;

import com.shuibo.game.Dir;
import com.shuibo.game.fireStrategy.DefaultFireStrategy;
import com.shuibo.game.fireStrategy.FireStrategy;
import com.shuibo.game.fireStrategy.FourDirectionFireStrategy;
import com.shuibo.game.fireStrategy.NBombFireStrategy;

import java.awt.*;
import java.util.ArrayList;

public class ManualTank extends Tank {
    private boolean isMoving;

    public ManualTank(int x, int y) {
        super(x, y);
    }

    public void setIsMoving_Dir(ArrayList<Dir> dir) {
        if (!dir.isEmpty()) {
            Dir pre = getDir();
            setDir(dir.get(dir.size() - 1));
            isMoving = pre == getDir();
        } else isMoving = false;
    }

    public void changeFireStrategy() {
        FireStrategy strategy = getFireStrategy();
        if (strategy.equals(DefaultFireStrategy.STRATEGY)) {
            setFireStrategy(FourDirectionFireStrategy.STRATEGY);
        } else if (strategy.equals(FourDirectionFireStrategy.STRATEGY)) {
            setFireStrategy(NBombFireStrategy.STRATEGY);
        } else if (strategy.equals(NBombFireStrategy.STRATEGY)) {
            setFireStrategy(DefaultFireStrategy.STRATEGY);
        }
    }

    @Override
    public void paint(Graphics graphics) {
        paintHelper(graphics);
        if (isMoving) move();
    }
}
