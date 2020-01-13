package com.sgcreatives.a0004;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;

import android.os.Environment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Token extends AppCompatActivity {
        Button btnCreate,btnload;
        EditText txttox;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_token);
            txttox = findViewById(R.id.editText);
            btnload=findViewById(R.id.button);
            btnCreate = (Button) findViewById(R.id.button2);
            btnCreate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    createPdf(txttox.getText().toString());
                }
            });
        }

        private void createPdf(String sometext) {
            // create a new document
            PdfDocument document = new PdfDocument();
            // crate a page description
            PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(300, 600, 1).create();
            // start a page
            PdfDocument.Page page = document.startPage(pageInfo);
            Canvas canvas = page.getCanvas();
            Paint paint = new Paint();
            paint.setColor(Color.BLACK);
            canvas.drawText(sometext, 80, 50, paint);
            //canvas.drawt
            // finish the page
            document.finishPage(page);
// draw text on the graphics object of the page
            // Create Page 2
            pageInfo = new PdfDocument.PageInfo.Builder(300, 600, 2).create();
            page = document.startPage(pageInfo);
            canvas = page.getCanvas();
            paint = new Paint();
            paint.setColor(Color.BLUE);
            canvas.drawCircle(100, 100, 100, paint);
            document.finishPage(page);
            // write the document content
            String directory_path = Environment.getExternalStorageDirectory().getPath() + "/mypdf/";
            File file = new File(directory_path);
            if (!file.exists()) {
                file.mkdirs();
            }
            String targetPdf = directory_path + "application.pdf";
            File filePath = new File(targetPdf);
            try {
                document.writeTo(new FileOutputStream(filePath));
                Toast.makeText(this, "Done", Toast.LENGTH_LONG).show();
            } catch (IOException e) {
                Log.e("main", "error " + e.toString());
                Toast.makeText(this, "Something wrong: " + e.toString(), Toast.LENGTH_LONG).show();
            }
            // close the document
            document.close();
        }
}