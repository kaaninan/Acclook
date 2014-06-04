package me.kaaninan.acclook;

import java.util.ArrayList;
import java.util.HashMap;

import me.kaaninan.acclook.adapter.FastScrollAdapter;
import me.kaaninan.acclook.adapter.KayitPinnedAdapter;
import me.kaaninan.acclook.constructor.KayitConstructor;
import me.kaaninan.acclook.db.DatabaseContract;
import me.kaaninan.acclook.db.DatabaseManager;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hb.views.PinnedSectionListView;

public class Kayit extends Fragment{

	@SuppressWarnings("unused")
	private Context context;
	private ViewGroup root;
	//private View empty;
	
	private DatabaseManager manager;
	private ArrayList<KayitConstructor> arrayListKayit;
	
	
	// Kayýt Adapter
	//private ListView listViewKayit;
	//private PullToRefreshListView listViewKayit2;
	//private KayitAdapter adapterList;
	
	
	// Expandable ListView
	//private ExpandableListView listViewExpandable;
	//private ExpandableListAdapter adapterExpandable;
	
	private HashMap<String, ArrayList<KayitConstructor>> listDataChild;
	private ArrayList<String> listDataHeader;
	
	private ArrayList<KayitConstructor> kayitlar;
	
	
	// Kayýt Pinned Adapter
	private PinnedSectionListView listSection;
	private KayitPinnedAdapter adapterPinned;
	
	/*
	private boolean hasHeaderAndFooter;
	private boolean isFastScroll;
	private boolean addPadding;
	private boolean isShadowVisible = true;
	*/
	
	
    public static Fragment newInstance(Context context) {
    	Kayit f = new Kayit();
        return f;
    }
    
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		root = (ViewGroup) inflater.inflate(R.layout.kayit, null);
		//ViewGroup emptyViewGroup = (ViewGroup) inflater.inflate(R.layout.empty, null);
		manager = new DatabaseManager(getActivity().getApplicationContext());
		
		
		
		// PinnedSectionListView
		
		listSection = (PinnedSectionListView) root.findViewById(R.id.listSection);
		
		prepareListData();
		
		adapterPinned = new KayitPinnedAdapter(getActivity(), R.layout.kayit_list, kayitlar);
		listSection.setAdapter(adapterPinned);

		// FastScroll
		listSection.setFastScrollEnabled(true);
		listSection.setFastScrollAlwaysVisible(true);
		listSection.setAdapter(new FastScrollAdapter(getActivity(), R.layout.kayit_list, kayitlar));
		
