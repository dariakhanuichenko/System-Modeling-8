package ua.kpi;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int key = 0;

        while (key != 4) {
            System.out.println();
            System.out.println("Оберіть завдання:");
            System.out.println("1. 1");
            System.out.println("2. 2");
            System.out.println("3. 3");
            System.out.println("4. Вихід");

            Scanner in = new Scanner(System.in);
            key = in.nextInt();
            switch (key) {
                case 1:
                    TransitionTask1();
                    break;
                case 2:
                    TransitionTask2();
                    break;
                case 3:
                    TransitionTask3();
                    break;
            }
        }
    }

    public static void TransitionTask1() {
        Transition genA = new Transition("Створення повідомлення A");
        Transition queryAB = new Transition("Запит від А на передачу в В");
        Transition replyBA = new Transition("Відповідь від вузла В вузла А");
        Transition sendAB = new Transition("Відправка повідомлення з А в В");
        Transition getInB = new Transition("Отримання повідомлення вузлом В");
        Transition informGetInB = new Transition("Сигнал успішне отримання повідомлення вузлом В");
        //
        Transition genB = new Transition("Створення повідомлення В");
        Transition queryBA = new Transition("Запит від В на передачу в А");
        Transition replyAB = new Transition("Відповідь від вузла А вузла В");
        Transition sendBA = new Transition("Відправка повідомлення з В в А");
        Transition getInA = new Transition("Отримання повідомлення вузлом А");
        Transition informGetInA = new Transition("Сигнал успішне отримання повідомлення вузлом A");

        Place indicator = new Place("Indicator", 1);
        Place incomingA = new Place("IncomingA", 1);
        Place generatedA = new Place("CreatedA", 0);
        Place requestedA = new Place("RequestedA", 0);
        Place admitedA = new Place("AdmitedA", 0);
        Place sentA = new Place("SentA", 0);
        Place gotB = new Place("GotB", 0);
        Place allGotB = new Place("All got in B", 0);
        //
        Place incomingB = new Place("IncomingB", 1);
        Place generatedB = new Place("CreatedB", 0);
        Place requestedB = new Place("RequestedB", 0);
        Place admitedB = new Place("AdmitedB", 0);
        Place sentB = new Place("SentB", 0);
        Place gotA = new Place("GotA", 0);
        Place allGotA = new Place("All got in A", 0);

        Arc indicator_replyBA = new Arc("ind-rba", indicator, replyBA, 1);
        Arc incomingA_genA = new Arc("inA-genA", incomingA, genA, 1);
        Arc genA_incomingA = new Arc("genA-inA", incomingA, 1);
        Arc genA_generatedA = new Arc("genA-gendA", generatedA, 1);
        Arc generatedA_queryAB = new Arc("gendA-qab", generatedA, queryAB, 1);
        Arc queryAB_requestedA = new Arc("qab-reqA", requestedA, 1);
        Arc requestedA_replyBA = new Arc("reqA-rba", requestedA, replyBA, 1);
        Arc replyBA_admitedA = new Arc("rba-admA", admitedA, 1);
        Arc admitedA_sendAB = new Arc("admA-sendAB", admitedA, sendAB, 1);
        Arc sendAB_sentA = new Arc("sendAB-sentA", sentA, 1);
        Arc sentA_getInB = new Arc("sentA-getinB", sentA, getInB, 1);
        Arc getInB_gotB = new Arc("getinB_gotB", gotB, 1);
        Arc gotB_infGotInB = new Arc("gotB_infgB", gotB, informGetInB, 1);
        Arc infGotInB_indicator = new Arc("infgB_ind", indicator, 1);
        Arc infGotInB_allGotB = new Arc("infgB_allGotB", allGotB, 1);
        //
        Arc indicator_replyAB = new Arc("ind-rab", indicator, replyAB, 1);
        Arc incomingB_genB = new Arc("inB-genB", incomingB, genB, 1);
        Arc genB_incomingB = new Arc("genB-inB", incomingB, 1);
        Arc genB_generatedB = new Arc("genB-gendB", generatedB, 1);
        Arc generatedB_queryBA = new Arc("gendB-qba", generatedB, queryBA, 1);
        Arc queryBA_requestedB = new Arc("qba-reqB", requestedB, 1);
        Arc requestedB_replyAB = new Arc("reqB-rab", requestedB, replyAB, 1);
        Arc replyAB_admitedB = new Arc("rab-admB", admitedB, 1);
        Arc admitedB_sendBA = new Arc("admB-sendBA", admitedB, sendBA, 1);
        Arc sendBA_sentB = new Arc("sendBA-sentB", sentB, 1);
        Arc sentB_getInA = new Arc("sentB-getinA", sentB, getInA, 1);
        Arc getInA_gotA = new Arc("getinA_gotA", gotA, 1);
        Arc gotA_infGotInA = new Arc("gotA_infgA", gotA, informGetInA, 1);
        Arc infGotInA_indicator = new Arc("infgA_ind", indicator, 1);
        Arc infGotInA_allGotA = new Arc("infgA_allGotA", allGotA, 1);

        genA.arcsIn.add(incomingA_genA);
        genA.arcsOut.add(genA_generatedA);
        genA.arcsOut.add(genA_incomingA);
        queryAB.arcsIn.add(generatedA_queryAB);
        queryAB.arcsOut.add(queryAB_requestedA);
        replyBA.arcsIn.add(requestedA_replyBA);
        replyBA.arcsIn.add(indicator_replyBA);
        replyBA.arcsOut.add(replyBA_admitedA);
        sendAB.arcsIn.add(admitedA_sendAB);
        sendAB.arcsOut.add(sendAB_sentA);
        getInB.arcsIn.add(sentA_getInB);
        getInB.arcsOut.add(getInB_gotB);
        informGetInB.arcsIn.add(gotB_infGotInB);
        informGetInB.arcsOut.add(infGotInB_allGotB);
        informGetInB.arcsOut.add(infGotInB_indicator);
        //
        genB.arcsIn.add(incomingB_genB);
        genB.arcsOut.add(genB_generatedB);
        genB.arcsOut.add(genB_incomingB);
        queryBA.arcsIn.add(generatedB_queryBA);
        queryBA.arcsOut.add(queryBA_requestedB);
        replyAB.arcsIn.add(requestedB_replyAB);
        replyAB.arcsIn.add(indicator_replyAB);
        replyAB.arcsOut.add(replyAB_admitedB);
        sendBA.arcsIn.add(admitedB_sendBA);
        sendBA.arcsOut.add(sendBA_sentB);
        getInA.arcsIn.add(sentB_getInA);
        getInA.arcsOut.add(getInA_gotA);
        informGetInA.arcsIn.add(gotA_infGotInA);
        informGetInA.arcsOut.add(infGotInA_allGotA);
        informGetInA.arcsOut.add(infGotInA_indicator);

        List<Place> places = Arrays.asList(incomingA, generatedA, requestedA, admitedA, sentA, gotB, allGotB, indicator,
                incomingB, generatedB, requestedB, admitedB, sentB, gotA, allGotA);
        List<Transition> transitions = Arrays.asList(genA, queryAB, replyBA, sendAB, getInB, informGetInB,
                genB, queryBA, replyAB, sendBA, getInA, informGetInA);
        Model task1 = new Model(places, transitions);
        task1.simulate(100);
        System.out.println();
        System.out.println("К-ть отриманих в  B: " + allGotB.markersCount);
        System.out.println("К-ть отриманих в  A: " + allGotA.markersCount);
    }

    public static void TransitionTask2() {
        int n;
        System.out.println("Введіть обмеження буфера:");
        Scanner in = new Scanner(System.in);
        n = in.nextInt();

        Transition processor = new Transition("Placessor");
        Transition consumer = new Transition("Consumer");

        Place incoming = new Place("Incoming", 1);
        Place buffer = new Place("Buffer", 0);
        Place stopPlaceProcessorRule = new Place("Free in buffer", n);
        Place consumedNumber = new Place("Consumed", 0);

        Arc incoming_processor = new Arc("inc-proc", incoming, processor, 1);
        Arc processor_buffer = new Arc("put", buffer, 1);
        Arc processor_incoming = new Arc("proc-inc", incoming, 1);
        Arc buffer_consumer = new Arc("take", buffer, consumer, 1);
        Arc consumer_stoprule = new Arc("cons-spr", stopPlaceProcessorRule, 1);
        Arc stoprule_processor = new Arc("spr-proc", stopPlaceProcessorRule, processor, 1);
        Arc consumer_consumed = new Arc("cons-count", consumedNumber, 1);

        processor.arcsIn.add(incoming_processor);
        processor.arcsIn.add(stoprule_processor);
        processor.arcsOut.add(processor_buffer);
        processor.arcsOut.add(processor_incoming);
        consumer.arcsIn.add(buffer_consumer);
        consumer.arcsOut.add(consumer_stoprule);
        consumer.arcsOut.add(consumer_consumed);

        List<Place> places = Arrays.asList(incoming, buffer, stopPlaceProcessorRule, consumedNumber);
        List<Transition> transitions = Arrays.asList(processor, consumer);

        Model task2 = new Model(places, transitions);
        task2.simulate(100);
        System.out.println();
        System.out.println("Середня к-ть маркерів у буфері: " + buffer.markersAvg);
    }

    public static void TransitionTask3() {
        int n;
        System.out.println("Введіть кількість ресурсу (>3):");
        Scanner in = new Scanner(System.in);
        n = in.nextInt();

        Transition type_1_create = new Transition("Create type 1");
        Transition type_1_process = new Transition("Placerocess type 1");
        Transition type_2_create = new Transition("Create type 2");
        Transition type_2_process = new Transition("Placerocess type 2");
        Transition type_3_create = new Transition("Create type 3");
        Transition type_3_process = new Transition("Placerocess type 3");

        Place resources = new Place("Resources", n);
        Place incoming1 = new Place("Incoming t1", 1);
        Place incoming2 = new Place("Incoming t2", 1);
        Place incoming3 = new Place("Incoming t3", 1);
        Place created1 = new Place("Created t1", 0);
        Place created2 = new Place("Created t2", 0);
        Place created3 = new Place("Created t3", 0);
        Place processed1 = new Place("Placerocessed t1", 0);
        Place processed2 = new Place("Placerocessed t2", 0);
        Place processed3 = new Place("Placerocessed t3", 0);

        Arc incoming1_create1 = new Arc("inc1-cr1", incoming1, type_1_create, 1);
        Arc create1_incoming1 = new Arc("cr1-inc1", incoming1, 1);
        Arc create1_created1 = new Arc("cr1-crd1", created1, 1);
        Arc created1_process1 = new Arc("crd1-pr1", created1, type_1_process, 1);
        Arc resources_process1 = new Arc("r-pr1", resources, type_1_process, n);
        Arc process1_resources = new Arc("pr1-r", resources, n);
        Arc process1_processed1 = new Arc("pr1-prd1", processed1, 1);
        //
        Arc incoming2_create2 = new Arc("inc2-cr2", incoming2, type_2_create, 1);
        Arc create2_incoming2 = new Arc("cr2-inc2", incoming2, 1);
        Arc create2_created2 = new Arc("cr2-crd2", created2, 1);
        Arc created2_process2 = new Arc("crd2-pr2", created2, type_2_process, 1);
        Arc resources_process2 = new Arc("r-pr2", resources, type_2_process, n / 3);
        Arc process2_resources = new Arc("pr2-r", resources, n / 3);
        Arc process2_processed2 = new Arc("pr2-prd2", processed2, 1);
        //
        Arc incoming3_create3 = new Arc("inc3-cr3", incoming3, type_3_create, 1);
        Arc create3_incoming3 = new Arc("cr3-inc3", incoming3, 1);
        Arc create3_created3 = new Arc("cr3-crd3", created3, 1);
        Arc created3_process3 = new Arc("crd3-pr3", created3, type_3_process, 1);
        Arc resources_process3 = new Arc("r-pr3", resources, type_3_process, n / 2);
        Arc process3_resources = new Arc("pr3-r", resources, n / 2);
        Arc process3_processed3 = new Arc("pr3-prd3", processed3, 1);

        type_1_create.arcsIn.add(incoming1_create1);
        type_1_create.arcsOut.add(create1_incoming1);
        type_1_create.arcsOut.add(create1_created1);
        type_1_process.arcsIn.add(created1_process1);
        type_1_process.arcsIn.add(resources_process1);
        type_1_process.arcsOut.add(process1_processed1);
        type_1_process.arcsOut.add(process1_resources);

        type_2_create.arcsIn.add(incoming2_create2);
        type_2_create.arcsOut.add(create2_incoming2);
        type_2_create.arcsOut.add(create2_created2);
        type_2_process.arcsIn.add(created2_process2);
        type_2_process.arcsIn.add(resources_process2);
        type_2_process.arcsOut.add(process2_processed2);
        type_2_process.arcsOut.add(process2_resources);

        type_3_create.arcsIn.add(incoming3_create3);
        type_3_create.arcsOut.add(create3_incoming3);
        type_3_create.arcsOut.add(create3_created3);
        type_3_process.arcsIn.add(created3_process3);
        type_3_process.arcsIn.add(resources_process3);
        type_3_process.arcsOut.add(process3_processed3);
        type_3_process.arcsOut.add(process3_resources);

        List<Place> places = Arrays.asList(resources, incoming1, incoming2, incoming3,
                created1, created2, created3,
                processed1, processed2, processed3);
        List<Transition> transitions = Arrays.asList(type_1_create, type_1_process, type_2_create, type_2_process, type_3_create, type_3_process);
        Model task3 = new Model(places, transitions);
        task3.simulate(100);

        System.out.println();
        int numberAllPlaceProcessed = processed1.markersCount + processed2.markersCount + processed3.markersCount;
        System.out.println("К-ть виконаних: " + numberAllPlaceProcessed);
        System.out.printf("%4s | %10s | %10s\n", "Тип задачі", "К-ть виконаних", "% від усіх");
        System.out.printf("%10s | %14d |%7.3f\n", "1", processed1.markersCount, (((double) processed1.markersCount) / numberAllPlaceProcessed) * 100);
        System.out.printf("%10s | %14d |%7.3f\n", "2", processed2.markersCount, (((double) processed2.markersCount) / numberAllPlaceProcessed) * 100);
        System.out.printf("%10s | %14d |%7.3f\n", "3", processed3.markersCount, (((double) processed3.markersCount) / numberAllPlaceProcessed) * 100);
    }
}

