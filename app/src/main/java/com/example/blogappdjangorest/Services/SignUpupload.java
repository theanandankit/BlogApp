package com.example.blogappdjangorest.Services;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.blogappdjangorest.Models.model.SignupFirestoreModel;
import com.example.blogappdjangorest.activities.LoginScreen;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class SignUpupload {

    OnSuccess onSuccess;
    Context context;

    public SignUpupload(OnSuccess onSuccess)
    {
        this.onSuccess=onSuccess;
    }


    public void upload(SignupFirestoreModel signupFirestoreModel)
    {

        DocumentReference documentReference=FirebaseFirestore.getInstance().collection("user").document(signupFirestoreModel.getEmail());

        documentReference.set(signupFirestoreModel).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

                onSuccess.success();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }

    public void exist(String email)
    {
        DocumentReference documentReference=FirebaseFirestore.getInstance().collection("user").document(email);

        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                if (value.exists())
                {
                    onSuccess.exist(true);
                }
                else
                {
                    onSuccess.exist(false);
                }

            }
        });
    }


    public interface OnSuccess
    {
        void success();
        void exist(boolean value);
    }

}
