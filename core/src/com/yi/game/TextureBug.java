package com.yi.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class TextureBug {
    static Texture textureBug = new Texture(Gdx.files.internal("bug.png"));
    Rectangle rectBugBorder;
    float screenWidth = Gdx.graphics.getWidth();
    float screenHeight = Gdx.graphics.getHeight();
    static float textureWidth = textureBug.getWidth();
    static float textureHeight = textureBug.getHeight();
    Vector2 vector2BugXY;
    float velocity;

    public TextureBug(){
        rectBugBorder = new Rectangle();
        rectBugBorder.x = MathUtils.random(1, screenWidth - textureWidth - 1);
        rectBugBorder.y = screenHeight - textureHeight - 1;
        rectBugBorder.width = textureWidth;
        rectBugBorder.height = textureHeight;

        vector2BugXY = new Vector2(0, 0);
        velocity = 200f;
        vector2BugXY.x = MathUtils.random(-velocity, velocity)*2;
        vector2BugXY.y = -velocity;
    }
}
