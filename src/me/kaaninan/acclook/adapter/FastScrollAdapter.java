package me.kaaninan.acclook.adapter;

import java.util.ArrayList;

import me.kaaninan.acclook.constructor.KayitConstructor;
import android.content.Context;
import android.util.Log;
import android.widget.SectionIndexer;


public class FastScrollAdapter extends KayitPinnedAdapter implements SectionIndexer {

    private KayitConstructor[] sections;
    
	private ArrayList<KayitConstructor> list;

    public FastScrollAdapter(Context context, int layoutResourceId, ArrayList<KayitConstructor> list) {
        super(context, layoutResourceId, list);
        this.list = list;
    }

    @Override
	public void prepareSections(int sectionsNumber) {        
        sections = new KayitConstructor[sectionsNumber];
    }

    @Override
	public void onSectionAdded(KayitConstructor section, int sectionPosition) {
        sections[sectionPosition] = section;
        Log.i("onSectionAdded",String.valueOf(section.listPosition));
    }

    @Override
    public KayitConstructor[] getSections() {
        return sections;
    }

    @Override
    public int getPositionForSection(int section) {
        if (section >= sections.length) {
            section = sections.length - 1;
        }
        
        // TODO Log
        Log.i("aasd",String.valueOf(section));
        
        Log.i("List.position", list.get(section)._not +" - "+list.get(section).listPosition);
        Log.i("Sections.position", sections[section]._not+" - "+ sections[section].listPosition);
        
        //return list.get(section).listPosition;
        return sections[section].listPosition;
    }

    @Override
    public int getSectionForPosition(int position) {
        if (position >= getCount()) {
            position = getCount() - 1;
        }
        return list.get(position).sectionPosition;
    }

}
