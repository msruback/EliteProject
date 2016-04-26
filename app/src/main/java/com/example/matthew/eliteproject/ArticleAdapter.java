package com.example.matthew.eliteproject;

import android.content.Context;
import android.content.SharedPreferences;
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
 * Created by mattr on 4/22/2016.
 */
public class ArticleAdapter extends ArrayAdapter<Article> {
    public ArticleAdapter(Context context, ArrayList<Article> articles) {
        super(context, 0, articles);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Article article = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_article, parent, false);
        }
        //Grabbing Views
        RelativeLayout articleLayout = (RelativeLayout)convertView.findViewById(R.id.article);
        ImageView artLogo = (ImageView)convertView.findViewById(R.id.logo);
        ImageView artLine = (ImageView)convertView.findViewById(R.id.line);
        TextView artHeadline = (TextView)convertView.findViewById(R.id.headline);
        TextView artDate = (TextView)convertView.findViewById(R.id.date);
        TextView artBody = (TextView)convertView.findViewById(R.id.body);
        //Getting the article data
        artHeadline.setText(article.getHeadline());
        artDate.setText(article.getDate());
        artBody.setText(article.getBody());
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getContext());
        switch(settings.getString("styles","fail")){
            case "galnet":
                articleLayout.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.colorPrimaryBackground2));
                artLogo.setBackgroundResource(R.drawable.primary_logo);
                artHeadline.setTextColor(ContextCompat.getColor(getContext(),R.color.colorPrimary));
                artLine.setColorFilter(ContextCompat.getColor(getContext(),R.color.colorPrimary));
                break;
            case "empire":
                articleLayout.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.colorEmpireBackground2));
                artLogo.setBackgroundResource(R.drawable.empire_logo);
                artHeadline.setTextColor(ContextCompat.getColor(getContext(),R.color.colorEmpire));
                artLine.setColorFilter(ContextCompat.getColor(getContext(),R.color.colorEmpire));
                break;
            case "alliance":
                articleLayout.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.colorAllianceBackground2));
                artLogo.setBackgroundResource(R.drawable.alliance_logo);
                artHeadline.setTextColor(ContextCompat.getColor(getContext(),R.color.colorAlliance));
                artLine.setColorFilter(ContextCompat.getColor(getContext(),R.color.colorAlliance));
                break;

        }
        return convertView;
    }
}
