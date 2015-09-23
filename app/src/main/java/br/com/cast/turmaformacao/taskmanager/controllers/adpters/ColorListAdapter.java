package br.com.cast.turmaformacao.taskmanager.controllers.adpters;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import br.com.cast.turmaformacao.taskmanager.R;
import br.com.cast.turmaformacao.taskmanager.model.entities.Color;

public class ColorListAdapter extends BaseAdapter {
    private Color[] values;
    private Activity context;

    public ColorListAdapter(Activity context, Color[] values) {
        super();
        this.context = context;
        this.values = values;
    }

    @Override
    public int getCount() {
        return values.length;
    }

    @Override
    public Color getItem(int position) {
        return values[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItem = context.getLayoutInflater().inflate(R.layout.list_item_spinner_color, parent, false);
        int cor = android.graphics.Color.parseColor(getItem(position).getHex());
        listItem.findViewById(R.id.spinnerItemColor).setBackgroundColor(cor);
        return listItem;
    }
}
