package org.javierbriones.androidcalc;

/**
 * Created by JCLoarca on 7/15/2016 9:29 PM.
 */
public class ProcesosCalculadora {

    private double mOperand;
    private double mWaitingOperand;
    private String mWaitingOperator;
    private double mCalculatorMemory;

    public static final String SUMA = "+";
    public static final String RESTA = "-";
    public static final String MULTIPLICACION = "*";
    public static final String DIVISION = "/";

    public static final String CLEAR = "C" ;
    public static final String CLEARMEMORIA = "MC";
    public static final String AMEMORIA = "M+";
    public static final String QUITMEMORIA = "M-";
    public static final String LLAMARMEMRORIA = "MR";
    public static final String RAIZCUADRADA = "√";
    public static final String ELEVADO = "x²";
    public static final String INVERTIR = "1/x";
    public static final String CAMBIOSIGNO = "+/-";
    public static final String SENO = "sin";
    public static final String COSENO = "cos";
    public static final String TANGENTE = "tan";

    // constructor
    public ProcesosCalculadora() {
        // initialize variables upon start
        mOperand = 0;
        mWaitingOperand = 0;
        mWaitingOperator = "";
        mCalculatorMemory = 0;
    }

    public void setOperand(double operand) {
        mOperand = operand;
    }

    public double getResult() {
        return mOperand;
    }

    // used on screen orientation change
    public void setMemory(double calculatorMemory) {
        mCalculatorMemory = calculatorMemory;
    }

    // used on screen orientation change
    public double getMemory() {
        return mCalculatorMemory;
    }

    public String toString() {
        return Double.toString(mOperand);
    }

    protected double performOperation(String operator) {

        switch (operator) {
            case CLEAR:
                mOperand = 0;
                mWaitingOperator = "";
                mWaitingOperand = 0;
                break;
            case CLEARMEMORIA:
                mCalculatorMemory = 0;
                break;
            case AMEMORIA:
                mCalculatorMemory = mCalculatorMemory + mOperand;
                break;
            case QUITMEMORIA:
                mCalculatorMemory = mCalculatorMemory - mOperand;
                break;
            case LLAMARMEMRORIA:
                mOperand = mCalculatorMemory;
                break;
            case RAIZCUADRADA:
                mOperand = Math.sqrt(mOperand);
                break;
            case ELEVADO:
                mOperand = mOperand * mOperand;
                break;
            case INVERTIR:
                if (mOperand != 0) {
                    mOperand = 1 / mOperand;
                }
                break;
            case CAMBIOSIGNO:
                mOperand = -mOperand;
                break;
            case SENO:
                mOperand = Math.sin(Math.toRadians(mOperand));
                break;
            case COSENO:
                mOperand = Math.cos(Math.toRadians(mOperand));
                break;
            case TANGENTE:
                mOperand = Math.tan(Math.toRadians(mOperand));
                break;
            default:
                performWaitingOperation();
                mWaitingOperator = operator;
                mWaitingOperand = mOperand;
                break;
        }
        return mOperand;
    }

    protected void performWaitingOperation() {

        switch (mWaitingOperator){
            case SUMA:
                mOperand = mWaitingOperand + mOperand;
                break;
            case RESTA:
                mOperand = mWaitingOperand - mOperand;
                break;
            case MULTIPLICACION:
                mOperand = mWaitingOperand * mOperand;
                break;
            case DIVISION:
                if (mOperand != 0) {
                    mOperand = mWaitingOperand / mOperand;
                }
                break;
        }
    }
}