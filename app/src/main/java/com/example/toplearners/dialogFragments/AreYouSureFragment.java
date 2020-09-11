package com.example.toplearners.dialogFragments;

import android.app.Dialog;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.toplearners.ApiInterface;
import com.example.toplearners.R;
import com.example.toplearners.SubmissionActivity;
import com.example.toplearners.model.Project;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class AreYouSureFragment extends DialogFragment {

    private static final String DIALOG = "AreYouSure";
    public static String BASE_URL ="https://docs.google.com/forms/d/e/";
    private String firstName;
    private String lastName;
    private String email;
    private String projectLink;

    

    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    public static Retrofit retrofit = builder.build();

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity())
                .inflate(R.layout.fragment_are_you_sure, null);

        ImageButton imageButton = view.findViewById(R.id.cancelBtn);
        Button yesButton = view.findViewById(R.id.yesBtn);
        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //dismiss();
                if (getArguments() != null) {
                    firstName = getArguments().getString("firstName");
                    lastName = getArguments().getString("lastName");
                    email = getArguments().getString("email");
                    projectLink = getArguments().getString("projectLink");
                }
                //Toast.makeText(getContext(), firstName + lastName + email + projectLink, Toast.LENGTH_SHORT).show();
                sendProject(firstName, lastName, email, projectLink);

            }
        });
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        AlertDialog dialog = new AlertDialog.Builder(getActivity())
                .setView(view)
                .create();
        return dialog;
    }

    private void sendProject(String fname, String lname, String email, String projectlink) {
        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        Call<ResponseBody> call = apiInterface.submitProject(
                fname,
                lname,
                email,
                projectlink
        );

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
                FragmentManager manager = getActivity().getSupportFragmentManager();
                SuccessDialogFragment dialog = new SuccessDialogFragment();
                dialog.show(manager, DIALOG);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                FragmentManager manager = getActivity().getSupportFragmentManager();
                UnSuccessfulDialogFragment dialog = new UnSuccessfulDialogFragment();
                dialog.show(manager, DIALOG);
            }
        });

    }


}