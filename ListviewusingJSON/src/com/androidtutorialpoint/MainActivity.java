package com.androidtutorialpoint;

import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {

	// Declare Variables
	ListView list;
	LazyAdapter lazyadp;
	public ImageLoader imageLoader;
	String[] name1;
	private ArrayList<HashMap<String, String>> mylist2;
	String[] image1;
	private ProgressDialog pDialog;
	// ImageView my_image;
	// Progress dialog type (0 - for Horizontal progress bar)
	public static final int progress_bar_type = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mylist2 = new ArrayList<HashMap<String, String>>();
		list = (ListView) findViewById(R.id.listView1);
		System.out.println("started");
		new look2().execute("http://192.168.1.40/image/jsonurl.php?");

		/*
		 * list.setOnItemClickListener(new OnItemClickListener() {
		 * 
		 * @Override public void onItemClick(AdapterView<?> parent, View view,
		 * int position, long id) { // Send single item click data to
		 * SingleItemView Class Intent i = new Intent(MainActivity.this,
		 * SingleItemView.class); // Pass all data rank i.putExtra("name1",
		 * name1); // Pass all data country
		 * 
		 * // Pass all data flag i.putExtra("image1", image1); // Pass a single
		 * position
		 * 
		 * // Open SingleItemView.java Activity startActivity(i); }
		 * 
		 * });
		 */

	}

	class look2 extends AsyncTask<String, String, String> {

		private JSONArray lookfor;
		private ProgressDialog pd;
		String srchtxt, url;

		private JSONObject e;
		private ArrayList<HashMap<String, String>> mylist;
		private HashMap<String, String> map;

		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pd = new ProgressDialog(MainActivity.this);
			pd.setMessage("Please wait...");
			pd.setCancelable(false);
			pd.show();
			System.out.println("Loading");
		}

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			try {

				String[] ss = params;

				StrictMode.ThreadPolicy policy1 = new StrictMode.ThreadPolicy.Builder()
						.permitAll().build();
				StrictMode.setThreadPolicy(policy1);
				mylist = new ArrayList<HashMap<String, String>>();
				System.out.println("In Background.....");

				url = ss[0];
				// url.replace("", "%20");
				System.out.println(url);
				JSONObject js = JSONfunctions.getJSONfromURL(url);
				System.out.println("....." + js);
				JSONObject res = js.getJSONObject("response");
				System.out.println("....." + res);

				// JSONObject res1=res.getJSONObject("success");
				if (res.has("items")) {
					lookfor = res.getJSONArray("items");
					System.out.println("items get+++++++");
					if (lookfor != null)

						for (int i = 0; i < lookfor.length(); i++) {
							map = new HashMap<String, String>();
							e = lookfor.getJSONObject(i);
							map.put("sno", e.getString("sno"));
							map.put("name1", e.getString("name1"));
							map.put("image1", e.getString("image1"));

							mylist.add(map);
							mylist2 = mylist;

							System.out.println("insert" + mylist);
						}

					else {

					}

				}

			} catch (JSONException e) {
				// TODO: handle exception
				System.out.println(e);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e);
			}

			return null;
		}

		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			String[] name1 = new String[mylist.size()];
		
			String[] image1 = new String[mylist.size()];

			for (int i = 0; i < mylist.size(); i++) {

				name1[i] = mylist.get(i).get("name1");
			
				image1[i] = mylist.get(i).get("image1");

			}

			list = (ListView) findViewById(R.id.listView1);
			// adapter = new ListViewAdapter2(name1.this, name1, para,image1);
			// list.setAdapter(adapter);

			lazyadp = new LazyAdapter(MainActivity.this, mylist);
			list.setAdapter(lazyadp);
			pd.dismiss();

		}

	}

	public class LazyAdapter extends BaseAdapter {

		private Activity activity;
		private ArrayList<HashMap<String, String>> data;
		private LayoutInflater inflater;
		public ImageLoader imageLoader;
		String ss;

		public LazyAdapter(Activity a, ArrayList<HashMap<String, String>> d) {
			activity = a;
			data = d;
			inflater = (LayoutInflater) activity
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			imageLoader = new ImageLoader(activity.getApplicationContext());
		}

		public int getCount() {
			return data.size();
		}

		public Object getItem(int position) {
			return position;
		}

		public long getItemId(int position) {
			return position;
		}

		public View getView(final int position, View convertView,
				ViewGroup parent) {

			View vi = convertView;
			if (convertView == null)
				vi = inflater.inflate(R.layout.list_row, null);
			TextView name1 = (TextView) vi.findViewById(R.id.textView1);

			ImageView thumb_image = (ImageView) vi
					.findViewById(R.id.imageView1); // thumb image

			HashMap<String, String> book = new HashMap<String, String>();
			book = data.get(position);
			name1.setText(book.get("name1"));

			imageLoader.DisplayImage(book.get("image1"), thumb_image);

			return vi;
		}
	}
}
