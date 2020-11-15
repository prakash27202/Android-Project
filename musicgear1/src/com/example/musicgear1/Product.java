package com.example.musicgear1;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class Product extends Activity {

	ListView lst;
	String[] name, price,img;
	Bitmap imgArr[];
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_product);

		lst = (ListView) findViewById(R.id.listView1);
		lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "hello there", Toast.LENGTH_LONG).show();
			}
		});
		new FetchProductsDetails().execute();

	}

	public class FetchProductsDetails extends AsyncTask<Object, Object, Object> {

		@Override
		protected Object doInBackground(Object... params) {
			// TODO Auto-generated method stub

			String url = "http://192.168.43.214/practise/product.php";
			HttpClient client = new DefaultHttpClient();
			HttpPost post = new HttpPost(url);
			StringBuilder sb = new StringBuilder();
			try {
				HttpResponse response = client.execute(post);
				BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
				String line = "";
				while ((line = br.readLine()) != null) {
					sb.append(line);
				}
				Log.e("Data::::::>>>>>", sb.toString());
				JSONObject Jsonobject = new JSONObject(sb.toString());
				JSONArray jsonArray = Jsonobject.getJSONArray("result");
				int len = jsonArray.length();
				name = new String[len];
				img = new String[len];
				price = new String[len];
				
				imgArr=new Bitmap[len];

				String url_map = "http://192.168.43.214/images/";
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject temp = jsonArray.getJSONObject(i);

					Log.e("PRODUCTS::", temp.toString());
					name[i] = temp.getString("product");
					img[i] = url_map + temp.getString("image");
					Log.e("Image::", img[i]);
					price[i] = temp.getString("price");
					
					URL url1=new URL(img[i]);
					Bitmap image1 = BitmapFactory.decodeStream(url1.openConnection().getInputStream());
					imgArr[i]=image1;
				}
				Log.e("Image Array::", imgArr+"");
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return sb.toString();
		}

		@Override
		protected void onPostExecute(Object result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			Adapter cus = new Adapter(Product.this, name, price,imgArr);
			lst.setAdapter(cus);
			Toast.makeText(getApplicationContext(), result.toString(), Toast.LENGTH_LONG).show();
		}
	}
}
