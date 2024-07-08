package tree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class TreeUtil {

	public static void printTree(TreeNode root) {
		List<List<String>> lines = new ArrayList<>();
		List<TreeNode> level = new ArrayList<>();
		List<TreeNode> next = new ArrayList<>();

		level.add(root);
		int nn = 1;
		int widest = 0;

		while (nn != 0) {
			List<String> line = new ArrayList<>();

			nn = 0;
			for (TreeNode n : level) {
				if (n == null) {
					line.add(null);

					next.add(null);
					next.add(null);
				} else {
					String aa = String.valueOf(n.val);
					line.add(aa);
					if (aa.length() > widest)
						widest = aa.length();

					next.add(n.left);
					next.add(n.right);

					if (n.left != null)
						nn++;
					if (n.right != null)
						nn++;
				}
			}

			if (widest % 2 == 1)
				widest++;

			lines.add(line);

			List<TreeNode> tmp = level;
			level = next;
			next = tmp;
			next.clear();
		}

		int perPiece = lines.get(lines.size() - 1).size() * (widest + 4);
		for (int i = 0; i < lines.size(); i++) {
			List<String> line = lines.get(i);
			int hpw = (int)Math.floor(perPiece / 2f) - 1;

			if (i > 0) {
				for (int j = 0; j < line.size(); j++) {
					char c = ' ';
					if (j % 2 == 1) {
						if (line.get(j - 1) != null) {
							c = (line.get(j) != null) ? '┴' : '┘';
						} else {
							if (j < line.size() && line.get(j) != null)
								c = '└';
						}
					}
					System.out.print(c);

					if (line.get(j) == null) {
						for (int k = 0; k < perPiece - 1; k++) {
							System.out.print(" ");
						}
					} else {
						for (int k = 0; k < hpw; k++) {
							System.out.print(j % 2 == 0 ? " " : "─");
						}
						System.out.print(j % 2 == 0 ? "┌" : "┐");
						for (int k = 0; k < hpw; k++) {
							System.out.print(j % 2 == 0 ? "─" : " ");
						}
					}
				}
				System.out.println();
			}

			for (int j = 0; j < line.size(); j++) {
				String f = line.get(j);
				if (f == null)
					f = "";
				int gap1 = (int)Math.ceil(perPiece / 2f - f.length() / 2f);
				int gap2 = (int)Math.floor(perPiece / 2f - f.length() / 2f);

				for (int k = 0; k < gap1; k++) {
					System.out.print(" ");
				}
				System.out.print(f);
				for (int k = 0; k < gap2; k++) {
					System.out.print(" ");
				}
			}
			System.out.println();

			perPiece /= 2;
		}
	}

	public static TreeNode toTree(Integer[] arr) {
		Deque<TreeNode> que = new LinkedList<>();
		TreeNode root = new TreeNode(arr[0]);
		que.offer(root);
		int i = 1;
		while (!que.isEmpty() && i < arr.length) {
			int max = que.size();
			for (int j = 0; j < max; j++) {
				TreeNode cur = que.poll();
				if (i < arr.length && arr[i] != null) {
					TreeNode left = new TreeNode(arr[i]);
					cur.left = left;
					que.offer(left);
				}
				i++;
				if (i < arr.length && arr[i] != null) {
					TreeNode right = new TreeNode(arr[i]);
					cur.right = right;
					que.offer(right);
				}
				i++;
			}
		}
		return root;
	}

	public static void main(String[] args) {
		Integer[] integers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
		TreeNode treeNode = TreeUtil.toTree(integers);
		System.out.println(treeNode);

	}
}
