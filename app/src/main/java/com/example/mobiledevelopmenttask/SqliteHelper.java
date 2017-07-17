package com.example.mobiledevelopmenttask;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Subbu on 16-07-2017.
 */

public class SqliteHelper extends SQLiteOpenHelper
{
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "registerManager";

    // Contacts table name
    private static final String TABLE_CONTACTS = "profile";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PH_NO = "phone_number";
    private static final String KEY_AADHAR = "aadahar";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_DATE_OFBIRTH = "date_of_birth";
    private static final String KEY_FATHER_FIRST_NAME = "father_first_name";
    private static final String KEY_FATHER_LAST_NAME = "father_LAst_name";
    private static final String KEY_MOTHER_FIRST_NAME = "mother_first_name";
    private static final String KEY_MOTHER_LAST_NAME = "mother_LAst_name";
    private static final String KEY_ADDRESS1_CURRENT = "current_address1";
    private static final String KEY_ADDRESS2_CURRENT = "current_address2";
    private static final String KEY_PINCODE_CURRENT = "current_pincode";
    private static final String KEY_CURRENT_CITY = "current_city";
    private static final String KEY_CURRENT_DISTRICT = "current_district";
    private static final String KEY_CURRENT_STATE = "current_state";
    private static final String KEY_ADDRESS1_PERMANENT = "permanent_address1";
    private static final String KEY_ADDRESS2_PERMANENT = "permanent_address2";
    private static final String KEY_PINCODE_PERMANENT = "permanent_pincode";
    private static final String KEY_PERMANENT_CITY = "permanent_city";
    private static final String KEY_PERMANENT_DISTRICT = "permanent_district";
    private static final String KEY_PERMANENT_STATE = "permanent_state";

    private static final String KEY_RELIGION = "religion";
    private static final String KEY_GENDER = "gender";
    private static final String KEY_MARTIALSTATUS = "martial_status";
    private static final String KEY_EXAMINATION_LEVEL = "examination_level";
    private static final String KEY_PASSING_YEAR = "passing_year";
    private static final String KEY_BOARD_UNIVERSITY = "board_university";
    private static final String KEY_MARKS = "marks";
    private static final String KEY_DIVISION_GRADE = "division_grade";
    private static final String KEY_MAJOR_SUBJECTS = "major_subjects";
    private static final String KEY_CAST_CATEGORY = "cast_category";
    private static final String KEY_POST_HELD = "post_held";
    private static final String KEY_OFFICE_INST = "office_institue";
    private static final String KEY_FROM = "from";
    private static final String KEY_TO = "to";
    private static final String KEY_SCALE_OF_PAY = "scale_of_pay";
    private static final String KEY_NATURE_OF_DUTIES = "nature_of_duties";
    private static final String KEY_NATURE_OF_PRESENT_EMP = "nature_of_present_emp";
    private static final String KEY_TOTAL_EMOLUMENTS = "total_emoluments";
    private static final String KEY_ADDITIONAL_INFO = "addition_info";
    private static final String KEY_REMARKS = "remarks";
    private static final String KEY_EXACT_NAME_OF_POST = "exact_name_of_post";
    private static final String KEY_ORGANISATION_NAME = "org_name";
    private static final String KEY_DURATION = "duration";
    private static final String KEY_JOB_DESCRIPT = "job_description";
    private static final String KEY_DATE = "date";
    private static final String KEY_FULL_NAME = "full_name";
    private static final String KEY_SIGNATURE = "signature";


    private  Context context;
    public SqliteHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;

    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," +KEY_AADHAR + " TEXT,"+ KEY_NAME + " TEXT,"
                + KEY_PH_NO + " TEXT,"  +KEY_DATE_OFBIRTH + " TEXT,"+ KEY_EMAIL + " TEXT,"+ KEY_FATHER_FIRST_NAME + " TEXT,"+
                KEY_FATHER_LAST_NAME + " TEXT,"+ KEY_MOTHER_FIRST_NAME + " TEXT,"+KEY_MOTHER_LAST_NAME + " TEXT,"+
                KEY_ADDRESS1_CURRENT + " TEXT,"+ KEY_ADDRESS2_CURRENT + " TEXT,"+ KEY_PINCODE_CURRENT + " TEXT,"+ KEY_CURRENT_CITY + " TEXT,"+
                KEY_CURRENT_DISTRICT + " TEXT,"+ KEY_CURRENT_STATE + " TEXT,"+ KEY_ADDRESS1_PERMANENT + " TEXT,"+KEY_ADDRESS2_PERMANENT + " TEXT,"+
                KEY_PINCODE_PERMANENT + " TEXT,"+ KEY_PERMANENT_CITY + " TEXT,"+ KEY_PERMANENT_DISTRICT + " TEXT,"+KEY_PERMANENT_STATE + " TEXT,"+
                KEY_RELIGION + " TEXT,"+ KEY_GENDER + " TEXT,"+ KEY_MARTIALSTATUS + " TEXT,"+KEY_EXAMINATION_LEVEL + " TEXT,"+
                KEY_PASSING_YEAR + " TEXT,"+KEY_BOARD_UNIVERSITY + " TEXT,"+ KEY_MARKS + " TEXT,"+ KEY_DIVISION_GRADE + " TEXT,"+
                KEY_MAJOR_SUBJECTS + " TEXT,"+ KEY_CAST_CATEGORY + " TEXT,"+ KEY_POST_HELD + " TEXT,"+ KEY_OFFICE_INST + " TEXT,"
                +KEY_FROM + " TEXT,"+KEY_TO + " TEXT,"+ KEY_SCALE_OF_PAY + " TEXT,"+KEY_NATURE_OF_DUTIES + " TEXT,"+
                KEY_NATURE_OF_PRESENT_EMP + " TEXT,"+ KEY_TOTAL_EMOLUMENTS + " TEXT,"+ KEY_ADDITIONAL_INFO + " TEXT,"+
                KEY_REMARKS + " TEXT,"+ KEY_EXACT_NAME_OF_POST + " TEXT,"+ KEY_ORGANISATION_NAME + " TEXT,"+
                KEY_DURATION + " TEXT,"+ KEY_JOB_DESCRIPT + " TEXT,"+ KEY_DATE + " TEXT,"+ KEY_FULL_NAME + " TEXT,"+
                KEY_SIGNATURE + " TEXT"+ ")";
        sqLiteDatabase.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

