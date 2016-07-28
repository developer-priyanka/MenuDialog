package my.assignment.menudialog;

import android.app.DatePickerDialog;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private CustomeAdapter adapter;
    private Person person;
    private ArrayList<Person> list=new ArrayList<Person>();
    private ListView listView;
    private String Tag="Main Activity";

    @Override //calls when activity start
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar();
        listView=(ListView)findViewById(R.id.list);


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.add:
                Log.d(Tag,"Add Option clicked");

                createDailog();


        }
        return  true;
    }

    public void createDailog(){  //create dialog

        LayoutInflater inflater=LayoutInflater.from(this);
        View dialog=inflater.inflate(R.layout.dialog_main,null);
        final AlertDialog alert=new AlertDialog.Builder(MainActivity.this).create();

        Button saveBtn,canBtn;
        final EditText nametxt,phtxt,dobtxt;
        saveBtn=(Button)dialog.findViewById(R.id.Save);
        canBtn=(Button)dialog.findViewById(R.id.cancel);
        nametxt=(EditText)dialog.findViewById(R.id.name);
        phtxt=(EditText)dialog.findViewById(R.id.phone);
        dobtxt=(EditText)dialog.findViewById(R.id.dob);

        dobtxt.setOnClickListener(new View.OnClickListener() {  //click on DOB edit text
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();

                DatePickerDialog dpd = new DatePickerDialog(MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                dobtxt.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);

                            }
                        }, c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DATE));
                dpd.show();


            }

        });

        saveBtn.setOnClickListener(new View.OnClickListener() {  //click on save button
            @Override
            public void onClick(View view) {
                String name=nametxt.getText().toString();
                String phone=getPhone(phtxt.getText().toString());
                String dob=dobtxt.getText().toString();
                Log.d(Tag,name+" "+phone+" "+isValidDate(dob));

                if(name==null||name.length()==0) {
                    Toast.makeText(getApplicationContext(), "Please Enter Name", Toast.LENGTH_SHORT).show();
                    nametxt.setText("");
                    return;
                }
                if(phone==null || phone.length()==0) {
                    Toast.makeText(getApplicationContext(), "Please Enter Valid phone no", Toast.LENGTH_SHORT).show();
                    phtxt.setText("");
                    return;
                }
                if(dob==null||dob.length()==0||isValidDate(dob)!=true) {
                    Toast.makeText(getApplicationContext(), "Please Enter Valid Date of Birth (dd/MM/yyyy)", Toast.LENGTH_SHORT).show();
                    dobtxt.setText("");
                    return;
                }


                person=new Person(name,phone,dob);
                list.add(person);
                adapter=new CustomeAdapter(MainActivity.this,list);  //set the adapter for list displaying on Main activity
                listView.setAdapter(adapter);
                Toast.makeText(getApplicationContext(), "Detail Saved", Toast.LENGTH_SHORT).show();
                nametxt.setText("");
                phtxt.setText("");
                dobtxt.setText("");


            }
        });
        canBtn.setOnClickListener(new View.OnClickListener() {  //click on cancel button
            @Override
            public void onClick(View view) {
               alert.cancel() ;
            }
        });
        alert.setTitle("Enter the Details");
        alert.setView(dialog);
        alert.show();
    }
    public boolean isValidDate(String dateString){                      //Valid the entered date format
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(dateString.trim());
        } catch (Exception pe) {
            return false;
        }
        return true;

    }
    public String getPhone(String num) { //append country code to phone no.
        String phone = "";
        if (num.length() < 10||num.length()>10)
            return null;
        phone = "+91-" + num;
        return phone;
    }


}
