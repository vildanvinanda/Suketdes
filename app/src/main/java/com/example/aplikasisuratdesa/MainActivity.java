package com.example.aplikasisuratdesa;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Environment;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;

import androidx.core.app.ActivityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.aplikasisuratdesa.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    EditText ednama, edttl, edpendidikan, edpekerjaan, ednortu_ayah, ednortu_ibu, edalamat;
    EditText edperuntukan, edpenghasilan, eddusun;

    Button btn_buat;
    Bitmap bitmap, scaleBitmap,scaleBitmap2,scaleBitmap3;
    int pageWidth = 1654;
    Calendar calendar = Calendar.getInstance();
    Date dateObject;
    DateFormat dateFormat;
    Spinner spinerdusun, spinerrt, spinerrw, spineredjk,spineragama,spinerstpk;
    private EditText ednik, edkk;
    private TextView txtlisten_nik,txtlisten_kk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dateObject = new Date();

        setContentView(R.layout.activity_main);

        ednama = findViewById(R.id.ednama);
        edttl = findViewById(R.id.edttl);

        edpendidikan = findViewById(R.id.edpendidikan);
        edpekerjaan = findViewById(R.id.edpekerjaan);
        ednortu_ayah = findViewById(R.id.ednortu_ayah);
        ednortu_ibu = findViewById(R.id.ednortu_ibu);
        edalamat = findViewById(R.id.edalamat);


        spinerdusun = findViewById(R.id.spinerdusun);
        spinerrt = findViewById(R.id.spinerrt);
        spinerrw = findViewById(R.id.spinerrw);
        spineredjk = findViewById(R.id.spineredjk);
        spineragama = findViewById(R.id.spineragama);
        spinerstpk = findViewById(R.id.spinerstpk);



        //baru
        edperuntukan = findViewById(R.id.edperuntukan);
        edpenghasilan = findViewById(R.id.edpenghasilan);
        eddusun = findViewById(R.id.eddusun);

        btn_buat = findViewById(R.id.btn_buat);
        bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.titlelogo);
        scaleBitmap = Bitmap.createScaledBitmap(bitmap, 1260, 199, false);
        //scaleBitmap = Bitmap.createScaledBitmap(bitmap, (int) 600, 104, false);

        bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.stampel);
        //scaleBitmap2 = Bitmap.createScaledBitmap(bitmap, 192, 103, false);
        scaleBitmap2 = Bitmap.createScaledBitmap(bitmap, 330, 215, false);

        //bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.logo3);
        //scaleBitmap3 = Bitmap.createScaledBitmap(bitmap, 106, 104, false);

        //TEXT LISTENNER
        ednik = (EditText) findViewById(R.id.ednik);
        txtlisten_nik = (TextView) findViewById(R.id.txtlisten_nik);
        edkk = findViewById(R.id.edkk);
        txtlisten_kk = (TextView) findViewById(R.id.txtlisten_kk);

        TextWatcher falidasiInput = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }


            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                int panjangInput = editable.length();
                txtlisten_nik.setText(panjangInput+"/16");
            }
        };

        ednik.addTextChangedListener(falidasiInput);
        TextWatcher falidasiInput2 = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }


            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                int panjangInput2 = editable.length();
                txtlisten_kk.setText(panjangInput2+"/16");
            }
        };
        edkk.addTextChangedListener(falidasiInput2);

        //permission
        ActivityCompat.requestPermissions(MainActivity.this, new String[] {
                Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);


    }

    public void createbtn_buat (View view) {

        if (ednama.getText().toString().length() == 0 ||
                edttl.getText().toString().length() == 0 ||
                edpendidikan.getText().toString().length() == 0 ||
                edpekerjaan.getText().toString().length() == 0 ||
                ednortu_ayah.getText().toString().length() == 0 ||
                ednortu_ibu.getText().toString().length() == 0 ||
                edperuntukan.getText().toString().length() == 0 ||
                edpenghasilan.getText().toString().length() == 0 ||
                spinerdusun.getSelectedItem().toString().length() == 0 ||
                spinerrt.getSelectedItem().toString().length() == 0 ||
                spinerrw.getSelectedItem().toString().length() == 0 ||
                spineragama.getSelectedItem().toString().length() == 0 ||
                spineredjk.getSelectedItem().toString().length() == 0 ||
                spinerstpk.getSelectedItem().toString().length() == 0 ||
                ednik.getText().toString().length() <16 ||
                edkk.getText().toString().length() <16) {
            Toast.makeText(MainActivity.this, "Tolong Lengkapi Formulirnya.!!", Toast.LENGTH_LONG).show();
        } else {

            PdfDocument myPdfDocument = new PdfDocument();
            PdfDocument.PageInfo myPageInfo = new PdfDocument.PageInfo.Builder(1654, 2339, 1).create();
            //PdfDocument.PageInfo myPageInfo = new PdfDocument.PageInfo.Builder((int) 793.8, (int) 1122.5,1).create();
            PdfDocument.Page myPage = myPdfDocument.startPage(myPageInfo);
            Paint myPaint = new Paint();
            Canvas canvas = myPage.getCanvas();
            Paint titlepaint = new Paint();
            Paint namabold = new Paint();


            canvas.drawBitmap(scaleBitmap, (float) 197, (float) 79, myPaint);
            //canvas.drawBitmap(scaleBitmap, 97, (float) 25, myPaint);

            //logo doang baru
            //canvas.drawBitmap(scaleBitmap3, 105, (float) 25, myPaint);
            //

            //INI ADALAH STAMPLE LAMA
            //canvas.drawBitmap(scaleBitmap2, (float) 505, (float) 750, myPaint);
            // INI ADALAH  STAMPLE BARU

            canvas.drawBitmap(scaleBitmap2, (float) 1035, (float) 1657, myPaint);


            //myPaint.setTextAlign(Paint.Align.CENTER);
            //myPaint.setTextSize(55);
            //canvas.drawText("PEMERINTAH DAERAH KABUPATEN SUMEDANG", myPageInfo.getPageWidth()/2, 30, myPaint);
            //canvas.drawText("KECAMATAN PASEH", myPageInfo.getPageWidth()/2,40, myPaint);

            //titlepaint.setTextAlign(Paint.Align.CENTER);
            //titlepaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
            //titlepaint.setTextSize(35);
            //canvas.drawText("SURAT KETERANGAN TIDAK MAMPU", pageWidth/2,380, titlepaint);
            //BARUUUUUU
            //myPaint.setTextSize(15);
            //canvas.drawText("PEMERINTAHAN DAERAH KABUPATEN SUMEDANG", 226, 39, myPaint);
            //canvas.drawText("KECAMATAN PASEH", 361, 62, myPaint);

            //titlepaint.setTextAlign(Paint.Align.CENTER);
            //titlepaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
            //titlepaint.setTextSize(18);
            //canvas.drawText("DESA PASIREUNGIT", 347,83, titlepaint);
//
            titlepaint.setTextAlign(Paint.Align.CENTER);
            titlepaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
            titlepaint.setTextSize(33);
            //titlepaint.setTextSize(14);
            canvas.drawText("SURAT KETERANGAN", pageWidth / 2, 390, titlepaint);
            //canvas.drawText("SURAT KETERANGAN", pageWidth/2,180, titlepaint);

            //canvas.drawText("SURAT KETERANGAN", pageWidth/2,390, titlepaint);
            //titlepaint.setTextSize(12);
            int year = calendar.get(Calendar.YEAR);
            canvas.drawText("Nomor : 551/136/IX/Ds. " + year, pageWidth / 2, 430, titlepaint);
            //canvas.drawText("Nomor : 551/136/IX/Ds.2021", pageWidth/2, 198, titlepaint);

            myPaint.setTextSize(30);
            //myPaint.setTextSize(12);
            canvas.drawText("Yang bertanda tangan di bawah ini, Kepala Desa PASIREUNGIT Kecamatan PASEH Kabupaten", 244, 505, myPaint);
            canvas.drawText("SUMEDANG, menerangkan dengan sebenarnya bahwa : ", 197, 555, myPaint);
            //canvas.drawText("Yang bertanda tangan di bawah ini, Kepala Desa PASREUNGIT Kecamatan PASEH", 139, 239, myPaint);
            //canvas.drawText("Kabupaten SUMEDANG, menerangkan dengan sebenarnya bahwa : ", 97, 263, myPaint);

            namabold.setTextSize(30);
            //namabold.setTextSize(12);
            canvas.drawText("Nama Lengkap                        ", 197, 615, myPaint);
            canvas.drawText(":", 590, 615, myPaint);
            //canvas.drawText("Nama Lengkap                          ", 97,299, myPaint);
            String myString = ednama.getText().toString();
            namabold.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
            int x = 610, y = 615;
            //int x = 271, y=299;
            myPage.getCanvas().drawText(myString, x, y, namabold);

            myPaint.setTextSize(30);
            canvas.drawText("Nomor Induk Kependudukan   ", 197, 665, myPaint);
            canvas.drawText(":", 590, 665, myPaint);
            //canvas.drawText("Nomor Induk Kependudukan  : ", 97,323, myPaint);
            String myString2 = ednik.getText().toString();
            int x2 = 610, y2 = 665;
            myPage.getCanvas().drawText(myString2, x2, y2, myPaint);

            myPaint.setTextSize(30);
            canvas.drawText("Nomor Kartu Keluarga              ", 197, 715, myPaint);
            canvas.drawText(":", 590, 715, myPaint);
            // canvas.drawText("Nomor Kartu Keluarga             : ", 97,347, myPaint);
            String myString3 = edkk.getText().toString();
            int x3 = 610, y3 = 715;
            myPage.getCanvas().drawText(myString3, x3, y3, myPaint);

            myPaint.setTextSize(30);
            canvas.drawText("Tempat, Tanggal Lahir             ", 197, 765, myPaint);
            canvas.drawText(":", 590, 765, myPaint);
            // canvas.drawText("Tempat, Tanggal Lahir              : ", 97,371, myPaint);
            String myString4 = edttl.getText().toString();
            int x4 = 610, y4 = 765;
            myPage.getCanvas().drawText(myString4, x4, y4, myPaint);

            myPaint.setTextSize(30);
            canvas.drawText("Jenis Kelamin                            ", 197, 815, myPaint);
            canvas.drawText(":", 590, 815, myPaint);
            //canvas.drawText("Jenis Kelamin                            : ", 97,395, myPaint);
//        String myString5 = edjk.getText().toString();
//        int x5 = 610, y5= 815;
//        myPage.getCanvas().drawText(myString5, x5,y5,myPaint);
            //masih error
            if (spineredjk.getSelectedItemPosition() != 0) {
                canvas.drawText(spineredjk.getSelectedItem().toString(), 610, 815, myPaint);
            }

            myPaint.setTextSize(30);
            canvas.drawText("Agama                                        ", 197, 865, myPaint);
            canvas.drawText(":", 590, 865, myPaint);
            //canvas.drawText("Agama                                        : ", 97,419, myPaint);
//        String myString6 = edagama.getText().toString();
//        int x6 = 610, y6= 865;
//        myPage.getCanvas().drawText(myString6, x6,y6,myPaint);
            if (spineragama.getSelectedItemPosition() != 0) {
                canvas.drawText(spineragama.getSelectedItem().toString(), 610, 865, myPaint);
            }


            myPaint.setTextSize(30);
            canvas.drawText("Pekerjaan                                  ", 197, 915, myPaint);
            canvas.drawText(":", 590, 915, myPaint);
            // canvas.drawText("Pekerjaan                                    : ", 97,443, myPaint);
            String myString7 = edpekerjaan.getText().toString();
            int x7 = 610, y7 = 915;
            myPage.getCanvas().drawText(myString7, x7, y7, myPaint);

            myPaint.setTextSize(30);
            canvas.drawText("Setatus Perkawinan                ", 197, 965, myPaint);
            canvas.drawText(":", 590, 965, myPaint);
//        String myString8 = edsp.getText().toString();
//        int x8 = 610, y8= 965;
//        myPage.getCanvas().drawText(myString8, x8,y8,myPaint);
            if (spinerstpk.getSelectedItemPosition() != 0) {
                canvas.drawText(spinerstpk.getSelectedItem().toString(), 610, 965, myPaint);
            }

            myPaint.setTextSize(30);
            canvas.drawText("Nama Orang Tua                ", 197, 1015, myPaint);
            canvas.drawText(":", 590, 1015, myPaint);
            String myString12 = ednortu_ayah.getText().toString();
            String myString111 = ednortu_ibu.getText().toString();
            int x12 = 610, y12 = 1015;
            myPage.getCanvas().drawText(myString12 + " / " + myString111, x12, y12, myPaint);

            myPaint.setTextAlign(Paint.Align.LEFT);
            myPaint.setTextSize(30);
            canvas.drawText("Alamat                                      ", 197, 1065, myPaint);
            canvas.drawText(":", 590, 1015, myPaint);
            canvas.drawText("Desa PASIREUNGIT  Kecamatan PASEH", (float) 610, 1115, myPaint);
            canvas.drawText("Kabupaten SUMEDANG  Provinsi JAWA BARAT", (float) 610, 1165, myPaint);
            //canvas.drawText("Alamat                                        : ", 97,491, myPaint);
            //canvas.drawText("Desa PASIREUNGIT  Kecamatan PASEH", (float) 271,515, myPaint);
            //canvas.drawText("Kabupaten SUMEDANG  Provinsi JAWA BARAT", (float) 271,539, myPaint);
//        String myString9 = eddusun.getText().toString();
//        int x9 = 610, y9= 1065;
//        myPage.getCanvas().drawText(myString9, x9,y9,myPaint);
            if (spinerdusun.getSelectedItemPosition() != 0) {
                canvas.drawText(spinerdusun.getSelectedItem().toString(), 610, 1065, myPaint);
            }
            if (spinerrt.getSelectedItemPosition() != 0) {
                canvas.drawText("RT." + spinerrt.getSelectedItem().toString(), 1065, 1065, myPaint);
            }
            if (spinerrw.getSelectedItemPosition() != 0) {
                canvas.drawText("RW." + spinerrw.getSelectedItem().toString(), 1140, 1065, myPaint);
            }

            myPaint.setTextSize(30);
            canvas.drawText("Berdasarkan keterangan dari RT/RW setempat dan data yang ada, benar bahwa yang", 244, 1245, myPaint);
            canvas.drawText("bersangkutan Penduduk Desa PASIREUNGIT Kecamatan PASEH dan :", 197, 1295, myPaint);
            //canvas.drawText("Berdasarkan keterangan dari RT/RW setempat dan data yang ada, benar bahwa yang", 139, 587, myPaint);
            //canvas.drawText("bersangkutan Penduduk Desa PASIREUNGIT Kecamatan PASEH dan :", 97, 611, myPaint);

            titlepaint.setTextAlign(Paint.Align.CENTER);
            titlepaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
            titlepaint.setTextSize(30);
            //canvas.drawText("==benar mempunyai penghasilan rata-rata                                  per bulan==", pageWidth/2,647, titlepaint);
            //String myString10 = edpenghasilan.getText().toString();
//            String myString10 = edpenghasilan.getText().toString();
//            //int x10 = 447, y10= 647;
//            int y10 = 1375;
//            //int y10= 647;
//            myPage.getCanvas().drawText("==benar mempunyai penghasilan rata-rata Rp." + myString10 + ",- per bulan==", pageWidth / 2, y10, titlepaint);
            Double a1 = Double.parseDouble(edpenghasilan.getText().toString());
            DecimalFormat kursIndonesia = (DecimalFormat)
            DecimalFormat.getCurrencyInstance();
            DecimalFormatSymbols formatRp = new DecimalFormatSymbols();
            formatRp.setCurrencySymbol("Rp.");
            formatRp.setMonetaryDecimalSeparator(',');
            formatRp.setGroupingSeparator('.');
            kursIndonesia.setDecimalFormatSymbols(formatRp);
            canvas.drawText("==benar mempunyai penghasilan rata-rata " + (kursIndonesia.format(a1))+",- per bulan==", pageWidth / 2,1375,titlepaint);


            myPaint.setTextSize(30);
            canvas.drawText("Surat Keterangan ini diperuntukan untuk :", 197, 1455, myPaint);
            // canvas.drawText("Surat Keterangan ini diperuntukan untuk :", 97,677, myPaint);
            String myString11 = edperuntukan.getText().toString();
            int x11 = 755, y11 = 1455;
            // int x11 = 330, y11= 677;
            myPage.getCanvas().drawText(myString11, x11, y11, myPaint);

            myPaint.setTextSize(30);
            canvas.drawText("Demikian keterangan ini, untuk dipergunakan sebagaimana mestinya.", 244, 1505, myPaint);
            //canvas.drawText("Demikian keterangan ini, untuk dipergunakan sebagaimana mestinya.", 139,701, myPaint);


            //myPaint.setStyle(Paint.Style.STROKE);
            //myPaint.setStrokeWidth(8f);
            //canvas.drawLine((float) 94.5,280,(float) 1559.5, 280,myPaint);
            //jarak y itu 60
            //230 - 290

            //myPaint.setStyle(Paint.Style.STROKE);
            // myPaint.setStrokeWidth(3.5f);
            //canvas.drawLine((float) 92,140,(float) 697, 140,myPaint);

            //myPaint.setStyle(Paint.Style.STROKE);
            // myPaint.setStrokeWidth(1f);
            //canvas.drawLine((float) 92,145,(float) 697, 145,myPaint);
            //539,6,386,1124.7,386

            ;

            dateFormat = new SimpleDateFormat("dd MMMM yyy");
            canvas.drawText("Sumedang, " + dateFormat.format(dateObject), 1105, 1657, myPaint);
            canvas.drawText("Kepala Desa Pasireungit", 1105, 1707, myPaint);
            titlepaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
            canvas.drawText("VILDAN VINANDA", 1260, 1874, titlepaint);

            myPaint.setStyle(Paint.Style.STROKE);
            myPaint.setStrokeWidth(3f);
            canvas.drawLine(1150, 1884, 1380, 1884, myPaint);


            myPaint.setStyle(Paint.Style.STROKE);
            myPaint.setStrokeWidth(6f);
            canvas.drawLine(197, 288, 1457, 288, myPaint);

            myPaint.setStyle(Paint.Style.STROKE);
            myPaint.setStrokeWidth(3f);
            canvas.drawLine(197, 297, 1457, 297, myPaint);

            myPaint.setStyle(Paint.Style.STROKE);
            myPaint.setStrokeWidth(3f);
            canvas.drawLine(666, 400, 990, 400, myPaint);


            myPdfDocument.finishPage(myPage);


            String myFilePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();

            File myFile = new File(myFilePath, "S.Penghasilan.pdf");


            try {
                myPdfDocument.writeTo(new FileOutputStream(myFile));
            } catch (Exception e) {
                e.printStackTrace();
            }

            Toast.makeText(MainActivity.this, "PDF Berhasil Dibuat", Toast.LENGTH_LONG).show();

            myPdfDocument.close();
        }

    }

        public void backtohomeactivity (View view){
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);
        }

}
