import java.io.*;
import java.util.*;
  
class TestValidLisp
{

    public static void main(String[] args){
    
        Scanner in = new Scanner(System.in);
        System.out.println("Enter a lisp string: ");
        String input = in.nextLine();
        
        System.out.println("String is valid lisp: " + isValid(input));
    
    }
    
    public boolean isValid(String input){
        boolean valid = true;
        Stack<Character> stack = new Stack<>();
        
        for(int i=0 ; i < input.length(); i++){
            
            char c = input.charAt(i);
            
            if(c == '('){
                stack.push(c);
                continue;
            }else if(c == ')'){
                char check = stack.pop();
                if(check != '('){
                    valid= false;
                }
            }
        }
        return valid;
        
    }
   
}