package com.example.linda.emailfortheblind;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import android.speech.tts.TextToSpeech;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class EmailRead extends AppCompatActivity {
    public TextToSpeech TTS;
    public String emailToRead;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_read);
        TTS = new TextToSpeech(EmailRead.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    TTS.setLanguage(Locale.UK);
                }
            }
        });


        emailToRead = getIntent().getStringExtra("emailToRead");
//            TTS.speak(emailToRead, TextToSpeech.QUEUE_FLUSH, null, null);
        }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if ((keyCode == android.view.KeyEvent.KEYCODE_VOLUME_UP)) {
            TTS.speak(emailToRead, TextToSpeech.QUEUE_FLUSH, null, null);
            if(!TTS.isSpeaking()) this.finish();
        }
        return true;
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_VOLUME_DOWN)) {
            if(!TTS.isSpeaking()) this.finish();
        }

        return true;
    }
}
