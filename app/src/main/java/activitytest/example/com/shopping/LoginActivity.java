package activitytest.example.com.shopping;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class LoginActivity extends AppCompatActivity {

    Handler mChildHandler,mMainHandler;
    JSONObject jo = new JSONObject();
    String json;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());

        mMainHandler = new Handler(){
            public void handleMessage(Message msg)
            {
                String str= (String)msg.obj;
                if(str.equals("false")){
                    new AlertDialog.Builder(LoginActivity.this).setTitle("登录失败")//设置对话框标题
                            .setMessage("账号或密码错误！")//设置显示的内容
                            .setPositiveButton("确定",new DialogInterface.OnClickListener() {//添加确定按钮
                                @Override
                                public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                                    // TODO Auto-generated method stub
                                }
                            }).show();//在按键响应事件中显示此对话框
                }else {
                   /* SharedPreferences.Editor editor1 = getSharedPreferences("admin",MODE_PRIVATE).edit();
                    editor1.putString("Name",editText1.getText().toString());
                    editor1.putString("Password",editText2.getText().toString());
                    editor1.commit();*/
                    new AlertDialog.Builder(LoginActivity.this).setTitle("登录成功")//设置对话框标题
                            .setMessage("恭喜登陆成功")//设置显示的内容
                            .setPositiveButton("确定",new DialogInterface.OnClickListener() {//添加确定按钮
                                @Override
                                public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                                    // TODO Auto-generated method stub
                                }
                            }).show();//在按键响应事件中显示此对话框
                    setResult(RESULT_OK);
                   /* Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    onDestroy();
                    startActivity(intent);*/


                }
            }
        };


        new LoginThread().start();
        //注册的点击事件
        TextView textView = (TextView) findViewById(R.id.lable_register);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,registerActivity.class);
                startActivity(intent);
            }
        });
        //登陆按钮的点击事件
        Button button1 = (Button)findViewById(R.id.button_login);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText1 = (EditText)findViewById(R.id.editText_name);
                EditText editText2 = (EditText)findViewById(R.id.editText_password);
                String name = editText1.getText().toString();
                String password = editText2.getText().toString();
                editText1.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        return (event.getKeyCode()==KeyEvent.KEYCODE_ENTER);
                    }
                });
                if(name.isEmpty()||password.isEmpty()){
                    Toast.makeText(LoginActivity.this,"用户名密码不能为空",Toast.LENGTH_SHORT);
                }else {
                   /* editor = pref.edit();
                    if (rem.isChecked()) {
                        editor.putBoolean("remember_password", true);
                        editor.putString("account", name);
                        editor.putString("password", password);
                    } else {
                        editor.clear();
                    }
                    editor.commit();*/
                    try {
                        jo.put("name", name);
                        jo.put("password", password);
                        Log.i("json", jo.toString());
                        Message child = mChildHandler.obtainMessage();
                        child.obj = jo.toString();
                        mChildHandler.sendMessage(child);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });




    }

    public void onDestroy() {
        super.onDestroy();
        if(mChildHandler!=null)
            mChildHandler.getLooper().quit();
    }

    class LoginThread extends Thread{
        private static final String CHILD_TAG = "ChildThread";

        public void run() {
            this.setName("ChildThread");
            Looper.prepare();

            mChildHandler = new Handler() {
                public void handleMessage(Message msg) {
                    String strUrl = SeverIP.ip+"/ShoppingServlet/LoginServlet";
                    URL url = null;
                    try{
                        url = new URL(strUrl);
                        HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
                        urlConnection.setDoInput(true);
                        urlConnection.setDoOutput(true);
                        urlConnection.setRequestMethod("POST");
                        urlConnection.setUseCaches(false);
                        urlConnection.setRequestProperty("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
                        urlConnection.setRequestProperty("Charset","utf-8");
                        urlConnection.connect();
                        //发送
                        //Log.d("",msg.obj.toString());
                        DataOutputStream dop = new DataOutputStream(urlConnection.getOutputStream());
                        dop.writeBytes("users="+ msg.obj);
                        Log.i("发送", "param="+ URLEncoder.encode((String)msg.obj,"utf-8"));
                        dop.flush();
                        dop.close();
                        //接收
                        InputStream is = urlConnection.getInputStream();
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is,"utf-8"));
                        String readLine = null;
                        String result = "";
                        while((readLine = bufferedReader.readLine())!=null)
                        {
                            result += readLine;
                        }
                        bufferedReader.close();
                        urlConnection.disconnect();
                        //将数据传到主线程
                       Message toMain = mMainHandler.obtainMessage();
                        toMain.obj = result;
                        mMainHandler.sendMessage(toMain);
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                }
            };

            //启动子线程消息循环队列
            Looper.loop();
        }
    }

}
