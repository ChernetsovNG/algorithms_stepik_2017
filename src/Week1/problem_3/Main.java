package Week1.problem_3;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Packet {
    int number;     //номер пакета
    int arrival;    //время прибытия
    int duration;   //время обработки
    int timeStart;  //момент начала обработки (-1 - если пакет отброшен)
    boolean isProcessed;  //был ли пакет обработан? (добавлен в буфер или отброшен)

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
            packets[i].isProcessed = false;
        }

        Queue<Packet> buffer = new LinkedList<>();

        int time = 0;
        int numHandlingPacket = 0; //номер рассматриваемого пакета
        Packet RunningPacket = new Packet();  //пакет, с которым работает компьютер
        boolean isRun = false;     //работает ли (true) или простаивает компьютер
        int countCompProcessedPackets = 0;  //кол-во обработанных компьютером пакетов

        while ((numHandlingPacket < n) || (countCompProcessedPackets < n)) {
            //2. Те пакеты, что компьютер обработал, больше не рассматриваем
            if (isRun) {
                if (RunningPacket.timeStart + RunningPacket.duration == time) {
                    //закончили обработку пакета, и готовы взять следующий из буфера
                    buffer.poll();  //удаляем пакет из буфера
                    countCompProcessedPackets++;
                    isRun = false;
                }
            }

            //3. Работа компьютера
            while (!isRun) {
                if (buffer.size() > 0) {  //если буфер не пустой
                    RunningPacket = buffer.peek();  //берём пакет из буфера, но пока не удаляем
                    RunningPacket.timeStart = time;
                    isRun = true;
                    if (RunningPacket.duration == 0) {  //если продолжительность нулевая, то сразу обрабатываем пакет
                        buffer.poll();
                        countCompProcessedPackets++;
                        isRun = false;
                    }
                } else {
                    isRun = false;  //ждём появления пакета в буфере
                    RunningPacket = null;
                    break;
                }
            }

            //1. добавляем пакеты в буфер
            if (numHandlingPacket < n) {
                while ((packets[numHandlingPacket].arrival == time) && (packets[numHandlingPacket].isProcessed == false)) {
                    if (buffer.size() < size) {
                        //если буфер пустой, и приходит пакет с нулевой длительностью обработки, и при этом компьютер свободен,
                        //то его сразу же обрабатываем
                        if ((buffer.size() == 0) && (packets[numHandlingPacket].duration == 0) && (!isRun)) {
                            packets[numHandlingPacket].timeStart = time;
                            packets[numHandlingPacket].isProcessed = true;
                            numHandlingPacket++;
                            countCompProcessedPackets++;
                            if (numHandlingPacket < n)
                                continue;
                            else
                                break;
                        }
                        buffer.offer(packets[numHandlingPacket]);
                        packets[numHandlingPacket].isProcessed = true;
                    } else {
                        packets[numHandlingPacket].timeStart = -1;  //пакет не влезает в буфер - отбрасываем
                        countCompProcessedPackets++;
                        packets[numHandlingPacket].isProcessed = true;
                    }
                    numHandlingPacket++;
                    if (numHandlingPacket < n)
                        continue;
                    else
                        break;
                }
            }

            //2. Те пакеты, что компьютер обработал, больше не рассматриваем
            if (isRun) {
                if (RunningPacket.timeStart + RunningPacket.duration == time) {
                    //закончили обработку пакета, и готовы взять следующий из буфера
                    buffer.poll();  //удаляем пакет из буфера
                    countCompProcessedPackets++;
                    isRun = false;
                }
            }

            //3. Работа компьютера
            while (!isRun) {
                if (buffer.size() > 0) {  //если буфер не пустой
                    RunningPacket = buffer.peek();  //берём пакет из буфера, но пока не удаляем
                    RunningPacket.timeStart = time;
                    isRun = true;
                    if (RunningPacket.duration == 0) {  //если продолжительность нулевая, то сразу обрабатываем пакет
                        buffer.poll();
                        countCompProcessedPackets++;
                        isRun = false;
                    }
                } else {
                    isRun = false;  //ждём появления пакета в буфере
                    RunningPacket = null;
                    break;
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
