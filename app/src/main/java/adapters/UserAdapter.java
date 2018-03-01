package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import models.User;

/**
 * Created by Jorge on 01/03/2018.
 */

public class UserAdapter extends ArrayAdapter<User> {

    private Context context;
    private ArrayList<User> users;

    public UserAdapter (Context context, ArrayList<User> users) {

        super(context, android.R.layout.simple_list_item_2, users);

        this.context = context;
        this.users = users;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout view;
        ViewHolder holder;

        if (convertView == null) {
            view = new LinearLayout(context);

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            inflater.inflate(android.R.layout.simple_list_item_2, view, true);

            holder = new ViewHolder();
            holder.text1 = (TextView) view.findViewById(android.R.id.text1);
            holder.text2 = (TextView) view.findViewById(android.R.id.text2);
        }
        else {
            view = (LinearLayout) convertView;
            holder = (ViewHolder) view.getTag();
        }

        User user = users.get(position);
        holder.text1.setText(user.getFullName());
        holder.text2.setText(user.getUserName());

        return view;
    }

    static class ViewHolder {
        TextView text1;
        TextView text2;
    }
}

