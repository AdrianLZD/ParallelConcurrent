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
    
    public SchemeOperation(double num1, double num2) {//Operator to be chosed randomly
        this.num1=num1;
        this.num2=num2;
        Random rand = new Random();
        char[] ops = {'+','-','/','*','^'};
        this.op=ops[rand.nextInt(5)];
    }
    
    public String getOperation(){//Returns the operation in the current format
        String n1= num1%1==0 ? String.valueOf((int)num1) : df2.format(num1);
        String n2= num2%1==0 ? String.valueOf((int)num2) : df2.format(num2);
        if (this.op == '^'){
            return "(expt "+n1+" "+n2+")";
        }else{
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
          //Exponent
          res=Math.pow(num1, num2);
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
    
    
    public static void main(String[] args) {
        SchemeOperation so = new SchemeOperation(1,0,'/');
        System.out.println(so.getOperation());
        System.out.println(so.solve());
        
        SchemeOperation so2 = new SchemeOperation(0,0,'/');
        System.out.println(so2.getOperation());
        System.out.println(so2.solve());
        
        SchemeOperation so1 = new SchemeOperation(1.2,3);
        System.out.println(so1.getOperation());
        System.out.println(so1.solve());
        
        SchemeOperation so3 = new SchemeOperation(234,34,'/');
        System.out.println(so3.getOperation());
        System.out.println(so3.solve());
        
        SchemeOperation so4 = new SchemeOperation(2,4,'/');
        System.out.println(so4.getOperation());
        System.out.println(so4.solve());
        
        SchemeOperation so5 = new SchemeOperation(5,2.2,'/');
        System.out.println(so5.getOperation());
        System.out.println(so5.solve());
    }
    
    
}
