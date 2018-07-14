package comp3350.ppms.presentation.generaluser;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.test.ppms.R;

import java.util.List;

import comp3350.ppms.domain.User;

public class UserAdapter extends ArrayAdapter<User> {

    private User user;

    public UserAdapter(Context context, List<User> users) {
        super(context,0,  users);
    }

    @Override
    public View getView(int index, View view, ViewGroup parent){

        // Check if an existing view is being reused, otherwise inflate the view
        if(view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.user_list_item, parent,
                    false);
        }

        // Get data from project and set up the views
        user = getItem(index);
        TextView userName = (TextView) view.findViewById(R.id.user_list_item_name);

        userName.setText(user.getUserNickName());

        return view;
    }

}
