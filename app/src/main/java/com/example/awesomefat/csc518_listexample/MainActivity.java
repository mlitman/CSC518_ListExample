package com.example.awesomefat.csc518_listexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private ListView studentListView;
    private ArrayAdapter<String> listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.studentListView = (ListView)this.findViewById(R.id.studentListView);
        for(int i = 0; i < Core.theStudentStrings.length; i++)
        {
            Core.theStudentStrings[i] = "UnKnown Student";
        }

        for(int i = 0; i < 100; i++)
        {
            Student s = new Student("Fname" + i, "Lname" + i, i);
            Core.theStudents[Core.numStudents] = s;
            Core.theStudentStrings[Core.numStudents] = s.toString();
            Core.numStudents++;
        }

        String[] bibleBooks = {"Matthew", "Mark", "Luke", "John", "Acts"};
        String[] arOfStuff = new String[1000];
        for(int i = 0 ; i < 1000; i++)
        {
            arOfStuff[i] = "dfssadfas" + i;
        }

        this.listAdapter = new ArrayAdapter<String>(this,
                R.layout.another_row, Core.theStudentStrings);
        this.studentListView.setAdapter(this.listAdapter);

    }

    public void onAddCreditCardButtonPressed(View v)
    {
        Intent i = new Intent(this, AddCreditCardActivity.class);
        this.startActivity(i);
    }

    public void onButtonPressed(View v)
    {
        TextView answerTV = (TextView)this.findViewById(R.id.answerTV);
        answerTV.setText("World");
    }

    public void onNextPageButtonPressed(View v)
    {
        Intent i = new Intent(this, Page2Activity.class);
        this.startActivity(i);
    }
}
