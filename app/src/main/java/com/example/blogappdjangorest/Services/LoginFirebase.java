package com.example.blogappdjangorest.Services;

import androidx.annotation.Nullable;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class LoginFirebase {

    LoginSuccess loginSuccess;

    public LoginFirebase(LoginSuccess loginSuccess)
    {
        this.loginSuccess=loginSuccess;
    }

    public void phone_get(String email)
    {
        DocumentReference documentReference= FirebaseFirestore.getInstance().collection("user").document(email);

        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                if (value.exists())
                {
                    loginSuccess.get_phone(String.valueOf(value.get("phone")));
                }
                else
                {
                    loginSuccess.get_phone("NA");
                }
            }
        });
    }
    public interface LoginSuccess
    {
        void get_phone(String Phone);
    }
}
