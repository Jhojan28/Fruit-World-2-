package io.github.alexlondon07.fruitworld2.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.github.alexlondon07.fruitworld2.R;
import io.github.alexlondon07.fruitworld2.adapters.FruitAdapter;
import io.github.alexlondon07.fruitworld2.models.Fruit;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private String TAG = "MainActivity";

    private ListView listView;
    private FruitAdapter adapterListView;
    private RecyclerView.LayoutManager layoutManager;
    private Fruit fruit;

    //Listado de Frutas
    private List<Fruit> fruitList;

    //Menú de opciones Grid / List
    private MenuItem itemListView;

    //Variables
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //Obtemos la Lista de frutas
        this.fruitList = getAllFruits();

        this.listView =  findViewById(R.id.activity_main_listView);

        //Adjuntamos el evento click
        this.listView.setOnItemClickListener(this);

        this.adapterListView = new FruitAdapter(this, R.layout.list_view_item_fruit, fruitList);

        this.listView.setAdapter(adapterListView);

        //Registramos el context menú
        registerForContextMenu(this.listView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Infamos el opcion menu con nuestro layout
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        this.itemListView = menu.findItem(R.id.activity_main_listView);
        return true;
    }

    /**
     * Metodo para obtener el listado de las frutas
     * @return
     */
    private List<Fruit> getAllFruits() {

        List<Fruit> list = new ArrayList<Fruit>(){{
            add(new Fruit("Apple", "\"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.", R.drawable.apple_bg,  R.drawable.pear_ic, 0));
            add(new Fruit("Banana", "Description Banana", R.drawable.banana_bg,  R.drawable.banana_ic, 0));
            add(new Fruit("Cherry", "Description Cherry", R.drawable.cherry_bg,  R.drawable.cherry_ic, 0));
            add(new Fruit("Orange", "Description Orange", R.drawable.orange_bg,  R.drawable.orange_ic, 0));
            add(new Fruit("Raspberry", "Description Raspberry", R.drawable.raspberry_bg,  R.drawable.raspberry_ic, 0));
            add(new Fruit("Strawberry", "Description Strawberry", R.drawable.strawberry_bg,  R.drawable.strawberry_ic, 0));
        }};

        return list;
    }

    /**
     * Eventos para los click en el botón  Agregar
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.add_fruit:
                this.addFruit(new Fruit("Added New Fruit nº " +(++counter),  "Unknown Description", R.drawable.plum_bg, R.drawable.plum_ic, 1));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        this.clickFruit(fruitList.get(position));
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        super.onCreateContextMenu(menu, v, menuInfo);

        // Inflamos el context menu con nuestro layout
        MenuInflater inflater = getMenuInflater();

        // Antes de inflar, le añadimos el header dependiendo del objeto que se halla clickeado
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        menu.setHeaderTitle(this.fruitList.get(info.position).getName());
        menu.setHeaderIcon(this.fruitList.get(info.position).getIcon());

        // Inflamos
        inflater.inflate(R.menu.context_menu_fruits, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        // Obtenemos info en el context menu del objeto que se pinche
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()) {
            case R.id.delete_fruit:
                this.deleteFruit(info.position);
                return true;

            case R.id.reset_quantity:
                 this.resetQuantityFruit(fruitList.get(info.position));
                 return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void resetQuantityFruit(Fruit fruit) {
        fruit.resetQuantity();
        this.adapterListView.notifyDataSetChanged();
    }

    /**
     * Metódo para agregar frutas
     * @param fruit
     */
    private void addFruit(Fruit fruit){
        this.fruitList.add(fruit);
        //Notificamos el cambio en ambos adaptadores
        this.adapterListView.notifyDataSetChanged();
    }

    /**
     * Metódo para eliminar frutas
     * @param position
     */
    private void deleteFruit(int position){
        this.fruitList.remove(position);
        //Notificamos el cambio en ambos adaptadores
        this.adapterListView.notifyDataSetChanged();
    }


    /**
     * Metodo para mostrar un mensaje el Usuario del elemento selecionado
     * @param fruit
     */
    private void clickFruit(Fruit fruit) {

        //Incrementamos la cantidad de la fruta seleccionada
        fruit.addQuantity(1);

        //Notificamos que el elemento ha cambiando
        this.adapterListView.notifyDataSetChanged();

        /*if(fruit.getQuantity() == fruit.LIMIT_QUANTITY){
            TextView textElement = findViewById(R.id.text_view_quantity);
            textElement.setTextColor(getResources().getColor(R.color.colorAccent)); //this is green color
        }*/

        if(fruit.getQuantity() >= fruit.LIMIT_QUANTITY){
            Toast.makeText(this, "The amount cannot exceed " + fruit.LIMIT_QUANTITY, Toast.LENGTH_SHORT).show();
        }
    }
}
