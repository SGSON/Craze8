package comp3350.ppms.presentation.allusers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.test.ppms.R;

import java.util.ArrayList;
import java.util.List;

public class CredentialsAdapter extends ArrayAdapter<String> {
    private String mCredential;

    public CredentialsAdapter(Context context, List<String> credentials) {
        super(context, 0, credentials);
    }


    @Override
    public View getView(int index, View view, ViewGroup parent){

        // Check if an existing view is being reused, otherwise inflate the view
        if(view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.list_item_credential, parent,
                    false);
        }

        // Get data from project and set up the views
        mCredential = getItem(index);
        TextView textView_project_credential = (TextView)view.findViewById(R.id.credential_value);

        textView_project_credential.setText(mCredential);

        return view;
    }
}
