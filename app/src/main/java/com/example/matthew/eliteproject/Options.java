package com.example.matthew.eliteproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Options extends AppCompatActivity {
    Spinner styleSpinner;
    SharedPreferences settings;
    TextView galnetButton,tradingButton,shipsButton,optionsButton,styleText;
    ImageView headerLine,headerBackground,styleDivider,styleLine;
    RelativeLayout styleLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ArrayList<ImageText> styleOptions = new ArrayList<ImageText>();
        styleOptions.add(new ImageText("Galnet",R.drawable.primary_logo));
        styleOptions.add(new ImageText("Empire",R.drawable.empire_logo));
        styleOptions.add(new ImageText("Alliance",R.drawable.alliance_logo));
        styleOptions.add(new ImageText("Federation",R.drawable.federation_logo));
        styleOptions.add(new ImageText("Independent",R.drawable.independent_logo));
        settings = PreferenceManager.getDefaultSharedPreferences(this);
        if(!settings.contains("styles")){
            SharedPreferences.Editor edit = settings.edit();
            edit.putString("styles","galnet");
            edit.commit();
        }
        switch(settings.getString("styles","fail")){
            case "galnet":
                setTheme(R.style.AppThemePrimary);
                break;
            case "empire":
                setTheme(R.style.AppThemeEmpire);
                break;
            case "alliance":
                setTheme(R.style.AppThemeAlliance);
                break;
            case "federation":
                setTheme(R.style.AppThemeFederation);
                break;
            case "independent":
                setTheme(R.style.AppThemeIndependent);
                break;
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        galnetButton = (TextView) findViewById(R.id.galnet);
        tradingButton = (TextView) findViewById(R.id.trading);
        shipsButton = (TextView) findViewById(R.id.ships);
        optionsButton = (TextView) findViewById(R.id.options);
        headerLine = (ImageView) findViewById(R.id.headerLine);
        headerBackground = (ImageView) findViewById(R.id.headerBackground);
        styleLayout = (RelativeLayout) findViewById(R.id.stylePreference);
        styleText = (TextView) findViewById(R.id.styleText);
        styleSpinner = (Spinner) findViewById(R.id.styleSpinner);
        styleLine = (ImageView) findViewById(R.id.styleLine);
        styleDivider = (ImageView) findViewById(R.id.styleDivider);

        StyleAdapter styleAdapter = new StyleAdapter(this,styleOptions);
        styleSpinner.setAdapter(styleAdapter);
        switch(settings.getString("styles","fail")){
            case "galnet":
                styleSpinner.setSelection(0);
                break;
            case "empire":
                styleSpinner.setSelection(1);
                break;
            case "alliance":

                styleSpinner.setSelection(2);

                break;
            case "federation":
                styleSpinner.setSelection(3);

                break;
            case "independent":
                styleSpinner.setSelection(4);

                break;
        }

        galnetButton.setOnClickListener(new TextView.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), Galnet.class);
                startActivity(intent);
            }
        });
        styleSpinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                settings = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor edit = settings.edit();
                switch(position){
                    case 0:
                        //header styling
                        galnetButton.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.colorPrimary));
                        optionsButton.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.colorPrimaryHighlight));
                        tradingButton.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.colorPrimaryDark));
                        shipsButton.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.colorPrimaryDark));
                        optionsButton.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.colorPrimaryHighlight));
                        headerLine.setColorFilter(ContextCompat.getColor(getApplicationContext(),R.color.colorPrimary));
                        headerBackground.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.colorPrimaryBackground));

                        //preference styling
                        styleLayout.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.colorPrimaryBackground2));
                        styleText.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.colorPrimary));
                        styleDivider.setColorFilter(ContextCompat.getColor(getApplicationContext(),R.color.colorPrimary));
                        styleLine.setColorFilter(ContextCompat.getColor(getApplicationContext(),R.color.colorPrimary));

                        edit.putString("styles","galnet");
                        break;
                    case 1:
                        //header styling
                        galnetButton.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.colorEmpire));
                        tradingButton.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.colorEmpireDark));
                        shipsButton.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.colorEmpireDark));
                        optionsButton.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.colorEmpireHighlight));
                        headerLine.setColorFilter(ContextCompat.getColor(getApplicationContext(),R.color.colorEmpire));
                        headerBackground.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.colorEmpireBackground));

                        //preference styling
                        styleLayout.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.colorEmpireBackground2));
                        styleText.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.colorEmpire));
                        styleDivider.setColorFilter(ContextCompat.getColor(getApplicationContext(),R.color.colorEmpire));
                        styleLine.setColorFilter(ContextCompat.getColor(getApplicationContext(),R.color.colorEmpire));

                        edit.putString("styles","empire");
                        break;
                    case 2:
                        //header styling
                        galnetButton.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.colorAlliance));
                        tradingButton.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.colorAllianceDark));
                        shipsButton.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.colorAllianceDark));
                        optionsButton.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.colorAllianceHighlight));
                        headerLine.setColorFilter(ContextCompat.getColor(getApplicationContext(),R.color.colorAlliance));
                        headerBackground.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.colorAllianceBackground));

                        //preference styling
                        styleLayout.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.colorAllianceBackground2));
                        styleText.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.colorAlliance));
                        styleDivider.setColorFilter(ContextCompat.getColor(getApplicationContext(),R.color.colorAlliance));
                        styleLine.setColorFilter(ContextCompat.getColor(getApplicationContext(),R.color.colorAlliance));

                        edit.putString("styles","alliance");
                        break;
                    case 3:
                        //header styling
                        galnetButton.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.colorFederation));
                        tradingButton.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.colorFederationDark));
                        shipsButton.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.colorFederationDark));
                        optionsButton.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.colorFederationHighlight));
                        headerLine.setColorFilter(ContextCompat.getColor(getApplicationContext(),R.color.colorFederation));
                        headerBackground.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.colorFederationBackground));

                        //preference styling
                        styleLayout.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.colorFederationBackground2));
                        styleText.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.colorFederation));
                        styleDivider.setColorFilter(ContextCompat.getColor(getApplicationContext(),R.color.colorFederation));
                        styleLine.setColorFilter(ContextCompat.getColor(getApplicationContext(),R.color.colorFederation));

                        edit.putString("styles","federation");
                        break;
                    case 4:
                        //header styling
                        galnetButton.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.colorIndependent));
                        tradingButton.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.colorIndependentDark));
                        shipsButton.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.colorIndependentDark));
                        optionsButton.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.colorIndependentHighlight));
                        headerLine.setColorFilter(ContextCompat.getColor(getApplicationContext(),R.color.colorIndependent));
                        headerBackground.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.colorIndependentBackground));

                        //preference styling
                        styleLayout.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.colorIndependentBackground2));
                        styleText.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.colorIndependent));
                        styleDivider.setColorFilter(ContextCompat.getColor(getApplicationContext(),R.color.colorIndependent));
                        styleLine.setColorFilter(ContextCompat.getColor(getApplicationContext(),R.color.colorIndependent));

                        edit.putString("styles","independent");
                        break;
                }
                edit.commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }

        });


        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/century-gothic.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
