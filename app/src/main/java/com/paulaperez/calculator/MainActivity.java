package com.paulaperez.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView[] tv_numeros = new TextView[11];
    private TextView[] tv_operators = new TextView[4];
    private TextView tv_Equal, tv_Clear, result;

    private int numtecla;

    private int operation, dotCount=0, symCount=0, returnValueResult=1, eCount=0;
    private float op1, op2, opResult;
    private String returnValue="", symbolOp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Numbers
        tv_numeros[0] = (TextView) findViewById(R.id.tv0);
        tv_numeros[1] = (TextView) findViewById(R.id.tv1);
        tv_numeros[2] = (TextView) findViewById(R.id.tv2);
        tv_numeros[3] = (TextView) findViewById(R.id.tv3);
        tv_numeros[4] = (TextView) findViewById(R.id.tv4);
        tv_numeros[5] = (TextView) findViewById(R.id.tv5);
        tv_numeros[6] = (TextView) findViewById(R.id.tv6);
        tv_numeros[7] = (TextView) findViewById(R.id.tv7);
        tv_numeros[8] = (TextView) findViewById(R.id.tv8);
        tv_numeros[9] = (TextView) findViewById(R.id.tv9);
        tv_numeros[10] = (TextView) findViewById(R.id.tvDot);

        //Operators
        tv_operators[0] = (TextView) findViewById(R.id.tvPlus);
        tv_operators[1] = (TextView) findViewById(R.id.tvMinus);
        tv_operators[2] = (TextView) findViewById(R.id.tvMult);
        tv_operators[3] = (TextView) findViewById(R.id.tvDiv);
        result=(TextView) findViewById(R.id.result);


        tv_Equal= (TextView) findViewById(R.id.tvEqual);
        tv_Clear= (TextView) findViewById(R.id.clear);




        tv_Clear.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        addNewNumber("0","0");
                        op1=0;
                        op2=0;
                        opResult=0;
                        returnValue="";
                        returnValueResult=3;
                        symbolOp="";
                        dotCount=0;
                        symCount=0;
                        eCount=0;

                    }
                }
        );



        for( numtecla = 0; numtecla < 11; numtecla++ ){
            tv_numeros[numtecla].setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                           /* addNewNumber(((TextView)view).getText().toString(),"1");
                            returnValue=returnValue+((TextView)view).getText().toString();*/
                           if(returnValue.equals("0") && ((TextView)view).getText().toString().equals("0") ){
                               returnValue="0";
                               result.setText("0");


                           }else if(returnValue.equals("0") && !((TextView)view).getText().toString().equals(".")) {

                               returnValue=((TextView)view).getText().toString();
                               addNewNumber(((TextView) view).getText().toString(), "1");


                           }

                               else{

                               if (((TextView) view).getText().toString().equals(".") && dotCount >= 1) {
                                   dotCount = dotCount + 1;
                                   returnValue = returnValue + "";
                               } else if (((TextView) view).getText().toString().equals(".")) {

                                   addNewNumber(((TextView) view).getText().toString(), "1");
                                   returnValue = returnValue + ((TextView) view).getText().toString();

                                   dotCount = dotCount + 1;
                               } else {
                                   addNewNumber(((TextView) view).getText().toString(), "1");
                                   returnValue = returnValue + ((TextView) view).getText().toString();


                               }
                           }
                            //Toast.makeText(MainActivity.this, returnValue, Toast.LENGTH_LONG).show();
                        }
                    }
            );
        }

        for( operation = 0; operation < 4; operation++ ){
            tv_operators[operation].setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {


                            if(symCount>=1) {

                            }

                             else{
                                    addNewNumber(((TextView)view).getText().toString(),"1");
                                    symbolOp=((TextView)view).getText().toString();
                                    op1=Float.parseFloat(returnValue);
                                    returnValue="";
                                    dotCount=0;
                                    symCount = symCount + 1;



                            }


                        }
                    }
            );
        }
        //returnValue=addNewNumber(((TextView)view).getText().toString(),"1");

        tv_Equal.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        //Toast.makeText(MainActivity.this, returnValue, Toast.LENGTH_LONG).show();

                        if(eCount>=1){

                            eCount=1;
                        }
                        else if(symCount==1 && returnValue.equals("")==false) {
                        addNewNumber(((TextView)view).getText().toString(),"1");
                            op2=Float.parseFloat(returnValue);



                            switch (symbolOp) {
                            case "-":
                                opResult = op1 - op2;
                                result.setText(Float.toString(opResult));
                                returnValue = "";
                                op2=0;
                                returnValueResult = 1;

                                break;
                            case "+":
                                opResult = op1 + op2;
                                result.setText(Float.toString(opResult));
                                returnValue = "";
                                op2=0;
                                returnValueResult = 1;

                                break;

                            case "*":
                                opResult = op1 * op2;
                                result.setText(Float.toString(opResult));
                                returnValue = "";
                                op2=0;
                                returnValueResult = 1;

                                break;

                            case "/":
                                opResult = op1 / op2;
                                result.setText(Float.toString(opResult));
                                returnValue = "";
                                op2=0;
                                returnValueResult = 1;


                                break;
                            default:


                                returnValue = "";
                                op2=0;
                                returnValueResult = 1;

                                break;
                        }

                        eCount=1;
                        }else{

                            //Toast.makeText(MainActivity.this, returnValue, Toast.LENGTH_LONG).show();

                        }





                        //Toast.makeText(MainActivity.this, ((TextView)view).getText().toString(), Toast.LENGTH_LONG).show();
                    }
                }


        );

    }

    private String addNewNumber(String number,  String cl){

        TextView result=(TextView) findViewById(R.id.result);

        String numDefault1=result.getText().toString();;


        String numDefault;

        if(cl.equals("0")){
            numDefault = "";
            returnValueResult = 0;
            symCount = 0;
            returnValue="";
            eCount=0;
            numDefault = numDefault + number;
            result.setText(numDefault);







        }else {
           numDefault=result.getText().toString();

           if(numDefault1.equals("0")){

               if(number.equals("+") || number.equals("-") || number.equals("*") || number.equals("/") || number.equals(".") ) {
                   numDefault = numDefault + number;
                   result.setText(numDefault);
               }else{

                   numDefault = "";
                   returnValueResult = 0;
                   symCount = 0;
                   eCount=0;
                   numDefault = numDefault + number;
                   result.setText(numDefault);
               }

           }

            else if(numDefault.equals("0") && returnValueResult==1) {
                numDefault = "";
                returnValueResult = 0;
                symCount = 0;
               eCount=0;

               numDefault = numDefault + number;
                result.setText(numDefault);


            }else if(returnValueResult==1) {

                numDefault = "";
                returnValueResult = 0;
                symCount = 0;
               eCount=0;

               numDefault = numDefault + number;
                result.setText(numDefault);

            }

        else{
            numDefault = numDefault + number;

            result.setText(numDefault);}

        }

        return numDefault;
    }



}

