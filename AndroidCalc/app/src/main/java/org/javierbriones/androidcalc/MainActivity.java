package org.javierbriones.androidcalc;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.jcloarca.androidcalc.R;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    int modeType;

    @BindView(R.id.btnClearMemoria)
    Button btnClearMemoria;
    @BindView(R.id.btnAMemoria)
    Button btnAMemoria;
    @BindView(R.id.btnQuitDeMemoria)
    Button btnQuitDeMemoria;
    @BindView(R.id.btnLlamarMemoria)
    Button btnLlamarMemoria;
    @BindView(R.id.btnClear)
    Button btnClear;
    @BindView(R.id.btnCambioSigno)
    Button btnCambioSigno;
    @BindView(R.id.btnDivi)
    Button btnDivi;
    @BindView(R.id.btnMulti)
    Button btnMulti;
    @BindView(R.id.row3)
    LinearLayout row3;
    @BindView(R.id.btn7)
    Button btn7;
    @BindView(R.id.btn8)
    Button button8;
    @BindView(R.id.btn9)
    Button btn9;
    @BindView(R.id.btnResta)
    Button btnResta;
    @BindView(R.id.btn4)
    Button btn4;
    @BindView(R.id.btn5)
    Button btn5;
    @BindView(R.id.btn6)
    Button btn6;
    @BindView(R.id.btnSum)
    Button btnSum;
    @BindView(R.id.btn1)
    Button btn1;
    @BindView(R.id.btn2)
    Button btn2;
    @BindView(R.id.btn3)
    Button btn3;
    @BindView(R.id.btn0)
    Button btn0;
    @BindView(R.id.btnDecimal)
    Button btnDecimal;
    @BindView(R.id.btnIgual)
    Button btnIgual;

    private TextView mCalculatorDisplay;
    private Boolean userIsInTheMiddleOfTypingANumber = false;
    private ProcesosCalculadora procesosCalcu;
    private static final String DIGITS = "0123456789.";

    DecimalFormat df = new DecimalFormat("@###########");

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        modeType = AppCompatDelegate.getDefaultNightMode();
        //NightOwl.owlBeforeCreate(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        modeType = AppCompatDelegate.getDefaultNightMode();
        ButterKnife.bind(this);

        //NightOwl.owlAfterCreate(this);

        procesosCalcu = new ProcesosCalculadora();
        mCalculatorDisplay = (TextView) findViewById(R.id.textView1);

        df.setMinimumFractionDigits(0);
        df.setMinimumIntegerDigits(1);
        df.setMaximumIntegerDigits(8);

        findViewById(R.id.btn0).setOnClickListener(this);
        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
        findViewById(R.id.btn3).setOnClickListener(this);
        findViewById(R.id.btn4).setOnClickListener(this);
        findViewById(R.id.btn5).setOnClickListener(this);
        findViewById(R.id.btn6).setOnClickListener(this);
        findViewById(R.id.btn7).setOnClickListener(this);
        findViewById(R.id.btn8).setOnClickListener(this);
        findViewById(R.id.btn9).setOnClickListener(this);

        findViewById(R.id.btnSum).setOnClickListener(this);
        findViewById(R.id.btnResta).setOnClickListener(this);
        findViewById(R.id.btnMulti).setOnClickListener(this);
        findViewById(R.id.btnDivi).setOnClickListener(this);
        findViewById(R.id.btnCambioSigno).setOnClickListener(this);
        findViewById(R.id.btnDecimal).setOnClickListener(this);
        findViewById(R.id.btnIgual).setOnClickListener(this);
        findViewById(R.id.btnClear).setOnClickListener(this);
        findViewById(R.id.btnClearMemoria).setOnClickListener(this);
        findViewById(R.id.btnAMemoria).setOnClickListener(this);
        findViewById(R.id.btnQuitDeMemoria).setOnClickListener(this);
        findViewById(R.id.btnLlamarMemoria).setOnClickListener(this);

        if (findViewById(R.id.btnRaiz) != null) {
            findViewById(R.id.btnRaiz).setOnClickListener(this);
        }
        if (findViewById(R.id.btnElevar) != null) {
            findViewById(R.id.btnElevar).setOnClickListener(this);
        }
        if (findViewById(R.id.btnInvertir) != null) {
            findViewById(R.id.btnInvertir).setOnClickListener(this);
        }
        if (findViewById(R.id.btnSeno) != null) {
            findViewById(R.id.btnSeno).setOnClickListener(this);
        }
        if (findViewById(R.id.btnCoseno) != null) {
            findViewById(R.id.btnCoseno).setOnClickListener(this);
        }
        if (findViewById(R.id.btnTangente) != null) {
            findViewById(R.id.btnTangente).setOnClickListener(this);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_help){
            startActivity(new Intent(MainActivity.this, SettingsActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    private void about() {
        AndroidCalcApp app = (AndroidCalcApp) getApplication();
        String strUrl = app.getUrlAbout();

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(strUrl));
        startActivity(intent);
    }


    /*@Override
    protected void onResume() {
        super.onResume();

        NightOwl.owlResume(this);
    }*/

    @Override
    public void onClick(View v) {

        String buttonPressed = ((Button) v).getText().toString();

        if (DIGITS.contains(buttonPressed)) {

            if (userIsInTheMiddleOfTypingANumber) {

                if (buttonPressed.equals(".") && mCalculatorDisplay.getText().toString().contains(".")) {
                    // Previene multiples .s
                } else {
                    mCalculatorDisplay.append(buttonPressed);
                }

            } else {

                if (buttonPressed.equals(".")) {
                    mCalculatorDisplay.setText(0 + buttonPressed);
                } else {
                    mCalculatorDisplay.setText(buttonPressed);
                }

                userIsInTheMiddleOfTypingANumber = true;
            }

        } else {
            if (userIsInTheMiddleOfTypingANumber) {

                procesosCalcu.setOperand(Double.parseDouble(mCalculatorDisplay.getText().toString()));
                userIsInTheMiddleOfTypingANumber = false;
            }

            procesosCalcu.performOperation(buttonPressed);
            mCalculatorDisplay.setText(df.format(procesosCalcu.getResult()));

        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putDouble("OPERAND", procesosCalcu.getResult());
        outState.putDouble("MEMORY", procesosCalcu.getMemory());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        procesosCalcu.setOperand(savedInstanceState.getDouble("OPERAND"));
        procesosCalcu.setMemory(savedInstanceState.getDouble("MEMORY"));
        mCalculatorDisplay.setText(df.format(procesosCalcu.getResult()));
    }
}
