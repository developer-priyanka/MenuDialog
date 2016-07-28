package my.assignment.menudialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by root on 7/27/16.
 */

public class CustomeAdapter extends BaseAdapter {

    private ArrayList<Person> data=new ArrayList<Person>();
    private Context context;
    private static LayoutInflater inflater=null;

    public CustomeAdapter(Context c,ArrayList<Person>list){
        context=c;
        data=list;
        inflater=LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class Holder{
        TextView tname;
        TextView tphone;
        TextView tdob;

    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Holder holder=new Holder();
        View rowView=view;
        rowView=inflater.inflate(R.layout.list_view,null);

        holder.tname=(TextView)rowView.findViewById(R.id.nametxt);
        holder.tphone=(TextView)rowView.findViewById(R.id.phonetxt);
        holder.tdob=(TextView)rowView.findViewById(R.id.datetxt);

        holder.tname.setText(data.get(i).getName());
        holder.tdob.setText(data.get(i).getDob());
        holder.tphone.setText((data.get(i).getPhone()));



        return rowView;
    }

}
