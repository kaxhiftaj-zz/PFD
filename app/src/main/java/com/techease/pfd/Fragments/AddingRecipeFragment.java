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
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.techease.pfd.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;


public class AddingRecipeFragment extends Fragment implements View.OnClickListener {

    ImageView imageView,btnAddNewetIng,btnAddNewetIns,ivCross;
    TextView tvTitle,tvIngredients,tvInstructions,tvTags,tvTime;
    EditText etTitle,etIngredients,etInstructions,etTime;
    Button   btnTag1,btnTag2,btnTag3,btnTag4,btnTag5,btnSubmitRecipe;
    Typeface typeface,typeface2;
    LinearLayout InstructionLayout,IngredientsLayout;
    FrameLayout frameLayoutInstruction,frameLayoutIngredients;
    ArrayList<EditText> ingredientList = new ArrayList();
    ArrayList<EditText> instructionList = new ArrayList<>();
    ArrayList<ImageView> ingredientImageViewList = new ArrayList();
    ArrayList<ImageView> instructionImageViewList = new ArrayList<>();
     int hint=0;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int REQUEST_TAKE_PHOTO=2;
    final CharSequence[] items = { "Take Photo", "Choose from Library","Cancel" };
    String strIngredients,strInstructions;
    EditText customEditText;
    ImageView customImageView;
      @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_adding_recipe, container, false);

        InstructionLayout=(LinearLayout) view.findViewById(R.id.parentLayoutInstructions);
        IngredientsLayout=(LinearLayout) view.findViewById(R.id.parentLayoutingredients);
        frameLayoutIngredients=(FrameLayout)view.findViewById(R.id.frameLayoutIngredients);
        frameLayoutInstruction=(FrameLayout)view.findViewById(R.id.frameLayoutInstructions);
        typeface=Typeface.createFromAsset(getActivity().getAssets(),"font/brandon_blk.otf");
        typeface2=Typeface.createFromAsset(getActivity().getAssets(),"font/brandon_reg.otf");
        tvTitle=(TextView)view.findViewById(R.id.tvTitle);
        imageView=(ImageView)view.findViewById(R.id.ivAddingImages);
        ivCross=(ImageView)view.findViewById(R.id.ivCross);
        tvTime=(TextView)view.findViewById(R.id.tvTime);
        tvInstructions=(TextView)view.findViewById(R.id.tvInstructions);
        tvIngredients=(TextView)view.findViewById(R.id.tvIngredients);
        tvTags=(TextView)view.findViewById(R.id.tvTags);
        etTitle=(EditText)view.findViewById(R.id.etTitle);
        etIngredients=(EditText)view.findViewById(R.id.etIngredients);
        etInstructions=(EditText)view.findViewById(R.id.etInstrunctions);
          btnAddNewetIng=(ImageView)view.findViewById(R.id.btnAddIng);
          btnAddNewetIns=(ImageView)view.findViewById(R.id.btnAddIns);
          instructionList.add(etInstructions);
          ingredientList.add(etIngredients);
          ingredientImageViewList.add(btnAddNewetIng);
          instructionImageViewList.add(btnAddNewetIns);
        etTime=(EditText)view.findViewById(R.id.etTime);
        btnTag1=(Button)view.findViewById(R.id.btnTag1);
        btnTag2=(Button)view.findViewById(R.id.btnTag2);
        btnTag3=(Button)view.findViewById(R.id.btnTag3);
        btnTag4=(Button)view.findViewById(R.id.btnTag4);
        btnTag5=(Button)view.findViewById(R.id.btnTag5);
        btnSubmitRecipe=(Button)view.findViewById(R.id.btnSubmitRecipe);

        tvTags.setTypeface(typeface);
        tvTitle.setTypeface(typeface);
        tvIngredients.setTypeface(typeface);
        tvInstructions.setTypeface(typeface);
        tvTime.setTypeface(typeface);
        etTime.setTypeface(typeface2);
        etInstructions.setTypeface(typeface2);
        etIngredients.setTypeface(typeface2);
        etTitle.setTypeface(typeface2);
        btnSubmitRecipe.setTypeface(typeface);
        btnTag1.setTypeface(typeface);
        btnTag2.setTypeface(typeface);
        btnTag3.setTypeface(typeface);
        btnTag4.setTypeface(typeface);
        btnTag5.setTypeface(typeface);

        imageView.setOnClickListener(new View.OnClickListener() {
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

          etInstructions.addTextChangedListener(new TextWatcher() {
              @Override
              public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

              }

              @Override
              public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

              }

              @Override
              public void afterTextChanged(Editable editable) {
                  if (editable.length() < 1) {
                      btnAddNewetIns.setVisibility(View.INVISIBLE);
                  } else {
                      btnAddNewetIns.setVisibility(View.VISIBLE);
                  }
              }
          });


          etIngredients.addTextChangedListener(new TextWatcher() {
              @Override
              public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

              }

              @Override
              public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

              }

              @Override
              public void afterTextChanged(Editable editable) {
                  if (editable.length() < 1) {
                      btnAddNewetIng.setVisibility(View.INVISIBLE);
                  } else {
                      btnAddNewetIng.setVisibility(View.VISIBLE);
                  }
              }
          });

          btnAddNewetIng.setOnClickListener(this);
          btnAddNewetIns.setOnClickListener(this);
          btnTag1.setOnClickListener(this);
          btnTag2.setOnClickListener(this);
          btnTag3.setOnClickListener(this);
          btnTag4.setOnClickListener(this);
          btnTag5.setOnClickListener(this);




        btnSubmitRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        ivCross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                imageView.setImageResource(R.drawable.img);
                ivCross.setVisibility(View.GONE);
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
        ivCross.setVisibility(View.VISIBLE);
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

    @Override
    public void onClick(View v) {
        switch(v.getId()){

            case R.id.btnAddIng:
                btnAddNewetIng.setVisibility(View.INVISIBLE);
                addEditTextForIngredients();
                break;
            case R.id.btnAddIns:
                btnAddNewetIns.setVisibility(View.INVISIBLE);
                addEditTextForInstructions();
                break;
        }
    }

    private void addEditTextForInstructions() {
        instructionImageViewList.get(instructionImageViewList.size() - 1).setVisibility(View.INVISIBLE);
        final FrameLayout frameLayout = new FrameLayout(getActivity());
        frameLayout.setLayoutParams(frameLayoutInstruction.getLayoutParams());
        frameLayout.setTag(instructionList.size());
       EditText editText = new EditText(getActivity());
        editText.setLayoutParams(etInstructions.getLayoutParams());
        editText.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.edittext_back));
        editText.setHint("Add Instruction");
        editText.setTypeface(typeface2);
        hint++;
        editText.setPadding(etInstructions.getPaddingLeft(), 0, 0, 0);
        frameLayout.addView(editText);
        final ImageView imageView = new ImageView(getActivity());
        imageView.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.delete));
        imageView.setLayoutParams(btnAddNewetIns.getLayoutParams());
        frameLayout.addView(imageView);
        imageView.setTag(0);
        imageView.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.delete));
        instructionImageViewList.add(imageView);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() < 1) {
                    imageView.setVisibility(View.VISIBLE);
                    imageView.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.delete));
                    imageView.setTag(0);
                } else {
                    imageView.setVisibility(View.VISIBLE);
                    imageView.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.plus));
                    imageView.setTag(1);
                }

            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((int) (imageView.getTag()) == 1) {
                    addEditTextForInstructions();
                } else {
                    InstructionLayout.removeView(frameLayout);
                    instructionList.remove((int) (frameLayout.getTag()));
                    instructionImageViewList.remove((int) (frameLayout.getTag()));
                    instructionImageViewList.get(instructionImageViewList.size() - 1).setVisibility(View.VISIBLE);

                }
            }
        });
        InstructionLayout.addView(frameLayout);
        instructionList.add(editText);

    }
    public void takeDataFromFields() {
        strIngredients = "";
        strInstructions = "";
        for (EditText etIngred : ingredientList) {
            strIngredients += etIngred.getText().toString() + ",";
        }
        for (EditText etInstruc : instructionList) {
            strInstructions += etInstruc.getText().toString() + ",";
        }
        strIngredients = strIngredients.substring(0, strIngredients.length() - 1);
        strInstructions = strInstructions.substring(0, strInstructions.length() - 1);

    }

    private void addEditTextForIngredients() {
        ingredientImageViewList.get(ingredientImageViewList.size() - 1).setVisibility(View.INVISIBLE);
        final FrameLayout frameLayout = new FrameLayout(getActivity());
        frameLayout.setLayoutParams(frameLayoutIngredients.getLayoutParams());
        frameLayout.setTag(ingredientList.size());
        EditText  editText = new EditText(getActivity());
        editText.setLayoutParams(etIngredients.getLayoutParams());
        editText.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.edittext_back));
        editText.setHint("Add Ingredient");
        editText.setTypeface(typeface2);
        editText.setPadding(etIngredients.getPaddingLeft(), 0, 0, 0);
        frameLayout.addView(editText);
        final ImageView imageView = new ImageView(getActivity());
        imageView.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.delete));
        imageView.setLayoutParams(btnAddNewetIng.getLayoutParams());
        frameLayout.addView(imageView);
        imageView.setTag(0);
        imageView.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.delete));
        ingredientImageViewList.add(imageView);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() < 1) {
                    imageView.setVisibility(View.VISIBLE);
                    imageView.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.delete));
                    imageView.setTag(0);
                } else {
                    imageView.setVisibility(View.VISIBLE);
                    imageView.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.plus));
                    imageView.setTag(1);
                }

            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((int) (imageView.getTag()) == 1) {
                    addEditTextForIngredients();
                } else {
                    IngredientsLayout.removeView(frameLayout);
                    ingredientList.remove((int) (frameLayout.getTag()));
                    ingredientImageViewList.remove((int) (frameLayout.getTag()));
                    ingredientImageViewList.get(ingredientImageViewList.size() - 1).setVisibility(View.VISIBLE);

                }
            }
        });
        IngredientsLayout.addView(frameLayout);
        ingredientList.add(editText);
    }

}
