import java.util.*;

public class URLPool {

    private int maxDepth;

    private LinkedList<URLDepthPair> pendingURLs;

    private LinkedList<URLDepthPair> processedURLs;
    
    private HashSet<String> seenURLs; 

    public URLPool(int max) 
	{
		pendingURLs = new LinkedList<URLDepthPair>();
		processedURLs = new LinkedList<URLDepthPair>();
		seenURLs = new HashSet<String>();
	
		maxDepth = max;
    }

    public synchronized int getpendingURLsCount()
	{
		return pendingURLs.size();
    }

    public synchronized void add(URLDepthPair nextPair) 
	{
		String newURL = nextPair.getURL().toString();

		String trimURL = (newURL.endsWith("/")) ? newURL.substring(0, newURL.length() -1) : newURL;
		if (seenURLs.contains(trimURL))
			return;
		seenURLs.add(trimURL);
	
		if (nextPair.getDepth() < maxDepth) 
			pendingURLs.add(nextPair);
		processedURLs.add(nextPair);
    }

    public synchronized URLDepthPair get() 
	{
		if (pendingURLs.size() == 0)
			return null;
		else
			return pendingURLs.removeFirst();
    }

    public synchronized void printURLs() 
	{
		System.out.println("\nUnique URLs Found: " + processedURLs.size());
		while (!processedURLs.isEmpty()) 
			System.out.println(processedURLs.removeFirst());
		
    }
}