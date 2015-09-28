package com.example.lijo.hawkeye;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

public class LOGIN extends Activity implements OnClickListener {

    Button btnlogin;
    Button btnreg;
    EditText username;
    EditText password;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if(!Controller.isFirstTime(getApplicationContext())){
            startActivity(new Intent(getApplicationContext(),HOME.class));
            finish();
        }

        try{
            getActionBar().setTitle("HAWCK EYE");
            getActionBar().setIcon(R.drawable.ic_launcher);
            //getActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.back));
        }catch(Exception ex){








        }


        // Buttons
        btnlogin = (Button) findViewById(R.id.btnlogin );
        btnreg = (Button) findViewById(R.id.btncreate);

        username = (EditText) findViewById(R.id.inputname);
        password = (EditText) findViewById(R.id.inputpassword);


        btnlogin.setOnClickListener(this);
        btnreg.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.btnlogin:
                new AttemptLogin().execute();
                break;
            case R.id.btncreate:
                Intent i = new Intent(this, Registration.class);
                startActivity(i);
                break;



            default:
                break;
        }
    }


    @Override
    public void onBackPressed() {
        Intent i = new Intent(this, LOGIN.class);
        startActivity(i);
    }

    class AttemptLogin extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        boolean failure = false;
        String error;
        boolean errorStatus = false;
        String result;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(LOGIN.this);
            pDialog.setMessage("Logging in User...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override

        protected String doInBackground(String... args) {
            // TODO Auto-generated method stub
            // Check for success tag
            int success;
            final String user = username.getText().toString();
            final String pass = password.getText().toString();
            try {

                HttpClient Client = new DefaultHttpClient();
                HttpPost httppost = new HttpPost("http://10.4.8.21/jesus/login.php?uname=" + user + "&password=" + pass);
                Log.i("SERVER REQ", "URL: " + "http://10.4.8.21/jesus/login.php?uname=" + user + "&password=" + pass);
               // HttpPost httppost = new HttpPost("http://lijojoby.hostzi.com/hawck/login.php?uname=" + user + "&password=" + pass);
               // Log.i("SERVER REQ", "URL: " + "http://lijojoby.hostzi.com/hawck/login.php?uname=" + user + "&password=" + pass);
                runOnUiThread(new Runnable() {
                    public void run() {
                        // Toast.makeText(getApplicationContext(), "URL: "+"http://10.4.8.21/jesus/login.php?uname="+user+"&password="+pass, 0).show();
                    }
                });
                ResponseHandler<String> responseHandler = new BasicResponseHandler();
                result = Client.execute(httppost, responseHandler);
                Log.i("SERVER RESPONSE", result);

            } catch (Exception e) {
                e.printStackTrace();
                //Toast.makeText(getApplicationContext(), "Something went wrong,please try later", 0).show();
                error=e.toString();
            }

            return null;

        }
        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once product deleted
            pDialog.dismiss();
            if (file_url != null){
                //Toast.makeText(MainActivity.this, file_url, Toast.LENGTH_LONG).show();
            }

            if(error!=null){
                Toast.makeText(getApplicationContext(), "Something went wrong!", 1).show();
            }else if(this.result.equals("1")){
                Controller.changeStatus(LOGIN.this);
                startActivity(new Intent(LOGIN.this,HOME.class));
                finish();
            }else if(this.result.equals("0")){
                Toast.makeText(getApplicationContext(), "Inavlid uname or paswrd!", 0).show();
            }

        }

    }

}