package com.example.noosehanger;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {



    TextView tvOrdDerskalGættes;
    String ordDerSkalGættes;
    String ordVistString;
    char[] ordVistCharArray;
    ArrayList<String> ordListe;
    EditText etInput;
    TextView tvForsøgteBogstaver;
    String forsøgteBogstaver;
    final String FORSØGTE_BOGSTAVER = "Forsøgte bogstver: ";
    TextView tvLivTilbage;
    String livTilbage;

    void visBogstavIOrd(char c) {
        int index = ordDerSkalGættes.indexOf(c);

        while(index >= 0) {
            ordVistCharArray[index] = ordDerSkalGættes.charAt(index);
            index = ordDerSkalGættes.indexOf(c, index + 1);
        }

        ordVistString = String.valueOf(ordVistCharArray);
    }

    void visOrdSkærm() {
        String s = "";
        for(char character : ordVistCharArray) {
            s += character + " ";
        }

        tvOrdDerskalGættes.setText(s);
    }

    void startSpil() {

        Collections.shuffle(ordListe);
        ordDerSkalGættes = ordListe.get(0);
        ordListe.remove(0);

        ordVistCharArray = ordDerSkalGættes.toCharArray();

        for(int i = 0; i < ordVistCharArray.length; i++) {
            ordVistCharArray[i] = '_';
        }

        visBogstavIOrd(ordVistCharArray[0]);
        visBogstavIOrd(ordVistCharArray[ordVistCharArray.length+1]);
        ordVistString = String.valueOf(ordVistCharArray);

        visOrdSkærm();

        etInput.setText("");

        forsøgteBogstaver = " ";
        tvForsøgteBogstaver.setText(FORSØGTE_BOGSTAVER);

        livTilbage = " X X X X X ";
        tvLivTilbage.setText(livTilbage);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ordListe = new ArrayList<String>();
        tvOrdDerskalGættes = findViewById(R.id.tvOrdatBliveGættet);
        etInput = findViewById(R.id.etInput);
        tvOrdDerskalGættes = findViewById(R.id.tvForsøgteBogstaver);
        tvLivTilbage = findViewById(R.id.tvLivTilbage);


        InputStream mInputStream = null;
        Scanner in = null;
        String o = "";

        try {
            mInputStream = getAssets().open("wordlist.txt");
            in = new Scanner(mInputStream);
            while (in.hasNext()) {
                o = in.next();
                ordListe.add(o);
            }
        } catch (IOException e) {
            Toast.makeText(MainActivity.this, e.getClass().getSimpleName() + ": " + e.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {
            if(in != null) {
                in.close();
            } try {
                if(mInputStream != null) {
                    mInputStream.close();
                }
            } catch (IOException e) {
                Toast.makeText(MainActivity.this, e.getClass().getSimpleName()+": "+ e.getMessage(), Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }

    }


}