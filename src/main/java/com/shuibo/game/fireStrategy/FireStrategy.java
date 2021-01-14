package com.shuibo.game.fireStrategy;

import com.shuibo.game.GameObject;
import com.shuibo.game.Tank;

import java.util.ArrayList;

public interface FireStrategy {
    ArrayList<GameObject> fire(Tank tank);
}