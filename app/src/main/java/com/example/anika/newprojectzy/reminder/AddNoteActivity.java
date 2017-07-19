package com.example.anika.newprojectzy.reminder;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.anika.newprojectzy.R;
import com.example.anika.newprojectzy.speech.MessageDialogFragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddNoteActivity extends AppCompatActivity implements RecognitionListener {
    //
    // Dialog Constants
    //
    private static final int DATE_PICKER_DIALOG = 0;
    private static final int TIME_PICKER_DIALOG = 1;
    private final int REQ_CODE_SPEECH_INPUT = 100;
    //
    // Date Format
    //
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String TIME_FORMAT = "kk:mm";
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd kk:mm:ss";
    private Long mRowId;
    private RemindersDbAdapter mDbHelper;
    private Calendar mCalendar;
    RelativeLayout relDate, relTime;
    EditText edTitle, edBody;
    TextView tvDate, tvTime;
    Button btnSave;
    CheckBox checkBox;
    boolean isSpeech = false;

    private static final String FRAGMENT_MESSAGE_DIALOG = "message_dialog";

    private static final String STATE_RESULTS = "results";

    private static final int REQUEST_RECORD_AUDIO_PERMISSION = 1;

    private SpeechRecognizer speech = null;
    private Intent recognizerIntent;
    private String LOG_TAG = "VoiceRecognitionActivity";


    ImageView imgSong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            mDbHelper = new RemindersDbAdapter(this);


            final Resources resources = getResources();
            final Resources.Theme theme = getTheme();

            setContentView(R.layout.activity_add_note);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            btnSave = (Button)findViewById(R.id.buttonSave);
            tvDate = (TextView) findViewById(R.id.textViewNoteDate);
            tvTime = (TextView) findViewById(R.id.textViewNoteTime);
            edTitle = (EditText) findViewById(R.id.editTextNoteTitle);
            edBody = (EditText) findViewById(R.id.editTextNoteBody);
            relDate = (RelativeLayout) findViewById(R.id.relDate);
            relTime = (RelativeLayout) findViewById(R.id.relTime);
            checkBox = (CheckBox) findViewById(R.id.checkBox);
            imgSong = (ImageView) findViewById(R.id.imageViewSong);
            imgSong.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    if(!isSpeech){
//                        isSpeech = true;
//                        speech.startListening(recognizerIntent);
//                    }
//                    else speech.stopListening();
                    promptSpeechInput();
                }
            });

            mCalendar = Calendar.getInstance();

            mRowId = savedInstanceState != null ? savedInstanceState.getLong(RemindersDbAdapter.KEY_ROWID)
                    : null;
            registerButtonListenersAndSetDefaultText();

            speech = SpeechRecognizer.createSpeechRecognizer(this);
            speech.setRecognitionListener(this);
            recognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE,"en");
            recognizerIntent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE,this.getPackageName());
            recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH);
            recognizerIntent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 3);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void onPause() {
        super.onPause();
        if (speech != null) {
            speech.destroy();

        }
        mDbHelper.close();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mDbHelper.open();
        setRowIdFromIntent();
        populateFields();
    }
    private void setRowIdFromIntent() {
        if (mRowId == null) {
            Bundle extras = getIntent().getExtras();
            mRowId = extras != null ? extras.getLong(RemindersDbAdapter.KEY_ROWID)
                    : null;

        }
    }


    @Override
    protected Dialog onCreateDialog(int id) {
        switch(id) {
            case DATE_PICKER_DIALOG:
                return showDatePicker();
            case TIME_PICKER_DIALOG:
                return showTimePicker();
        }
        return super.onCreateDialog(id);
    }

    private DatePickerDialog showDatePicker() {


        DatePickerDialog datePicker = new DatePickerDialog(AddNoteActivity.this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                mCalendar.set(Calendar.YEAR, year);
                mCalendar.set(Calendar.MONTH, monthOfYear);
                mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateDateButtonText();
            }
        }, mCalendar.get(Calendar.YEAR), mCalendar.get(Calendar.MONTH), mCalendar.get(Calendar.DAY_OF_MONTH));
        return datePicker;
    }

    private TimePickerDialog showTimePicker() {

        TimePickerDialog timePicker = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                mCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                mCalendar.set(Calendar.MINUTE, minute);
                updateTimeButtonText();
            }
        }, mCalendar.get(Calendar.HOUR_OF_DAY), mCalendar.get(Calendar.MINUTE), true);

        return timePicker;
    }

    private void registerButtonListenersAndSetDefaultText() {

        relDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                showDialog(DATE_PICKER_DIALOG);
            }
        });


        relTime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                showDialog(TIME_PICKER_DIALOG);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                saveState();
                setResult(RESULT_OK);
                Toast.makeText(AddNoteActivity.this, getString(R.string.task_saved_message), Toast.LENGTH_SHORT).show();
                finish();
            }

        });

        updateDateButtonText();
        updateTimeButtonText();
    }

    private void populateFields()  {



        // Only populate the text boxes and change the calendar date
        // if the row is not null from the database.
        if (mRowId != null) {
            Cursor reminder = mDbHelper.fetchReminder(mRowId);
            startManagingCursor(reminder);
            edTitle.setText(reminder.getString(
                    reminder.getColumnIndexOrThrow(RemindersDbAdapter.KEY_TITLE)));
            edBody.setText(reminder.getString(
                    reminder.getColumnIndexOrThrow(RemindersDbAdapter.KEY_BODY)));


            // Get the date from the database and format it for our use.
            SimpleDateFormat dateTimeFormat = new SimpleDateFormat(DATE_TIME_FORMAT);
            Date date = null;
            try {
                String dateString = reminder.getString(reminder.getColumnIndexOrThrow(RemindersDbAdapter.KEY_DATE_TIME));
                date = dateTimeFormat.parse(dateString);
                mCalendar.setTime(date);
            } catch (ParseException e) {
                Log.e("ReminderEditActivity", e.getMessage(), e);
            }
        } else {
            // This is a new task - add defaults from preferences if set.
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
            String defaultTitleKey = getString(R.string.pref_task_title_key);
            String defaultTimeKey = getString(R.string.pref_default_time_from_now_key);

            String defaultTitle = prefs.getString(defaultTitleKey, null);
            String defaultTime = prefs.getString(defaultTimeKey, null);

            if(defaultTitle != null)
                edTitle.setText(defaultTitle);

            if(defaultTime != null)
                mCalendar.add(Calendar.MINUTE, Integer.parseInt(defaultTime));

        }

        updateDateButtonText();
        updateTimeButtonText();

    }

    private void updateTimeButtonText() {
        // Set the time button text based upon the value from the database
        SimpleDateFormat timeFormat = new SimpleDateFormat(TIME_FORMAT);
        String timeForButton = timeFormat.format(mCalendar.getTime());
        tvTime.setText(timeForButton);
    }

    private void updateDateButtonText() {
        // Set the date button text based upon the value from the database
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        String dateForButton = dateFormat.format(mCalendar.getTime());
        tvDate.setText(dateForButton);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong(RemindersDbAdapter.KEY_ROWID, mRowId);
    }



    private void saveState() {

        String body = edBody.getText().toString();
        String title = body.length()>20 ? body.substring(0,17)+"...": body;
        String isSound = "1";
        if(checkBox.isChecked()) isSound = "0";
        else isSound = "1";
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat(DATE_TIME_FORMAT);
        String reminderDateTime = dateTimeFormat.format(mCalendar.getTime());

        if (mRowId == null) {

            long id = mDbHelper.createReminder(title, body, reminderDateTime,isSound);
            if (id > 0) {
                mRowId = id;
            }
        } else {
            mDbHelper.updateReminder(mRowId, title, body, reminderDateTime, isSound);
        }

        new ReminderManager(this).setReminder(mRowId, mCalendar, title,isSound);
    }


    @Override
    protected void onStart() {
        super.onStart();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
                == PackageManager.PERMISSION_GRANTED) {

        } else if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.RECORD_AUDIO)) {
            showPermissionMessageDialog();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO},
                    REQUEST_RECORD_AUDIO_PERMISSION);
        }
    }

    @Override
    protected void onStop() {
        // Stop listening to voice

        super.onStop();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_RECORD_AUDIO_PERMISSION) {
            if (permissions.length == 1 && grantResults.length == 1
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            } else {
                showPermissionMessageDialog();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private void showPermissionMessageDialog() {
        MessageDialogFragment
                .newInstance(getString(R.string.permission_message))
                .show(getSupportFragmentManager(), FRAGMENT_MESSAGE_DIALOG);
    }

    private void showStatus(final boolean hearingVoice) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //mStatus.setTextColor(hearingVoice ? mColorHearing : mColorNotHearing);
            }
        });
    }

    @Override
    public void onReadyForSpeech(Bundle bundle) {

    }

    @Override
    public void onBeginningOfSpeech() {

    }

    @Override
    public void onRmsChanged(float v) {

    }

    @Override
    public void onBufferReceived(byte[] bytes) {

    }

    @Override
    public void onEndOfSpeech() {

    }

    @Override
    public void onError(int i) {

    }

    @Override
    public void onResults(Bundle bundle) {
        ArrayList<String> matches = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        String text = "";
        for (String result : matches)
            text += result + "\n";

        edBody.setText(text);
    }

    @Override
    public void onPartialResults(Bundle bundle) {

    }

    @Override
    public void onEvent(int i, Bundle bundle) {

    }

    /**
     * Showing google speech input dialog
     * */
    private void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_prompt));
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Receiving speech input
     * */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    edBody.setText(result.get(0));
                }
                break;
            }

        }
    }
}
