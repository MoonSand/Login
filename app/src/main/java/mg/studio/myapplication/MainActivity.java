package mg.studio.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView tvName;

    private SessionManager session;
    private String[] appName = {"00_LifeCycle", "01_UserName","01_UserName_Final", "02_Layout","02_Layout_Final","03_Button_Design","03_Button_Toast"
            ,"04_Button_Intent","05_Button_StartActivity","05_Button_StartActivity_extra","06_ImageButton","07_EditText","08_RadioButtons_listener"
            ,"08_RadioButtons_onclick","09_listView","10_GetColor","11_GradientBackground","13_Weather_App_Design"
            ,"15_ListView","16_ListViewCustomAdapter","17_AudioRecorder","19_DataBase","20_FragmentOne","21_Webview","22_ServiceDemo"
            ,"23_Service","24_Fingerprint","25_AppPrivateDirectory","26_AssetsFolder","27_IntentExtras"};


    private Class[] mClass={mg.studio.activitylifecycle.MainActivity.class,mg.studio.username.MainActivity.class,mg.studio.usernamefinal.MainActivity.class
    ,com.example.layouts.MainActivity.class,mg.studio.layouts.MainActivity.class,mg.studio.button.MainActivity.class,mg.studio.buttontoast.MainActivity.class
    ,mg.studio.buttonintent.MainActivity.class, cn.edu.cqu.buttontoast.MainActivity.class, cn.edu.cqu.buttontoast_extra.MainActivity.class
    ,com.example.imagebutton.MainActivity.class,com.example.edittext.MainActivity.class,com.example.radiobuttons.MainActivity.class,com.example.radiobuttonsclick.MainActivity.class
    ,com.example.listView.MainActivity.class,cn.edu.cqu.getcolor.MainActivity.class, mg.studio.gradientbackground.MainActivity.class
    ,mg.studio.weather.MainActivity.class,mg.studio.listview_demo.MainActivity.class,mg.studio.listviewcustomadapter.MainActivity.class
    ,mg.studio.audiorecorder.MainActivity.class,mg.studio.database.MainActivity.class,mg.studio.fragmentdemo001.MainActivity.class
    ,mg.studio.prototyping.MainActivity.class,mg.studio.servicedemo.MainActivity.class,mg.studio.service.MainActivity.class,mg.studio.fingerprint.MainActivity.class
    ,mg.studio.writefileexternalstorage.MainActivity.class,mg.studio.assetsfolder.MainActivity.class,mg.studio.intentpassdata.MainActivity.class};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvName = findViewById(R.id.user_name);


        /**
         * Only logged in users should access this activity
         */
        session = new SessionManager(getApplicationContext());
        if (!session.isLoggedIn()) {
            logout();
        }

        /**
         * If the user just registered an account from Register.class,
         * the parcelable should be retrieved
         */
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            // Retrieve the parcelable
            Feedback feedback = bundle.getParcelable("feedback");
            // Get the from the object
            String userName = feedback.getName();
            tvName.setText(userName);
        }

        ArrayAdapter<String> mAdapter = new ArrayAdapter<String>
                (this, R.layout.list_item, R.id.item_text, appName);

        ListView listView = findViewById(R.id.listview); //Connect the view list
        listView.setAdapter(mAdapter); //populate the ListView

        //Set the onClick listener to each item of the ListView
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(mg.studio.myapplication.MainActivity.this, mClass[i]));

            }
        });



    }

    /**
     * Logging out the user:
     * - Will set isLoggedIn flag to false in SharedPreferences
     * - Clears the user data from SqLite users table
     */

    public void btnLogout(View view) {
        logout();
    }

    public void logout() {
        // Updating the session
        session.setLogin(false);
        // Redirect the user to the login activity
        startActivity(new Intent(this, Login.class));
        finish();
    }


}