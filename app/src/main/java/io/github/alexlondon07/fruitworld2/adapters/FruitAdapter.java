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
            viewHolder.textViewName = convertView.findViewById(R.id.text_view_name);
            viewHolder.textViewDescription = convertView.findViewById(R.id.text_view_description);
            viewHolder.textViewQuantity = convertView.findViewById(R.id.text_view_quantity);
            viewHolder.imageViewImage = convertView.findViewById(R.id.image_view_fruit);
            convertView.setTag(viewHolder);

        }else{
            //Obtenemos la referencia, reciclamos su uso sin necesidad de buscar de nuevo
            viewHolder = (ViewHolder) convertView.getTag();
        }


        //Obtenemos el objeto actual
        final Fruit currentFruit = getItem(position);
        viewHolder.textViewName.setText(currentFruit.getName());
        viewHolder.textViewDescription.setText(currentFruit.getDescription());
        viewHolder.textViewQuantity.setText(Integer.toString(currentFruit.getQuantity()));
        viewHolder.imageViewImage.setImageResource(currentFruit.getImage());

        //Retornamos el Objeto fruta con los datos correspondientes
        return convertView;
    }

    static class ViewHolder{
        private TextView textViewName, textViewDescription, textViewQuantity;
        private ImageView imageViewImage;
    }
}
