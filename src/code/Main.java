package code;

import java.lang.reflect.Array;
import java.util.Scanner;

public class Main {
	
	public static void fillSequentially(int[] menu) { //1. fills in array sequentially
		for(int i = 0; i < menu.length; i++)
			menu[i] = i+1;
		System.out.println("array filled sequentially");
	}
	
	public static void fillRandom(int[] menu) { //2. fills in array randomly
		int min1 = 1, max1 = 999;
		
		for(int i = 0; i < menu.length; i++) {
			int randomNum = (int)(Math.random()*(max1 - min1 + 1) + min1);
			menu[i] = randomNum;
		}
		
		System.out.println("array filled randomly");
	}
	
	
	public static boolean checkOrder(int[] menu) { //3. checks if it is sorted
		boolean order = true;
		
		for(int i = 0; i < menu.length; i++) {
			if(i+1 != menu[i]) {
				order = false;
				return order;
			}
		}
		
		return order;
	}
	
	public static void display(int[] menu) { //4. displays grid
		for(int i = 0; i < 100; i++) {
			if(menu[i] >= 100)
				System.out.print(menu[i] + " ");
			else if(menu[i] >= 10)
				System.out.print(menu[i] + "  ");
			else
				System.out.print(menu[i] + "   ");
			if((i+1) % 10 == 0)
				System.out.println();
		}
	}
	
	public static void shuffle(int[] menu) { // 5. shuffles array
		int max1 = 99, min1 = 0;
		int swap = 0; // number swapped
		
		for(int i = 0; i < menu.length; i++) {
			int randomNum = (int)(Math.random()*(max1 - min1 + 1) + min1);
			
			swap = menu[i];
			menu[i] = menu[randomNum];
			menu[randomNum] = swap;
		}
		
		System.out.println("array shuffled");
	}
	
	public static boolean linearSearch(int[] menu, int find1) { // 6. searches linearly. works best for shuffled lists
		boolean found = true;
		
		for(int i = 0; i < menu.length; i++) 
			if(menu[i] == find1)
				return found;
		
		found = false;
		return found;
	}
	
	public static boolean binarySearch(int[] menu, int find2) { //7. binary search. works best for unshuffled lists
		int first = 0, last = 99, middle; //takes min and max and middle
		boolean found = true;
		
		while(first != last-1) {
			if(menu[first] == find2 || menu[last] == find2)
				return found;
			else {
				middle = (first+last) / 2;
				if(menu[middle] == find2)
					return found;
				else if(menu[middle] > find2)
					last = middle;
				else if(menu[middle] < find2)
					first = middle;
			}
		}
		
		
		found = false;
		return found;
	}
	
	public static void selectSort(int[]menu) { //8. selection sort FIX
		int min, max, minpos = 0, maxpos = 0, swapmin = 0, swapmax = 0;
		
		for(int x = 0, z = menu.length-1; x < z; x++, z--) {
			min = menu[x];
			max = menu[x];
			
			for(int y = x; y <= z; y++) {
				if(menu[y] > max) {
					max = menu[y]; //assigns new max
					maxpos = y;
				}
				if(menu[y] < min) { // assigns new min
					min = menu[y];
					minpos = y;
				}
			}
			swap(menu, minpos, x);  //swaps smallest value
			
			if(menu[minpos] == max) //exception
				swap(menu, minpos, z+1);
			 else
				swap(menu, maxpos, z); // swaps largest values
			
		}
	}
	
	
	public static void insertionSort(int[] menu) { //9. insertion sort FIX
		for(int i = 1; i < menu.length; i++) {
			int count = i-1;
			int start = count;
			int finish;
			boolean done = false;
			
			while(menu[i] < menu[count] && count != 0) {
				count--;
				done = true;
			}
				
			if(done == true) {
				finish = count+1;
				swapInsertion(menu, i, start, finish);
			}
		}
	}
	
	public static void swapInsertion(int[] menu, int i, int start, int finish) { //swap algorithm for insertion sort 
		int swap = menu[i];
		
		for(int x = start; x >= finish; x--) {
			menu[x+1] = menu[x];
		}
		
		menu[finish] = swap;
	}
	
