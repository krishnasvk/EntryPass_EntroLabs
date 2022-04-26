package lbrce.ac.in.entrypass_entrolabs;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.pax.dal.IDAL;
import com.pax.dal.IPrinter;
import com.pax.dal.exceptions.PrinterDevException;
import com.pax.neptunelite.api.NeptuneLiteUser;

import vtek.GL11.AndroidPrinter;

public class MainActivity extends AppCompatActivity {
    public static final int REPORTS = 0, LOGOUT = 1, REPRINT = 2, UPDATE = 3;
    public static IDAL idal = null;

    static {
        System.loadLibrary("printerfinal");
        System.out.println("Printer Library Loaded Successfully");
    }
    TextView adu_text,child_text,camer_text,video_text,total_text;
    int count =0;
    int a=0;
    int b=0;
    int c =0;
    int total = 0;
    IPrinter printer;
   // public static IDAL idal = null;
    AndroidPrinter ap = new AndroidPrinter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
     //   printer = GetObj.getDal().getPrinter();


        if (Build.MANUFACTURER.equalsIgnoreCase("PAX")) {
            try {

                idal = NeptuneLiteUser.getInstance().getDal(this);
                printer = (IPrinter) idal;
            } catch (Exception e) {
               Log.i ("print Error ","exception printer init" + e.toString());
            }
            if (null == idal) {
                Toast.makeText(this, "error occurred,DAL is null.", Toast.LENGTH_LONG).show();
            }
        } else {
            ap.prn_open();
        }
        printer=GetObj.getDal().getPrinter();
        try {
            printer.init();
        } catch (PrinterDevException e) {
            e.printStackTrace();
        }


        adu_text = findViewById(R.id.ad_text1);
        child_text = findViewById(R.id.chid_text1);
        camer_text = findViewById(R.id.camer_text1);
        video_text = findViewById(R.id.video_text1);
        total_text = findViewById(R.id.total1);
    }

    public void ad_dec(View view) {
        if(count>0){
            count-=10;
            adu_text.setText(""+count);
            total = total-10;
            total_text.setText(""+total);


        }


    }

    public void ad_inc(View view) {
        count+=10;
        adu_text.setText(""+count);
        total = total+10;
        total_text.setText(""+total);


    }

    public void chi_dec(View view) {
        if(a>0){
            a=a-5;
            child_text.setText(""+a);
            total = total-5;
            total_text.setText(""+total);

        }
    }

    public void chi_inc(View view) {
        a=a+5;
        child_text.setText(""+a);
        total = total+5;
        total_text.setText(""+total);

    }

    public void camer_dec(View view) {
        if(b>0){
            b-=20;

            camer_text.setText(""+b);
           total = total-20;
           total_text.setText(""+total);
        }
    }
    public void camer_inc(View view) {
        b+=20;
        camer_text.setText(""+b);
        total = total+20;
        total_text.setText(""+total);
    }

    public void video_dec(View view) {
        if(c>0){
            c-=100;
            video_text.setText(""+c);
            total = total-100;
            total_text.setText(""+total);
        }
    }
    public void video_inc(View view) {
        c+=100;
        video_text.setText(""+c);
        total = total+100;
        total_text.setText(""+total);
    }
    public void reset(View view) {
        adu_text.setText("");
        child_text.setText("");
        camer_text.setText("");
        video_text.setText("");
        total_text.setText("");
    }
    public void card(View view) throws PrinterDevException {
       /*Intent intent = new Intent(this,SecondActivity.class);
        startActivity(intent);*/
        // printer.init();
      //  printer.printStr("Hello Print","kkk");
        int ss  = ap.prn_write_text("HelloWorld ", 7, 2);
        ap.prn_paper_feed(1);
        if(ss==0){
            Toast.makeText(this, "Print Successfully", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
        }
    }
    static class GetObj {
        private static IDAL dal;
        public  String logStr = "";

        // 获取IDal dal对象
        public static IDAL getDal() {
            dal = MainActivity.idal;
            if (dal == null) {
                Log.e("NeptuneLiteDemo", "dal is null");
            }
            return dal;
        }

    }
    public void cash(View view) {
    }


}