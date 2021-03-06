package br.com.cast.turmaformacao.taskmanager.controllers.adpters;

import android.app.Activity;
import android.graphics.PorterDuff;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.cast.turmaformacao.taskmanager.R;
import br.com.cast.turmaformacao.taskmanager.model.entities.Label;
import br.com.cast.turmaformacao.taskmanager.model.entities.Task;

/**
 * Created by Administrador on 15/09/2015.
 */
public class TaskListAdpater extends BaseAdapter {

    private List<Task> taskList;
    private Activity context;

    public TaskListAdpater(Activity context, List<Task> taskList) {
        this.taskList = taskList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return taskList.size();
    }

    @Override
    public Task getItem(int position) {
        return taskList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Task task = getItem(position);

        View taskListItemView = context.getLayoutInflater().inflate(R.layout.list_item_task, parent, false);

        View textColor = taskListItemView.findViewById(R.id.textViewLabel);

        TextView textViewName = (TextView) taskListItemView.findViewById(R.id.textViewName);

        textViewName.setText(task.getName());

        String color = task.getLabel() == null ? "#ffffff" : task.getLabel().getColor().getHex();

        int hexColor = android.graphics.Color.parseColor(color);

        textColor.getBackground().setColorFilter(hexColor, PorterDuff.Mode.SRC);


        return taskListItemView;
    }

    public void setDataValues(List<Task> values) {
        taskList.clear();
        taskList.addAll(values);
    }

}
