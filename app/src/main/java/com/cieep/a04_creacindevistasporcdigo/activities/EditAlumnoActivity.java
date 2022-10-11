package com.cieep.a04_creacindevistasporcdigo.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import com.cieep.a04_creacindevistasporcdigo.R;
import com.cieep.a04_creacindevistasporcdigo.databinding.ActivityEditAlumnoBinding;
import com.cieep.a04_creacindevistasporcdigo.modelos.Alumno;

public class EditAlumnoActivity extends AppCompatActivity {

    private ActivityEditAlumnoBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityEditAlumnoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        muestraAlumnoActual();

        binding.btnCancelarEditAlumno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        binding.btnEliminarEditAlumno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putInt("POSICION", getIntent().getExtras().getInt("POSICION"));
                intent.putExtras(bundle);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        binding.btnActualizarEditAlumno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = binding.txtNombreEditAlumno.getText().toString();
                String apellidos = binding.txtApellidosEditAlumno.getText().toString();
                int selected = binding.spCiclosEditAlumno.getSelectedItemPosition();
                int radioSelected = binding.rgGrupoEditAlumno.getCheckedRadioButtonId();

                if (!nombre.isEmpty() && !apellidos.isEmpty() && selected != 0 && radioSelected != -1) {
                    String ciclo = (String) binding.spCiclosEditAlumno.getSelectedItem();
                    RadioButton rb = findViewById(binding.rgGrupoEditAlumno.getCheckedRadioButtonId());
                    char grupo = rb.getText().toString().charAt(0);
                    Alumno alumno = new Alumno(nombre, apellidos, ciclo, grupo);
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("ALUMNO", alumno);
                    bundle.putInt("POSICION", getIntent().getExtras().getInt("POSICION"));
                    Intent intent = new Intent();
                    intent.putExtras(bundle);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });

    }

    private void muestraAlumnoActual() {

        Alumno alumno = getIntent().getExtras().getParcelable("ALUMNO");
        binding.txtNombreEditAlumno.setText(alumno.getNombre());
        binding.txtApellidosEditAlumno.setText(alumno.getApellidos());

        switch (alumno.getCiclo()){
            case "SMR":
                binding.spCiclosEditAlumno.setSelection(1);
                break;
            case "DAM":
                binding.spCiclosEditAlumno.setSelection(2);
                break;
            case "DAW":
                binding.spCiclosEditAlumno.setSelection(3);
                break;
            case "3D":
                binding.spCiclosEditAlumno.setSelection(4);
                break;
        }

        switch (alumno.getGrupo()){
            case 'A':
                binding.rbGAEditAlumno.setChecked(true);
                break;
            case 'B':
                binding.rbGBEditAlumno.setChecked(true);
                break;
            case 'C':
                binding.rbGCEditAlumno.setChecked(true);
                break;
        }

    }
}