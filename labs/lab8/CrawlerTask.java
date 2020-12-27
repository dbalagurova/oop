import java.io.*;
import java.net.*;

import org.jsoup.Jsoup;
import org.jsoup.select.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class CrawlerTask implements Runnable {
	
    private URLPool pool;

    public CrawlerTask(URLPool p) 
	{
		pool = p;
    }

    public void processURL(URLDepthPair url)
	{
		Document doc;
		try {
			doc = Jsoup.connect(url.getURL().toString()).get();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}

		Element body  = doc.body();
		if (body == null)
			return;

		Elements links = body.getElementsByTag("a");
		for(Element link : links)
		{
			String newURL = link.attr("href").toString();

			URL newSite;
			try {
				if (URLDepthPair.isAbsolute(newURL))
					newSite = new URL(newURL);
				else
					newSite = new URL(url.getURL(), newURL);
				pool.add(new URLDepthPair(newSite, url.getDepth() + 1));
			}
			catch (MalformedURLException e)
			{
				System.err.println("Error with URL - " + e.getMessage());
			}
		}
    }
    
    public void run() 
	{
		URLDepthPair nextPair = pool.get();
		while (nextPair != null)
		{
			processURL(nextPair);
			nextPair = pool.get();
		}
    }
}
