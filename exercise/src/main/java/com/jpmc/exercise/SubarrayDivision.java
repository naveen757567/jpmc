package com.jpmc.exercise;

import java.util.ArrayList;
import java.util.List;

public class SubarrayDivision {

	private List<Integer> arr;

	private List<Integer> p1;
	private Integer s1;

	private List<Integer> p2;
	private Integer s2;

	private List<Integer> p3;
	private Integer s3;

	private Integer min;

	public SubarrayDivision(List<Integer> arr) {
		this.arr = arr;
		min = Integer.MAX_VALUE;
		p1 = p2 = p3 = null;
		s1 = s2 = s3 = 0;
	}

	public List<Integer> getArr() {
		return arr;
	}

	public List<Integer> getP1() {
		return p1;
	}

	public Integer getS1() {
		return s1;
	}

	public List<Integer> getP2() {
		return p2;
	}

	public Integer getS2() {
		return s2;
	}

	public List<Integer> getP3() {
		return p3;
	}

	public Integer getS3() {
		return s3;
	}

	public Integer getMin() {
		return min;
	}

	public boolean validate() {
		if (arr == null || arr.size() < 3) {
			return false;
		}
		return true;
	}

	public void compute() {

		if (!this.validate()) {
			return;
		}

		int t1 = 0;
		int n = arr.size();

		for (int i = 0; i < n - 2; i++) {

			int t2 = 0;
			t1 += arr.get(i);

			for (int j = i + 1; j < n - 1; j++) {

				int t3 = 0;
				t2 += arr.get(j);

				for (int k = j + 1; k < n; k++) {
					t3 += arr.get(k);
				}

				// Computing the absolute difference of their sums
				int diff = Math.abs(t1 - t2 - t3);

				// Updating the minimum sum and storing sublists
				if (diff < min) {
					s1 = t1;
					s2 = t2;
					s3 = t3;
					min = diff;
					p1 = arr.subList(0, i + 1);
					p2 = arr.subList(i + 1, j + 1);
					p3 = arr.subList(j + 1, n);
				}
			}
		}
	}

	public void printSubArrays() {
		System.out.println("Part 1: " + p1 + ", Sum: " + s1);
		System.out.println("Part 2: " + p2 + ", Sum: " + s2);
		System.out.println("Part 3: " + p3 + ", Sum: " + s3);

		System.out.println("Difference: |" + s1 + " - " + s2 + " - " + s3 + "| = " + min);
	}

	public static void main(String[] args) {

		if (args.length > 0) {
			List<Integer> arr = new ArrayList<>();

			for (String arg : args) {
				try {
					arr.add(Integer.parseInt(arg));
				} catch (NumberFormatException e) {
					System.out.println("Please enter integers only");
					return;
				}
			}

			SubarrayDivision obj = new SubarrayDivision(arr);
			if (obj.validate()) {
				obj.compute();
				obj.printSubArrays();
			} else {
				System.out.println("Array size must be >= 3");
			}

		} else {
			System.out.println("No arguments found");
		}

	}

}
