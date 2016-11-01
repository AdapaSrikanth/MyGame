package com.MyGame;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff.Mode;
import android.util.AttributeSet;

public class MyForeground extends MyGameSurfaceView {
	
	Sprite mySprite;
	
	float canvasScaleX = 1.0f;
	float canvasScaleY = 1.0f;
	float canvasTranslateX = 0.0f;
	float canvasTranslateY = 0.0f;
	float canvasRotate = 0.0f;

	public MyForeground(Context context) {
		super(context);
		init();
	}

	public MyForeground(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public MyForeground(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}
	
	private void init(){
		mySprite = new Sprite(
				BitmapFactory.decodeResource(getResources(), R.drawable.icon_me),
				100, 100);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		//Clear Canvas with transparent background
		canvas.drawColor(Color.TRANSPARENT, Mode.CLEAR);
		
		canvas.scale(canvasScaleX, canvasScaleY);
		canvas.translate(canvasTranslateX, canvasTranslateY);
		canvas.rotate(canvasRotate, mySprite.getX(), mySprite.getY());
		mySprite.draw(canvas);
		
	}

	@Override
	public void updateStates() {
		// TODO Auto-generated method stub
		mySprite.update();
	}
	
	void updateAccelerometer(float tx, float ty){
		mySprite.setX((int)tx);
		mySprite.setY((int)ty);
	}
	
	public void canvasUp(){
		canvasTranslateY -= 10.0f;
	}
	
	public void canvasDown(){
		canvasTranslateY += 10.0f;
	}
	
	public void canvasLeft(){
		canvasTranslateX -= 10.0f;
	}
	
	public void canvasRight(){
		canvasTranslateX += 10.0f;
	}
	
	public void canvasClockwise(){
		canvasRotate += 10.0f;
	}
	
	public void canvasAntiClockwise(){
		canvasRotate -= 10.0f;
	}
	
	public void canvasEnlarge(){
		canvasScaleX *= 2.0f;
		canvasScaleY *= 2.0f;
	}
	
	public void canvasReduce(){
		canvasScaleX /= 2.0f;
		canvasScaleY /= 2.0f;
	}

}
