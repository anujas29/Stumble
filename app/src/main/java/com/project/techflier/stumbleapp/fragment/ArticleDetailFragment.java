package com.project.techflier.stumbleapp.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.project.techflier.stumbleapp.R;
import com.project.techflier.stumbleapp.model.ArticleDataModel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.parceler.Parcels;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by anuja on 1/17/2018.
 */

public class ArticleDetailFragment extends Fragment {

    public static final String ARG_ARTICLE = "ARG_ARTICLE";

    private ArticleDataModel data;
    WebView webView;
    View v;
    Unbinder unbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        data = Parcels.unwrap(getArguments().getParcelable(ARG_ARTICLE));


        setHasOptionsMenu(true);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_detail_mediabyte, container, false);

        unbinder = ButterKnife.bind(this, v);
        webView = (WebView)v.findViewById(R.id.postwebview);

        String html = data.getArticle_content().getYoutubeURL();
//        Document doc = Jsoup.parse(html);
//        Elements links = doc.select("iframe");
        System.out.println("============= html ================"+data.getArticle_link());

        String html1 = "";
        html1 += "<html><body>";
        html1 += "<iframe width=\"560\" height=\"315\" src=\""+html+"\" frameborder=\"0\" allowfullscreen></iframe>";
        html1 += "</body></html>";

        System.out.println("============= html1 ================"+html1);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(data.getArticle_link());

       // webView.loadData(data.getArticle_content().getYoutubeURL().toString(),"text/html","UTF-8");

        // to open webview inside app -- otherwise It will open url in device browser
        webView.setWebViewClient(new WebViewClient());
        return v;
    }
}





