package com.techease.pfd.Fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.text.InputFilter;
import android.text.InputType;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.techease.pfd.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.app.Activity.RESULT_OK;


public class AddingRecipeFragment extends Fragment {

    ImageView imageView;
    TextView tvTitle,tvIngredients,tvInstructions,tvTags,tvTime;
    EditText etTitle,etIngredients,etInstructions,etTime;
    Button   btnTag1,btnTag2,btnTag3,btnTag4,btnTag5,btnAddImage,btnSubmitRecipe,btnAddNewetIng,btnAddNewetIns;
    Typeface typeface,typeface2;
    LinearLayout parentLayout,parentLayout2;
     int hint=0;
     int hint2=0;
     int ivId=0;
     int ivId2=0;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int REQUEST_TAKE_PHOTO=2;
    final CharSequence[] items = { "Take Photo", "Choose from Library","Cancel" };
    String mCurrentPhotoPath;
    EditText customEditText;
    ImageView customImageView;
      @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_adding_recipe, container, false);

        parentLayout=(LinearLayout) view.findViewById(R.id.parentLayout);
        parentLayout2=(LinearLayout) view.findViewById(R.id.parentLayout2);
        typeface=Typeface.createFromAsset(getActivity().getAssets(),"font/brandon_blk.otf");
        typeface2=Typeface.createFromAsset(getActivity().getAssets(),"font/brandon_reg.otf");
        tvTitle=(TextView)view.findViewById(R.id.tvTitle);
        imageView=(ImageView)view.findViewById(R.id.ivAddingImages);
        tvTime=(TextView)view.findViewById(R.id.tvTime);
        tvInstructions=(TextView)view.findViewById(R.id.tvInstructions);
        tvIngredients=(TextView)view.findViewById(R.id.tvIngredients);
        tvTags=(TextView)view.findViewById(R.id.tvTags);
        etTitle=(EditText)view.findViewById(R.id.etTitle);
        etIngredients=(EditText)view.findViewById(R.id.etIngredients);
        etInstructions=(EditText)view.findViewById(R.id.etInstrunctions);
        etTime=(EditText)view.findViewById(R.id.etTime);
        btnTag1=(Button)view.findViewById(R.id.btnTag1);
        btnTag2=(Button)view.findViewById(R.id.btnTag2);
        btnTag3=(Button)view.findViewById(R.id.btnTag3);
        btnTag4=(Button)view.findViewById(R.id.btnTag4);
        btnTag5=(Button)view.findViewById(R.id.btnTag5);
        btnSubmitRecipe=(Button)view.findViewById(R.id.btnSubmitRecipe);
        btnAddImage=(Button)view.findViewById(R.id.btnAddImage);
        btnAddNewetIng=(Button)view.findViewById(R.id.btnAddIng);
        btnAddNewetIns=(Button)view.findViewById(R.id.btnAddIns);

        tvTags.setTypeface(typeface);
        tvTitle.setTypeface(typeface);
        tvIngredients.setTypeface(typeface);
        tvInstructions.setTypeface(typeface);
        tvTime.setTypeface(typeface);
        etTime.setTypeface(typeface2);
        etInstructions.setTypeface(typeface2);
        etIngredients.setTypeface(typeface2);
        etTitle.setTypeface(typeface2);
        btnAddImage.setTypeface(typeface);
        btnSubmitRecipe.setTypeface(typeface);
        btnTag1.setTypeface(typeface);
        btnTag2.setTypeface(typeface);
        btnTag3.setTypeface(typeface);
        btnTag4.setTypeface(typeface);
        btnTag5.setTypeface(typeface);

        btnAddNewetIns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                LayoutInflater inflater = (LayoutInflater)getActivity(). getSystemService(getActivity().LAYOUT_INFLATER_SERVICE);
