package matrix;

import io.onelinelister.OneLineListReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import cmdGA2.CommandLine;
import cmdGA2.SingleArgumentOption;
import cmdGA2.returnvalues.InfileValue;

import pair.Pair;

public class MatrixComparator {
	// ///////////////////////////////////////////
	// Instance Variables
	private Map<Pair<String, String>, Double> matrix_1;
	// Values From The First Matrix Read
	private Map<Pair<String, String>, Double> matrix_2;
	// Values From The Second Matrix Read
	private double std_dv;
	// Field to store the std_dv when calculating the mean
	private static StringListParser stringlistparser = new StringListParser();

	// //////////////////////////////////////////
	// Constructor
	public MatrixComparator(Map<Pair<String, String>, Double> matrix_1,
			Map<Pair<String, String>, Double> matrix_2) {
		super();
		this.setMatrix_1(matrix_1);
		this.setMatrix_2(matrix_2);

	}

	// //////////////////////////////////////////
	// Public Interface

	/**
	 * Given two matrices of the same dimension and labels, calculates the ratio
	 * of each element of matrix 1 over matrix 2. Returns the mean value and
	 * sets the std dv to std_dv field.
	 */
	public double getRatio() {

		double mean = 0;

		double std_dv = 0;

		List<Double> ratios = new ArrayList<Double>();

		int N_elements = 0;

		Set<Pair<String, String>> keySet = this.getMatrix_1().keySet();
		
		for (Pair<String, String> pair : keySet) {

			Double value_mat_1 = this.getMatrix_1().get(pair);

			Double value_mat_2 = this.getMatrix_2().get(pair);

			if (value_mat_1 != null && value_mat_2 != null) {

				if (value_mat_2>0) {
				
					double ratio = value_mat_1 / value_mat_2;
	
					ratios.add(ratio);
	
					mean = mean + ratio;
	
					N_elements++;
				
				}

			}

		}

		mean = mean / N_elements;

		for (Double ratio : ratios) {

			std_dv = std_dv + Math.sqrt(Math.pow(ratio - mean, 2)) / N_elements;

		}

		this.setStdDv(std_dv);

		return mean;

	}

	public void setStdDv(double std_dv) {
		this.std_dv = std_dv;
	}

	public double getStdDv() {
		return this.std_dv;
	}

	public Map<Pair<String, String>, Double> getMatrix_1() {
		return matrix_1;
	}

	public void setMatrix_1(Map<Pair<String, String>, Double> matrix_1) {
		this.matrix_1 = matrix_1;
	}

	public Map<Pair<String, String>, Double> getMatrix_2() {
		return matrix_2;
	}

	public void setMatrix_2(Map<Pair<String, String>, Double> matrix_2) {
		this.matrix_2 = matrix_2;
	}

	// //////////////////////////////////////////
	// Main Executable
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// //////////////////////////
		// Create Comman Line Object
		CommandLine cmd = new CommandLine();

		// //////////////////////////
		// Add Options to Command Line
		SingleArgumentOption<File> inMat1Opt = new SingleArgumentOption<File>(
				cmd, "-mat1", new InfileValue(), null);
		SingleArgumentOption<File> inMat2Opt = new SingleArgumentOption<File>(
				cmd, "-mat2", new InfileValue(), null);

		// ////////////////////////////
		// Parser Command Line
		cmd.readAndExitOnError(args);

		// ////////////////////////////
		// Validate Command Line
		if (!inMat1Opt.isPresent() || !inMat2Opt.isPresent()) {
			System.err.println("-mat1 && -mat2 options are mandatory.");
			System.exit(1);
		}

		// ////////////////////////////
		// Get Values From Command Line
		File mat_1_file = inMat1Opt.getValue();
		File mat_2_file = inMat2Opt.getValue();

		// ////////////////////////////
		// Main Code
		Map<Pair<String, String>, Double> mat1 = MatrixComparator
				.readMatrix(mat_1_file);
		Map<Pair<String, String>, Double> mat2 = MatrixComparator
				.readMatrix(mat_2_file);
		// Read files with the matrices

		MatrixComparator mc = new MatrixComparator(mat1, mat2);
		// Creates the object tha makes the calculus

		System.out.println("Mean     : " + mc.getRatio());
		System.out.println("Std. dv. : " + mc.getStdDv());
		// Makes the calulus and print out the result.
	}

	/**
	 * Reads a matrix in a three columns separated by tab format. The two first
	 * columns are labels to identify a given value. Those labes can be
	 * interpreted as column labels and row labels of a matrix Labels are parsed
	 * as strings. The third column is double value.
	 * 
	 * <pre>
	 * Example:
	 * label_colum_1    label_row_1     1
	 * label_colum_1    label_row_2     2
	 * label_colum_2    label_row_1     3
	 * label_colum_2    label_row_2     4
	 * </pre>
	 * 
	 * @param input
	 * @return
	 */
	public static Map<Pair<String, String>, Double> readMatrix(File input) {

		try {
			BufferedReader br = new BufferedReader(new FileReader(input));

			return MatrixComparator.readMatrix(br);

		} catch (FileNotFoundException e) {
			System.err.println("There Was an error: " + e.getMessage());
			System.exit(1);
		}

		return null;

	}

	/**
	 * Reads a matrix in a three columns separated by tab format. The two first
	 * columns are labels to identify a given value. Those labes can be
	 * interpreted as column labels and row labels. Labels are parsed as
	 * strings. The third column is double value.
	 * 
	 * <pre>
	 * Example
	 * label_colum_1    label_row_1     1
	 * label_colum_1    label_row_2     2
	 * label_colum_2    label_row_1     3
	 * label_colum_2    label_row_2     4
	 * </pre>
	 * 
	 * @param input
	 * @return
	 */
	public static Map<Pair<String, String>, Double> readMatrix(BufferedReader br) {

		OneLineListReader<List<String>> reader = new OneLineListReader<List<String>>(
				MatrixComparator.stringlistparser);

		try {
			List<List<String>> readValues = reader.read(br);

			Map<Pair<String, String>, Double> result = new HashMap<Pair<String, String>, Double>();

			for (List<String> list : readValues) {

				String pos_1 = list.get(0).trim();

				String pos_2 = list.get(1).trim();

				if (pos_1.compareToIgnoreCase(pos_2) < 0) {
					String tmp = pos_1;
					pos_1 = pos_2;
					pos_2 = tmp;
				}
				Double value;
				try {
					value = Double.valueOf(list.get(2));
				} catch (Exception e) {
					value = null;
				}

				result.put(new Pair<String, String>(pos_1, pos_2), value);

			}
			
			return result;

		} catch (IOException e) {
			System.err.println("There Was an error: " + e.getMessage());
			System.exit(1);

		}

		return null;

	}

}
