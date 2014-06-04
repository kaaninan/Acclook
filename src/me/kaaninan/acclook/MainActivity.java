package me.kaaninan.acclook;

import me.kaaninan.acclook.db.DatabaseManager;
import me.kaaninan.acclook.test.DatabaseList;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends FragmentActivity {

	private DrawerLayout DrawerLayout;
    private ListView DrawerList;
    private ActionBarDrawerToggle DrawerToggle;

    // My Wallet adý
    //private CharSequence DrawerTitle;
    private CharSequence Title;
    private String[] NavigationListItem;
    private String[] Pages;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
    	
		getActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbar));
	    
		// "My Wallet" ismini çeker
		//Title = DrawerTitle = getTitle();
		NavigationListItem = getResources().getStringArray(R.array.drawer);
		DrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		DrawerList = (ListView) findViewById(R.id.drawer_list);

		DrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
		DrawerList.setAdapter(new ArrayAdapter<String>(this,R.layout.drawer_list_item, NavigationListItem));
		
		DrawerList.setOnItemClickListener(new DrawerItemClickListener());
		        
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		DrawerToggle = new ActionBarDrawerToggle(
				this,
				DrawerLayout,
				R.drawable.ic_drawer2,
				R.string.drawer_open,
				R.string.drawer_close
		){
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(Title); 
				invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
			}
			public void onDrawerOpened(View drawerView) {
				// My Wallet ismi göürünür
				//getActionBar().setTitle(DrawerTitle);
				invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
			}
		};
		DrawerLayout.setDrawerListener(DrawerToggle);
		
		if (savedInstanceState == null) {
			selectItem(0);
		}
	}
		
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /* Called whenever we call invalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        boolean drawerOpen = DrawerLayout.isDrawerOpen(DrawerList);
        menu.findItem(R.id.action_add).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }
    
    private int options = 0; // Options menu item id'si ör/ hesap ekle = 0, kayýt ekle = 1

    @SuppressWarnings("deprecation")
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	
        if (DrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        
        switch(item.getItemId()) {
        
	        case R.id.action_add:
				showDialog(EKLE_KAYIT);
				break;
				
	        case R.id.action_db:
	        	// TODO Database
	        	Intent intent = new Intent(this, DatabaseList.class);
	        	startActivity(intent);
	        	break;
	        	
	        default:
	            return super.onOptionsItemSelected(item);
	        }
		return false;
    }
    
    
    /* Dialog */
    
    private static final int EKLE_HESAP = 0;
    public static final int EKLE_KAYIT = 1;
    private DatabaseManager manager;
    
    @Override
	protected Dialog onCreateDialog(int id){
		Dialog dialog;
		switch(id){
			case EKLE_HESAP:
				dialog = ekleHesapDialog();
			case EKLE_KAYIT:
				dialog = ekleKayitDialog();
				break;
				/*
				switch(options){
					case 1:
						dialog = ekleGelirDialog();
						break;
					case 2:
						dialog = ekleGiderDialog();
						break;
					default:
						dialog = null;
				}
				*/
				
			default:
				dialog = null;
		}
		return dialog;
	}
    
    @SuppressWarnings("deprecation")
	private Dialog ekleHesapDialog(){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		
		final View dialogEkle = LayoutInflater.from(this).inflate(R.layout.dialog_hesap_ekle, null);
		
		builder.setTitle("Ekle");
		builder.setView(dialogEkle);

		//final EditText tutarEdit = (EditText) dialogEkle.findViewById(R.id.editTextTutar);
    	//final EditText aciklamaEdit = (EditText) dialogEkle.findViewById(R.id.editTextAciklama);

		builder.setNegativeButton("Ýptal", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				removeDialog(EKLE_HESAP);
			}
		});
    	
		manager = new DatabaseManager(this);
		
		builder.setPositiveButton("Kaydet", new DialogInterface.OnClickListener() {	
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
				//float tutar = Float.valueOf(tutarEdit.getText().toString());
	            //String aciklama = aciklamaEdit.getText().toString();
	            
	    		//manager.ekleKayit(tutar, aciklama);
	    		/*
	    		Kayitlar kayit = (Kayitlar) getSupportFragmentManager().findFragmentById(R.id.main);
	    		kayit.refresh();
*/
	            dialog.dismiss();
	            removeDialog(EKLE_HESAP);
			}
		});

		builder.setCancelable(true);
	    return builder.create();
	}
    
	@SuppressWarnings("deprecation")
	private Dialog ekleKayitDialog(){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		
		final View dialogEkle = LayoutInflater.from(this).inflate(R.layout.dialog_kayit_ekle, null);
		
		builder.setTitle("Ekle");
		builder.setView(dialogEkle);

		final EditText tutarEdit = (EditText) dialogEkle.findViewById(R.id.editTextTutar);
    	final EditText aciklamaEdit = (EditText) dialogEkle.findViewById(R.id.editTextAciklama);
    	final EditText tarihEdit = (EditText) dialogEkle.findViewById(R.id.editTextTarih);

		builder.setNegativeButton("Ýptal", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				removeDialog(EKLE_KAYIT);
			}
		});
    	
		manager = new DatabaseManager(this);
		
		builder.setPositiveButton("Kaydet", new DialogInterface.OnClickListener() {	
			@Override
			public void onClick(DialogInterface dialog, int which) {
				float tutar = Float.valueOf(tutarEdit.getText().toString());
	            String aciklama = aciklamaEdit.getText().toString();
	            String tarih = null;
	            tarih = tarihEdit.getText().toString();
	            
	    		manager.ekleKayit(tutar, aciklama, tarih);
	    		
	    		// TODO refresh
	    		/*
	    		Kayit kayitlar = (Kayit) getSupportFragmentManager().findFragmentById(R.id.main);
	    		kayitlar.refresh();
				*/
	    		
	            dialog.dismiss();
	            removeDialog(EKLE_KAYIT);
			}
		});

		builder.setCancelable(true);
	    return builder.create();
	}
	
	/* Dialog End */


    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
            options = position;
        }
    }

    private void selectItem(int position) {
    	Pages = getResources().getStringArray(R.array.pages);
    	FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        tx.replace(R.id.main, Fragment.instantiate(MainActivity.this, Pages[position]));
        tx.commit();

        DrawerList.setItemChecked(position, true);
        setTitle(NavigationListItem[position]);
        DrawerLayout.closeDrawer(DrawerList);
    }

	@Override
    public void setTitle(CharSequence title) {
        Title = title;
        getActionBar().setTitle(Title);
	}
	
	
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        DrawerToggle.syncState();
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        DrawerToggle.onConfigurationChanged(newConfig);
    }
    
}
