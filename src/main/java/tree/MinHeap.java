package tree;

// 最小堆类
public class MinHeap {
	private int[][] heap;
	private int size;
	private int capacity;

	public MinHeap(int capacity) {
		this.capacity = capacity;
		this.size = 0;
		this.heap = new int[capacity][2]; // 每个元素存储 [值, 频率]
	}

	public void insert(int value, int frequency) {
		if (size < capacity) {
			heap[size][0] = value;
			heap[size][1] = frequency;
			heapifyUp(size);
			size++;
		} else if (frequency > heap[0][1]) {
			heap[0][0] = value;
			heap[0][1] = frequency;
			heapifyDown(0);
		}
	}

	public int extractMin() {
		int minValue = heap[0][0];
		heap[0][0] = heap[size - 1][0];
		heap[0][1] = heap[size - 1][1];
		size--;
		heapifyDown(0);
		return minValue;
	}

	private void heapifyUp(int index) {
		while (index > 0) {
			int parentIndex = (index - 1) / 2;
			if (heap[index][1] < heap[parentIndex][1]) {
				swap(index, parentIndex);
				index = parentIndex;
			} else {
				break;
			}
		}
	}

	private void heapifyDown(int index) {
		while (index < size) {
			int leftChild = 2 * index + 1;
			int rightChild = 2 * index + 2;
			int smallest = index;

			if (leftChild < size && heap[leftChild][1] < heap[smallest][1]) {
				smallest = leftChild;
			}
			if (rightChild < size && heap[rightChild][1] < heap[smallest][1]) {
				smallest = rightChild;
			}

			if (smallest != index) {
				swap(index, smallest);
				index = smallest;
			} else {
				break;
			}
		}
	}

	private void swap(int i, int j) {
		int[] temp = heap[i];
		heap[i] = heap[j];
		heap[j] = temp;
	}
}
