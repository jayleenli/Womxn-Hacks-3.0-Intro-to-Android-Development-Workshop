package com.example.womxnhacksandroidworkshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    //Actual functionality of app!
    Button generateButton = findViewById(R.id.generate);
    Button uploadButton = findViewById(R.id.upload);
    Button switchActivityButton = findViewById(R.id.switchActivity);

    generateButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        EditText topMemeTextInput = findViewById(R.id.topMemeTextInput);
        EditText bottomMemeTextInput = findViewById(R.id.bottomMemeTextInput);
        TextView topMemeText = findViewById(R.id.topMemeText);
        TextView bottomMemeText = findViewById(R.id.bottomMemeText);

        topMemeText.setText(topMemeTextInput.getText().toString());
        bottomMemeText.setText(bottomMemeTextInput.getText().toString());
      }
    });

    uploadButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, 1);
      }
    });

    switchActivityButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        openAnotherActivity();
      }
    });
  }

  //Open Another Activity
  public void openAnotherActivity() {
    Intent intent = new Intent(this, AnotherActivity.class);
    startActivity(intent);
  }


  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (resultCode == RESULT_OK && requestCode == 1) {
      //NOTE: This does not actually upload the image to the app! It just changes the URI of the image to something on your phone
      Uri selectedImage = data.getData();
      ImageView memeImage = findViewById(R.id.memeImage);
      memeImage.setImageURI(selectedImage);
      }
  }
}