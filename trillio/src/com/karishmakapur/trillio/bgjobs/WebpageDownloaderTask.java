package com.karishmakapur.trillio.bgjobs;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import com.karishmakapur.trillio.daos.BookmarkDao;
import com.karishmakapur.trillio.entities.WebLink;
import com.karishmakapur.trillio.entities.WebLink.DownloadStatus;
import com.karishmakapur.trillio.util.HttpConnect;
import com.karishmakapur.trillio.util.IOUtil;

public class WebpageDownloaderTask implements Runnable {
	private static BookmarkDao dao = new BookmarkDao();

	private static final long TIME_FRAME = 3000000000L;

	private boolean downloadAll = false;
	ExecutorService downloadExecutor = Executors.newFixedThreadPool(5);

	private static class Downloader<T extends WebLink> implements Callable<T> {
		private T weblink;

		public Downloader(T weblink) {
			this.weblink = weblink;
		}

		public T call() {
			try {
				if (!weblink.getUrl().endsWith(".pdf")) {
					weblink.setDownloadStatus(WebLink.DownloadStatus.FAILED);
					String htmlPage = HttpConnect.download(weblink.getUrl());
					weblink.setHtmlPage(htmlPage);
				} else {
					weblink.setDownloadStatus(WebLink.DownloadStatus.NOT_ELIGIBLE);
				}
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
			return weblink;
		}
	}

	public WebpageDownloaderTask(boolean downloadAll) {
		this.downloadAll = downloadAll;
	}

	@Override
	public void run() {

		while (!Thread.currentThread().isInterrupted()) {
			// Get Weblinks
			List<WebLink> weblinks = getWebLinks();

			// Download Concurrently

			if (weblinks.size() > 0) {
				download(weblinks);
			} else {
				System.out.println("No new Web Links to download!!");
			}

			// Wait
			try {
				TimeUnit.SECONDS.sleep(15);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		downloadExecutor.shutdown();
	}

	private void download(List<WebLink> weblinks) {
		List<Downloader<WebLink>> tasks = getTasks(weblinks);
		List<Future<WebLink>> futures = new ArrayList<>();
						
		try {
			futures = downloadExecutor.invokeAll(tasks, TIME_FRAME, TimeUnit.NANOSECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		for (Future<WebLink> future : futures) {
			try {
				if (!future.isCancelled()) {
					WebLink weblink = future.get();
					String webPage = weblink.getHtmlPage();
					if(webPage != null) {
						IOUtil.write(webPage, weblink.getId());
						weblink.setDownloadStatus(WebLink.DownloadStatus.SUCCESS);
						System.out.println("Download Success: " + weblink.getUrl());

					}
					else {
						System.out.println("Webpage not downloaded: " + weblink.getUrl());

					}
				} else {
					System.out.println("\n\nTask is cancelled --> " + Thread.currentThread());
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		
	}

	private List<Downloader<WebLink>> getTasks(List<WebLink> weblinks) {
		List<Downloader<WebLink>> tasks = new ArrayList<>();
		
		for(WebLink weblink : weblinks) {
			tasks.add(new Downloader<WebLink>(weblink));
		}
		return tasks;
	}

	private List<WebLink> getWebLinks() {
		List<WebLink> weblinks = null;

		if (downloadAll) {
			weblinks = dao.getAllWebLinks();
			downloadAll = false;
		} else {
			weblinks = dao.getWebLinks(DownloadStatus.NOT_ATTEMPTED);
		}
		return weblinks;
	}

}
