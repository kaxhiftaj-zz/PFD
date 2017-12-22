package com.techease.pfd.Fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputFilter;
import android.text.InputType;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.techease.pfd.R;


public class AddingRecipeFragment extends Fragment {

    TextView tvTitle,tvIngredients,tvInstructions,tvTags,tvTime;
    EditText etTitle,etIngredients,etInstructions,etTime;
    Button   btnTag1,btnTag2,btnTag3,btnTag4,btnTag5,btnAddImage,btnSubmitRecipe,btnAddNewetIng,btnAddNewetIns;
    Typeface typeface,typeface2;
    LinearLayout parentLayout,parentLayout2;
     int hint=2;
     int hint2=2;
      @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_adding_recipe, container, false);

        parentLayout=(LinearLayout)view.findViewById(R.id.parentLayout);
        parentLayout2=(LinearLayout)view.findViewById(R.id.parentLayout2);
        typeface=Typeface.createFromAsset(getActivity().getAssets(),"font/brandon_blk.otf");
        typeface2=Typeface.createFromAsset(getActivity().getAssets(),"font/brandon_reg.otf");
        tvTitle=(TextView)view.findViewById(R.id.tvTitle);
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

                createEditTextViewIns();

            }
        });

        btnAddNewetIng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                createEditTextViewIng();
            }
        });




          return view;
    }

    private void createEditTextViewIng() {
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams (
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        params.setMargins(0,10,0,10);
        EditText editText = new EditText(getActivity());
        int maxLength = 5;
        hint++;
        editText.setHint("Add Ingredients "+hint);
        editText.setLayoutParams(params);
        editText.setHeight(50);
        editText.setTypeface(typeface2);
        editText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.delete, 0);
        editText.setPadding(12,0,12,0);
        editText.setBackgroundResource(R.drawable.edittext_back);
        editText.setInputType(InputType.TYPE_CLASS_TEXT);
        editText.setTextSize(TypedValue.COMPLEX_UNIT_SP,17);
        editText.setId(hint);
        InputFilter[] fArray = new InputFilter[1];
        fArray[0] = new InputFilter.LengthFilter(maxLength);
        editText.setFilters(fArray);
        parentLayout2.addView(editText);
    }

    private void createEditTextViewIns() {
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams (
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        params.setMargins(0,10,0,10);
        EditText edittTxt = new EditText(getActivity());
        int maxLength = 5;
        hint2++;
        edittTxt.setHint("Add Instruction "+hint2);
        edittTxt.setLayoutParams(params);
        edittTxt.setHeight(50);
        edittTxt.setTypeface(typeface2);
        edittTxt.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.delete, 0);
        edittTxt.setPadding(12,0,12,0);
        edittTxt.setBackgroundResource(R.drawable.edittext_back);
        edittTxt.setInputType(InputType.TYPE_CLASS_TEXT);
        edittTxt.setTextSize(TypedValue.COMPLEX_UNIT_SP,17);
        edittTxt.setId(hint2);
        InputFilter[] fArray = new InputFilter[1];
        fArray[0] = new InputFilter.LengthFilter(maxLength);
        edittTxt.setFilters(fArray);
        parentLayout.addView(edittTxt);
    }
}
