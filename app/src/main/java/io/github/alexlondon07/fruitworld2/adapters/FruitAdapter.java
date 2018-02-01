package io.github.alexlondon07.fruitworld2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import io.github.alexlondon07.fruitworld2.R;
import io.github.alexlondon07.fruitworld2.models.Fruit;

/**
 * Created by alexlondon07 on 1/31/18.
 */

public class FruitAdapter  extends BaseAdapter{

    private Context context;
    private int layout;
    private List<Fruit> fruitList;

    public FruitAdapter(Context context, int layout, List<Fruit> fruitList) {
        this.context = context;
        this.layout = layout;
        this.fruitList = fruitList;
    }

    @Override
    public int getCount() {
        return fruitList.size();
    }

    @Override
    public Fruit getItem(int position) {
        return fruitList.get(position);
    }

    @Override
    public long getItemId(int id) {
        return id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if(convertView == null){

            //La primera vez que entre, se infla la vista y adjuntamos las referencias del layout en una nueva instancia de nuestro viewholder
             convertView = LayoutInflater.from(context).inflate(layout, null);

            viewHolder = new ViewHolder();
            viewHolder.name = convertView.findViewById(R.id.text_view_name);
            viewHolder.description = convertView.findViewById(R.id.text_view_description);
            viewHolder.image = convertView.findViewById(R.id.image_view_fruit);
            viewHolder.quantity = convertView.findViewById(R.id.textViewQuantity);

            convertView.setTag(viewHolder);

        }else{
            //Obtenemos la referencia, reciclamos su uso sin necesidad de buscar de nuevo

            viewHolder = (ViewHolder) convertView.getTag();
        }


        //Obtenemos el objeto actual
        final Fruit currentFruit = getItem(position);
        viewHolder.name.setText(currentFruit.getName());
        viewHolder.description.setText(currentFruit.getDescription());
        viewHolder.quantity.setText(currentFruit.getQuantity());
        viewHolder.image.setImageResource(currentFruit.getImage());
        viewHolder.icon.setImageResource(currentFruit.getIcon());


        //Retornamos el Objeto fruta con los datos correspondientes
        return convertView;
    }


    static class ViewHolder{
        private TextView name, description, quantity;
        private ImageView icon, image;
    }
}
