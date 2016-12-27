package com.ivacompany.test.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ivacompany.test.R;
import com.ivacompany.test.dialogs.SimpleDialog;
import com.ivacompany.test.fragments.MainFragment;
import com.ivacompany.test.interfaces.FragmentRequestListener;

public class MainActivity extends AppCompatActivity implements FragmentRequestListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startMainFragment(0);

    }


    @Override
    public void startMainFragment(int id) {
        if (id == 0) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.mainContainer,
                            MainFragment.newInstance(id),
                            MainFragment.TAG)
                    .commit();
            return;
        }
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.mainContainer,
                        MainFragment.newInstance(id),
                        MainFragment.TAG)
                .addToBackStack(MainFragment.TAG)
                .commit();
    }

    @Override
    public void startDialog() {
        SimpleDialog optionsBlock = new SimpleDialog();

        optionsBlock.show(this.getSupportFragmentManager(), "PopUp");
    }
}
