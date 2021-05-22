/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parallelconcurrent;

import java.text.DecimalFormat;
import java.util.Random;

/**
 *
 * @author Milton
 */
public class SchemeOperation {
    char op;
    double num1;
    double num2;

    public static DecimalFormat df2=new DecimalFormat("#.##");
    
    public SchemeOperation(double num1, double num2, char op) {//Define the operator to be used
        this.num1=num1;
        this.num2=num2;
        this.op=op;
    }
    
    public SchemeOperation(double num1, double num2, int productionMode) {//Operator to be chosed randomly
        this.num1=num1;
        this.num2=num2;
        Random rand = new Random();
        char[] ops = {'+','-','/','*','a','^'};
        if (productionMode==1){
            this.op=ops[rand.nextInt(6)];
        }else{
            this.op=ops[rand.nextInt(5)];
        }
        
    }
    
    public String getOperation(){//Returns the operation in the current format
        String n1= num1%1==0 ? String.valueOf((int)num1) : df2.format(num1);
        String n2= num2%1==0 ? String.valueOf((int)num2) : df2.format(num2);
        switch(this.op){
            case '^':
                return "(expt "+n1+" "+n2+")";
            case 'a':
                return "(abs "+n1+")";
            default:
                return "("+this.op+" "+n1+" "+n2+")";
        }
    }
    
    public String solve(){//Solves the operation based on the operator sign used
        
        double res=0;
        
        switch(this.op) {
        case '+':
          // Sum
          res=num1+num2;
          break;
        case '-':
          // Sub
          res=num1-num2;
          break;
        case '*':
          // Mult
          res=num1*num2;
          break;
        case '/':
          // Divide
          return this.toFraction(num1,num2);
        case '^':
          // Exponent
          res=Math.pow(num1, num2);
          break;
        case 'a':
          // Absolute value
          res=Math.abs(num1);
          break;
        default:
          throw new Error("The operator char used is not handled");
        }
        
        return res%1==0 ? String.valueOf((int)res): df2.format(res);
    }
    
    private static String toFraction(double num1, double num2){//Gets the division in the smallest fraction
        if(num2==0) return "Exception in /";
        if(num1%1!=0 || num2%1!=0) return df2.format((num1/num2));
        double denom=0;
        for(int i=0; i<(num1>num2?num1:num2);i++){
            if(num1%i==0 && num2%i==0){
                denom=i;
            }
        }
        return (int)(num1/denom)+"/"+(int)(num2/denom);
    }
    
    private double checkSquareRoot(double x, double y){
        if (y < 0){
            return Math.pow(x, y+1)/x;
        }else if (y == 0){
            return 1;
        }else{
            return x*(Math.pow(x, y-1));
        }
            
    }   
    
    public static void main(String[] args) {
        
    }
    
    
}
