package com.yi.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 */
public class TextureDroplet {
    static Texture textureDroplet = new Texture(Gdx.files.internal("droplet.png"));
    Rectangle rectDropletBorder;
    float screenWidth = Gdx.graphics.getWidth();
    float screenHeight = Gdx.graphics.getHeight();
    static float textureWidth = textureDroplet.getWidth();
    static float textureHeight = textureDroplet.getHeight();
    Vector2 vector2DropletXY;
    float velocity;

    public TextureDroplet(){
        rectDropletBorder = new Rectangle();
        rectDropletBorder.x = screenWidth / 2 - textureWidth / 2;
        rectDropletBorder.y = 1;
        rectDropletBorder.width = textureWidth;
        rectDropletBorder.height = textureHeight;

        vector2DropletXY = new Vector2(0, 0);
        velocity = 500f;
    }

    public void positionUpdate(int touchX, float deltaTime){
        if(touchX < rectDropletBorder.x + textureWidth/2){
            rectDropletBorder.x += -velocity * deltaTime;
        }
        else if(touchX > rectDropletBorder.x + textureWidth/2){
            rectDropletBorder.x += velocity * deltaTime;
        }
    }

    public void reset(){
        rectDropletBorder.x = screenWidth / 2 - textureWidth / 2;
        rectDropletBorder.y = 1;
    }
}
