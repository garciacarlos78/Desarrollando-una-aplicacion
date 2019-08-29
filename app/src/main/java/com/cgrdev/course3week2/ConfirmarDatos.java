package com.cgrdev.course3week2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ConfirmarDatos extends AppCompatActivity {

    // Elementos de la vista
    TextView tvName;
    TextView tvBirthdate;
    TextView tvPhone;
    TextView tvEmail;
    TextView tvDescriptionContent;
    Button btEditData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_datos);

        // Obtenemos el bundle con los extras
        Bundle b = getIntent().getExtras();

        // Asignamos elementos de la vista
        tvName = (TextView) findViewById(R.id.tvName);
        tvBirthdate = (TextView) findViewById(R.id.tvBirthdate);
        tvPhone = (TextView) findViewById(R.id.tvPhone);
        tvEmail = (TextView) findViewById(R.id.tvEmail);
        tvDescriptionContent = (TextView) findViewById(R.id.tvDescriptionContent);
        btEditData = (Button) findViewById(R.id.btEditData);

        // Ponemos los extras en sus correspondientes TextView
        tvName.setText(b.getString("nombre"));
        tvBirthdate.setText(b.getString("fecha"));
        tvPhone.setText(b.getString("telefono"));
        tvEmail.setText(b.getString("email"));
        tvDescriptionContent.setText(b.getString("descripcion"));

        // Asignamos el listener al botón

        // Opción 1: la MainActivity está apilada (onPause).
        // En este caso basta con cambiar de Activity
//        btEditData.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(ConfirmarDatos.this,MainActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
//                startActivity(intent);
//            }
//        });

        // Opción 2: la MainActivity está destruida, se debe crear de cero y enviar datos. Se destruye la actual Activity.
        btEditData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(creaIntent());
                finish();
            }
        });
    }

    // Se devuelve el Intent con los extras listo para llamar a MainActivity
    private Intent creaIntent() {

        Intent intent = new Intent(this,MainActivity.class);

        // Obtenemos los datos de la pantalla para introducir como extras al intent
        String nombre = tvName.getText().toString();
        if (nombre.equals("No se ha introducido nombre."))
            nombre = null;

        String fecha = tvBirthdate.getText().toString();

        String telefono = tvPhone.getText().toString();
        if (telefono.equals("No se ha introducido teléfono."))
            telefono = null;

        String email = tvEmail.getText().toString();
        if (email.equals("No se ha introducido dirección de correo."))
            email = null;

        String descripcion = tvDescriptionContent.getText().toString();
        if (descripcion.equals("No se ha introducido descripción."))
            descripcion = null;

        // Los introducimos al intent
        intent.putExtra("nombre", nombre);
        intent.putExtra("fecha", fecha);
        intent.putExtra("telefono", telefono);
        intent.putExtra("email", email);
        intent.putExtra("descripcion", descripcion);

        return intent;
    }
}
