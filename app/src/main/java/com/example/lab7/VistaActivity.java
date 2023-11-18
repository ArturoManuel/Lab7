package com.example.lab7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.lab7.Entity.Clientes;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class VistaActivity extends AppCompatActivity {

    private FirebaseFirestore db;
    private List<Clientes> clientes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista);
        db = FirebaseFirestore.getInstance();

        db.collection("usuarios").addSnapshotListener((collection, error) -> {
            if (error != null) {
                Log.d("lectura", "Error listening for document changes.");
                return;
            }
            if (collection != null && !collection.isEmpty()) {
                for (QueryDocumentSnapshot document : collection) {
                    Clientes cliente = document.toObject(Clientes.class);
                    if (cliente.getRol().equals("cliente")){
                        clientes.add(cliente);
                    }

                }

            }
        });




    }



}