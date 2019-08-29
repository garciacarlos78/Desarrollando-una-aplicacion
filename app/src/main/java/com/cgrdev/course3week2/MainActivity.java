package com.cgrdev.course3week2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    // Elementos de la vista
    EditText etNombre;
    DatePicker dpFechaNacimiento;
    EditText etTelefono;
    EditText etEmail;
    EditText etDescription;
    Button btSiguiente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtenemos los elementos de la vista
        etNombre = (EditText) findViewById(R.id.etNombre);
        dpFechaNacimiento = (DatePicker) findViewById(R.id.dpFechaNacimiento);
        etTelefono = (EditText) findViewById(R.id.etTelefono);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etDescription = (EditText) findViewById(R.id.etDescripcion);
        btSiguiente = (Button) findViewById(R.id.btSiguiente);

        // Comprobamos si el Intent tiene extras (se utiliza sólo en Opción 2, pero no molesta en Opción 1, ver más abajo)
        Bundle b = getIntent().getExtras();

        // Si el Bundle no está vacío, actualizamos los EditText con la información que contenga
        if (b != null) {
            try {
                actualizaInfo(b);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        // Asignamos comportamiento al botón

        // Opción 1: iniciamos la Activity ConfirmarDatos dejando que esta activity quede en el stack (onPause)
        // Con esta opción, para volver y encontrar los datos el botón de Editar Datos será como el botón de volver atrás
//        btSiguiente.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(obtenerIntent());
//                //onPause();
//            }
//        });

        // Opción 2: iniciamos la Activity ConfirmarDatos y destruímos esta Activity
        // Con esta opción, liberamos memoria, a cambio de tener que enviar los datos de vuelta al presiona el botón Editar Datos
        btSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(obtenerIntent());
                finish();
            }
        });
    }

    // Obtenemos los datos del Bundle y los introducimos en los Views correspondientes
    private void actualizaInfo(Bundle b) throws ParseException {

        // Obtenemos los datos del Bundle
        String nombre = b.getString("nombre");
        String fecha = b.getString("fecha");
        String telefono = b.getString("telefono");
        String email = b.getString("email");
        String descripcion = b.getString("descripcion");

        // Los introducimos en los EditText
        etNombre.setText(nombre);
        etTelefono.setText(telefono);
        etEmail.setText(email);
        etDescription.setText(descripcion);

        // Caso especial: fecha
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        Date date = sdf.parse(fecha);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        dpFechaNacimiento.updateDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));

    }

    private Intent obtenerIntent() {

        // Obtenemos los contenidos de la pantalla
        String nombre = null;
        if (etNombre.getText().length()>0)
            nombre = etNombre.getText().toString();
        else nombre = "No se ha introducido nombre.";

        String fecha = String.valueOf(dpFechaNacimiento.getDayOfMonth()) +
                "/" + String.valueOf(dpFechaNacimiento.getMonth()) +
                "/" + String.valueOf(dpFechaNacimiento.getYear());

        String telefono = null;
        if (etTelefono.getText().length()>0)
            telefono = etTelefono.getText().toString();
        else telefono = "No se ha introducido teléfono.";

        String email = null;
        if (etEmail.getText().length()>0)
            email = etEmail.getText().toString();
        else email = "No se ha introducido dirección de correo.";

        String descripcion = null;
        if (etDescription.getText().length()>0)
            descripcion = etDescription.getText().toString();
        else descripcion = "No se ha introducido descripción.";

        // Creamos el Intent
        Intent intent = new Intent(this,ConfirmarDatos.class);


        // Introducimos los extras al Intent
        intent.putExtra("nombre", nombre);
        intent.putExtra("fecha", fecha);
        intent.putExtra("telefono", telefono);
        intent.putExtra("email", email);
        intent.putExtra("descripcion", descripcion);

        // Devolvemos el Intent con la información
        return intent;
    }

}
