package CourseObjectBuilder;

public class Lab {
    private String labTime;
    private String labDays;

    public Lab(String labTime, String labDays) {
        labTime = this.labTime;
        labDays = this.labDays;
    }

    public String getLabTime() {
        return labTime;
    }

    public String getLabDays() {
        return labDays;
    }
}
