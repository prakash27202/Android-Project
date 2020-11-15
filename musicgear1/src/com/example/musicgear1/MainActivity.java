package com.example.musicgear1;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
public class MainActivity extends Activity {
	EditText fullname;
	EditText email;
	EditText pass;
	EditText mobile;
	EditText address;
	Button btnsignup;
	public TextView t1;
@Override
protected void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
setContentView(R.layout.activity_main);
fullname=(EditText)findViewById(R.id.id_Password);
email=(EditText)findViewById(R.id.id_password);
pass=(EditText)findViewById(R.id.id_pass);
mobile=(EditText)findViewById(R.id.id_mobile);
address=(EditText)findViewById(R.id.id_address);
btnsignup=(Button)findViewById(R.id.id_btnsignin);
t1=(TextView)findViewById(R.id.textView2);
t1.setOnClickListener(new View.OnClickListener() {
	
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		Intent intent=new Intent(MainActivity.this,Login1.class);
		startActivity(intent);
	}
});
btnsignup.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
// TODO Auto-generated method stub
	String username=fullname.getText().toString();
	String useremail=email.getText().toString();
	String userpass=pass.getText().toString();
	String usermobile=mobile.getText().toString();
	String useraddress=address.getText().toString();
	new insertData(username,useremail,userpass,usermobile,useraddress).execute();
}
});
}
@Override
public boolean onCreateOptionsMenu(Menu menu) {
// Inflate the menu; this adds items to the action bar if it is present. getMenuInflater().inflate(R.menu.insert, menu);
return true;
}
public class insertData extends AsyncTask<Object, Object, Object> {
String uname,upassword,uemail,umobile,uaddress;
public insertData(String a, String b, String c, String d, String e) {
// TODO Auto-generated constructor stub
uname=a;
upassword=c;
uemail=b;
umobile=d;
uaddress=e;
Log.e("CHECK DATA",a+" "+b+" "+c+" "+d+" "+e);
}
@Override
protected Object doInBackground(Object... params) {
List <NameValuePair> nameValue = new ArrayList<NameValuePair>();
nameValue.add(new BasicNameValuePair("name", uname));
nameValue.add(new BasicNameValuePair("pass", upassword));
nameValue.add(new BasicNameValuePair("email", uemail));
nameValue.add(new BasicNameValuePair("mobile", umobile));
nameValue.add(new BasicNameValuePair("address", uaddress));
String url = "http://192.168.43.214/practise/register1.php";
HttpClient client = new DefaultHttpClient();
HttpPost post = new HttpPost(url);
try {
post.setEntity(new UrlEncodedFormEntity(nameValue));
HttpResponse httpResponse = client.execute(post);
HttpEntity httpEntity = httpResponse.getEntity();
} catch (UnsupportedEncodingException e) {
// TODO Auto-generated catch block
e.printStackTrace();
} catch (ClientProtocolException e) {
// TODO Auto-generated catch block
e.printStackTrace();
} catch (IOException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
Log.e("Insert","Data Inserted");
return "Data Inserted";
}
@Override
protected void onPostExecute(Object result) {
// TODO Auto-generated method stub
super.onPostExecute(result);
Toast.makeText(getApplicationContext(), "Data Inserted Successfully", Toast.LENGTH_LONG).show();
}
}
}
