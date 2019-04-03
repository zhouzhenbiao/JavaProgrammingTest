package chapter12;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 失败的类，因为截取 http 这个算法有问题，不能截取到正确的网址，所以接下来级爬不了Web网站了！！！
 */
public class WebCrawler {
    public static void main(String[] args) {
        System.out.print("Enter a URL: ");
        String url = new Scanner(System.in).nextLine();
        WebCrawler.crawler(url);
    }

    public static void crawler(String startingURL) {
        ArrayList<String> listOfPendingURLs = new ArrayList<>();//将要遍历的
        ArrayList<String> listOfTraversedURLs = new ArrayList<>();//已经被遍历的
        listOfPendingURLs.add(startingURL);

        // 保证了一直在递归，但是有递归条件
//        int i = 2;
        while (!listOfPendingURLs.isEmpty() && listOfTraversedURLs.size() <= 100) {//
            //将listOfPendingURLs索引位为0的字符串移出。
            String urlString = listOfPendingURLs.remove(0);
            // 将移出的字符串累加在listOfTraversedURLs中，并且要小于100个，
            // 做一个判断，防止在listOfTraversedURLs中出现重复的字符串网址
            if (!listOfTraversedURLs.contains(urlString)) {
                listOfTraversedURLs.add(urlString);
                System.out.println("Crawler: " + urlString);
                for (String s : WebCrawler.getSubURLs(urlString)) {
                    if (!listOfTraversedURLs.contains(s))
                        listOfPendingURLs.add(s);
                }
            }

        }

    }

    public static ArrayList<String> getSubURLs(String urlString) {
        ArrayList<String> list = new ArrayList<>();
        try {
            URL url = new URL(urlString);
            Scanner input = new Scanner(url.openStream());
            int currentIndex = 0; //当前行的索引下标
            while (input.hasNext()) {
                String nextLine = input.nextLine();
                currentIndex = nextLine.indexOf("http:", currentIndex);
                while (currentIndex > 0) {
                    int endIndex = nextLine.indexOf("\"", currentIndex);
                    //如果endIndex <= 0 代表currentIndex有错误。
                    if (endIndex > 0) {
                        list.add(nextLine.substring(currentIndex, endIndex));
                        currentIndex = nextLine.indexOf("http:", endIndex);
                    } else
                        currentIndex = -1;
                }

            }
        }  catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
