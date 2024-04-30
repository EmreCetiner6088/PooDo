package com.example.studyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import android.content.DialogInterface;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TodoActivity extends AppCompatActivity {
    ImageButton add;
    AlertDialog dialog;
    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);

        add = findViewById(R.id.add);
        layout = findViewById(R.id.container);

        buildDialog();

        Intent home_page = new Intent(this, HomePageActivity.class);
        Intent pomo_page = new Intent(this, PomodoroActivity.class);

        ImageButton btn_home = findViewById(R.id.btn_home);
        ImageButton btn_pomo = findViewById(R.id.ViewButton);


        btn_home.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // ImageButton üzerine gelindiğinde
                        v.animate().scaleX(1.2f).scaleY(1.2f).setDuration(200).start();
                        break;
                    case MotionEvent.ACTION_UP:
                        // ImageButton üzerinden ayrıldığında
                        v.animate().scaleX(1f).scaleY(1f).setDuration(200).start();
                        break;
                }
                return false;
            }
        });

        btn_pomo.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // ImageButton üzerine gelindiğinde
                        v.animate().scaleX(1.2f).scaleY(1.2f).setDuration(200).start();
                        break;
                    case MotionEvent.ACTION_UP:
                        // ImageButton üzerinden ayrıldığında
                        v.animate().scaleX(1f).scaleY(1f).setDuration(200).start();
                        break;
                }
                return false;
            }
        });

        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(home_page);
            }
        });

        btn_pomo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(pomo_page);
            }
        });
    }

    public void buildDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.dialog, null);

        final EditText name = view.findViewById(R.id.nameEdit);

        builder.setView(view);
        builder.setTitle("Yapmak istediğinizi giriniz")
                .setPositiveButton("EKLE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String taskName = name.getText().toString().trim();
                        if (!taskName.isEmpty()) { // Kullanıcı bir şey yazdıysa
                            addCard(taskName);
                            name.setText(""); // Yazı ekledikten sonra alanı temizle
                        } else {
                            showErrorDialog();
                        }
                    }
                })
                .setNegativeButton("VAZGEÇ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Vazgeç butonuna tıklanınca hiçbir şey yapma, dialogu kapat
                    }
                });

        dialog = builder.create();

        // Ekle butonuna tıklama animasyonunu ayarla
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.animate().scaleX(0.9f).scaleY(0.9f).setDuration(100).withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        v.animate().scaleX(1f).scaleY(1f).setDuration(100).start();
                        dialog.show();
                    }
                }).start();
            }
        });
    }

    private void showErrorDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Hata")
                .setMessage("Lütfen bir görev giriniz.")
                .setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Hata mesajını kapattığında hiçbir şey yapma
                    }
                });
        AlertDialog errorDialog = builder.create();
        errorDialog.show();
    }

    private void addCard(String name) {
        // Yeni bir kart oluştur
        final View view = getLayoutInflater().inflate(R.layout.card, null);

        // Kart içindeki metin alanını ve silme düğmesini tanımla
        TextView nameView = view.findViewById(R.id.name);
        Button delete = view.findViewById(R.id.delete);

        // Görev metnini kartın metin alanına ekle
        nameView.setText(name);

        // Görev eklenirken "Merhaba" yazısını kaldır
        TextView helloTextView = findViewById(R.id.hello_text);
        helloTextView.setVisibility(View.GONE);

        // Kartın silme düğmesinin tıklanma olayını tanımla
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout.removeView(view);
                checkEmptyState(); // Görev kartı silindikten sonra kontrolü yap
            }
        });

        // Kartı ana düzene ekle
        layout.addView(view);

        checkEmptyState(); // Görev kartı eklendikten sonra kontrolü yap
    }

    // Dizinde hiçbir kart kalmadığında kontrolü yapacak fonksiyon
    private void checkEmptyState() {
        if (layout.getChildCount() == 0) {
            // Eğer hiçbir kart yoksa "Merhaba" yazısını göster
            TextView helloTextView = findViewById(R.id.hello_text);
            helloTextView.setVisibility(View.VISIBLE);
        }
    }
}
