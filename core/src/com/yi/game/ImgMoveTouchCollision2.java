package com.yi.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class ImgMoveTouchCollision2 extends ApplicationAdapter {
	SpriteBatch batch;
	TextureBugControl textureBugControl;
	TextureDroplet textureDroplet;
	TextureCookieControl textureCookieControl;

	@Override
	public void create () {
		batch = new SpriteBatch();
		textureBugControl = new TextureBugControl();
		textureBugControl.addNTextureBug(5);
		textureDroplet = new TextureDroplet();
		textureCookieControl = new TextureCookieControl();
	}

	@Override
	public void dispose() {
		batch.dispose();
		for(int i=0; i < textureBugControl.arrTextureBugs.size; i++){
			textureBugControl.arrTextureBugs.get(i).textureBug.dispose();
		}
		textureDroplet.textureDroplet.dispose();
		for(int i=0; i < textureCookieControl.arrTextureCookies.size; i++){
			textureCookieControl.arrTextureCookies.get(i).textureCookie.dispose();
		}
	}

	@Override
	public void render() {
		update();
		draw();
	}

	public void resetGame(){
		textureCookieControl.arrTextureCookies.clear();
		textureBugControl.arrTextureBugs.clear();
		textureBugControl.addNTextureBug(5);
		textureDroplet.reset();
	}

	public void update() {
		Gdx.input.setInputProcessor(new InputProcessor() {
			@Override
			public boolean touchDown(int screenX, int screenY, int pointer, int button) {
				textureDroplet.positionUpdate(screenX, Gdx.graphics.getDeltaTime() * 2);
				return true;
			}
			@Override
			public boolean touchUp(int screenX, int screenY, int pointer, int button) {
				textureCookieControl.addTextureCookie(
						new Vector2(textureDroplet.rectDropletBorder.x + textureDroplet.rectDropletBorder.width/2 - TextureCookie.textureCookie.getWidth()/2,
						textureDroplet.rectDropletBorder.y + textureDroplet.rectDropletBorder.height));
				return true;
			}
			@Override
			public boolean touchDragged(int screenX, int screenY, int pointer) {
				textureDroplet.positionUpdate(screenX, Gdx.graphics.getDeltaTime());
				return true;
			}
			@Override
			public boolean keyDown(int keycode) {
				return false;
			}
			@Override
			public boolean keyUp(int keycode) {
				return false;
			}
			@Override
			public boolean keyTyped(char character) {
				return false;
			}
			@Override
			public boolean mouseMoved(int screenX, int screenY) {
				return false;
			}
			@Override
			public boolean scrolled(int amount) {
				return false;
			}
		});

		if(textureBugControl.arrTextureBugs.size == 0){
			resetGame();
		}
		textureCookieControl.update();
		textureBugControl.update();
	}

	public void draw(){
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();

		for(TextureCookie textureCookie : textureCookieControl.arrTextureCookies){
			batch.draw(textureCookie.textureCookie, textureCookie.rectCookieBorder.x, textureCookie.rectCookieBorder.y);
		}
		for(TextureBug textureBug : textureBugControl.arrTextureBugs){
			batch.draw(textureBug.textureBug, textureBug.rectBugBorder.x, textureBug.rectBugBorder.y,
					textureBug.rectBugBorder.width, textureBug.rectBugBorder.height);
			for(TextureCookie textureCookie : textureCookieControl.arrTextureCookies){
				if(textureCookie.rectCookieBorder.overlaps(textureBug.rectBugBorder)){
					textureCookieControl.arrTextureCookies.removeValue(textureCookie, true);
					textureBugControl.arrTextureBugs.removeValue(textureBug, true);
				}
			}
			if(textureBug.rectBugBorder.overlaps(new Rectangle(textureDroplet.rectDropletBorder.x, textureDroplet.rectDropletBorder.y,
					textureDroplet.rectDropletBorder.width, textureDroplet.rectDropletBorder.height))){
				resetGame();
			}
		}

		batch.draw(textureDroplet.textureDroplet, textureDroplet.rectDropletBorder.x, textureDroplet.rectDropletBorder.y);

		batch.end();
	}
}
