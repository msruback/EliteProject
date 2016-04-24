package com.example.matthew.eliteproject;

/**
 * Created by mattr on 4/22/2016.
 */
public class Article {
    private String headline,date,body;

    public Article(String setHeadline,String setDate,String setBody){
        headline=setHeadline;
        date=setDate;
        body=setBody;
    }

    public String getHeadline(){
        return headline;
    }
    public String getDate(){
        return date;
    }
    public String getBody(){
        return body;
    }
}
