package Week2.problem_2;

import java.util.Scanner;

class Pair {
    int n1;
    int n2;

    public Pair(int n1, int n2) {
        this.n1 = n1;
        this.n2 = n2;
    }

    //сравниваем сперва по n2, потом по n1
    public int compareTo(Pair another) {
        if (this.n2 < another.n2) {
            return -1;
        }
        else if (this.n2 == another.n2) {
            if (this.n1 < another.n1) {
                return -1;
            }
            else if (this.n1 == another.n1) {
                return 0;
            } else {
                return 1;
            }
        } else {
            return 1;
        }
    }
}

//двоичная мин-куча
class MinHeap {
    private Pair[] H;

    private int size;
    private int maxSize;

    //создаём пустую кучу
    public MinHeap(int maxSize) {
        H = new Pair[maxSize];

        this.size = 0;
        this.maxSize = maxSize;
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
        Pair tmp = H[i];
        H[i] = H[j];
        H[j] = tmp;
    }

    //всплытие элемента
    public void siftUp(int i) {
        while ((i > 0) && (H[parent(i)].compareTo(H[i]) > 0)) {
            swap(parent(i), i);
            i = parent(i);
        }
    }

    //утапливание элемента
    public void siftDown(int i) {
        int minIndex = i;

        int l = leftChild(i);
        if ((l < size) && (H[l].compareTo(H[minIndex]) < 0)) {
            minIndex = l;
        }

        int r = rightChild(i);
        if ((r < size) && (H[r].compareTo(H[minIndex]) < 0)) {
            minIndex = r;
        }

        if (i != minIndex) {
            swap(i, minIndex);
            siftDown(minIndex);
        }
    }

    public void insert(Pair p) {
        if (size == maxSize) {
            throw new IndexOutOfBoundsException();
        }
        H[size] = p;
        siftUp(size);
        size++;
    }

    public Pair extractMin() {
        Pair result = H[0];
        H[0] = H[size-1];
        size--;
        siftDown(0);
        return result;
    }

    public void remove(int i) {
        int minimum = (Integer.MIN_VALUE - 10) * (-1);
        H[i] = new Pair(minimum, minimum);
        siftUp(i);
        extractMin();
    }

    public void changePriority(int i, Pair p) {
        Pair oldp = H[i];
        H[i] = p;
        if (p.compareTo(oldp) < 0) {
            siftUp(i);
        } else {
            siftDown(i);
        }
    }

    public boolean isFull() {
        return (size == maxSize);
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
                System.out.print(
                        "(" + H[n].n1 + "," + H[n].n2 + ")" + " "
                );
                n++;
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
        String[] s1Arr = s1.split(" ");

        int n = Integer.parseInt(s1Arr[0]);  //n процессоров
        int m = Integer.parseInt(s1Arr[1]);  //m задач

        String s2 = sc.nextLine();

        String[] s2Arr = s2.split(" ");

        int[] t = new int[m];
        Pair[] result = new Pair[m];

        for (int i = 0; i < m; i++) {
            t[i] = Integer.parseInt(s2Arr[i]);
        }

        MinHeap minHeap = new MinHeap(n);  //куча размером n

        int n_curr_task = 0;
        int N_remain_tasks = m;  //сколько задач осталось обработать?
        int n_curr_proc = 0;

        while (N_remain_tasks > 0) {
            if (!minHeap.isFull()) {  //куча не заполнена
                if (t[n_curr_task] > 0) {
                    Pair pair = new Pair(n_curr_proc, t[n_curr_task]);
                    minHeap.insert(pair);
                    result[n_curr_task] = new Pair(n_curr_proc, 0);
                    n_curr_proc++;
                    n_curr_task++;
                    N_remain_tasks--;
                } else {
                    result[n_curr_task] = new Pair(n_curr_proc, 0);
                    n_curr_task++;
                    N_remain_tasks--;
                }
            } else {  //куча заполнена
                Pair currPair = minHeap.extractMin();
                result[n_curr_task] = new Pair(currPair.n1, currPair.n2);

                Pair newPair = new Pair(currPair.n1, currPair.n2 + t[n_curr_task]);
                minHeap.insert(newPair);

                n_curr_task++;
                N_remain_tasks--;
            }
        }

        //выводим результат
        for (int i = 0; i < m; i++) {
            Pair pair = result[i];
            System.out.println(pair.n1 + " " + pair.n2);
        }

    }

}
