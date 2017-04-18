package Week1.problem_3;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Packet {
    int number;     //номер пакета
    int arrival;    //время прибытия
    int duration;   //время обработки
    int timeStart;  //момент начала обработки (-1 - если пакет отброшен)

    public Packet() {
    }
}

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String s1 = sc.nextLine();
        String[] s1Array = s1.split(" ");

        int size = Integer.parseInt(s1Array[0]);  //размер буфера
        int n = Integer.parseInt(s1Array[1]);     //кол-во пакетов

        Packet[] packets = new Packet[n];   //массив пакетов

        String s2;
        String[] s2Array;

        for (int i = 0; i < n; i++) {
            s2 = sc.nextLine();
            s2Array = s2.split(" ");
            packets[i] = new Packet();
            packets[i].number = i;
            packets[i].arrival = Integer.parseInt(s2Array[0]);
            packets[i].duration = Integer.parseInt(s2Array[1]);
            packets[i].timeStart = -1;
        }

        Queue<Packet> buffer = new LinkedList<>();

        int time = 0;
        int numHandlingPacket = 0; //номер рассматриваемого пакета
        Packet RunningPacket = new Packet();  //пакет, с которым работает компьютер
        boolean isRun = false;     //работает ли (true) или простаивает компьютер

        while (numHandlingPacket < n) {
            //1. добавляем пакеты в буфер
            while (packets[numHandlingPacket].arrival <= time) {
                if (packets[numHandlingPacket].arrival == time) {
                    if (buffer.size() < size)
                        buffer.offer(packets[numHandlingPacket]);
                } else {
                    packets[numHandlingPacket].timeStart = -1;  //пакет не влезает в буфер - отбрасываем
                }
                numHandlingPacket++;
            }

            //2. Те пакеты, что компьютер обработал, больше не рассматриваем
            if (isRun) {
                if (RunningPacket.timeStart + RunningPacket.duration == time) {
                    //закончили обработку пакета, и готовы взять следующий из буфера
                    isRun = false;
                }
            }

            //3. Работа компьютера
            if (!isRun) {
                if (buffer.size() > 0) {  //если буфер не пустой
                    RunningPacket = buffer.poll();  //берём пакет из буфера
                    RunningPacket.timeStart = time;
                    isRun = true;
                } else {
                    isRun = false;  //ждём появления пакета в буфере
                }
            }

            time++;  //делаем шаг по времени
        }

        //Выводим результат работы программы
        for (int i = 0; i < n; i++) {
            System.out.println(packets[i].timeStart);
        }

    }
}
