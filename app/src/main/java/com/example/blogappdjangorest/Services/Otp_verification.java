package com.example.blogappdjangorest.Services;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.chaos.view.PinView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class Otp_verification extends AppCompatDialogFragment {

    private Context mcontext;
    PinView pinView;
    private String verificationid;
    private String code;
    private FirebaseAuth mauth;
    OnSuccess onSuccess;

    public void verify(String number, Context context, PinView pinView,OnSuccess onSuccess) {
        mcontext = context;
        this.pinView = pinView;
        this.onSuccess=onSuccess;

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                number,
                60,
                TimeUnit.SECONDS,
                TaskExecutors.MAIN_THREAD,
                mcallback
        );


        setpintext();
    }
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mcallback = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
            code = phoneAuthCredential.getSmsCode();
            pinView.setText(code);

        }

        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            verificationid = s;
            Log.e("Code", s);
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {

            Toast.makeText(mcontext, e.getMessage(), Toast.LENGTH_LONG).show();

        }
    };

    public void setpintext() {
        pinView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                if (s.toString().length() == 6) {

                    check(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void verify_manually() {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationid, code);
            signin(credential, mcontext);
    }

    private void signin(PhoneAuthCredential credential, final Context mcontext) {
        mauth = FirebaseAuth.getInstance();
        mauth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    onSuccess.completed();

                } else
                    Toast.makeText(mcontext, "something went wrong", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void check(String value) {
        if (value.equals(code)) {
            verify_manually();
        } else {
            Toast.makeText(mcontext, "PIN wrong", Toast.LENGTH_LONG).show();

        }
    }
    public interface OnSuccess
    {
       public void completed();
    }
    public void ii()
    {
        onSuccess.completed();
    }

}
