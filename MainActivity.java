package com.example.learning;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static int player = 1; //initialise counter for turns
    public static int[][] arr = new int[3][3]; //array for values
    public static boolean win = false;
    public boolean IsAI = false;
    public Button resetBtn, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    public TextView txt;
    public Button AIBtn;
    public TextView AIText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //makes buttons public
        resetBtn = findViewById(R.id.ResetBtn);
        btn1 = findViewById(R.id.button);
        btn2 = findViewById(R.id.button2);
        btn3 = findViewById(R.id.button3);
        btn4 = findViewById(R.id.button4);
        btn5 = findViewById(R.id.button5);
        btn6 = findViewById(R.id.button6);
        btn7 = findViewById(R.id.button7);
        btn8 = findViewById(R.id.button8);
        btn9 = findViewById(R.id.button9);
        txt = findViewById(R.id.textView2);
        AIBtn = findViewById(R.id.AIBtn);
        AIText = findViewById(R.id.AiTxt);
        // calling onClick() method
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        AIBtn.setOnClickListener(this);
        resetBtn.setOnClickListener(this);
        //initialises array
        for (int i = 0; i< 3; i++){
            for (int j = 0; j< 3; j++) {
                arr[i][j] = 0;
            }
        }
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        //reloads values
        player = savedInstanceState.getInt("Player");
        //arr = (int[][]) savedInstanceState.getSerializable("array");
        btn1.setText(savedInstanceState.getString("btn1text"));
        btn2.setText(savedInstanceState.getString("btn2text"));
        btn3.setText(savedInstanceState.getString("btn3text"));
        btn4.setText(savedInstanceState.getString("btn4text"));
        btn5.setText(savedInstanceState.getString("btn5text"));
        btn6.setText(savedInstanceState.getString("btn6text"));
        btn7.setText(savedInstanceState.getString("btn7text"));
        btn8.setText(savedInstanceState.getString("btn8text"));
        btn9.setText(savedInstanceState.getString("btn9text"));
        AIBtn.setVisibility(savedInstanceState.getInt("visible"));
        txt.setText(savedInstanceState.getString("texttxt"));
        IsAI = savedInstanceState.getBoolean("IsAItrue");
        AIText.setText(savedInstanceState.getString("AiTxt"));
        //reloads array values
        arr[0][0] = savedInstanceState.getInt("one");
        arr[0][1] = savedInstanceState.getInt("two");
        arr[0][2] = savedInstanceState.getInt("three");
        arr[1][0] = savedInstanceState.getInt("four");
        arr[1][1] = savedInstanceState.getInt("five");
        arr[1][2] = savedInstanceState.getInt("six");
        arr[2][0] = savedInstanceState.getInt("seven");
        arr[2][1] = savedInstanceState.getInt("eight");
        arr[2][2] = savedInstanceState.getInt("nine");
    }

    @Override
    public void onClick(View v) {
        Button Current;
        switch (v.getId()) {
            case R.id.button:
                Current = btn1;
                //if contains nothing, assign it player value
                if (btn1.length() == 0) {
                    arr[0][0] = ((player+1)%2)+1;   //1 or 2
                }
                break;
            case R.id.button2:
                Current = btn2;
                if (btn2.length() == 0) {
                    arr[1][0] = ((player+1)%2)+1;
                }
                break;
            case R.id.button3:
                Current = btn3;
                if (btn3.length() == 0) {
                    arr[2][0] = ((player+1)%2)+1;
                }
                break;
            case R.id.button4:
                Current = btn4;
                if (btn4.length() == 0) {
                    arr[0][1] = ((player+1)%2)+1;
                }
                break;
            case R.id.button5:
                Current = btn5;
                if (btn5.length() == 0) {
                    arr[1][1] = ((player+1)%2)+1;
                }
                break;
            case R.id.button6:
                Current = btn6;
                if (btn6.length() == 0) {
                    arr[2][1] = ((player+1)%2)+1;
                }
                break;
            case R.id.button7:
                Current = btn7;
                if (btn7.length() == 0) {
                    arr[0][2] = ((player+1)%2)+1;
                }
                break;
            case R.id.button8:
                Current = btn8;
                if (btn8.length() == 0) {
                    arr[1][2] = ((player+1)%2)+1;
                }
                break;
            case R.id.button9:
                Current = btn9;
                if (btn9.length() == 0) {
                    arr[2][2] = ((player+1)%2)+1;
                }
                break;
            case R.id.ResetBtn:
                //resets all values
                for (int i = 0; i<3; i++){
                    for (int j = 0; j<3; j++) {
                        arr[i][j] = 0;
                    }
                }
                player = 1;
                win = false;
                btn1.setText("");
                btn2.setText("");
                btn3.setText("");
                btn4.setText("");
                btn5.setText("");
                btn6.setText("");
                btn7.setText("");
                btn8.setText("");
                btn9.setText("");
                String pl = "Player 1's turn";
                txt.setText(pl);
                AIBtn.setVisibility(View.VISIBLE);
                return;
            case R.id.AIBtn:
                //enables ai
                if (!IsAI){
                    String str = "AI is 2nd player";
                    AIText.setText(str);
                    IsAI = true;
                    return;
                }
                //disables ai
                String str = "AI disabled";
                AIText.setText(str);
                IsAI = false;
                return;
            default:
                return;
        }
        //if not used yet and not win
        if (Current.length() == 0 && !win) {
            AIBtn.setVisibility(View.INVISIBLE);
            NextTurn(Current);
            Winner();
            //AI calculates best move
            if(!win && IsAI){
                AImove();
                String pl = "Player 1's turn";
                txt.setText(pl);
                Winner();
            }
        }
    }

    private void AImove(){
        int highest = -10, score, posx = -1, posy = -1;
        //checks at each position in array
        for(int i = 0; i< 3; i++){
            for(int j = 0; j<3; j++){
                //visits if not used
                if(arr[i][j] == 0) {
                    //assigns as AI move
                    arr[i][j] = 2;
                    //checks all positions
                    score = AICheck( 0, false);
                    // Undo the move
                    arr[i][j] = 0;
                    //checks for best move
                    if (score > highest)
                    {
                        posy = i;
                        posx = j;
                        highest = score;
                    }
                }
            }
        }
        AssignValues(posx, posy);
    }

    private void AssignValues(int x, int y){
        //assigns values to view and array
        arr[y][x] = 2;
        if(x == 0 && y == 0){
            btn1.setText("O");
        }
        else if(x == 0 && y == 1){
            btn2.setText("O");
        }
        else if(x == 0){
            btn3.setText("O");
        }
        else if(x == 1 && y == 0){
            btn4.setText("O");
        }
        else if(x == 1 && y == 1){
            btn5.setText("O");
        }
        else if(x == 1){
            btn6.setText("O");
        }
        else if(y == 0){
            btn7.setText("O");
        }
        else if(y == 1){
            btn8.setText("O");
        }
        else{
            btn9.setText("O");
        }
    }

    private int AICheck( int depth, boolean max) {
        int result = evaluate(depth);
        //if not an end state, return
        if (result != -100) {
            return result;
        }
        //checks for highest on AI turn
        if (max) {
            int highs = -10;
            //checks all spots in array
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    // if spot is available
                    if (arr[i][j] == 0) {
                        arr[i][j] = 2;
                        //repeats but as human next
                        int score = AICheck(depth + 1, false);
                        // undo the move
                        arr[i][j] = 0;
                        //checks for better score
                        if (score > highs) {
                            highs = score;
                        }
                    }
                }
            }
            return highs;
        }
        //checks for lowest on human turn
        else{
            int lows = 10;
            //checks all spots in array
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    // if spot is available
                    if (arr[i][j] == 0) {
                        arr[i][j] = 1;
                        //repeats but as AI next
                        int score = AICheck(depth + 1, true);
                        // undo the move
                        arr[i][j] = 0;
                        //checks for better score
                        if (score < lows) {
                            lows = score;
                        }
                    }
                }
            }
            return lows;
        }
    }

    private int evaluate(int depth){
        boolean wins;
        //checks vertically for win
        wins = vertical();
        //if not a win, checks horizontal
        if (!wins) {
            wins = horizontal();
        }
        //if not a win, checks diagonal
        if (!wins) {
            wins = Diagonal();
        }
        if (wins) {
            //returns AI win, return 1 (max)
            if((player+depth) % 2 == 0){
                return 1;
            }
            //else -1 (minimum as loss)
            return -1;
        }
        //checks there is somewhere to place
        int openSpots = 0;
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (arr[row][col] == 0) {
                    openSpots++;
                }
            }
        }
        //if no position, 0 for draw
        if (openSpots == 0){
            return 0;
        }
        //else in game state, so -100 to carry on
        return -100;
    }

    private void NextTurn(Button Current){
        if (player < 10){
            //player 1 and not more that 9 turns
            if (player % 2 == 1 && player < 9){
                Current.setText("X");
                String pl = "Player 2's turn";
                txt.setText(pl);
            }
            else if (player % 2 == 0){
                Current.setText("O");
                String pl = "Player 1's turn";
                txt.setText(pl);
            }
            else{
                Current.setText("X");
                String dr = "Draw";
                txt.setText(dr);
                win = true;//prevent re enter
            }
        }
    }

    private void Winner() {
        TextView txt = findViewById(R.id.textView2);
        boolean wins;
        //checks vertically for win
        wins = vertical();
        //if not a win, checks horizontal
        if (!wins) {
            wins = horizontal();
        }
        //if not a win, checks diagonal
        if (!wins) {
            wins = Diagonal();
        }
        if (wins) {
            //checks which player
            int num = ((player+1)%2)+1;
            String str = "Player "+ num + " Wins";
            if (num == 2 && IsAI){
                str = "AI Wins";
            }
            txt.setText(str);
            player = 10;//prevents re entering
            win = true;
        }
        player++;
    }
    private boolean vertical(){
        for (int i = 0; i < 3; i++){
            if ((arr[i][0] == arr[i][1]) && (arr[i][2] == arr[i][1]) && (arr[i][1] != 0 )){
                return true;
            }
        }
        return false;
    }
    private boolean horizontal(){
        for (int i = 0; i < 3; i++){
            if (arr[0][i] == arr[1][i] && arr[2][i] == arr[1][i] && arr[1][i] != 0){
                return true;
            }
        }
        return false;
    }
    private boolean Diagonal(){
        return ((arr[0][0] == arr[1][1]) && (arr[2][2] == arr[1][1]) && (arr[1][1] != 0)) || ((arr[0][2] == arr[1][1]) && (arr[2][0] == arr[1][1]) && (arr[1][1] != 0));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //saves values if the close or rotate device
        outState.putInt("one", arr[0][0]);
        outState.putInt("two", arr[0][1]);
        outState.putInt("three", arr[0][2]);
        outState.putInt("four", arr[1][0]);
        outState.putInt("five", arr[1][1]);
        outState.putInt("six", arr[1][2]);
        outState.putInt("seven", arr[2][0]);
        outState.putInt("eight", arr[2][1]);
        outState.putInt("nine", arr[2][2]);

        //outState.putSerializable("array", arr);
        outState.putInt("Player", player);
        outState.putString("btn1text", (String) btn1.getText());
        outState.putString("btn2text", (String) btn2.getText());
        outState.putString("btn3text", (String) btn3.getText());
        outState.putString("btn4text", (String) btn4.getText());
        outState.putString("btn5text", (String) btn5.getText());
        outState.putString("btn6text", (String) btn6.getText());
        outState.putString("btn7text", (String) btn7.getText());
        outState.putString("btn8text", (String) btn8.getText());
        outState.putString("btn9text", (String) btn9.getText());
        outState.putString("texttxt", (String) txt.getText());
        outState.putBoolean("IsAItrue", IsAI);
        outState.putInt("visible", AIBtn.getVisibility());
        outState.putString("AiTxt", (String) AIText.getText());

        super.onSaveInstanceState(outState);
    }
}
