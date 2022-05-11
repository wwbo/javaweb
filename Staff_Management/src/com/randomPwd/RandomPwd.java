package com.randomPwd;

public class RandomPwd {
	public static String getRandomPwd() {
		String pwd = "";
			for (int j = 0; j < 6; j++) {
				double rand = Math.random();
				double randTri = Math.random() * 3;
				if (randTri >= 0 && randTri < 1) {
					pwd += (char) (rand * ('9' - '0') + '0');
				} else if (randTri >= 1 && randTri < 2) {
					pwd += (char) (rand * ('Z' - 'A') + 'A');
				} else {
					pwd += (char) (rand * ('z' - 'a') + 'a');
				}
			}
			return pwd;
	}
}