//                View view1 = inflater.inflate(R.layout.custom_edittext, null, false);
//                parentLayout.addView(view1);
//                customEditText=(EditText)view1.findViewById(R.id.etCustomEt);
//                customImageView=(ImageView)view1.findViewById(R.id.ivDelete);
//                customImageView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        customEditText.setVisibility(View.GONE);
//                        customImageView.setVisibility(View.GONE);
//                        parentLayout.removeView(customEditText);
//                    }
//                });

                String EmptyOrNot=etInstructions.getText().toString();
                if (EmptyOrNot.equals(""))
                {
                    etInstructions.setError("First add an instruction");
                }
                else
                createEditTextViewIns();

            }
        });

        btnAddNewetIng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String EmptyOrNot=etIngredients.getText().toString();
                if (EmptyOrNot.equals(""))
                {
                    etIngredients.setError("First add an ingredients");
                }
                else
                    createEditTextViewIng();
            }
        });

        btnAddImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Add Photo!");
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if (items[which].equals("Take Photo"))
                        {
                            callCamera();
                        }
                        else if (items[which].equals("Choose from Library"))
                        {
                            callGallery();
                        }
                        else if (items[which].equals("Cancel"))
                        {
                            dialog.dismiss();
                        }

                    }
                });
                builder.show();
            }
        });

        btnSubmitRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String test=customEditText.getText().toString();
                Toast.makeText(getActivity(), test, Toast.LENGTH_SHORT).show();
            }
        });


          return view;
    }

    private void callGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_PICK);
        //intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"),REQUEST_TAKE_PHOTO);
    }

    private void callCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {

            onCaptureImageResult(data);
        }
        else if (requestCode==REQUEST_TAKE_PHOTO)
        {
            onSelectFromGalleryResult(data);

        }
    }

    private void onCaptureImageResult(Intent data) {

        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        File destination = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".jpg");
        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        imageView.setImageBitmap(thumbnail);
    }

    private void onSelectFromGalleryResult(Intent data) {

//        InputStream stream;
//        try {
//            stream = getActivity().getContentResolver().openInputStream(data.getData());
//            Bitmap realImage = BitmapFactory.decodeStream(stream);
//            imageView.setImageBitmap(realImage);
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
        Bitmap bm=null;
        if (data != null) {
            try {
                bm = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), data.getData());
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(getActivity(), e.getCause().toString(), Toast.LENGTH_SHORT).show();
            }
            imageView.setImageBitmap(bm);
        }
    }

    private void createEditTextViewIng() {
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams (
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        params.setMargins(0,10,0,10);
        final EditText editText = new EditText(getActivity());
        final ImageView imageView=new ImageView(getActivity());
        imageView.setImageResource(R.drawable.delete);
        imageView.setPadding(250,0,0,0);
        int maxLength = 5;
        hint++;
        ivId2++;
        editText.setHint("Add Ingredients "+hint);
        editText.setLayoutParams(params);
      //  editText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.delete, 0);
        editText.setHeight(50);
        editText.setTypeface(typeface2);
        editText.setPadding(12,0,12,0);
        editText.setBackgroundResource(R.drawable.edittext_back);
        editText.setInputType(InputType.TYPE_CLASS_TEXT);
        editText.setTextSize(TypedValue.COMPLEX_UNIT_SP,17);
        editText.setId(hint);
        imageView.setId(ivId2);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentLayout.removeView(editText);
                parentLayout.removeView(imageView);
            }
        });
        InputFilter[] fArray = new InputFilter[1];
        fArray[0] = new InputFilter.LengthFilter(maxLength);
        editText.setFilters(fArray);
        parentLayout2.addView(editText);

    }

    private void createEditTextViewIns() {
        final FrameLayout.LayoutParams params = new FrameLayout.LayoutParams (
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.WRAP_CONTENT);
     //   params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        params.setMargins(0,10,10,10);
        final EditText edittTxt = new EditText(getActivity());
        final ImageView imageView=new ImageView(getActivity());
        imageView.setImageResource(R.drawable.delete);
        imageView.setPadding(250,0,0,0);
        int maxLength = 5;
        hint2++;
        ivId++;
        edittTxt.setHint("Add Instruction "+hint2);
        edittTxt.setLayoutParams(params);
        edittTxt.setHeight(50);
        edittTxt.setTypeface(typeface2);
      //  edittTxt.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.delete, 0);
        edittTxt.setPadding(12,0,12,0);
        edittTxt.setBackgroundResource(R.drawable.edittext_back);
        edittTxt.setInputType(InputType.TYPE_CLASS_TEXT);
        edittTxt.setTextSize(TypedValue.COMPLEX_UNIT_SP,17);
        edittTxt.setId(hint2);
        imageView.setId(ivId);
        InputFilter[] fArray = new InputFilter[1];
        fArray[0] = new InputFilter.LengthFilter(maxLength);
        edittTxt.setFilters(fArray);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentLayout.removeView(edittTxt);
                parentLayout.removeView(imageView);
            }
        });
        parentLayout.addView(edittTxt);
        parentLayout.addView(imageView);
    }


}
