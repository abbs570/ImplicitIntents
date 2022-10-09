package com.example.implicitintents;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    //2.1: Add private variable at top of class to hold the editText object for the website URL
    private TextInputEditText mWebsiteEditText;
    private TextInputEditText mLocationEditText;
    private TextInputEditText mShareTextEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //use findviewbyid() to get a reference to the edit text instance
        mLocationEditText = findViewById(R.id.location_Edittext);

        //use findviewbyid to get a reference to the EditText instance
        mShareTextEditText = findViewById(R.id.share_edittext);
    }

    public void openWebsite(View view) {

        //use findViewById() to get a reference to the TExtInputEditText instance
        mWebsiteEditText = findViewById(R.id.website_edittext);

        //add statement to new open website method that gets string value of the TextInputEditText
        String url = mWebsiteEditText.getText().toString();
        //Encode and parse sting to a URI object
        Uri webpage = Uri.parse(url);
        //create a new intent as the action and the uri as the data
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);

        //Make sure at least one activity can handle the requests
        //Use intent action and data with the intent filters for installed apps
        if(intent.resolveActivity(getPackageManager())!=null) {
            //call start activity to send the intent
            startActivity(intent);
        }
            else{
                Log.d("ImplicitIntents", "Can't handle this!");
            }


    }

    public void openLocation(View view) {

        //statement to get the string value of mLocationEditText
        String loc = mLocationEditText.getText().toString();
        //parse string to a Uri object with a geo search query
        Uri addressUri=Uri.parse("geo:0,0?q=" + loc);
        //create a new intent with Intent.ACTIONVIEW as the action and loc as the data
        Intent intent = new Intent(Intent.ACTION_VIEW, addressUri);

        //resolve intent and check to make sure the intent is resolved
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);

        }else{
            Log.d("ImplicitIntents", "Can't handle this intent!");
        }
    }

    public void shareText(View view) {
        //statement to get the string value
        String txt = mShareTextEditText.getText().toString();

        //Define the mime type of text to share
        String mimeType = "text/plain";

        //call ShareCompat.IntentBuilder with these methods:
        ShareCompat.IntentBuilder
                .from(this)
                .setType(mimeType)
                .setChooserTitle("Share this text with: ")
                .setText(txt)
                .startChooser();

        //Extract the value of setChooserTitle to string resorse

    }


    //Method for openCamera
    public void openCamera(View view) {

        //create a new intent
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");

        //resolve intent and check to make sure the intent is resolved
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);

        }else{
            Log.d("ImplicitIntents", "Can't handle this intent!");
        }
    }

}