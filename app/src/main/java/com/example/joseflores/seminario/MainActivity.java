package com.example.joseflores.seminario;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment fragmentSelected = null;

            switch (item.getItemId()) {
                case R.id.nav_registro:

                    MainActivity.this.setTitle(getString(R.string.registro));
                    fragmentSelected = new RegistroFragment();

                    break;
                case R.id.nav_presentacion:

                    MainActivity.this.setTitle(getString(R.string.fondo_presentacion));
                    fragmentSelected = new PresentacionFragment();

                    break;
                case R.id.nav_estilo:

                    MainActivity.this.setTitle(getString(R.string.estilo_presentacion));
                    fragmentSelected = new EstiloFragment();

                    break;

                case R.id.nav_resultado:

                    MainActivity.this.setTitle(getString(R.string.resultados_obtenidos));
                    fragmentSelected = new ResultadosFragment();

                    break;
            }

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frame_container, fragmentSelected).commit();

            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, new RegistroFragment()).commit();
    }

}
