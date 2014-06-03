package me.kaaninan.acclook;

import me.kaaninan.acclook.constructor.KayitConstructor;
import android.content.Context;
import android.widget.SectionIndexer;
/*
public class FastScrollAdapter extends SimpleAdapter implements SectionIndexer {

    private KayitConstructor[] sections;

    public FastScrollAdapter(Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }

    @Override protected void prepareSections(int sectionsNumber) {
        sections = new KayitConstructor[sectionsNumber];
    }

    @Override protected void onSectionAdded(KayitConstructor section, int sectionPosition) {
        sections[sectionPosition] = section;
    }

    @Override public KayitConstructor[] getSections() {
        return sections;
    }

    @Override public int getPositionForSection(int section) {
        if (section >= sections.length) {
            section = sections.length - 1;
        }
        return sections[section].listPosition;
    }

    @Override public int getSectionForPosition(int position) {
        if (position >= getCount()) {
            position = getCount() - 1;
        }
        return getItem(position).sectionPosition;
    }

}*/