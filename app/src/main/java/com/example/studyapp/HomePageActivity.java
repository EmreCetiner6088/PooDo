package com.example.studyapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class HomePageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);


        String[] ozluSozlerArray = {
                "Her gün bir adım daha yakınsın.",
                "Yapabileceğine inanırsan, her şeyi başarabilirsin.",
                "Başarı, istemekten daha fazlasını gerektirir.",
                "Bugünün küçük adımları, yarının büyük başarılarıdır.",
                "Güçlü bir istek, başarının anahtarıdır.",
                "Her zorluk, yeni bir fırsattır.",
                "Geleceğe olan inancın, başarının temelidir.",
                "Pes etmek, başarısızlığın garantisi değildir, ancak denememek kesinlikle başarısızlıktır.",
                "Zorluklar sadece güçlü insanlara sunulan fırsatlardır.",
                "Hayalini kur, gerçekleştir!",
                "Sınırlarını zorla, yeni ufuklara açıl.",
                "Engeller, hedefe giden yolda sadece duraklardır.",
                "Bugün için çalış, yarın için hayal kur.",
                "Pes etme, başarıya giden yol sabır ve azimle döşenir.",
                "Güçlü ol, çünkü daha güçlüsün.",
                "Başlamak için mükemmel bir zaman yoktur, zamanı mükemmel hale getirirsin.",
                "Başarılı olmanın sırrı, başarısız olmaktan korkmamaktır.",
                "Her gün biraz daha iyileş.",
                "Engeller, başarıya giden yolda sadece birer kilometre taşıdır.",
                "Pes etme, çünkü düşme yalnızca başarının bir parçasıdır.",
                "Büyük düşler, büyük başarılar getirir.",
                "Geleceğini inşa etmek için bugünü kullan.",
                "Sıkı çalışmanın altın anahtarını elinde tutuyorsun.",
                "Başarılı olmanın sırrı, asla pes etmemektir.",
                "Her gün biraz daha güçlü, biraz daha bilge, biraz daha iyi ol.",
                "Engelleri aş, zirveye ulaş.",
                "Yükseklerde uçmak istiyorsan, küçük adımlarla başla.",
                "Başlamak için geç değil, şimdi harekete geç.",
                "İstediğin kadar sıkı çalışırsan, istediğin kadar başarırsın.",
                "Güneş her batışında bir gün daha doğar.",
                "Unutma, en büyük zaferler en zorlu savaşlardan gelir."
                };
        TextView ozlusozler = findViewById(R.id.ozlusozler); List<String> ozluSozlerList = new ArrayList<>();
        Collections.addAll(ozluSozlerList, ozluSozlerArray);

        // Liste elemanlarını karıştır
        Collections.shuffle(ozluSozlerList);

        // Rastgele bir cümleyi TextView'e yerleştir
        ozlusozler.setText(ozluSozlerList.get(0));

        ImageButton todo_btn = findViewById(R.id.btn_todo);
        ImageButton pomo_btn = findViewById(R.id.ViewButton);


        todo_btn.setOnTouchListener(new View.OnTouchListener() {
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


        pomo_btn.setOnTouchListener(new View.OnTouchListener() {
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

        TextView text_entrance = findViewById(R.id.namelogin);
        TextView text_date = findViewById(R.id.text_date);
        Intent intent = getIntent();
        String userName = intent.getStringExtra("userName");
        text_entrance.setText("Merhaba " + userName);

        LocalDate localDate = LocalDate.now();

        Intent pomoPage = new Intent(this, PomodoroActivity.class);

        Intent todoPage = new Intent(this, TodoActivity.class);



        // Türkçe olarak gün ismini almak için Locale'ı Türkçe olarak ayarlayın
        String dayName = localDate.getDayOfWeek().getDisplayName(TextStyle.FULL, new Locale("tr"));
        int dayOfMonth = localDate.getDayOfMonth();

        text_date.setText(dayName + " " + dayOfMonth);


        pomo_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(pomoPage);

            }
        });
        todo_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(todoPage);
            }
        });

    }
}
