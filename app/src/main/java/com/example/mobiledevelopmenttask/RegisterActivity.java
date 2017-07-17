package com.example.mobiledevelopmenttask;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

public class RegisterActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    private EditText inputAadhar,inputName,inputPhone,inputDateofBirth,inputEmail,inputFathersFirstName,inputFathersLastName
            ,inputMothersFirstName,inputMothersLastName,inputCurrentAddress1,inputCurrentAddress2,inputCurrentPincode,inputCurrentCity
            ,inputCurrentDistrict,inputCurrentState,inputPermanetAddress1,inputPermanetAddress2,inputPermanetPincode,inputPermanetCity
            ,inputPermanetDistrict,inputPermanetState,inputMarks,inputDivisionGrade,inputMajorSubjects,inputFrom,inputTo,
            inputNatureOfPresentEmp,jobDescriptSpinner,inputTotalEmoluments,inputAdditionalInfo,inputRemarks,inputDate,inputFullName,inputSignature;
    private MaterialBetterSpinner religionSpinner,genderSpinner,martialStatusSpinner,examinationLevelSpinner,passingYearSpinner
            ,boardOrUniversitySpinner,castCategorySpinner,postHeldSpinner,officeInstOrgSpinner,scaleOfPaySpinner,natureOfDutiesSpinner
            ,exactNameOfPostSpinner,organisationNameSpinner,durationSpinner;
    private CheckBox isPermanentSameAsCurrent,registerTermsAndCondCheck ;
    private Button register;
    private String[] religiondata = {"Hindus","Muslims","Christians","Sikhs","Buddhists","Jains","Others"};
    private  String[] genderData = {"Male","Female","others"};
    private  String[] martialStatusData = {"Single","Married","Widowed"};
    private  String[] examLevelData = {"SSC","Intermediate","B.tech","Degree","M.tech","M.C.A","M.B.A"};
    private  String[] castCategoryData = {"OC","BC-A","BC-B","SC","ST","BC_D"};

    private  String[] postHeldData = {"Student","Faculty","Principal"};
    private  String[] boardData = {"JNTUK","GEETHAM","IIT-WARANGAL","JNTU-H","IIT-MADRAS","IIT-KANPUR"};
    private  String[] officeData = {"Student","Faculty","Principal"};
    private  String[] scaleOfPayData = {"10k","20k","30k","40k","50k","60k"};
    private  String[] natureOfDutiesData = {"Sales Manager","Software Engineer","Android Developer","Java developer",
            "Associate Manager","Project Manager"};
    private  String[] exactNamePostData = {"Software Engineer","Manager","CEO","Project Manager","Associate Manager"};
    private  String[] durationData = {"6 Months","1 Years","2 years","3 years","4 years","5 years"};
    private  String[] organisationData = {"Main Brain Tech","TCS","Infosys","HCL","IBM","CGI"};
    RegisterBean registerBean = new RegisterBean();
    private DatePickerDialog datePickerDialog;
    private int Year, Month, Day ;
    private  Calendar calendar ;
    private ArrayList<String> years = new ArrayList<String>();
    private  int thisYear = Calendar.getInstance().get(Calendar.YEAR);

    private int variable;
    private LinearLayout OpenGallery,OpenCamera;
    private int width,height;
    private AlertDialog alert;
    private  static  final  int CAMERA_REQUEST=654;
    private  static  final int GALLERY_REQUEST=566;
    Uri outputFileUri;
    public static File sdImageMainDirectory;
    private  ImageView profilePic;
    private  Bitmap imageBitmap;
    private TextView applicationRefNumber;
    private String referenceNumber;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_form);

        DisplayMetrics mertics=this.getResources().getDisplayMetrics();
        width=mertics.widthPixels;
        height=mertics.heightPixels;

        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        toolbar.setTitle(getResources().getString(R.string.register1));
        toolbar.setTitleTextColor(Color.WHITE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.getNavigationIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);
        toolbar.setNavigationOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                RegisterActivity.this.finish();
            }
        });
        calendar = Calendar.getInstance();

        Year = calendar.get(Calendar.YEAR) ;
        Month = calendar.get(Calendar.MONTH);
        Day = calendar.get(Calendar.DAY_OF_MONTH);

        // for taking pic
        File root = new File(Environment.getExternalStorageDirectory()
                + File.separator + "Profile"
                + File.separator);

        if (!root.exists())
        {
            root.mkdirs();
        } else {
            root.delete();
            root.mkdirs();
        }

        sdImageMainDirectory = new File(root, String.valueOf(System
                .currentTimeMillis()) + "frames.jpg");


        referenceNumber = generateRandomNumber();
        applicationRefNumber = (TextView) findViewById(R.id.application_ref_no);
        applicationRefNumber.setText(getResources().getText(R.string.application_ref_number)+"\n"+referenceNumber);

        inputAadhar = (EditText) findViewById(R.id.input_aadhar_number);
        inputName = (EditText) findViewById(R.id.input_name);
        inputPhone = (EditText) findViewById(R.id.input_phone);
        inputDateofBirth = (EditText) findViewById(R.id.input_date_of_birth);
        inputEmail = (EditText) findViewById(R.id.input_email);
        inputFathersFirstName = (EditText) findViewById(R.id.input_father_first_name);
        inputFathersLastName = (EditText) findViewById(R.id.input_father_last_name);
        inputMothersFirstName = (EditText) findViewById(R.id.input_mother_first_name);
        inputMothersLastName = (EditText) findViewById(R.id.input_mother_last_name);
        inputCurrentAddress1 = (EditText) findViewById(R.id.input_address1);
        inputCurrentAddress2 = (EditText) findViewById(R.id.input_address2);
        inputCurrentPincode = (EditText) findViewById(R.id.input_pincode);
        inputCurrentCity = (EditText) findViewById(R.id.input_city);
        inputCurrentDistrict = (EditText) findViewById(R.id.input_district);
        inputCurrentState = (EditText) findViewById(R.id.input_state);
        inputPermanetAddress1 = (EditText) findViewById(R.id.input_address1_permanent);
        inputPermanetAddress2 = (EditText) findViewById(R.id.input_address2_permanent);
        inputPermanetPincode = (EditText) findViewById(R.id.input_pincode_permanent);
        inputPermanetDistrict = (EditText) findViewById(R.id.input_district_permanent);
        inputPermanetCity = (EditText) findViewById(R.id.input_city_permanent);
        inputPermanetState = (EditText) findViewById(R.id.input_state_permanent);
        inputMarks = (EditText) findViewById(R.id.input_marks);
        inputDivisionGrade = (EditText) findViewById(R.id.input_division_grade_cgpa);
        inputMajorSubjects = (EditText) findViewById(R.id.input_major_subjects);
        inputFrom = (EditText) findViewById(R.id.input_from);
        inputTo = (EditText) findViewById(R.id.input_to);
        inputNatureOfPresentEmp = (EditText) findViewById(R.id.input_nature_of_present_emp);
        inputTotalEmoluments = (EditText) findViewById(R.id.input_totalemoluments);
        inputAdditionalInfo = (EditText) findViewById(R.id.input_addtional_info);
        inputRemarks = (EditText) findViewById(R.id.input_remarks);
        inputDate = (EditText) findViewById(R.id.input_date);
        inputFullName = (EditText) findViewById(R.id.input_full_name);
        inputSignature = (EditText) findViewById(R.id.input_signature);

        //spinners registration
        religionSpinner = (MaterialBetterSpinner) findViewById(R.id.religion_spinner);
        genderSpinner = (MaterialBetterSpinner) findViewById(R.id.gender_spinner);
        martialStatusSpinner = (MaterialBetterSpinner) findViewById(R.id.martial_status_spinner);
        examinationLevelSpinner = (MaterialBetterSpinner) findViewById(R.id.exam_level_spinner);
        passingYearSpinner = (MaterialBetterSpinner) findViewById(R.id.passing_year_spinner);
        boardOrUniversitySpinner = (MaterialBetterSpinner) findViewById(R.id.board_university_spinner);
        castCategorySpinner = (MaterialBetterSpinner) findViewById(R.id.cast_category_spinner);
        postHeldSpinner = (MaterialBetterSpinner) findViewById(R.id.post_held_spinner);
        officeInstOrgSpinner = (MaterialBetterSpinner) findViewById(R.id.office_institute_spinner);
        scaleOfPaySpinner = (MaterialBetterSpinner) findViewById(R.id.scale_of_pay_spinner);
        natureOfDutiesSpinner = (MaterialBetterSpinner) findViewById(R.id.nature_of_duties_spinner);
        exactNameOfPostSpinner = (MaterialBetterSpinner) findViewById(R.id.exact_name_of_post_spinner);
        organisationNameSpinner = (MaterialBetterSpinner) findViewById(R.id.organisation_name_spinner);
        durationSpinner = (MaterialBetterSpinner) findViewById(R.id.duration_spinner);
        jobDescriptSpinner = (EditText) findViewById(R.id.job_description_spinner);

        profilePic = (ImageView) findViewById(R.id.profile_pic);
        profilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCameraGallery();
            }
        });
        isPermanentSameAsCurrent = (CheckBox) findViewById(R.id.check_current_same_as_permanent);
        registerTermsAndCondCheck = (CheckBox) findViewById(R.id.registration_accept_terms_conds_check);
        isPermanentSameAsCurrent.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(b)
                {
                    inputPermanetAddress1.setText(inputCurrentAddress1.getText().toString().trim());
                    inputPermanetAddress2.setText(inputCurrentAddress2.getText().toString().trim());
                    inputPermanetPincode.setText(inputCurrentPincode.getText().toString().trim());
                    inputPermanetCity.setText(inputCurrentCity.getText().toString().trim());
                    inputPermanetDistrict.setText(inputCurrentDistrict.getText().toString().trim());
                    inputPermanetState.setText(inputCurrentState.getText().toString().trim());

                }
                else
                {
                    inputPermanetAddress1.setText(" ");
                    inputPermanetAddress2.setText(" ");
                    inputPermanetPincode.setText(" ");
                    inputPermanetCity.setText(" ");
                    inputPermanetDistrict.setText(" ");
                    inputPermanetState.setText(" ");
                }
            }
        });



        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, religiondata);
        religionSpinner.setAdapter(arrayAdapter);
        religionSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                registerBean.setReligion(religiondata[i]);
            }
        });


        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, genderData);
        genderSpinner.setAdapter(arrayAdapter1);
        genderSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                registerBean.setGender(genderData[i]);
            }
        });



        ArrayAdapter<String> martialAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, martialStatusData);
        martialStatusSpinner.setAdapter(martialAdapter);
        martialStatusSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                registerBean.setMartialStatus(martialStatusData[i]);
            }
        });


        for (int i = 1980; i <= thisYear; i++)
        {
            years.add(""+i);
        }
        ArrayAdapter<String> passingYearAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, years);
        passingYearSpinner.setAdapter(passingYearAdapter);
        passingYearSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                registerBean.setPassingYear(years.get(i));
            }
        });

        ArrayAdapter<String> examLevelAdapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, examLevelData);
        examinationLevelSpinner.setAdapter(examLevelAdapter1);
        examinationLevelSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                registerBean.setExaminationLevel(examLevelData[i]);
            }
        });

        ArrayAdapter<String> boardAdapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, boardData);
        boardOrUniversitySpinner.setAdapter(boardAdapter1);
        boardOrUniversitySpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                registerBean.setBoardOrUniversity(boardData[i]);
            }
        });

        ArrayAdapter<String> castCatAdapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, castCategoryData);
        castCategorySpinner.setAdapter(castCatAdapter1);
        castCategorySpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                registerBean.setCastCategory(castCategoryData[i]);
            }
        });
        ArrayAdapter<String> postHeldAdapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, postHeldData);
        postHeldSpinner.setAdapter(postHeldAdapter1);
        postHeldSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                registerBean.setPostHeld(postHeldData[i]);
            }
        });

        ArrayAdapter<String> officeAdapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, organisationData);
        officeInstOrgSpinner.setAdapter(officeAdapter1);
        officeInstOrgSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                registerBean.setOfficeInst(organisationData[i]);
            }
        });

        ArrayAdapter<String> scaleOfPayAdapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, scaleOfPayData);
        scaleOfPaySpinner.setAdapter(scaleOfPayAdapter1);
        scaleOfPaySpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                registerBean.setScaleOfPay(scaleOfPayData[i]);
            }
        });

        ArrayAdapter<String> natureOfDutiesAdapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, natureOfDutiesData);
        natureOfDutiesSpinner.setAdapter(natureOfDutiesAdapter1);
        natureOfDutiesSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                registerBean.setNatureOfDuties(natureOfDutiesData[i]);
            }
        });

        ArrayAdapter<String> exactNameOfPostAdapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, exactNamePostData);
        exactNameOfPostSpinner.setAdapter(exactNameOfPostAdapter1);
        examinationLevelSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                registerBean.setExamNameOfPost(exactNamePostData[i]);
            }
        });

        ArrayAdapter<String> orgAdapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, organisationData);
        organisationNameSpinner.setAdapter(orgAdapter1);
        organisationNameSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                registerBean.setOrganisationName(organisationData[i]);
            }
        });

        ArrayAdapter<String> durationAdapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, durationData);
        durationSpinner.setAdapter(durationAdapter1);
        durationSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                registerBean.setDuration(durationData[i]);
            }
        });
        datePickerDialog = DatePickerDialog.newInstance(RegisterActivity.this, Year, Month, Day);

        datePickerDialog.setThemeDark(false);

        datePickerDialog.showYearPickerFirst(false);

        datePickerDialog.setAccentColor(Color.parseColor("#009688"));

        datePickerDialog.setTitle("Select Date From DatePickerDialog");


        inputDateofBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show(getFragmentManager(), "DatePickerDialog");
                variable =1;

            }
        });
        inputFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show(getFragmentManager(), "DatePickerDialog");
                variable =2;

            }
        });
        inputTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show(getFragmentManager(), "DatePickerDialog");
                variable =3;

            }
        });

        inputDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show(getFragmentManager(), "DatePickerDialog");
                variable =4;

            }
        });

        register = (Button) findViewById(R.id.btn_register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                registerBean.setReferenceNumber(referenceNumber);
                registerBean.setAadharNumber(inputAadhar.getText().toString().trim());
                registerBean.setPersonName(inputName.getText().toString().trim());
                registerBean.setPhoneNumber(inputPhone.getText().toString().trim());
                registerBean.setDateOfBirth(inputDateofBirth.getText().toString().trim());
                registerBean.setEmail(inputEmail.getText().toString().trim());
                registerBean.setFathersFirstName(inputFathersFirstName.getText().toString().trim());
                registerBean.setFathersSecondName(inputFathersLastName.getText().toString().trim());
                registerBean.setMothersFirstName(inputMothersFirstName.getText().toString().trim());
                registerBean.setMothersSecondName(inputMothersLastName.getText().toString().trim());
                registerBean.setAddress1(inputCurrentAddress1.getText().toString().trim());
                registerBean.setAddress2(inputCurrentAddress2.getText().toString().trim());
                registerBean.setPincode(inputCurrentPincode.getText().toString().trim());
                registerBean.setCity(inputCurrentCity.getText().toString().trim());
                registerBean.setDistrict(inputCurrentDistrict.getText().toString().trim());
                registerBean.setState(inputCurrentState.getText().toString().trim());
                registerBean.setAddress1Permanent(inputPermanetAddress1.getText().toString().trim());
                registerBean.setAddress2Permanent(inputPermanetAddress2.getText().toString().trim());
                registerBean.setPincodePermanent(inputPermanetPincode.getText().toString().trim());
                registerBean.setCityPermanent(inputPermanetCity.getText().toString().trim());
                registerBean.setDistrictPermanent(inputPermanetDistrict.getText().toString().trim());
                registerBean.setStatePermanent(inputPermanetState.getText().toString().trim());
                registerBean.setMarks(inputMarks.getText().toString().trim());
                registerBean.setDivisionOrGrade(inputDivisionGrade.getText().toString().trim());
                registerBean.setMajorSubjects(inputMajorSubjects.getText().toString().trim());
                registerBean.setNatureOfPresentEmp(inputNatureOfPresentEmp.getText().toString().trim());
                registerBean.setTotalEmoluments(inputTotalEmoluments.getText().toString().trim());
                registerBean.setAdditionalInfo(inputAdditionalInfo.getText().toString().trim());
                registerBean.setRemarks(inputRemarks.getText().toString().trim());
                registerBean.setFullNameOfCandidate(inputFullName.getText().toString().trim());
                registerBean.setSignature(inputSignature.getText().toString().trim());
                registerBean.setJobDescription(jobDescriptSpinner.getText().toString().trim());

                registerBean.setFrom(inputFrom.getText().toString().trim());
                registerBean.setTo(inputTo.getText().toString().trim());
                registerBean.setDate(inputDate.getText().toString().trim());

                ByteArrayOutputStream out = new ByteArrayOutputStream();
                if(imageBitmap!=null)
                {
                    imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
                    byte[] buffer=out.toByteArray();

                    registerBean.setImage(buffer);
                }

                if(registerTermsAndCondCheck.isChecked())
                {
                    SqliteHelper helper = new SqliteHelper(RegisterActivity.this);
                    helper.addContact(registerBean);


                }
                else
                {
                    Toast.makeText(RegisterActivity.this, "Please Accept Terms & Conditions.", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

    private  String generateRandomNumber()
    {
        char[] chars = "1234567890".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        String referenceNumber = sb.toString();
        return referenceNumber;
    }
    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = "" + dayOfMonth + "-" + (monthOfYear+1)+ "-" + year;

        if(variable==1)
        inputDateofBirth.setText(date);
        else if(variable==2)
            inputFrom.setText(date);
        else if(variable==3)
            inputTo.setText(date);
        else if(variable==4)
            inputDate.setText(date);
    }

    public void openCameraGallery()
    {


        AlertDialog.Builder opencameragallery; opencameragallery = new AlertDialog.Builder(RegisterActivity.this);
        LayoutInflater lf=(LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View vv=lf.inflate(R.layout.opencaremagarllerylayout, null);

        opencameragallery.setView(vv);
        opencameragallery.setTitle(Html.fromHtml("<font color='#141414'>Choose</font>"));
        OpenCamera=(LinearLayout) vv.findViewById(R.id.OpenCamera);
        OpenCamera.getLayoutParams().height=width/3;
        OpenGallery=(LinearLayout) vv.findViewById(R.id.OpenGallery);
        OpenGallery.getLayoutParams().height=width/3;
        ImageView cameraimg=(ImageView)vv.findViewById(R.id.cameraimg);
        cameraimg.getLayoutParams().width=width/6;

        cameraimg.getLayoutParams().height=width/6;

        ImageView galleryimg=(ImageView)vv.findViewById(R.id.galleryimage);
        galleryimg.getLayoutParams().width=width/6;

        galleryimg.getLayoutParams().height=width/6;
        opencameragallery.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                dialog.cancel();
            }
        });

        alert=opencameragallery.create();
        alert.setCanceledOnTouchOutside(false);
        //opencameragallery.setCanceledOnTouchOutside(false);

        alert.setOnShowListener(new DialogInterface.OnShowListener() {

            @Override
            public void onShow(DialogInterface dialog)
            {
                // TODO Auto-generated method stub
                alert.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#141414"));
            }
        });
        alert.show();
        OpenCamera.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                alert.dismiss();

               /* outputFileUri = Uri.fromFile(sdImageMainDirectory);
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);

                intent.putExtra("return-data", true);
                startActivityForResult(intent, CAMERA_REQUEST);*/
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
//					startActivityForResult(new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE),camera_ReqCode);
            }
        });

        OpenGallery.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                alert.dismiss();

                startActivityForResult(new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI), GALLERY_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==CAMERA_REQUEST && resultCode==RESULT_OK)
        {
            try
            {

                imageBitmap = (Bitmap) data.getExtras().get("data");
              /*  final float densityMultiplier = this.getResources().getDisplayMetrics().density;
                float newHeight = 150;
                int h= (int) (newHeight*densityMultiplier);
                int w= (int) (h * imageBitmap.getWidth()/((double) imageBitmap.getHeight()));
                imageBitmap=Bitmap.createScaledBitmap(imageBitmap, w, h, true);*/
                profilePic.setImageBitmap(imageBitmap);
            }
            catch (Exception e)
            {
                e.printStackTrace();
                Log.e("Image  Error", "****" + e.getMessage());
            }
        }
        if (requestCode == GALLERY_REQUEST && resultCode == RESULT_OK) {
            try {
                imageBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());
                final float densityMultiplier = this.getResources().getDisplayMetrics().density;
                float newHeight = 150;
                int h= (int) (newHeight*densityMultiplier);
                int w= (int) (h * imageBitmap.getWidth()/((double) imageBitmap.getHeight()));
                imageBitmap=Bitmap.createScaledBitmap(imageBitmap, w, h, true);
                profilePic.setImageBitmap(imageBitmap);
            }
            catch (Exception e)
            {
                e.printStackTrace();
                Log.e("Image  Error", "****" + e.getMessage());
            }
        }
    }
}