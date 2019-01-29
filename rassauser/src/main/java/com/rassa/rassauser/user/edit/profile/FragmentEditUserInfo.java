package com.rassa.rassauser.user.edit.profile;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.rassa.rassauser.R;
import com.rassa.rassauser.utils.ActivityCropImage;
import com.rassa.rassauser.utils.UserProfile;
import com.rassa.rassauser.webApi.register.registerUserInfo.model.ParamsRegister;
import com.rassa.rassauser.webApi.register.registerUserInfo.model.UserInfo;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FragmentEditUserInfo extends Fragment implements iVEditUserInfo {

    private OnFragmentInteractionListener mListener;

    private View view;
    private EditText editTextName, editTextLastName;
    private ImageView imageViewUserImg,camera;
    private Button buttonUserInfo;
    private TextView text_phone_number,error,title;
    private ProgressBar progressBar;
    private PEditUserInfo pUserInfo;
    private UserProfile userProfile;

    private static final int REQUEST_SELECT_IMAGE = 20;
    private Bitmap bitmapUserImage = null;
    private Uri uriUserImage;

    private String encodedImageData;

    private ParamsRegister paramsRegister;

    public FragmentEditUserInfo() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        pUserInfo = new PEditUserInfo(this);
        userProfile=new UserProfile(getContext());
        view = inflater.inflate(R.layout.rs_fragment_register_user_info, container, false);
        setView();
        return view;
    }

    private void setView(){
        title = view.findViewById(R.id.text_toolbar_title);
        title.setText("ویرایش اطلاعات کاربری");
        editTextName = view.findViewById(R.id.edit_text_name);
        editTextLastName = view.findViewById(R.id.edit_text_last_name);

        imageViewUserImg = view.findViewById(R.id.image_user_info_avatar);
        camera = view.findViewById(R.id.image_user_info_edit);
        text_phone_number = view.findViewById(R.id.text_phone_number);
        progressBar = view.findViewById(R.id.frg_registerUserInfo_progressBar);
        text_phone_number.setText(userProfile.getKeyPhoneNumber(""));

        editTextName.setText(userProfile.getKeyFirstName(""));
        editTextLastName.setText(userProfile.getKeyLastName(""));

        String imageUrl=userProfile.getKeyImgUrl("avatar");
        if(!imageUrl.isEmpty() || imageUrl != null){
            Picasso.get().load(imageUrl).error(R.drawable.ruser_empty_gray).placeholder(R.drawable.ruser_empty_gray).into(imageViewUserImg, new Callback() {
                @Override
                public void onSuccess() {

                }

                @Override
                public void onError(Exception e) {
                }
            });
        } else {
            Picasso.get().load(R.drawable.ruser_empty_gray).into(imageViewUserImg);
        }

        error = view.findViewById(R.id.text_error);
        imageViewUserImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(
                        new Intent(getContext(), ActivityCropImage.class).putExtra("KEY_HAS_ASPECT", false),
                        REQUEST_SELECT_IMAGE);
            }
        });
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(
                        new Intent(getContext(), ActivityCropImage.class).putExtra("KEY_HAS_ASPECT", false),
                        REQUEST_SELECT_IMAGE);
            }
        });

        buttonUserInfo = view.findViewById(R.id.frg_registerUserInfo_sendBtn);
        buttonUserInfo.setText("ویرایش اطلاعات");

        buttonUserInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateName()) {
                    return;
                }
                if (!validateLastName()) {
                    return;
                }

                InputMethodManager imm = (InputMethodManager) getContext().getSystemService(getContext().INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

                paramsRegister = new ParamsRegister();
                paramsRegister.setPhoneNumber(userProfile.getKeyPhoneNumber(""));
                paramsRegister.setEmail("");
                paramsRegister.setFirstName(editTextName.getText().toString());
                paramsRegister.setLastName(editTextLastName.getText().toString());
                paramsRegister.setImageUrl(encodedImageData);
                pUserInfo.setUserInfo(paramsRegister);

            }
        });

      /*  pUserInfo.getUserInfo(Utils.getStringPreference(getContext(),Utils.KEY_REGISTER,
                Utils.KEY_PHONENUMBER,"-1"));*/
    }

    private boolean validateName() {
        if (editTextName.getText().toString().trim().isEmpty()) {
            editTextName.setError("لطفا نام راوارد کنید");
            requestFocus(editTextName);
            return false;
        } else {
        }
        return true;
    }

    private boolean validateLastName() {
        if (editTextLastName.getText().toString().trim().isEmpty()) {
            editTextLastName.setError("لطفا نام خانوادگی راوارد کنید");
            requestFocus(editTextLastName);
            return false;
        } else {
        }
        return true;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

        }
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onStartSetUserInfo() {
        //mListener.onStartSetUserInfo();
        error.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        buttonUserInfo.setEnabled(false);
        if (mListener!=null) {
            mListener.onStartSetUserInfo();
        }
    }

    @Override
    public void setUserInfoSuccess(UserInfo userInfo) {
        if (mListener!=null) {
            mListener.setUserInfoSuccess();
        }
    }

    @Override
    public void setUserInfoFailed(String msg) {
        error.setText(msg);
        error.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        buttonUserInfo.setEnabled(true);

        if (mListener!=null) {
            mListener.setUserInfoFailed(msg);
        }
    }






    public interface OnFragmentInteractionListener {

        void onStartSetUserInfo();
        void setUserInfoSuccess();
        void setUserInfoFailed(String msg);
    }

    private  Uri getUri(Bitmap bitmap, String name) {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(new Date());
        File filesDir = getActivity().getFilesDir();
        File imageFile = new File(filesDir, timeStamp + "_" + name + ".jpg");
        OutputStream os;
        try {
            os = new FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
            os.flush();
            os.close();
        } catch (Exception e) {
        }
        return Uri.fromFile(imageFile);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_SELECT_IMAGE && resultCode== Activity.RESULT_OK){
            byte[] byteArray = data.getByteArrayExtra("image");
            Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            imageViewUserImg.setImageBitmap(bmp);
            uriUserImage=getUri(bmp,"userimage");
            bitmapUserImage=bmp;
            imageViewUserImg.buildDrawingCache();
            Bitmap bmap = imageViewUserImg.getDrawingCache();
            encodedImageData ="data:image/.*?;base64," + getEncoded64ImageStringFromBitmap(bmap);
        }
    }

    public String getEncoded64ImageStringFromBitmap(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
        byte[] byteFormat = stream.toByteArray();
        // get the base 64 string
        return Base64.encodeToString(byteFormat, Base64.NO_WRAP);
    }
}
