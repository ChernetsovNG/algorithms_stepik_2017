package Week2.problem_1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//двоичная мин-куча
class MinHeap {
    private int[] H;

    private int size;
    private int maxSize;

    public int nSwaps;
    public List<String> whichSwaps;

    //создаём пустую кучу
    public MinHeap(int maxSize) {
        H = new int[maxSize];

        this.size = 0;
        this.maxSize = maxSize;
        this.nSwaps = 0;
        this.whichSwaps = new ArrayList<>();
    }

    //создаём кучу из массива
    public MinHeap(int[] A) {
        int n = A.length;

        H = A;

        this.size = n;
        this.maxSize = n;
        this.nSwaps = 0;
        this.whichSwaps = new ArrayList<>();

        for (int i = n/2; i >= 0; i--) {
            siftDown(i);
        }
    }

    private int parent(int i) {
        return (i-1)/2;
    }

    private int leftChild(int i) {
        return 2*i+1;
    }

    private int rightChild(int i) {
        return 2*i+2;
    }

    private void swap(int i, int j) {
        int tmp = H[i];
        H[i] = H[j];
        H[j] = tmp;
        nSwaps++;
        whichSwaps.add(i + " " + j);
    }

    //всплытие элемента
    public void siftUp(int i) {
        while ((i > 0) && (H[parent(i)] > H[i])) {
            swap(parent(i), i);
            i = parent(i);
        }
    }

    //утапливание элемента
    public void siftDown(int i) {
        int minIndex = i;

        int l = leftChild(i);
        if ((l < size) && (H[l] < H[minIndex])) {
            minIndex = l;
        }

        int r = rightChild(i);
        if ((r < size) && (H[r] < H[minIndex])) {
            minIndex = r;
        }

        if (i != minIndex) {
            swap(i, minIndex);
            siftDown(minIndex);
        }
    }

    public void insert(int p) {
        if (size == maxSize) {
            throw new IndexOutOfBoundsException();
        }
        H[size] = p;
        siftUp(size);
        size++;
    }

    public int extractMin() {
        int result = H[0];
        H[0] = H[size-1];
        size--;
        siftDown(0);
        return result;
    }

    public void remove(int i) {
        H[i] = (Integer.MAX_VALUE - 10) * (-1);
        siftUp(i);
        extractMin();
    }

    public void changePriority(int i, int p) {
        int oldp = H[i];
        H[i] = p;
        if (p < oldp) {
            siftUp(i);
        } else {
            siftDown(i);
        }
    }

    //печатаем кучу в виде "пирамиды"
    public void printHeap() {
        int n = 0;
        int k = 1;

        System.out.println(H[n++]);

        while (n < size) {
            k *= 2;
            for (int f = 0; f < k; f++) {
                if (n == size) {
                    break;
                }
                System.out.print(H[n++] + " ");
            }
            System.out.println("");
        }
    }

}

public class Main {
    public static void main(String[] args) {
        //Ввод исходных данных
        Scanner sc = new Scanner(System.in);

        String s1 = sc.nextLine();

        int n = Integer.parseInt(s1);

        String s2 = sc.nextLine();
        String[] s2Arr = s2.split(" ");

        int[] A = new int[n];

        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(s2Arr[i]);
        }

        //Решение
        MinHeap minHeap = new MinHeap(A);

        System.out.println(minHeap.nSwaps);
        int m = minHeap.whichSwaps.size();
        for (int i = 0; i < m; i++) {
            System.out.println(minHeap.whichSwaps.get(i));
        }
    }
}
