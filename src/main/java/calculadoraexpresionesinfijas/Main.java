/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package calculadoraexpresionesinfijas;

import java.util.Stack;
import javax.swing.JOptionPane;

/**
 *
 * @author Sebastian
 */
public class Main {

    public static void main(String[] args) {
        int menu = 0;
        while(menu != 1){
            int Opcion;
            int Error = 0;
            String Opciones[] = {"Ingresar expresion","Salir"};
            Opcion = JOptionPane.showOptionDialog(null, "Elige una opcion", "Ingresar", 0, JOptionPane.QUESTION_MESSAGE, null, Opciones, null);
            switch(Opcion){
                case 0:                  
                    String Expresion;
                    String ExPOS = "";
                    String ExPRE = "";
                    Stack<Character> Entrada = new Stack<>();
                    Expresion = (JOptionPane.showInputDialog("Expresion: "));
                    Expresion = Expresion.replaceAll("\\s","");
                    for(int i=0;i<=Expresion.length()-1;i++){  
                    char Simbolo = Expresion.charAt(i);
                    if(Simbolo == '('){
                        Entrada.push(Simbolo);
                    }else 
                        if(Simbolo == ')'){
                            while(Entrada.peek() != '('){
                                ExPOS = ExPOS + Entrada.pop();
                            }
                            Simbolo = Entrada.pop();  
                    }else 
                        if(esOperando(Simbolo) == true){
                            ExPOS = ExPOS + Simbolo;
                        }
                        else
                            if(esOperador(Simbolo) == true){
                                while(Entrada.empty() == false && Prioridad(Simbolo) <= Prioridad(Entrada.peek())){
                                    ExPOS = ExPOS + Entrada.pop();
                            }
                            Entrada.push(Simbolo);
                            }
                            else{
                                JOptionPane.showMessageDialog(null,"Expresion erronea" + "\n" +  Simbolo + " no es valido" + "\n" + "Ingresela de nuevo");  
                                Error = 1;
                            }
                    }
                    if(Error == 1){
                        break;
                    }
                    while(Entrada.empty() == false){
                        ExPOS = ExPOS + Entrada.pop();
                    } 
                    
                    for(int i = Expresion.length()-1;i >=0 ;i--){                      
                       char Simbolo = Expresion.charAt(i);
                          if(Simbolo == ')'){                   
                              Entrada.push(Simbolo);
                          }else
                              if(Simbolo == '('){
                                  while(Entrada.peek() != ')'){
                                        ExPRE = ExPRE + Entrada.pop();
                                    }
                                    Simbolo = Entrada.pop(); 
                              }else
                                  if(esOperando(Simbolo) == true){
                                    ExPRE = ExPRE + Simbolo;
                                  }else
                                      if(esOperador(Simbolo) == true){
                                        while(Entrada.empty() == false && Prioridad(Simbolo) <= Prioridad(Entrada.peek())){
                                            ExPRE = ExPRE + Entrada.pop();
                                        }
                                        Entrada.push(Simbolo);
                                        }
                                        else{
                                            Error = 1;
                                        }
                        }
                    while(Entrada.empty() == false){
                        ExPRE = ExPRE + Entrada.pop();
                    }
                    String Aux = "";
                    for(int i = 0; i <= ExPRE.length()-1; i++){
                        Aux = ExPRE.charAt(i) + Aux;         
                    }
                    ExPRE = Aux;
                                            
                    JOptionPane.showMessageDialog(null,"Expresion infija: " + Expresion + "\n" + "Postfijo: " + ExPOS + "\n" + "Prefijo: " + ExPRE);
                    break;
                case 1:
                    menu = 1;
                    System.exit(0);
                    break;        
            }
        }      
    }
  
    public static int Prioridad(char letra){
        int Prioridad = 0;
        switch(letra){
            case '^':
                Prioridad = 5;
                break;
            case '*':
                Prioridad = 4;
                break;
            case '/':
                Prioridad = 4;
                break;
            case '%':
                Prioridad = 3;
                break;
            case '+':
                Prioridad = 2;
                break;
            case '-':
                Prioridad = 2;
                break;   
            case '(':
                Prioridad = 1;
                break;
            case ')':
                Prioridad = 1;
                break;
            
        }
    return Prioridad;
    } 
    
    public static boolean esOperando(char Simbolo){   
        for(int i = 0; i <= 63; i++){
            char [] Array = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','ñ','o','p','q','r','s','t','u','v','w','x','y','z',
            'A','B','C','D','E','F','G','H','I','J','K','L','M','N','Ñ','O','P','Q','R','S','T','U','V','W','X','Y','Z','1','2','3','4',
            '5','6','7','8','9','0'};
            if(Array[i]==Simbolo){
                return true;
            }
        }
        return false;
    }
    
    public static boolean esOperador(char Simbolo){   
        for(int i = 0; i <= 5; i++){
            char [] Array = {'^','*','/','%','+','-'};
            if(Array[i]==Simbolo){
                return true;
            }
        }
        return false;
    }
}
