package com.cieep.a04_creacindevistasporcdigo;

import android.content.Intent;
import android.os.Bundle;

import com.cieep.a04_creacindevistasporcdigo.activities.AddAlumnoActivity;
import com.cieep.a04_creacindevistasporcdigo.activities.EditAlumnoActivity;
import com.cieep.a04_creacindevistasporcdigo.modelos.Alumno;
import com.google.android.material.snackbar.Snackbar;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.view.LayoutInflater;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.cieep.a04_creacindevistasporcdigo.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    private ActivityResultLauncher<Intent> alumnoLauncher;
    private ActivityResultLauncher<Intent> editLauncher;

    private ArrayList<Alumno> alumnos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        alumnos = new ArrayList<>();

        alumnoLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK) {
                            Alumno alumno = result.getData().getExtras().getParcelable("ALUMNO");
                            alumnos.add(alumno);
                            mostarAlumnos();
                        }
                    }
                });
        editLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK){
                            int posicion = result.getData().getExtras().getInt("POSICION");
                            Alumno alumno = result.getData().getExtras().getParcelable("ALUMNO");

                            if (alumno == null){
                                alumnos.remove(posicion);
                            }
                            else {
                                alumnos.set(posicion, alumno);
                            }
                            mostarAlumnos();
                        }
                    }
                }
        );

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alumnoLauncher.launch(new Intent(MainActivity.this,
                        AddAlumnoActivity.class));
            }
        });
    }

    private void mostarAlumnos() {
        // 1. Conjunto de Datos -> ArrayLis
        // 2. Contenedor de los elementos -> View capaz de contener elementos LinearLayout Vertical -> Scroll
        // 3. Plantilla para la visualización de los elementos ->

        binding.contendorMain.contenedorAlumnos.removeAllViews();
        int posicion = 0;
        for (Alumno a: alumnos) {
            View alumnoVista = LayoutInflater.from(this).inflate(R.layout.alumno_layout, null);
            TextView txtNombre = alumnoVista.findViewById(R.id.lblNombreAlumno);
            TextView txtApellidos = alumnoVista.findViewById(R.id.lblApellidosAlumno);
            TextView txtCiclo = alumnoVista.findViewById(R.id.lblCicloAlumno);
            TextView txtNombreGrupo = alumnoVista.findViewById(R.id.lblGrupoAlumno);
            txtNombre.setText(a.getNombre());
            txtApellidos.setText(a.getApellidos());
            txtCiclo.setText(a.getCiclo());
            txtNombreGrupo.setText(""+a.getGrupo());

            int finalPosicion = posicion;
            alumnoVista.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, EditAlumnoActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("ALUMNO", a);
                    bundle.putInt("POSICION", finalPosicion);
                    intent.putExtras(bundle);
                    editLauncher.launch(intent);
                }
            });
            binding.contendorMain.contenedorAlumnos.addView(alumnoVista);
            posicion++;
        }

    }
}