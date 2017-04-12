package team2.com.easyaspi.adaptersPackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import team2.com.easyaspi.R;
import team2.com.easyaspi.apiPackage.NotificationBean;

/**
 * Created by DLau on 2017-04-10.
 */

public class NotificationAdapter extends ArrayAdapter<NotificationBean> {


    public NotificationAdapter(Context context, ArrayList<NotificationBean> arrayNotification) {
        super(context,R.layout.notification_layout,arrayNotification);

    }



    private static class ViewHolder {
        TextView subject;
        TextView notification;
    }



    public View getView(int position, View convertView, ViewGroup parent) {
        NotificationBean notification = getItem(position);
        ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflate = LayoutInflater.from(getContext());
            convertView = inflate.inflate(R.layout.notification_layout,parent,false);

            viewHolder.subject = (TextView)convertView.findViewById(R.id.value_subject);
            viewHolder.notification = (TextView)convertView.findViewById(R.id.value_notification);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        viewHolder.subject.setText(notification.getSubject());
        viewHolder.notification.setText(notification.getNotification());
        return convertView;
    }

}
