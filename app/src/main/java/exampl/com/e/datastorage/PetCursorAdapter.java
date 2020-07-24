package exampl.com.e.datastorage;

import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import exampl.com.e.datastorage.data.PetContract;

class PetCursorAdapter extends CursorAdapter {
    public PetCursorAdapter(Context context, Cursor c) {
        super(context, c,0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // find indivisual  view that we want to modify in te list item layout
        TextView nameTextView = (TextView) view.findViewById(R.id.name);
        TextView summaryTextView =(TextView) view.findViewById(R.id.summary);


        // Find the column of the pet attributes that we are interested in
        int nameColumnIndex = cursor.getColumnIndex(PetContract.PetEntry.COLUMN_PET_NAME);
        int summaryColumnIndex = cursor.getColumnIndex(PetContract.PetEntry.COLUMN_PET_BREED);

        //Read the column of the attributes that we are interested in
        String petName = cursor.getString(nameColumnIndex);
        String petBreed = cursor.getString(summaryColumnIndex);

        // If the pet breed is empty string or null, then use some default text
        // that says "Unknown breed", so the TextView isn't blank.
        if (TextUtils.isEmpty(petBreed)) {
            petBreed = context.getString(R.string.unknown_breed);
        }


        //update the TextView With The Attributes for the current Pets
        nameTextView.setText(petName);
        summaryTextView.setText(petBreed);

    }
}
