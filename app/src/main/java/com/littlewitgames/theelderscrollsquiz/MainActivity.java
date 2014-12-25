package com.littlewitgames.theelderscrollsquiz;
import com.google.android.gms.ads.InterstitialAd;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends ActionBarActivity {
    private InterstitialAd interstitial;
    private Button playGameBtn;
    private Button howToPlaybtn;
    private Intent intent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addButtonListener();
    }

    public void addButtonListener() {
        playGameBtn      = (Button) this.findViewById(R.id.playButton);
        howToPlaybtn     = (Button) this.findViewById(R.id.howButton);

        playGameBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openGameMenu();
            }
        });
        howToPlaybtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openListMenu();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.screenshot:
                Bitmap screenshot = takeScreenshot();
                saveToInternalStorage(screenshot);
                saveBitmap(screenshot);
                System.out.println("Screenshot taken");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public Bitmap takeScreenshot() {
        View rootView = findViewById(android.R.id.content).getRootView();
        rootView.setDrawingCacheEnabled(true);
        return rootView.getDrawingCache();
    }

    public void saveBitmap(Bitmap bitmap) {
        File imagePath = new File(Environment.getExternalStorageDirectory() + "/screenshot.png");
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(imagePath);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            Log.e("GREC", e.getMessage(), e);
        } catch (IOException e) {
            Log.e("GREC", e.getMessage(), e);
        }

        System.out.println(Environment.getExternalStorageDirectory());
    }

    private void saveToInternalStorage(Bitmap bitmapImage){
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        // Create imageDir
        File mypath = new File(directory,"quizScreenshot.jpg");

        FileOutputStream fos = null;
        try {

            fos = new FileOutputStream(mypath);

            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(mypath);
    }

    public void openGameMenu() {
        this.intent = new Intent(this, GameMenuActivity.class);
        startActivity(intent);
    }

    public void openListMenu() {
        this.intent = new Intent(this, GameHelpActivity.class);
        startActivity(intent);
    }
 }


