package com.example.todolistapp;  //パッケージ名

import android.os.Bundle;  //アプリ起動時の状態を保持するクラス
import androidx.appcompat.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;  //UIを操作するために必要
import com.example.todolistapp.databinding.ActivityMainBinding;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // ToDoのリストを格納するための箱を作る
    ArrayList <String> tasks = new ArrayList<>();

    // ListViewにtasksを表示するための橋渡し
    ArrayAdapter<String> adapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //XMLに書いたUIパーツをJAVAコードと繋げる
        EditText editTextTask = findViewById(R.id.editTextTask);
        Button buttonAdd = findViewById(R.id.buttonAdd);
        ListView listViewTasks = findViewById(R.id.listViewTasks);

        // ListViewとtasksを繋げるアダプターの作成
        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, tasks);
        listViewTasks.setAdapter(adapter);
        
        
        //ボタンが押された時の動作を設定
        buttonAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //入力されたテキストを取得し、前後の空白を削除する処理
                String task = editTextTask.getText().toString().trim();
                //空欄でなければリストに追加
                if (!task.isEmpty()){
                    tasks.add(task);                 //リスト追加
                    adapter.notifyDataSetChanged();  //リスト更新
                    editTextTask.setText("");        //入力クリア
                }
            }
        });
    }
}
