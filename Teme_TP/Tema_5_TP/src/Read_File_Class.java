

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static java.util.stream.Collectors.summingInt;


public class Read_File_Class {

    private List<MonitoredData> monitored_data_list;
    private DateTimeFormatter date_time_formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    public Read_File_Class() throws IOException {
        this.Read_File();
    }


    private void Read_File() throws IOException {
        try (Stream <String> strr = Files.lines(Paths.get("Activities.txt") ) ) {
            monitored_data_list = strr.map(s -> s.split("\\s\\s+") )
                    .map(stringgs -> new MonitoredData(stringgs[0], stringgs[1], stringgs[2]) )
                    .collect(Collectors.toList() );
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        Task1();
        Task2();
        Task3();
        Task4();
    }


    private void Task1() throws IOException {
        FileOutputStream filee = new FileOutputStream( new File("Task_1.txt") );
        monitored_data_list.forEach(iteratee ->{
            try {
                filee.write( (iteratee.getStart_time() + "\t" + iteratee.getEnd_time() + "\t" +
                        iteratee.getActivity_label() + String.format("%n") ).getBytes() );
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        });
        filee.close();
    }


    private void Task2() throws IOException {
        FileOutputStream filee = new FileOutputStream(new File("Task_2.txt") );
        //int count_distinct_days = 1;
        AtomicInteger count_distinct_days = new AtomicInteger(1);

        List<LocalDate> loc_dt_list = monitored_data_list.stream().map(p-> LocalDateTime.parse(p.getEnd_time(),
                date_time_formatter) ).collect(Collectors.toList() ).stream().map(LocalDateTime::toLocalDate).collect(Collectors.toList() );
        List<LocalDate> local_date_list = loc_dt_list.stream().distinct().collect(Collectors.toList() );

        local_date_list.forEach(interatee->{
            try {
                filee.write( ( interatee + " - Distinct day number => "+ count_distinct_days +
                        String.format("%n") ).getBytes() );
                //count_distinct_days++;
                count_distinct_days.getAndIncrement();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        });
        filee.close();
    }


    private void Task3() throws IOException {
        FileOutputStream filee = new FileOutputStream( new File("Task_3.txt") );
        Map<String, Integer> activity_occurrence = monitored_data_list.stream().collect(Collectors.groupingBy(
                MonitoredData::getActivity_label, Collectors.reducing(0, iteratee-> 1, Integer::sum) ) );

        activity_occurrence.forEach( (key_of_map, value_of_map) -> {
            try {
                filee.write( (key_of_map + " => "+ value_of_map + String.format("%n") ).getBytes() );
            }
            catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        filee.close();
    }


    private void Task4() throws IOException {

        FileOutputStream filee = new FileOutputStream(new File("Task_4.txt") );
        Map<Integer, Map<String, Integer>> map_of_map = monitored_data_list.stream().collect(Collectors.groupingBy(
                iteratee ->LocalDateTime.parse(iteratee.getStart_time(), date_time_formatter).getDayOfMonth(),
                Collectors.groupingBy(MonitoredData::getActivity_label, summingInt(iteratee-> 1 ) ) ) );

        map_of_map.forEach( (key_of_map, value_of_map) -> {
            try {
                filee.write( (" " + key_of_map + "  =>  " + value_of_map.entrySet() +
                        String.format("%n%n") ).getBytes() );
            }
            catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        filee.close();
    }


}
