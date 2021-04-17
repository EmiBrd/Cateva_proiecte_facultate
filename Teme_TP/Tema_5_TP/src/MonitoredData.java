

import java.io.IOException;
import java.util.List;


public class MonitoredData {

    private String start_time;
    private String end_time;
    private String activity_label;
    private List MonitoredData;


    public MonitoredData(String start_time, String end_time, String activity) {
        this.start_time = start_time;
        this.end_time = end_time;
        this.activity_label = activity;
    }


    public MonitoredData() throws IOException {
        new Read_File_Class();
    }


    public String getStart_time() {
        return start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public String getActivity_label() {
        return activity_label;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public void setActivity_label(String activity_label) {
        this.activity_label = activity_label;
    }

    public List getMonitoredData() {
        return MonitoredData;
    }

    public void setMonitoredData(List monitoredData) {
        MonitoredData = monitoredData;
    }



}
