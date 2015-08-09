package package1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.Stack;

public class RandomName {

	// private String name;
	//
	// private String mName = "";
	// private String fName = "";

	private Stack<String> maleNames;
	private Stack<String> femaleNames;

	public RandomName() {
		// name = "";
		// names = new ArrayList<String>();

		maleNames = new Stack<String>();
		femaleNames = new Stack<String>();

		getLists();

		// maleNames = new ArrayList<String>();
		// femaleNames = new ArrayList<String>();
	}

	public String getName() {
		String name = "";
		if (generateRandom(1, 2) == 1) {
			name = maleNames.get(generateRandom(1, maleNames.size()));
		} else {
			name = femaleNames.get(generateRandom(1, femaleNames.size()));
		}
		return name;
	}

	public int generateRandom(int min, int max) {
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}

	public void getLists() {
		ClassLoader classLoader = getClass().getClassLoader();
		File listMale = new File(classLoader.getResource("male.txt").getFile());
		File listFemale = new File(classLoader.getResource("female.txt")
				.getFile());

		try (BufferedReader br = new BufferedReader(new FileReader(listMale))) {
			String line = br.readLine();
			// String result = yourString.replaceAll("[-+.^:,]","");
			// line = line.replaceAll(" .", "");

			while (line != null) {
				// mName += line;

				maleNames.push(line);
				line = br.readLine();
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try (BufferedReader br = new BufferedReader(new FileReader(listFemale))) {
			String line = br.readLine();

			while (line != null) {
				// fName += line;

				femaleNames.push(line);
				line = br.readLine();
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// public void cleanLists() {
	// mName = mName.replaceAll("[^a-zA-Z]+", ",");
	// fName = fName.replaceAll("[^a-zA-Z]+", ",");
	//
	// maleNames = mName.split(",");
	// femaleNames = fName.split(",");
	//
	// // mName = mName.replaceAll(".", "");
	// // fName = fName.replaceAll(".", "");
	//
	// // System.out.println(maleNames.length);
	// // System.out.println(femaleNames.length);
	// }

	// public void setLists() {
	// BufferedWriter writer = null;
	//
	// // Get file from resources folder
	// ClassLoader classLoader = getClass().getClassLoader();
	// File listMale = new File(classLoader.getResource("male.txt").getFile());
	// File listFemale = new File(classLoader.getResource("female.txt")
	// .getFile());
	//
	// try {
	// writer = new BufferedWriter(new FileWriter(listMale));
	//
	// for (int i = 0; i < maleNames.length; i++) {
	// writer.write(maleNames[i]);
	// writer.newLine();
	// }
	//
	// } catch (Exception e) {
	// e.printStackTrace();
	// } finally {
	// try {
	// // Close the writer regardless of what happens...
	// writer.close();
	// } catch (Exception e) {
	// }
	// }
	// try {
	// writer = new BufferedWriter(new FileWriter(listFemale));
	//
	// for (int i = 0; i < femaleNames.length; i++) {
	// writer.write(femaleNames[i]);
	// writer.newLine();
	// }
	//
	// } catch (Exception e) {
	// e.printStackTrace();
	// } finally {
	// try {
	// // Close the writer regardless of what happens...
	// writer.close();
	// } catch (Exception e) {
	// }
	// }
	// }

}
