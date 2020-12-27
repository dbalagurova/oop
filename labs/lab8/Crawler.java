import java.net.*;

public class Crawler {
	
    private URLPool pool;

	private int numThreads = 4;

	private Crawler(String root, int max, int num) throws MalformedURLException
	{
		numThreads = num;
		pool = new URLPool(max);
		URL rootURL = new URL(root);
		pool.add(new URLDepthPair(rootURL, 0));
    }

    public void crawl() 
	{
		CrawlerTask firstCrawler = new CrawlerTask(pool);
		firstCrawler.processURL(pool.get());

		for (int i = 0; i < numThreads; i++)
		{
			CrawlerTask crawler = new CrawlerTask(pool);
			Thread thread = new Thread(crawler);
			thread.start();
		}
		while (pool.getpendingURLsCount() != 0)
		{
			try
			{
				Thread.sleep(500);
			}
			catch (InterruptedException e)
			{
			System.out.println("Ignoring unexpected InterruptedException - " + e.getMessage());
			}
		}
		pool.printURLs();
    }

    public static void main(String[] args) 
	{
		if (args.length != 3)
		{
			System.err.println("Usage: java Crawler <URL> <depth> <threads>");
			System.exit(1);
		}

		try 
		{ 
			Crawler crawler = new Crawler(args[0], Integer.parseInt(args[1]), Integer.parseInt(args[2]));
			crawler.crawl();
		}
		catch (MalformedURLException e) 
		{
			System.err.println("Error: The URL " + args[0] + " is not valid");
			System.exit(1);
		}
		System.exit(0);
    }
}