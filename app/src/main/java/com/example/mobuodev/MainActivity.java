package com.example.mobuodev;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    LinearLayout linearLayoutGiriş,linearLayoutOyun;
    RadioButton radioButtonKolay,radioButtonZor;
    Button buttonOyungir,buttonTahminEt,buttonTekrarOyna;
    TextView textViewKalanHak,textViewDurum,textViewhile;
    ImageView imageViewTahmindurum;
    EditText editTextNumberTahmin;
 Random rnd=new Random();
 int tahminSayısı;
  int hakSayısı;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //region Tanımlamalar
        linearLayoutGiriş=findViewById(R.id.linearLayoutGiriş);
        linearLayoutOyun=findViewById(R.id.linearLayoutOyun);
        radioButtonKolay=findViewById(R.id.radioButtonKolay);
        radioButtonZor=findViewById(R.id.radioButtonZor);
        buttonOyungir=findViewById(R.id.buttonOyungir);
        buttonTahminEt=findViewById(R.id.buttonTahminEt);
        buttonTekrarOyna=findViewById(R.id.buttonTekrarOyna);
        textViewKalanHak=findViewById(R.id.textViewKalanHak);
        textViewDurum=findViewById(R.id.textViewDurum);
        imageViewTahmindurum=findViewById(R.id.imageViewTahmindurum);
        editTextNumberTahmin=findViewById(R.id.editTextNumberTahmin);
        textViewhile=findViewById(R.id.textViewhile);
        //endregion

        //region Giriş Ekranı
        buttonOyungir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linearLayoutGiriş.setVisibility(View.GONE);
                linearLayoutOyun.setVisibility(View.VISIBLE);
                if (radioButtonKolay.isChecked()){
                    hakSayısı=5;
                    textViewKalanHak.setText("Kalan Hak: "+hakSayısı);
                    tahminSayısı=rnd.nextInt(100);
                }else if (radioButtonZor.isChecked()){
                    hakSayısı=7;
                    textViewKalanHak.setText("Kalan Hak: "+hakSayısı);
                    tahminSayısı=rnd.nextInt(1000 - 100) + 100;
                }
                textViewhile.setText("Hile: "+tahminSayısı);
            }
        });

        //endregion

        //region Oyun Ekranı
        buttonTahminEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tahmin=Integer.parseInt(editTextNumberTahmin.getText().toString());
                if (tahmin==tahminSayısı){
                    textViewDurum.setText("doğru bildiniz doğru sayı: "+tahminSayısı);
                    Toast.makeText(MainActivity.this, "Tebrikler doğru bildiniz", Toast.LENGTH_SHORT).show();
                    imageViewTahmindurum.setImageResource(R.drawable.target);
                    buttonTahminEt.setEnabled(false);
                    buttonTekrarOyna.setVisibility(View.VISIBLE);
                }else if (tahmin<tahminSayısı){
                    textViewDurum.setText("Yukarı");
                    imageViewTahmindurum.setImageResource(R.drawable.increase);
                    hakSayısı--;
                    textViewKalanHak.setText("Kalan Hak: "+hakSayısı);
                    if (hakSayısı==0){
                        Toast.makeText(MainActivity.this, "Hakkınız bitti tekrar deneyiniz doğru sayı: "+tahminSayısı, Toast.LENGTH_SHORT).show();

                        buttonTahminEt.setEnabled(false);
                        buttonTekrarOyna.setVisibility(View.VISIBLE);
                    }
                }else if (tahmin>tahminSayısı){
                    textViewDurum.setText("Aşağı");
                    imageViewTahmindurum.setImageResource(R.drawable.decrease);
                    hakSayısı--;
                    textViewKalanHak.setText("Kalan Hak: "+hakSayısı);
                    if (hakSayısı==0){
                        Toast.makeText(MainActivity.this, "Hakkınız bitti tekrar deneyiniz Doğru sayı: "+tahminSayısı, Toast.LENGTH_SHORT).show();
                        buttonTahminEt.setEnabled(false);
                        buttonTekrarOyna.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
        //endregion

        //region Tekrar Oyna
        buttonTekrarOyna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linearLayoutGiriş.setVisibility(View.VISIBLE);
                linearLayoutOyun.setVisibility(View.GONE);
                buttonTahminEt.setEnabled(true);
                buttonTekrarOyna.setVisibility(View.GONE);
                editTextNumberTahmin.setText("");
                textViewDurum.setText("başladı");
                textViewKalanHak.setText("Kalan Hak: "+hakSayısı);
                imageViewTahmindurum.setImageResource(R.drawable.start);
            }
        });

        //endregion
    }
}