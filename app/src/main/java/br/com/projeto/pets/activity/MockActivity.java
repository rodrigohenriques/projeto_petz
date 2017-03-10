package br.com.projeto.pets.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import br.com.projeto.pets.R;

public class MockActivity extends AppCompatActivity {

    private static String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mock);
    }

}
