package com.hashem.workshopmidexam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.hashem.workshopmidexam.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ProductAdapter.OnAdapterActionListener , MyDialog.OnDialogActionListener {
    ActivityMainBinding binding;
    List<Product> products;
    ProductDao productDao;
    ProductAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        products = new ArrayList<>();

        productDao = ProductDatabase.getDatabase(this).productDao();


        ProductDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                products = productDao.getAllProducts();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter = new ProductAdapter(products,MainActivity.this);
                        binding.productsRv.setAdapter(adapter);
                        binding.productsRv.setLayoutManager(new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false));
                    }
                });

            }
        });

        binding.addProductBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String productName = binding.productNameEt.getText().toString();
                String productQuantity = binding.productQuantityEt.getText().toString();
                boolean productOffer = binding.offerCB.isChecked();

//                products.add(new Product(productName, productQuantity, productOffer, R.drawable.ic_product));
                ProductDatabase.databaseWriteExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        long result = productDao.insertProduct(new Product(productName, productQuantity, productOffer, R.drawable.ic_product));
                        products = productDao.getAllProducts();
                        Log.d("TAGgg", "run insert: "+result);
//                        Toast.makeText(MainActivity.this, ""+result, Toast.LENGTH_SHORT).show();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                adapter = new ProductAdapter(products,MainActivity.this);
                                binding.productsRv.setAdapter(adapter);
                                binding.productsRv.setLayoutManager(new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false));
                            }
                        });
                    }
                });
            }
        });

    }

    @Override
    public void onBackPressed() {
        MyDialog.newInstance("هل تريد تأكيد عملية الخروج",0,-2).show(getSupportFragmentManager(),"");
    }

    @Override
    public void onDelete(int pos) {
        MyDialog.newInstance("هل تود حذف المنتج؟",1,pos).show(getSupportFragmentManager(),"");
    }

    @Override
    public void onYesClick(int pos) {
        ProductDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
            int result =  productDao.deleteProduct(products.get(pos));
                products = productDao.getAllProducts();
                Log.d("TAGgg", "run delete: "+result);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter = new ProductAdapter(products,MainActivity.this);
                        binding.productsRv.setAdapter(adapter);
                        binding.productsRv.setLayoutManager(new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false));
                    }
                });
            }
        });
    }
}