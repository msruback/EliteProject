package com.example.matthew.eliteproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by mattr on 4/26/2016.
 */
public class StyleAdapter extends ArrayAdapter<ImageText>{

    public StyleAdapter(Context context, ArrayList<ImageText> styles) {
        super(context, 0, styles);
    }
    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        return getView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageText style = getItem(position);
        LayoutInflater inflater=(LayoutInflater) getContext().getSystemService(  Context.LAYOUT_INFLATER_SERVICE );
        View row=inflater.inflate(R.layout.spinner_style, parent, false);
        RelativeLayout option = (RelativeLayout)row.findViewById(R.id.option);
        TextView name=(TextView)row.findViewById(R.id.name);
        ImageView logo = (ImageView) row.findViewById(R.id.logo);
        switch(style.getName()){
            case "Galnet":
                option.getBackground().mutate().setColorFilter(ContextCompat.getColor(getContext(),R.color.colorPrimary), PorterDuff.Mode.MULTIPLY);
                name.setTextColor(ContextCompat.getColor(getContext(),R.color.colorPrimary));
                break;
            case "Empire":
                option.getBackground().mutate().setColorFilter(ContextCompat.getColor(getContext(),R.color.colorEmpire), PorterDuff.Mode.MULTIPLY);
                name.setTextColor(ContextCompat.getColor(getContext(),R.color.colorEmpire));
                break;
            case "Alliance":
                option.getBackground().mutate().setColorFilter(ContextCompat.getColor(getContext(),R.color.colorAlliance), PorterDuff.Mode.MULTIPLY);
                name.setTextColor(ContextCompat.getColor(getContext(),R.color.colorAlliance));
                break;
            case "Federation":
                option.getBackground().mutate().setColorFilter(ContextCompat.getColor(getContext(),R.color.colorFederation), PorterDuff.Mode.MULTIPLY);
                name.setTextColor(ContextCompat.getColor(getContext(),R.color.colorFederation));
                break;
            case "Independent":
                option.getBackground().mutate().setColorFilter(ContextCompat.getColor(getContext(),R.color.colorIndependent), PorterDuff.Mode.MULTIPLY);
                name.setTextColor(ContextCompat.getColor(getContext(),R.color.colorIndependent));
                break;
        }
        name.setText(style.getName());
        logo.setBackgroundResource(style.getImage());
        return row;
    }
}
