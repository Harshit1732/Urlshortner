package com.harshit.shorturl;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class XUrlImpl implements XUrl{


    private HashMap<String, String>lmap= new HashMap<>();
    private HashMap<String,String>smap= new HashMap<>();
    private HashMap<String, Integer>count= new HashMap<>();
    private final String shorturl = "http://short.url/";
    private final String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"+ "0123456789"+"abcdefghijklmnopqrstuvxyz";

    private String generateRandomString()
    {
        StringBuilder s = new StringBuilder(9);
        for ( int i = 0; i < 9; i++) {
            // generating a random number
            int index
            = (int)(AlphaNumericString.length()
            * Math.random());
            // add Character one by one in end of s
            s.append(AlphaNumericString
            .charAt(index));
            }
            return s.toString();
    }

    private String URLSplitter(String Url) throws MalformedURLException{
        URL url = new URL(Url);
        String res= url.getProtocol()+":"+"//"+url.getHost()+"/";
        return res;
        
    }

   public String registerNewUrl(String longUrl)
    {
        String res= shorturl+generateRandomString();
         lmap.put(longUrl, res);
         smap.put(res,longUrl);
         return res;
    }


    public String registerNewUrl(String longUrl, String shortUrl) 
    {
      try{
        // System.out.println(URLSplitter(shortUrl));
        if(!this.shorturl.equals(URLSplitter(shortUrl)))
        {
            return null;
        }
      }catch(Exception e)
      {
       }
        if(lmap.containsKey(longUrl))
        {
            return null;
        }
        lmap.put(longUrl, shortUrl);
        smap.put(shortUrl,longUrl);
        return shortUrl;
    }

   public String getUrl(String shortUrl)
   {
      if(smap.containsKey(shortUrl))
      {
        String longurl= smap.get(shortUrl);
        if(count.containsKey(longurl))
        {
            int c= count.get(longurl);
            count.put(longurl,c+1);
        }else{
            count.put(longurl,1);
        }
        return smap.get(shortUrl);
      }
      return null;
   }

    public Integer getHitCount(String longUrl)
    {
        if(count.containsKey(longUrl))
        {
            return count.get(longUrl);
        }
        return 0;

    }

    public String delete(String longUrl){
        String surl= lmap.get(longUrl);
        lmap.remove(longUrl);
        smap.remove(surl);
        return longUrl; 
    }


}