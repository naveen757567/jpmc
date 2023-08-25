package com.jpmc.exercise;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

		int n = arr.size();
		Map<Integer, List<List<Integer>>> map = new HashMap<>();

		for (int i = 0; i < n - 2; i++) {
			for (int j = i + 1; j < n - 1; j++) {
				List<List<Integer>> part = new ArrayList<>();

				// Finding the sub arrays and computing their sum
				List<Integer> l1 = arr.subList(0, i + 1);
				int s1 = l1.stream().reduce(Integer::sum).get();
				part.add(l1);

				List<Integer> l2 = arr.subList(i + 1, j + 1);
				int s2 = l2.stream().reduce(Integer::sum).get();
				part.add(l2);

				List<Integer> l3 = arr.subList(j + 1, n);
				int s3 = l3.stream().reduce(Integer::sum).get();
				part.add(l3);

				// Computing the absolute difference of their sums
				int diff = Math.abs(s1 - s2 - s3);

				// Storing the sub arrays along with their difference
				map.put(diff, part);

				// Updating the minimum sum
				min = Math.min(min, diff);

			}
		}

		// Storing the result
		List<List<Integer>> res = map.get(min);

		p1 = res.get(0);
		s1 = p1.stream().reduce(Integer::sum).get();

		p2 = res.get(1);
		s2 = p2.stream().reduce(Integer::sum).get();

		p3 = res.get(2);
		s3 = p3.stream().reduce(Integer::sum).get();

	}

	public void printSubArrays() {

		System.out.println("Result:");

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
