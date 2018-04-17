package activitytest.example.com.shopping;

import android.content.DialogInterface;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class registerActivity extends AppCompatActivity {
    Handler mChildHandler,mMainHandler;
    JSONObject jo = new JSONObject();
    NumberPicker numberPicker;
    RadioGroup radioGroup;
    String gender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //性别选择按钮初始化
        radioGroup = (RadioGroup)findViewById(R.id.register_group);
        RadioButton radioButton1 = (RadioButton)findViewById(R.id.register_male);

        //年龄选择按钮初始化
        numberPicker = (NumberPicker)findViewById(R.id.register_old);
        numberPicker.setFocusable(true);
        numberPicker.setMinValue(8);
        numberPicker.setMaxValue(120);
        numberPicker.setValue(15);
        numberPicker.setFocusableInTouchMode(true);

        mMainHandler = new Handler(){
            public void handleMessage(Message msg)
            {
                String str= (String)msg.obj;
                if(str.equals("false")){
                    new AlertDialog.Builder(registerActivity.this).setTitle("注册失败")//设置对话框标题
                            .setMessage("该用户名已被注册")//设置显示的内容
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
                    new AlertDialog.Builder(registerActivity.this).setTitle("注册成功")//设置对话框标题
                            .setMessage("恭喜注册成功")//设置显示的内容
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
        new RegisterThread().start();
        Button b_register = (Button)findViewById(R.id.button_register);
        b_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText e_name = (EditText)findViewById(R.id.register_name);
                EditText e_password = (EditText)findViewById(R.id.register_pass);
                if(radioGroup.getCheckedRadioButtonId()==R.id.register_female)
                    gender = "2";
                else
                    gender = "1";
                String old = String.valueOf(numberPicker.getValue());
                String name = e_name.getText().toString();
                String password = e_password.getText().toString();
               /* editText1.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        return (event.getKeyCode()==KeyEvent.KEYCODE_ENTER);
                    }
                });*/
                if(name.isEmpty()||password.isEmpty()||old.isEmpty()||gender.isEmpty()){
                    Toast.makeText(registerActivity.this,"请填写完整",Toast.LENGTH_SHORT);
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
                        jo.put("gender",gender);
                        jo.put("old",old);
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


    class RegisterThread extends Thread{
        private static final String CHILD_TAG = "ChildThread";

        public void run() {
            this.setName("ChildThread");
            Looper.prepare();

            mChildHandler = new Handler() {
                public void handleMessage(Message msg) {
                    String strUrl = SeverIP.ip+"/ShoppingServlet/RegisterServlet";
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
