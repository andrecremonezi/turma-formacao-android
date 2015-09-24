package br.com.cast.turmaformacao.taskmanager.controllers.adpters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;
import br.com.cast.turmaformacao.taskmanager.R;
import br.com.cast.turmaformacao.taskmanager.model.entities.Label;

public class LabelListAdapter extends BaseAdapter{
    private List<Label> labels;
    private Activity context;

    public LabelListAdapter(List<Label> labels, Activity context){
        this.labels  = labels;
        this.context = context;
    }

    public LabelListAdapter(Activity context,List<Label> labels) {
        this.labels = labels;
        this.context = context;
    }

    @Override
    public int getCount() {
        return labels.size();
    }

    @Override
    public Label getItem(int position) {
        return labels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = context.getLayoutInflater().inflate(R.layout.list_item_label, parent, false);
        TextView textName = (TextView) view.findViewById(R.id.textViewLabelName);

        TextView textDescricao = (TextView) view.findViewById(R.id.textViewLabelDesc);

        TextView textColor = (TextView) view.findViewById(R.id.textViewLabelColor);

        Label label = getItem(position);

        textName.setText(label.getName());
        textDescricao.setText(label.getDescription());

        String color = label.getColor() == null ? "#ffffff" : label.getColor().getHex();

        int hexColor = android.graphics.Color.parseColor(color);

        textColor.setBackgroundColor(hexColor);

        return view;
    }
}