        // Create tables again
        onCreate(sqLiteDatabase);
    }

    public void addContact(RegisterBean registerBean) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_AADHAR, registerBean.getAadharNumber());
        values.put(KEY_NAME, registerBean.getPersonName()); // Contact Name
        values.put(KEY_PH_NO, registerBean.getPhoneNumber()); // Contact Phone Number
        values.put(KEY_DATE_OFBIRTH, registerBean.getDateOfBirth());
        values.put(KEY_EMAIL, registerBean.getEmail());
        values.put(KEY_FATHER_FIRST_NAME, registerBean.getFathersFirstName());
        values.put(KEY_FATHER_LAST_NAME, registerBean.getFathersSecondName());
        values.put(KEY_MOTHER_FIRST_NAME, registerBean.getMothersFirstName());
        values.put(KEY_MOTHER_LAST_NAME, registerBean.getMothersSecondName());
        values.put(KEY_ADDRESS1_CURRENT, registerBean.getAddress1());
        values.put(KEY_ADDRESS2_CURRENT, registerBean.getAddress2());
        values.put(KEY_PINCODE_CURRENT, registerBean.getPincode());
        values.put(KEY_CURRENT_CITY, registerBean.getCity());
        values.put(KEY_CURRENT_DISTRICT, registerBean.getDistrict());
        values.put(KEY_CURRENT_STATE, registerBean.getState());
        values.put(KEY_ADDRESS1_PERMANENT, registerBean.getAddress1Permanent());
        values.put(KEY_ADDRESS2_PERMANENT, registerBean.getAddress2Permanent());
        values.put(KEY_PINCODE_PERMANENT, registerBean.getPincodePermanent());
        values.put(KEY_PERMANENT_CITY, registerBean.getCityPermanent());
        values.put(KEY_PERMANENT_DISTRICT, registerBean.getDistrictPermanent());
        values.put(KEY_PERMANENT_STATE, registerBean.getStatePermanent());
        values.put(KEY_RELIGION, registerBean.getReligion());
        values.put(KEY_GENDER, registerBean.getGender());
        values.put(KEY_MARTIALSTATUS, registerBean.getMartialStatus());
        values.put(KEY_EXAMINATION_LEVEL, registerBean.getExaminationLevel());
        values.put(KEY_PASSING_YEAR, registerBean.getPassingYear());
        values.put(KEY_BOARD_UNIVERSITY, registerBean.getBoardOrUniversity());
        values.put(KEY_MARKS, registerBean.getMarks());
        values.put(KEY_DIVISION_GRADE, registerBean.getDivisionOrGrade());
        values.put(KEY_MAJOR_SUBJECTS, registerBean.getMajorSubjects());
        values.put(KEY_CAST_CATEGORY, registerBean.getCastCategory());
        values.put(KEY_POST_HELD, registerBean.getPostHeld());
        values.put(KEY_OFFICE_INST, registerBean.getOfficeInst());
        values.put(KEY_FROM, registerBean.getFrom());
        values.put(KEY_TO, registerBean.getTo());
        values.put(KEY_SCALE_OF_PAY, registerBean.getScaleOfPay());
        values.put(KEY_NATURE_OF_DUTIES, registerBean.getNatureOfDuties());
        values.put(KEY_NATURE_OF_PRESENT_EMP, registerBean.getNatureOfPresentEmp());
        values.put(KEY_TOTAL_EMOLUMENTS, registerBean.getTotalEmoluments());
        values.put(KEY_ADDITIONAL_INFO, registerBean.getAdditionalInfo());
        values.put(KEY_REMARKS, registerBean.getRemarks());
        values.put(KEY_EXACT_NAME_OF_POST, registerBean.getExamNameOfPost());
        values.put(KEY_ORGANISATION_NAME, registerBean.getOrganisationName());
        values.put(KEY_DURATION, registerBean.getDuration());
        values.put(KEY_JOB_DESCRIPT, registerBean.getJobDescription());
        values.put(KEY_DATE, registerBean.getDate());
        values.put(KEY_FULL_NAME, registerBean.getFullNameOfCandidate());
        values.put(KEY_SIGNATURE, registerBean.getSignature());

        // Inserting Row
        db.insert(TABLE_CONTACTS, null, values);
        Toast.makeText(context,"Data added succesfully",Toast.LENGTH_SHORT).show();
        db.close(); // Closing database connection
    }
}
