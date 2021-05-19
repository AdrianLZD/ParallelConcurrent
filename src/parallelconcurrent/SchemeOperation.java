/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parallelconcurrent;

import java.util.Random;

/**
 *
 * @author Milton
 */
public class SchemeOperation {
    char op;
    double num1;
    double num2;

    public SchemeOperation(double num1, double num2, char op) {//Define the operator to be used
        this.num1=num1;
        this.num2=num2;
        this.op=op;
    }
    
    public SchemeOperation(double num1, double num2) {//Operator to be chosed randomly
        this.num1=num1;
        this.num2=num2;
        Random rand = new Random();
        char[] ops = {'+','-','/','*'};
        this.op=ops[rand.nextInt(4)];
    }
    
    public String getOperation(){
        String n1= num1%1==0 ? String.valueOf((int)num1) : String.valueOf(num1);
        String n2= num2%1==0 ? String.valueOf((int)num2) : String.valueOf(num2);
        return "("+this.op+" "+n1+" "+n2+")";
    }
    
    public String solve(){
        
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
          res=num1/num2;
          break;
        default:
          throw new Error("The operator used is not handled");
        }
        
        if(Double.isInfinite(res)) return "Exception in /";
        return res%1==0 ? String.valueOf((int)res): String.valueOf(res);
    }
    
    public static void main(String[] args) {
        SchemeOperation so = new SchemeOperation(1,2);
        System.out.println(so.getOperation());
        System.out.println(so.solve());
        
        SchemeOperation so1 = new SchemeOperation(1.2,3);
        System.out.println(so1.getOperation());
        System.out.println(so1.solve());
    }
    
    
}