	public static int[] rSort(int[] menu) { // 10. radix sort. best for large numbers
		int[] copy = new int[100];
		
		for(int a = 0; a < 3; a++) { // tracks digit
			int count = 0; // keeps track of position in copy array
			
			for(int b = 0; b < 10; b++) { // tracks value of digit
				for(int c = 0; c < menu.length; c++) { //moves through original array
					if(getDigit(menu, a, c) == b) {
						copy[count] = menu[c];
						count++; // shifts to next position in copy array
					}
				}
			}
			
			menu = copy;
			copy = new int[100]; // new copy
		}
		
		return menu;
		
	}
	
	public static int getDigit(int[] menu, int a, int c) { // gets single digit
		// divide 10 depending on length, then %10
		int digit = 0;
		
		if(a == 0) 
			return digit = menu[c] % 10;
		if(a == 1)
			return digit = (menu[c] / 10) % 10;
		if(a == 2)
			return digit = (menu[c] / 100) % 10;
		
		return digit;
	}
	
	public static void quickSort(int[] menu, int low, int high) { // 11. quick sort
		if(low >= high) // if done sorting
			return;
		
		int leftPoint = low, rightPoint = high;
		int pivot = menu[high]; // sets pivot point
		
		while(leftPoint < rightPoint) {
			while(menu[leftPoint] <= pivot && leftPoint < rightPoint)  // checks less than pivot
				leftPoint++;
			
			while(menu[rightPoint] >= pivot && leftPoint < rightPoint)  //checks greater than pivot
				rightPoint--;
			
			swap(menu, leftPoint, rightPoint); // swaps the values that are on the wrong side
		}
		swap(menu, leftPoint, high);
		
		quickSort(menu, low, leftPoint-1); // recursive. sorts left side
		quickSort(menu, leftPoint+1, high); // recursive, sorts right side
	}
	
	
	public static void swap(int[] menu, int num1, int num2) { // swap algorithm
		int temp = menu[num1];
		menu[num1] = menu[num2];
		menu[num2] = temp;
	}
	
	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		
		int[] menu = new int[100];
		
		int option = 0;
		
		do {
			System.out.println("0. Exit\r\n"
					+ "1. Populate the array sequentially (1-100)\r"
					+ "2. Populate the array randomly (1-999)\r"
					+ "3. Checks to see if the array is sorted\r"
					+ "4. Displays the array grid\r"
					+ "5. Shuffles the array\r"
					+ "6. Linear search\r"
					+ "7. Binary search\r"
					+ "8. Selection sort\r"
					+ "9. Insertion sort\r"
					+ "10. Radix sort\r"
					+ "11. Quick sort");
		
			
			System.out.println("---------------------------------------------------------");
			
			option = input.nextInt(); // gets user input
			
			if(option == 1) 
				fillSequentially(menu);
			if(option == 2)
				fillRandom(menu);
			if(option == 3)
				System.out.println(checkOrder(menu));
			if(option == 4) // prints array
				display(menu);
			if(option == 5) // shuffles array
				shuffle(menu);
			if(option == 6) {
				System.out.print("find value: ");
				int find1 = input.nextInt();
				System.out.println(linearSearch(menu, find1));
			}
			if(option == 7) {
				System.out.print("find value: ");
				int find2 = input.nextInt();
				System.out.println(binarySearch(menu, find2));
			}
			if(option == 8) {
				selectSort(menu);
				display(menu);
			}
			if(option == 9) {
				insertionSort(menu);
				display(menu);
			}
			if(option == 10) {
				menu = rSort(menu);
				display(menu);
			}
			if(option == 11) {
				quickSort(menu, 0, menu.length-1);
				display(menu);
			}
			if(option == 12) {
				fillSequentially(menu);
				shuffle(menu);
				display(menu);
			}
				
			
			System.out.println("---------------------------------------------------------");
			
		}while(option != 0);
		
		System.out.println("program exited");
		

	}
}


