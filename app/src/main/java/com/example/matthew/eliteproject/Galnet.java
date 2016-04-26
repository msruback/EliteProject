package com.example.matthew.eliteproject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Galnet extends AppCompatActivity {
    SwipeRefreshLayout refreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galnet);
        Calendar c = Calendar.getInstance();
        int date = c.get(Calendar.DAY_OF_YEAR);
        TextView headlineMarquee = (TextView) findViewById(R.id.headlines);
        ImageView tickerLine = (ImageView) findViewById(R.id.tickerLine);
        TextView galnetButton = (TextView) findViewById(R.id.galnet);
        TextView tradingButton = (TextView) findViewById(R.id.trading);
        TextView shipsButton = (TextView) findViewById(R.id.ships);
        TextView optionsButton = (TextView) findViewById(R.id.options);
        ImageView headerLine = (ImageView) findViewById(R.id.headerLine);
        ImageView headerBackground = (ImageView) findViewById(R.id.headerBackground);
        ListView articlesListView = (ListView) findViewById(R.id.articles);
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresher);
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        if(!settings.contains("styles")){
            SharedPreferences.Editor edit = settings.edit();
            edit.putString("styles","galnet");
            edit.commit();
        }
        switch(settings.getString("styles","fail")){
            case "galnet":
                //header styling
                galnetButton.setBackgroundColor(ContextCompat.getColor(this,R.color.colorPrimaryHighlight));
                tradingButton.setTextColor(ContextCompat.getColor(this,R.color.colorPrimaryDark));
                shipsButton.setTextColor(ContextCompat.getColor(this,R.color.colorPrimaryDark));
                optionsButton.setTextColor(ContextCompat.getColor(this,R.color.colorPrimary));
                headerLine.setColorFilter(ContextCompat.getColor(this,R.color.colorPrimary));
                headerBackground.setBackgroundColor(ContextCompat.getColor(this,R.color.colorPrimaryBackground));

                //ticker styling
                headlineMarquee.setTextColor(ContextCompat.getColor(this,R.color.colorPrimary));
                headlineMarquee.setBackgroundColor(ContextCompat.getColor(this,R.color.colorPrimaryBackground));
                tickerLine.setColorFilter(ContextCompat.getColor(this,R.color.colorPrimary));

                //refresh styling
                refreshLayout.setColorSchemeColors(ContextCompat.getColor(this,R.color.colorPrimary),0,0,0);
                refreshLayout.setProgressBackgroundColorSchemeColor(ContextCompat.getColor(this,R.color.colorPrimaryBackground2));

                break;
            case "empire":
                //header styling
                galnetButton.setBackgroundColor(ContextCompat.getColor(this,R.color.colorEmpireHighlight));
                tradingButton.setTextColor(ContextCompat.getColor(this,R.color.colorEmpireDark));
                shipsButton.setTextColor(ContextCompat.getColor(this,R.color.colorEmpireDark));
                optionsButton.setTextColor(ContextCompat.getColor(this,R.color.colorEmpire));
                headerLine.setColorFilter(ContextCompat.getColor(this,R.color.colorEmpire));
                headerBackground.setBackgroundColor(ContextCompat.getColor(this,R.color.colorEmpireBackground));

                //ticker styling
                headlineMarquee.setTextColor(ContextCompat.getColor(this,R.color.colorEmpire));
                headlineMarquee.setBackgroundColor(ContextCompat.getColor(this,R.color.colorEmpireBackground));
                tickerLine.setColorFilter(ContextCompat.getColor(this,R.color.colorEmpire));

                //refresh styling
                refreshLayout.setColorSchemeColors(ContextCompat.getColor(this,R.color.colorEmpire),0,0,0);
                refreshLayout.setProgressBackgroundColorSchemeColor(ContextCompat.getColor(this,R.color.colorEmpireBackground2));

                break;
        }

        headlineMarquee.setSelected(true);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/century-gothic.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
        refreshLayout.setOnRefreshListener((new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new GetArticles().execute();
                refreshLayout.setRefreshing(false);
            }
        }));
        if(!settings.contains("lastUpdated")){
            SharedPreferences.Editor edit = settings.edit();
            edit.putInt("lastUpdated",date);
            edit.commit();
            new GetArticles().execute();
        }

        if(settings.getInt("lastUpdated",date)!=date) {
            new GetArticles().execute();
        }

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    private class GetArticles extends AsyncTask<Void, Void, Void> {
        ArrayList<Article> articles;
        ProgressDialog mProgressDialog;
        String ticker;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            articles = new ArrayList<Article>();
            ticker = "|| ";
            Calendar c = Calendar.getInstance();
            ticker += c.get(Calendar.DAY_OF_MONTH);
            switch(c.get(Calendar.MONTH)){
                case 0:
                    ticker+=" JAN ";
                    break;
                case 1:
                    ticker+=" FEB ";
                    break;
                case 2:
                    ticker+=" MAR ";
                    break;
                case 3:
                    ticker+=" APR ";
                    break;
                case 4:
                    ticker+=" MAY ";
                    break;
                case 5:
                    ticker+=" JUN ";
                    break;
                case 6:
                    ticker+=" JUL ";
                    break;
                case 7:
                    ticker+=" AUG ";
                    break;
                case 8:
                    ticker+=" SEP ";
                    break;
                case 9:
                    ticker+=" OCT ";
                    break;
                case 10:
                    ticker+=" NOV ";
                    break;
                case 11:
                    ticker+=" DEC ";
                    break;
            }
            ticker+=(c.get(Calendar.YEAR)+1286);
            ticker+=" || ";
            mProgressDialog = new ProgressDialog(Galnet.this);
            mProgressDialog.setTitle("Getting News");
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Document galnetDoc = Jsoup.connect("https://community.elitedangerous.com/galnet").get();
                Elements articlesElement = galnetDoc.getElementsByClass("article");
                Element article;
                String headline, date, bodyHTML,body;

                for (int i = 0; i < 15; i++) {
                    article = articlesElement.get(i);
                    headline = article.getElementsByTag("a").get(0).ownText();
                    ticker+=headline;
                    ticker+=" | ";
                    date = article.getElementsByTag("p").get(0).ownText();
                    //needs to preserve linebreaks
                    bodyHTML = article.getElementsByTag("p").get(1).html();
                    Document bodyDocument = Jsoup.parse(bodyHTML);
                    bodyDocument.outputSettings(new Document.OutputSettings().prettyPrint(false));
                    bodyDocument.select("br").append("\\n");
                    bodyDocument.select("p").prepend("\\n\\n");
                    String s = bodyDocument.html().replaceAll("\\\\n", "\n");
                    body=Jsoup.clean(s, "", Whitelist.none(), new Document.OutputSettings().prettyPrint(false));
                    articles.add(new Article(headline, date, body));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            TextView tickerView = (TextView) findViewById(R.id.headlines);
            ListView articlesListView = (ListView) findViewById(R.id.articles);
            tickerView.setText(ticker);
            ArticleAdapter adapter = new ArticleAdapter(getApplicationContext(), articles);
            articlesListView.setAdapter(adapter);
            mProgressDialog.dismiss();
        }
    }
}
