package com.jimfo.bakingapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jimfo.bakingapp.R;
import com.jimfo.bakingapp.model.Step;

public class StepActivity extends AppCompatActivity {

    private Step mStep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);

        Intent i = getIntent();
        Bundle extras = i.getExtras();

        if (extras != null && extras.containsKey(this.getResources().getString(R.string.stepKey))) {
            mStep = extras.getParcelable(this.getResources().getString(R.string.stepKey));
        }
    }
}
