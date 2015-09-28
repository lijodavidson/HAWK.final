package com.example.lijo.hawkeye;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

public class HOME extends Activity implements View.OnClickListener{
    public static  final String TAG_SEARCH="search";
    public static  final String TAG_NEWS="news";
    public static  final String TAG_ADDCASE="addcase";
    public static  final String TAG_LOGOUT="logout";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.fab1);

        FloatingActionButton actionButton = new FloatingActionButton.Builder(this)
                .setContentView(imageView)
                .build();

        // SubActionButton.Builder itemBuilder = new SubActionButton.Builder(this);
        ImageView iconnews = new ImageView(this);
        iconnews.setImageResource(R.drawable.exit);

        ImageView iconsearch = new ImageView(this);
        iconsearch.setImageResource(R.drawable.exit);


        ImageView iconaddcase = new ImageView(this);
        iconsearch.setImageResource(R.drawable.abc_ic_search_api_mtrl_alpha);

        ImageView iconlogout = new ImageView(this);
        iconlogout.setImageResource(R.drawable.exit);

        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(this);


        SubActionButton buttonnews = itemBuilder.setContentView(iconnews).build();
        SubActionButton buttonsearch = itemBuilder.setContentView(iconsearch).build();
        SubActionButton buttonaddcase = itemBuilder.setContentView(iconaddcase).build();
        SubActionButton buttonlogout = itemBuilder.setContentView(iconlogout).build();


        buttonnews.setTag(TAG_NEWS);
        buttonnews.setTag(TAG_SEARCH);
        buttonnews.setTag(TAG_ADDCASE);
        buttonnews.setTag(TAG_LOGOUT);


        buttonnews.setOnClickListener(this);
        buttonsearch.setOnClickListener(this);
        buttonaddcase.setOnClickListener(this);
        buttonlogout.setOnClickListener(this);


        FloatingActionMenu actionMenu = new FloatingActionMenu.Builder(this)
                .addSubActionView(buttonnews)
                .addSubActionView(buttonsearch)
                .addSubActionView(buttonaddcase)
                .addSubActionView(buttonlogout)


                .attachTo(actionButton)
                .build();










    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);


    }

    @Override
    public void onClick(View v) {

        if(v.getTag().equals(TAG_NEWS))
        {


        }


        if(v.getTag().equals(TAG_SEARCH))
        {

            Controller.clearCache(getApplicationContext());
            Intent z = new Intent(getApplicationContext(), LOGIN.class);
            startActivity(z);
            finish();


        }



        if(v.getTag().equals(TAG_ADDCASE))
        {

            Intent z = new Intent(this, Locator.class);
            startActivity(z);

        }


        if(v.getTag().equals(TAG_LOGOUT))
        {

            Controller.clearCache(getApplicationContext());
            Intent z = new Intent(getApplicationContext(), LOGIN.class);
            startActivity(z);
            finish();


        }

    }
}
