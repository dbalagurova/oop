import java.net.*;
import java.util.*;
import java.io.*;


public class Crawler {

	public static void main(String[] args) {

		if (args.length != 2) {
			System.out.println("usage: java Crawler <URL> <depth>");
			System.exit(1);
		}

		int depth = 0;

		try {
			depth = Integer.parseInt(args[1]);
		}
		catch (NumberFormatException nfe) {
			System.out.println("usage: java Crawler <URL> <depth>");
			System.exit(1);
		}

		LinkedList<URLDepthPair> pendingURLs = new LinkedList<URLDepthPair>();
		LinkedList<URLDepthPair> processedURLs = new LinkedList<URLDepthPair>();

		URLDepthPair currentDepthPair = new URLDepthPair(args[0], 0);

		pendingURLs.add(currentDepthPair);

		ArrayList<String> seenURLs = new ArrayList<String>();
		seenURLs.add(currentDepthPair.getURL());

		while (pendingURLs.size() != 0)
		{
			URLDepthPair depthPair = pendingURLs.pop();

			processedURLs.add(depthPair);

			int myDepth = depthPair.getDepth();
			if (myDepth >= depth)
				continue;

			LinkedList<String> linksList = Crawler.getAllLinks(depthPair);

			for (int i = 0; i < linksList.size(); i++)
			{
				String newURL = linksList.get(i);
				if (!seenURLs.contains(newURL))
				{
					URLDepthPair newDepthPair = new URLDepthPair(newURL, myDepth + 1);
					pendingURLs.add(newDepthPair);
					seenURLs.add(newURL);
				}
			}
		}
		Iterator<URLDepthPair> iter = processedURLs.iterator();
		while (iter.hasNext()) {
			System.out.println(iter.next());
		}
	}


	private static LinkedList<String> getAllLinks(URLDepthPair myDepthPair) {

		LinkedList<String> URLs = new LinkedList<String>();

		BufferedReader in;

		try
		{
			URL url = new URL(myDepthPair.getURL());
			URLConnection con = url.openConnection();
			in = new BufferedReader(new InputStreamReader(con.getInputStream()));

		} catch (IOException ioex)
		{
			//System.err.println("IOException: " + ioex.getMessage());
			return URLs;
		}

		while (true)
		{
			String line;
			try
			{
				line = in.readLine();
			}
			catch (IOException ioex)
			{
				System.err.println("IOException: " + ioex.getMessage());
				return URLs;
			}
			if (line == null)
				break;

			int beginIndex = 0;
			int endIndex = 0;
			int index = 0;

			while (true) {
				String URL_INDICATOR = "a href=\"";
				String END_URL = "\"";
				index = line.indexOf(URL_INDICATOR, index);
				if (index == -1)
					break;

				index += URL_INDICATOR.length();
				beginIndex = index;

				endIndex = line.indexOf(END_URL, index);
				index = endIndex;

				String newLink = line.substring(beginIndex, endIndex);
				URLs.add(newLink);
			}
		}
		return URLs;
	}
}