		// ###
		
		
		/*
		// Kayit Adapter
		listViewKayit = (ListView) root.findViewById(R.id.listKayit);
		//listViewKayit2 = (PullToRefreshListView) root.findViewById(R.id.hadi);
		
		arrayListKayit = manager.getKayitlar();
		
		if(arrayListKayit.size() == 0){
			listViewKayit.setEmptyView(empty);
		}else{
			adapterList = new KayitAdapter(getActivity().getApplicationContext(), R.layout.kayit_list, arrayListKayit);
			listViewKayit.setAdapter(adapterList);
		}
		// End Kayit Adapter
		*/
		
		
		/*
		// Expandable ListView
		listViewExpandable = (ExpandableListView) root.findViewById(R.id.listExpandable);
		prepareListData();
		
		listViewExpandable.setEmptyView(root.findViewById(R.id.emptyView));
		
		adapterExpandable = new KayitExpandableAdapter(getActivity(), listDataHeader, listDataChild);
		listViewExpandable.setAdapter(adapterExpandable);
		
		// End Expandable ListView
		*/
		
		
		
		
		// Expandable ListView onClick ###############################################################################
		/*
		list.setOnGroupClickListener(new OnGroupClickListener() {
	
			@Override
			public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
				Log.i("onGroupClick","týklandý");
				// Toast.makeText(getApplicationContext(),
				// "Group Clicked " + listDataHeader.get(groupPosition),
				// Toast.LENGTH_SHORT).show();
				return false;
			}
		});

		// Listview Group expanded listener
		list.setOnGroupExpandListener(new OnGroupExpandListener() {
			@Override
			public void onGroupExpand(int groupPosition){
				Toast.makeText(getActivity().getApplicationContext(), listDataHeader.get(groupPosition) + " Expanded",
						Toast.LENGTH_SHORT).show();
				//list.setGroupIndicator(bottom);
			}
		});

		// Listview Group collasped listener
		list.setOnGroupCollapseListener(new OnGroupCollapseListener() {

			@Override
			public void onGroupCollapse(int groupPosition) {
				Toast.makeText(getActivity().getApplicationContext(),
						listDataHeader.get(groupPosition) + " Collapsed",
						Toast.LENGTH_SHORT).show();
				//list.setGroupIndicator(top);

			}
		});

		// Listview on child click listener
		list.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				Toast.makeText(
						getActivity().getApplicationContext(),
						listDataHeader.get(groupPosition)
								+ " : "
								+ listDataChild.get(
										listDataHeader.get(groupPosition)).get(
										childPosition), Toast.LENGTH_SHORT)
						.show();
				return false;
			}
		});
		// End Expandable ListView onClick #############################################################################
		*/
		
    	
        return root;
	}
	
	
	
	
	private int a;
	
	private int gun;
	private int ay;
	private int yil;
	
	private String dbTarih;
	//private String dbSaat;
	
	private String[] gun_ay_yil;
	
	private Cursor c;
	
	private int yapildi;
	private int yapilacak;
	
	private String tercih;
	
	private void prepareListData() {
		
		a = 0;
		listDataHeader = new ArrayList<String>();
		listDataChild = new HashMap<String, ArrayList<KayitConstructor>>();
		
		kayitlar = new ArrayList<KayitConstructor>();
		
		manager = new DatabaseManager(getActivity());
		c = manager.sorgulaKayitlar();
		
		
		// Bir kayýt için olanlar
		while (c.moveToNext()){
			dbTarih = c.getString(c.getColumnIndex(DatabaseContract.Kayit.COLUMN_TARIH));
			
			// Tarih Ýþlemleri ##################################################
			String[] tarihSaat = manager.convertDateTimeTireNokta(dbTarih);
			// Gün ay yýl bulma
			String delims = "[.]+";
			gun_ay_yil = tarihSaat[0].split(delims);
			
			
			// TODO tercih
			tercih = "ay";
			
			// Tercihe göre sýralama belirleme ##################################
			if(tercih == "gun"){
				gun = Integer.parseInt(gun_ay_yil[0]);
				yapilacak = gun;
				guneGoreSirala(dbTarih);
				
			}else if(tercih == "ay"){
				ay = Integer.parseInt(gun_ay_yil[1]);
				yapilacak = ay;
				ayaGoreSirala(ay, dbTarih);
				
			}else if(tercih == "yil"){
				yil = Integer.parseInt(gun_ay_yil[2]);
				yapilacak = yil;
				yilaGoreSirala(yil, dbTarih);
				
			}
		}
	}
	
	// Baþlýk Ekleme #############################################################
	
	private void guneGoreSirala(String dbTarih){
		if(yapilacak != yapildi){
			// Baþlýk Ekleme 
			listDataHeader.add("Gün ismi "+dbTarih);
			// List Ekleme
			gunEkle(dbTarih);
		}
	}
	
	private void ayaGoreSirala(int ay, String dbTarih){
		
		switch(ay){
			case 1:
				if(yapilacak != yapildi){
					listDataHeader.add("Ocak "+gun_ay_yil[2]);
					ayEkle(dbTarih);
					break;
				}
			case 2:
				if(yapilacak != yapildi){
					listDataHeader.add("Þubat "+gun_ay_yil[2]);
					ayEkle(dbTarih);
					break;
				}
			case 3:
				if(yapilacak != yapildi){
					listDataHeader.add("Mart "+gun_ay_yil[2]);
					ayEkle(dbTarih);
					break;
				}
			case 4:
				if(yapilacak != yapildi){
					listDataHeader.add("Nisan "+gun_ay_yil[2]);
					ayEkle(dbTarih);
					break;
				}
			case 5:
				if(yapilacak != yapildi){
					listDataHeader.add("Mayýs "+gun_ay_yil[2]);
					ayEkle(dbTarih);
					break;
				}
			case 6:
				if(yapilacak != yapildi){
					listDataHeader.add("Haziran "+gun_ay_yil[2]);
					ayEkle(dbTarih);
					break;
				}
			case 7:
				if(yapilacak != yapildi){
					listDataHeader.add("Temmuz "+gun_ay_yil[2]);
					ayEkle(dbTarih);
					break;
				}
			case 8:
				if(yapilacak != yapildi){
					listDataHeader.add("Aðustos "+gun_ay_yil[2]);
					ayEkle(dbTarih);
					break;
				}
			case 9:
				if(yapilacak != yapildi){
					listDataHeader.add("Eylül "+gun_ay_yil[2]);
					ayEkle(dbTarih);
					break;
				}
			case 10:
				if(yapilacak != yapildi){
					listDataHeader.add("Ekim "+gun_ay_yil[2]);
					ayEkle(dbTarih);
					break;
				}
			case 11:
				if(yapilacak != yapildi){
					listDataHeader.add("Kasým "+gun_ay_yil[2]);
					ayEkle(dbTarih);
					break;
				}
			case 12:
				if(yapilacak != yapildi){
					listDataHeader.add("Aralýk "+gun_ay_yil[2]);
					ayEkle(dbTarih);
					break;
				}
			default:
				break;
		}
	}
	
	private void yilaGoreSirala(int yil, String dbTarih){
		if(yapilacak != yapildi){
			listDataHeader.add(gun_ay_yil[2]);
			yilEkle(dbTarih);
		}
	}
	
	
	
	
	// List Ekleme ################################################################
	
	private void gunEkle(String dbTarih){
		arrayListKayit = manager.getKayitlar(dbTarih, tercih);
		yapildi = gun;
		listDataChild.put(listDataHeader.get(a), arrayListKayit);
		a++;
	}
	
	private void ayEkle(String dbTarih){
		
		// Expandable ListView
		arrayListKayit = manager.getKayitlar(dbTarih, tercih);
		yapildi = ay;
		listDataChild.put(listDataHeader.get(a), arrayListKayit);
		
		int sP = manager.sectionPosition;
		Log.i("1", String.valueOf(sP));
		
		int lP = manager.listPosition;

		// Pinned için
		KayitConstructor kayitt = new KayitConstructor();
		kayitt.setNot(listDataHeader.get(a));
		kayitt.type = 1;
		kayitt.sectionPosition = sP;
		kayitt.listPosition = lP++;
		
		Log.i("2", String.valueOf(sP));
		
		
		// FastScrollAdapter
		kayitlar.add(kayitt);
		kayitlar.addAll(arrayListKayit);
		
		manager.sectionPosition++;
		
		Log.i("3", String.valueOf(manager.sectionPosition));
		
		a++;
	}
	
	private void yilEkle(String dbTarih){
		arrayListKayit = manager.getKayitlar(dbTarih, tercih);
		yapildi = yil;
		listDataChild.put(listDataHeader.get(a), arrayListKayit);
		a++;
	}
	
	
	
	
/*
	private class GetDataTask extends AsyncTask<Void, Void, String[]> {
	    @Override
	    protected void onPostExecute(String[] result) {
	        // Call onRefreshComplete when the list has been refreshed.
	        list.onRefreshComplete();
	        super.onPostExecute(result);
	    }

		@Override
		protected String[] doInBackground(Void... params) {
			return null;
		}
	}*/

	
	public void refresh() {
/*
		// Normal ListView
		arrayListKayit = manager.getKayitlar(null);
		adapterList = new KayitAdapter(getActivity(), R.layout.kayit_list, arrayListKayit);
		listViewKayit.setAdapter(adapterList);
*/
		
/*
		// Expandable ListView
		prepareListData();	
		adapterExpandable = new KayitExpandableAdapter(getActivity(), listDataHeader, listDataChild);
		listViewExpandable.setAdapter(adapterExpandable);
*/

		// Pinned Section
		arrayListKayit = manager.getKayitlar(null, null);
		
		KayitPinnedAdapter adapterPinned = new KayitPinnedAdapter(getActivity(), android.R.layout.simple_list_item_1, kayitlar);
		listSection.setAdapter(adapterPinned);
	}

		
}