package com.example.denis.lab4ejercicio1;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText etNum;
    private TextView numAcertadas, txtnumIntentos, txtResult;
    private int numJuego, numIntentos = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Relacionamos las variables con los valores de los objetos
        etNum = (EditText) findViewById(R.id.etNum);
        numAcertadas = (TextView) findViewById(R.id.numAcertadas);
        txtnumIntentos = (TextView) findViewById(R.id.numIntentos);
        txtResult = (TextView) findViewById(R.id.txtResult);
        //Creamos un numero de juego aleatorio*/
        numJuego = 1 + (int) (Math.random()*10);
    }
    //Accion del boton para jugar.
    public void juego(View v){
        //obtenemos el numero del editText
        int numJugador = Integer.parseInt(etNum.getText().toString());
        //Hacemos la comprabación de si el numero es igual o no.
        if (numJugador==numJuego){
            //Cogemos el numero de acertadas y lo aumentamos en uno
            SharedPreferences  datos = getSharedPreferences("Datos", Context.MODE_PRIVATE);
            int AcertadasAct = Integer.parseInt(String.valueOf(datos.getInt("acertadas", 0)));
            AcertadasAct++;
            //Actualizamos el numero de Acertadas.
            numAcertadas.setText(String.valueOf(AcertadasAct));
            //Mostramos en el txtResult el mensaje de se ha acertado el numero.
            txtResult.setText("Ha acertado el número. Puede seguir jugando.");
            //Se pone a 0 el editText.
            etNum.setText("");
            //Editamos la preferencia para actualizarla.
            SharedPreferences.Editor editor = datos.edit();
            //Añadimos las partidas acertadas.
            editor.putInt("acertadas",AcertadasAct);
            //Añadimos el numero de intentos.
            editor.putInt("intentos", numIntentos);
            //Guardamos las actualizaciones
            editor.commit();
            //Generamos otro numero aleatorio.
            numJuego = 1 + (int) (Math.random()*10);
            //Mostramos el numero de intentos que se han realizado
            txtnumIntentos.setText(String.valueOf(numIntentos));
        }else if (numJuego > numJugador){
            //Se muestra un mensaje de que el numero es menor al aleatorio.
            txtResult.setText("El número a adivinar es mayor que el introducido.");
            //Actualizamos el numero de intentos.
            txtnumIntentos.setText(String.valueOf(numIntentos));
            //Aumentamos en uno el numero de intentos.
            numIntentos++;
            //Se pone a 0 el editText.
            etNum.setText("");
        }else if (numJuego < numJugador){
            //Se muestra un mensaje de que el numero es menor al aleatorio.
            txtResult.setText("El número a adivinar es menor que el introducido.");
            //Actualizamos el numero de intentos.
            txtnumIntentos.setText(String.valueOf(numIntentos));
            //Aumentamos en uno el numero de intentos.
            numIntentos++;
            //Se pone a 0 el editText.
            etNum.setText("");
        }
    }
}
