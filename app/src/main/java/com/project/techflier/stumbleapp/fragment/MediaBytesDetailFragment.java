package com.project.techflier.stumbleapp.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.project.techflier.stumbleapp.R;
import com.project.techflier.stumbleapp.model.VideoDataModel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.parceler.Parcels;

/**
 * Created by anuja on 1/9/2018.
 */

public class MediaBytesDetailFragment extends Fragment{

    public static final String ARG_MEDIABYTES = "ARG_MEDIABYTES";

    private VideoDataModel data;
    WebView webView;
    View v;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("-------------------------------- MediaBytesDetailFragment onCreate() -----------------------------------------------");
        data = Parcels.unwrap(getArguments().getParcelable(ARG_MEDIABYTES));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_detail_mediabyte, container, false);
        //webView = (WebView)findViewById(R.id.postwebview);
        webView = (WebView)v.findViewById(R.id.postwebview);

       // this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

       // Log.e("WpPostDetails ", "  title is " + MainActivity.mListPost.get(position).getTitle().getRendered());
        //Log.e("MediaBytesDetailFragment"+data.getTitle().getRendered());
        System.out.println("-------------------------------- MediaBytesDetailFragment onCreateView() -----------------------------------------------"+data.getLink());
        System.out.println("-------------------------------- MediaBytesDetailFragment onCreateView() -----------------------------------------------"+data.getTitle().getRendered());
        System.out.println("-------------------------------- MediaBytesDetailFragment onCreateView() -----------------------------------------------"+data.getContent().getYoutubeURL());

        String html = data.getContent().getYoutubeURL();
        Document doc = Jsoup.parse(html);
        Elements links = doc.select("iframe");
        System.out.println("============= links ================"+links);
        String absoluteUrl = links.attr("abs:src");

        System.out.println("============= scr ================"+absoluteUrl);


        String html1 = "";
        html1 += "<html><body>";
        html1 += "<iframe width=\"560\" height=\"315\" src=\""+absoluteUrl+"\" frameborder=\"0\" allowfullscreen></iframe>";
        html1 += "</body></html>";

        System.out.println("============= html1 ================"+html1);
        webView.getSettings().setJavaScriptEnabled(true);
        //webView.loadUrl(data.getLink());
       // webView.loadUrl(absoluteUrl);
        webView.loadData(html1, "text/html", null);

        // to open webview inside app -- otherwise It will open url in device browser
        webView.setWebViewClient(new WebViewClient());
        return v;
    }
}
