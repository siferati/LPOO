package com.example.tiago.lpoo.Layouts;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.example.tiago.lpoo.Logic.Wizard;
import com.example.tiago.lpoo.R;

public class GameActivityLayout extends SurfaceView implements Runnable{

    //Attributes:

    Wizard wizard;
    Thread thread;
    boolean canDraw;
    Canvas canvas;
    SurfaceHolder surfaceHolder;

    //Methods:

    public GameActivityLayout(Context context) {
        super(context);
        Bitmap wizardAnimation = BitmapFactory.decodeResource(getResources(), R.drawable.wizard_animation);
        wizard = new Wizard(50, 50, wizardAnimation, null);
        thread = null;
        canDraw = false;
        surfaceHolder = getHolder();
    }

    @Override
    public void run() {

        while (canDraw)
        {
            if (!surfaceHolder.getSurface().isValid())
                continue;
            canvas = surfaceHolder.lockCanvas();
            canvas.drawBitmap(wizard.getBitmap(), wizard.getPosition().x, wizard.getPosition().y, null);
            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    public void pause()
    {
        canDraw = false;
        boolean control = true;
        while (control) {
            try {
                thread.join();
                control = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        thread = null;
    }

    public void resume()
    {
        canDraw = true;
        thread = new Thread(this);
        thread.start();
    }
}
