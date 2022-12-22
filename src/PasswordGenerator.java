

public class PasswordGenerator {
	
	public static char getRandChar(boolean num, boolean let, boolean spe) {
		int type = -1;
		boolean works = false;
		while(!works) {
			type = (int) (Math.random()*3);
			if(!num && type == 0) continue;
			if(!let && type == 1) continue;
			if(!spe && type == 2) continue;
			works = true;
		}
		
		switch(type) {
			case 0:
				return (char) (((int) (Math.random()*10))+'0');
			case 1:
				int initial = (int) (Math.random() * 26 + 65);
				if(Math.random() > .5) initial += 32;
				return (char) initial;
			case 2:
				return (char) (Math.random() * 15 + 33);
			}
		return '~';
	}
	
	public static String generatePassword(int len, boolean num, boolean let, boolean spe) throws Exception {
		if(!num && !let && !spe) return "";
		
		char[] lets = new char[len];
		for(int i = 0; i < len; i++) {
			lets[i] = getRandChar(num, let, spe);
			if(lets[i] == '~') throw new Exception("Error in generating password");
		}
		return new String(lets);
	}
	
	public static void main(String[] args) throws Exception {
		
		Frame myFrame = new Frame();
		
//		Scanner userIn = new Scanner(System.in);
//		System.out.println("Enter desired password length: ");
//		int length = userIn.nextInt();
//		System.out.println("Password: " + generatePassword(length));
//		userIn.close();
	}
	
}
