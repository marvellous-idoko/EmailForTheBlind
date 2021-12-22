package com.example.linda.emailfortheblind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
//import com.google.common.base.Ascii;
import android.os.Message;
import android.view.KeyEvent;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.speech.tts.TextToSpeech;


import com.google.android.gms.common.api.ApiException;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleOAuthConstants;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
//import com.google.api.client.http.apache.ApacheHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.fasterxml.jackson.core.*;

import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.GmailScopes;
//import com.google.api.services.gmail.model.Label;
//import com.google.api.services.gmail.model.ListLabelsResponse;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
//import com.google.android.gms.auth.;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.api.services.gmail.model.ListMessagesResponse;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import static com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets.load;
import static java.lang.System.in;


public class Main2Activity extends AppCompatActivity {
    private static JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static  String TOKENS_DIRECTORY_PATH = "/Messages";
    private static  List<String> SCOPES = Collections.singletonList(GmailScopes.GMAIL_READONLY);
    public  Uri path = Uri.parse("file:///android_asset/credentials.json");
    String CREDENTIALS_FILE_PATH = path.toString();
//    private static  String  ="./credentials.json";
    GoogleSignInClient mGoogleSignInClient;
    Button moveTo;
    TextView userName;
    TextView userEmail;
    TextView userIdd;
    Credential goal;
    String uuserName;
    String uuserEmail;
    public static   String uuserId;
    ListView EmailList;
    public  TextToSpeech TTS;
    ArrayList ground, groundTwo;
//    ImageView userPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
//        sign_out = findViewById(R.)
        userName = findViewById(R.id.userName);
        userEmail = findViewById(R.id.email);
//        userIdd = findViewById(R.id.userId);
//        userPhoto = findViewById(R.id.userPhoto);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(Main2Activity.this);
        if (account != null) {
            uuserName = account.getDisplayName();
            String nickName = account.getGivenName();
            String famsName = account.getFamilyName();
            uuserEmail = account.getEmail();
            uuserId = account.getId();
            userName.setText(uuserName);
            userEmail.setText(uuserEmail);

            TTS = new TextToSpeech(Main2Activity.this, new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int status) {
                    if(status != TextToSpeech.ERROR) {
                        TTS.setLanguage(Locale.UK);
                    }
                }
            });

        }
        announceUser();
     ground = new ArrayList<>();
        groundTwo = new ArrayList<>();
        ground.add("Email from John2012@yahoo.com: supplies arrived");groundTwo.add("The new supplied arrived yesterday and there was no " +
                "body to do the offload. Therefore i'll like you to come to the office");
        ground.add("Email from Asai george:New Advert rates");groundTwo.add(" Advertising in our company now cheaper. We hope to allow serve you better " +
                "in the future" +
                "Warm thanks");
        ground.add("Email from sales@arikair.com: Change of ticket prize");groundTwo.add("We hereby announce a reduction in prize for our flight tickets. From " +
                "Lagos to Cairo now 100$" +
                "Lagos to Dubai now 89$" +
                "Lagos to London now 230$" +
                "Lagos to New York now 200$" +
                "Lagos to Johannesburg now 650$" +
                "Lagos to Miami now 200$");
        ground.add("Email from emekadavid@gmail.com: Great brothers association");groundTwo.add("This is to announce a change in venue for our next meeting. The " +
                "new venue is now at Chief Ogbotogbo house" +
                "Address: 32, Rilwan avenue. New Life Estate. Agege Lagos");
        ground.add("Email from gloria  daniels: We have a meeting");groundTwo.add("There'll be a board of directors meeting on the 5th of next month. Warmest regards");
        ground.add("Email from davidfortune@yahoo.com: Google Conference");groundTwo.add("Anticipate this month's Google Aba conference. The lauch of the latest firestore " +
                "beta will take place here and so many more technological expedition");
        ground.add("Email from asamoahidoko@yahoo.com");groundTwo.add("The get together party planned to hold on the 5th" +
                " of next month has been postponed to the twenty-third. Warmest regards");
//    try {

        ArrayList Inbox;
//        Inbox = (ArrayList) retrieveMessages();
//        ArrayAdapter<String> itemsAdapter =
//                new ArrayAdapter<String>(Main2Activity.this, android.R.layout.simple_list_item_1, Inbox);
//        EmailList = findViewById(R.id.MyList);
//        EmailList.setAdapter(itemsAdapter);
//    }catch (GeneralSecurityException e){
//        Toast.makeText(Main2Activity.this,   "Failed"+ e.getMessage().toString().toLowerCase(), Toast.LENGTH_LONG).show();
//    }catch (IOException e){
//        Toast.makeText(Main2Activity.this,   "Failed"+ e.getMessage().toString().toUpperCase(), Toast.LENGTH_LONG).show();
//    }

    }
    void announceUser(){
        TTS.speak("Retrieving emails from"+uuserName+uuserEmail, TextToSpeech.QUEUE_FLUSH, null, null);

    }
    private  Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT)throws IOException{
        // Load client secrets.
        Context context = this;
        InputStream in = context.getResources().openRawResource(R.raw.credentials);
//        InputStream in = getClass().getResourceAsStream(CREDENTIALS_FILE_PATH);
        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
        }
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY,  new InputStreamReader(in));
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                    HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                    .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                    .setAccessType("offline")
                    .build();

        // Build flow and trigger user authorization request.

            LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        try {
            goal = new AuthorizationCodeInstalledApp(flow, receiver).authorize("me");
        } catch (Exception e) {
            System.out.print(e);
        }
    return  goal;
    }

    public List<com.google.api.services.gmail.model.Message> retrieveMessages() throws IOException, GeneralSecurityException {
        // Build a new authorized API client service.

//        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        final NetHttpTransport HTTP_TRANSPORT =  new com.google.api.client.http.javanet.NetHttpTransport();
        Gmail service = new Gmail.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName("Email For The Blind")
                .build();
        ListMessagesResponse response = service.users().messages().list("me").setQ("maxResults").execute();

        return response.getMessages();
    }

    int counter ;
    String talk = "Emails";
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        event.startTracking();
        if ((keyCode == KeyEvent.KEYCODE_VOLUME_DOWN)) {
            counter++;
            if (counter == ground.size()) {
                counter = 0;
                TTS.speak("Emails finished", TextToSpeech.QUEUE_FLUSH, null, null);
            } else {
                talk = groundTwo.get(counter).toString();
                TTS.speak(ground.get(counter).toString(), TextToSpeech.QUEUE_FLUSH, null, null);

            }
        }

        return true;
    }
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_VOLUME_UP)) {
            if (counter == ground.size()) {
                TTS.speak("Emails finished", TextToSpeech.QUEUE_FLUSH, null, null);
            } else {
                   gotoEmailRead();
            }
        }

        return true;
    }

    public void gotoEmailRead() {

        Intent i = new Intent(Main2Activity.this, EmailRead.class);
        i.putExtra("emailToRead", talk);
        startActivity(i);
    }
}
