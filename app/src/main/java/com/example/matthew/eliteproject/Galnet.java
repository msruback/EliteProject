package com.example.matthew.eliteproject;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.widget.ListView;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Galnet extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galnet);
        TextView headlineMarquee = (TextView) findViewById(R.id.headlines);
        TextView tradingButton = (TextView) findViewById(R.id.trading);
        TextView shipsButton = (TextView) findViewById(R.id.ships);
        TextView optionsButton = (TextView) findViewById(R.id.options);
        ListView articlesListView = (ListView) findViewById(R.id.articles);
        headlineMarquee.setSelected(true);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/century-gothic.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
        new GetArticles().execute();

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
            String ticker = "|| ";

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
