package pk.katibapp.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.onesignal.OneSignal;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private static final String ONESIGNAL_APP_ID = "5a6357c8-e116-46c2-970d-ca936968cfa1";
    private static final int HOME_ID = R.id.home;
    // Define constants for other resource IDs

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        initializeOneSignal();
        setupWebView();
        setupBottomNavigation();
    }

    private void initializeOneSignal() {
//        OneSignal.setAppId(ONESIGNAL_APP_ID);
//        OneSignal.initWithContext(this);
//        OneSignal.promptForPushNotifications();
    }

    private void setupWebView() {
        WebView webView = findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://katib.pk");

        WebSettings webSettings = webView.getSettings();
        // Carefully review JavaScript usage for XSS vulnerabilities
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setSupportMultipleWindows(true);
        webSettings.setLoadsImagesAutomatically(true);
        webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        webSettings.setUseWideViewPort(true);
        webSettings.setSaveFormData(true);
    }

    private void setupBottomNavigation() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case HOME_ID:
                    loadUrl("https://katib.pk");
                    return true;
                // ... other cases using defined constants ...
            }
            return false;
        });
    }

    private void loadUrl(String url) {
        WebView webView = findViewById(R.id.webview);
        if (webView != null) {
            webView.loadUrl(url);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        WebView webView = findViewById(R.id.webview);
        if (webView.canGoBack()) {
            webView.goBack();
            return true;
        } else {
            return super.onSupportNavigateUp();
        }
    }
}
