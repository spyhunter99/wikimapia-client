package com.github.spyhunter99.wikimapia_client;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import org.wikimapia.api.Categories;
import org.wikimapia.api.Category;
import org.wikimapia.api.Parser;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * created on 1/11/2017.
 *
 * @author Alex O'Ree
 */

public class CategoryAdapter extends BaseAdapter implements Filterable {
    Context context;

    List<Category> data = new ArrayList<>();
    public CategoryAdapter(Context ctx){
        context=ctx;
    }



    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                data = (List<Category>) results.values;
                notifyDataSetChanged();
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                FilterResults results = new FilterResults();
                List<Category> FilteredArrayNames = new ArrayList<Category>();

                // perform your search here using the searchConstraint String.

                constraint = constraint.toString().toLowerCase();
                for (int i = 0; i < data.size(); i++) {
                    Category dataNames = data.get(i);
                    if (dataNames.getName().toLowerCase().startsWith(constraint.toString()))  {
                        FilteredArrayNames.add(dataNames);
                    }
                }

                results.count = FilteredArrayNames.size();
                results.values = FilteredArrayNames;
                Log.e("VALUES", results.values.toString());

                return results;
            }
        };

        return filter;
    }


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null) {
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.categorylistitem, parent);
        }
        TextView tv = (TextView) convertView.findViewById(R.id.categoryListViewItemText);
        tv.setText(((Category)getItem(position)).getName());
        return convertView;
    }

    public void addData(Categories results) {
        data.addAll(results.getCategories());
        notifyDataSetChanged();
    }
}
