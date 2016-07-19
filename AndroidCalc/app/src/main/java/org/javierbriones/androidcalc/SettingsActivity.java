package org.javierbriones.androidcalc;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import org.jcloarca.androidcalc.R;

import butterknife.ButterKnife;

public class SettingsActivity extends AppCompatActivity {

    /*@BindView(R.id.infoText)
    TextView infoText;*/

    private TextView infoText;
    private Button btnNightMode, btnDayMode, btnDeveloper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);

        btnNightMode = (Button) findViewById(R.id.btnNightMode);
        btnDayMode = (Button) findViewById(R.id.btnDayMode);
        btnDeveloper = (Button) findViewById(R.id.btnDeveloper);
        infoText = (TextView) findViewById(R.id.infoText);

        infoText.setText(Html.fromHtml(getString(R.string.settings_paragraph_help)));

        btnNightMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                Intent i = new Intent(SettingsActivity.this, MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });

        btnDayMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                Intent in = new Intent(SettingsActivity.this, MainActivity.class);
                in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(in);
            }
        });

        btnDeveloper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                about();
            }
        });
    }

    private void about() {
        AndroidCalcApp app = (AndroidCalcApp) getApplication();
        String strUrl = app.getUrlAbout();

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(strUrl));
        startActivity(intent);
    }
}
