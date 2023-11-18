package com.example.lab7;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.lab7.databinding.ActivityMainBinding;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.helper.widget.MotionEffect;

import com.example.lab7.databinding.ActivityMainBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding mainBinding;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    /// usuarios :  usuarioB@gmail.com
    // usuarios : gestorA@gmail.com

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        // Inicializa Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        // Inicializa Firestore
        db = FirebaseFirestore.getInstance();

        mainBinding.loginButton.setOnClickListener(v -> loginUser());
    }

    private void loginUser() {
        String email = mainBinding.email.getText().toString().trim();
        String password = mainBinding.password.getText().toString().trim();
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, signInTask -> {
            if (signInTask.isSuccessful()) {
                Log.d(TAG, "signInWithEmail:success");
                Query query = db.collection("usuarios").whereEqualTo("autHID", mAuth.getCurrentUser().getUid());
                query.get().addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        QuerySnapshot querySnapshot = task.getResult();
                        if (!querySnapshot.isEmpty()) {
                            DocumentSnapshot document = querySnapshot.getDocuments().get(0);
                            String rol = document.getString("rol");
                            if ("cliente".equals(rol)) {
                                navigateToActivity(Cliente.class);
                            } else if ("Gestor de Salón".equals(rol)) {
                                navigateToActivity(VistaActivity.class);
                            }

                        } else {
                            Log.d(MotionEffect.TAG, "No such document");
                        }
                    } else {
                        Log.d(MotionEffect.TAG, "get failed with ", task.getException());
                    }
                });


            }
        });

    }
    private void navigateToActivity(Class<?> destinationClass) {
        Intent intent = new Intent(MainActivity.this, destinationClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }




}
