package com.example.quizapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // クイズの問題（5問）
    String[] questions = {
            "Javaでクラスから作られる実体は？",
            "Javaで、複数の同じ名前のメソッドを引数違いで定義することを何という？",
            "Javaで、親クラスのメソッドを子クラスが上書きすることを何という？",
            "例外処理で、エラーが起きた時に実行されるブロックは？",
            "「==」と違って、中身の値を比較するのに使うメソッドは？",
    };

    // 選択肢を入れる（各問題に4つずつ）
    String[][] choices = {
            {"オブジェクト", "メソッド", "フィールド", "スレッド"},
            {"継承", "ポリモーフィズム", "オーバーロード", "オーバーライド"},
            {"キャスト", "オーバーライド", "オーバーロード", "コンパイル"},
            {"try", "if", "finally", "catch"},
            {"equals()", "isSame()", "compare()", "match()"},
    };

    // 正解のインデックス（0〜3）
    int[] answers = {0, 2, 1, 3, 0};

    int currentQuestion = 0; // 今何問目か
    int score = 0; // 正解数をカウント

    // UIの部品
    TextView questionText;
    Button option1;
    Button option2;
    Button option3;
    Button option4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 画面とつなげる（XMLのIDと結びつける）
        questionText = findViewById(R.id.questionText);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);

        // 最初の問題を表示
        showQuestion();

        // 各ボタンが押された時の動きを定義
        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(0); // 一番上の選択肢
            }
        });

        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(1);
            }
        });

        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(2);
            }
        });

        option4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(3);
            }
        });
    }

    // 問題と選択肢を画面に表示する関数
    void showQuestion() {
        if (currentQuestion < questions.length) {
            questionText.setText(questions[currentQuestion]);
            option1.setText(choices[currentQuestion][0]);
            option2.setText(choices[currentQuestion][1]);
            option3.setText(choices[currentQuestion][2]);
            option4.setText(choices[currentQuestion][3]);
        } else {
            // 全部終わったらスコアを表示して終了
            Toast.makeText(this, "終了！スコア: " + score + " / " + questions.length, Toast.LENGTH_LONG).show();

            // ボタンを押せなくする
            option1.setEnabled(false);
            option2.setEnabled(false);
            option3.setEnabled(false);
            option4.setEnabled(false);
        }
    }

    // ユーザーの答えが合ってるかどうかをチェックする関数
    void checkAnswer(int selected) {
        if (selected == answers[currentQuestion]) {
            // 正解だったらスコアを増やす
            score++;
            Toast.makeText(this, "正解！", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "残念！", Toast.LENGTH_SHORT).show();
        }

        // 次の問題へ
        currentQuestion++;
        showQuestion();
    }
}
