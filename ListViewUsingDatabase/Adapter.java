package com.example.musicgear1;


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Adapter extends ArrayAdapter<String> {

	final String[] p_name,p_price;
	final Context context;
	TextView itemname, itemprice;
	ImageView img;
	Bitmap image;
	Bitmap[] imgArray;

	public Adapter(Context context, String[] name, String[] p_price, Bitmap[] imgArr) {
		// TODO Auto-generated constructor stub
		super(context, R.layout.activity_adapter, name);
		this.context = context;
		this.p_name = name;
		this.p_price = p_price;
		this.imgArray=imgArr;

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub

		if (convertView == null) {
			convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_adapter, null);
		}
	/*	URL url;
		try {
			url = new URL(p_img[position]);
			image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
*/
		itemname = (TextView) convertView.findViewById(R.id.textView1);
		itemprice = (TextView) convertView.findViewById(R.id.textView2);
		img = (ImageView) convertView.findViewById(R.id.imageView1);

		itemname.setText(p_name[position]);
		itemprice.setText(p_price[position]);
		img.setImageBitmap(imgArray[position]);

		return convertView;
	}
}
