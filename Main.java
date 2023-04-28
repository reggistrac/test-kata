import java.util.Scanner;

public class Main {

public static String calc(String input){
	String arab = "0123456789";
	String rim = "IVX";
	String de = "+-*/";
	String[] cufru = {"0","I","II","III","IV","V","VI","VII","VIII","IX","X"};
	int first = -1;
	int second;
	int deystvie;
	int result = -99;
	String rimResult = "";

	int strokaLength = input.length();
	if(strokaLength < 5 || strokaLength > 11){
		return "Короче или длиннее.";
	}
	int n = -1;
	char sy1 = input.charAt(0);
	int indexOfSy1 = arab.indexOf(sy1);
	int indexOfR1 = rim.indexOf(sy1);
	char sy2 = input.charAt(1);
	char sy3 = input.charAt(2);
	if(indexOfSy1 > -1){
		int indexOfSy2 = arab.indexOf(sy2);
		if(indexOfSy2 > -1){
			if(sy3 != ' ' || sy1 != '1' || sy2 != '0'){
				return "Или 1 не 1 или 2 не 0 или 3 не пробел.";
			}else{
				first = indexOfSy1*10 + indexOfSy2;
				n = 3;
			}
		}else if(sy2 == ' '){
			first = indexOfSy1;
			System.out.println("Первое число однозначное.");
			n = 2;
		}else{
			return "2 не араб и не пробел.";
		}
		deystvie = de.indexOf(input.charAt(n));
		if(deystvie < 0 || input.charAt(n+1) != ' '){
			return "Нет действия.";
		}
		second = 0;
		for(int i = n+2;i < strokaLength;i++){
			char element = input.charAt(i);
			int indexOfElement = arab.indexOf(element);
			if(indexOfElement > -1){
				second = second*10 + indexOfElement;
			}else{
				return "Второе не число.";
			}
		}
		if(second > 10){
			return "Второе больше десяти.";
		}
		switch (deystvie){
			case 0:
				result = first + second;
				n = result % 10;
				rimResult =  arab.charAt(n) + rimResult;
				result = result/10;
				if(result > 0){
					rimResult = arab.charAt(result) + rimResult;
				}
				break;
			case 1:
				result = first - second;
				if(result > 0) {
					n = result % 10;
					rimResult = arab.charAt(n) + rimResult;
					result = result / 10;
					if (result > 0) {
						rimResult = arab.charAt(result) + rimResult;
					}
				}else if(result < 0){
					result = result * -1;
					n = result % 10;
					rimResult = arab.charAt(n) + rimResult;
					result = result / 10;
					if (result > 0) {
						rimResult = arab.charAt(result) + rimResult;
					}
					rimResult = "-" + rimResult;
				}else{
					rimResult = "0";
				}
				break;
			case 2:
				result = first * second;
				if(result == 100){
					rimResult = "100";
				}else{
					n = result % 10;
					rimResult =  arab.charAt(n) + rimResult;
					result = result/10;
					if(result > 0){
						rimResult = arab.charAt(result) + rimResult;
					}
				}
				break;
			case 3:
				if(second != 0) {
					result = first / second;
					n = result % 10;
					rimResult =  arab.charAt(n) + rimResult;
					result = result/10;
					if(result > 0){
						rimResult = arab.charAt(result) + rimResult;
					}
				}else{
					return "На ноль делить нельзя!";
				}
				break;
		}
		return rimResult;
	}else if(indexOfR1 > -1){
		if(sy1 == 'X'){
			if(sy2 == ' '){
				first = 10;
				n = 1;
			}else{
				return "Первый Х, второй не пробел";
			}
		}else if(sy1 == 'V'){
			first = 5;
			for(int i=1;i<5;i++){
				char sy = input.charAt(i);
				if(sy == ' '){
					n = i;
					break;
				}else if(sy == 'I'){
					first = first+1;
					n = i;
				}else{
					n = i;
					System.out.println((i+1)+" не тот.");
					break;
				}
			}
		}else{
			switch (sy2){
				case ' ':
					first = 1;
					n = 1;
					break;
				case 'X':
					first = 9;
					n = 2;
					break;
				case 'V':
					first = 4;
					n = 2;
					break;
				case 'I':
					if(sy3 == ' '){
						first = 2;
						n = 2;
					}else if(sy3 == 'I'){
						first = 3;
						n = 3;
					}else{
						return "Третий символ не тот.";
					}
					break;
				default:
					return "Второе символ не тот";
			}
		}
		if(input.charAt(n) != ' '){
			return "После первого не пробел.";
		}
		System.out.println(first + " -- " + n);
		n++;
		if(n < strokaLength-1) {
			deystvie = de.indexOf(input.charAt(n));
		}else{
			return "Нет действия(строка кончилась).";
		}
		if(deystvie < 0 || input.charAt(n+1) != ' '){
			return "Нет действия.";
		}
		second = 0;
		n = n +2;
		char sy;
		if(n < strokaLength) {
			sy = input.charAt(n);
		}else{
			return "Строка закончилась";
		}
		if(sy == 'X'){
			if(n+1 < strokaLength){
				return "Второе больше 10";
			}else{
				second = 10;
			}
		}else if(sy == 'V'){
			second = 5;
			for(int i=n+1;i<strokaLength;i++) {
				if (input.charAt(i) == 'I' && second<8) {
					second = second + 1;
				} else {
					second = -1;
					System.out.println("Второе на V не то.");
					break;
				}
			}
			if(second < 0){
				return "second < 0";
			}
		}else{
			n++;
			if(n == strokaLength){
				second = 1;
			}else{
				sy = input.charAt(n);
				if(sy == 'X' && n == strokaLength-1){
					second = 9;
				}else if(sy =='V' && n == strokaLength-1){
					second = 4;
				}else if(sy == 'I'){
					if(n == strokaLength-1){
						second = 2;
					}else if(input.charAt(n+1) == 'I' && n+1 == strokaLength-1){
						second = 3;
					}else{
						return "Второе не число. #213";
					}
				}else{
					return "Второе не число. #217";
				}
			}
		}
		System.out.println("  "+first+"  "+deystvie+"  "+second);
		switch (deystvie){
			case 0:
				result = first + second;
				break;
			case 1:
				result = first - second;
				if(result < 1){
					System.out.println("Римское не может быть меньше 1.");
				}
				break;
			case 2:
				result = first * second;
				break;
			case 3:
				if(second != 0) {
					result = first / second;
				}else{
					System.out.println("На ноль делить нельзя!");
				}
				break;
		}
		if(result > 0){
			if(result == 100){
				rimResult ="C";
				System.out.println(rimResult);
			}else {
				n = result % 10;
				if(n > 0) {
					rimResult = cufru[n];
				}
				result = result / 10;
				switch (result){
					case 1:
						rimResult = "X" + rimResult;
						break;
					case 2:
						rimResult = "XX" + rimResult;
						break;
					case 3:
						rimResult = "XXX" + rimResult;
						break;
					case 4:
						rimResult = "XL" + rimResult;
						break;
					case 5:
						rimResult = "L" + rimResult;
						break;
					case 6:
						rimResult = "LX" + rimResult;
						break;
					case 7:
						rimResult = "LXX" + rimResult;
						break;
					case 8:
						rimResult = "LXXX" + rimResult;
						break;
					case 9:
						rimResult = "XC" + rimResult;
						break;
				}
				System.out.println(rimResult);
			}
		}
	}else {
		System.out.println("Не рим и не араб.");
	}

	return rimResult;
}

public static void main(String[] args){
	Scanner scanner = new Scanner(System.in);
	String arab = "0123456789";
	String rim = "IVX";
	String de = "+-*/";
	String[] cufru = {"0","I","II","III","IV","V","VI","VII","VIII","IX","X"};
	int first = -1;
	int second;
	int deystvie;
	int result = -99;
	String rimResult = "";

	while (true){
		System.out.println("Введите выражение #1main");
		String verajenie = scanner.nextLine();
		if(verajenie.equals("exit")){
			break;
		}
		rimResult = calc(verajenie);
		System.out.println(rimResult);
	}
/*	while(true) {
		System.out.println("Введите выражение");
		String verajenie = scanner.nextLine();
		if(verajenie.equals("exit")){
			break;
		}
		int strokaLength = verajenie.length();
		if(strokaLength < 5 || strokaLength > 11){
			System.out.println("Короче или длиннее.");
			continue;
		}
		int n = -1;
		char sy1 = verajenie.charAt(0);
		int indexOfSy1 = arab.indexOf(sy1);
		int indexOfR1 = rim.indexOf(sy1);
		char sy2 = verajenie.charAt(1);
		char sy3 = verajenie.charAt(2);
		if(indexOfSy1 > -1){
			int indexOfSy2 = arab.indexOf(sy2);
			if(indexOfSy2 > -1){
				if(sy3 != ' ' || sy1 != '1' || sy2 != '0'){
					System.out.println("Или 1 не 1 или 2 не 0 или 3 не пробел.");
					continue;
				}else{
					first = indexOfSy1*10 + indexOfSy2;
					System.out.println("Первое число двухзначное.");
					n = 3;
				}
			}else if(sy2 == ' '){
				first = indexOfSy1;
				System.out.println("Первое число однозначное.");
				n = 2;
			}else{
				System.out.println("2 не араб и не пробел.");
				continue;
			}
			deystvie = de.indexOf(verajenie.charAt(n));
			if(deystvie < 0 || verajenie.charAt(n+1) != ' '){
				System.out.println("Нет действия.");
				continue;
			}
			second = 0;
			for(int i = n+2;i < strokaLength;i++){
				char element = verajenie.charAt(i);
				int indexOfElement = arab.indexOf(element);
				if(indexOfElement > -1){
					second = second*10 + indexOfElement;
				}else{
					System.out.println("Второе не число.");
					break;
				}
			}
			if(second > 10){
				System.out.println("Второе больше десяти.");
				continue;
			}
			switch (deystvie){
				case 0:
					result = first + second;
					System.out.println(result);
					break;
				case 1:
					result = first - second;
					System.out.println(result);
					break;
				case 2:
					result = first * second;
					System.out.println(result);
					break;
				case 3:
					if(second != 0) {
						result = first / second;
						System.out.println(result);
					}else{
						System.out.println("На ноль делить нельзя!");
					}
					break;
			}
		}else if(indexOfR1 > -1){
			if(sy1 == 'X'){
				if(sy2 == ' '){
					first = 10;
					n = 1;
				}else{
					System.out.println("Первый Х, второй не пробел");
					continue;
				}
			}else if(sy1 == 'V'){
				first = 5;
				for(int i=1;i<5;i++){
					char sy = verajenie.charAt(i);
					if(sy == ' '){
						n = i;
						break;
					}else if(sy == 'I'){
						first = first+1;
						n = i;
					}else{
						n = i;
						System.out.println((i+1)+" не тот.");
						break;
					}
				}
			}else{
				switch (sy2){
					case ' ':
						first = 1;
						n = 1;
						break;
					case 'X':
						first = 9;
						n = 2;
						break;
					case 'V':
						first = 4;
						n = 2;
						break;
					case 'I':
						if(sy3 == ' '){
							first = 2;
							n = 2;
						}else if(sy3 == 'I'){
							first = 3;
							n = 3;
						}else{
							System.out.println("Третий символ не тот.");
							continue;
						}
						break;
					default:
						System.out.println("Второе символ не тот");
						continue;
				}
			}
			if(verajenie.charAt(n) != ' '){
				System.out.println("После первого не пробел.");
				continue;
			}
			System.out.println(first + " -- " + n);
			n++;
			if(n < strokaLength-1) {
				deystvie = de.indexOf(verajenie.charAt(n));
			}else{
				System.out.println("Нет действия(строка кончилась).");
				continue;
			}
			if(deystvie < 0 || verajenie.charAt(n+1) != ' '){
				System.out.println("Нет действия.");
				continue;
			}
			second = 0;
			n = n +2;
			char sy;
			if(n < strokaLength) {
				sy = verajenie.charAt(n);
			}else{
				System.out.println("Строка закончилась");
				continue;
			}
			if(sy == 'X'){
				if(n+1 < strokaLength){
					System.out.println("Второе больше 10");
					continue;
				}else{
					second = 10;
				}
			}else if(sy == 'V'){
				second = 5;
				for(int i=n+1;i<strokaLength;i++) {
					if (verajenie.charAt(i) == 'I' && second<8) {
						second = second + 1;
					} else {
						second = -1;
						System.out.println("Второе на V не то.");
						break;
					}
				}
				if(second < 0){
					System.out.println("second < 0");
					continue;
				}
			}else{
				n++;
				if(n == strokaLength){
					second = 1;
				}else{
					sy = verajenie.charAt(n);
					if(sy == 'X' && n == strokaLength-1){
						second = 9;
					}else if(sy =='V' && n == strokaLength-1){
						second = 4;
					}else if(sy == 'I'){
						if(n == strokaLength-1){
							second = 2;
						}else if(verajenie.charAt(n+1) == 'I' && n+1 == strokaLength-1){
							second = 3;
						}else{
							System.out.println("Второе не число. #213");
							continue;
						}
					}else{
						System.out.println("Второе не число. #217");
						continue;
					}
				}
			}
			System.out.println("  "+first+"  "+deystvie+"  "+second);
			switch (deystvie){
				case 0:
					result = first + second;
					break;
				case 1:
					result = first - second;
					if(result < 1){
						System.out.println("Римское не может быть меньше 1.");
					}
					break;
				case 2:
					result = first * second;
					break;
				case 3:
					if(second != 0) {
						result = first / second;
					}else{
						System.out.println("На ноль делить нельзя!");
					}
					break;
			}
			if(result > 0){
				if(result == 100){
					rimResult ="C";
					System.out.println(rimResult);
				}else {
					n = result % 10;
					if(n > 0) {
						rimResult = cufru[n];
					}
					result = result / 10;
					switch (result){
						case 1:
							rimResult = "X" + rimResult;
							break;
						case 2:
							rimResult = "XX" + rimResult;
							break;
						case 3:
							rimResult = "XXX" + rimResult;
							break;
						case 4:
							rimResult = "XL" + rimResult;
							break;
						case 5:
							rimResult = "L" + rimResult;
							break;
						case 6:
							rimResult = "LX" + rimResult;
							break;
						case 7:
							rimResult = "LXX" + rimResult;
							break;
						case 8:
							rimResult = "LXXX" + rimResult;
							break;
						case 9:
							rimResult = "XC" + rimResult;
							break;
					}
					System.out.println(rimResult);
				}
			}
		}else {
			System.out.println("Не рим и не араб.");
		}
	}*/
}
}