package com.cieep.a04_creacindevistasporcdigo.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;

import com.cieep.a04_creacindevistasporcdigo.R;
import com.cieep.a04_creacindevistasporcdigo.adapters.SpinnerAdapter;
import com.cieep.a04_creacindevistasporcdigo.databinding.ActivityAddAlumnoBinding;
import com.cieep.a04_creacindevistasporcdigo.modelos.Alumno;

import java.util.ArrayList;

public class AddAlumnoActivity extends AppCompatActivity {

    private ActivityAddAlumnoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddAlumnoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnCancelarAddAlumno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        binding.btnGuardarAddAlumno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = binding.txtNombreAddAlumno.getText().toString();
                String apellidos = binding.txtApellidosAddAlumno.getText().toString();
                int selected = binding.spCicloAddAlumno.getSelectedItemPosition();
                int radioSelected = binding.rgGruposAddAlumno.getCheckedRadioButtonId();

                if (!nombre.isEmpty() && !apellidos.isEmpty() && selected != 0 && radioSelected != -1) {
                    String ciclo = (String) binding.spCicloAddAlumno.getSelectedItem();
                    RadioButton rb = findViewById(binding.rgGruposAddAlumno.getCheckedRadioButtonId());
                    char grupo = rb.getText().toString().charAt(0);
                    Alumno alumno = new Alumno(nombre, apellidos, ciclo, grupo);
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("ALUMNO", alumno);
                    Intent intent = new Intent();
                    intent.putExtras(bundle);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
    }
}