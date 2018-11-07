package printer;
//Test pushing
import java.io.Serializable;
import java.util.Objects;

public class Job implements Serializable {
    public int jobNumber;
    public String fileName;

    public Job(int jobNumber, String filename) {
        this.jobNumber=jobNumber;
        this.fileName=filename;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Job job = (Job) o;
        return jobNumber == job.jobNumber &&
                Objects.equals(fileName, job.fileName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jobNumber, fileName);
    }

    @Override
    public String toString() {
        return "{" +
                "jobNumber=" + jobNumber +
                ", fileName='" + fileName + '\'' +
                '}';
    }
}
