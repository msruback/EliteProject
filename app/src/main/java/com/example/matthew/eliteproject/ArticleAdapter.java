package com.example.matthew.eliteproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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
        // Lookup view for data population
        TextView artHeadline = (TextView)convertView.findViewById(R.id.headline);
        TextView artDate = (TextView)convertView.findViewById(R.id.date);
        TextView artBody = (TextView)convertView.findViewById(R.id.body);
        // Populate the data into the template view using the data object
        artHeadline.setText(article.getHeadline());
        artDate.setText(article.getDate());
        artBody.setText(article.getBody());
        // Return the completed view to render on screen
        return convertView;
    }
}
