package com.example.jose.proyecto;

import android.content.Context;



import android.view.LayoutInflater;


import android.view.View;


import android.widget.Button;
import android.widget.ImageButton;


import android.widget.ImageView;
import android.widget.LinearLayout;

import android.widget.TextView;

public class ItemList extends LinearLayout{

//definimos una variable para la etiqueta, la imagen y el botón

private TextView lbl;

private ImageView img;
private Button btn;



//Defínimos un método que nos permita setear desde fuera el texto de la etiqueta

public void setLabelText(String t){
        lbl.setText(t);     //el textview
        }


//Defínimos un método que nos permita setear desde fuera de la clase la imagen.

//Como parámetro pasamos un entero ya queA ndroid hará referencia a los recursos con un entero para cada uno.       IMPORTANTE!!
public void setImage(int resource){
        img.setImageResource(resource);

        }

public ItemList(Context context) {
        super(context);

        //Leemos la plantilla que hicimos antes para pintarla en pantalla. R.layout.item_list es una referencia al archivo item_list.xml
        // que creamos antes, para hacer referencia muchos de los archivos de recursos que utilicemos lo haremos de esta forma, no
        // tienen que ser siempre desde layout, pueden estar en drawable, string...
        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater li = (LayoutInflater)getContext().getSystemService(infService);
        li.inflate(R.layout.item_list, this, true);

        //inicializamos los 3 controles que tenemos haciendo referencia a ellos y convirtiendolos al tipo que corresponda.
        // Podemos tener en otros layouts elementos que se llamen igual pero Android sabe que estamos manejando este
        // layout y que es aquí donde tiene que buscar primero elementos que tengas estos nombres.
        lbl = (TextView) findViewById(R.id.lbl);
        img = (ImageView) findViewById(R.id.img);
        btn = (Button) findViewById(R.id.btn);

        //definimos una acción para el botón cuando lo pulsemos
        btn.setOnClickListener(new OnClickListener() {
                                    public void onClick(View v) {
                                    //En este caso solo cambiamos el texto de la etiqueta
                                        lbl.setText("Hola jose");
                                    }
        });
        }


        }
