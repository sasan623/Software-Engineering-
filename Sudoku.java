import java.io.BufferedWriter;

import java.io.File;

import java.io.FileInputStream;

import java.io.FileNotFoundException;

import java.io.FileWriter;

import java.io.IOException;

import java.io.InputStreamReader;

import java.io.Reader;

import java.io.UnsupportedEncodingException;

import java.util.ArrayList;

import java.util.Random;
/**
 * Demo class
 * 
 * @author lei
 * @date 2020/3/25
 */
public class Sudoku{

	public static int m;

	public static int n;
	
	public static String inputFilename;

	public static String outputFilename;
	
	
	/** matrix[][]���������������*/    
    public static Boolean cheak(int Shudu[][],int x,int y,int n,int m){

        for (int i = 0; i < m; i++) {

            /**��������������ظ������򷵻�false������3��5��7����*/        	                

                 if (i != x && Shudu[i][y] == n) {

                     return false;

                 }                

                 if (i != y && Shudu[x][i] == n) {

                     return false;

                 }

             }
        
        if(m==3)
        {
        	return true;
        }
        
        else if(m==4){
            
            int tempRow = x / 2 * 2;

            int templine = y / 2 * 2;

            for (int i = tempRow; i < tempRow+2; i++) {

                for (int j = templine; j <templine+2; j++) {

                    /**���С�Ź����еķ�(x,y)�������ϵ�ֵ�ظ�Ϊn������false*/

                    if (i != x && j != y && Shudu[i][j] == n) {

                        return false;

                    }

                }

            }

        }
        
        else if(m==5){
        	return true;
        }
        
        else if(m==6){
           
            int tempRow = x / 2*2;

            int templine = y / 3*3;

            for (int i =tempRow; i < tempRow + 2; i++) {

                for (int j = templine; j < templine + 3; j++) {

                    /**���С�Ź����еķ�(x,y)�������ϵ�ֵ�ظ�Ϊn������false*/

                    if (i != x && j != y && Shudu[i][j] == n) {

                        return false;

                    }

                }

            }

        }
        
        else if(m==7){
        	return true;
        }
        
        else if(m==8){

            int tempRow = x / 4 * 4;

            int templine = y / 2 * 2;

     
            for (int i = tempRow; i < tempRow + 4; i++) {

                for (int j = templine; j < templine + 2; j++) {

                    /**���С�Ź����еķ�(x,y)�������ϵ�ֵ�ظ�Ϊn������false*/

                    if (i != x && j != y && Shudu[i][j] == n) {

                        return false;

                    }

                }

            }

        }
        
        else if(m==9){

            int temprow = x / 3 * 3;

            int templine = y / 3 * 3;

     
            for (int i = temprow; i < temprow + 3; i++) {

                for (int j = templine; j < templine + 3; j++) {

                    /**���С�Ź����еķ�(x,y)�������ϵ�ֵ�ظ�Ϊn������false*/

                    if (i != x && j != y && Shudu[i][j] == n) {

                        return false;

                    }

                }

            }

        }
                   
        return true;

    }
    
    public static int matrix[][] = new int[10][10];

    public static void setmatrix(int[][] matrix) {

        Sudoku.matrix = matrix;

    }
	
 /**����cheak�������ж������Ƿ���ȷ����*/
    public static void matrixbackTrace(int k,int m) throws IOException {

        if (k == (m*m)) {

          String outfile="E:\\output9.txt";

            try{

            FileWriter fw = new FileWriter(outfile,true);
            
            for(int i=0;i<m;i++){

            for(int j=0;j<m;j++){ 

                fw.write(matrix[i][j]+" ");

                }

                fw.write("\r\n");

            }

             fw.write("\r\n");
            
             fw.close(); /** �ر� */

            }

            catch (Exception e) {  

                e.printStackTrace();  

            }  

            return;
        }

        int x = k / m;

        int y = k % m;

        if (matrix[x][y] == 0) {

            for (int i = 1; i <= m; i++) {

                matrix[x][y] = i;

                if (cheak(matrix,x, y,i, m)) {

                	matrixbackTrace(k + 1,m);

                }

            }

            matrix[x][y] = 0;

        } else {

        	matrixbackTrace(k + 1,m);

        }

    }
/**���ݷ�������Ⲣд���ļ�*/
    public static void loadArgs(String args[]){

    	if(args.length>0&&args!=null){

    		for(int i=0;i<args.length;i++){

    			switch (args[i]) {

				case "-i":

					inputFilename = args[++i];

					break;

				case "-o": 

					outputFilename = args[++i];

					break;

				case "-m": 

					m=Integer.valueOf(args[++i]);

					break;

				case "-n":

					n=Integer.valueOf(args[++i]);

				    break;



				default:

					break;

				}

    		}

    	}

    }
/**��ʼ�������еĴ���Ĳ���������*/
    public static void main(String[] args) throws IOException {
    	
    	loadArgs(args);

    	int generateSudoku[][]=new int[10][10];   

		File infile = new File("E:\\input9.txt");

		Reader reader = new InputStreamReader(new FileInputStream(infile),"UTF-8"); 


		int tc; int i=0; int j=0;

		    while ((tc = reader.read()) != -1) {  

		    if ( (((char) tc) != '\n') &&(((char) tc) != ' ')) {  

		        if(i<m){

		        	if(j<m){

		        		if(tc!=13){

		        			generateSudoku[i][j]=((char) tc)-48;

			        		j++;

		        		}

		        	}else{	

		        		i++;

		        		j=0;

		        		generateSudoku[i][j]=((char) tc)-48;

		        	}

		        }

		        if(i==m){

		        	if(n!=0){

		        		setmatrix(generateSudoku);

			            matrixbackTrace(0,m);

			            n--;

			            i=0;j=0;

		        	}
		        	
		        }

		    }  

		}

		reader.close();
        
    }

}
/**main������Ҫ�Ƕ����ļ������ػ��ݷ�*/