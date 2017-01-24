package com.example.ramu.calculator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;

import java.util.StringTokenizer;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    String current_expression = "";
    String previous_expression = "";
    EditText edit_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit_text = (EditText) findViewById(R.id.et_result);
        edit_text.setText("");
        edit_text.setGravity(Gravity.BOTTOM|Gravity.RIGHT);
    }

    public void calculate_listener(View view){

        switch (view.getId()){
            case R.id.ac:
                Log.d("event","ac");
                current_expression = "0";
                previous_expression = "";
                break;
            case R.id.one:
                Log.d("event","one");
                current_expression = trimZeros(current_expression);
                current_expression += "1";
                break;
            case R.id.two:
                Log.d("event","two");
                current_expression = trimZeros(current_expression);
                current_expression += "2";
                break;
            case R.id.three:
                Log.d("event","three");
                current_expression = trimZeros(current_expression);
                current_expression += "3";
                break;
            case R.id.four:
                Log.d("event","four");
                current_expression = trimZeros(current_expression);
                current_expression +="4";
                break;
            case R.id.five:
                Log.d("event","five");
                current_expression = trimZeros(current_expression);
                current_expression +="5";
                break;
            case R.id.six:
                Log.d("event","six");
                current_expression = trimZeros(current_expression);
                current_expression +="6";
                break;
            case R.id.seven:
                Log.d("event","seven");
                current_expression = trimZeros(current_expression);
                current_expression +="7";
                break;
            case R.id.eight:
                Log.d("event","eight");
                current_expression = trimZeros(current_expression);
                current_expression +="8";
                break;
            case R.id.nine:
                Log.d("event","nine");
                current_expression = trimZeros(current_expression);
                current_expression +="9";
                break;
            case R.id.zero:
                Log.d("event","zero");
                current_expression = trimZeros(current_expression);
                current_expression +="0";
                break;
            case R.id.addition:
                Log.d("event","addition");
                if(isLastCharOperator(current_expression) == true)
                    current_expression = replaceLastChar(current_expression,'+');
                else
                    current_expression +="+";
                break;
            case R.id.minus:
                Log.d("event", "minus");
                if(isLastCharOperator(current_expression) == true)
                    current_expression = replaceLastChar(current_expression,'-');
                else
                    current_expression +="-";
                break;
            case R.id.multiplication:
                Log.d("event", "*");
                if(isLastCharOperator(current_expression) == true)
                    current_expression = replaceLastChar(current_expression,'*');
                else
                    current_expression +="*";
                break;
            case R.id.division:
                Log.d("event", "division");
                if(isLastCharOperator(current_expression) == true)
                    current_expression = replaceLastChar(current_expression,'/');
                else
                    current_expression +="/";
                break;
            case R.id.dot:
                Log.d("event", "dot");

                current_expression +=".";
                break;
            case R.id.percentile:

                Log.d("event", "percentile");
                if(isLastCharOperator(current_expression) == true)
                    current_expression = replaceLastChar(current_expression,'%');
                else
                    current_expression +="%";
                break;
            case R.id.remove_c:
                Log.d("event","remove_c");
                current_expression = removeLastChar(current_expression);
                break;
            case R.id.equals:
                Log.d("event","equals");
                String result = calculate(current_expression);
                previous_expression = current_expression;
                current_expression = result;
                edit_text.setText(previous_expression+"\n"+current_expression);
                break;
        }

        if(view.getId()!=R.id.equals)
            edit_text.setText(previous_expression+"\n"+current_expression);
    }
    public String calculate(String expression){
        Log.d("calculate",expression);
        Vector<String> operands = new Vector<>(10,2);
        String operators;

        StringTokenizer tokens = new StringTokenizer(expression,"+-*/%");
        while(tokens.hasMoreTokens()){
            //Log.d("token",tokens.nextToken());
            operands.add(tokens.nextToken());
        }
        operators = OperatorArray(expression);
        //Log.d("operands",operands.toString());
        //Log.d("operators",operators.toString());

        /* addition + operations */
        int i=0;
        int j=0;
        while((j = operators.indexOf('%',i))!=-1){

                Log.d("performing %","...");
                //int opd1 = Integer.parseInt(operands.get(j));
                //int opd2 = Integer.parseInt(operands.get(j+1));

                double d_opd1 = Double.parseDouble(operands.get(j));
                double d_opd2 = Double.parseDouble(operands.get(j+1));

                d_opd1 = d_opd1%d_opd2;

                operands.setElementAt(String.valueOf(d_opd1),j);
                operands.remove(j+1);
                Log.d("operands",operands.toString());


                StringBuffer temp_buffer = new StringBuffer(operators);
                temp_buffer.deleteCharAt(j);
                operators = temp_buffer.toString();
                Log.d("operators",operators.toString());
                i=j;
        }

        i = 0;
        j = 0;
        while((j = operators.indexOf('/',i))!=-1){

            Log.d("performing /","...");
            double d_opd1 = Double.parseDouble(operands.get(j));
            double d_opd2 = Double.parseDouble(operands.get(j+1));

            d_opd1 = d_opd1/d_opd2;


            operands.setElementAt(String.valueOf(d_opd1),j);
            operands.remove(j+1);
            Log.d("operands",operands.toString());


            StringBuffer temp_buffer = new StringBuffer(operators);
            temp_buffer.deleteCharAt(j);
            operators = temp_buffer.toString();
            Log.d("operators",operators.toString());
            i=j;
        }

        i = 0;
        j = 0;
        while((j = operators.indexOf('*',i))!=-1){

            Log.d("performing *","...");
            double d_opd1 = Double.parseDouble(operands.get(j));
            double d_opd2 = Double.parseDouble(operands.get(j+1));

            d_opd1 = d_opd1*d_opd2;


            operands.setElementAt(String.valueOf(d_opd1),j);
            operands.remove(j+1);
            Log.d("operands",operands.toString());


            StringBuffer temp_buffer = new StringBuffer(operators);
            temp_buffer.deleteCharAt(j);
            operators = temp_buffer.toString();
            Log.d("operators",operators.toString());
            i=j;
        }

        i = 0;
        j = 0;
        while((j = operators.indexOf('-',i))!=-1){

            Log.d("performing -","...");
            double d_opd1 = Double.parseDouble(operands.get(j));
            double d_opd2 = Double.parseDouble(operands.get(j+1));

            d_opd1 = d_opd1-d_opd2;


            operands.setElementAt(String.valueOf(d_opd1),j);
            operands.remove(j+1);
            Log.d("operands",operands.toString());


            StringBuffer temp_buffer = new StringBuffer(operators);
            temp_buffer.deleteCharAt(j);
            operators = temp_buffer.toString();
            Log.d("operators",operators.toString());
            i=j;
        }

        i = 0;
        j = 0;
        while((j = operators.indexOf('+',i))!=-1){

            Log.d("performing +","...");

            double d_opd1 = Double.parseDouble(operands.get(j));
            double d_opd2 = Double.parseDouble(operands.get(j+1));

            d_opd1 = d_opd1+d_opd2;

            operands.setElementAt(String.valueOf(d_opd1),j);
            operands.remove(j+1);
            Log.d("operands",operands.toString());


            StringBuffer temp_buffer = new StringBuffer(operators);
            temp_buffer.deleteCharAt(j);
            operators = temp_buffer.toString();
            Log.d("operators",operators.toString());
            i=j;
        }


        return operands.get(0);
    }

    public String OperatorArray(String expression){
        String operator_array = "";
        int count=0;
        while(count<expression.length()){
            char c = expression.charAt(count);
            if(c=='+' || c=='-' || c=='*' || c=='/' || c=='%') {
                operator_array += String.valueOf(c);
            }
            count++;
        }
        return operator_array;
    }

    public String removeLastChar(String expression){

        int len = expression.length();
        if(len >= 1)
        {
            StringBuffer buffer = new StringBuffer(expression);
            buffer.deleteCharAt(len-1);
            return buffer.toString();
        }
        else if(previous_expression.length() >=1){
            expression = previous_expression;
            previous_expression = "";
            len = expression.length();
            StringBuffer buffer = new StringBuffer(expression);
            buffer.deleteCharAt(len-1);
            return buffer.toString();
        }
        return expression;
    }

    public boolean isLastCharOperator(String expression){
        int len = expression.length();
        char last_char = expression.charAt(len-1);
        if(last_char == '+' || last_char == '-' || last_char == '*' || last_char == '/' || last_char == '%' || last_char == '.')
        {
            return true;
        }
        return  false;
    }

    public String replaceLastChar(String exp, char opt){

            int len = exp.length();
            StringBuffer buffer = new StringBuffer(exp);
            Log.d("last_char", String.valueOf(exp.charAt(len-1)));
            buffer.setCharAt(len-1,opt);
            exp = buffer.toString();
            Log.d("buffer",exp);
            return exp;
    }
    public String trimZeros(String exp)
    {
        int len = exp.length();

        if(len == 1 && exp.charAt(0) == '0') {
            exp = "";
            return exp;
        }
        if(len > 1)
        {
            char last_char = exp.charAt(len-1);
            char last_but_char = exp.charAt(len-2);
            if(last_char == '0'){
                if(last_but_char == '+' || last_but_char == '-' || last_but_char == '*' || last_but_char == '/' || last_but_char == '%'){
                    StringBuffer buffer = new StringBuffer(exp);
                    buffer.deleteCharAt(len-1);
                    return buffer.toString();
                }
            }
        }
        return exp;
    }
}
