package datviet.FlyBeau.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import datviet.FlyBeau.R;
import datviet.FlyBeau.model.Group;
import datviet.FlyBeau.utils.lazylist.ImageLoader;


/**
 * Created by NamNgo on 14/03/2016.
 */
//
public class PickGroupAdapter extends BaseAdapter {

    Context context;
    private String[] data;
    private static LayoutInflater inflater=null;

    public datviet.FlyBeau.utils.lazylist.ImageLoader imageLoader;
    ArrayList<Group> lstGroup;
    public PickGroupAdapter(Context context, ArrayList<Group> lstGroup) {
        //activity = a;
        this.context = context;
        this.lstGroup = lstGroup;
        imageLoader = new ImageLoader(this.context.getApplicationContext());
    }

    class Holder
    {
        ImageView iv;
        TextView tvGroupName, tvNumberPeople;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return lstGroup.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder;
        if(convertView == null)
        {
            holder = new Holder();
            inflater = (LayoutInflater)this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.pick_group_item, null);

            holder.iv=(ImageView)convertView.findViewById(R.id.imageView1);
            holder.tvGroupName=(TextView)convertView.findViewById(R.id.tvGroupName);
            holder.tvNumberPeople=(TextView)convertView.findViewById(R.id.tvNumberPeople);
            convertView.setTag(holder);

        }
        else
        {
            holder=(Holder)convertView.getTag();
        }
        holder.tvGroupName.setText(lstGroup.get(position).groupName);
        holder.tvNumberPeople.setText(lstGroup.get(position).numberPeople + " people");
        //imageLoader.DisplayImage(lstVideo.get(position).url, holder.iv);
        holder.iv.setImageResource(lstGroup.get(position).imageSRC);
        return convertView;
    }


}
