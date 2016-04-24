package com.example.matthew.eliteproject;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Galnet extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galnet);
        TextView headlineMarquee =(TextView)findViewById(R.id.headlines);
        TextView tradingButton = (TextView)findViewById(R.id.trading);
        TextView shipsButton = (TextView)findViewById(R.id.ships);
        TextView optionsButton = (TextView)findViewById(R.id.options);
        ListView articlesListView = (ListView)findViewById(R.id.articles);
        headlineMarquee.setSelected(true);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/century-gothic.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
        ArrayList<Article> articles = new ArrayList<Article>();
        String headline,date,body;
        headline="Freelance Report: Explorers Contribute to Mapping Project";
        date="24 APR 3302";
        body="For several weeks, explorers have been gathering at the Institute of Galactic Exploration and Research at Kippax Ring in the HIP 72043 system. The system has become a hub for explorers contributing to the Sagittarius-Carina Mission, a project to chart the Sagittarius-Carina arm from its root at the galactic core to its tip at the galactic rim.\n" +
                "\n" +
                "Organised by the First Great Expedition, the mission is open to all pilots - the only requirement being that they own a ship and can dream to dare.\n" +
                "\n" +
                "\"We have a strong support network, an amazing list of places to visit, and vast regions of uncharted territory to explore. What we don´t have is a schedule. Anyone can jump in any time, at any stage of the journey,\" said one member of the FGE.\n" +
                "\n" +
                "Commander Corbin Moran";
        articles.add(new Article(headline,date,body));
        ArticleAdapter adapter = new ArticleAdapter(this,articles);
        articlesListView.setAdapter(adapter);


    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
